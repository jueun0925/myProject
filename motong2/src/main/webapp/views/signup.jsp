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
    <title>회원가입</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="/resources/assets/favicon.ico" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/resources/css/styles.css" rel="stylesheet" />

    <style type="text/css">
    	.hi{
    		width:400px;
    	}
		
		th{
			text-align:left;
		}
    </style>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script type="text/javascript">
		
	onload=function(){
		// 아이디 중복체크 후, 다른 정보를 입력할 수 있게 처리
		var inputs=document.querySelectorAll("input[name]");
		var idChk=document.getElementById("idChk");
		for(var i=1; i<inputs.length; i++){
			inputs[i].onfocus=function(){
				if(idChk.textContent=="n"){	// 중복체크 여부 확인
					alert("아이디 중복체크를 먼저 확인하세요");
					inputs[0].focus();	// 아이디 입력박스 이동
				}
			}
		}
	}
	
	function checkPW(form){
		if(form.password.value==form.passwordChk.value){
			return true;	
		}else{
			document.getElementById("diff").textContent="비밀번호가 일치하지 않습니다.";
			form.password.value=""; //비밀번호 초기화
			form.passwordChk.value="";
			form.password.focus();
			return false;
		}
	}
	
	// 아이디 중복 체크
	function idChk(){
		var email=document.getElementsByName("email")[0].value;
		if(email==""){
			alert("이메일을 입력하세요.");
		}else{
			$.ajax({
				url:"/user/idChk",	// 요청 url
				method:"post",		// 전송방식
				data:{"email":email},		// 전송할 데이터
				dataType:"text",	// 전달받을 데이터 타입 (xml,json,html,text ...)
				async: false,		// $.ajax()메서드를 실행하는 방식
				success: function(data){// 데이터 받기 성공하면 함수 실행
					console.log(data);
					if(data =="null" || data=="" || data == null){
						$("#enabledEmail").css("color","blue").text(" 사용 가능한 이메일입니다.");
						$("#idChk").text("y");
					}else{
						$("#enabledEmail").css("color","red").text(" 사용 불가능한 이메일입니다.");
						$("#idChk").text("n");
					}
				}, error:function(){ // 데이터받기 실패하면 함수 실행
					alert("통신 실패");
				}	
			}); 

		}
	}
	</script>

</head>
<body>
<!-- Responsive navbar-->
        <nav class="navbar navbar-expand-lg navbar-light" style="width:100%; position:fixed; z-index:100; top:0px;  background-color:#e3f2fd;">
            <div class="container">
            	<a href="/"><img src="/resources/img/motong_logo.png" style="width:100px; height:50px;" /></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="/">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="/moim/moimlist?pnum=1">모임리스트</a></li>
                        <li class="nav-item"><a class="nav-link" href="/user/signin_form">로그인</a></li>
                        <li class="nav-item"><a style="font-weight: 800; text-shadow:2px 2px 2px lightgray;" class="nav-link" href="/user/signup">회원가입</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    <!-- Content section-->
    <section class="py-5">
        <div class="container my-5">
            <div class="row justify-content-center"> 
                <div class="col-lg-6">
                <br/><br/><br/>
                    <div style="padding:20px; border-radius: 20px; background-color: #FCFCFC; box-shadow: 5px 5px 15px gray; ">
                	<h2 style="font-weight: bold;">회원가입</h2>
                	<hr/>

                    <form action="/user/addUser" method="post" onsubmit="return checkPW(this)" style="text-align:center;">
                    	<table class="table" >
                    		<tr>

                    			<th>이메일</th>
                    			<td><input style="margin:auto;" type="text"name="email" class="form-control hi" required/>
                    				<a style="margin-left:0px;" href="#" onclick="idChk()">중복체크</a>
                    				<span id="idChk" style="display:none;">n</span>
                    				<span id="enabledEmail"></span>
                    			</td>
                    		</tr>
                    		<tr>
                    			<th>비밀번호</th>
                    			<td><input style="margin:auto;" type="password" name="password" class="form-control hi" minlength="8" maxlength="16" placeholder="8자리 이상 16자리 이하" required /></td>
                    		</tr>
                    		<tr>
                    			<th>비밀번호 확인</th>
                    			<td>
                    				<input style="margin:auto;" type="password" name="passwordChk" class="form-control hi" required />
                    				<span id="diff" style="color:red; font-size:10pt;"></span>
                    			</td>
                    		</tr>
                    		<tr>
                    			<th>이름</th>
                    			<td><input style="margin:auto;" type="text" name="name" class="form-control hi" required/></td>
                    		</tr>
                    		<tr>
                    			<th>핸드폰번호</th>
                    			<td><input style="margin:auto;" type="text" name="phone" class="form-control hi" required/></td>
                    		</tr>
                    	</table>
                    	<button style="width:200px; font-weight: bold;" type="submit" class="btn btn-outline-primary">가입하기</button>
                    </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Footer-->
    
     <footer style="width:100%; bottom:0; background-color:#e3f2fd;" class="py-3"  >
            <div class="container"><p class="m-0 text-center text-gray" style=" height: 40px;">Copyright &copy; motong 2023</p></div>
     </footer>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="resources/js/scripts.js"></script>
</body>
</html>






