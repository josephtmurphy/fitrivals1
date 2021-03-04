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
        <div id="allactivities">
        <br/>
        <div id="cardio" class="groupdiv">
        <form action="createCardio" method="post">
            <pre>
        <%   
    try {
    dbcon db = new dbcon();
    Connection con = db.getCon();
    String username = request.getParameter("loggedname");
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM group_members WHERE username = '" + username + "';");
        %>
        <p>Select Group:
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
            
        <div id="score" class="groupdiv">
                        <h2>Create a "score" group here:</h2>
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
<p>Select Group:
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
            <br/>
            <input type="text" name="name" value="${user.username}" readonly="readonly"/>

            <h3>Muscle Group Worked:</h3>
            <select name="muscleGroup1" id="muscleGroup1">
            <option>Arms</option>
            <option>Back</option>
            <option>Chest</option>
            <option>Legs</option>
            <option>Shoulders</option>
            <option>Full Body</option>
            </select>

            <h3>Secondary Muscle Group Worked:</h3>
            <select name="muscleGroup2" id="muscleGroup2">
            <option></option>
            <option>Arms</option>
            <option>Back</option>
            <option>Chest</option>
            <option>Legs</option>
            <option>Shoulders</option>
            </select>
            
            <h3>Activity Details</h3>
            <input type="number" name="time" placeholder="Time (mins)"/>

            <input type="text" name="comment" placeholder="Your comment on this activity..."/>
<%
}
catch(SQLException sqe)
{ 
out.println(sqe);
}
%>
            <input type="submit" value="Log Activity"/>

            </pre>
        </form>
        </div>            
            
        <div id="join" class="groupdiv">
        <form action="writeComment" method="post">
        <input type="text" name="name12" value="${user.username}" readonly="readonly"/>
            <pre>
<%   
    try {
    dbcon db = new dbcon();
    Connection con = db.getCon();
    String username = request.getParameter("loggedname");
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM group_members WHERE username = '" + username + "';");
%>
            <br/>

<p>Select Group:
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
            
            <input type="text" name="comment" placeholder="Your comment..."/>
<%
}
catch(SQLException sqe)
{ 
out.println(sqe);
}
%>            
            <input type="submit" value="Post Comment"/>

            </pre>
        </form>     
        </div>
            
        <div id="view" class="groupdiv">
        <form action="viewGroups" method="POST">
            <pre>
            <h2>View the standings in your groups here:</h2>
            <input type="text" name="name12" value="${user.username}" readonly="readonly"/>
    
<%   
    try {
    dbcon db = new dbcon();
    Connection con = db.getCon();
    String username = request.getParameter("loggedname");
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM group_members WHERE username = '" + username + "';");
%>
            <label for="groupname"><b>Select Group:</b></label>
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
<%
}
catch(SQLException sqe)
{ 
out.println(sqe);
}
%>
            <input type="submit" value="View"/>
            </pre>
            </div>
        </form>                
        </div>
    </body>
</html>