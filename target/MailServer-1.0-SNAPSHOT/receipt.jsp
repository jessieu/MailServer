<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: weilanyu
  Date: 10/7/20
  Time: 3:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Receipt</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <c:if test = "${success}">
        <h2>Congratulation! Your email has been sent successfully!</h2>
        <a role="button" class="btn btn-success" href="${pageContext.request.contextPath}">Send more</a>
    </c:if>
    <c:if test = "${not success}">
        <h2>Sorry, all of our mail servers are currently down. You can try again or come back later!</h2>
        <a role="button" class="btn btn-primary" onClick="history.go(-1)">Retry</a>
    </c:if>
</body>
</html>
