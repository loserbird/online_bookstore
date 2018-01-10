<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">

        function update(bookId) {
            var count = document.getElementById(bookId);
            var url = "${pageContext.request.contextPath}/cart/update.do?bookId="+bookId+"&count="+count.value;
            window.location.href = url;
        }
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
<p>${result.msg}</p>
<div class="mycontainer">
    <div>
        <form action="${pageContext.request.contextPath}/cart/order.do" method="post">
            <table class="table table-condensed">
                <thead>
                <tr>
                    <th>书名</th>
                    <th>状态</th>
                    <th>库存</th>
                    <th>数量</th>
                    <th>单价</th>
                    <th>总价</th>
                    <th>是否勾选</th>
                    <th>修改数量</th>
                    <th>删除</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${result.data.cartBookVoList}" var="book">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/book/detail.do?bookId=${book.bookId}">${book.bookName}</a></td>
                        <c:if test="${book.bookStatus eq 0 }">
                            <td>已下架</td>
                        </c:if>
                        <c:if test="${book.bookStatus eq 1 }">
                            <td>在售</td>
                        </c:if>
                        <td>${book.bookStock}</td>
                        <td><input type="text" value="${book.quantity}" id="${book.bookId}" style="width:25px;"></td>
                        <td>${book.bookPrice}</td>
                        <td>${book.bookTotalPrice}</td>
                        <c:if test="${book.bookChecked eq 0 }">
                            <td align="center"><input type="checkbox" name="bookId" value="${book.bookId}"/></td>
                        </c:if>
                        <c:if test="${book.bookChecked eq 1 }">
                            <td align="center"><input type="checkbox" name="bookId" value="${book.bookId}" checked="checked"/></td>
                        </c:if>
                        <td><input type="button" class="btn btn-success" value="修改" onclick="update(${book.bookId})"/></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/cart/delete.do?bookId=${book.bookId}" class="btn btn-warning">
                                删除
                            </a>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:if test="${not empty result.data.cartBookVoList}">
                <div class="col-sm-offset-11 col-sm-1">
                    <input type="submit" value="去结算" class="btn btn-success"/>
                </div>
            </c:if>
        </form>
    </div>
    <div><p>总计：${result.data.cartTotalPrice} 元</p></div>
</div>

</body>
</html>