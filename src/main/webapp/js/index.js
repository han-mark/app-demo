//JavaScript代码区域
layui.use(['jquery','element','layer'], function(){
    var element = layui.element;
    var $ = layui.$;
    var layer = layui.layer;
    $.ajax({
        url: ctx + "/getMenusList",
        type: "get",
        async: "true",
        dataType: "json",
        success: function(data){
            var menuData = data.data;
            console.log(data);
            var largeHtml = generateMenus(menuData);
            $("#left-menus").html(largeHtml);
            //动态拼接导航栏后,重新渲染
            var layFilter = $("#menus").attr('lay-filter');
            element.render('menus', layFilter);
        },
        error : function () {

        }
    });
});

//生成左侧菜单
function generateMenus(menuData){
    var menusHtml = "";
    menusHtml += '<div class="layui-side layui-bg-black" kit-side="true">';
    menusHtml += '<div class="layui-side-scroll">';
    menusHtml += '<ul class="layui-nav layui-nav-tree"  lay-filter="menus">';
    menuData.forEach(function( val, index ) {
        if(val.parentId == '0' && val.children.length > 0) {
            if( index == '0') {
                menusHtml += '<li class="layui-nav-item layui-nav-itemed">';
            } else {
                menusHtml += '<li class="layui-nav-item">';
            }
            menusHtml += '<a class="" href="javascript:;"><i class="layui-icon" style="margin-right: 10px;">' +val.icon+ '</i>' + val.title + '</a>';
            menusHtml += '<dl class="layui-nav-child">';
            val.children.forEach(function( cVal, cIndex ){
                menusHtml += '<dd><a href="' + ctx + '/' + cVal.href + '" target="mainiframe"><i class="layui-icon" style="margin-right: 10px;">' +cVal.icon+ '</i>' + cVal.title + '</a></dd>';
            });
            menusHtml += '</dl>';
            menusHtml += '</li>';
        } else {
            if( index == '0') {
                menusHtml += '<li class="layui-nav-item layui-nav-itemed"><a href="' + ctx + '/' + val.href + '" target="mainiframe"><i class="layui-icon" style="margin-right: 10px;">' +val.icon+ '</i>' + val.title + '</a></li>';
            } else {
                menusHtml += '<li class="layui-nav-item"><a href="' + ctx + '/' + val.href + '" target="mainiframe"><i class="layui-icon" style="margin-right: 10px;">' +val.icon+ '</i>' + val.title + '</a></li>';
            }
        }
    });
    menusHtml += '</ul>';
    menusHtml += '</div>';
    menusHtml += '</div>';
    console.log(menusHtml);
    return menusHtml;
}

//获取用户信息
function findUserDetail(id){
    layer.open({
        type:2, //2表示frame
        title:'用户信息',
        area:['50%','80%'],
        shadeClose:false,
        closeBtn:1,
        content:ctx + '/pages/updateuser.jsp?id='+id + '&edit=1',
        end:function(){
            // table.reload('table-user'); //对应table.render中定义的id
        }
    });
}

//修改用户密码
function updateUserPwd(){
    layer.open({
        type:2, //2表示frame
        title:'修改密码',
        area:['50%','80%'],
        shadeClose:false,
        closeBtn:1,
        content:ctx + '/pages/updatepwd.jsp',
        end:function(){
            // table.reload('table-user'); //对应table.render中定义的id
        }
    });
}