layui.use(['jquery','form'], function(){
    var form = layui.form;
    var $ = layui.$;//重点处
    var param = getParam();

    //id有值，表示不是新增
    if (param.menuId) {
        $.ajax({
            type : 'GET',
            url : ctx + '/getMenus?menuId=' + param.menuId,
            dataType : 'json',
            success : function(e){
                if (e.code == 0) {
                    //e为后台返回的数据
                    $("#menuId").val(e.data.menuId);
                    $("#title").val(e.data.title);
                    $("#icon").val(e.data.icon);
                    $("#href").val(e.data.href);
                    $("#perms").val(e.data.perms);
                    $("#parentId").val(e.data.parentId);
                    $("#sorting").val(e.data.sorting);
                } else {
                    layer.msg(e.msg);
                }
            },
            error : function(){
                layer.msg("服务器开小差了，请稍后再试...");
            }
        });
    }

    if(param.parentId){
        //新增下级菜单时,父级ID赋值
        $("#parentId").val(param.parentId);
    }

    //监听提交
    form.on('submit(formDemo)', function(data){
        $.ajax({
            type : 'POST',
            url : ctx + '/addOrModifyMenus',
            dataType : 'json',
            contentType : 'application/json;charset=utf-8', //设置请求头信息
            data : JSON.stringify(data.field),
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