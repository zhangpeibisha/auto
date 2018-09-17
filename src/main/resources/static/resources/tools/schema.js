// table展示处理
var schemaVue = new Vue({
    el: "#content",
    data: {
        checkValue: new Map(),
        currPageData: [],
        phoneInfo: new Map(),
        apk: ""
    }
});


//========================================================各种表格定制======================================================================
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
    });
}


//==================================schema表
/**
 * 展示schema的信息
 */
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
        }, {
            field: "maxUseVersion",
            title: "最高使用版本"
        }, {
            field: "createTime",
            title: "创建时间"
        }, {
            field: "updateTime",
            title: "更新时间"
        }]
    ];
    // 请求地址
    var url = "/schema/findSchemaList/pagination";
    // 工具ID
    var toolbar = "#toolbarDemo";
    // 导出文件时的文件名字
    var title = "schemaList";
    // 是否需要统计
    var totalrow = false;
    // 是否分页
    var page = true;
    // 表格ID
    var id = "schema";
    // 是否显示加载
    var loading = true;

    var toolsStr = `<table class="layui-hide" id=${id} lay-filter=${id}></table>
                <script type="text/html" id="toolbarDemo">
                  <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-sm" lay-event="getCheckData">查看选中的数据</button>
                    <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">查看选中数据的数量</button>
                  </div>
                </script>`;

    showTable(id, url, toolbar, title, totalrow, schemaCols, page, loading, toolsStr, function (res, curr, count) {
        console.log("数据加载触发", res, curr, count);
        schemaVue.currPageData = res.data;
        //配置当前页的选中状态
        var temp = schemaVue.checkValue;
        var len = schemaVue.currPageData.length;
        for (var i = 0; i < len; i++) {
            var tempValue = temp.has(schemaVue.currPageData[i].id);
            if (tempValue) {
                //这里才是真正的有效勾选
                res.data[i]["LAY_CHECKED"] = 'true';
                //找到对应数据改变勾选样式，呈现出选中效果
                var index = res.data[i]['LAY_TABLE_INDEX'];
                $('.layui-table-fixed-l tr[data-index=' + index + '] input[type="checkbox"]').prop('checked', true);
                $('.layui-table-fixed-l tr[data-index=' + index + '] input[type="checkbox"]').next().addClass('layui-form-checked');
            }
        }
    })


    layui.use('table', function (key) {
        var table = layui.table;

        //工具栏事件
        table.on('toolbar(' + id + ')', function (obj) {
            console.log("工具栏事件", obj);
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'getCheckData':
                    var str = "";
                    var i = 0;
                    for (let value of schemaVue.checkValue.values()) {
                        str += ++i + ": " + value + "<hr>"
                    }
                    layer.alert(str);
                    break;
                case 'getCheckLength':
                    layer.msg('选中了：' + schemaVue.checkValue.size + ' 个');
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
                    schemaVue.checkValue.set(data.id, JSON.stringify(data));
                }
                if (obj.type === 'all') {
                    var tempData = schemaVue.currPageData;
                    var len = tempData.length;
                    for (var i = 0; i < len; i++) {
                        schemaVue.checkValue.set(tempData[i].id, JSON.stringify(tempData[i]));
                    }
                }

            } else {
                if (obj.type === 'one') {
                    schemaVue.checkValue.delete(data.id, JSON.stringify(data));
                }
                if (obj.type === 'all') {
                    var tempData = schemaVue.currPageData;
                    var len = tempData.length;
                    for (var i = 0; i < len; i++) {
                        schemaVue.checkValue.delete(tempData[i].id, JSON.stringify(tempData[i]));
                    }
                }
            }

            console.log("监听复选框", schemaVue.checkValue)
        });
    })


}

//==================================schema表


//==================================手机信息填写表
/**
 * 展示添加设备信息的方法
 */
function addPhoneInfo() {

    var str = `
          <div>
               <button onclick="addPhone()" class="layui-btn">添加手机</button>
          </div>
         <table class="layui-hide" id="phoneTable" lay-filter="phoneTable"></table>
        <script type="text/html" id="barDemo">
          <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
          <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>          
        `;
    updateContent(str);
    layui.use("table", function (key) {
        var table = layui.table;
        table.render({
            elem: '#phoneTable',
            cols: [[{
                field: "ip",
                title: "电脑IP"
            }, {
                field: "port",
                title: "端口号"
            }, {
                field: "udid",
                title: "手机序列号"
            }, {
                title: "操作",
                fixed: 'right',
                width: 178,
                align: 'center',
                toolbar: '#barDemo'
            }]],
            data: mapToArr(schemaVue.phoneInfo),
            limit: 30
        });
    });
}

