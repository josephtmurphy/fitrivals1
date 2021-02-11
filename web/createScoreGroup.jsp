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
            <h3>Score Group - Distance</h3>
            <input type="text" name="groupname" placeholder="Group Name"/>
            <br/>
            <input type="text" name="name" value="${user.username}" readonly="readonly"/>
            <br/>
            <input type="number" name="run_points" placeholder="Points per KM Ran"/>
            <br/>
            <input type="number" name="cycle_points" placeholder="Points per KM Cycled"/>
            <br/>
            <input type="number" name="walk_points" placeholder="Points per KM Walked"/>
            <br/>
            <input type="submit" value="Create Group"/>
            <br/>
            </pre>
        </form>
            
        <form action="createScoreGroup2" method="post">
            <pre>
            <h3>Score Group - Time</h3>
            <input type="text" name="groupname" placeholder="Group Name"/>
            <br/>
            <input type="text" name="name" value="${user.username}" readonly="readonly"/>
            <br/>
            <input type="number" name="run_points" placeholder="Points per minute Ran"/>
            <br/>
            <input type="number" name="cycle_points" placeholder="Points per minute Cycled"/>
            <br/>
            <input type="number" name="walk_points" placeholder="Points per minute Walked"/>
            <br/>
            <input type="number" name="strength_points" placeholder="Points per minute Strength"/>
            <br/>
            <input type="submit" value="Create Group"/>

            </pre>
        </form>            
    </body>
</html>
