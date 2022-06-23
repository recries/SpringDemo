<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div id="wrap">
	<table>
	
	<tr>
	<th>num</th><th>name</th><th>age</th><th>loc</th>
	</tr>
	
	<tr>
	<td>${dto.num}</td><td>${dto.name}</td><td>${dto.age}</td><td>${dto.loc}</td>
	</tr>
	
	</table>
 </div>
</body>
</html>