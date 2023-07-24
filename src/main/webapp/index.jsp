<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>DemoFB</title>
</head>
<style>
    input {
        display: block;
    }
</style>
<body>

<img src="${pageContext.request.contextPath}/resources/images/fb.jpeg" alt="facebook" width="225" height="225"/>
<h1>Login
</h1>
<br/>
<form method="post" action="${pageContext.request.contextPath}/hello-servlet">
    <label>Email: <input name="email" type="text"></label>
    <label>Password: <input name="password" type="password"></label>
    <button type="submit">Login</button>
    <%
    if (request.getAttribute("error")!=null)
    {
        %>
    <p>
    <%=request.getAttribute("error")%>
    </p>
    <%
    }
    %>
</form>
</body>
</html>