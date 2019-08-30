layui.config({
    base: '../layui-tree/js/', //引用的扩展模块
})
layui.use(['treeTable','layer','code','form'],function(){
    var o = layui.$,
        form = layui.form,
        layer = layui.layer,
        treeTable = layui.treeTable;

    //渲染树形表格(菜单列表)
    var	re = treeTable.render({
        elem: '#tree-table',
        // data: [{"id":1,"pid":0,"title":"1-1"},{"id":2,"pid":0,"title":"1-2"},{"id":3,"pid":0,"title":"1-3"},{"id":4,"pid":1,"title":"1-1-1"},{"id":5,"pid":1,"title":"1-1-2"},{"id":6,"pid":2,"title":"1-2-1"},{"id":7,"pid":2,"title":"1-2-3"},{"id":8,"pid":3,"title":"1-3-1"},{"id":9,"pid":3,"title":"1-3-2"},{"id":10,"pid":4,"title":"1-1-1-1"},{"id":11,"pid":4,"title":"1-1-1-2"}],
        url: ctx + '/getTreeMenusListData?roleId=-1', //返回数据为数组格式，同上述data数据
        primary_key: 'menuId',
        parent_key: 'parentId',
        icon_key: 'title',
        // is_checkbox: true, //开启复选框
        // checked: {
        //     key: 'id',
        //     data: [0,1,4,10,11,5,2,6,7,3,8,9],
        // },
        end: function(e){
            form.render();
        },
        cols: [
            {
                key: 'title',
                title: '名称',
                width: '150px',
                template: function(item){
                    if(item.level == 0){
                        return '<span style="color:#000000;">'+item.title+'</span>';
                    }else if(item.level == 1){
                        return '<span style="color:rgba(34,11,12,0.69);">'+item.title+'</span>';
                    }else if(item.level == 2){
                        return '<span style="color:#aaa;">'+item.title+'</span>';
                    }
                }
            },
            {
                key: 'menuId',
                title: '菜单ID',
                width: '100px',
                align: 'center',
            },
            {
                key: 'icon',
                title: '图标',
                width: '100px',
                align: 'center',
                template: function(item){
                    if(item.icon){
                        return '<i class="layui-icon">' +item.icon+ '</i>';
                    }else{
                        return '';
                    }
                }
            },
            {
                key: 'href',
                title: '默认页面',
                width: '120px',
                align: 'center',
            },
            {
                key: 'perms',
                title: '权限',
                width: '100px',
                align: 'center',
            },
            {
                key: 'parentId',
                title: '父ID',
                width: '100px',
                align: 'center',
            },
            {
                key: 'sorting',
                title: '排序',
                width: '100px',
                align: 'center',
                template: function(item){
                    if(item.sorting){
                        return item.sorting;
                    }else{
                        return '';
                    }
                }
            },
            {
                title: '操作',
                align: 'center',
                template: function(item){
                    // return '<shiro:hasPermission name="menus:add"><a onclick="addDownMenus('+ item.menuId +')" title="添加下级菜单" href="#">添加</a></shiro:hasPermission> ' +
                    //     '| <shiro:hasPermission name="menus:update"><a onclick="editMenus('+ item.menuId +')" title="编辑当前菜单" href="#">编辑</a></shiro:hasPermission>' +
                    //     '| <shiro:hasPermission name="menus:delete"><a onclick="deleteMenus('+ item.menuId +')" title="级联删除菜单" href="#">删除</a></shiro:hasPermission>';
                    return getHandleButton(item);
                }
            }
        ]
    });
    // 监听展开关闭
    treeTable.on('tree(flex)',function(data){
        // layer.msg(JSON.stringify(data));
    })
    // 全部展开
    o('.open-all').click(function(){
        treeTable.openAll(re);
    })
    // 全部关闭
    o('.close-all').click(function(){
        treeTable.closeAll(re);
    })



    //新增一级菜单点击事件
    o(".add-top").bind("click",function(){
        //弹出层处理
        layer.open({
            type:2, //2表示frame
            title:'新增一级菜单',
            area:['50%','80%'],
            shadeClose:false,
            closeBtn:1,
            content:ctx + '/pages/updatemenus.jsp',
            end:function(){
                treeTable.render(re); //刷新树形表格
            }
        });
    });
})

//级联删除菜单
function deleteMenus(menuId){
    layer.confirm('确定级联删除吗', function(index){
        layer.close(index);
        //向服务端发送删除指令
        layui.$.ajax({
            type : 'GET',
            url : ctx + '/deleteMenus?menuId=' + menuId,
            dataType : 'json',
            success : function(e){
                //e为后台返回的数据
                if(e.code == 0){
                    layer.msg(e.msg);
                    //不知道怎么在外部渲染,直接刷新页面
                    location.reload();
                }else{
                    layer.msg(e.msg);
                }
            },
            error : function(){
                layer.msg("服务器开小差了，请稍后再试...");
            }
        });
    });
}


//新增下级菜单点击事件
function addDownMenus(menuId){
    //弹出层处理
    layer.open({
        type:2, //2表示frame
        title:'新增下级菜单',
        area:['50%','80%'],
        shadeClose:false,
        closeBtn:1,
        content:ctx + '/pages/updatemenus.jsp?parentId=' + menuId,
        end:function(){
            //不知道怎么在外部渲染,直接刷新页面
            location.reload();
        }
    });
}

//编辑菜单点击事件
function editMenus(menuId){
    //弹出层处理
    layer.open({
        type:2, //2表示frame
        title:'编辑菜单',
        area:['50%','80%'],
        shadeClose:false,
        closeBtn:1,
        content:ctx + '/pages/updatemenus.jsp?menuId=' + menuId,
        end:function(){
            //不知道怎么在外部渲染,直接刷新页面
            location.reload();
        }
    });
}