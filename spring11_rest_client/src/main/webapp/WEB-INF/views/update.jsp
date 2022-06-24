<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="frm" action="update.do" method="post">
<input type="hidden" name="num" value="${dto.num}"> 

<p><input type="text" name="name" placeholder="${dto.name}"/></p>

<p><input type="text" name="age" placeholder="${dto.age}"/></p>

<p><input type="text" name="loc" placeholder="${dto.loc}"/></p>

<p><input type="submit" value="send"></p>

</form>
</body>
</html>