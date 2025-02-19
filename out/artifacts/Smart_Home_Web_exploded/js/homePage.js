$(() => {
    let username = sessionStorage.getItem("username");
    let password = sessionStorage.getItem("password");
    let token = sessionStorage.getItem("token");
    let permission = sessionStorage.getItem("permission");

    // 全屋家具
    controlAll(username, password, token, permission);

    // 绑定房间按钮
    $('#room-all').on('click', () => controlAll(username, password, token, permission));
    $('#room-master').on('click', () => otherRoom(username, password, token, permission, '主卧'));
    $('#room-living').on('click', () => otherRoom(username, password, token, permission, '客厅'));
    $('#room-kitchen').on('click', () => otherRoom(username, password, token, permission, '厨房'));
    $('#room-bathroom').on('click', () => otherRoom(username, password, token, permission, '厕所'));

    // 事件委托，监听动态生成的按钮
    $('.furniture-container').on('click', '.furniture-switch', function() {
        const ip = $(this).data('ip');
        const id = $(this).data('id');
        openOrCloseDevice(username, password, token, permission, ip, id);
    });

    // 点击消息图标时跳转通知页面
    $('#messages').on('click', function() {
        window.location="../html/notice.html";
    });

    // 获取城市后获取天气
    getCity(username,password,token);

    // 设置自动刷新
    refreshAtMidnight();

});

// 全屋家居的函数
function controlAll(username, password, token, permission){
    $.ajax({
        url: '/intercept/getAllDevices',
        dataType: 'json',
        type: 'GET',
        data: { username, password, token, permission },
        success: data => {
            console.log(data);
            let html = '';
            if (!data.success) {
                alert('暂无家居');
                $('.furniture-container').html(html);
                return;
            }
            data.data.forEach(device => {
                html += `
                    <div class="furniture-item">
                        <div class="furniture-img">
                            <img src="${device.images}" alt="${device.name}">
                            <button class="furniture-switch" data-ip="${device.ip}" data-id="${device.id}">
                                ${device.status === 1 ? '关闭' : '打开'}
                            </button>
                        </div>
                        <div class="furniture-name">${device.name}（${device.rooms}）</div>
                    </div>
                `;
            });
            $('.furniture-container').html(html);

            // 设置按钮颜色
            $('.furniture-switch').each(function() {
                let status = $(this).text().trim();
                $(this).css('background-color', status === '关闭' ? 'red' : 'green');
            });
        },
        error: () => alert('出现未知错误，请联系开发者或管理员解决')
    });
}

// 其余房间（主卧，厨房，客厅，厕所）
function otherRoom(username, password, token, permission, room){
    $.ajax({
        url: '/intercept/getDeviceByRoom',
        dataType: 'json',
        type: 'GET',
        data: { username, password, token, permission, room },
        success: data => {
            let html = '';
            console.log(data);
            if (!data.success) {
                alert('暂无家居');
                $('.furniture-container').html(html);
                return;
            }
            data.data.forEach(device => {
                html += `
                    <div class="furniture-item">
                        <div class="furniture-img">
                            <img src="${device.images}" alt="${device.name}">
                            <button class="furniture-switch" data-ip="${device.ip}" data-id="${device.id}">
                                ${device.status === 1 ? '关闭' : '打开'}
                            </button>
                        </div>
                        <div class="furniture-name">${device.name}</div>
                    </div>
                `;
            });
            $('.furniture-container').html(html);

            // 设置按钮颜色
            $('.furniture-switch').each(function() {
                let status = $(this).text().trim();
                $(this).css('background-color', status === '关闭' ? 'red' : 'green');
            });
        },
        error: () => alert('出现未知错误，请联系开发者或管理员解决')
    });
}

// 打开关闭设备
function openOrCloseDevice(username, password, token, permission, ip, id){
    $.ajax({
        url: '/intercept/openOrCloseDevice',
        type: 'POST',
        data: { username, password, token, permission, ip, id },
        dataType: 'json',
        success: data => {
            console.log(data);
            if(data.success){
                let button = $(`button[data-id="${id}"]`);
                let newStatus = button.text().trim() === '打开' ? '关闭' : '打开';
                button.text(newStatus);
                button.css('background-color', newStatus === '关闭' ? 'red' : 'green');
            }
        },
        error: () => alert('出现未知错误，请联系开发者或管理员解决')
    });
}

