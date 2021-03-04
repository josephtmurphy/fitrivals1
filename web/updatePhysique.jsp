
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*;" %>
<%@ page import="Database.dbcon;" %>
<%@ page import="Session.UserDAO;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Drop Down List</title>
</head>
<form action="updatePhysique" method="post">
<body>

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

<%--obtains username to handle session--%>
    <input type="text" name="name12" value="${user.username}" readonly="readonly"/>
    
<%   
    //connect to db, sql statement to call relevant physqiue data from users SQL table
    try {
    dbcon db = new dbcon();
    Connection con = db.getCon();
    String username = request.getParameter("loggedname");
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + username + "';");
    rs.next();
%>
<%--Shows all relevant information--%>
<p><i>Your details:</i>
    <br/>
    <label for="username"><b>Username</b></label>
    <input type="text" readonly="readonly" name="username" value="<%=rs.getString(3)%>"<%=rs.getString(3)%></input>
    <br/>
    <br/>    
    <label for="height"><b>Height (cm)</b></label>
    <input type="text" name="height" value="<%=rs.getString(6)%>"<%=rs.getString(6)%></input>
    <br/>
    <br/>
    <label for="weight"><b>Weight (lbs)</b></label>
    <input type="text" name="weight" value="<%=rs.getString(7)%>"<%=rs.getString(7)%></input>
    <br/>
    <br/>
    <label for="thigh"><b>Thigh (cm)</b></label>
    <input type="text" name="thigh" value="<%=rs.getString(8)%>"<%=rs.getString(8)%></input>
    <br/>
    <br/>
    <label for="bicep"><b>Bicep (cm)</b></label>
    <input type="text" name="bicep" value="<%=rs.getString(9)%>"<%=rs.getString(9)%></input>
    <br/>
    <br/>
    <label for="waist"><b>Waist (cm)</b></label>
    <input type="text" name="waist" value="<%=rs.getString(10)%>"<%=rs.getString(10)%></input>
</p>

<%
}
catch(SQLException sqe)
{ 
out.println(sqe);
}
%>
<%--applies changes and activates updatePhysique servlet--%>       
<input type="submit" value="Update"/>
</body>
</form>
</html>