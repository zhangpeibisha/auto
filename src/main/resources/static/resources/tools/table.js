// table展示处理
var tableVue = new Vue({
    el: "#content",
    data: {
        checkValue: new Map(),
        currPageData: []
    }
});

/**
 * 文档URL：https://www.layui.com/doc/modules/table.html#cols
 *
 * 显示表格，在内容中
 * @param id 表格ID，需要与initTable中的ID对应
 *           设定容器唯一ID。注意：从 layui 2.2.0 开始，
 *           该参数等价于 <table id="test"></table> 中的 id 值。
 *           id值是对表格的数据操作方法上是必要的传递条件，
 *           它是表格容器的索引。你在下文也将会见识它的存在。
 * @param url 发起数据请求的路径
 * @param toolbar 开启表格头部工具栏区域，该参数支持四种类型值：
 toolbar: '#toolbarDemo' //指向自定义工具栏模板选择器
 toolbar: '<div>xxx</div>' //直接传入工具栏模板字符
 toolbar: true //仅开启工具栏，不显示左侧模板
 toolbar: 'default' //让工具栏左侧显示默认的内置模板
 * @param title 定义 table 的大标题（在文件导出等地方会用到）
 * @param totalrow 是否开启合计行区域
 * @param cols  设置表头。值是一个二维数组。方法渲染方式必填
 * @param page  开启分页（默认：false）
 *        注：从 layui 2.2.0 开始，支持传入一个对象，里面可包含 laypage 组件所有支持的参数（jump、elem除外）
 * @param loading 是否显示加载条
 * @param initTable 初始化table
 * @param done 当获取到数据后到回调函数
 */
function showTable(id, url, toolbar, title, totalrow, cols, page, loading, initTable, done) {
    // 初始化表格
    updateContent(initTable);

    layui.use('table', function (key) {
        var table = layui.table;

        table.render({
            elem: '#' + id,
            url: url,
            toolbar: toolbar,
            title: title,
            totalRow: totalrow,
            cols: cols,
            page: page,
            loading: loading,
            done: done
        });

        //工具栏事件
        table.on('toolbar(' + id + ')', function (obj) {
            console.log("工具栏事件", obj);
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'getCheckData':
                    var str = "";
                    var i = 0;
                    for (let value of tableVue.checkValue.values()) {
                        str += ++i + ": " + value + "<hr>"
                    }
                    layer.alert(str);
                    break;
                case 'getCheckLength':
                    layer.msg('选中了：' + tableVue.checkValue.size + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选' : '未全选')
                    break;
            }
            ;
        });

        // 做跨页选取的缓存处理，作为key值的键需要后续更改策略
        table.on('checkbox(' + id + ')', function (obj) {
            console.log("选中监听", obj);
            var data = obj.data;
            if (obj.checked) {
                if (obj.type === 'one') {
                    tableVue.checkValue.set(md5(data.path), JSON.stringify(data));
                }
                if (obj.type === 'all') {
                    var tempData = tableVue.currPageData;
                    var len = tempData.length;
                    for (var i = 0; i < len; i++) {
                        tableVue.checkValue.set(md5(tempData[i].path), JSON.stringify(tempData[i]));
                    }
                }

            } else {
                if (obj.type === 'one') {
                    tableVue.checkValue.delete(md5(data.path), JSON.stringify(data));
                }
                if (obj.type === 'all') {
                    var tempData = tableVue.currPageData;
                    var len = tempData.length;
                    for (var i = 0; i < len; i++) {
                        tableVue.checkValue.delete(md5(tempData[i].path), JSON.stringify(tempData[i]));
                    }
                }
            }

            console.log("监听复选框", tableVue.checkValue)
        });


    });
}


/**
 * 示例使用
 * @param id 表格ID
 * @returns {string} 拼装的表格h5字符串
 */
// function setTableFunction(id) {
//     return `<table class="layui-hide" id="test" lay-filter=${id}></table>
//                 <script type="text/html" id="toolbarDemo">
//                   <div class="layui-btn-container">
//                     <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
//                     <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
//                     <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
//                   </div>
//                 </script>`;
// }

