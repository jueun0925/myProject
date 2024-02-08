<%@page import="java.util.List"%>
<%@page import="com.hk.motong.dtos.MoimDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
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
      <style>
	 </style>
	<script type="text/javascript">
    function addMoim(){
    	var select= document.getElementsByName("account_seq")[0].value;
    	if(select == "no" || select == "noAccount"){
    		alert("사용할 계좌를 선택하세요");
    		return false;    		
    	}else if(select == "usingAccount"){
    		alert("다른 모임에 사용중인 계좌");
    		return false;
    	}else{
    		return true;	
    	}    	
    }
    
     
	</script>
</head>
<body>
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
                    <li class="nav-item"><a class="nav-link" href="/user/myPage?email=${sessionScope.ldto.email}"  >마이 페이지</a></li>
                    <li class="nav-item"><a style="font-weight: 800; text-shadow:2px 2px 2px lightgray;" class="nav-link" href="/moim/moimlist">모임리스트</a></li>
                    <li class="nav-item"><a class="nav-link" href="/bank/my_moim?pnum=1">나의 모임</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <section class="py-5">
    <br/><br/><br/>
    <div class="container my-5">
    <div class="row justify-content-center">
    <div class="col-lg-6" style="width:800px; overflow:auto; padding:30px; border:1px solid lightgray; border-radius: 20px;">
    <h2 style="font-weight: bold;">모임 개설하기</h2>
    <br/>
	    <form action="/moim/addMoim" method="post" onsubmit="return addMoim()" style="text-align:center;">	  
	      <input type="hidden" name="leader" value="${sessionScope.ldto.user_seq}">
	      <table class="table">
	      		<col style="width:100px"/>
	      		<col style="width:400px"/>
	   			<tr>
	   				<th >이름</th>
	   				<td> <input type="text" name="mname"  placeholder="모임이름을 입력하세요"  class="form-control" /></td>
	   			</tr>
	   			<tr>
	   				<th>계좌</th>
	   				<td>
	   					<select name="account_seq" class="acseq form-control" style="text-align:center;">
						      <option value="no" selected="selected">-----------------------------------</option> 
						      <c:set var="lists" value="${acList}" />
							      <c:choose>
							          <c:when test="${empty lists}">
							              <option value="noAccount">등록된 계좌번호가 없음</option>
							          </c:when>
							          <c:otherwise>
							              <c:forEach items="${lists}" var="dto">
								              <c:choose>
										          <c:when test="${dto.delflag == 'Y'}">
										         		 <option value="usingAccount">(사용중)${dto.bank_name}[${dto.account_num_masked}]</option>
										          </c:when>
											      <c:otherwise>
											      		<option value="${dto.account_seq}">${dto.bank_name}[${dto.account_num_masked}]</option> 											      
											      </c:otherwise>
										      </c:choose>
							              </c:forEach>
							          </c:otherwise>
							      </c:choose>
						  </select>
	   				  </td>
	   			</tr>
	   			<tr>
	   				<th>PIN번호</th>
	   				<td><input type="text" name="moimPin"  placeholder="6자리" minlength="6" maxlength="6" class="form-control" ></td>
				
	   			</tr>
	   		</table>
	   		<button  class="btn btn-secondary"  type="submit">모임개설!</button>
	     </form>	     
    </div>
   </div>
   </div>
   </section>
   <br/><br/><br/>
   <footer class="py-3" style="background-color:#e3f2fd;">
       <div class="container"><p class="m-0 text-center text-black" style="height: 40px;">Copyright &copy; motong 2023</p></div>
    </footer>
		<!-- Bootstrap core JS-->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>