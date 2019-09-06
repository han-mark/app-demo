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

    <link rel="stylesheet" href="${ctx }/css/user.css">
    <link rel="stylesheet" href="${ctx }/common/layui/css/layui.css" media="all">

    <script type="text/javascript" src="${ctx }/common/layui/layui.js"></script>
    <script>
        var ctx = "${ctx}";
    </script>
</head>

<body>
<!-- 工具条 -->
<script type="text/html" id="barUser">
    <shiro:hasPermission name="user:update">
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="user:update">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="user:delete">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </shiro:hasPermission>
</script>
<shiro:hasPermission name="user:add">
<div class="top_tool">
    <!-- 新增按钮 -->
    <button class="layui-btn btn_extend" type="button" id="add">
        <i class="layui-icon">&#xe608;</i> 添加用户
    </button>
</div>
</shiro:hasPermission>
<!-- 表格 -->
<table id="table-user" lay-filter="table-user"></table>

<!-- layui中的语法 -->
<script type="text/html" id="sexTxt">
    {{#  if(d.sex === '1'){ }}
    男
    {{#  } else if(d.sex === '2'){ }}
    <span style="color: #F581B1;">女</span>
    {{#  } else{ }}
    保密
    {{#  } }}
</script>

<script type="text/javascript" src="${ctx }/js/user.js"></script>
</body>
</html>