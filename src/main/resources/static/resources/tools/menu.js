// 头部信息，水平栏
var header = new Vue({
    el: "#header",
    data: {
        logo: "YQB-UI测试",
        user: {
            img: "http://t.cn/RCzsdCq",
            name: "zhangpei341@pingan.com.cn",
            permission: [{
                name: "个人中心",
                jump: ""
            }, {
                name: "工作台",
                jump: ""
            }]
        },
        login: {
            isLogin: true,
            value: "退出",
            jump: ""
        },
        navbar: [{
            name: "控制台",
            jump: ""
        }, {
            name: "其它系统",
            jump: "",
            sons: [{
                name: "邮件管理",
                jump: ""
            }]
        }]
    }
})

// 菜单信息
var menu = new Vue({
    el: "#menu",
    data: {
        menu: [{
            name: "提交记录",
            jump: ""
        }, {
            name: "兼容性测试",
            jump: "",
            sons: [{
                name: "schema",
                jump: ""
            }, {
                name: "computer",
                jump: ""
            }]
        }]
    }
})

function updateContent(content) {
    $("#content").html(content);
}

updateContent("zhangpei")

// table展示处理
var table = new Vue({
    el: "#content",
    data: {}
});

// 创建表格
function showTable(id, url, toolbar, title, totalrow, cols, page,initTable) {
    // 初始化表格
    updateContent(initTable);

    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem: id,
            url: url,
            toolbar: toolbar,
            title: title,
            totalRow: totalrow,
            cols: cols,
            page: page
        });

        //工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            layer.alert(JSON.stringify(data));
            switch (obj.event) {
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：' + data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选' : '未全选')
                    break;
            }
            ;
        });
    });
}


function setTableFunction() {
    return `<table class="layui-hide" id="test" lay-filter="test"></table>
                <script type="text/html" id="toolbarDemo">
                  <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
                    <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
                    <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
                  </div>
                </script>
                 
                <script type="text/html" id="barDemo">
                  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                </script>`;
}

// 示例参数
var cols = [
    [{
        type: 'checkbox',
        fixed: 'left'
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
var url = "/test/getSchemaJson";
var toolbar = "#toolbarDemo";
var title = "test";
var totalrow = true;
var page = true;
var id = "#test";
// 示例
showTable(id, url, toolbar, title, totalrow, cols, page,setTableFunction());
