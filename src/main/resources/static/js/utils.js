function isValidIP(ip) {
    var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/
    return reg.test(ip);
} 

function isPort(port){
	if(port>1023&&port<65535){
		return true;
	}
	return false;
}

var models = [{
    id:"submit",
    method:"submit(nowIndex)",
    message:"确认提交信息吗"
}];



function tankuangModel(model) {
    return `<div role="dialog" class="modal fade" id=${model.id} data-index="{{nowIndex}}">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                    <h4 class="modal-title">${model.message}</h4>
                </div>
                <div class="modal-body text-right">
                    <button class="btn btn-primary btn-sm" data-dismiss="modal">取消</button>
                    <button class="btn btn-danger btn-sm" data-dismiss="modal" v-on:click="${model.method}">确认</button>
                </div>
            </div>
        </div>
    </div>`;
}
console.log("model",models[0],tankuangModel(models[0]));
