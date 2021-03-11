
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
        <title>Home - Brand</title>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic">
        <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css">
    </head>
    <body id="page-top">
    <nav class="navbar navbar-light navbar-expand-lg fixed-top" id="mainNav">
        <div class="container"><a class="navbar-brand js-scroll-trigger" href="#page-top">fitrivals</a><button data-toggle="collapse" data-target="#navbarResponsive" class="navbar-toggler navbar-toggler-right" type="button" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><i class="fa fa-align-justify"></i></button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"><a class="nav-link js-scroll-trigger" href="#about">Groups</a></li>
                    <li class="nav-item"><a class="nav-link js-scroll-trigger" href="#services">Blog</a></li>
                    <li class="nav-item"><a class="nav-link js-scroll-trigger" href="#portfolio">Activity</a></li>
                    <li class="nav-item"><a class="nav-link js-scroll-trigger" href="#contact">Contact</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <form action="updatePhysique" method="post">

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
            } catch (SQLException sqe) {
                out.println(sqe);
            }
        %>
        <%--applies changes and activates updatePhysique servlet--%>       
        <input type="submit" value="Update"/>
        
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/bs-init.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js"></script>
        <script src="assets/js/creative.js"></script>
    </body>
</form>
</html>