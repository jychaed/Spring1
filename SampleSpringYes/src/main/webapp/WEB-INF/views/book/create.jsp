<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" rel="stylesheet"/>
<title>책 생성하기</title>
</head>
<body>

<h2>책 생성하기</h2>
	<form method="post">
		<p> 제목 : <input type="text" name="title" /></p>
		<p> 카테고리 : <input type="text" name="category" /></p>
		<p> 가격 : <input type="text" name="price" /></p>
		<p> <input type="submit" value="저장" /></p>
	</form>
		
</body>
</html>