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
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>FitRivals - Blog</title>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic">
        <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css">
    </head>

    <body id="page-top">
        <nav class="navbar navbar-light navbar-expand-lg fixed-top" id="mainNav">
            <div class="container"><a class="navbar-brand js-scroll-trigger" href="frHomepage.jsp">fitrivals</a><button data-toggle="collapse" data-target="#navbarResponsive" class="navbar-toggler navbar-toggler-right" type="button" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><i class="fa fa-align-justify"></i></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="frHomepage.jsp#groups">Groups</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="frHomepage.jsp#blog">Blog</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="frHomepage.jsp#activity">Activity</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="frHomepage.jsp#myaccount">My Account</a></li>
                        <li class="nav-item"><form action="UserLogoutServlet"><input type="submit" style="  background: none!important;border: none;color:crimson;font-weight: bold;display:block;padding:.5rem 1rem" value="LOG OUT"></button></form></li>
                    </ul>
                </div>
            </div>
        </nav> 

        <div class="header">
            <br/>
            <h2 style="color: white;"><strong>FitRivals - Community Blog</strong></h2>
        </div>
        <div id="videodiv">
            <a href="submitBlog.jsp">Submit a Blog</a>
            <%
                //establishes connection to SQL database and fetches relevant user information   
                try {
                    dbcon db = new dbcon();
                    Connection con = db.getCon();
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM blog_submissions WHERE blog_type = 'Personal Blog Post' ORDER BY score DESC;");
            %>

            <%
                while (rs.next()) {

                    //prints the HTML code to display a blog made up of details from the SQL database
                    out.println("<form action=\"votingSystem\" method=\"post\">");
                    String str = "<div class=\"row\">"
                            + "<div class=\"leftcolumn\">"
                            + "<div class=\"card\">";

                    str += "<input type=\"text\" hidden name=\"post_id\" value=" + rs.getInt(1) + " readonly=\"readonly\"/><h3><strong>" + rs.getString(4) + "</strong></h3><h6>" + rs.getString(3) + "</h6><br/><p>" + rs.getString(5) + "</p><p><i>Community Score: </i>" + rs.getInt(7) + ".</p>";
            %>

            <%--session handling--%> 
            <input hidden type="text" name="name" value="${user.username}" readonly="readonly"/>

            <%
                    str += "<input id=\"upvote\" type=\"submit\" name=\"upvote\" value=\"Upvote\"/><input id=\"downvote\" type=\"submit\" name=\"downvote\" value=\"Downvote\"/>"
                            + "</div>"
                            + "</div>"
                            + "</div>";
                    out.println(str);

                    out.println("</form>");
                }

                out.println("<br/>");
                out.println("<a href=\"homepage.jsp\">Return home</a>");

                con.close();
            %>


            <%
                } catch (SQLException sqe) {
                    out.println(sqe);
                }
            %>
        </div>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/bs-init.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js"></script>
        <script src="assets/js/creative.js"></script>
    </body>
</html>
