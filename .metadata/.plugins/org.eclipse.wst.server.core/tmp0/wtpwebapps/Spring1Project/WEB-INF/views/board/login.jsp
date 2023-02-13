<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" rel="stylesheet"/>
	<title>로그인</title>
</head>
<body>
<div class="jumbotron">
		<div class="container"> 
			<h2 class="display-4">로그인</h2>
		</div>
</div>
		<div class="container">
			<form method="post" >
				<p>  아이디 : <input type="text" name="mem_id" id="mem_id"/> </p>
				<p>  비밀번호 : <input type="text" name="mem_pass" id="mem_pass"/> </p>
				<input type="submit" value="로그인" class="btn btn-primary"/>
			</form>
		</div>
</body>
</html>
