// 专门创建列表信息

// 更新内容体
function update_table_info(bodys) {
	$("#table").html(table_create(bodys));
}

// 0.大体框架
// tatile:标题头  search：搜索框代码  buttons：对整表操作的按钮
function body(title, serch, buttons) {
	return `	<!-- 内容主体区域 -->
				<div style="padding: 15px;">
					<fieldset class="layui-elem-field">
					
					     <legend>${title}</legend>
					     
					     <div class="layui-field-box">
						     ${serch}
						     
						     ${buttons}
						     
						     <div id='table'></div>
						     <div id='table-page'></div>
						     <div id='true' style='display: flex;justify-content: flex-end;'><button class='layui-btn' >
						     <i class='layui-icon'>&#xe615;</i>确 定</button></div>
	                     </div>
	                </fieldset>
	          </div>
	`;
}

// 1.搜索框信息
function serch(placeholder, clickName) {
	return `	<!--serch-->
			<form class="layui-form" action="">
				<div class="layui-form-item" style="text-align:center;">
					<div class="layui-inline">
						<div class="layui-input-inline">
							<input autocomplete="off" class="layui-input" placeholder="${placeholder}" type="text" name="name" value="" onclick="">
						</div>
					</div>
					<div class="layui-inline" style="text-align:left;">
						<div class="layui-input-inline" onclick=${clickName}>
							<button class="layui-btn" ><i class="layui-icon">&#xe615;</i>查询</button>
						</div>
					</div>
				</div>
			</form>`;
}

// 正常使用
function runTest(obj) {
	alert("成功", obj);
}

// 表格栏位数量
function table_colgroup(number) {
	var str = "<colgroup>";
	str += "<col>";
	for(var i = 0; i < number; i++) {
		str += "<col>";
	}
	str += "<col>";
	str += "</colgroup>";
	return str;
}

// 标题信息
function table_thead(body) {
	var str = "<thead>";
	str += "<th class='selectAll'>"
	str += "<input type='checkbox'/>"
	str += "</th>";
	for(var key in body) {
		str += "<th>";
		str += key;
		str += "</th>";
	}
	str += "</thead>";
	return str;
}

// 内容实际信息
function table_tbody(bodys) {
	var len = bodys.length;
	var str = "";
	str += "<tbody>";
	for(var i = 0; i < len; i++) {
		str += "<tr>";
		str += "<td><input type='checkbox' name='id' value=" + i + "></td>";
		for(var key in bodys[i]) {
			str += "<td>"
			str += bodys[i][key]
			str += "</td>"
		}
		str += "</tr>";
	}
	str += "</tbody>";
	return str;
}

function table_create(table) {
	// 必须大于0才可以运行这段代码
	if(table.length > 0) {
		var str = "<table class='layui-table''>";
		str += table_colgroup(table[0].length);
		str += table_thead(table[0]);
		str += table_tbody(table)
		str += "</table>"
	}
	return str;
}

// 成功展示
//console.log("执行")
//console.log(table_create(bodys));
//$("#content").html(table_create(bodys))
console.log("分页js执行")

function create_pagination() {
	// 分页代码
	layui.use(['laypage', 'layer'], function() {
		var laypage = layui.laypage,
			layer = layui.layer;
		var i = Math.round(Math.random() * 100);

		//完整功能
		laypage.render({
			elem: 'table-page',
			count: i,
			layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
			jump: function(obj) {
				console.log(obj);
				// 最终将做ajax请求处理
				if(obj.curr === 1) {
					// 处理分页逻辑
					var bodys = [{
						"id": 3,
						"name": "张沛",
						"face": "高"
					}, {
						"id": 3,
						"name": "张沛",
						"face": "高"
					}]
					update_table_info(bodys);
				} else {
					// 处理分页逻辑
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
					update_table_info(bodys);
				}

			}
		});
	})
}
// 处理分页逻辑
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
// 完整代码整理 - 成功
$("#content").html(body("张沛帅哥", serch("请输入schema", "runTest()"), ""));
update_table_info(bodys)