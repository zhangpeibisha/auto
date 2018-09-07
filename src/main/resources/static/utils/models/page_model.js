// 整个界面的模板信息


// 创建内容区域
function create_conten(content) {
	return `<div class="layui-body">${content}<div>`;
}

// 创建主体内容
function create_body(hearder, muen, bottom) {
	return `	<div class="layui-layout layui-layout-admin">
			<!--头部信息-->
             ${hearder}
			<!--菜单生成区-->
			${muen}
			<!--内容区域由各种模块代替-->
			<div class="layui-body">
			    <div id="content"></div>
			</div>
			<div class="layui-footer">
			<!-- 底部固定区域 -->
			${bottom}
		    </div>
		</div>`;
}

function getContent() {
	return `<!-- 内容主体区域 -->
				<div style="padding: 15px;">
					<fieldset class="layui-elem-field">
						<legend>控制台-系统信息</legend>
						<div class="layui-field-box">
							<table class="layui-table">
								<colgroup>
									<col width="150">
									<col width="200">
									<col>
								</colgroup>
								<thead>
									<tr>
										<th>昵称</th>
										<th>加入时间</th>
										<th>签名</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>贤心</td>
										<td>2016-11-29</td>
										<td>人生就像是一场修行</td>
									</tr>
									<tr>
										<td>许闲心</td>
										<td>2016-11-28</td>
										<td>于千万人之中遇见你所遇见的人，于千万年之中，时间的无涯的荒野里…</td>
									</tr>
									<tr>
										<td>sentsin</td>
										<td>2016-11-27</td>
										<td> Life is either a daring adventure or nothing.</td>
									</tr>
								</tbody>
							</table>
						</div>
					</fieldset>
					<fieldset class="layui-elem-field">
						<legend>控制台-最新公告</legend>
						<div class="layui-field-box">
							<table class="layui-table">
								<colgroup>
									<col width="150">
									<col width="200">
									<col>
								</colgroup>
								<thead>
									<tr>
										<th>昵称</th>
										<th>加入时间</th>
										<th>签名</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>贤心</td>
										<td>2016-11-29</td>
										<td>人生就像是一场修行</td>
									</tr>
									<tr>
										<td>许闲心</td>
										<td>2016-11-28</td>
										<td>于千万人之中遇见你所遇见的人，于千万年之中，时间的无涯的荒野里…</td>
									</tr>
									<tr>
										<td>sentsin</td>
										<td>2016-11-27</td>
										<td> Life is either a daring adventure or nothing.</td>
									</tr>
								</tbody>
							</table>
						</div>
					</fieldset>
				</div>
			</div>`;
}

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
				"url": "选项跳转的页面",
				"name": "我的测试"
			}, {
				"url": "选项跳转的页面",
				"name": "我好帅"
			}],
			"controller": "退出"
		}
	},
	"menu": [{
		"name": "用户管理"
	}, {
		"name": "测试资源",
		"son": [{
			"url": "rule.html",
			"name": "schema信息"
		}, {
			"url": "adminRole.html",
			"name": "服务器信息"
		}]
	}]
}

function create_page(page) {
	var header = create_hearder(page.header).toString();
	var menu = create_menu_ul(page.menu).toString();
	// 修改标题名字
	document.title = page.title;
	// 创建实体
	return create_body(header, menu, page.bottom);
}
$("#body").html(create_page(page))


$("#content").html(getContent())

// 该方法主要用于更换内容信息
function updateContetn(content){
	$("#content").html(content)
}
