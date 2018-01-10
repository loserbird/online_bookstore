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
        <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/order/create.do" method="post">
            <input type="hidden" name="shippingId" value="${shipping.id}">
            <div class="form-group">
                <label for="receiverName" class="col-sm-3 control-label">收货人</label>
                <div class="col-sm-9">
                    <label id="receiverName" class="control-label">${shipping.receiverName}</label>
                </div>
            </div>
            <div class="form-group">
                <label for="receiverMobile" class="col-sm-3 control-label">手机号</label>
                <div class="col-sm-9">
                    <label id="receiverMobile" class="control-label">${shipping.receiverMobile}</label>
                </div>
            </div>
            <div class="form-group">
                <label for="address" class="col-sm-3 control-label">地址</label>
                <div class="col-sm-9">
                    <label id="address" class="control-label">${shipping.receiverProvince} ${shipping.receiverCity}
                        ${shipping.receiverDistrict} ${shipping.receiverAddress}</label>

                </div>
            </div>
            <div class="form-group">
                <label for="paymenttype" class="col-sm-3 control-label">支付方式</label>
                <div class="col-sm-9">
                    <label id="paymenttype" class="control-label">在线支付</label>

                </div>
            </div>
            <div class="form-group">
                <label for="postage" class="col-sm-3 control-label">运费</label>
                <div class="col-sm-9">
                    <label id="postage" class="control-label">0 元</label>
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
                    <c:forEach items="${result.data.cartBookVoList}" var="book">
                        <c:if test="${book.bookChecked eq 1 }">
                        <tr>
                            <td>${book.bookName}</td>
                            <td>${book.quantity}</td>
                            <td>${book.bookPrice}</td>
                            <td>${book.bookTotalPrice}</td>

                        </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div><p>应付款：${result.data.cartTotalPrice} 元</p></div>
            <c:if test="${not empty result.data.cartBookVoList}">
                <div class="col-sm-offset-11 col-sm-1">
                    <input type="submit" value="提交订单" class="btn btn-warning"/>
                </div>
            </c:if>
        </form>
    </div>

</div>

</body>
</html>