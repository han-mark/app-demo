<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ include file="/pages/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>后台权限管理系统</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <link rel="stylesheet" href="${ctx }/css/roles.css">
    <link rel="stylesheet" href="${ctx }/common/layui/css/layui.css" media="all">

    <script type="text/javascript" src="${ctx }/common/layui/layui.js"></script>
    <script>
        var ctx = "${ctx}";
    </script>
</head>

<body>
<!-- 工具条 -->
<script type="text/html" id="barRoles">
    <shiro:hasPermission name="roles:update">
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="roles:update">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="roles:delete">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </shiro:hasPermission>
</script>
<shiro:hasPermission name="roles:add">
<div class="top_tool">
    <!-- 新增按钮 -->
    <button class="layui-btn btn_extend" type="button" id="add">
        <i class="layui-icon">&#xe608;</i> 添加角色
    </button>
</div>
</shiro:hasPermission>
<!-- 表格 -->
<table id="table-roles" lay-filter="table-roles"></table>

<script type="text/javascript" src="${ctx }/js/roles.js"></script>
</body>
</html>