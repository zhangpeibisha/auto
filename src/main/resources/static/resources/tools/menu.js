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

/**
 * 更新模版中的内容
 * @param content
 */
function updateContent(content) {
    $("#content").html(content);
}

updateContent("zhangpei")


