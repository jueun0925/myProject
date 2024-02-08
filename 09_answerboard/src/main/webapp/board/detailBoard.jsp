<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js">
	
</script>
<script type="text/javascript">
	$(function(){
// 		alert("하이");
		$("#replyButton").click(function(){
			$("#replyForm").toggle(); //답글 폼 보여주고 닫기
			document.getElementsByTagName("form")[0].reset(); //JS //열고 닫을때마다 초기화 하겟단거여요.
// 			$("#replyForm").reset();  //이거는 안되는 코드
		});
	});
</script>
<style type="text/css">
	/*답글 폼 처음에는 안보이게 처리*/
	#replyForm{
		display:none;
	}
</style>
</head>
<body>
<h1>답변형게시판</h1>
<div id="container">
	<h2>상세보기</h2>
	<table>
		<tr>
			<th>작성자</th>
			<td>${dto.id}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${dto.title}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" readonly="readonly">${dto.content}</textarea></td>
		</tr>
		<tr>
			<td colspan="2">
     				<button type="button" id="replyButton">답글</button>
     				<button type="button" onclick="location.href='updateBoardForm.board?seq=${dto.seq}'">수정</button>
     				<button type="button" onclick="location.href='mulDel.board?chk=${dto.seq}'">삭제</button>
     				<button type="button" onclick="location.href='boardList.board'">목록</button>
			</td>
		</tr>
	</table>
	<div id="replyForm">
		<h2>답글 작성하기</h2>
		<form action="replyBoard.board" method="post">
		<input type="hidden" name="seq" value="${dto.seq}"/>
		<table>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="id" pattern="^[a-zA-Z]+$" required="required"/></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" required="required"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="content" required="required"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
	     				<button type="submit">답글 등록</button>
	     				<button type="button" onclick="location.href='boardList.board?pnum=1'">목록</button>
				</td>
			</tr>
		</table>
		</form>		
	</div>
</div>
</body>
</html>