
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*;" %>
<%@ page import="Database.dbcon;" %>
<%@ page import="Session.UserDAO;" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
        

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
  <a href="#about">About</a>
  <a href="javascript:void(0);" class="icon" onclick="myFunction()">&#9776;</a>
</div>
</div>
 
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
</html>
