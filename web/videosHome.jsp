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
        <%--link to css stylesheet, template retrieved from Bootstrap studio, name "Creative--%>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>FitRivals - Video Blog</title>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic">
        <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css">
    </head>

    <%--navbar code with links to specific sections of the homepage--%>
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
        <div id="videodiv">
            <br/>
            <a href="submitBlog.jsp">Submit a Blog</a>
            <%
                //establishes connection to SQL database and fetches relevant user information   
                try {
                    dbcon db = new dbcon();
                    Connection con = db.getCon();
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM blog_submissions WHERE blog_type = 'YouTube Video' ORDER BY score DESC;;");
            %>        

            <%
                //prints all the video type entries from user submissions
                while (rs.next()) {
                    out.println("<form action=\"votingSystem\" method=\"post\">");
                    //begins to create a HTML string which will form the basis for the video blog
                    String str = "<div class=\"row\">"
                            + "<div class=\"leftcolumn\">"
                            + "<div class=\"card\">";

                    //result set items are the items from the SQL table containing blog submissions
                    str += "<input hidden type=\"text\" name=\"post_id\" value=" + rs.getInt(1) + " readonly=\"readonly\"/><h3><strong>" + rs.getString(4) + "</strong></h3><h6>" + rs.getString(3) + "</h6>" + rs.getString(6) + "<br/><p>" + rs.getString(5) + "</p><p><i>Community Score: </i>" + rs.getInt(7) + ".</p>";
            %>

            <%--this is here for session handling--%>
            <input hidden type="text" name="name" value="${user.username}" readonly="readonly"/>

            <%
                    //prints upvote and downvote buttons
                    str += "<input id=\"upvote\" type=\"submit\" name=\"upvote\" value=\"Upvote\"/><input id=\"downvote\" type=\"submit\" name=\"downvote\" value=\"Downvote\"/>"
                            + "</div>"
                            + "</div>"
                            + "</div>";
                    out.println(str);

                    out.println("</form>");
                }

                out.println("<br/>");
                out.println("<a href=\"videosHome.jsp\">Return to videos</a>");

                con.close();
            %>


            <%
                } catch (SQLException sqe) {
                    out.println(sqe);
                }
            %>
        </div>

        <%--retrieves javascript code, also from bootstrap studio--%>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/bs-init.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js"></script>
        <script src="assets/js/creative.js"></script>
    </body>
</html>
