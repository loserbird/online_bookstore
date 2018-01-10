<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ page import="com.loserbird.bookstore.pojo.User" %>
      <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%
	User user = (User)session.getAttribute("user"); //权限校验
	if(user != null&&user.getRole().equals("1")){
		response.sendRedirect("middle.jsp");
		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.mycontainer{
		width:400px;
		margin:0 auto;
		margin-top:40px;
	}
</style>
</head>
<body>
<jsp:include page="common.jsp"/>
<div class="mycontainer">
  <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/manage/category/add.do" method="post">
   		<div class="form-group">
          <label  class="col-sm-offset-3 col-sm-6 control-label">添加书籍类别</label>
        </div>
          <div class="form-group">
          <label for="categoryName" class="col-sm-3 control-label">类名</label>
          <div class="col-sm-9">
            <input type="text" class="form-control" name="categoryName" id="categoryName">
          </div>
        </div>
       
        <div>
        	<div class="form-group">
        	
        	<div class="col-sm-offset-6 col-sm-2">
        	  <input type="submit" class="btn btn-success" value="添加"/>
        	</div>
        	<div class="col-sm-offset-1 col-sm-3">
        	  <input type="reset" class="btn btn-primary" value="取消"/>
        	</div>  
        </div>
		</div>
        </form>
</div>
</body>
</html>