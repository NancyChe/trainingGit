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

			html += "<tr><td>"
					+ data[i].id
					+ "</td><td>"
					+ data[i].stNo
					+ "</td><td>"
					+ data[i].stName
					+ "</td><td>"
					+ data[i].stSex
					+ "</td><td>"
					+ data[i].stMajor
					+ "</td><td>&nbsp;<button onclick='edit(event)'>修改</button>&nbsp;<button onclick='del(event)'>删除</button></td></tr>";

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
function edit(event) {
	var $tr = $(event.target).parent().parent();
var id = $tr.find("td:first").html();
	//alert(id);
	window.location.href="editStud.html?id=" + id;
//	$.ajax({
//		type : "POST",
//		url : "http://localhost:8080/todolist/findinfo.do",
//		data : {
//			"id" : id
//		},
//		dataType : "json",
//		success : function(result) {
//			console.log(result);
//			
//			//$("#edit")[0].append("<input type='text' value='123'/><br/><br/>");
//			//$("#edit")[0].append("<input type='text' value='123'/><br/><br/>");
//			
//		},
//		error : function(result) {
//			console.log(result);
//			alert("操作异常?!");
//		}
//	});


function GetRequest() {
	var url = location.search; // 获取url中"?"符后的字串
	//alert(url);
	var theRequest = new Object();
	
	$.ajax({
		type : "POST",
		url: url,
		data : {
			"id" : id,
			
		},
		dataType : "json",
		success:function(obj){
		     $('#key').each(function(e){
		         $(this).html(obj.stNo);
		     })
		      
		    }
		
	});
}
//	if (url.indexOf("?") != -1) {
//		var str = url.substr(1);
//		strs = str.split("&");
//		for (var i = 0; i < strs.length; i++) {
//			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
//		}
//	}
	return theRequest;
} 


	

window.onload = load();
// form submit
/*
 * var form = document.getElementById("form"); form.addEventListener("submit",
 * postaction, false);
 */