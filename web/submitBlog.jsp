<%-- 
    Document   : submitBlog
    Created on : 20-Feb-2021, 13:18:35
    Author     : josep
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="submitBlog" method="post">
        <input type="text" name="name" value="${user.username}" readonly="readonly"/>
        <br/>
        <br/>
        <input type="text" name="blog_title" placeholder="Blog Title"/>
        <br/>
        <br/>
        <textarea placeholder="Blog..." name="blog_content" cols="50" rows="12"></textarea>
        <br/>
        <br/>
        <input type="submit" value="Submit Blog"/>
        </form>
    </body>
</html>
