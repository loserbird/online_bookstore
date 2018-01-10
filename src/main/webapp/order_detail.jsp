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
                <label for="receiverMobile" class="col-sm-3 control-label">金额</label>
                <div class="col-sm-9">
                    <label id="receiverMobile" class="control-label">${result.data.payment}</label>
                </div>
            </div>
            <div class="form-group">
                <label for="address" class="col-sm-3 control-label">状态</label>
                <div class="col-sm-9">
                    <label id="address" class="control-label">${result.data.statusDesc}</label>
                </div>
            </div>
            <div class="form-group">
                <label for="paymenttype" class="col-sm-3 control-label">支付方式</label>
                <div class="col-sm-9">
                    <label id="paymenttype" class="control-label">${result.data.paymentTypeDesc}</label>

                </div>
            </div>
            <div class="form-group">
                <label for="postage" class="col-sm-3 control-label">运费</label>
                <div class="col-sm-9">
                    <label id="postage" class="control-label">${result.data.postage}</label>
                </div>
            </div>
            <div><p>所买商品：</p></div>
            <div>
                <table class="table table-condensed">
                    <thead>
                    <tr>
                        <th>书名</th>
                        <th>数量</th>
                        <th>单价</th>
                        <th>总价</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${result.data.orderItemVoList}" var="item">
                            <tr>
                                <td>${item.bookName}</td>
                                <td>${item.quantity}</td>
                                <td>${item.currentUnitPrice}</td>
                                <td>${item.totalPrice}</td>

                            </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:if test="${result.data.status ne 10 and result.data.status ne 0}">
                <div class="form-group">
                    <label for="paymentTime" class="col-sm-3 control-label">支付时间</label>
                    <div class="col-sm-9">
                        <label id="paymentTime" class="control-label">${result.data.paymentTime}</label>
                    </div>
                </div>
            </c:if>

            <c:if test="${result.data.status eq 10}">
                <div class="col-sm-offset-3 col-sm-3">
                    <a href="${pageContext.request.contextPath}/order/cancel.do?orderNo=${result.data.orderNo}" class="btn btn-info">取消</a>
                </div>
                <div class="col-sm-offset-3 col-sm-3">
                    <input type="submit" value="支付" class="btn btn-warning"/>
                </div>
            </c:if>
        </form>
    </div>

</div>

</body>
</html>