<%--
  Created by IntelliJ IDEA.
  User: jjh
  Date: 2026-06-10
  Time: 오후 1:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- request scope --%>
<form action="board" method="post">
  <input type="hidden" name="action" value="write">
  제목: <input type="text" name="title"><br />
  작성자: <input type="text" name="author"><br />
  내용: <textarea name="content" rows="10" ></textarea><br />
  <input type="submit" value="저장">
</form>
</body>
</html>
