<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    <style type="text/css">
        .main{
            margin-right: 20%;
            margin-left: 40%;
            margin-top: 100px;
            width: 300px;
        }
        body{
            background-image: url('${pageContext.request.contextPath}/image/library.jpg');
            background-repeat: no-repeat;
            background-size: 100%;
        }
    </style>
</head>
<body>
<div class="main">
恭喜你注册成功
<span id="time" style="color:red;">4</span>秒后将跳回登陆界面<br>
你也可直接点击此<a href="login.jsp">链接</a>回到登陆界面
</div>

</body>
<script type="text/javascript">
	function delayURL(url) {
		var delay = document.getElementById("time").innerHTML;
		if(delay > 0) {
			delay--;
			document.getElementById("time").innerHTML = delay;
		} else {
			window.location.href = url;
		}
		setTimeout("delayURL('" + url + "')", 1000);
	}

	delayURL("${pageContext.request.contextPath}/login.jsp");
</script>
</html>