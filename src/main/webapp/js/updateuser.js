layui.use(['jquery','form','laydate'], function(){
    var form = layui.form;
    var $ = layui.$;//重点处
    var param = getParam();

    //渲染时间控件
    var laydate = layui.laydate;
    //执行一个laydate实例
    laydate.render({
        elem: '#birthday' //指定元素
        ,btns: ['clear', 'now', 'confirm'] //按钮顺序
        ,theme: '#393D49' //主题
        ,trigger: 'click' //触发方式
    });

    //初始化角色下拉列表
    $.ajax({
        url: ctx + "/getRolesList",
        type: "get",
        async: "true",
        dataType: "json",
        success: function(data){
            var rolesData = data.data;
            console.log(data);
            var largeHtml = initRolesList(rolesData);
            $("#roleId").html(largeHtml);
            //动态拼接下拉框后,重新渲染
            form.render('select');
        },
        error : function () {
            layer.msg("服务器开小差了，请稍后再试...");
        }
    });

    //邮箱校验
    $("#eMail").blur(function(){
        $.ajax({
            type: "get",
            url: ctx + "/checkUserEmail?username=" + $("#username").val() + "&eMail=" + $("#eMail").val(),
            success:function(data){
                if(data.code!=0){
                    layer.msg(data.msg);
                    $("#eMail").val("");
                    $("#eMail").focus();
                }
            }
        });
    });

    //id有值，表示不是新增
    if (param.id) {
        //用户名后台作为加密盐值,此处不可编辑
        $("#username").attr("disabled","disabled");
        $("#pwd").hide();
        $("#repwd").hide();
        //flag有值表示查看详情，输入框不可修改
        if(param.flag){
            $("#fullname").attr("disabled","disabled");
            $("#eMail").attr("disabled","disabled");
            $("#birthday").attr("disabled","disabled");
            $("#address").attr("disabled","disabled");
            $("#phone").attr("disabled","disabled");
            $("#roleId").attr("disabled","disabled");
            $("input[name='sex']").attr("disabled",true);
            $("input[name='sex']").attr("disabled","disabled");
            $("#submit").hide();
            $("#reset").hide();
        }
        //edit有值表示用户编辑(不能修改角色),非管理员编辑
        if(param.edit){
            $("#roleId").attr("disabled","disabled");
        }
        $.ajax({
            type : 'GET',
            url : ctx + '/getUser?id=' + param.id,
            dataType : 'json',
            success : function(e){
                if (e.code == 0) {
                    //e为后台返回的数据
                    $("#id").val(e.data.id);
                    $("#username").val(e.data.username);
                    $("#fullname").val(e.data.fullname);
                    $("#eMail").val(e.data.eMail);
                    $("#birthday").val(e.data.birthday);
                    $("#address").val(e.data.address);
                    $("#phone").val(e.data.phone);
                    //性别按实际显示
                    var sexs = document.getElementsByName("sex");
                    for (var i=0; i<sexs.length; i++){
                        if (sexs[i].value == e.data.sex) {
                            sexs[i].checked= true;
                            break;
                        }
                    }
                    //角色按实际显示
                    var s = $("#roleId");
                    var ops = s[0].options;
                    for(var i=0;i<ops.length; i++){
                        var tempValue = ops[i].value;
                        if(tempValue == e.data.roleId)
                        {
                            ops[i].selected = true;
                        }
                    }
                    //渲染下拉框
                    form.render('select');
                } else {
                    layer.msg(e.msg);
                }
            },
            error : function(){
                layer.msg("服务器开小差了，请稍后再试...");
            }
        });
    } else {
        //只有在新增是,才进行用户名和密码框校验,编辑时用户名不可编辑

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

        $("#username").blur(function(){
            $.ajax({
                type: "get",
                url: ctx + "/checkUserName/"+$("#username").val(),
                success:function(data){
                    if(data.code!=0){
                        layer.msg(data.msg);
                        $("#username").val("");
                        $("#username").focus();
                    }
                }
            });
        });
    }

    //监听提交
    form.on('submit(formDemo)', function(data){
        if(!data.field.password){
            data.field.password = null;
        }
        $.ajax({
            type : 'POST',
            url : ctx + '/addOrModifyUser',
            dataType : 'json',
            contentType : 'application/json;charset=utf-8', //设置请求头信息
            data : JSON.stringify(data.field),
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

function initRolesList(rolesData){
    var rolesHtml = "";
    rolesHtml += '<option value=""></option>';
    rolesData.forEach(function( val, index ) {
        rolesHtml += '<option value="' + val.roleId + '">' + val.roleName + '</option>';
    });
    console.log(rolesHtml);
    return rolesHtml;
}