<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import= "Database.dbcon;" %>

<%-- 
    Document   : joinGroup
    Created on : 25-Nov-2020, 17:32:18
    Author     : josep
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body
        <%
                Statement statement = null;
                ResultSet resultSet = null;
        %>
        
    <h2 align="center"><font><strong>Retrieve data from database in jsp</strong></font></h2>
    <table align="center" cellpadding="5" cellspacing="5" border="1">
    <tr>
        
    </tr>
    <tr bgcolor="#A52A2A">
    <td><b>id</b></td>
    <td><b>Name</b></td>
    <td><b>Distance</b></td>
    </tr>
    
    <%
        dbcon db = new dbcon();
        Connection con = db.getCon();
        
        try{ 
        statement = con.createStatement();
        String sql ="SELECT * FROM d_guigang2020";

        resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
    %>

    <tr bgcolor="#DEB887">

        <td><%=resultSet.getString("user_id") %></td>
        <td><%=resultSet.getString("name") %></td>
        <td><%=resultSet.getString("distance") %></td>

    </tr>

<% 
}

} catch (Exception e) {
e.printStackTrace();
}
%>
</table>
    </body>
</html>
