<%@ page language="java" contentType="text/html; charset=UTF-8"
                pageEncoding="UTF-8"%>
<%@ include file="/pages/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>后台权限管理系统</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <link rel="stylesheet" href="${ctx}/css/user.css">
    <link rel="stylesheet" href="${ctx}/common/layui/css/layui.css" media="all">

    <script type="text/javascript" src="${ctx}/common/layui/layui.js"></script>
    <script type="text/javascript" src="${ctx}/js/util.js"></script>
    <script>
        var ctx = "${ctx}";
    </script>
</head>

<body>
<!-- 表单 -->
<form class="layui-form" id="form" action="" style="margin-top: 15px;">
    <div class="layui-form-item">
        <label class="layui-form-label">旧密码</label>
        <div class="layui-input-block">
            <input type="password" id="opassword" name="opassword" class="layui-input userName" lay-verify="pass" placeholder="请输入旧密码" name="password">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">新密码</label>
        <div class="layui-input-block">
            <input type="password" id="password" name="password" class="layui-input userName" lay-verify="pass" placeholder="请输入新密码">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认新密码</label>
        <div class="layui-input-block">
            <input type="password" class="layui-input" lay-verify="repass" placeholder="请再次输入新密码">
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
<script type="text/javascript" src="${ctx }/js/updatepwd.js"></script>
</body>
</html>