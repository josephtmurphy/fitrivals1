<%-- 
    Document   : videosHome
    Created on : 22-Feb-2021, 15:30:36
    Author     : josep
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Database.dbcon"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/blogCss.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
<div class="header">
  <h2>FitRivals - PLANS AND VIDEOS</h2>
</div>

<%   
    //establishes connection to SQL database and fetches relevant user information   
    try {
    dbcon db = new dbcon();
    Connection con = db.getCon();
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM blog_submissions WHERE blog_type = 'YouTube Video';"); 
%>        
        
<%     
    while(rs.next()) {
        String str = "<div class=\"row\">"
            + "<div class=\"leftcolumn\">"
            + "<div class=\"card\">";
        
        str+= "<h2>"+rs.getString(4)+"</h2><h5>"+rs.getString(3)+"</h5>"+rs.getString(6)+"<p>"+rs.getString(5)+"</p>";

        str += "</div>"
                + "</div>"
                + "</div>";
    out.println(str);
    }
        
            out.println("<br/>");     
            out.println("<a href=\"homepage.jsp\">Return home</a>");
            
            con.close();
%>
        

<%
    
} catch(SQLException sqe)
{ 
out.println(sqe);
}
%>

<div class="footer">
  <h2>Footer</h2>
</div>
    </body>
</html>
