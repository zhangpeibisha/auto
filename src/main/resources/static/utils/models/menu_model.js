// 菜单模版信息

// 菜单json模型
var menu = [{
	"name": "用户管理",
	"son": [{
		"url": "admin.html",
		"name": "用户列表"
	}, {
		"url": "url",
		"name": "菜单名字"
	}]
}, {
	"name": "权限管理",
	"son": [{
		"url": "rule.html",
		"name": "规则列表"
	}, {
		"url": "adminRole.html",
		"name": "用户角色"
	}]
}]

//console.log("创建菜单")
//console.log(create_menu_ul(menu))

// 动态创建菜单
$("#muen").html(create_menu_ul(menu))


// 完整的菜单列表
function create_menu_ul(menu_json) {
	var content = create_menu_lis(menu_json);
	return `
	<div class="layui-side layui-bg-black">
       <div class="layui-side-scroll">
	     <ul class="layui-nav layui-nav-tree"  lay-filter="test">
	         ${content}
	     </ul> 
	   </div>
    </div>`;
}

function create_menu_lis(menu_json) {
	var str = "";
	$.each(menu_json, function(index, item) {
		str += frist_level_menu(item);
	});
	return str;
}

// 单个二级菜单
function second_level_menu(second_level) {
	return `<dd><a href=${second_level.herf}>${second_level.name}</a></dd>`;
}

// 组合信息
function second_level_menus(second_levels) {
	var str = "";
	$.each(second_levels, function(index, item) {
		str += second_level_menu(item);
	});
	return str;
}

// 单个一级菜单
function frist_level_menu(frist_level) {
	var sons = second_level_menus(frist_level.son);
	console.log("value",sons,sons==="",sons===null)
	if(sons==""){
		return `<li class="layui-nav-item">
          <a href="javascript:;">${frist_level.name}</a>
        </li>`;
	}else{
		return `<li class="layui-nav-item">
          <a href="javascript:;">${frist_level.name}</a>
          <dl class="layui-nav-child">
             ${sons}
          </dl>
        </li>`;
	}
	
}

// 后续准备在导航栏上实时放置用户点击的窗口