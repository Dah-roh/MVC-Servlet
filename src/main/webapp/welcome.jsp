<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 24/07/2023
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DemoFB</title>
</head>
<body>
<%--SEO--%>
<h1>DemoFB-Social media</h1>
<p>Welcome user with email :
    <%
        String loggedInUser = "";
        if (request.getAttribute("email")!=null){
            loggedInUser = (String) request.getAttribute("email");
    %>
    <%=
    loggedInUser
            %>
    <%
    }
    %>


</p>
</body>
</html>
