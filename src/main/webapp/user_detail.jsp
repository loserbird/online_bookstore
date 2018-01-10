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
        <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/manage/user/update.do" method="post">
            <input type="hidden" name="id" value="${result.data.id}">
            <div class="form-group">
                <label  class="col-sm-3 control-label">用户名</label>
                <div class="col-sm-9">
                    <input type="text" value="${result.data.username}" name="username"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">邮箱</label>
                <div class="col-sm-9">
                    <input type="text" value="${result.data.email}" name="email"/>
                </div>
            </div>
                <div class="col-sm-offset-3 col-sm-3">
                    <input type="submit" value="修改" class="btn btn-warning"/>
                </div>

        </form>
    </div>

</div>

</body>
</html>