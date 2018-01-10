<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        .mycontainer{
            width:700px;
            margin:0 auto;
            margin-top:40px;
        }
    </style>
</head>
<body>
<jsp:include page="common.jsp"/>
<div class="mycontainer">
    <form class="form-inline" role="form" action="${pageContext.request.contextPath}/manage/user/list.do" method="post">

        <div class="form-group">
            <label for="username" class="control-label">用户名</label>
            <input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名">
        </div>
        <div class="form-group">
            <label for="author" class="control-label">邮箱</label>
            <input type="text" class="form-control" name="email" id="author" placeholder="请输入邮箱">
        </div>

        <div class="form-group">
            <input type="submit" class="btn btn-success" value="搜索"/>
        </div>
    </form>

    <p>${result.msg}</p>
    <div>
            <table class="table table-condensed">
                <thead>
                <tr>
                    <th>用户名</th>
                    <th>邮箱</th>
                    <th>注册时间</th>
                    <th>删除</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${result.data.list}" var="user">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/manage/user/detail.do?userId=${user.id}">${user.username}</a></td>
                        <td>${user.email}</td>
                        <td>${user.createTime}</td>
                        <td><a class="btn-danger" href="${pageContext.request.contextPath}/manage/user/delete.do?userId=${user.id}">删除</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
    </div>
</div>

</body>
</html>