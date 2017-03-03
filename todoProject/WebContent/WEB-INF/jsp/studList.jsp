<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  <head>  
    <title>学生列表</title>  
  </head>  
  <body>  
    <table border="1" width="600px">
		<thead>
			<tr>
				<th>id</th>
				<th>学号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>专业</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${studList}" var="u">  
				<tr>
					<td>${u.stId}</td>
					<td>${u.stNo}</td>
					<td>${u.stName}</td>
					<td>${u.stSex}</td>
					<td>${u.stMajor}</td>
					<td>
						<a href="#">修改</a> <a href="#">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>  
  </body>  
</html>