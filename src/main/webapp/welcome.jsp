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
<style>
    textarea {
        width: 200px;
    }
</style>
<body>
<%--SEO--%>
<h1>DemoFB-Social media</h1>
<p>Welcome
    <%
        String loggedInUser = "";
        if (session.getAttribute("name")!=null){
            loggedInUser = (String) session.getAttribute("name");
    %>
    <%=
    loggedInUser
            %>
    <%
    }
    %>
<h2>Make a post</h2>
<form method="post" action="${pageContext.request.contextPath}/homepage">
    <label><textarea name="post" rows="5" cols="8" placeholder="Write something today..."></textarea></label>
    <button type="submit">Post</button>
</form>

</p>
</body>
</html>
