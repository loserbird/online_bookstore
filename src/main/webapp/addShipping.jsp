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
<span style="color:red;font-size:16px;">${result.msg}</span>
<div class="mycontainer">
  <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/shipping/add.do" method="post">
   		<div class="form-group">
          <label  class="col-sm-offset-3 col-sm-6 control-label">添加收货地址</label>
        </div>
          <div class="form-group">
          <label for="receiverName" class="col-sm-3 control-label">姓名</label>
          <div class="col-sm-9">
            <input type="text" class="form-control" name="receiverName" id="receiverName">
          </div>
        </div>
        <div class="form-group">
          <label for="receiverPhone" class="col-sm-3 control-label">电话</label>
          <div class="col-sm-9">
            <input type="text" class="form-control" name="receiverPhone" id="receiverPhone">
          </div>
        </div>
      <div class="form-group">
          <label for="receiverMobile" class="col-sm-3 control-label">手机</label>
          <div class="col-sm-9">
              <input type="text" class="form-control" name="receiverMobile" id="receiverMobile">
          </div>
      </div>
      <div class="form-group">
          <label for="receiverProvince" class="col-sm-3 control-label">省份</label>
          <div class="col-sm-9">
              <input type="text" class="form-control" name="receiverProvince" id="receiverProvince">
          </div>
      </div>
      <div class="form-group">
          <label for="receiverCity" class="col-sm-3 control-label">城市</label>
          <div class="col-sm-9">
              <input type="text" class="form-control" name="receiverCity" id="receiverCity">
          </div>
      </div>
      <div class="form-group">
          <label for="receiverDistrict" class="col-sm-3 control-label">区域</label>
          <div class="col-sm-9">
              <input type="text" class="form-control" name="receiverDistrict" id="receiverDistrict">
          </div>
      </div>
      <div class="form-group">
          <label for="receiverAddress" class="col-sm-3 control-label">详细地址</label>
          <div class="col-sm-9">
              <input type="text" class="form-control" name="receiverAddress" id="receiverAddress">
          </div>
      </div>
      <div class="form-group">
          <label for="receiverZip" class="col-sm-3 control-label">邮编</label>
          <div class="col-sm-9">
              <input type="text" class="form-control" name="receiverZip" id="receiverZip">
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