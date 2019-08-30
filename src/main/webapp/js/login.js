layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;
    
    //登录按钮事件
    form.on("submit(login)", function (data) {
        $.ajax({
            type: "POST",
            url: ctx+"/login",
            data:$("#form").serialize(),
            success: function (result) {
                if (result.code == 0) {//登录成功
                    parent.location.href = ctx+'/index';
                } else{
                    layer.msg(result.msg, {icon: 5});
                    refreshCode();
                }
            }
        });
        return false;
    })
});
//刷新验证码
function refreshCode(){
    var captcha = document.getElementById("captcha");
    //加随机参数,解决点击验证码不刷新情况
    captcha.src = ctx+"/vcode?t=" + new Date().getTime();
}

//修改用户密码
function forgetUserPwd(){
    layer.open({
        type:2, //2表示frame
        title:'密码找回',
        area:['40%','60%'],
        shadeClose:false,
        closeBtn:1,
        content:ctx + '/pages/forgetpwd.jsp',
        end:function(){
            // table.reload('table-user'); //对应table.render中定义的id
        }
    });
}