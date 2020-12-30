<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%-- 
    Document   : writeComment
    Created on : 02-Dec-2020, 14:03:39
    Author     : josep
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Write Comment</title>
    </head>
    <body>
        <form action="writeComment" method="post">
            <pre>
            <input type="text" name="groupname" placeholder="Group Name"/>
            <br/>
            <input type="text" name="name" value="${user.username}" readonly="readonly"/>

            <select name="grouptype1" id="grouptype">
            <option>Distance</option>
            <option>Score</option>
            <option>Time</option>
            </select>
            
            <input type="text" name="comment" placeholder="Your comment..."/>
            
            <input type="submit" value="Post Comment"/>

            </pre>
        </form>
    </body>
</html>
