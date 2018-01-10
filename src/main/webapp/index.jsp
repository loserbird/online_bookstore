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
	.left{
	float:left;
}
</style>
</head>
<body>
<jsp:include page="common.jsp"/>
<div class="mycontainer">
  <form class="form-inline" role="form" action="${pageContext.request.contextPath}/book/search.do" method="post">

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
	<c:forEach items="${result.data.list}" var="book" varStatus="vs">
		<c:if test="${vs.count%5==0}">
			<div>
		</c:if>
		<c:if test="${vs.count%5!=0}">
			<div class="left">
		</c:if>
			<img alt="图片" width="100px" height="100px" 
				src="${pageContext.request.contextPath}/${book.image}"/>
			<p>
				<a href="${pageContext.request.contextPath}/book/detail.do?bookId=${book.id}">${book.name }</a>
			</p>	
		</div>
	</c:forEach>
</div>
        <div>
        <ul class="pagination">
            <li><a href="${pageContext.request.contextPath}/book/list.do?pageNum=${result.data.prePage}">&laquo;</a></li>
            <c:forEach var="i" begin="${result.data.firstPage}" end="${result.data.lastPage}" step="1">
            <li>

                <c:if test="${i eq result.data.pageNum}">
                    <a href="${pageContext.request.contextPath}/book/list.do?pageNum=${i}" class="active"><c:out value="${i}"/></a>
                </c:if>
                    <c:if test="${i ne result.data.pageNum}">
                        <a href="${pageContext.request.contextPath}/book/list.do?pageNum=${i}"><c:out value="${i}"/></a>
                    </c:if>


            </li>
            </c:forEach>
            <li><a href="${pageContext.request.contextPath}/book/list.do?pageNum=${result.data.nextPage}">&raquo;</a></li>
        </ul>
        </div>
</div>

</body>
</html>