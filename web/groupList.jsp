<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%-- 
    Document   : groupList
    Created on : 26-Nov-2020, 09:29:02
    Author     : josep
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Groups</title>
    </head>
        <h2>View group details</h2>
        <p>Here, you can view the current standings in your FitRivals groups.</p>
        <p>Just enter the group's name and the focus of the group.</p>    
        <form action="viewGroups" method="post">
        <br/>
        <input type="text" name="groupname" placeholder="Join Group"/>
        <br/>
        <br/>
            <select name="grouptype" id="grouptype">
            <option>Distance</option>
            <option>Score</option>
            <option>Time</option>
            </select>        
        <br/>
        <br/>
        <input type="submit" value="View"/>
        </form>
    
</html>
