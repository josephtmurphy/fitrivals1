<%-- 
    Document   : frHomepage
    Created on : 09-Mar-2021, 14:12:11
    Author     : josep
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
    <header class="text-center text-white d-flex masthead" style="background-image:url('assets/img/header12.jpeg');">
        <div class="container my-auto">
            <div class="row">
                <div class="col-lg-10 mx-auto">
                    <h1 class="text-uppercase"><strong>Welcome to FitRivals!</strong></h1>
                    <hr>
                </div>
            </div>
            <div class="col-lg-8 mx-auto">
                <p class="text-faded mb-5">Our goal is to help you stay motivated and consistent with your exercise by creating an atmosphere of camarederie, competition and community.</p>
                <input type="text" name="loggedname" value="${user.username}" hidden readonly="readonly"/><a class="btn btn-primary btn-xl js-scroll-trigger" role="button" href="#services">Find Out More</a>
            </div>
        </div>
    </header>
    <section class="bg-primary" id="about">
        <div class="container">
            <div class="row">
                <form action="groupHomepage.jsp">
                <div class="col offset-lg-8 text-center mx-auto">
                    <h2 class="text-white section-heading">Better together!</h2>
                    <hr class="light my-4">
                    <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
                    <input type="submit" value="Go"/>
                    <p class="text-faded mb-4">Our application thrives off the group dynamic. Here, you can access multiple group activities: whether you want to create, join, or view the standings in a group, you can do it here!</p><a class="btn btn-light btn-xl js-scroll-trigger" role="button" href="groupHome.jsp">Take me to Groups!</a>
                </div>
                </form>
            </div>
        </div>
    </section>
    <section id="services">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">FitRivals Community</h2>
                    <hr class="my-4">
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-lg-3 text-center">
                    <div class="mx-auto service-box mt-5"><i class="fa fa-globe fa-4x text-primary mb-3 sr-icons" data-aos="zoom-in" data-aos-duration="200" data-aos-once="true"></i>
                        <h3 class="mb-3">Our Community</h3>
                        <p class="text-muted mb-0">We want to cultivate community across all users, not just within private groups.</p>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3 text-center">
                    <div class="mx-auto service-box mt-5"><i class="fa fa-pencil fa-4x text-primary mb-3 sr-icons" data-aos="zoom-in" data-aos-duration="200" data-aos-delay="200" data-aos-once="true"></i>
                        <h3 class="mb-3">FitRivals Blog</h3>
                        <p class="text-muted mb-0">Have any helpful tips or stories that you think are worth sharing? Share them with the FitRivals blog!</p>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3 text-center">
                    <div class="mx-auto service-box mt-5"><i class="fa fa-video-camera fa-4x text-primary mb-3 sr-icons" data-aos="zoom-in" data-aos-duration="200" data-aos-delay="400" data-aos-once="true"></i>
                        <h3 class="mb-3">Videos Hub</h3>
                        <p class="text-muted mb-0">We offer a video hub for YouTube clips that are informational, motivational, or even personal vlogs.</p>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3 text-center">
                    <div class="mx-auto service-box mt-5"><i class="fa fa-thumbs-up fa-4x text-primary mb-3 sr-icons" data-aos="fade" data-aos-duration="200" data-aos-delay="600" data-aos-once="true"></i>
                        <h3 class="mb-3">User Moderated</h3>
                        <p class="text-muted mb-0">Our upvote/downvote system leaves it to you to decide which posts get seen!</p>
                    </div>
                </div>
                <a class="btn btn-light btn-xl js-scroll-trigger" role="button" href="blogHome.jsp">Take me to Blog!</a>
            </div>
        </div>
    </section>
    <section class="text-white bg-dark">
        <form action="activityHomepage.jsp">
        <div class="container text-center">
            <h2 class="mb-4">Want to log a session?</h2>
            <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
            <p>Do it here!</p><a class="btn btn-light btn-xl sr-button" role="button" data-aos="zoom-in" data-aos-duration="400" data-aos-once="true">Log a Workout</a>
            <input type="submit" value="Go"/>
        </div>
        </form>
    </section>    
    <section id="portfolio" class="p-0">
        <div class="container-fluid p-0">
            <div class="row no-gutters popup-gallery">
                <div class="col-sm-6 col-lg-4"><a class="portfolio-box" href="images/runninggroup.jpg"><img class="img-fluid" src="images/runninggroup.jpg" style="height: 300px; width: 100%;">
                        <div class="portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded"><span>Cardio</span></div>
                                <div class="project-name"><span>Log a Cardio Activity</span></div>
                            </div>
                        </div>
                    </a></div>
                <div class="col-sm-6 col-lg-4"><a class="portfolio-box" href="images/gymgroup.jpg"><img class="img-fluid" src="images/gymgroup.jpg" style="height: 300px; width: 100%;">
                        <div class="portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded"><span>Strength</span></div>
                                <div class="project-name"><span>Log a Strength Activity</span></div>
                            </div>
                        </div>
                    </a></div>
                <div class="col-sm-6 col-lg-4"><a class="portfolio-box" href="images/encouragement.jpg"><img class="img-fluid" alt="comment" src="images/encouragement.jpg" style="height: 300px; width: 100%;">
                        <div class="portfolio-box-caption">
                            <div class="portfolio-box-caption-content">
                                <div class="project-category text-faded"><span>Comments</span></div>
                                <div class="project-name"><span>Write a Comment</span></div>
                            </div>
                        </div>
                    </a></div>
            </div>
        </div>
    </section>
    <section id="contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 text-center mx-auto">
                    <h2 class="section-heading">All About You!</h2>
                    <hr class="my-4">
                    <p class="mb-5">You can also view some of your own statistics! You can update body composition and view your updates, as well as a dashboard of your own logged sessions.</p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 text-center ml-auto"><i class="fa fa-bar-chart fa-3x mb-3 sr-contact" data-aos="zoom-in" data-aos-duration="300" data-aos-once="true"></i>
                    <p><a href="updatePhysique.jsp">Update your physical stats</a></p>
                </div>
                <div class="col-lg-4 text-center mr-auto"><i class="fa fa-dashboard fa-3x mb-3 sr-contact" data-aos="zoom-in" data-aos-duration="300" data-aos-delay="300" data-aos-once="true"></i>
                    <p><a href="updateAccount.jsp">View a log of your workout activity and body updates</a></p>
                </div>
                <div class="col-lg-4 text-center mr-auto"><i class="fa fa-cogs fa-3x mb-3 sr-contact" data-aos="zoom-in" data-aos-duration="300" data-aos-delay="300" data-aos-once="true"></i>
                    <p><a href="updateAccount.jsp">Your account information</a></p>
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