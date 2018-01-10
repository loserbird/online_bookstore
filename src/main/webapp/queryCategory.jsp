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
		width:400px;
		margin:0 auto;
		margin-top:40px;
	}
</style>
</head>
<body>
<jsp:include page="common.jsp"/>
<div class="mycontainer">
    <p>${result.msg}</p>
<table class="table table-hover">
<caption>所有书籍类别</caption>
	<thead>
    <tr>
      <th>类别</th>
      <th>修改</th>
    </tr>
  </thead>
  <tbody>
	<c:forEach items="${result.data}" var="category">
	<tr>
        <form action="${pageContext.request.contextPath}/manage/category/update.do" method="post">
            <input type="hidden" name="categoryId" value="${category.id}">
            <td><input type="text" class="form-control" name="categoryName" id="categoryName" value="${category.name}"></td>
           <td><input type="submit" class="btn btn-success" value="修改"/></td>
        </form>
	</tr>
	</c:forEach>
	</tbody>
</table>
</div>
</body>
</html>