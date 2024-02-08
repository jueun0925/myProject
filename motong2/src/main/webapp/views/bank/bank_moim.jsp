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
    <link rel="icon" type="image/x-icon" href="/resources/assets/favicon.ico" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/resources/css/styles.css" rel="stylesheet" />
   <style type="text/css">
      .box{border-bottom: 1px solid gray; margin-bottom: 10px;}
      .box > .sub_menu{text-align: right;}
   </style>
   <script type="text/javascript">


	//거래내역조회
	function transactionList(fintech_use_num,btnEle){
		$.ajax({
			url:"/banking/transactionList",
			method:"get",
			data:{"fintech_use_num":fintech_use_num},
			dataType:"json",
			success:function(data){ //data: 응답결과을 받을 변수
				var list="<div> <table class='table table-striped' style=''>"
						 +"<tr style='padding:10px; background-color: #5A78AF;'>"
						 +	"<th style='color:#F9FFFF; border-radius:10px 0 0 10px;'>거래일자</th>"
						 +	"<th style='color:#F9FFFF;'>은행</th>"
						 +	"<th style='color:#F9FFFF;'>거래유형</th>"
						 +	"<th style='color:#F9FFFF;'>메모</th>"
						 +	"<th style='color:#F9FFFF; border-radius:0 10px 10px 0;'>금액</th>"
						 +"</tr>";
				// data.res_list  -->  배열
				for (var i = 0; i < data.res_list.length; i++) {
					var res=data.res_list[i];// json객체를 가져온다 {key:value,...}
					list+="<tr>"
						  +"<td>"+res.tran_date.substring(0,4)+"-"+res.tran_date.substring(4,6)+"-"+res.tran_date.substring(6,8)+"</td>"
						  +"<td>"+res.branch_name+"</td>"
						  +"<td style='color:blue;'>"+res.inout_type+"</td>"
						  +"<td>"+res.print_content+"</td>"
						  +"<td>"+res.tran_amt+"</td>"
						  +"</tr>"
				}
				list+="</table></div>";
				$(btnEle).next().next().next().next().next().html(list);	
			}
		});
	}
   
	//거래내역 조회 toggle
	$(function(){
		$("#tranList").click(function(){
			$(".tranList").toggle();		
		});
	});
			

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
                    <li class="nav-item"><a class="nav-link active" aria-current="page" href="/main">Home</a></li>
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
        <br/><br/>
        <div class="container my-5">
            <div class="row justify-content-center">
            <div class="col-lg-6" style="width:800px; overflow:auto; padding:30px; border:1px solid lightgray; border-radius: 20px; ">
                <h2 style="font-weight: bold;">회비 관리</h2>
                <hr/>
                <div id="getMoimAccount">
                	<table class="table">
                		<tr>
                			<th>모임 이름</th>
                			<td>${mname}</td>
                		</tr>
                		<tr>
                			<th>은행 명</th>
                			<td>${dto.bank_name}</td>
                		</tr>
                		<tr>
                			<td colspan="2">
                			<button type="button" id="tranList" class="btn btn-outline-primary" onclick="transactionList('${dto.fintech_use_num}',this)">거래 내역 조회</button>
                			<button type="button" class="btn btn-outline-primary" onclick="window.open('/banking/deposit_popup?fintech_use_num=${dto.fintech_use_num}', 'withdrawpopup','width=700, height=400, top=150, left=200')">입금</button>
                			<c:choose>
                			<c:when test="${sessionScope.ldto.user_seq eq dto.user_seq}">
                				<button type="button" class="btn btn-outline-primary" onclick="window.open('/banking/withdraw_popup?fintech_use_num=${dto.fintech_use_num}', 'withdrawpopup','width=700, height=400, top=150, left=200')">출금</button>
                			</c:when>
                			</c:choose>
                			<br/><br/><div class="tranList"></div>
                		</tr>
                	</table>
                </div>
            </div>
            </div>
        </div>
         <br/><br/><br/><br/>
    </section>
    <!-- Footer-->
    <footer class="py-3"  style="width:100%; background-color:#e3f2fd;" >
            <div class="container"><p class="m-0 text-center text-gray" style=" height: 40px;">Copyright &copy; motong 2023</p></div>
        </footer>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
<!--     <script src="resources/js/scripts.js"></script> -->
</body>
</html>