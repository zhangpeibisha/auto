// 用于展现按钮模型的类
var model = {
	"title": "新增用户",
	"class": "class信息",
	"url": "点击，跳转的处理信息页面",
	"buttonValue": "新增"
}

function butoonGroup(butoons){
	return `<div class="layui-btn-group">
			    ${butoons}
							</div>`;
}

$("#test").html(test(function(){
	console.log("value")
}))

function test(onclick){
	return `<input type="button" name="" id="" value="" onclick=${onclick} />`;
}



// 增加按钮
function addButton(title,url,buttonValue,click){
	return `<button class="layui-btn layui-btn-xs layui-btn-normal dw-dailog" dw-url=${url} dw-title=${title} dw-width="100%" dw-height="100%">
                      <i class="layui-icon">&#xe654;</i>${buttonValue}
                  </button>`;
}

$("#button").html(butoonGroup(addButton("zhangpei","baidu","ceshi")))

