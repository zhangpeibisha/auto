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
	var muens = create_use_muens(user.user_center);
	return `
     <!--用户信息-->
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src=${user.img} class="layui-nav-img">
          ${user.name}
        </a>
        <dl class="layui-nav-child">
          ${muens}
        </dl>
      </li>
      <li class="layui-nav-item"><a href="">${user.controller}</a></li>
    </ul>
  `;
}

// 单个菜单
function create_use_muen(muen) {
	return ` <dd><a href=${muen.url}>${muen.name}</a></dd>`;
}
// 创建多个菜单
function create_use_muens(muens) {
	var str = "";
	$.each(muens, function(index, item) {
		str += create_use_muen(item);
	});
	return str;
}

// 构造导航栏

// 头部完整信息
var header = {
	"name": "头部标题信息",
	"user": {
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
}

// 创建整个完整的头
function create_hearder(header){
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
$("#hearder").html(create_hearder(header))

var choose = {
	"url":"路径",
	"name":"选中名字"
}
function add_hearder_choose(choose){
	return `<li class="layui-nav-item"><a href=${choose.url}>${choose.name}</a></li>`;
}

// 动态加载横排
var chooses = [];
function add_hearder_chooses(chooses){
	var str = "";
	$.each(chooses, function(index,item) {
		str += add_hearder_choose(item);
	});
	return str;
}
$("#chooses").html(add_hearder_chooses(chooses))
