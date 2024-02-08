<%@page import="com.hk.motong.dtos.MoimDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Motong</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="resources/assets/favicon.ico" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/resources/css/styles.css" rel="stylesheet" />
   <style type="text/css">
      .box{border-bottom: 1px solid gray; margin-bottom: 10px;}
      .box > .sub_menu{text-align: right;}
     
      .active {
		background-color:blue;
		color:white;
		font-weight: bold; 
		}
	
   </style>
   <script type="text/javascript">

   </script>

</head>
<body>
<!-- Responsive navbar-->
   <nav class="navbar navbar-expand-lg navbar-light" style="width:100%; position:fixed; z-index:100; top:0px;  background-color:#e3f2fd;">
        <div class="container">
           <a href="/main"><img src="/resources/img/motong_logo.png" style="width:100px; height:50px;" /></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-item"><a class="nav-link" aria-current="page" href="/main">Home</a></li>
                    <li><img src="/resources/img/user.png" style="width:30px; height:30px; margin-top: 5px"/></li>
                    <li class="nav-item"><a class="nav-link" aria-current="page" href="#!">${sessionScope.ldto.name}님</a></li>
                    <li class="nav-item"><a class="nav-link" href="/user/logout">로그아웃</a></li>
                    <li class="nav-item"><a class="nav-link" href="/user/myPage?email=${sessionScope.ldto.email}" >마이 페이지</a></li>
                    <li class="nav-item"><a class="nav-link" href="/moim/moimlist?pnum=1">모임리스트</a></li>
                    <li class="nav-item"><a style="font-weight: 800; text-shadow:2px 2px 2px lightgray;" class="nav-link" href="/bank/my_moim?pnum=1">나의 모임</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- Content section-->
    <section class="py-5">
        <div class="container my-5">
            <div class="row justify-content-center">
            <div class="col-lg-6" style="width:1200px; overflow:auto;">
            	<div style="background-color: #F7FCFF; width:100%; height:150px; display:flex;">
		    	<div style="width:850px;">
					<h2 style="padding:20px; font-weight: bold; text-align: left;">가입된 모임</h2>
					<a style="margin-left:20px; width:300px; color: gray; font-size: 15px;">내가 가입한 모임을 확인하세요 !</a>
		    	</div>
				<img src="/resources/img/gather_icon.png" style="height:180px; ">
    		</div>
                <hr/>
                <!--  <form> -->
                    <table class="table table-hover" style="text-align:center;">
                      <col width="150px"/>
                      <col width="200px"/>
                      <col width="400px"/>
                      <col width="200px"/>
                      <col width="200px"/>
                
                      <thead>
                      <tr>
                         <th>No.</th><th>모임장</th><th>모임이름</th><th>  회비 관리</th><th>커뮤니티</th>
                      </tr>
                      </thead>
                      <tbody>
                     <c:set var="list" value="${list}"/>
                     <c:choose>
                     	<c:when test="${empty list}">
						<tr>
							<td colspan="5">--가입된 모임이 없습니다.--</td>
						</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${list}" var="dto" varStatus="status">
								<tr>
									<td style="width:100px;">${dto.moim_seq}</td>
									<td style="width:100px;">${dto.name}</td>
									<td>${dto.mname}</td>													
									<td><button type="button" style="width:100px;" class="btn btn-outline-primary amt-btn" onclick="location.href='/bank/bank_moim?account_seq=${dto.account_seq}'">회비 관리</button></td>
									<form action="/chatform" method="post">
									<td><input type="submit" class="btn btn-outline-primary amt-btn" value="커뮤니티"/></td>
               						<td><input style="width:50px;" type="hidden" name="roomNo" value="${dto.moim_seq}"></td>
               						</form>
								</tr>
								
							</c:forEach>
						</c:otherwise>
                     </c:choose>
                     </tbody>
                   </table>
               <!-- </form> --> 
               <!-- 페이징 처리부분 시작 -->
				<nav style="text-align: center; margin-left: 50%">
				  <ul class="pagination">
				    <li class="page-item"><a class="page-link" href="/bank/my_moim?pnum=${pMap.prePageNum}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
				    <c:forEach var="i" begin="${pMap.startPage}" end="${pMap.endPage}">
				    	<li><a ${sessionScope.pnum==i?"class='active page-link'":"class='page-link'"} href="/bank/my_moim?pnum=${i}">${i}<span class="sr-only"></span></a></li>
				    </c:forEach> 
				    <li class="page-item" ><a class="page-link" href="/bank/my_moim?pnum=${pMap.nextPageNum}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
				  </ul>
				</nav>
				<!-- 페이징 처리부분 종료 -->
               </div>
            </div>
            <br/><br/><br/><br/><br/><br/><br/>
        </div>
    </section>
    <!-- Footer-->
    <footer class="py-3"  style="background-color:#e3f2fd;" >
            <div class="container"><p class="m-0 text-center text-gray" style=" height: 40px;">Copyright &copy; motong 2023</p></div>
        </footer>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
<!--     <script src="resources/js/scripts.js"></script> -->
</body>
</html>