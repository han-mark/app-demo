layui.use(['jquery','form'], function(){
    var form = layui.form;
    var $ = layui.$;//重点处

    //密码框验证规则
    form.verify({
        pass: [/^[\S]{6,12}$/,'密码必须6到12位，且不能出现空格']
        ,
        repass: function(value){
            var repassvalue = $('#password').val();
            if(value != repassvalue){
                return '两次输入的密码不一致!';
            }
        }
    });

    $("#opassword").blur(function(){
        $.ajax({
            type: "get",
            url: ctx + "/checkUserPwd/"+$("#opassword").val(),
            success:function(data){
                if(data.code!=0){
                    layer.msg(data.msg);
                    $("#opassword").val("");
                    $("#opassword").focus();
                }
            }
        });
    });

    //监听提交
    form.on('submit(formDemo)', function(data){
        if(!data.field.password){
            data.field.password = null;
        }
        $.ajax({
            type : 'get',
            url : ctx + '/modifyPassword/'+$("#password").val(),
            success : function(e){
                //e为后台返回的数据
                if(e.code == 0){
                    //当你在iframe页面关闭自身时
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                }
                layer.msg(e.msg);
            },
            error : function(){
                layer.msg("服务器开小差了，请稍后再试...");
            }
        });
    });
});