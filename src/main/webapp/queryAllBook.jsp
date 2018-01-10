<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload = function() {
	var xmlHttp =  new XMLHttpRequest();	
	xmlHttp.open("GET", "${pageContext.request.contextPath}/manage/category/getAllCategoryName.do", true);
	xmlHttp.send(null);
	xmlHttp.onreadystatechange = function() {
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var text = xmlHttp.responseText;
			var arr = text.split(",");
			var category = document.getElementById("category");
			for(var i = 0; i < arr.length; i++) {
				var op = document.createElement("option");
				op.value = arr[i];
				var textNode = document.createTextNode(arr[i]);
				op.appendChild(textNode);
				category.appendChild(op);
			}
		}
	};
};
</script>
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
  <form class="form-inline" role="form" action="${pageContext.request.contextPath}/manage/book/search.do" method="post">
   		
          <div class="form-group">
          <label for="name" class="control-label">书名</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="请输入书名">
        </div>
        <div class="form-group">
          <label for="author" class="control-label">作者</label>
            <input type="text" class="form-control" name="author" id="author" placeholder="请输入作者">
           
        </div>
       
          <div class="form-group">
          <label for="category" class="control-label">类别</label>
             <select name="categoryName" id="category" class="form-control">
				<option>全部</option>
			</select>
        </div>
        
        	<div class="form-group">
        	  <input type="submit" class="btn btn-success" value="搜索"/>
        	</div>
        </form>

        <p>${result.msg}</p>
        
		<div>
		<form action="${pageContext.request.contextPath}/manage/book/updownBook.do" method="post">
		<table class="table table-condensed">
			<thead>
			<tr>
				<th>书名</th>	
				<th>作者</th>
				<th>状态</th>	
				<th>上架|下架</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${result.data}" var="book">
				<tr>
				<td><a href="${pageContext.request.contextPath}/manage/book/detail.do?bookId=${book.id}">${book.name}</a></td>
				<td>${book.author }</td>
				<c:if test="${book.status eq 0 }">
					<td>已下架</td>
					<td align="center">上架<input type="checkbox" name="bookId" value="${book.id}"/></td>
				</c:if>
				<c:if test="${book.status eq 1 }">
					<td>在售</td>
					<td align="center">下架<input type="checkbox" name="bookId" value="${book.id}"/></td>	
				</c:if>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		<c:if test="${not empty result.data}">
				<div class="col-sm-offset-11 col-sm-1">
				<input type="submit" value="确定" class="btn btn-success"/>
				</div>
		</c:if>
		</form>
		</div>
</div>

</body>
</html>