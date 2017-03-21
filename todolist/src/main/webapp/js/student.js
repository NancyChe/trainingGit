//XMLHttpRequest对象
var xmlHttpReq = null;

// 创建XMLHttpRequest
function createXMLHttpRequest() {
	if (window.XMLHttpRequest) {
		xmlHttpReq = new XMLHttpRequest();
	} else {
		try {
			xmlHttpReq = new ActiveXObject("MSXML2.XMLHTTP");
		} catch (e) {
			try {
				xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
	if (!xmlHttpReq) {
		alert("当前浏览器不支持");
		return null;
	}
}

// Ajax请求
function sendData(value, url) {
	createXMLHttpRequest();
	xmlHttpReq.open("POST", url, false);
	xmlHttpReq.setRequestHeader("Content-Type",
			"application/json;charset=utf-8");
	xmlHttpReq.send(value);
	return xmlHttpReq.responseText;
}

function load() {
	var collection = sendData(null, "stlist.do");
	var title = document.getElementById("title");
	if (collection != null) {
		// show list
		var data = JSON.parse(collection);
		
		var html = "";
		for (var i = 0; i < data.length; i++) {

			html += "<tr><td class='tdId'>"
					+ data[i].id
					+ "</td><td class='tdText'>"
					+ data[i].stNo
					+ "</td><td class='tdText'>"
					+ data[i].stName
					+ "</td><td class='tdText'>"
					+ data[i].stSex
					+ "</td><td class='tdText'>"
					+ data[i].stMajor
					+ "</td><td><button class='edit'>修改</button><button class='save'style='display:none'>保存</button><button onclick='del(event)'>删除</button></td></tr>";

		}
		document.getElementById("title").innerHTML = html;
	}
}

function loadData() {
	var collection = sendData(null, "stlist.do");
	if (collection != null) {
		// parse用于从一个字符串解析出一个json对象
		return JSON.parse(collection);
	} else {
		return [];
	}
}

// 新增
function postaction() {
	var save = document.getElementById("form");
	if (save == "") {
		alert("save can not be empty, please fill in");
	} else {
		var todo = '{"stNo":"' + stNo.value + '","stName":"' + stName.value
				+ '","stSex":"' + stSex.value + '","stMajor":"' + stMajor.value
				+ '"}';
		// 向后端发送数据
		sendData(todo, "stadd.do");
		alert("添加成功!");
		load();
	}
}

// 删除 remove stu
function del(event) {
	var $tr = $(event.target).parent().parent();
	var id = $tr.find("td:first").html();
	if (confirm("确定删除第" + id + "数据？")) {
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/todolist/stdelete.do",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function(result) {
				alert("删除成功！");
				load();
			},
			error : function(result) {
				alert("删除失败！");
				load();
			}
		});
	}
}
//修改
$(function(){
	//var $tr = $(event.target).parent().parent();
	$(".save").hide();
	$(".edit").each(function(index, item){
		$(item).on('click',function(){
			$(item).next().show();
			$(item).hide();
			var objTD=$(this).parents("tr").find(".tdText");
			$(objTD).each(function(index2,item2){
				var oldText = $.trim($(item2).text()); // 保存老的类别名称 
				var input = $("<input type='text' value='" + oldText + "' />"); // 文本框的HTML代码 
				$(item2).html(input); // 当前td的内容变为文本框 
				input.css("border-width", "0px"); //边框为0 
				input.css("font-size", "10px"); // 文本框的内容文字大小为10px 
				input.css("text-align", "center"); // 文本居中 
				input.height($(item2).height()); //文本框的高度为当前td单元格的高度 
				input.width($(item2).width()); // 宽度为当前td单元格的宽度 
			});
		});
		
		/*$(item).on('click',function(){
			var objTD=$(this).parents("tr").find(".tdText");
		$(objTD).each(function(index2,item2){
			$(item2).addClass('editText');	
		});*/
			/*$(objTD).each(function(index2,item2){
				var oldText = $.trim($(item2).text()); // 保存老的类别名称 
				//var input = $("<input type='text' value='" + oldText + "' />"); // 文本框的HTML代码 
				$(item2).html(input); // 当前td的内容变为文本框 
				//设置文本框的点击事件失效
				$(input).on('blur',function(){
					$(item2).html($(input).val());
				});
				// 设置文本框的样式 
//				input.css("border-width", "0px"); //边框为0 
//				input.height($(item2).height()); //文本框的高度为当前td单元格的高度 
//				input.width($(item2).width()); // 宽度为当前td单元格的宽度 
				input.css("font-size", "14px"); // 文本框的内容文字大小为14px 
				input.css("text-align", "center"); // 文本居中 
			});*/
			
		//});
	});		
	
	$(".save").each(function(index, item){
		$(item).on('click',function(){
			$(item).prev().show();
			$(item).hide();
			var objTD=$(this).parents("tr").find(".tdText");
			var id = $(this).parents("tr").find(".tdId").text();
			var input = $('input');
			$(objTD).each(function(index2,item2){
				$(item2).html($(input[index2]).val());
			});
//			var data = {"id":id,
//					"stNo" : $($(objTD)[0]).text(),
//					"stName":$($(objTD)[1]).text(),
//					"stSex": $($(objTD)[2]).text(),
//					"stMajor":$($(objTD)[3]).text()};
			var data ='{"id":"' + id + '","stNo":"' + $($(objTD)[0]).text()
			+ '","stName":"' + $($(objTD)[1]).text() + '","stSex":"' + $($(objTD)[2]).text()+ '","stMajor":"' + $($(objTD)[3]).text()
			+ '"}';
			
			$.ajax({
				type : "POST",
				url : "http://localhost:8080/todolist/stupdate.do",
				
				data : data,
				//dataType : "json",
				contentType:"application/json",
				success : function(result) {
					var res = eval("("+ result +")");
					alert("修改成功！"+ res.code);				
					load();
				},
				error : function(result) {
					alert("修改失败！"+result.responseText);
					load();
				}
			});
		});
	});
	         
	
});

//function GetRequest() {
//	var url = location.search; // 获取url中"?"符后的字串
//	//alert(url);
//	var theRequest = new Object();
//	
//	$.ajax({
//		type : "POST",
//		url: url,
//		data : {
//			"id" : id,
//			
//		},
//		dataType : "json",
//		success:function(obj){
//		     $('#key').each(function(e){
//		         $(this).html(obj.stNo);
//		     })
//		      
//		    }
//		
//	});
//}
////	if (url.indexOf("?") != -1) {
////		var str = url.substr(1);
////		strs = str.split("&");
////		for (var i = 0; i < strs.length; i++) {
////			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
////		}
////	}
//	return theRequest;
//} 


	

window.onload = load();
// form submit
/*
 * var form = document.getElementById("form"); form.addEventListener("submit",
 * postaction, false);
 */