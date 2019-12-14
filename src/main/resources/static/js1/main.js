initMenu();
function initMenu(){
	 $.ajax({  
	     url:"/konsult/permissions/current",
	     type:"get",  
	     async:false,
	     success:function(data){
	    	 if(!$.isArray(data)){
	    		 location.href='/konsult/';
	    		 return;
	    	 }

	    	 var menu = $("#menu");
	    	 $.each(data, function(i,item){
	             var a = $("<a href='javascript:;'></a>");
	             
	             var css = item.css;
	             if(css!=null && css!=""){
	            	 a.append("<i aria-hidden='true' class='fa " + css +"'></i>");
	             }
	             a.append("<cite>"+item.name+"</cite>");
	             a.attr("lay-id", item.id);
	             
	             var href = item.href;
	             if(href != null && href != ""){
	                a.attr("data-url", href);
	             }
	             
	             var li = $("<li class='layui-nav-item'></li>");
	             if (i == 0) {
	            	 li.addClass("layui-nav-itemed");
	             }
	             li.append(a);
	             
	             //二级菜单
	             var child2 = item.child;
	             if(child2 != null && child2.length > 0){
	            	 $.each(child2, function(j,item2){
	            		 var ca = $("<a href='javascript:;'></a>");
                         ca.attr("data-url", item2.href);
                         ca.attr("lay-id", item2.id);
                         
                         var css2 = item2.css;
                         if(css2!=null && css2!=""){
                        	 ca.append("<i aria-hidden='true' class='fa " + css2 +"'></i>");
                         }
                         
                         ca.append("<cite>"+item2.name+"</cite>");
                         
                         var dd = $("<dd class='layui-nav-child'></dd>");
                         dd.append(ca);
                         
                         var dl = $("<dl class='layui-nav-child'></dl>");
                         dl.append(dd);
                         
                         li.append(dl);
	            	 });
	            }
	            menu.append(li);
	        });
	     }
	 });



}

// 登陆用户头像昵称
showLoginInfo();
function showLoginInfo(){
	$.ajax({
		type : 'get',
		url : '/konsult/users/current',
		async : false,
		success : function(data) {
			$(".admin-header-user span").text(data.nickname);
			
			var pro = window.location.protocol;
			var host = window.location.host;
			var domain = pro + "//" + host;
			
			var sex = data.sex;
			var url = data.headImgUrl;

			if(url == null || url == ""){
				if(sex == 1){
					url = "/konsult/images/sunny.png";
				} else {

					url = "/konsult/images/1.png";
				}
				
				url = domain + url;
			} else {
				url = domain + "/konsult/statics" + url;
			}
			var img = $(".admin-header-user img");
			img.attr("src", url);
		}
	});
}

//showUnreadNotice();
function showUnreadNotice(){
	$.ajax({
		type : 'get',
		url : '/konsult/notices/count-unread',
		success : function(data) {
			$("[unreadNotice]").each(function(){
				if(data>0){
					$(this).html("<span class='layui-badge'>"+data+"</span>");
				}else{
					$(this).html("");
				}
			});
			
		}
	});
}

function logout(){
	$.ajax({
		type : 'get',
		url : '/konsult/logout',
		success : function(data) {
			localStorage.removeItem("token");
			location.href='/konsult/';
		}
	});
}

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

var active;

