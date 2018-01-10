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
<span style="color:red;">${result.msg}</span>



<div class="mycontainer">
  <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/cart/add.do" method="post">
  	<input type="hidden" name="bookId" value="${result.data.id}">
          <div class="form-group">
          <label for="bookName" class="col-sm-3 control-label">书名</label>
          <div class="col-sm-9">
              <label class="control-label" id="bookName">${result.data.name}</label>
          </div>
        </div>
        <div class="form-group">
          <label for="author" class="col-sm-3 control-label">作者</label>
          <div class="col-sm-9">
              <label class="control-label" id="author">${result.data.author}</label>
          </div>
        </div>
      <div class="form-group">
          <label for="stock" class="col-sm-3 control-label">库存</label>
          <div class="col-sm-9">
              <label class="control-label" id="stock">${result.data.stock}</label>
          </div>
      </div>
      <div class="form-group">
          <label for="price" class="col-sm-3 control-label">价格</label>
          <div class="col-sm-9">
              <label class="control-label" id="price">${result.data.price}</label>
          </div>
      </div>
      <div class="form-group">
          <label for="detail" class="col-sm-3 control-label">详情</label>
          <div class="col-sm-9">
              <label class="control-label" id="detail">${result.data.detail}</label>
          </div>
      </div>
      <div class="form-group">
          <label for="detail" class="col-sm-3 control-label">图片</label>
          <div class="col-sm-9">
          <img alt="图片" width="100px" height="100px"
               src="${pageContext.request.contextPath}/${result.data.image}"/>
          </div>
      </div>
      <div class="form-group">
          <label for="count" class="col-sm-3 control-label">购买数量</label>
          <div class="col-sm-9">
              <input type="text" class="form-control" name="count" id="count" value="1">
          </div>
      </div>
        <div>
            <div class="form-group">
                <c:if test="${result.data.stock gt 0 }">
                <div class="col-sm-offset-2 col-sm-2">
                    <input type="submit" class="btn btn-info" value="加入购物车"/>
                </div>
                <div class="col-sm-2">
                    <a href="" class="btn btn-success" role="button">立即购买</a>
                </div>
                </c:if>
            </div>
        </div>


        </form>
</div>
</body>
</html>