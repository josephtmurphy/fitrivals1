<%-- 
    Document   : welcomeHome
    Created on : 04-Mar-2021, 13:02:27
    Author     : josep
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link href="css/homePage_css.css" rel="stylesheet">
                  
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
</div>
</head>
    
        <div id ="welcome">
            <h2>Welcome to FitRivals!</h2>
            <p>Our goal is to help with your training.</p>
        </div>
        <form action="groupHome.jsp">
        <div id="groups21">
            <h2>Groups</h2>
            <p>Here you can access our group capabilities.</p>
            <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
            <input type="submit" value="Go"/>
        </div>
        </form>
        <form action="activityHome.jsp">
        <div id="activities">
            <h2>Activities</h2>
            <p>Here you can log a training session or a comment in a group.</p>
            <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
            <input type="submit" href="activityHome.jsp" value="Go"/>
        </div>
        </form>
        <div id="blog">
            <h2>Blog</h2>
            <p>Here you can view our community blog.</p>
        </div>
        <div id="user">
            <h2>Your Account</h2>
            <p>Here you can view and update your own statistics.</p>
        </div>
    </body>
</html>
