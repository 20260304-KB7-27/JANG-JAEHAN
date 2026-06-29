<%--
  Created by IntelliJ IDEA.
  User: jjh
  Date: 2026-06-25
  Time: 오후 2:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>/security/admin</h1>
    <h2>관리자 접근 가능</h2>

    <form action="/security/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="로그아웃"/>
    </form>
</body>
</html>
