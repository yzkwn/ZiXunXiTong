//form序列化为json
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

//获取url后的参数值
function getUrlParam(key) {
	var href = window.location.href;
	var url = href.split("?");
	if(url.length <= 1){
		return "";
	}
	var params = url[1].split("&");
	
	for(var i=0; i<params.length; i++){
		var param = params[i].split("=");
		if(key == param[0]){
			return param[1];
		}
	}
}
//下载文件
var DownLoadFile = function (options) {
    var config = $.extend(true, { method: 'post' }, options);
    var $iframe = $('<iframe id="down-file-iframe" />');
    var $form = $('<form target="down-file-iframe" method="' + config.method + '" />');
    $form.attr('action', config.url);
    for (var key in config.data) {
        $form.append('<input type="hidden" name="' + key + '" value="' + config.data[key] + '" />');
    }
    $iframe.append($form);
    $(document.body).append($iframe);
    $form[0].submit();
    $iframe.remove();
}