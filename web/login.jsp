<%-- 
    Document   : login
    Created on : 29-Dec-2020, 11:44:06
    Author     : josep
--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>FitRivals - Log In</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css">
</head>

<body id="page-top">
    <div style="text-align: center; background-color: lightsalmon;">
        <br/>
        <br/>
        <br/>
        <i class="fa fa-heartbeat fa-4x text-primary mb-3 sr-icons" data-aos="fade" data-aos-duration="200" data-aos-delay="600" data-aos-once="true"></i><i class="fa fa-bicycle fa-4x black mb-3 sr-icons" data-aos="fade" data-aos-duration="200" data-aos-delay="600" data-aos-once="true"></i> <i class="fa fa-bar-chart-o fa-4x text-primary mb-3 sr-icons" data-aos="fade" data-aos-duration="200" data-aos-delay="600" data-aos-once="true"></i>
        <h1 class="text-uppercase"><strong>Welcome to FitRivals!</strong></h1>
        <h2 class="text-uppercase text-primary"><strong>Login here</strong></h2>
        <form action="UserLoginServlet" method="post">
            <label for="email">Email Address:</label>
            <input name="email" size="30" />
            <br><br>
            <label for="password">Password:</label>
            <input type="password" name="password" size="30" />
            <br>${message}
            <br>          
            <button type="submit">Login</button>
            <br/>
            <br/>
            <h3 class="text-uppercase" style="color: white;"><strong>Or create an account at the link below...</strong></h3>
            <a href="createUser.jsp">Create an account</a>
            
        </form>
            <br/>
            <br/>
            <br/>
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