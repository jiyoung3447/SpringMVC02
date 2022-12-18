<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>

	<div class="container">
		<h2>Spring MVC01</h2>
		<div class="panel panel-default">
			<div class="panel-heading">BOARD</div>
			<div class="panel-body">

				<form action="boardInsert.do" method="POST">
					<table class="table">
						<tr>
							<th>제목</th>
							<td><input name="title" class="form-control" /></td>
						</tr>
						<th>작성자</th>
						<td><input type="text" name="writer" class="form-control" /></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea rows="7" name="content" class="form-control"></textarea></td>
						</tr>
						</tr>

						<tr>
							<td colspan="2" align="right">
								<button type="submit" class="btn btn-info btn-sm">등록</button>
								<button type="reset" class="btn btn-sm">취소</button>
							</td>
					</table>
				</form>
			</div>
			<div class="panel-footer">인프런_스프1단 푸터</div>
		</div>
	</div>

</body>
</html>