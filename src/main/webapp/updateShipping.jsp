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
  <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/shipping/update.do" method="post">
  	<input type="hidden" name="id" value="${result.data.id}">
      <div class="form-group">
          <label  class="col-sm-offset-3 col-sm-6 control-label">修改收货地址</label>
      </div>
      <div class="form-group">
          <label for="receiverName" class="col-sm-3 control-label">姓名</label>
          <div class="col-sm-9">
              <input type="text" class="form-control" name="receiverName" id="receiverName" value="${result.data.receiverName}">
          </div>
      </div>
      <div class="form-group">
          <label for="receiverPhone" class="col-sm-3 control-label">电话</label>
          <div class="col-sm-9">
              <input type="text" class="form-control" name="receiverPhone" id="receiverPhone" value="${result.data.receiverPhone}">
          </div>
      </div>
      <div class="form-group">
          <label for="receiverMobile" class="col-sm-3 control-label">手机</label>
          <div class="col-sm-9">
              <input type="text" class="form-control" name="receiverMobile" id="receiverMobile" value="${result.data.receiverMobile}">
          </div>
      </div>
      <div class="form-group">
          <label for="receiverProvince" class="col-sm-3 control-label">省份</label>
          <div class="col-sm-9">
              <input type="text" class="form-control" name="receiverProvince" id="receiverProvince" value="${result.data.receiverProvince}">
          </div>
      </div>
      <div class="form-group">
          <label for="receiverCity" class="col-sm-3 control-label">城市</label>
          <div class="col-sm-9">
              <input type="text" class="form-control" name="receiverCity" id="receiverCity" value="${result.data.receiverCity}">
          </div>
      </div>
      <div class="form-group">
          <label for="receiverDistrict" class="col-sm-3 control-label">区域</label>
          <div class="col-sm-9">
              <input type="text" class="form-control" name="receiverDistrict" id="receiverDistrict" value="${result.data.receiverDistrict}">
          </div>
      </div>
      <div class="form-group">
          <label for="receiverAddress" class="col-sm-3 control-label">详细地址</label>
          <div class="col-sm-9">
              <input type="text" class="form-control" name="receiverAddress" id="receiverAddress" value="${result.data.receiverAddress}">
          </div>
      </div>
      <div class="form-group">
          <label for="receiverZip" class="col-sm-3 control-label">邮编</label>
          <div class="col-sm-9">
              <input type="text" class="form-control" name="receiverZip" id="receiverZip" value="${result.data.receiverZip}">
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