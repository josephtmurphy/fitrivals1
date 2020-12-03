<%-- 
    Document   : createCardio
    Created on : 02-Dec-2020, 11:29:55
    Author     : josep
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log Activity</title>
    </head>
        <form action="createCardio" method="post">
            <pre>
            <input type="text" name="groupname" placeholder="Group Name"/>
            <br/>
            <input type="text" name="name" placeholder="Your name in this group..."/>
            <select name="grouptype1" id="grouptype">
            <option>Distance</option>
            <option>Score</option>
            <option>Time</option>
            </select>

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
</html>
