<%@page import="java.util.List"%>
<%@page import="com.hk.motong.dtos.MoimDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8" />
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
      <meta name="description" content="" />
      <meta name="author" content="" />
      <title>Motong</title>
      <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
      <!-- Favicon-->
      <link rel="icon" type="image/x-icon" href="/resources/assets/favicon.ico" />
      <!-- Core theme CSS (includes Bootstrap)-->
      <link href="/resources/css/styles.css" rel="stylesheet" />
      <style>
      
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
                    <li class="nav-item"><a class="nav-link" href="/user/myPage?email=${sessionScope.ldto.email}"  >마이 페이지</a></li>
                    <li class="nav-item"><a style="font-weight: 800; text-shadow:2px 2px 2px lightgray;" class="nav-link" href="/moim/moimlist">모임리스트</a></li>
                    <li class="nav-item"><a class="nav-link" href="/bank/my_moim?pnum=1">나의 모임</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <section class="py-5">
    <br/><br/>
    <div class="container my-5">
    <div class="row justify-content-center">
    <div class="col-lg-6" style="width:1200px; overflow:auto; padding:30px; border:1px solid lightgray; border-radius: 20px;">
    <form action="/moim/addUserMoim" method="post">
    	<input type="hidden" name="user_seq" value="${sessionScope.ldto.user_seq}">
    	<input type="hidden" name="moim_seq" value="${dto.moim_seq}">
        <div id="getMoim" style="width:500px; float:left;">
        <h2 style="font-weight: bold;">모임 상세</h2>
        <hr/>
              <table class="table">
              	<tr>
                	<th>모임 번호</th>
                	<td>${dto.moim_seq}</td>
                </tr>
              	<tr>
                	<th>모임 이름</th>
                	<td>${dto.mname}</td>
                </tr>
               </table>
                <c:set var="flag" value="f" />
                <c:forEach items="${seqList}" var="item" varStatus="status">
                	<c:if test="${sessionScope.ldto.user_seq eq seqList[status.index]}">
	                	<c:set var="flag" value="t" />
                	</c:if>
				</c:forEach>
				<c:choose>
                		<c:when test="${flag eq 't' }">
                			<span style="color:blue;">이미 가입되어 있습니다.</span>
                		</c:when>
                		<c:otherwise>
				            <button class="btn btn-outline-primary" data-dismiss="alert" type="submit">모임 가입</button>
                		</c:otherwise>
                </c:choose>	
        <br/><br/>
        <h2 style="font-weight: bold;">모임장 정보</h2>
        <hr/>
              <table class="table">
                <tr>
                	<th>모임장</th>
                	<td>${dto.name}</td>
                </tr>
                <tr>
                	<th>모임장 이메일</th>
                	<td>${dto.email}</td>
                </tr>
                <tr>
                	<th>모임장 핸드폰번호</th>
                	<td>${dto.phone}</td>
                </tr>
               </table>
        </div>
        <div id="moimUser" style="width:500px;  float:right;">
        <br/><br/><br/><br/>
        <h2 style="font-weight: bold;">모임원 목록</h2>
      
        <table class="table" style="text-align: center;">
        	<col width="100px;"/>
        	<col width="200px;"/>
        	<tr>
        		<th>이름</th>
        		<th>핸드폰</th>
        	</tr>        
	        <c:set var="lists" value="${list}"/>
			<c:choose>
				<c:when test="${empty lists}">
					<tr>
						<td colspan="2">--모임원이 없습니다.--</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${lists}" var="dtos">
					    <tr class="listContents">					        
					        <td>${dtos.name}</td>
					        <td>${dtos.phone}</td>
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
        </div>
    </form>
    </div>
   </div>
   </div>
   </section>
   <footer class="py-3" style="background-color:#e3f2fd;">
       <div class="container"><p class="m-0 text-center text-black" style="height: 40px;">Copyright &copy; motong 2023</p></div>
    </footer>
		<!-- Bootstrap core JS-->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>