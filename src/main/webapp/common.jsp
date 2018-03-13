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
  .library-img{
    margin: 0;
    padding: 0;
    width: 100%;
    height: 200px;
    background-image: url('${pageContext.request.contextPath}/image/bookstore.jpg');
    background-repeat: no-repeat;  
    background-size: 100% 100%;  
  }
   
  </style>
</head>
<body>
<nav class="navbar navbar-inverse  navbar-static-top" role="navigation">
    <div class="container-fluid">
    <div class="navbar-header">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/book/list.do">首页</a>
    </div>
    <div>
        <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/userInfo.jsp">个人资料</a></li>
            <li><a href="${pageContext.request.contextPath}/updatePassword.jsp">修改密码</a></li>
            <c:if test="${user.role == 1 }">
                <li><a href="${pageContext.request.contextPath}/manage/user/list.do">用户管理</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    分类管理 <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/addCategory.jsp">添加分类</a></li>
                    <li><a href="${pageContext.request.contextPath}/manage/category/queryall.do">查看分类</a></li>
                </ul>
            </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        图书管理 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/addBook.jsp">添加图书</a></li>
                        <li><a href="${pageContext.request.contextPath}/manage/book/list.do">查看图书</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        订单管理 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/manage/order/list.do">所有订单</a></li>
                        <li><a href="${pageContext.request.contextPath}/order_search.manage.jsp">搜索订单</a></li>
                        <li><a href="${pageContext.request.contextPath}/manage/order/list.do?status=20">待发货</a></li>
                        <li><a href="${pageContext.request.contextPath}/manage/order/list.do?status=40">待完结</a></li>
                    </ul>
                </li>

             </c:if>
             
            <c:if test="${user.role == 0}">
                <li><a href="${pageContext.request.contextPath}/cart/list.do">我的购物车</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        收获地址管理 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/addShipping.jsp">添加</a></li>
                        <li><a href="${pageContext.request.contextPath}/shipping/list.do">查看</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    订单管理 <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/order/list.do">所有订单</a></li>
                    <li><a href="${pageContext.request.contextPath}/order/list.do?status=10">待支付</a></li>
                    <li><a href="${pageContext.request.contextPath}/order/list.do?status=20">待发货</a></li>
                    <li><a href="${pageContext.request.contextPath}/order/list.do?status=0">已取消</a></li>
                    <li><a href="${pageContext.request.contextPath}/order/list.do?status=40">待收货</a></li>
                </ul>
               </li>
            </c:if>
        </ul>
        
       
         <ul class="nav navbar-nav navbar-right"> 
            <li>
            <a href="${pageContext.request.contextPath}/regist.jsp" target="_parent">
            <span class="glyphicon glyphicon-user"></span> 注册 </a></li>
            <li><a href="${pageContext.request.contextPath}/user/logout.do" target="_parent">
            <span class="glyphicon glyphicon-log-in"></span> 退出登录</a></li> 
        </ul> 
    </div>
    </div>
</nav>
<div class="library-img">

</div>



</body>
</html>
</body>
</html>