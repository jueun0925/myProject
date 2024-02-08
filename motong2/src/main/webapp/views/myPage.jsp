<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
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
		$(function(){
			$(".deleteAccount").hover(function(){
				$(this).css("color","red");
				},function(){
					$(this).css("color","black");
				});
		});
	
		function isDel(email){
			if(confirm("정말 탈퇴하시겠습니까?")){
				location.href="/user/delUser?email="+email;
			}else{
				return false;
			}
		}
		
		//사용자 인증 되어잇으면 되어있다고 알림
		function already(){
			alert("사용자 인증이 완료된 회원입니다.");
		}
		
		//사용자 인증 요청하기(사용자 인증하면서 계좌등록까지 진행함)
		function authorization(){
			var url="https://testapi.openbanking.or.kr/oauth/2.0/authorize?"
				   +"response_type=code&" //고정값 code: 인증요청시 반환되는 값의 형식의미
				   +"client_id=4987e938-f84b-4e23-b0a2-3b15b00f4ffd&" //이용기관의 ID
				   +"redirect_uri=http://localhost:8070/user/authresult&"//응답URL
				   +"scope=login inquiry transfer&" //토큰의 권한
				   +"state=12345678123456781234567812345678&" //32자리 난수 설정
				   +"auth_type=0"; //0:최초 한번 인증, 2:인증생략
				   
			window.open(url,"인증하기","width=400px,height=600px");
			
// 			alert("사용자 인증 완료");
		}
		
		//계좌등록하기(센터인증 이용기관용: 사용자 인증후에 계좌 등록 가능)
		function addAccount(email, useraccesstoken){
				console.log(useraccesstoken);
			if(useraccesstoken == "" || useraccesstoken == null){
				alert("계좌를 등록하려면 사용자 인증을 해야합니다.");
			}else{
				var url="https://testapi.openbanking.or.kr/oauth/2.0/authorize?"
					   +"response_type=code&" //고정값 code: 인증요청시 반환되는 값의 형식의미
					   +"client_id=4987e938-f84b-4e23-b0a2-3b15b00f4ffd&" //이용기관의 ID
					   +"redirect_uri=http://localhost:8070/banking/addaccount&"//응답URL
					   +"scope=login inquiry transfer&" //토큰의 권한
					   +"state=12345678123456781234567812345678&" //32자리 난수 설정
					   +"auth_type=0"; //0:최초 한번 인증, 2:인증생략
					   
				window.open(url,"인증하기","width=400px,height=600px");
			}
		}
		
		//잔액확인 버튼 누르면 잔액 확인
		$(function(){
				$(".amt-btn").click(function(){
					$(this).parent().parent().next().find(".amt").toggle();
					$(this).parents("tr").eq(1).siblings().each(function(){
						$(this).find(".amt").hide();
					});
					$(this).parents("tr").eq(1).siblings().each(function(){
						$(this).find(".tranList").hide();
					});
				});
					
						
				$(".tran-btn").click(function(){
					$(this).parent().parent().next().find(".tranList").toggle();
					$(this).parents("tr").eq(1).siblings().each(function(){
						$(this).find(".amt").hide();
					});
					$(this).parents("tr").eq(1).siblings().each(function(){
						$(this).find(".tranList").hide();
					});
				});
				
				
				$(".delflag").each(function (index, item) {
		               if($(this).text() == "Y"){
		                    $(this).next().text("사용중인 계좌");
		               }
		        });   
			});
		
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
					$(btnEle).parent().parent().next().find(".tranList").html(list);	
				}
			});
		}
		
		function deleteAccount(account_seq,bank_name,account_num_masked,email){
			if(confirm("정말 삭제하시겠습니까?("+bank_name+":"+account_num_masked+")")){
				location.href="/banking/deleteAccount?account_seq="+account_seq+"&email="+email;
			}else{
				return false;
			}
		}
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
	                <li class="nav-item"><a style="font-weight: 800; text-shadow:2px 2px 2px lightgray;" class="nav-link" href="/user/myPage?email=${sessionScope.ldto.email}"  >마이 페이지</a></li>
                    <li class="nav-item"><a class="nav-link" href="/moim/moimlist?pnum=1">모임리스트</a></li>
                    <li class="nav-item"><a class="nav-link" href="/bank/my_moim?pnum=1">나의 모임</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- Content section-->
    <section class="py-5">
    <br/><br/>
          <div  class="container my-5">
       		 <div class="row justify-content-center">
                <div class="col-lg-6" style="width:1200px; overflow:auto; padding:30px; border:1px solid lightgray; border-radius: 20px; ">
                	<div id="myinfo" style="width:500px; float:left; ">
					<h2 style="font-weight: bold;">나의 정보</h2>
					<hr size="4px"/>	
						<table class="table">
							<tr>
								<th>이름</th>
								<td>${dto.name}</td>
							</tr>
							<tr> 
								<th>아이디</th>
								<td>${dto.email}</td>
							</tr>
							<tr>
								<th>비밀번호</th>
								<td>${dto.password}</td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td>${dto.phone}</td>
							</tr>
							<tr>
								<td colspan="2">
									<button type="button" class="btn btn-outline-primary" onclick="isDel('${sessionScope.ldto.email}')" >회원탈퇴</button>
									<button type="button" class="btn btn-outline-primary" onclick="location.href='/user/updateMyInfoForm?email=${sessionScope.ldto.email}'">정보수정</button>
								</td>
							</tr>
						</table>
					</div>
					<div id="myAccount" style="width:600px;  float:right;">
					<h2 style="font-weight: bold;">나의 계좌</h2>
					<hr size="4px"/>
						<button type="button" class="btn btn-outline-primary" onclick="${sessionScope.ldto.useraccesstoken == null ? 'authorization()':'already()'}">사용자인증</button>
						<button type="button" class="btn btn-outline-primary" onclick="addAccount('${sessionScope.ldto.email}','${sessionScope.ldto.useraccesstoken}')" >계좌 등록하기</button>
						<br/><br/>
						<table class="table">
							<c:choose> 
								<c:when test="${empty aList}"><!-- 안에가 비어있냐 -->
									<tr style="color:red; font-weight: bold;">
										<td><br/><br/>-- 등록된 계좌가 없습니다. (사용자 인증을 진행하세요) --</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${aList}" var="aTdto">
										<tr style="margin:10px; ">
											<td style="border-radius:20px; background-color: #DAE6FF; padding: 20px; margin:10px;">
												<table>
													<col width="300px;" />
													<col width="300px;" />
													<tr>
														<th>
															<p style="font-size:15pt">${aTdto.bank_name}
																<span class="delflag" style="display:none;">${aTdto.delflag}</span>
																<span style="color:red; font-size:8pt;"></span>
															</p>														
														</th>
														<th>
															<button style="text-decoration : underline; border:none; background-color:#DAE6FF; color:black; margin-left:200px; font-size:9pt;" class="deleteAccount" 
																	onclick="deleteAccount('${aTdto.account_seq}','${aTdto.bank_name}','${aTdto.account_num_masked}','${sessionScope.ldto.email}')">계좌 삭제</button>															
														<th/>												
													</tr>
													<tr>
														<th>
															<span>계좌번호 : ${aTdto.account_num_masked}</span>														
														</th>
														<th>
															<button class="btn btn-outline-primary amt-btn" style="margin-left:50px;">잔액조회</button>
															<button class="btn btn-outline-primary tran-btn" onclick="transactionList('${aTdto.fintech_use_num}',this)">거래내역조회</button>
															<br/>														
														<th/>												
													</tr>
													<tr>
														<td colspan = "2">
															<span class="amt" style="display:none; font-size: 18px; color: blue;">잔액: 500000원</span>
															<div class="tranList" > </div>	
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</table>
					</div>
                </div>
              </div>
        </div>
    </section>
  <br/><br/><br/><br/>
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