/**
 * 添加手机的弹框
 */
function addPhone() {
    layui.use('layer', function () {
        var layer = layui.layer;

        layer.open({
            type: 1,
            title: "添加设备信息",
            content: createConfirm(),
            area: ['350px', '300px']
        });
    });
}

/**
 * 设置弹框页面
 */
function createConfirm() {
    return `<form class="layui-form" action="">

              <div style="height: 20px"></div>
              
              <div class="layui-inline">
                 <label class="layui-form-label">输入IP</label>
                 <div class="layui-input-inline">
                     <input type="tel" name="ip" placeholder="请输入电脑IP" autocomplete="off" class="layui-input">
                  </div>
              </div>
              
              <div style="height: 20px"></div>
              
               <div class="layui-inline">
                 <label class="layui-form-label">输入port</label>
                 <div class="layui-input-inline">
                     <input type="tel" name="port" placeholder="请输入appium的port" autocomplete="off" class="layui-input">
                  </div>
              </div>
              
              <div style="height: 20px"></div>
              
               <div class="layui-inline">
                 <label class="layui-form-label">输入udid</label>
                 <div class="layui-input-inline">
                     <input type="tel" name="udid" placeholder="请输入手机序列号" autocomplete="off" class="layui-input">
                  </div>
              </div>
              
              <div style="height: 20px"></div>
               
              <div class="layui-form-item">
                <div class="layui-input-block">
                  <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                  <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
              </div>
            </form>
             
            <script>
            //Demo
            layui.use('form', function(){
              var form = layui.form;
              //监听提交
              form.on('submit(formDemo)', function(data){                            
                addPhoneInfoConfirm(data)
                return false;
              });
            });
            </script>`;
}

/**
 * 获取用户输入内容，并反馈到街面上
 */
function addPhoneInfoConfirm(data) {

    if (schemaVue.phoneInfo.length + 1 > 30 || schemaVue.phoneInfo.length < 0) {
        layer.msg("添加设备数量一次性不允许超过30个");
        return;
    } else {
        var ip = ip = data.field.ip;
        var port = data.field.port;
        var udid = data.field.udid;
        if (schemaVue.phoneInfo.has(ip + port)) {
            layer.msg("不允许一次提交中ip+port相同值出现两次");
            return;
        } else {
            schemaVue.phoneInfo.set(ip + port, {
                ip: ip,
                port: port,
                udid: udid
            });
        }
    }

    layui.use("table", function (key) {
        var table = layui.table;
        table.render({
            elem: '#phoneTable',
            cols: [[{
                field: "ip",
                title: "电脑IP"
            }, {
                field: "port",
                title: "端口号"
            }, {
                field: "udid",
                title: "手机序列号"
            }, {
                title: "操作",
                fixed: 'right',
                width: 178,
                align: 'center',
                toolbar: '#barDemo'
            }]],
            data: mapToArr(schemaVue.phoneInfo),
            limit: 30
        });

        //监听表格复选框选择
        table.on('checkbox(phoneTable)', function (obj) {
            console.log(obj)
        });
        //监听工具条
        table.on('tool(phoneTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                layer.alert('行信息为：<br>' + JSON.stringify(data))
            } else if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    console.log("删除", obj);
                    schemaVue.phoneInfo.remove(obj.ip + obj.port);
                    obj.del();
                    layer.close(index);
                });
            }
        });
    });
}

function mapToArr(map) {
    var temp = [];
    for (let value of map.values()) {
        temp.push(value)
    }
    return temp;
}

//==================================手机信息填写表


//==================================提交数据

/**
 * 提交这一次的提交信息
 */
function submitSchemaTest() {
    var submit = {};
    submit.computers = mapToArr(schemaVue.phoneInfo);
    submit.schemas = getSchemasId();
    submit.apkId = schemaVue.apk;
    if (submit.computers == null) {
        alert("你的设备信息未添加，无法提交测试请求");
        return;
    }
    if (submit.schemas == null || submit.schemas.length === 0) {
        alert("你的schema信息未选择，无法提交测试请求")
        return;
    }
    if (submit.apkId == null) {
        alert("你的apk信息提交不完整，无法提交测试请求")
        return;
    }
    $.ajax({
        url: "/schema/schemaServer",
        data: JSON.stringify(submit),
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (res) {
            console.log("正确反馈信息", res)
        },
        error: function (res) {
            console.log("异常反馈信息", res)
        }
    })
}

