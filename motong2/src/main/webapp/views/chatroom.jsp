<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
      <meta name="description" content="" />
      <meta name="author" content="" />
      <title>Motong</title>
      <!-- Favicon-->
      <link rel="icon" type="image/x-icon" href="resources/assets/favicon.ico" />
      <!-- Core theme CSS (includes Bootstrap)-->
      <link href="resources/css/styles.css" rel="stylesheet" />
      <style>
        h2 { text-align: center; margin-top: 10px;}
        
        .my-div {
        text-align : center;
      }

      </style>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
   var ws;
   window.addEventListener("load", (event) => {
      if ($("#userId").val() == "unknown") {
         alert("권한이 없습니다.");
         location.href="/loginform";
      }
      ws = new WebSocket("ws://" + location.host + "/chatting/" + $("#roomNo").val());
      wsEvt();
      
//       $("#chating").append("<p>" + $("#userid").val() + "님 환영합니다. </p>");   
   });
   
   function wsEvt() {
      
   //채팅이 시작되면 실행되는 함수
   ws.onopen = function(data){
       var option ={
            type : "wellcom",
            userId: $("#userId").val(),
            roomNo : $("#roomNo").val(),
            msg : $("#userId").val() + "님이 입장하셨습니다."
           }
          ws.send(JSON.stringify(option));
      }
         

   
   //   ws.onclose = function(data){
         
     //    var user = $("#userId").val();
     //   var str = $("#userId").val() + "님이 퇴장하셨습니다.";
          
      //   $("#chatting").append(str);
         
     
    //  }
      
      
     //서버에서 메시지를 받으면 실행하는 함수
      ws.onmessage = function(data) {
         //메시지를 받으면 동작
         var msg = data.data;
         console.log(msg);
         var json = JSON.parse(msg);
         
         if (json.type == "wellcom") {
            $("#chatting").append("<span id='ent_chat'> " + json.msg + "</span>" +"<br/><p></p>");   
         } else if (json.type == "bye") {
            $("#chatting").append("<span id='ent_chat'> " + json.msg + "</span>" +"<br/><p></p>");   
         }else if (json.userId == "${sessionScope.ldto.name}" ) {
            $("#chatting").append("<p></p><div id='id_content'><span id='content'>" + json.userId + ": " + json.msg + "</span></div><p></p>");   
         }else {
               $("#chatting").append("<p></p><div id='id_content2'><a id='content2'>" + json.userId + ": " + json.msg + "</a></div><p></p>");   
            } 
         var chatting = $('#chatting');
          chatting.scrollTop(chatting.prop('scrollHeight'));
      }
      

    //웹소켓 연결 종료되면 실행
      ws.onclose=function(){
         
         ws=null;//웹소켓 연결 끊고 null로 초기화
//         alert("채팅종료");
      }
      
      //키보드를 누르면 함수 실행 --> 누른 키의 code가 13이면 enter 키임(enter키를 누르면 send()함수 실행)
      document.addEventListener("keypress", function(e){
         if(e.keyCode == 13){ //enter press
            send();
         }
      });
   }
   
 
   
   //메시지를 서버로 전송하는 함수
   function send() {
      //전송할 내용을 json으로 정의
      var option ={
         type : "msg",
         userId: $("#userId").val(),
          roomNo : $("#roomNo").val(),
          msg : $("#chat").val()
      }
      //서버로 전송한다.(json객체를 문자열로 변환하여 보낸다)
      //WebChatHandler.java에 
      ws.send(JSON.stringify(option));
      var chat = $('#chat'); //chat: 입력박스 엘리먼트 탐색
      chat.val("");//메시지 보냈으니 입력박스 초기화
//       alert(chatting.prop('scrollHeight'));
      
      
   }
  

   
</script>
<style type="text/css">
   #container{
      width:1200px;
      margin: 0 auto;
   }
   #chatting{
      border:1px solid gray;
      width:1175px;
      height:500px;
      padding:10px ;
   }
   #chat{
      width:295px;
   }
   #id_content > p{
      width:50px;
      background-color: write;
      border-radius:5px;
      padding:5px 5px;
      text-align: center;
      margin-left: 10px;
   }
  #id_content > span{
      width:1120px;
      height:60px;
      display:inline-block;
      padding:5px 5px;
      border-radius:7px 7px 7px 7px;
      border:none;
      margin-left: 10px;
      background-color: lightyellow;
   }
    #id_content2 > a{
      width:1120px;
      height:60px;
      display:inline-block;
      padding:5px 5px;
      border-radius:7px 7px 7px 7px;
      border:none;
      margin-left: 12px;
      background-color: lightgray;
   }
</style>
</head>
<body>
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
                    <li class="nav-item"><a class="nav-link" href="/user/myPage?email=${sessionScope.ldto.email}" >마이 페이지</a></li>
                    <li class="nav-item"><a class="nav-link" href="/moim/moimlist?pnum=1">모임리스트</a></li>
                    <li class="nav-item"><a class="nav-link" href="/bank/my_moim?pnum=1">나의 모임</a></li>
                </ul>
            </div>
        </div>
    </nav>
<div id="container">
<br/>
	<div style="background-color: #F7FCFF; width:100%; height:150px; display:flex;">
		<div style="width:850px;">
		<h2 style="padding:20px; font-weight: bold; text-align: left;">대화방</h2>
		<a style="margin-left:20px; width:300px; color: gray; font-size: 15px;">접속한 모임원과 채팅을 시작하세요 !</a>
		</div>
		<img src="/resources/img/talk.png" style="height:150px; ">
    </div>
	<br/>
      <!-- Main Content-->
      <input type="hidden" id="roomNo" value="${param.roomNo}"><br/>
     <input type="hidden" id="userId" value="${sessionScope.ldto.name}">
   <main>
         <div class="container-fluid">
          <div>
             <div id="card" >
                <div id="chatting" style="overflow-y: scroll; border-radius:10px;">
                
                </div>
             </div>
             <br/>
             <div class="input-group mb-3">
               <input id="chat" type="text" placeholder="메세지를 입력하세요" name="chatt"
               class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2" required />
               <div class="input-group-append">
                <button style="margin-left:10px; font-weight: bold;" class="btn btn-outline-secondary" id="send-btn"  onclick="send()" type="button"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat-left-dots" viewBox="0 0 16 16">
  					<path d="M14 1a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H4.414A2 2 0 0 0 3 11.586l-2 2V2a1 1 0 0 1 1-1zM2 0a2 2 0 0 0-2 2v12.793a.5.5 0 0 0 .854.353l2.853-2.853A1 1 0 0 1 4.414 12H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2z"/>
  					<path d="M5 6a1 1 0 1 1-2 0 1 1 0 0 1 2 0m4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0m4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0"/>
				</svg> 전송</button>
               </div>   
            </div>
         </div>
      </div>
    </main>
</div>
 <footer class="py-3"  style="background-color:#e3f2fd;" >
            <div class="container"><p class="m-0 text-center text-gray" style=" height: 40px;">Copyright &copy; motong 2023</p></div>
        </footer>
<!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="resources/js/scripts.js"></script>
</body>
</html>