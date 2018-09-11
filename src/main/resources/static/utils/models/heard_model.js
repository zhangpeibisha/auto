// 用来构建头部信息展示

// 用户信息json展示
var user = {
	"img": "http://t.cn/RCzsdCq",
	"name": "用户名字",
	"user_center": [{
		"url": "选项跳转的页面",
		"name": "页面标题"
	}, {
		"url": "选项跳转的页面",
		"name": "页面标题"
	}],
	"controller": "退出"
}

function create_user_info(user) {
	var muens = create_use_center_muen(user.user_center);
	return `
     <!--用户信息-->
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src=${user.img} class="layui-nav-img" id="user-img">
          <label id="user-name">${user.name}</label>
        </a>
        <dl class="layui-nav-child" id="user-muens">
          ${muens}
        </dl>
      </li>
      <li class="layui-nav-item"><a href="javascript:;" id="exit" onclick="hearder_run(this)">${user.controller}</a></li>
    </ul>
  `;
}

// 单个菜单
function create_use_center(muen) {
	return ` <dd><a href="javascript:;" id=${muen.id} onclick="hearder_run(this)">${muen.name}</a></dd>`;
}
// 创建多个菜单
function create_use_center_muen(muens) {
	var str = "";
	$.each(muens, function(index, item) {
		str += create_use_center(item);
	});
	return str;
}

// 构造导航栏

// 头部完整信息
//var header = {
//	"name": "头部标题信息",
//	"user": {
//		"img": "http://t.cn/RCzsdCq",
//		"name": "用户名字",
//		"user_center": [{
//			"url": "选项跳转的页面",
//			"name": "页面标题"
//		}, {
//			"url": "选项跳转的页面",
//			"name": "页面标题"
//		}],
//		"controller": "退出"
//	}
//}

// 创建整个完整的头
function create_hearder(header) {
	var userInfo = create_user_info(header.user);
	return `<div class="layui-header">
    <div class="layui-logo">${header.name}</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
       <!-- 选中的选卡 -->
       <div id="chooses"></div>
    </ul>
    ${userInfo}
  </div>`;
}
//$("#hearder").html(create_hearder(header))

function add_hearder_choose(choose) {
	return `<li class="layui-nav-item"><a href="javascript:;" id=${choose.id} onclick="hearder_run(this)">${choose.name}</a></li>`;
}

// 动态加载横排
var chooses = [];

function add_hearder_chooses(chooses) {
	var str = "";
	$.each(chooses, function(index, item) {
		str += add_hearder_choose(item);
	});
	$("#chooses").html(str)
}

// 除了特定的用户信息管理，其它交给统一处理
function hearder_run(obj) {
	if($(obj).attr("id") === "exit" || $(obj).attr("id") === "login") {
		user_login_exit(obj)
	} else {
		// 做按钮控制
		//处理点击时间，按钮选择
		click(obj);
	}

}

// 用户登陆或者退出
function user_login_exit(obj) {
	console.log("img", $("#user-img")[0])
	if($(obj).attr("id") === "exit") {
		// 发出退出系统处理信息
		console.log("用户退出", obj);
		$(obj).text("登陆");
		$(obj).attr("id", "login")
		console.log("img", $("#user-img"))
		$("#user-img").attr("src", "https://www.baidu.com/link?url=KT_BYCP3e7E64WXR48-aVUiYRxxnFBa6DFnYJx14hA_ll1JzoixhFFQ0uFeZWfFDUghLZV90OGHhvjHhhlZ_mfrvRQodhofOwPSY1JuidvpwdLt99IjuVuB4cKEUY6qV163P-wJGaVj6J_uA_FI0xxkdgrVAM5PY4oqSD-d1sNQAuxwRXNT0jXyRXVYPhCKlobrhrnO9JNtADssXLxj5LTTJfUA7p2QQOqkxI0dDrOOmn7P37uuHZ_iYkoqJpPXL75GZX83Ypzj6c3NT9NH17JzSmnAHM5dy92jcrUuPkECnWxv0jhOawwEepoOOr31ZOQBESwB04opAenpxmUQ79ES2sywtmlHtijdTxr3RXg2CpRKyJqDm3h-VLLZuq_4Gci8JAvgXv5QReEUGjHoCD7w-dZ-ei4rUAQqVw1cn-QV76k3b0vWvJk9xYUIvqBiqXizOdUwq4c45Q6I63YL1TkyAIg_WyuEuNoqmu8x2Qgw3U9h3LST7n1eF6n7rqibIbucuqIDCmidm_7VxtJgGlouzGckWbDFrTzYwUkvjqmG5B2liVSGC_6-3lYa0iSelxafYEtoaIyJh2q1gW_me-30LM8ReSQGORY_U758dvODc_PC8XXrrFaalJAYvdJhguGeQPLFIoDhAutuWqiFSBK&timg=&click_t=1536635559867&s_info=2313_1227&wd=&eqid=c18579e70001a0aa000000025b9732a3");
		$("#user-name").text("请先登陆");

		$("#user-muens").empty();
	} else {
		// 发出登陆系统处理信息
		console.log("用户登陆", obj);
		$(obj).text("退出");
		$(obj).attr("id", "exit")
		$("#user-img").attr("src", "http://t.cn/RCzsdCq");
		$("#user-name").text("张沛");

		// 显示下拉菜单
		$("#user-muens").html(create_use_center_muen(user.user_center));
	}

}