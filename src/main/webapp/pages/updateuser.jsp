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
<form class="layui-form" id="form" action="">
    <input id="id" name="id" type="hidden" value="" />
    <div class="layui-form-item">
        <label class="layui-form-label">用户</label>
        <div class="layui-input-block">
            <input type="text" id="username" name="username" style="width:100%" required  lay-verify="required" lay-reqtext="用户名不能为空" placeholder="请输入用户" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" id="pwd">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
            <input type="password" id="password" class="layui-input userName" lay-verify="pass" placeholder="请输入密码" name="password">
        </div>
    </div>
    <div class="layui-form-item" id="repwd">
        <label class="layui-form-label">确认密码</label>
        <div class="layui-input-block">
            <input type="password" class="layui-input userName" lay-verify="repass" placeholder="请输入确认密码">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" id="fullname" name="fullname" style="width:100%" required  lay-verify="required" lay-reqtext="姓名不能为空" placeholder="请输入姓名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="text" id="eMail" name="eMail" style="width:100%" required  lay-verify="email" lay-reqtext="邮箱不能为空" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
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
        <label class="layui-form-label">出生日期</label>
        <div class="layui-input-inline">
            <input type="text" id="birthday" name="birthday" placeholder="yyyy-MM-dd" class="layui-input" lay-key="1">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">地址</label>
        <div class="layui-input-block">
            <input type="text" id="address" name="address" style="width:100%"  lay-verify="title" placeholder="请输入地址" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-block">
            <input type="text" id="phone" name="phone" style="width:100%"  lay-verify="required|phone" placeholder="请输入手机号" lay-reqtext="手机号不能为空" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">分配角色</label>
        <div class="layui-input-block" style="width: 190px;">
            <select id="roleId" name="roleId" lay-verify="required" lay-filter="roleId">
            </select>
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
<script type="text/javascript" src="${ctx }/js/updateuser.js"></script>
</body>
</html>