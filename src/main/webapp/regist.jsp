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
<span style="color:red;font-size:16px;">${registError}</span>
<div style="color:red;font-size:16px;">
<c:forEach items="${regist_errorlist}" var="error">
	<p style="color:red;">${error }</p>
</c:forEach>
</div>
<div class="main">
<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/user/regist.do" method="post">
  <div class="form-group">
    <label for="id" class="col-sm-3 control-label">账号</label>
    <div class="col-sm-9">
      <input type="text" class="form-control" id="id" placeholder="请输入账号" name="username">
    </div>
  </div>
   <div class="form-group">
    <label for="email" class="col-sm-3 control-label">邮箱</label>
    <div class="col-sm-9">
      <input type="text" class="form-control"  placeholder="请输入邮箱" name="email" id="email">
    </div>
  </div>
  <div class="form-group">
    <label for="password" class="col-sm-3 control-label">密码</label>
    <div class="col-sm-9">
      <input type="password" class="form-control" id="password" placeholder="请输入密码" name="password">
    </div>
  </div>
   <div class="form-group">
    <label for="rpassword" class="col-sm-3 control-label">确认密码</label>
    <div class="col-sm-9">
      <input type="password" class="form-control" id="rpassword" placeholder="请再次输入密码" name="rpassword">
    </div>
  </div>

 
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-3">
      <input type="submit" class="btn btn-info" value="注册">
    </div>
     <div class="col-sm-offset-3  col-sm-3">
      <input type="reset" class="btn btn-success" value="重置">
    </div>
  </div>


</form>
</div>
<script type="text/javascript">

    var email = document.getElementById("email");
    email.onblur = function(){
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open("POST", "${pageContext.request.contextPath}/user/isEmailExist.do", true);
        xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xmlHttp.send("email=" + email.value);
        xmlHttp.onreadystatechange = function() {
            if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                var text = xmlHttp.responseText;
                if(text=="0"){
                    alert("该邮箱已经被注册")
                }else{

                }
            }
        };
    };
</script>
</body>
</html>