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

    <link rel="stylesheet" href="${ctx}/css/roles.css">
    <link rel="stylesheet" href="${ctx}/common/layui/css/layui.css" media="all">

    <script type="text/javascript" src="${ctx}/common/layui/layui.js"></script>
    <script type="text/javascript" src="${ctx}/js/util.js"></script>
    <script>
        var ctx = "${ctx}";
    </script>
</head>

<body>
<!-- 表单 -->
<form class="layui-form" id="form" action="">
    <input id="roleId" name="roleId" type="hidden" value="" />
    <div class="layui-form-item">
        <label class="layui-form-label">角色名</label>
        <div class="layui-input-block">
            <input type="text" id="roleName" name="roleName" style="width:100%" required  lay-verify="required" lay-reqtext="角色名不能为空" placeholder="请输入角色名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">角色描述</label>
        <div class="layui-input-block">
            <input type="text" id="roleRemark" name="roleRemark" style="width:100%" required  lay-verify="required" lay-reqtext="角色描述不能为空" placeholder="请输入角色描述" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">分配权限</label>
    </div>
    <div id="treeData"></div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <!-- button自带提交属性，影响ajax提交，增加type="button" -->
            <button id="submit" type="button" class="layui-btn" lay-submit lay-filter="formDemo">保存</button>
            <button id="reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx }/js/updateroles.js"></script>
</body>
</html>