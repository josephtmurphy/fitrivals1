<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%><%-- 
    Document   : createScoreGroup
    Created on : 03-Dec-2020, 09:20:32
    Author     : josep
--%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create "Score" Group</title>
    </head>
    <body>
        <form action="createScoreGroup" method="post">
            <pre>
            <input type="text" name="groupname" placeholder="Group Name"/>
            <br/>
            <input type="text" name="name" value="${user.fullname}" readonly="readonly"/>
            <br/>
            <input type="number" name="pointsperkm" placeholder="How Many Point Awarded per km"/>
            <br/>
            <input type="number" name="pointspermin" placeholder="How Many Point Awarded per min"/>
            <br/>
            <input type="submit" value="Create Group"/>

            </pre>
        </form>
    </body>
</html>
