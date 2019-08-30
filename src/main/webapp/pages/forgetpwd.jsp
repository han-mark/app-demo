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
    <link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all">

    <script type="text/javascript" src="${ctx}/layui/layui.js"></script>
    <script type="text/javascript" src="${ctx}/js/util.js"></script>
    <script>
        var ctx = "${ctx}";
    </script>
</head>

<body>
<!-- 表单 -->
<form class="layui-form" id="form" action="" style="margin-top: 15px;">
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱：</label>
        <div class="layui-input-inline">
            <input type="text" id="email" name="email" required
                   lay-verify="email" placeholder="请输入邮箱" lay-reqtext="邮箱不能为空" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item" style="height:38px;">
        <label class="layui-form-label">验证码：</label>
        <div class="layui-input-inline" style="width: 105px;">
            <input type="text" id="code" name="code" required lay-verify="required"
                   placeholder="请输入验证码" lay-reqtext="验证码不能为空" autocomplete="off" class="layui-input"
                   style="width:100%">

        </div>
        <div class="layui-form-mid layui-word-aux" style="height: 38px;">
            <button id="code-Btn" type="button" class="layui-btn"
                    style="width: 140px;margin-top: -9px;">获取验证码</button>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">新密码：</label>
        <div class="layui-input-inline">
            <input type="password" id="password" name="password" placeholder="请输入密码" lay-verify="pass"
                   autocomplete="off" class="layui-input" />
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认密码：</label>
        <div class="layui-input-inline">
            <input type="password" placeholder="请再次输入密码" lay-verify="repass"
                   autocomplete="off" class="layui-input" />
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 20px;">
        <div class="layui-input-block">
            <!-- button自带提交属性，影响ajax提交，增加type="button" -->
            <button id="submit" type="button" class="layui-btn" lay-submit lay-filter="formDemo">保存</button>
            <button id="reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/forgetpwd.js"></script>
</body>
</html>