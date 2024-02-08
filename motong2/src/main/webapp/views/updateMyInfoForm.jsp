<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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
	function isReal(){
		if(confirm("수정하시겠습니까?")){
			return true;
		}
		return false;
		
	}
	
	function checkPW(form){
		if(form.password.value==form.passwordChk.value){
			if(confirm("수정하시겠습니까?")){
				return true;
			}
			return false;	
		}else{
			document.getElementById("diff").textContent="비밀번호가 일치하지 않습니다.";
			form.password.value=""; //비밀번호 초기화
			form.passwordChk.value="";
			form.password.focus();
			return false;
		}
	}
	</script>

</head>
<body>
<!-- Responsive navbar-->
    <nav class="navbar navbar-expand-lg navbar-light" style="background-color:#e3f2fd;">
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
        <div class="container my-5">
            <div class="row justify-content-center">
                <div class="col-lg-6">
                <div style="padding:20px; border-radius: 20px; background-color: #FCFCFC; box-shadow: 1px 1px 20px gray; ">
					<h2 style="font-weight: bold;">나의 정보 수정</h2>
					<hr/>
					<div id="myinfo">
					<form action="/user/updateMyInfo" method="post" onsubmit="return checkPW(this)">
					<input type="hidden" name="email" class="form-control" value="${dto.email}" />
						<table class="table">
							<tr>
								<th>이름</th>
								<td><input type="text" name="name" class="form-control" value="${dto.name}" required/></td>
							</tr>
							<tr>
								<th>아이디</th>
								<td>${dto.email}</td>
							</tr>
							<tr>
								<th>비밀번호</th>
								<td><input type="password" name="password" class="form-control" value="${dto.password}" minlength="8" maxlength="16" placeholder="8자리 이상 16자리 이하" required/></td>
							</tr>
							<tr>
                    			<th>비밀번호 확인</th>
                    			<td>
                    				<input type="password" name="passwordChk" class="form-control hi" required />
                    				<span id="diff" style="color:red; font-size:10pt;"></span>
                    			</td>
                    		</tr>
							<tr>
								<th>전화번호</th>
								<td><input type="text" name="phone" class="form-control" value="${dto.phone}" required/></td>
							</tr>
							<tr>
								<td colspan="2">
									<button type="submit" class="btn btn-outline-primary">수정하기</button>
								</td>
							</tr>
						</table>
					</form>
					</div>
					</div>
                </div>
            </div>
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