/**
 * 通过地址获取参数
 * @returns 返回获取到的参数，没有参数则返回一个空字典{}
 */
function getParam() {
    var search = location.search; //获取search
    var search_str = search.split("?"); //去掉问号
    var data = {};
    if (search_str.length < 2) { //判断问号后面是否有参数
        return {};
    }
    search_parm = search_str[1]; //获取问号后的参数字符串
    var parms = search_parm.split("&"); //按照&符号分割字符串
    for (var i = 0; i < parms.length; i++) {
        var parm = parms[i].split("="); //	按照=分割字符串，获得参数名与参数值
        data[parm[0]] = parm[1]; //保存参数
    }
    return data;
};