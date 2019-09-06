<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
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

    <link rel="shortcut icon" type="image/x-icon" href="${ctx}/images/favicon.ico" />

    <link rel="stylesheet" href="${ctx}/common/layui/css/layui.css" media="all">
    <script type="text/javascript" src="${ctx}/common/layui/layui.js"></script>
    <script>
        var ctx = "${ctx}";
    </script>
</head>

<body>
<%
    Date d = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String now = df.format(d);
%>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">后台权限管理系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${ctx}/images/login-head.jpg" class="layui-nav-img">
                    <!-- user是在IndexController中放入到request域 -->
                    ${user.fullname}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="#" onclick="findUserDetail(${user.id})">基本资料</a></dd>
                    <dd><a href="#" onclick="updateUserPwd()">修改密码</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="${ctx}/loginOut">退出</a></li>
        </ul>
    </div>
    <div id="left-menus">
        <!-- 左侧导航栏部分 -->
    </div>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <iframe src="${ctx}/pages/home.jsp" width="100%" height="100%" style="min-height: 450px" frameborder="0" scrolling="auto" id="iframe" name="mainiframe"></iframe>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © cuihui.com - <%=now %>
    </div>
</div>
<script type="text/javascript" src="${ctx }/js/index.js"></script>
<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?d214947968792b839fd669a4decaaffc";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
</body>
</body>
</html>