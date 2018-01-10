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
	var category = document.getElementById("category");
	var childs = category.childNodes;
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
<span style="color:red;">${result.msg}</span>



<div class="mycontainer">
  <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/manage/book/update.do" method="post">
  	<input type="hidden" name="id" value="${result.data.id}">
          <div class="form-group">
          <label for="bookName" class="col-sm-3 control-label">书名</label>
          <div class="col-sm-9">
            <input type="text" class="form-control" name="name" id="bookName" value="${result.data.name}">
          </div>
        </div>
        <div class="form-group">
          <label for="author" class="col-sm-3 control-label">作者</label>
          <div class="col-sm-9">
            <input type="text" class="form-control" name="author" id="author" value="${result.data.author}">
          </div>
        </div>
         <div class="form-group">
          <label for="category" class="col-sm-3 control-label">类别</label>
          <div class="col-sm-9">
          <select  class="form-control" name="categoryName" id="category">
				<option>请选择类别</option>
			</select>
          </div>
        </div>
      <div class="form-group">
          <label for="stock" class="col-sm-3 control-label">库存</label>
          <div class="col-sm-9">
              <input type="text" class="form-control" name="stock" id="stock" value="${result.data.stock}">
          </div>
      </div>
      <div class="form-group">
          <label for="price" class="col-sm-3 control-label">价格</label>
          <div class="col-sm-9">
              <input type="text" class="form-control" name="price" id="price" value="${result.data.price}">
          </div>
      </div>
      <div class="form-group">
          <label for="detail" class="col-sm-3 control-label">详情</label>
          <div class="col-sm-9">
              <input type="textarea" name="detail" rows="10" cols="40" class="form-control" id="detail" value="${result.data.detail}">
          </div>
      </div>
        <div>
        	<div class="form-group">
        	
        	<div class="col-sm-offset-11 col-sm-1">
        	  <input type="submit" class="btn btn-success" value="修改"/>
        	</div>
        </div>
		</div>
        </form>
</div>
</body>
</html>