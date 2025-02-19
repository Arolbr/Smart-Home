$(document).ready(function () {
    // 切换子菜单显示/隐藏
    $(".nav-header").on("click", function() {
        let target = $(this).data("target");
        $(target).toggle();
    });

    // 打开添加用户的模态窗口
    $('#add-user').on('click', function () {
        $('#add-user-modal').show();
    });

    // 关闭添加用户的模态窗口
    $('#close-add-user-modal').on('click', function () {
        $('#add-user-modal').hide();
    });

    // 提交添加用户
    $('#add-user-form').on('submit', function (e) {
        e.preventDefault();
        let addUsername = $('#username-input').val();
        let addPassword = $('#password-input').val();
        let addPermission = $('#permission-input').val();
        let username = sessionStorage.getItem('AdminUsername');
        let password = sessionStorage.getItem('AdminPassword');
        let token = sessionStorage.getItem('AdminToken');
        $.ajax({
            url: '/intercept/add',
            type: 'POST',
            data: {addUsername: addUsername, addPassword: addPassword, addPermission: addPermission, username: username, password: password, token: token},
            dataType: 'json',
            success: function (data) {
                console.log(data);
                if (data.data === true){
                    alert('添加成功');
                    return;
                }
                alert('添加失败');
            }
        });
        // 请求成功后关闭模态窗口
        $('#add-user-modal').hide();
        window.location.reload();
    });

    // 打开修改用户的模态窗口
    $('#modify-user').on('click', function () {
        $('#modify-user-modal').show();
    });

    // 关闭修改用户的模态窗口
    $('#close-modify-user-modal').on('click', function () {
        $('#modify-user-modal').hide();
    });

    // 提交修改用户
    $('#modify-user-submit').on('click', function () {
        let id = $('#modify-user-id').val();
        let username1 = $('#modify-username').val();
        let password1 = $('#modify-password').val();
        let permission = $('#modify-permission').val();
        let username = sessionStorage.getItem('AdminUsername');
        let password = sessionStorage.getItem('AdminPassword');
        let token = sessionStorage.getItem('AdminToken');
        // 进行修改用户的AJAX请求
        console.log('修改用户', id, username1, password1, permission);
        $.ajax({
            url: '/intercept/update',
            type: 'POST',
            data: {id: id, username1: username1, password1: password1, permission: permission, username: username, password: password, token: token},
            dataType: 'json',
            success: data => {
                console.log(data);
                if(data.data === true){
                    alert('修改成功');
                    return;
                }
                alert('修改失败');
            }
        });
        $('#modify-user-modal').hide();
        window.location.reload();
    });

    // 打开删除用户的模态窗口
    $('#delete-user').on('click', function () {
        $('#delete-user-modal').show();
    });

    // 关闭删除用户的模态窗口
    $('#close-delete-user-modal').on('click', function () {
        $('#delete-user-modal').hide();
    });

    // 提交删除用户
    $('#delete-user-submit').on('click', function () {
        let userId = $('#delete-user-id').val();
        let username = sessionStorage.getItem('AdminUsername');
        let password = sessionStorage.getItem('AdminPassword');
        let token = sessionStorage.getItem('AdminToken');
        // 进行删除用户的AJAX请求
        console.log('删除用户', userId);
        $.ajax({
            url: '/intercept/delete',
            type: 'POST',
            data: {id: userId, username: username, token: token, password: password},
            dataType: 'json',
            success: data => {
                console.log(data);
                if(data.data === true){
                    alert('删除成功');
                    return;
                }
                alert('删除失败');
            }
        });
        $('#delete-user-modal').hide();
        window.location.reload();
    });

    // 打开添加设备的模态窗口
    $('#add-device').on('click', function () {
        $('#add-device-modal').show();
    });

    // 关闭添加设备的模态窗口
    $('#close-add-device-modal').on('click', function () {
        $('#add-device-modal').hide();
    });

    // 提交添加设备
    $('#add-device-form').on('submit', function (e) {
        e.preventDefault();
        let name = $('#device-name-input').val();
        let description = $('#device-description-input').val();
        let type = $('#device-type-input').val();
        let status = $('#device-status-input').val();
        let rooms = $('#device-rooms-input').val();
        let ip = $('#device-ip-input').val();
        let images = $('#device-images-input').val();
        let username = sessionStorage.getItem('AdminUsername');
        let password = sessionStorage.getItem('AdminPassword');
        let token = sessionStorage.getItem('AdminToken');
        // 进行设备添加的AJAX请求
        console.log('添加设备', name, description, type, status, rooms, ip, images);
        $.ajax({
            url: '/intercept/addDevice',
            type: 'POST',
            data: {name: name, description: description, type: type, status: status, rooms: rooms, ip: ip, images: images, username: username, password: password, token: token},
            dataType: 'json',
            success: data => {
                console.log(data);
                if(data.data === true){
                    alert('添加成功');
                    return;
                }
                alert('添加失败');
            }
        });
        $('#add-device-modal').hide();
        window.location.reload();
    });

    // 打开修改设备的模态窗口
    $('#modify-device').on('click', function () {
        $('#modify-device-modal').show();
    });

    // 关闭修改设备的模态窗口
    $('#close-modify-device-modal').on('click', function () {
        $('#modify-device-modal').hide();
    });

    // 提交修改设备
    $('#modify-device-submit').on('click', function () {
        let id = $('#modify-device-id').val();
        let name = $('#modify-device-name').val();
        let description = $('#modify-device-description').val();
        let type = $('#modify-device-type').val();
        let status = $('#modify-device-status').val();
        let rooms = $('#modify-device-rooms').val();
        let ip = $('#modify-device-ip').val();
        let images = $('#modify-device-images').val();
        let username = sessionStorage.getItem('AdminUsername');
        let password = sessionStorage.getItem('AdminPassword');
        let token = sessionStorage.getItem('AdminToken');
        // 进行设备修改的AJAX请求
        console.log('修改设备', id, name, description, type, status, rooms, ip, images);
        $.ajax({
            url: '/intercept/updateDevice',
            type: 'POST',
            data: {id: id, name: name, description: description, type: type, status: status, rooms: rooms, ip: ip, images: images, username: username, password: password, token: token},
            dataType: 'json',
            success: data => {
                console.log(data);
                if(data.data === true){
                    alert('修改成功');
                    return;
                }
                alert('修改失败');
            }
        });
        $('#modify-device-modal').hide();
        window.location.reload();
    });

    // 打开删除设备的模态窗口
    $('#delete-device').on('click', function () {
        $('#delete-device-modal').show();
    });

    // 关闭删除设备的模态窗口
    $('#close-delete-device-modal').on('click', function () {
        $('#delete-device-modal').hide();
    });

    // 提交删除设备
    $('#delete-device-submit').on('click', function () {
        let deviceId = $('#delete-device-id').val();
        let username = sessionStorage.getItem('AdminUsername');
        let password = sessionStorage.getItem('AdminPassword');
        let token = sessionStorage.getItem('AdminToken');
        // 进行删除设备的AJAX请求
        console.log('删除设备', deviceId);
        $.ajax({
            url: '/intercept/deleteDevice',
            type: 'POST',
            data: {id: deviceId, username: username, password: password, token: token},
            dataType: 'json',
            success: data => {
                console.log(data);
                if(data.data === true){
                    alert('删除成功');
                    return;
                }
                alert('删除失败');
            }
        });
        $('#delete-device-modal').hide();
        window.location.reload();
    });

    let username = sessionStorage.getItem("AdminUsername");
    let password = sessionStorage.getItem("AdminPassword");
    let token = sessionStorage.getItem("AdminToken");
    let permission = sessionStorage.getItem("AdminPermission");
    let html= '';
    $.ajax({
        url: '/intercept/getAllUser',
        type: 'GET',
        dataType: 'json',
        data: {username: username, password: password, token: token, permission: permission},
        success: data => {
            console.log(data);
            // 将用户数据动态渲染到页面中
            data.data.forEach(user => {
                html += `<tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.permission}</td>
                </tr>`;
            });
            $('#user-data-tbody').html(html);
        }
    })

    // 点击设备管理后显示所有设备
    $('#deviceAdmin').on('click', ()=>{
        let username = sessionStorage.getItem('AdminUsername');
        let password = sessionStorage.getItem('AdminPassword');
        let token = sessionStorage.getItem('AdminToken');
        $.ajax({
            url: '/intercept/getAllDevices',
            type:'GET',
            dataType:'json',
            data: {username: username, password: password, token: token},
            success: data => {
                console.log(data);
                let html = '';
                $('#main2').text('名称');
                $('#main3').text('说明');
                $('#device1').text('类型');
                $('#device2').text('状态');
                $('#device3').text('房间');
                $('#device4').text('IP');
                $('#device5').text('图片');
                let status = '故障/错误'
                data.data.forEach(device => {
                    if(device.status === 0){
                        status = '关闭'
                    } else if(device.status === 1){
                        status = '运行'
                    }else if(device.status === 2){
                        status = '待命'
                    }
                    html += `<tr>
                        <td>${device.id}</td>
                        <td>${device.name}</td>
                        <td>${device.description}</td>
                        <td>${device.type}</td>
                        <td>${status}</td>
                        <td>${device.rooms}</td>
                        <td>${device.ip}</td>
                        <td>${device.images}</td>
                    </tr>`;
                });
                $('#user-data-tbody').html(html);
            }
        })
    });

    // 点击用户管理后显示所有用户
    $('#userAdmin').on('click', ()=>{
        let username = sessionStorage.getItem('AdminUsername');
        let password = sessionStorage.getItem('AdminPassword');
        let token = sessionStorage.getItem('AdminToken');
        $.ajax({
            url: '/intercept/getAllUser',
            type: 'GET',
            dataType: 'json',
            data: {username: username, password: password, token: token},
            success: data => {
                console.log(data);
                let html = '';
                $('#main2').text('帐号');
                $('#main3').text('权限');
                $('#device1').text(' ');
                $('#device2').text(' ');
                $('#device3').text(' ');
                $('#device4').text(' ');
                $('#device5').text(' ');
                data.data.forEach(user => {
                    html += `<tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.permission}</td>
                    </tr>`;
                });
                $('#user-data-tbody').html(html);
            }
        })
    })
});