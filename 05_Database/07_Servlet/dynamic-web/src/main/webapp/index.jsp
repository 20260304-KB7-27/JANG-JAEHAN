<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>

<h4>form -> get 요청 </h4>
<form action="service-test" method = "get">
    <input type = "submit" value = "GET 방식 요청 전송"/>
</form>

<h4>form -> post 요청 </h4>
<form action="service-test" method = "post">
    <input type = "submit" value = "POST 방식 요청 전송"/>
</form>

</body>
</html>