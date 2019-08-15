<%@ page language="java" contentType="text/html; charset=UTF-8"
                pageEncoding="UTF-8"%>
<%@ include file="/pages/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Test Page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <link rel="stylesheet" href="${ctx}/css/user.css">
    <link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">

    <script type="text/javascript" src="${ctx}/layui/layui.js"></script>
    <script type="text/javascript" src="${ctx}/js/util.js"></script>
</head>

<body>
<!-- 表单 -->
<form class="layui-form" id="form" action="">
    <input id="uuid" name="uuid" type="hidden" value="" />
    <div class="layui-form-item">
        <label class="layui-form-label">用户</label>
        <div class="layui-input-block">
            <input type="text" id="uname" name="uname" style="width:250px" required  lay-verify="required" placeholder="请输入用户" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">年龄</label>
        <div class="layui-input-block">
            <input type="text" id="age" name="age" style="width:250px" required  lay-verify="required" placeholder="请输入年龄" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="1" title="男" checked>
            <input type="radio" name="sex" value="2" title="女">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <!-- button自带提交属性，影响ajax提交，增加type="button" -->
            <button id="submit" type="button" class="layui-btn" lay-submit lay-filter="formDemo">保存</button>
            <button id="reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

</body>
<script type="application/javascript">
    layui.use(['jquery','form'], function(){
        var form = layui.form;
        var $ = layui.$;//重点处
        var param = getParam();
        //uuid有值，表示不是新增
        if (param.uuid) {
            //flag有值表示查看详情，输入框不可修改
            if(param.flag){
                $("#uname").attr("disabled","disabled");
                $("#age").attr("disabled","disabled");
                $("input[name='sex']").attr("disabled",true);
                $("input[name='sex']").attr("disabled","disabled");
                $("#submit").hide();
                $("#reset").hide();
            }
            $.ajax({
                type : 'POST',
                url : '${ctx}/getUserByUuid',
                dataType : 'json',
                data : {
                    'uuid' : param.uuid
                },
                success : function(e){
                    //e为后台返回的数据
                    $("#uuid").val(e.uuid);
                    $("#uname").val(e.uname);
                    $("#age").val(e.age);
                    var selects = document.getElementsByName("sex");
                    for (var i=0; i<selects.length; i++){
                        if (selects[i].value == e.sex) {
                            selects[i].checked= true;
                            break;
                        }
                    }
                },
                error : function(){
                    layer.msg("服务器开小差了，请稍后再试...");
                }
            });
        }

        //监听提交
        form.on('submit(formDemo)', function(data){
            var paramData = {
                "uuid" : data.field.uuid,
                "uname" : data.field.uname,
                "sex" : data.field.sex,
                "age" : data.field.age
            };
            $.ajax({
                type : 'POST',
                url : '${ctx}/addOrUpdateUser',
                dataType : 'json',
                data : paramData,
                success : function(e){
                    //e为后台返回的数据
                    if(e == "1"){
                        //当你在iframe页面关闭自身时
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                        layer.msg("修改成功");
                    }else{
                        layer.msg("修改失败");
                    }
                },
                error : function(){
                    layer.msg("服务器开小差了，请稍后再试...");
                }
            });
        });
    });
</script>
</html>