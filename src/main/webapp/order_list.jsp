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
    <table class="table table-condensed">
        <thead>
        <tr>
            <th>订单号</th>
            <th>金额</th>
            <th>状态</th>
            <th>创建时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${result.data.list}" var="order">
            <tr>
                <td><a href="${pageContext.request.contextPath}/order/detail.do?orderNo=${order.orderNo}">${order.orderNo}</a></td>
                <td>${order.payment}</td>
                <td>${order.statusDesc}</td>
                <td>${order.createTime}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>
    <div>
        <ul class="pagination">
            <li><a href="${pageContext.request.contextPath}/order/list.do?pageNum=${result.data.prePage}">&laquo;</a></li>
            <c:forEach var="i" begin="${result.data.firstPage}" end="${result.data.lastPage}" step="1">
            <li>

                <c:if test="${i eq result.data.pageNum}">
                    <a href="${pageContext.request.contextPath}/order/list.do?pageNum=${i}" class="active"><c:out value="${i}"/></a>
                </c:if>
                    <c:if test="${i ne result.data.pageNum}">
                        <a href="${pageContext.request.contextPath}/order/list.do?pageNum=${i}"><c:out value="${i}"/></a>
                    </c:if>


            </li>
            </c:forEach>
            <li><a href="${pageContext.request.contextPath}/order/list.do?pageNum=${result.data.nextPage}">&raquo;</a></li>
        </ul>
        </div>
</div>

</body>
</html>