layui.use(['layer', 'element'], function() {
	var $ = layui.jquery,
	layer = layui.layer;
	var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
    element.on('nav(demo)', function(elem){
      //layer.msg(elem.text());
    });
	   activity={
		tabAdd: function(obj){
		    var tabs = $(".layui-tab-title").children();
            var lay_id = $(obj).attr("lay-id");
            for(var i = 0; i < tabs.length; i++) {
                if($(tabs).eq(i).attr("lay-id") == lay_id) {
                    active.tabChange(lay_id);
                    return;
                }
            }
			var lay_id =obj.getAttribute("lay-id");
			var title = $(obj).html() + '<cite>批次商品详情</cite>'+ '<i class="layui-icon" data-id="' + lay_id + '"></i>';
		//新增一个Tab项
		element.tabAdd('admin-tab', {
			title: title,
			content: '<iframe src="' + $(obj).attr('data-url') + '"></iframe>',
			id: lay_id
		});
		element.tabChange("admin-tab", lay_id);
			resize();
	},

	}
		//触发事件
	   active = {

	       tabAdd: function(obj){
	    	 var lay_id = $(this).attr("lay-id");
	    	 var title = $(this).html() +'<i class="layui-icon" data-id="' + lay_id + '"></i>';
	         //新增一个Tab项  
	         element.tabAdd('admin-tab', {  
	           title: title,
	           content: '<iframe src="' + $(this).attr('data-url') + '"></iframe>',
	           id: lay_id
	         });  
	         element.tabChange("admin-tab", lay_id);    
	       }, 
	       tabDelete: function(lay_id){
    	      element.tabDelete("admin-tab", lay_id);
    	   },
	       tabChange: function(lay_id){
	         element.tabChange('admin-tab', lay_id);
	       }  
	   };  
	   //添加tab  
	   $(document).on('click','a',function(){
	       if(!$(this)[0].hasAttribute('data-url') || $(this).attr('data-url')===''){
	    	   return;  
	       }

	       var tabs = $(".layui-tab-title").children();
	       var lay_id = $(this).attr("lay-id");

	       for(var i = 0; i < tabs.length; i++) {
	           if($(tabs).eq(i).attr("lay-id") == lay_id) {
	        	   active.tabChange(lay_id);
	               return;  
	           }    
	       }
		   active["tabAdd"].call(this);
		   var u = navigator.userAgent;
		   var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
		   var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端

		   if(!isiOS){
			   resize();
		   }
		   else{
			   (function() {
				   var cacheHeight = 0;
				   function run() {
					   var mf = $("#mainFrame")[0];
					   // when the main frame has already been loaded, the check its height
					   if (mf && mf.contentDocument && mf.contentDocument.body) {
						   var iframeHeight = $("#mainFrame")[0].contentDocument.body.clientHeight;
						   if (iframeHeight && iframeHeight != cacheHeight) {
							   // cache the main frame height
							   cacheHeight = iframeHeight;
							   $("#mainFrame").height(iframeHeight);

						   }
					   }
					   setTimeout(run, 200);
				   }
				   run();
			   })();
		   }

	   });






		//iframe自适应
	   function resize(){

	       var $content = $('.admin-nav-card .layui-tab-content');
	       $content.height($(this).height() - 147);
	       $content.find('iframe').each(function() {
	           $(this).height($content.height());
	       });
	   }  
	   $(window).on('resize', function() {
	       var $content = $('.admin-nav-card .layui-tab-content');
	       $content.height($(this).height() - 147);
	       $content.find('iframe').each(function() {
	           $(this).height($content.height());
	       });
	   }).resize();



	   
	   //toggle左侧菜单  
	   $('.admin-side-toggle').on('click', function() {
	       var sideWidth = $('#admin-side').width();  
	       if(sideWidth === 200) {  
	           $('#admin-body').animate({  
	               left: '0'  
	           });
	           $('#admin-footer').animate({  
	               left: '0'  
	           });  
	           $('#admin-side').animate({  
	               width: '0'  
	           });  
	       } else {  
	           $('#admin-body').animate({  
	               left: '200px'  
	           });  
	           $('#admin-footer').animate({  
	               left: '200px'  
	           });  
	           $('#admin-side').animate({  
	               width: '200px'  
	               });  
	           }  
	       });
	   
	    //手机设备的简单适配
	    var treeMobile = $('.site-tree-mobile'),
	    shadeMobile = $('.site-mobile-shade');
	    treeMobile.on('click', function () {
	        $('body').addClass('site-mobile');
	    });
	    shadeMobile.on('click', function () {
	        $('body').removeClass('site-mobile');
	    });
});