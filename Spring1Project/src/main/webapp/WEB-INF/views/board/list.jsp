<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" rel="stylesheet"/>
<title>게시판 목록</title>
</head>
<body>
<div class="jumbotron">
		<div class="container"> 
			<h2 class="display-4">게시판 상세보기</h2>
		</div>-
	</div>
	<div class="container">
	<div class="row">
			<div class="col-md-12 mb-2">
				<div class="row">
					<div class="col-md-7">
					</div>
						<div class="col-md-5">
							<form>
								<div class="row">
									<div class="col-md-10">
										<input type="text" class="form-control" name="keyword" value="${keyword}"/>
									</div>
									<div class="col-md-2">
										<select name="search">
										  <option value="BO_TITLE" selected="selected">제목</option>
										  <option value="BO_WRITER">작성자</option>
										  <option value="BO_CONTENT">내용</option>
										  <option value="BO_HIT">조회수</option>
										</select>
										<input type="submit" class="btn btn-secondary" value="검색"/>								
									</div>
								</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-12 mb-2">
				<div class="row">
					<table class="table"> 
						<thead class="table-dark">
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${empty boardList}">
									<tr>
										<td colspan="4">조회하실 게시물이 존재하지 않습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${boardList}" var="board">
										<tr>
											<td>
												<a href="/board/detail.do?bo_no=${board.BO_NO}">
													${board.BO_NO}
												</a>
											</td>
											<td>${board.BO_TITLE}</td>
											<td>
												${board.BO_WRITER}											
											</td>
											<td>
												${board.BO_HIT}											
											</td>
										</tr>
									</c:forEach>
								</c:otherwise>	
							</c:choose>
						</tbody>	
					</table>
				</div>
			</div>
			<a href="/board/form.do" class="btn btn-primary">등록</a>
		</div>	
	</div>			

</body>
</html>