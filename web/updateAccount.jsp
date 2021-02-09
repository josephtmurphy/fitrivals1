
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
<form action="updateUser" method="post">
<body>
<%   String blah = request.getParameter("NAME12");
%>

    <input type="text" name="name12" value="${user.username}" readonly="readonly"/>
    
<%   
    try {
    dbcon db = new dbcon();
    Connection con = db.getCon();
    String username = request.getParameter("loggedname");
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + username + "';");
    rs.next();
%>
<p><i>Your details:</i>
    <br/>
    <label for="email"><b>E-mail</b></label>
    <input type="text" name="email" value="<%=rs.getString(2)%>"<%=rs.getString(2)%></input>
    <br/>
    <br/>
    <label for="username"><b>Username</b></label>
    <input type="text" readonly="readonly" name="username" value="<%=rs.getString(3)%>"<%=rs.getString(3)%></input>
    <br/>
    <br/>
    <label for="fullname"><b>Full Name</b></label>
    <input type="text" name="fullname" value="<%=rs.getString(4)%>"<%=rs.getString(4)%></input>
    <br/>
    <br/>   
    <label for="dob"><b>Date of Birth</b></label>
    <input type="date" name="dob" value="<%=rs.getString(11)%>"<%=rs.getString(11)%></input>    
    <br/>
    <br/>
    <label for="password"><b>Password</b></label>
    <input type="text" name="password" value="<%=rs.getString(5)%>"<%=rs.getString(5)%></input>
</p>

<%
}
catch(SQLException sqe)
{ 
out.println(sqe);
}
%>
        <input type="submit" value="Update"/>
</body>
</form>
</html>