function getSchemasId() {
    var schemaId = [];
    var schemaArr = mapToArr(schemaVue.checkValue);
    var len = schemaArr.length;
    for (var i = 0; i < len; i++) {
        schemaId.push(schemaArr[i].id);
    }
    return schemaId;
}

//==================================提交数据


//==================================apk
function showApkInfo() {
    // 设置参数
// 设置表头
    var schemaCols = [
        [{
            type: 'radio',
        }, {
            field: "id",
            title: "ID"
        }, {
            field: "runEnvironment",
            title: "运行环境"
        }, {
            field: "version",
            title: "apk版本"
        }, {
            field: "installPath",
            title: "安装路径"
        }, {
            field: "appPackage",
            title: "apk包名"
        }, {
            field: "appActivity",
            title: "apk活动"
        }, {
            field: "createTime",
            title: "创建时间"
        }, {
            field: "updateTime",
            title: "更新时间"
        }]
    ];
    // 请求地址
    var url = "/schema/findApkList/pagination";
    // 工具ID
    var toolbar = "#toolbarDemo";
    // 导出文件时的文件名字
    var title = "ApkList";
    // 是否需要统计
    var totalrow = false;
    // 是否分页
    var page = true;
    // 表格ID
    var id = "apk";
    // 是否显示加载
    var loading = true;

    var toolsStr = `<table class="layui-hide"  id=${id}  lay-filter=${id}></table>
                <script type="text/html" id="toolbarDemo">
                  <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-sm" lay-event="getCheckData">确定选中</button>
                    <button class="layui-btn layui-btn-sm" lay-event="viewData">查看选中的数据</button>
                  </div>
                </script>`;

    showTable(id, url, toolbar, title, totalrow, schemaCols, page, loading, toolsStr, function (res, curr, count) {
        console.log("单选框-数据加载触发", res, curr, count);
        var currData = res.data;
        //配置当前页的选中状态
        var temp = currData;
        var len = currData.length;
        for (var i = 0; i < len; i++) {
            if (temp[i].id === schemaVue.apk) {
                console.log("找到选中的数据",temp[i]);
                //这里才是真正的有效勾选
                res.data[i]["LAY_CHECKED"] = 'true';
                //找到对应数据改变勾选样式，呈现出选中效果
                var index = res.data[i]['LAY_TABLE_INDEX'];
                $('tbody tr[data-index='+index+']').addClass("layui-table-click");
                $('tbody tr[data-index='+index+'] input[type=radio]').next().addClass("layui-form-radioed");
                $('tbody tr[data-index='+index+'] input[type=radio]').next().children("i").addClass("layui-anim-scaleSpring");
                $('tbody tr[data-index='+index+'] input[type=radio]').next().children("i").text("");
                break;
            }
        }
    });

    layui.use("table", function (key) {
        var table = layui.table;

        //头工具栏事件
        table.on('toolbar('+id+')', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
            switch (obj.event) {
                case 'getCheckData':
                    var data = checkStatus.data;  //获取选中行数据
                    console.log("data", data);
                    schemaVue.apk = data[0].id;
                    layer.alert("选中了apk:id=" + schemaVue.apk);
                    break;
                case 'viewData':
                    console.log("查看选中数据",JSON.stringify(schemaVue.apk));
                    layer.alert("选中了apk:<hr>" + JSON.stringify(schemaVue.apk));
                    break;
            }
        });
    })

}


//==================================apk


//==================================查看结果
function viewSchemaTestResult() {

}


//==================================查看结果


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
//     schemaVue.currPageData = res.data;
//     console.log("获取信息", res)
//     console.log("赋值信息", schemaVue.currPageData)
//     //配置当前页的选中状态
//     var temp = schemaVue.checkValue;
//     var len = schemaVue.currPageData.length;
//     for (var i = 0; i < len; i++) {
//         var tempValue = temp.has(md5(schemaVue.currPageData[i].path));
//         console.log("tempValue",tempValue);
//         console.log("md5",md5(schemaVue.currPageData[i].path));
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
















