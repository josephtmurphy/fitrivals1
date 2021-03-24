
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*;" %>
<%@ page import="Database.dbcon;" %>
<%@ page import="Session.UserDAO;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>FitRivals - Account</title>
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
        <div style="background-color: palegreen; padding: 10px; padding-left: 50px;">
            <form action="updateAccount" method="post">
            <%--Fetches username to handle session--%>
        <br/>
        <br/>
        <%--obtains username to handle session--%>
        <input type="text" name="name12" value="${user.username}" readonly="readonly"/>
        <br/>
        <br/>
            <%
                //establishes connection to SQL database and fetches relevant user information
                try {
                    dbcon db = new dbcon();
                    Connection con = db.getCon();
                    String username = request.getParameter("loggedname");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + username + "';");
                    rs.next();
            %>
            <%--Fetches and displays user's account details, makes them available to edit--%>
            <p><i>Enter your desired changes to your account information in the boxes below, and click "update" to save.</i></p>
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
            <%--submit button allows you to save changes by activating updateAccount servlet--%>
            <%
                } catch (SQLException sqe) {
                    out.println(sqe);
                }
            %>
            <input type="submit" value="Update"/>
            <br/>
            <br/>
            <br/>
            <script src="assets/js/jquery.min.js"></script>
            <script src="assets/bootstrap/js/bootstrap.min.js"></script>
            <script src="assets/js/bs-init.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js"></script>
            <script src="assets/js/creative.js"></script>
        </body>
    </form>
            </div>
</html>