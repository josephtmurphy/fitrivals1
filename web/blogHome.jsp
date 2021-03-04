<%-- 
    Document   : blogHome
    Created on : 16-Feb-2021, 16:10:40
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

    <br/>        
        
    <div class="header">
        <h2>FitRivals Community Blog</h2>
    </div>

<%   
    //establishes connection to SQL database and fetches relevant user information   
    try {
    dbcon db = new dbcon();
    Connection con = db.getCon();
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM blog_submissions WHERE blog_type = 'Personal Blog Post' ORDER BY score DESC;"); 
%>

<%     
    while(rs.next()) {
        
        //prints the HTML code to display a blog made up of details from the SQL database
        out.println("<form action=\"votingSystem\" method=\"post\">");
        String str = "<div class=\"row\">"
            + "<div class=\"leftcolumn\">"
            + "<div class=\"card\">";
        
        str+= "<input type=\"text\" name=\"post_id\" value="+rs.getInt(1)+" readonly=\"readonly\"/><h2>"+rs.getString(4)+"</h2><h5>"+rs.getString(3)+"</h5><p>"+rs.getString(5)+"</p><p>Community Score: "+rs.getInt(7)+".</p>";
                    %>
                    
        <%--session handling--%> 
        <input hidden type="text" name="name" value="${user.username}" readonly="readonly"/>
        
            <%
        str += "</div>"
                + "</div>"
                + "</div>";
    
        out.println(str);
        out.println("<input type=\"submit\" name=\"upvote\" value=\"Upvote\"/>");
        out.println("<input type=\"submit\" name=\"downvote\" value=\"Downvote\"/>");
        out.println("</form>");        
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

    </body>
</html>