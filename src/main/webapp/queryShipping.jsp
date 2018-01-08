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
        <p>${result.msg}</p>
		<table class="table table-condensed">
			<thead>
			<tr>
				<th>姓名</th>
				<th>手机</th>
				<th>地址</th>
				<th>修改</th>
				<th>删除</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${result.data.list}" var="shipping">
				<tr>
				<td>${shipping.receiverName}</td>
				<td>${shipping.receiverMobile }</td>
				<td>${shipping.receiverProvince} ${shipping.receiverCity}
                    ${shipping.receiverDistrict} ${shipping.receiverAddress}</td>
				<td><a href="${pageContext.request.contextPath}/shipping/select.do?shippingId=${shipping.id}" class="btn btn-success">修改</a></td>
                    <td><a href="${pageContext.request.contextPath}/shipping/del.do?shippingId=${shipping.id}" class="btn btn-info">删除</a></td>
			</tr>
			</c:forEach>
			</tbody>
		</table>

		</div>
</div>

</body>
</html>