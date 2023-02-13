<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" rel="stylesheet"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<title>게시글 상세보기</title>
</head>
<body>

<div class="jumbotron">
		<div class="container"> 
			<h2 class="display-4">게시글 상세보기</h2>
		</div>
	</div>
	<div class="container">
	<div class="row">
		<div class="col-md-12 mb-2">
				<div class="row">
						<div class="col-md-2">
							<label for="bo_title" class="col-form-label">제목</label>
						</div>
						<div class="col-md-10">${board.BO_TITLE}</div>
					</div>
				</div>
				<div class="col-md-12 mb-2">
					<div class="row">
						<div class="col-md-2">
							<label for="bo_writer" class="col-form-label">작성자</label>
						</div>
						 <div class="col-md-10">${board.BO_WRITER}</div>
					</div>
				</div>
				<div class="col-md-12 mb-2">
					<div class="row">
						<div class="col-md-2">
							<label for="bo_content" class="col-form-label">내용</label>
						</div>
						<div class="col-md-10">${board.BO_CONTENT}</div>
						
					</div>
				</div>
				<div class="col-md-12 mb-2">
					<div class="row">
						<div class="col-md-2">
							<label for="bo_date" class="col-form-label">작성일</label>
						</div>
						<div class="col-md-10">
							<fmt:formatDate value="${board.BO_DATE}" pattern="yyyy.MM.dd HH:mm:ss"/>
						</div>
					</div>
				</div>
				<div class="col-md-12 mb-2">
					<div class="row">
						<div class="col-md-2">
							<label for="bo_date" class="col-form-label">조회수</label>
						</div>
						<div class="col-md-10">${board.BO_HIT}</div>
					</div>
				</div>
				<a href="/board/update.do?bo_no=${bo_no}" class="btn btn-info">수정</a>
				<a href="/board/list.do" class="btn btn-primary">목록</a>
				<form action="/board/delete.do" method="post" id="delForm">
					<input type="hidden" name="bo_no" value="${bo_no }"/>
					<input type="button" class="btn btn-danger" value="삭제" id="delBtn"/>
				</form>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	var delBtn = $("#delBtn");
	var delForm = $("#delForm");
	
	delBtn.on("click", function(){
		if(confirm("정말로 삭제하시겠습니까?")){
			delForm.submit();
		}else{
			delForm.reset();
		}
	});
});

</script>

</html>