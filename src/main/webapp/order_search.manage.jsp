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
        .left{
            float:left;
        }
    </style>
</head>
<body>
<jsp:include page="common.jsp"/>
<div class="mycontainer">
    <div>
    <form class="form-inline" role="form" action="${pageContext.request.contextPath}/manage/order/search.do" method="post">
        <div class="form-group">
            <label for="name" class="control-label">订单号</label>
            <input type="text" class="form-control" name="orderNo" id="name" placeholder="请输入订单号">
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-success" value="搜索"/>
        </div>
    </form>
    </div>
    <div><p>${result.msg}</p></div>
    <div>
        <table class="table table-condensed">
            <thead>
            <tr>
                <th>订单号</th>
                <th>收货人</th>
                <th>金额</th>
                <th>状态</th>
                <th>创建时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${result.data.list}" var="order">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/manage/order/detail.do?orderNo=${order.orderNo}">${order.orderNo}</a></td>
                    <td>${order.receiverName}</td>
                    <td>${order.payment}</td>
                    <td>${order.statusDesc}</td>
                    <td>${order.createTime}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

</body>
</html>