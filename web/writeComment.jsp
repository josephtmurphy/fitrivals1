
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*;" %>
<%@ page import="Database.dbcon;" %>
<%@ page import="Session.UserDAO;" %>
<%-- 
    Document   : writeComment
    Created on : 02-Dec-2020, 14:03:39
    Author     : josep
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Write Comment</title>
    </head>
    <body>
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
    </body>
</html>
