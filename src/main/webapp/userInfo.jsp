<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function checkNull(field){	
	var reg = /^\s*$/;
	var value =  document.getElementById(field).value;
	if(reg.test(value)){
		alert("信息不完整");
		return false;
	}
	return true;
}

function checkForm(){
	var flag1 = checkNull("userName");
	var flag2 = checkNull("email");
	return flag1&&flag2;
}
	window.onload = function(){
		var updateResult = "${updateResult}";
		if(updateResult == 2){
			alert("个人信息修改失败");
		}else if(updateResult == 1){
			alert("个人信息修改成功");
		}
		
    }
</script>
<style type="text/css">
	.mycontainer{
		width:400px;
		margin:0 auto;
	}
</style>
</head>
<body>
<jsp:include page="common.jsp"/>
<div class="mycontainer">
  <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/user/update.do" method="post" onsubmit="return checkForm();">
          <div class="form-group">
          <label for="username" class="col-sm-3 control-label">账号</label>
          <div class="col-sm-9">
              <input type="text" class="form-control" name="username" id="username" value="${user.username}">
          </div>
        </div>

         <div class="form-group">
          <label for="email" class="col-sm-3 control-label">邮件</label>
          <div class="col-sm-9">
            <input type="text" class="form-control" name="email" id="email" value="${user.email}">
          </div>
        </div>
        <div>
        	<div class="form-group">
        	
        	<div class="col-sm-offset-6 col-sm-2">
        	  <input type="submit" class="btn btn-success" value="更改"/>
        	</div>
        	<div class="col-sm-offset-1 col-sm-3">
        	  <input type="reset" class="btn btn-primary" value="重置"/>
        	</div>  
        </div>
		</div>
        </form>
</div>
</body>
</html>