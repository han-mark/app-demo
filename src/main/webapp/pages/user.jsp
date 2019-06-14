<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Test Page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">

    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>

<body>
    <!-- 工具条 -->
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <!-- 新增按钮 -->
    <button id="add" type="button" class="layui-btn" style="margin-top: 10px">
        <i class="layui-icon">&#xe608;</i> 添加
    </button>
    <!-- 表格 -->
    <table id="demo" lay-filter="test"></table>

    <!-- 表单 -->
    <form class="layui-form" id="form" action="" style="display: none;margin-top: 100px;margin-left: 500px">
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
                <button id="submit" class="layui-btn" lay-submit lay-filter="formDemo">修改</button>
                <button id="addsubmit" class="layui-btn" lay-submit lay-filter="formDemo">新增</button>
                <button id="reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
                <button class="layui-btn" id="confirm">确定</button>
            </div>
        </div>
    </form>

    <!-- 隐藏域放入工程路径 -->
    <input id="PageContext" type="hidden" value="${pageContext.request.contextPath}" />
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user.js"></script>

</html>