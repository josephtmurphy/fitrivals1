<%-- 
    Document   : activityHomepage
    Created on : 09-Mar-2021, 20:07:42
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
            <div class="container"><a class="navbar-brand js-scroll-trigger" href="frHomepage.jsp">fitrivals</a><button data-toggle="collapse" data-target="#navbarResponsive" class="navbar-toggler navbar-toggler-right" type="button" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><i class="fa fa-align-justify"></i></button>
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
        <header class="text-center text-white d-flex masthead" style="background-image:url('images/cardiobackground2.jpg');">
            <div class="container my-auto">
                <div class="row">
                    <div class="col-lg-10 mx-auto">
                        <h1 class="text-uppercase"><strong>Activity Homepage</strong></h1>
                        <hr>
                    </div>
                </div>
                <div class="col-lg-8 mx-auto">
                    <p class="text-faded mb-5">Here you can record your workouts. Whether it be a run, cycle, walk or a strength training session, all you have to do is enter the details of the workout and choose a group to write it to.</p><a class="btn btn-primary btn-xl js-scroll-trigger" role="button" href="#services">Find Out More</a>
                </div>
            </div>
        </header>
        <section class="bg-primary" id="about">
            <div class="container">
                <div class="row">
                    <div class="col offset-lg-8 text-center mx-auto">
                        <i class="fa fa-bicycle fa-4x black mb-3 sr-icons" data-aos="zoom-in" data-aos-duration="200" data-aos-once="true"></i>
                        <h2 class="text-white section-heading">Cardio Workouts</h2>         
                        <hr class="light my-4">
                        <p class="text-faded mb-4">Select the group you wish to write the activity to, and then enter a distance (km), time (minutes), and a comment to be displayed in your group.</p>
                        <form action="createCardio">
                            <%
                                try {
                                    dbcon db = new dbcon();
                                    Connection con = db.getCon();
                                    String username = request.getParameter("loggedname");
                                    Statement stmt = con.createStatement();
                                    ResultSet rs = stmt.executeQuery("SELECT * FROM group_members WHERE username = '" + username + "';");
                            %>   
                            <label for="groupname">Group name:</label>
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
                            <br/>
                            <br/>
                            <label for="name">Username:</label>
                            <input type="text" name="name" value="${user.username}" readonly/>
                            <br/>
                            <br/>
                            <label for="activityType">Activity Type:</label>
                            <select name="activityType" id="activitytype">
                                <option>Run</option>
                                <option>Walk</option>
                                <option>Cycle</option>
                            </select>        
                            <br/>
                            <br/>
                            <label for="distance">Distance (km):</label>
                            <input type="number" name="distance" placeholder="Distance (km)"/>   
                            <br/>
                            <br/>
                            <label for="time">Time (mins):</label>
                            <input type="number" name="time" placeholder="Time (mins)"/>
                            <br/>
                            <br/>            
                            <label for="comment">Your comment on this activity:</label>
                            <input type="text" name="comment" placeholder="Your comment..."/>
                            <br/>
                            <br/>            
                            <input class="btn btn-light btn-xl js-scroll-trigger" role="button" type="submit" value="Log Activity"/>
                            <%
                                } catch (SQLException sqe) {
                                    out.println(sqe);
                                }
                            %>
                        </form>

                    </div>
                </div>
            </div>
        </section>
        <section id="services">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <i class="fa fa-heartbeat fa-4x black mb-3 sr-icons" data-aos="zoom-in" data-aos-duration="200" data-aos-once="true"></i>
                        <h2 class="text-black section-heading">Strength Workouts</h2>
                        <hr class="my-4">
                        <p class="mb-5">Select the group you wish to write the activity to, then choose the muscle groups you trained, time (minutes) for the duration of the session, and a comment to be displayed in your group.</p>
                        <form action="createStrength">
                            <%
                                try {
                                    dbcon db = new dbcon();
                                    Connection con = db.getCon();
                                    String username = request.getParameter("loggedname");
                                    Statement stmt = con.createStatement();
                                    ResultSet rs = stmt.executeQuery("SELECT * FROM group_members WHERE username = '" + username + "';");
                            %>
                            <label for="groupname">Group Name:</label>
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
                            <br/>
                            <br/>
                            <label for="username">Username:</label>
                            <input type="text" value="${user.username}" readonly/>
                            <br/>
                            <br/>
                            <label for="muscleGroup1">Muscle Group Worked:</label>
                            <select name="muscleGroup1" id="muscleGroup1">
                                <option>Arms</option>
                                <option>Back</option>
                                <option>Chest</option>
                                <option>Legs</option>
                                <option>Shoulders</option>
                                <option>Full Body</option>
                            </select>
                            <br/>
                            <br/>
                            <label for="muscleGroup2">Secondary Muscle Group Worked:</label>
                            <select name="muscleGroup2" id="muscleGroup2">
                                <option></option>
                                <option>Arms</option>
                                <option>Back</option>
                                <option>Chest</option>
                                <option>Legs</option>
                                <option>Shoulders</option>
                            </select>      
                            <br/>
                            <br/>
                            <label for="time"><b>Time (mins):</b></label>
                            <input type="number" name="time" placeholder="Time (mins)"/>      
                            <br/>
                            <br/>
                            <label for="comment"><b>Your comment on this activity:</b></label>
                            <input type="text" name="comment" placeholder="Your comment..."/>
                            <br/>
                            <br/>
                            <%} catch (SQLException sqe) {
                                    out.println(sqe);
                                } %>
                            <input class="btn btn-light btn-xl js-scroll-trigger" role="button" type="submit" value="Log Activity"/>
                        </form>
                    </div>
                </div>
            </div>
            <div class="container">

            </div>
        </section>
        <section class="text-white bg-dark">
            <div class="container text-center">
                <i class="fa fa-commenting fa-4x black mb-3 sr-icons" data-aos="zoom-in" data-aos-duration="200" data-aos-once="true"></i>
                <h2 class="mb-4">Write a Comment</h2>
                <p>Write a comment within a group - why not egg on a fellow group member with some support or banter?</p>
                <form action="writeComment">
                    <%
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
                    <label for="name12">Username:</label>
                    <input type="text" name="name12" value="${user.username}" readonly="readonly"/>
                    <br/>
                    <br/>
                    <label for="comment"><b>Your comment:</b></label>
                    <input type="text" name="comment" placeholder="Your comment..."/>
                    <br/>
                    <br/>          
                    <input class="btn btn-light btn-xl js-scroll-trigger" role="button" type="submit" value="Log Activity"/>
                    <input type="submit" value="go"/>
                </form>
            </div>
        </section>    
        <section id="contact">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 text-center mx-auto">
                        <h2 class="section-heading">Let's Get In Touch!</h2>
                        <hr class="my-4">
                        <p class="mb-5">Ready to start your next project with us? That's great! Give us a call or send us an email and we will get back to you as soon as possible!</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-4 text-center ml-auto"><i class="fa fa-phone fa-3x mb-3 sr-contact" data-aos="zoom-in" data-aos-duration="300" data-aos-once="true"></i>
                        <p>123-456-6789</p>
                    </div>
                    <div class="col-lg-4 text-center mr-auto"><i class="fa fa-envelope-o fa-3x mb-3 sr-contact" data-aos="zoom-in" data-aos-duration="300" data-aos-delay="300" data-aos-once="true"></i>
                        <p><a href="mailto:your-email@your-domain.com">email@example.com</a></p>
                    </div>
                </div>
            </div>
        </section>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/bs-init.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js"></script>
        <script src="assets/js/creative.js"></script>
    </body>

</html>