// 天气情况
function getWeather(city){
    $.ajax({
        url: `https://api.vvhan.com/api/weather`,
        type: 'GET',
        dataType: 'json',
        data:{city: city},
        success: data => {
            console.log(data);
            if(data.message === '天气仅支持国内天气'){
                alert('城市天气数据获取失败，请关闭VPN或代理后刷新页面');
                return;
            }
            console.log("所属城市:" + data.city);
            $('#city').text(data.city);
            let air = data.air.aqi;
            let air_name = data.air.aqi_name;
            let air_level = data.air.aqi_level;
            let wind_speed = data.data.fengli;
            let wea = data.data.type;
            let wind_direction = data.data.fengxiang;
            let low_temperature = data.data.low;
            let high_temperature = data.data.high;
            let time = data.data.date;
            let week = data.data.week;
            $('#weather').html("<b>空气指数：</b>" + air + "&nbsp;&nbsp;&nbsp;<b>空气等级："+ air_level +"级--</b>" + air_name +
                "&nbsp;&nbsp;&nbsp;<b>风速：</b>" + wind_speed + "&nbsp;&nbsp;&nbsp;<b>天气：</b>" + wea + "&nbsp;&nbsp;&nbsp;<b>风向：</b>" + wind_direction
            );
            $('#air_temperature').html("<b>最低温度：</b>" + low_temperature + "&nbsp;&nbsp;&nbsp;<b>最高温度：</b>" + high_temperature +
                "&nbsp;&nbsp;&nbsp;<b>更新时间：</b>" + time + "&nbsp;&nbsp;&nbsp;" + week
            );
        },
        error: () => alert('出现未知错误，请联系开发者或管理员解决')
    });
}

// 获取经纬度城市所在位置
function getCity(username, password, token){
    console.log("查询城市调用")
    // 请求用户允许获取位置信息
    getUserLocation(username, password, token);
}

// 获取浏览器定位
function getUserLocation(username, password, token) {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            function(position) {
                let lat = position.coords.latitude;
                let lon = position.coords.longitude;
                console.log(`浏览器定位成功: 纬度 ${lat}, 经度 ${lon}`);
                fetchCityFromCoordinates(lat, lon, username, password, token);
            },
            function(error) {
                console.warn("浏览器定位失败:", error.message);
                console.log("尝试使用高德地图API进行定位...");
                getLocationFromGaode();
            },
            { enableHighAccuracy: true, timeout: 5000 }
        );
    } else {
        console.log("浏览器不支持Geolocation，尝试高德地图API...");
        getLocationFromGaode();
    }
}

// 高德API定位
function getLocationFromGaode() {
    $.ajax({
        url: "https://restapi.amap.com/v3/ip",
        type: "GET",
        data: {
            key: "a61fedfdc0f0feef8f2d86a28821e035"
        },
        success: function(data) {
            if (data.status === "1" && data.city) {
                console.log("高德IP定位成功:", data.city);
                getWeather(data.city);
            } else {
                console.log("高德IP定位失败，尝试使用IP定位...");
                getLocationFromIP();
            }
        },
        error: function() {
            console.warn("高德API请求失败，尝试IP定位...");
            getLocationFromIP();
        }
    });
}

// IP定位
function getLocationFromIP() {
    $.get("https://ip-api.com/json/?lang=zh-CN", function(data) {
        if (data && data.city) {
            console.log("IP定位成功:", data.city);
            getWeather(data.city);
        } else {
            console.warn("IP定位失败，无法获取用户位置");
        }
    });
}

// 通过经纬度获取城市
function fetchCityFromCoordinates(lat, lon, username, password, token) {
    $.ajax({
        url: '/intercept/getCity',
        type: 'GET',
        dataType: 'json',
        data: {longitude: lon, latitude: lat, username: username, password: password, token: token},
        success: function(data) {
            try {
                const response = JSON.parse(data.data);
                console.log("解析后的数据：", response);
                if (response && response.regeocode && response.regeocode.addressComponent) {
                    let city = response.regeocode.addressComponent.city || response.regeocode.addressComponent.province;
                    getWeather(city);
                } else {
                    alert('出现未知错误，请联系开发者或者管理员解决');
                    console.warn("无法提取城市信息，数据格式异常");
                }
            } catch (e) {
                alert('出现未知错误，请联系开发者或者管理员解决');
                console.error("JSON 解析错误:", e);
            }
        },
        error: function() {
            alert('出现未知错误，请联系开发者或者管理员解决');
            console.error("获取城市信息失败");
        }
    });
}

// 设置为每天凌晨十二点自动刷新页面
function refreshAtMidnight(){
    // 获取当前时间
    let nowDate = new Date();
    // 计算午夜的时间戳
    let midnight = new Date();
    midnight.setHours(24,0,0,0);
    // 计算时间差
    let time = midnight - nowDate;
    // 设置一个定时器，到时间就刷新页面
    setTimeout(() => {
       location.reload();
    }, time);
}
