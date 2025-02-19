$(()=>{
   $('#login').on('click',()=>{
      event.preventDefault();
      $.ajax({
         url:'/login',
         dataType: 'json',
         type: 'POST',
         data: {username: $('#username').val(), password: $('#password').val()},
         success: data => {
            if(data.code === '200'){
               let userData = data.data[0]
               alert('登录成功')
               let token = userData.username + userData.permission + userData.password;
               sessionStorage.setItem('token', token)
               sessionStorage.setItem('username', userData.username)
               sessionStorage.setItem('password', userData.password)
               sessionStorage.setItem('permission', userData.permission)
               window.location.href = '../html/homePage.html'
            }else{
               alert('用户名或密码错误')
            }
         }
      });
   });

   // 点击后端管理按钮显示模态窗口
   $('#admin-login-btn').on('click', function() {
      $('#admin-login-modal').fadeIn();  // 显示模态窗口
   });

   // 点击关闭按钮隐藏模态窗口
   $('#close-modal').on('click', function() {
      $('#admin-login-modal').fadeOut();  // 隐藏模态窗口
   });

   // 点击模态窗口外部区域关闭模态窗口
   $(window).on('click', function(event) {
      if ($(event.target).is('#admin-login-modal')) {
         $('#admin-login-modal').fadeOut();  // 隐藏模态窗口
      }
   });

   // 后端管理登录提交操作
   $('#admin-login-form').on('submit', function(event) {
      event.preventDefault();  // 防止表单默认提交行为

      let adminUsername = $('#admin-username').val();
      let adminPassword = $('#admin-password').val();
      let adminPermission = $('#admin-permission').val(); // 获取权限
      console.log(adminUsername)
      console.log(adminPassword)
      console.log(adminPermission)
      // 发送 AJAX 请求进行后端登录验证
      $.ajax({
         url: '/admin/login',  // 后端管理登录的接口
         type: 'POST',
         dataType: 'json',
         data: {
            username: adminUsername,
            password: adminPassword,
            permission: adminPermission // 将权限值传给后端
         },
         success: data => {
            console.log(data)
            if (data.code === '200') {
               alert('登录成功');
               let userData = data.data[0]
               let token = userData.username + userData.permission + userData.password;
               sessionStorage.setItem('AdminToken', token)
               sessionStorage.setItem('AdminUsername', userData.username)
               sessionStorage.setItem('AdminPassword', userData.password)
               sessionStorage.setItem('AdminPermission', userData.permission)
               // 跳转到后端管理页面
               window.location.href = '../html/admin.html';
            } else {
               alert('登录失败：' + data.message);
            }
         },
         error: function() {
            alert('发生错误，请稍后再试');
         }
      });

      // 登录成功后隐藏模态窗口
      $('#admin-login-modal').fadeOut();
   });
});