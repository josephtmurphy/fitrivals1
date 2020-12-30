<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>FitRivals - Home</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h2>FitRivals Home</h2>
        <b>Welcome ${user.fullname}</b>
        <p>Please select an action:</p>
        
        <a href="createGroup.jsp">Create a group</a>
        <br/>
        <br/>
        <a href="createScoreGroup.jsp">Create a "score" (points-based) group</a>
        <br/>
        <br/>        
        <a href="joinGroup.jsp">Join an existing group</a>
        <br/>
        <br/>
        <a href="groupList.jsp">View your groups</a>
        <br/>
        <br/>
        <a href="createCardio.jsp">Log an activity</a>
        <br/>
        <br/>
        <a href="logScoreActivity.jsp">Log Score activity</a>
        <br/>
        <br/>        
        <a href="writeComment.jsp">Write a comment in a group</a>
        <br/>          
        <br/>        
        <a href="createStrength.jsp">Log a strength training session</a>
        <br/>          
        <br/>        
        <a href="<%=request.getContextPath()%>/UserLogoutServlet">Logout</a>        
        
    </body>
</html>
