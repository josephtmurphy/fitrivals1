<%-- 
    Document   : writeComment
    Created on : 02-Dec-2020, 14:03:39
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
        <form action="writeComment" method="post">
            <pre>
            <input type="text" name="groupname" placeholder="Group Name"/>
            <br/>
            <input type="text" name="name" placeholder="Your name in this group..."/>

            <select name="grouptype1" id="grouptype">
            <option>Distance</option>
            <option>Score</option>
            <option>Time</option>
            </select>
            
            <input type="text" name="comment" placeholder="Your comment..."/>
            
            <input type="submit" value="Create Group"/>

            </pre>
        </form>
    </body>
</html>
