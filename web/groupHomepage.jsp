<%-- 
    Document   : groupHomepage
    Created on : 09-Mar-2021, 16:42:20
    Author     : josep
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="Database.dbcon"%>
<%@page import="java.sql.SQLException"%>
<%@ page import="java.sql.*;" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <%--link to css stylesheet, template retrieved from Bootstrap studio, name "Creative--%>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>FitRivals - Groups</title>
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
        
        <%--header with background image, retrieved from bootstrap studio sample programs--%>
        <header class="text-center text-white d-flex masthead" style="background-image:url('images/gymgroup2.jpg');">
            <div class="container my-auto">
                <div class="row">
                    <div class="col-lg-10 mx-auto">
                        <h1 class="text-uppercase"><strong>Group Homepage</strong></h1>
                        <hr>
                    </div>
                </div>
                <div class="col-lg-8 mx-auto">
                    <p class="text-faded mb-5">Here you can create, join and view groups.</p><a class="btn btn-primary btn-xl js-scroll-trigger" role="button" href="#services">Find Out More</a>
                </div>
            </div>
        </header>
        
        <%--create a group syntax--%>
        <section class="bg-primary" id="about">
            <div class="container">
                <div class="row">
                    <div class="col offset-lg-8 text-center mx-auto">
                        <i class="fa fa-plus fa-4x black mb-3 sr-icons" data-aos="zoom-in" data-aos-duration="200" data-aos-once="true"></i>
                        <h2 class="text-white section-heading">Create A Group</h2>
                        <hr class="light my-4">
                        <p class="text-faded mb-4">To create a group, entire your desired group name below, and then select what criteria you want it to be ranked by (i.e., total distance covered, total time spent on activities).</p>
                        <form action="createGroup">
                            <label class="text-faded mb-4" for="groupname">Group name:</label>
                            <input type="text" name="groupname" id="groupname" placeholder="groupname"/>
                            <br/>
                            <br/>
                            <label class="text-faded mb-4" for="username">Username:</label>
                            <input type="text" name="name" value="${user.username}" readonly/>
                            <br/>
                            <br/>
                            <label class="text-faded mb-4" for="grouptype1">Group type:</label>
                            <select name="grouptype1" id="grouptype">
                                <option>Distance</option>
                                <option>Time</option>
                            </select>
                            <br/>
                            <br/>
                            <input class="btn btn-light btn-xl js-scroll-trigger" role="button" type="submit" value="Create Group"/>
                        </form>

                    </div>
                </div>
            </div>
        </section>
                            
        <%--create a "distance" score group--%>
        <section style="background-color: lightseagreen;" id="about">
            <div class="container">
                <div class="row">
                    <div class="col offset-lg-8 text-center mx-auto">
                        <i class="fa fa-plus fa-4x black mb-3 sr-icons" data-aos="zoom-in" data-aos-duration="200" data-aos-once="true"></i>
                        <h2 class="text-white section-heading">Create A "Score" Group: <strong>Distance</strong></h2>
                        <hr class="light my-4">
                        <p class="text-faded mb-4">To create a group, entire your desired group name below, and then select what criteria you want it to be ranked by (i.e., total distance covered, total time spent on activities).</p>
                        <form action="createScoreGroup">
                            <label for="groupname">Group name:</label>
                            <input type="text" name="groupname" id="groupname" placeholder="groupname"/>
                            <br/>
                            <br/>
                            <label for="name">Username:</label>
                            <input type="text" name="name" value="${user.username}" readonly/>
                            <br/>
                            <br/>
                            <label for="run_points">Points per KM ran:</label>
                            <input type="number" name="run_points" placeholder="Points per KM ran"/>
                            <br/>
                            <label for="cycle_points">Points per KM cycled:</label>
                            <input type="number" name="cycle_points" placeholder="Points per KM cycled"/>
                            <br/>
                            <label for="walk_points">Points per KM walked:</label>
                            <input type="number" name="walk_points" placeholder="Points per KM walked"/>
                            <br/>
                            <br/>
                            <input class="btn btn-light btn-xl js-scroll-trigger" role="button" type="submit" value="Create Group"/>
                        </form>

                    </div>
                </div>
            </div>
        </section>
                            
        <%--create a "time" score group--%>                    
        <section style="background-color: orchid;" id="about">
            <div class="container">
                <div class="row">
                    <div class="col offset-lg-8 text-center mx-auto">
                        <i class="fa fa-plus fa-4x black mb-3 sr-icons" data-aos="zoom-in" data-aos-duration="200" data-aos-once="true"></i>
                        <h2 class="text-white section-heading">Create A "Score" Group: <strong>Time</strong></h2>
                        <hr class="light my-4">
                        <p class="text-faded mb-4">To create a group, entire your desired group name below, and then select what criteria you want it to be ranked by (i.e., total distance covered, total time spent on activities).</p>
                        <form action="createScoreGroup2">
                            <label for="groupname">Group name:</label>
                            <input type="text" name="groupname" id="groupname" placeholder="groupname"/>
                            <br/>
                            <br/>
                            <label for="name">Username:</label>
                            <input type="text" name="name" value="${user.username}" readonly/>
                            <br/>
                            <br/>
                            <label for="run_points">Points per min ran:</label>
                            <input type="number" name="run_points" placeholder="Points per min ran"/>
                            <br/>
                            <label for="cycle_points">Points per min cycled:</label>
                            <input type="number" name="cycle_points" placeholder="Points per min cycled"/>
                            <br/>
                            <label for="walk_points">Points per min walked:</label>
                            <input type="number" name="walk_points" placeholder="Points per min walked"/>
                            <br/>
                            <br/>
                            <label for="strength_points">Points per min strength training:</label>
                            <input type="number" name="strength_points" placeholder="Points per min strength"/>
                            <br/>
                            <br/>                        
                            <input class="btn btn-light btn-xl js-scroll-trigger" role="button" type="submit" value="Create Group"/>
                        </form>
                    </div>
                </div>
            </div>
        </section>                     
                            
        <%--join group syntax--%>                    
        <section id="services">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <i class="fa fa-user-plus fa-4x black mb-3 sr-icons" data-aos="zoom-in" data-aos-duration="200" data-aos-once="true"></i>
                        <h2 class="text-black section-heading">Join A Group</h2>
                        <hr class="my-4">
                        <p class="mb-5">To join a group, enter the name of the group you wish to join below, and then select what criteria which it is ranked by (i.e., total distance covered, total time spent on activities).</p>
                        <form action="joinGroup">
                            <label for="groupname">Group name:</label>
                            <input type="text" name="groupname" id="groupname" placeholder="groupname"/>
                            <br/>
                            <br/>
                            <label for="username">Username:</label>
                            <input type="text" name="name" value="${user.username}" readonly/>
                            <br/>
                            <br/>
                            <input class="btn btn-dark btn-xl js-scroll-trigger" role="button" type="submit" value="Join Group"/>
                        </form>
                    </div>
                </div>
            </div>
        </section>
                            
        <%--view groups syntax--%>                    
        <section class="text-white bg-dark">
            <div class="container text-center">
                <i class="fa fa-users fa-4x black mb-3 sr-icons" data-aos="zoom-in" data-aos-duration="200" data-aos-once="true"></i>
                <h2 class="mb-4">View your Groups</h2>
                <form action="viewGroups">
                    <p>To see the current standings in your groups, select a group from the dropdown below!</p>
                    <input type="text" hidden name="name12" value="${user.username}" readonly="readonly"/>
                    <%
                        //java code to show groups that the user is a member of in a dropdown
                        try {
                            dbcon db = new dbcon();
                            Connection con = db.getCon();
                            String username = request.getParameter("loggedname");
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("SELECT * FROM group_members WHERE username = '" + username + "';");
                    %>
                    <label for="groupname"><b>Select Group:</b></label>
                    <br/>
                    <select name="groupname" id="groupname">
                        <%
                            while (rs.next()) {
                                String groupname = rs.getString(3);
                        %>
                        <option value="<%=groupname%>"><%=groupname%></option>
                        <%
                            }
                        %>
                    </select>
                    <%
                        } catch (SQLException sqe) {
                            out.println(sqe);
                        }
                    %>
                    <br/>
                    <br/>
                    <input class="btn btn-light btn-xl js-scroll-trigger" role="button" type="submit" value="View Group"/>
                </form>
            </div>
        </section>    
                    
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