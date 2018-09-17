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
                jump: "jump(this)"
            }, {
                name: "工作台",
                jump: "jump(this)"
            }]
        },
        login: {
            isLogin: true,
            value: "退出",
            jump: "jump(this)"
        },
        navbar: [{
            name: "控制台",
            jump: "jump(this)"
        }, {
            name: "其它系统",
            jump: "jump(this)",
            sons: [{
                name: "邮件管理",
                jump: "jump(this)"
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
            jump: "jump(this)"
        }, {
            name: "兼容性测试",
            jump: "jump(this)",
            sons: [{
                name: "schema",
                jump: "jump(this)"
            }, {
                name: "computer",
                jump: "jump(this)"
            }, {
                name: "apk",
                jump: "jump(this)"
            }, {
                name: "submit",
                jump: "jump(this)"
            }, {
                name: "result",
                jump: "jump(this)"
            }]
        }]
    }
})

/**
 * 更新模版中的内容
 * @param content
 */
function updateContent(content) {
    $("#content").html(content);
}

// 开始展示提交记录
showSubmitRecording();

function jump(obj) {
    console.log("jump", obj);
    var value = $(obj).text();
    switch (value) {
        case 'schema':
            showSchemaTable();
            break;
        case  'computer':
            addPhoneInfo();
            break;
        case 'apk':
            showApkInfo();
            break;
        case 'submit':
            submitSchemaTest();
            break;
        case 'result':
            viewSchemaTestResult();
            break;
        case '提交记录':
            showSubmitRecording();
            break;
    }
}
