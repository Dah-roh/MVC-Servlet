<%@ page import="java.util.List" %>
<%@ page import="com.example.demofb.DTO.PostDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DemoFB</title>
</head>
<style>
    textarea {
        width: 200px;
    }
    .link {
        display: block;
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

    <%
    if (request.getAttribute("update-post")!=null&&request.getAttribute("post")!=null){
    %>
<form method="post" action="${pageContext.request.contextPath}/homepage">
    <textarea name="update-post" rows="5" cols="8"><%=request.getAttribute("update-post")%></textarea>
    <input type="hidden" name="post-id" value=<%=request.getAttribute("post-id")%>>
    <button type="submit">Edit</button>
</form>

<%
    }
    else if (request.getAttribute("post")!=null){
%>


<h2>Make a post</h2>
</p>
<form method="post" action="${pageContext.request.contextPath}/homepage">
    <label><textarea name="post" rows="5" cols="8" placeholder="Write something today..."></textarea></label>
    <button type="submit">Post</button>
</form>

<section>
    <h2>Your Posts</h2>
    <%
        List<PostDTO> postContent  = (List<PostDTO>) request.getAttribute("post");

        for (PostDTO postDTO : postContent)
        {


        %>
    <p><%=postDTO.getContent()%>
    </p><a href=${pageContext.request.contextPath}/homepage?del=<%=postDTO.getId()%>>Delete</a>
    <a href="${pageContext.request.contextPath}/homepage?upd=<%=postDTO.getId()%>">Edit</a><br/><br/><br/>

    <%
            }

        }

        %>


</section>

</body>
</html>