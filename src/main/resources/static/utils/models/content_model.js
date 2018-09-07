// 内容模版

// 1.schema展示内容

var schema_list = {
	"list_name": [
		"路径", "含义", "tpl", "备注", "最低支持版本", "最高支持版本", "带参链接/使用说明"
	],
	"schemas": [{
		"url": "www.baidu.com",
		"name": "名字",
		"use": true,
		"eg": "",
		"use_version": "3.0.0",
		"max_use_version": "6.0.0",
		"remarks": "备注信息",
		"create_time": "创建时间",
		"update_time": "修改时间",
		"id": "数据库中的ID值"
	}]
}

// 创建一行的数据
function create_td(td) {
	return `<td>${td}</td>`;
}
// 创建一个schema的列的信息信息
function create_tds_by_json(schema) {
	var str = "";
	for(var key in schema) {
		str += create_td(schema[key]);
	}
	return str;
}
// 创建一个完整的scehma的列的信息
function create_complete_sdchema_li(schema) {
	var tds = create_tds_by_json(schema);
	return `
	           <tr>
	               <td><input type="checkbox" name="id" value=${schema.id}></td>
	               ${tds}
              </tr>`;
}
console.log("schema li")
var schema_value = {
		"url": "www.baidu.com",
		"name": "名字",
		"use": true,
		"eg": "",
		"use_version": "3.0.0",
		"max_use_version": "6.0.0",
		"remarks": "备注信息",
		"create_time": "创建时间",
		"update_time": "修改时间",
		"id": "数据库中的ID值"
	}
console.log(create_complete_sdchema_li(schema_value))
$("#testValue").html(create_complete_sdchema_li(schema_value))
// 创建一个数组schema的值的table
function cerate_lists_tds(schemas) {
	var str = "";
	$.each(schemas, function(index, item) {
		str += create_complete_sdchema_li(item);
	});
}
// 坐表格标题
function create_table_titile(list_name) {
	var str = "";
	$.each(list_name, function(index, item) {
		str += create_table_th();
	});

	return ` <thead>
              <tr>
                 <th class="selectAll"><input type="checkbox"></th>
                 ${str}
              </tr> 
          </thead>`;
}

function create_table_th(titile) {
	return `<th>${title}</th>`;
}

function create_table(schema_list) {
   var title = create_table_titile(schema_list.list_name);
}