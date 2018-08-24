// 完成功能操作

// body模版

$("#content").html(model_body("computer-1"))

function model_body(id_value){
	return `
	<div id=${id_value} class="layout-container">
			<div class="layout-cont">
				<div class="layout-cont-main">
					<div class="add-newclass">
						<form action="">
							<div class="category-cont">
								<!--大分类-->
								<div class="big-box category-box">
									<div class="big-category category">
										<input type="text" placeholder="请输入电脑IP地址" style="outline-style: none;"/>
										<div class="btns">
											<a><img src="img/icon_add.png" /></a>
											<a><img src="img/icon_edit.png" /></a>
											<a><img src="img/icon_close.png" /></a>
											<a class="up-btn"><img src="img/icon_bottom.png" /></a>
										</div>
									</div>
									
								    <div id="son-${id_value}">
									
								</div>
							</div>
							
							<div class="operation-btns">
								<a href="addCategory.html" class="layout-btn">+ 添加新分类</a>
								<input type="submit" class="layout-btn subbtn" value="保存" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	`;
}

$("#son-computer-1").html(samll_class())

// 填写电脑对应的port和对应的udid
function samll_class(){
	return `
    <!--prot-->
	<div class="in-box category-box">
		<div class="in-category category">
			<input type="text" placeholder="请填写该电脑开放appium服务器的端口" style="outline-style: none;"/>
			<div class="btns">
				<a><img src="img/icon_add.png" /></a>
				<a><img src="img/icon_edit.png" /></a>
				<a><img src="img/icon_close.png" /></a>
				<a class="up-btn"><img src="img/icon_bottom.png" /></a>
			</div>
		</div>
		<!--udid-->
		<div class="small-box category-box">
			
			<div class="small-category category">
				<input type="text" placeholder="请填写需要运行的手机的udid号码" style="outline-style: none;"/>
				<div class="btns">
					<a><img src="img/icon_edit.png" /></a>
					<a><img src="img/icon_close.png" /></a>
				</div>
			</div>
			
		</div>
	</div>
	`;
}
