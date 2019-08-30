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

    $("#code-Btn").click(function() {
        //检验邮箱的正则表达式
        var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
        var email = $("#email").val();
        //先检验数据的合法性
        if (email == null || email == '') {
            layer.msg("邮箱不可以为空！", {
                icon : 5,
                time : 1000,
                end : function() {}
            });
        } else if (re.test(email) == false) {
            layer.msg("邮箱格式不合法！", {
                icon : 5,
                time : 1000,
                end : function() {}
            });
        } else {
            getCode(email);
        }
    });

    //获取验证码
    function getCode(email) {
        var load = null;
        $.ajax({
            url : ctx + '/getForgotPwdCode?email=' + email,
            type : 'get',
            aysnc : false,
            dataType : 'json',
            beforeSend : function() {
                load = layer.load(2);
            },
            success : function(data) {
                if (data.code == 0) {
                    layer.msg(data.msg, {
                        icon : 6,
                        time : 1000,
                        end : function() {}
                    });

                    var seconds = 60;
                    $("#email").attr('readonly', 'readonly');
                    $("#code-Btn").attr('disabled', 'true');
                    $("#code-Btn").addClass('layui-btn-disabled');
                    $("#code-Btn").text("");
                    $("#code-Btn").text(seconds + 's后可重新获取');
                    var time = setInterval(function() {
                        seconds--;
                        $("#code-Btn").text(seconds + 's后可重新获取');
                        if (seconds == 0) {
                            clearInterval(time);
                            $("#code-Btn").removeAttr('disabled');
                            $("#code-Btn").removeClass('layui-btn-disabled');
                            $("#code-Btn").text('获取验证码');
                            $("#email").removeAttr('readonly');
                        }
                    }, 1000);

                } else {
                    layer.msg(data.msg, {
                        icon : 5,
                        time : 1000,
                        end : function() {}
                    })
                }
            },
            error : function() {
                layer.msg('服务器出小差了，请稍等...');
            },
            complete : function() {
                layer.close(load);
            }
        });
    }

    //监听提交
    form.on('submit(formDemo)', function(data){
        if(!data.field.password){
            data.field.password = null;
        }
        $.ajax({
            type : 'get',
            url : ctx + '/resetPwd?email=' + data.field.email + '&code=' +data.field.code + '&password=' +data.field.password,
            success : function(e){
                layer.msg(e.msg, {
                    icon : 5,
                    time : 1000,
                    end : function() {
                        //e为后台返回的数据
                        if(e.code == 0){
                            //当你在iframe页面关闭自身时
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index); //再执行关闭
                        }
                    }
                });

            },
            error : function(){
                layer.msg("服务器开小差了，请稍后再试...");
            }
        });
    });
});