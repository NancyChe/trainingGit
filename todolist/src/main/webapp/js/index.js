// index.js
// 有关span上添加事件时使用remove的情况

//XMLHttpRequest对象
var xmlHttpReq = null;

//创建XMLHttpRequest
function createXMLHttpRequest(){
	if(window.XMLHttpRequest){
		xmlHttpReq = new XMLHttpRequest();
	}else{
		try{
			xmlHttpReq = new ActiveXObject("MSXML2.XMLHTTP");
		}catch(e){
			try{
				xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			}catch(e){}
		}
	}
	if(!xmlHttpReq){
		alert("当前浏览器不支持");
		return null;
	}
}

//Ajax请求
function sendData(value,url){
	createXMLHttpRequest();
	xmlHttpReq.open("POST",url,false);
	xmlHttpReq.setRequestHeader("Content-Type","application/json;charset=utf-8");
	xmlHttpReq.send(value);
	return xmlHttpReq.responseText;	
}

// load list
function load(){
//	var collection = localStorage.getItem("todo");
	var collection = sendData(uip, "getAll.do");
	var todolist = document.getElementById("todolist");
	var donelist = document.getElementById("donelist");
	if(collection != null){
		//show list
		var data = JSON.parse(collection);
		var todocount = 0;
		var donecount = 0;
		var todoString = "";
		var doneString = "";
		for(var i = 0; i < data.length; i++){
			if(data[i].done==1){
				// onclick='edit("+i+")'
				doneString += "<li><input type='checkbox' onchange='update("+data[i].id+",0,\""+data[i].title+"\")' /><p id='p_"+data[i].id+"' onclick='edit("+data[i].id+",1)'>"+data[i].title+"</p><span onclick='del("+data[i].id+")'></span></li>";
				donecount++;
			}else{
				todoString += "<li><input type='checkbox' onchange='update("+data[i].id+",1,\""+data[i].title+"\")' /><p id='p_"+data[i].id+"' onclick='edit("+data[i].id+",0)'>"+data[i].title+"</p><span onclick='del("+data[i].id+")'></span></li>"
				todocount++;
			}
		}
		todolist.innerHTML = todoString;
		document.getElementById("todocount").innerHTML = todocount;
		donelist.innerHTML = doneString;
		document.getElementById("donecount").innerHTML = donecount;
	}else{
		
	}
	//checkbox change
	// var checkbox = document.getElementsByName("checkbox");
	// for(var i = 0; i < checkbox.length; i++){
	// 	console.log(checkbox[i]);
	// }
}

//form action
function postaction () {
	var title = document.getElementById("title");
	if(title == ""){
		alert("title can not be empty, please fill in");
	}else{
		var todo = '{"id":0,"title":"'+title.value+'","done":0,"uip":"'+uip+'"}';
		// 向后端发送数据
		sendData(todo,"save.do");
		load();
	}
}

// saveData
// function saveData(data){
// 	localStorage.setItem("todo",JSON.stringify(data));
// }

// load data
function loadData(){
	var collection = sendData(uip,"getAll.do");
	if(collection != null){
		//parse用于从一个字符串解析出一个json对象
		return JSON.parse(collection);
	}else{
		return [];
	}
}

// change item state
function update(index,nextState,title){
	var todo='{"id":'+index+',"title":"'+title+'","done":'+nextState+',"uip":"'+uip+'"}';
	console.log(todo);
	sendData(todo,"update.do");
	load();
}

// remove item
function del(index){
	sendData(index+","+uip,"delete.do");
	load();
}

// edit item
function edit(i,state)
{
	// load();
	var p = document.getElementById("p_"+i);
	title = p.innerHTML;
	p.innerHTML="<input id='input-"+i+"' value='"+title+"' />";
	var input = document.getElementById("input-"+i);
	input.setSelectionRange(0,input.value.length);
	input.focus();
	input.onblur =function(){
		if(input.value.length == 0){
			p.innerHTML = title;
			alert("内容不能为空");
		}
		else{
			update(i,state,input.value);
			
		}
	};
}


//get user ip
var uip = returnCitySN.cip;
var strUip = '{"uip":"'+uip+'"}';
console.log(strUip);

window.onload = load();
//form submit
var form = document.getElementById("form");
form.addEventListener("submit",postaction,false);

