<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>

<script>
    var url = "<c:out value='${url}'/>";
    $(function(){
	    swal({
	    	title : "모임 가입!",
	    	icon  : "success",
	    	closeOnClickOutside : false
	    	}).then(function(){
	    	location.href = url;
	    });
    });
</script>
</head>
<body>

</body>
</html>