// 示例表头
// var cols = [
//     [{
//         type: 'checkbox',
//         fixed: 'left',
//         event: "choose"
//     }, {
//         field: "id",
//         title: "ID"
//     }, {
//         field: "name",
//         title: "含义"
//     }, {
//         field: "path",
//         title: "请求URL"
//     }, {
//         field: "remarks",
//         title: "备注"
//     }, {
//         field: "use",
//         title: "是否使用"
//     }, {
//         field: "useVersion",
//         title: "最低使用版本"
//     }]
// ];
// var url = "/test/getSchemaJson";
// var toolbar = "#toolbarDemo";
// var title = "test";
// var totalrow = false;
// var page = true;
// var id = "test";
// var loading = true;
// // 示例
// showTable(id, url, toolbar, title, totalrow, cols, page, loading, setTableFunction(id), function (res, curr, count) {
//     tableVue.currPageData = res.data;
//     console.log("获取信息", res)
//     console.log("赋值信息", tableVue.currPageData)
//     //配置当前页的选中状态
//     var temp = tableVue.checkValue;
//     var len = tableVue.currPageData.length;
//     for (var i = 0; i < len; i++) {
//         var tempValue = temp.has(md5(tableVue.currPageData[i].path));
//         console.log("tempValue",tempValue);
//         console.log("md5",md5(tableVue.currPageData[i].path));
//         if (tempValue){
//             //这里才是真正的有效勾选
//             res.data[i]["LAY_CHECKED"]='true';
//             //找到对应数据改变勾选样式，呈现出选中效果
//             var index= res.data[i]['LAY_TABLE_INDEX'];
//             $('.layui-table-fixed-l tr[data-index=' + index + '] input[type="checkbox"]').prop('checked', true);
//             $('.layui-table-fixed-l tr[data-index=' + index + '] input[type="checkbox"]').next().addClass('layui-form-checked');
//         }
//     }
// });


//========================================================各种表格定制======================================================================
//==================================schema表

function showSchemaTable() {
    // 设置参数
// 设置表头
    var schemaCols = [
        [{
            type: 'checkbox',
            fixed: 'left',
            event: "choose"
        }, {
            field: "id",
            title: "ID"
        }, {
            field: "name",
            title: "含义"
        }, {
            field: "path",
            title: "请求URL"
        }, {
            field: "remarks",
            title: "备注"
        }, {
            field: "use",
            title: "是否使用"
        }, {
            field: "useVersion",
            title: "最低使用版本"
        }]
    ];
    // 请求地址
    var url = "/test/getSchemaJson";
    // 工具ID
    var toolbar = "#toolbarDemo";
    // 导出文件时的文件名字
    var title = "schemaList";
    // 是否需要统计
    var totalrow = false;
    // 是否分页
    var page = true;
    // 表格ID
    var id = "test";
    // 是否显示加载
    var loading = true;

    var toolsStr = `<table class="layui-hide" id="test" lay-filter=${id}></table>
                <script type="text/html" id="toolbarDemo">
                  <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-sm" lay-event="getCheckData">查看选中的数据</button>
                    <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">查看选中数据的数量</button>
                  </div>
                </script>`;

    showTable(id,url,toolbar,title,totalrow,schemaCols,page,loading,toolsStr,function(res, curr, count){
        tableVue.currPageData = res.data;
        //配置当前页的选中状态
        var temp = tableVue.checkValue;
        var len = tableVue.currPageData.length;
        for (var i = 0; i < len; i++) {
            var tempValue = temp.has(md5(tableVue.currPageData[i].path));
            if (tempValue){
                //这里才是真正的有效勾选
                res.data[i]["LAY_CHECKED"]='true';
                //找到对应数据改变勾选样式，呈现出选中效果
                var index= res.data[i]['LAY_TABLE_INDEX'];
                $('.layui-table-fixed-l tr[data-index=' + index + '] input[type="checkbox"]').prop('checked', true);
                $('.layui-table-fixed-l tr[data-index=' + index + '] input[type="checkbox"]').next().addClass('layui-form-checked');
            }
        }
    })
}


//==================================schema表


//==================================手机信息填写表


//==================================报告查看表