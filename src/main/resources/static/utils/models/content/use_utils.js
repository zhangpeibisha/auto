// 后台组装页面信息
// 页面完整的json数据组合
var page = {
	"title": "YQB测试工具平台",
	"bottom": "© yqb.com - 平安付",
	"header": {
		"name": "YQB测试平台",
		"user": {
			"img": "http://t.cn/RCzsdCq",
			"name": "张沛341",
			"user_center": [{
				"id": "test",
				"name": "我的测试"
			}, {
				"id": "jump",
				"name": "我好帅"
			}],
			"controller": "退出"
		}
	},
	"menu": [{
		"id": "mange",
		"name": "用户管理"
	}, {
		"id": "testResource",
		"name": "测试资源",
		"son": [{
			"id": "schema",
			"name": "schema信息"
		}, {
			"id": "adminRole.html",
			"name": "服务器信息"
		}]
	}]
}

// 0.初始化页面
$("#body").html(create_page(page))

function click(obj) {
	var id = $(obj).attr("id");
	if(id === "schema") {
		updateContent_schema();
	} else {
		console.log("use")
		console.log(obj)
		updateContent("");
	}
}

// 1.配置用户添加电脑信息界面
var bodys = [{
	"id": 3,
	"name": "张沛",
	"face": "帅",
	"address": "重庆"
}, {
	"id": 3,
	"name": "张沛",
	"face": "帅",
	"address": "重庆"
}]
// 展示可选schema信息
function updateContent_schema() {
	updateContent(body("张沛帅哥", serch("请输入schema", "runTest()"), ""))
	create_pagination();
	update_table_info(bodys)
}

function serche(obj) {
	console.log("use", obj)
}

// 展示用户提交记录及记录详细信息展示