layui.use(['jquery','form','tree'], function(){
    var form = layui.form;
    var tree = layui.tree;
    var $ = layui.$;//重点处
    var param = getParam();
    var roleId = (param.roleId == undefined)? '-1' : param.roleId;

    //roleId有值，表示不是新增
    if (param.roleId) {
        //用户名后台作为加密盐值,此处不可编辑
        $("#roleName").attr("disabled","disabled");
        //flag有值表示查看详情，输入框不可修改
        if(param.flag){
            $("#roleRemark").attr("disabled","disabled");
            $("#pers").attr("disabled","disabled");
            $("#submit").hide();
            $("#reset").hide();
        }
        $.ajax({
            type : 'GET',
            url : ctx + '/getRoles?roleId=' + param.roleId,
            dataType : 'json',
            success : function(e){
                if (e.code == 0) {
                    //e为后台返回的数据
                    $("#roleId").val(e.data.roleId);
                    $("#roleName").val(e.data.roleName);
                    $("#roleRemark").val(e.data.roleRemark);
                } else {
                    layer.msg(e.msg);
                }
            },
            error : function(){
                layer.msg("服务器开小差了，请稍后再试...");
            }
        });
    } else {
        //只有在新增,才进行角色名校验,编辑时用户名不可编辑
        $("#roleName").blur(function(){
            $.ajax({
                type: "get",
                url: ctx + "/checkRolesName/"+$("#roleName").val(),
                success:function(data){
                    if(data.code!=0){
                        layer.msg(data.msg);
                        $("#roleName").val("");
                        $("#roleName").focus();
                    }
                }
            });
        });
    }

    //获取树形数据(编辑角色设置权限用)
    $.ajax({
        type : 'GET',
        url : ctx + '/getTreeMenusList?roleId=' + roleId,
        dataType : 'json',
        success : function(e){
            if (e.code == 0) {
                var treeData = initTreeMenus(e.data);
                //渲染
                tree.render({
                    elem: '#treeData'  //绑定元素
                    ,id: 'treeId' //定义索引
                    ,data: treeData
                    ,showCheckbox: true //是否显示复选框
                });
            } else {
                layer.msg(e.msg);
            }
        },
        error : function(){
            layer.msg("服务器开小差了，请稍后再试...");
        }
    });

    //监听提交
    form.on('submit(formDemo)', function(data){
        //获得选中的节点
        var checkData = tree.getChecked('treeId');
        var sendData = [];
        if (checkData.length != 0) {
            checkData.forEach(function (val, index) {
                sendData.push({
                    roleId : roleId,
                    menuId : val.id
                });
                if (val.children) {
                    val.children.forEach(function (val1, index1) {
                        sendData.push({
                            roleId : roleId,
                            menuId : val1.id
                        });
                        if (val1.children) {
                            val1.children.forEach(function (val2, index2) {
                                sendData.push({
                                    roleId : roleId,
                                    menuId : val2.id
                                });
                            })
                        }
                    })
                }
            })
        }
        var paramData = {
            roleId : data.field.roleId,
            roleName : data.field.roleName,
            roleRemark : data.field.roleRemark,
            perms : sendData
        }
        $.ajax({
            type : 'POST',
            url : ctx + '/addOrModifyRoles',
            dataType : 'json',
            contentType : 'application/json;charset=utf-8', //设置请求头信息
            data : JSON.stringify(paramData),
            success : function(e){
                //e为后台返回的数据
                if(e.code == 0){
                    //当你在iframe页面关闭自身时
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                }
                layer.msg(e.msg);
            },
            error : function(){
                layer.msg("服务器开小差了，请稍后再试...");
            }
        });
    });
});

//生成三级菜单(用于编辑角色设置权限)
function initTreeMenus(menusData) {
    var tree1 = [];
    menusData.forEach(function( val, index ) {
        if (val.parentId == '0') {
            tree1.push({
                id : val.menuId,
                title : val.title,
                checked : val.checked
            });
        }
    });
    tree1.forEach(function( val1, index1 ) {
        var children = [];
        menusData.forEach(function( val2, index2 ) {
            if (val1.id == val2.parentId) {
                children.push({
                    id : val2.menuId,
                    title : val2.title,
                    checked : val2.checked
                });
            }
        });
        if (children.length != 0) {
            val1.children = children;
        }
    });
    tree1.forEach(function( val3, index3 ) {
        var children1 = val3.children;
        if (children1) {
            children1.forEach(function( val4, index4 ) {
                var children2 = [];
                menusData.forEach(function( val5, index5 ) {
                    if (val4.id == val5.parentId) {
                        children2.push({
                            id : val5.menuId,
                            title : val5.title,
                            checked : val5.checked
                        });
                    }
                });
                if (children2.length != 0) {
                    val4.children = children2;
                }
            });
        }
    });
    console.log(tree1);
    return tree1;
}