<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%-- 
    Document   : logScoreActivity
    Created on : 03-Dec-2020, 09:32:59
    Author     : josep
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log Activity</title>
    </head>
    <body>
        <form action="logScoreActivity" method="post">
            <pre>
            <input type="text" name="groupname" placeholder="Group Name"/>
            <br/>
            <input type="text" name="name" value="${user.username}" readonly="readonly"/>

            <h2>Activity Details:</h2>
            <select name="activityType" id="activitytype">
            <option>Run</option>
            <option>Walk</option>
            <option>Cycle</option>
            </select>

            <input type="number" name="distance" placeholder="Distance (km)"/>
            
            <input type="number" name="time" placeholder="Time (mins)"/>

            <input type="text" name="comment" placeholder="Your comment on this activity..."/>

            <input type="submit" value="Log Activity"/>

            </pre>
        </form>
    </body>
</html>
