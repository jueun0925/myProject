<%@page import="java.util.List"%>
<%@page import="com.hk.motong.dtos.MoimDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
      <meta name="description" content="" />
      <meta name="author" content="" />
      <title>Motong</title>
      <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
      <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
      <!-- Favicon-->
      <link rel="icon" type="image/x-icon" href="/resources/assets/favicon.ico" />
      <!-- Core theme CSS (includes Bootstrap)-->
      <link href="/resources/css/styles.css" rel="stylesheet" />
   <style>
   .right-btn {
     float: right;
   }
   h2 { text-align: center; margin-top: 10px;}
   
 	a{text-decoration: none;}
	
	.pagination{
		display: flex;
		padding-left: 550px;
		font-family: sans-serif;
		font-weight: bold;		
	}
	
	.active {
		background-color:blue;
		color:white;
		font-weight: bold; 
	}
	
   </style>

<script type="text/javascript">
	$(function(){
		$(".listContents").click(function(){
			$(this).find("input").focus();
		});
	});
	
	function enter(pin,email,moim_seq,btnEle){
		console.log(email);
		if(email == null || email==""){
			alert("회원이 아닙니다. 로그인하세요");
		}else{
			var pinNum = btnEle.parentNode.previousElementSibling.childNodes[0].value;
			if(pinNum == pin){
				swal("입장~").then(function(){
			    	location.href = "/moim/getMoim?moim_seq="+moim_seq;
				});
			}else{
				alert("PIN번호가 일치하지 않습니다.");
				pinNum.focus;
			}				
		}
	}

	function addMoimForm(email){
		if(email == null || email==""){
			alert("회원이 아닙니다. 로그인하세요");
		}else{
			location.href="/moim/newmoim";
		}
	}
	
 </script>
</head>

<body>
	 <nav class="navbar navbar-expand-lg navbar-light" style="background-color:#e3f2fd;">
	     <div class="container">
	     	<a href="/main"><img src="/resources/img/motong_logo.png" style="width:100px; height:50px;" /></a>
	         <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
	         
	         <c:choose>
				<c:when test="${sessionScope.ldto.email != null}">
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
	                	<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
		                    <li class="nav-item"><a class="nav-link" aria-current="page" href="/main">Home</a></li>
		                    <li><img src="/resources/img/user.png" style="width:30px; height:30px; margin-top: 5px"/></li>
		                    <li class="nav-item"><a class="nav-link" aria-current="page" href="#!">${sessionScope.ldto.name}님</a></li>
		                    <li class="nav-item"><a class="nav-link" href="/user/logout">로그아웃</a></li>
		                    <li class="nav-item"><a class="nav-link" href="/user/myPage?email=${sessionScope.ldto.email}"  >마이 페이지</a></li>
		                    <li class="nav-item"><a style="font-weight: 800; text-shadow:2px 2px 2px lightgray;" class="nav-link" href="/moim/moimlist?pnum=1">모임리스트</a></li>
                    		<li class="nav-item"><a class="nav-link" href="/bank/my_moim?pnum=1">나의 모임</a></li>
	                    </ul>
	         	   </div>
				</c:when>
				<c:otherwise>
		            <div class="collapse navbar-collapse" id="navbarSupportedContent">
		            	<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
		                	<li class="nav-item"><a class="nav-link" aria-current="page" href="/">Home</a></li>
		                	<li class="nav-item"><a class="nav-link" href="/moim/moimlist?pnum=1">모임리스트</a></li>
		                	<li class="nav-item"><a class="nav-link" href="/user/signin_form">로그인</a></li>
		                	<li class="nav-item"><a class="nav-link" href="/user/signup">회원가입</a></li>
		               	</ul>
	                </div>
				</c:otherwise>
			</c:choose>
	     </div>
	 </nav>
	<div class="container my-5">
    <div class="row justify-content-center">
    <div class="col-lg-6" style="width:1200px; overflow:auto;">
    	<div style="background-color: #F7FCFF; width:100%; height:150px; display:flex;">
    	<div style="width:850px;">
			<h2 style=" padding:20px; font-weight: bold; text-align: left;">모임 목록</h2>
			<a style=" margin-left:20px; width:300px; color: gray; font-size: 15px;">간편하게 모임을 개설하고 가입해보세요 !</a>
    	</div>
			<img src="/resources/img/moim_icon.png" style="height:180px; ">
    	</div>
		<hr/>
	 	<table class="table" style="text-align: center;">	 	
            <col width="150px;" />
            <col width="150px;" />
            <col width="600px;" />
            <col width="120px;" />
            <col width="200px;" />
	 		<tr>
	 			<th>No</th>
	 			<th>모임장</th>
	 			<th>모임이름</th>	 			
	 			<th>PIN번호</th>
	 			<th></th>
	 		</tr>
	 		<c:set var="lists" value="${list}" />
			<c:choose>
				<c:when test="${empty lists}">
					<tr>
						<td colspan="5">--모임이 없습니다.--</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${lists}" var="dto">
					    <tr class="listContents">					        
					        <td>${dto.moim_seq}</td>
					        <td>${dto.name}</td>
					        <td>${dto.mname}</td>   
					        <td><input type="password" name="pin" class="pinNum form-control"/></td>
					        <td><button class="btn btn-outline-secondary" onclick="enter('${dto.pin}','${sessionScope.ldto.email}','${dto.moim_seq}',this)" >입장</button></td>
					    </tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			</table>
			<!-- 페이징 처리부분 시작 -->
				<nav style="text-align: center;">
				  <ul class="pagination">
				    <li class="page-item"><a class="page-link" href="/moim/moimlist?pnum=${pMap.prePageNum}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
				    <c:forEach var="i" begin="${pMap.startPage}" end="${pMap.endPage}">
				    	<li><a ${sessionScope.pnum==i?"class='active page-link'":"class='page-link'"} href="/moim/moimlist?pnum=${i}">${i}</a></li>
				    </c:forEach> 
				    <li class="page-item" ><a class="page-link" href="/moim/moimlist?pnum=${pMap.nextPageNum}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
				  </ul>
				</nav>
				<!-- 페이징 처리부분 종료 -->
		<div class="container">
         <div class="row">
         <form method="post" name="search" action="/moim/research">
            <table class="pull-right">
            <col width="300px;" />
            <col width="100px;" />
            <col width="600px;" />
               <tr>
                  <td><input type="text" class="form-control"
                     placeholder="검색어 입력" name="searchData" maxlength="100"></td>
                  <td><button  type="submit" name="researchBtn" class="btn btn-secondary"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
  					<path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
					</svg> 검색</button>
				  </td>
                  <td style="text-align: right;">
		               <button style="margin-left:400px;" class="btn btn-secondary" type="button" onclick="addMoimForm('${sessionScope.ldto.email}')">모임개설</button>                  
                  </td>  
               </tr>
            </table>

         </form>         
		      </div>
		   </div>
    </div>
  </div>
  </div>
  <br/><br/><br/><br/><br/><br/><br/>
<footer class="py-3" style="background-color:#e3f2fd;">
    <div class="container"><p class="m-0 text-center text-black" style="height: 40px;">Copyright &copy; motong 2023</p></div>
</footer>
<!-- Bootstrap core JS-->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>