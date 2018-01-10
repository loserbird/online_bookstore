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
    <div><h3>订单创建成功，订单详情：</h3></div>
    <div>
        <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/order/pay.do" method="post">
            <input type="hidden" name="orderNo" value="${result.data.orderNo}">
            <div class="form-group">
                <label for="receiverName" class="col-sm-3 control-label">订单号</label>
                <div class="col-sm-9">
                    <label id="receiverName" class="control-label">${result.data.orderNo}</label>
                </div>
            </div>
            <div class="form-group">
                <label for="receiverMobile" class="col-sm-3 control-label">应付金额</label>
                <div class="col-sm-9">
                    <label id="receiverMobile" class="control-label">${result.data.payment}</label>
                </div>
            </div>
            <div class="form-group">
                <label for="paymenttype" class="col-sm-3 control-label">支付方式</label>
                <div class="col-sm-9">
                    <label id="paymenttype" class="control-label">支付宝</label>
                </div>
            </div>
                <div class="col-sm-offset-11 col-sm-1">
                    <input type="submit" value="支付" class="btn btn-warning"/>
                </div>
        </form>
    </div>

</div>

</body>
</html>