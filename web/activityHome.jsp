<%-- 
    Document   : activityHome
    Created on : 04-Mar-2021, 13:03:08
    Author     : josep
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="Database.dbcon"%>
<%@page import="java.sql.SQLException"%>
<%@ page import="java.sql.*;" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/homePage_css.css" rel="stylesheet">
        <title>Activities</title>
        <%--Navigation bar--%>
        <div class="fixed">
        <div class="navbar" id="myTopnav">
            <a href="homepage.jsp" class="active">Home</a>
            <a href="#news">News</a>
            <a href="#contact">Contact</a>
        <div class="dropdown">
            <button class="dropbtn">Blog
            <i class="fa fa-caret-down"></i>
            </button>
        <div class="dropdown-content">
            <a href="videosHome.jsp">Plans & Videos</a>
            <a href="blogHome.jsp">FitRivals Blog</a>
            <a href="submitBlog.jsp">Submit a Blog Post</a>
        </div>
        </div>
        </div>
        </div>        
    </head>
    
    <body>
        <div id="allgroups">
        <br/>
        <div style="background-image: url('images/runninggroup.jpg'); background-size: cover; background-repeat: no-repeat; background-position: center;" id="cardio" class="activitydiv">
            <h2>Log a cardio training session here:</h2>
        <form action="createCardio" method="post">
            <pre>
            <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
        <%   
    try {
    dbcon db = new dbcon();
    Connection con = db.getCon();
    String username = request.getParameter("loggedname");
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM group_members WHERE username = '" + username + "';");
        %>   
<label for="groupname"><b>Group name:</b></label>
<select name="groupname" id="groupname">
<%
while(rs.next()) {
String groupname = rs.getString(3); 
%>
<option value="<%=groupname %>"><%=groupname %></option>
<%
}
%>
</select>       
            <label for="name">Username:</label>
            <input type="text" name="name" value="${user.username}" readonly="readonly"/>
            <label for="activityType">Activity Type:</label>
            <select name="activityType" id="activitytype">
            <option>Run</option>
            <option>Walk</option>
            <option>Cycle</option>
            </select>          
            <label for="distance">Distance (km):</label>
            <input type="number" name="distance" placeholder="Distance (km)"/>          
            <label for="time">Time (mins):</label>
            <input type="number" name="time" placeholder="Time (mins)"/>
            <label for="comment">Your comment on this activity:</label>
            <input type="text" name="comment" placeholder="Your comment..."/>
            <input type="submit" value="Log Activity"/>

<%
    }
        catch(SQLException sqe)
    { 
        out.println(sqe);
    }
%>
            
            </pre>
        </form>       
        </div>
            
        <div style="background-image: url('images/gymgroup.jpg'); background-size: cover; background-repeat: no-repeat; background-position: center;" id="strength" class="activitydiv">
                        <h2>Log a strength training session here:</h2>
        <form action="createStrength" method="post">
            <pre>
<%   
    try {
    dbcon db = new dbcon();
    Connection con = db.getCon();
    String username = request.getParameter("loggedname");
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM group_members WHERE username = '" + username + "';");
%>
<label for="groupname">Group Name:</label>
<select name="groupname" id="groupname">
<%
while(rs.next()) {
String groupname = rs.getString(3); 
%>
<option value="<%=groupname %>"><%=groupname %></option>
<%
}
%>
</select>
            <label for="name">Username:</label>
            <input type="text" name="name" value="${user.username}" readonly="readonly"/>
            <label for="muscleGroup1">Muscle Group Worked:</label>
            <select name="muscleGroup1" id="muscleGroup1">
            <option>Arms</option>
            <option>Back</option>
            <option>Chest</option>
            <option>Legs</option>
            <option>Shoulders</option>
            <option>Full Body</option>
            </select>
            <label for="muscleGroup2">Secondary Muscle Group Worked:</label>
            <select name="muscleGroup2" id="muscleGroup2">
            <option></option>
            <option>Arms</option>
            <option>Back</option>
            <option>Chest</option>
            <option>Legs</option>
            <option>Shoulders</option>
            </select>      
            <label for="time"><b>Time (mins):</b></label>
            <input type="number" name="time" placeholder="Time (mins)"/>      
            <label for="comment"><b>Your comment on this activity:</b></label>
            <input type="text" name="comment" placeholder="Your comment..."/>
            <input type="submit" value="Log Activity"/>
<%}
catch(SQLException sqe)
{ 
out.println(sqe);
} %>
            </pre>
        </form>
        </div>            
            
        <div style="background-image: url('images/encouragement.jpg'); background-size: cover; background-repeat: no-repeat; background-position: center;" id="comment" class="activitydiv">
            <h2>Write a comment in a group here:</h2>
        <form action="writeComment" method="post">
            <pre>
<%   
    try {
    dbcon db = new dbcon();
    Connection con = db.getCon();
    String username = request.getParameter("loggedname");
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM group_members WHERE username = '" + username + "';");
%>     
<label for="groupname"><b>Group name:</b></label>
<select name="groupname" id="groupname">
<%
while(rs.next()) {
String groupname = rs.getString(3); 
%>
<option value="<%=groupname %>"><%=groupname %></option>
<%
}
%>
</select>          
            <label for="name12">Username:</label>
            <input type="text" name="name12" value="${user.username}" readonly="readonly"/>
            <label for="comment"><b>Your comment:</b></label>
            <input type="text" name="comment" placeholder="Your comment..."/>
            <input type="submit" value="Post Comment"/>
<%
}
catch(SQLException sqe)
{ 
out.println(sqe);
}
%>            
            
            </pre>
        </form>     
        </div>
    
        
    
        
    </body>
</html>