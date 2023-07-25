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
<% if (request.getAttribute("id")==null)
{
%>
<form method="post" action="${pageContext.request.contextPath}/hello-servlet">
    <label>Email: <input name="email" type="email"></label>
    <label>Password: <input name="password" type="password"></label>
    <label>lastname: <input name="lastname" type="text"></label>
    <label>firstname: <input name="firstname" type="text"></label>
    <input name="signup" type="hidden" value="signup">
    <button type="submit">Sign up</button> <a href="${pageContext.request.contextPath}/hello-servlet?id=1" >Login</a> if you have an account
</form>
<%
    }
%>
<% if (request.getAttribute("id")!=null){
    %>
<form method="post" action="${pageContext.request.contextPath}/hello-servlet">
    <label>Email: <input name="email" type="text"></label>
    <label>Password: <input name="password" type="password"></label>
    <button type="submit">Login</button>
</form>
<%
    if (request.getAttribute("login")!=null)
    {
%>
<p>
    <%=request.getAttribute("login")%>
</p>
<%
    }
%>
<%
    }
%>
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
</body>
</html>