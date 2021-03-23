<%-- 
    Document   : submitBlog
    Created on : 20-Feb-2021, 13:18:35
    Author     : josep
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>FitRivals - Submit Blog</title>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic">
        <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css">
    </head>

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
    
    <div id="submitdiv">
        <body>
            <form action="submitBlog" method="post">
                <%--session handling--%>  
                <br/>
                <input type="text" name="name" value="${user.username}" readonly="readonly" hidden/>


                <h2>FitRivals Blog Submission</h2>

                <p>Here, you can submit a personal post to be published on the FitRivals Community Blog - detailing any tips and tricks
                    you have picked up, any success stories of your wins within FitRivals, or your personal success - we'd love to hear from you,
                    and so would everyone else in the community.</p>

                <p>Also, there is the option to submit a YouTube video for publication on FitRivals Community - be it a video you found helpful
                    with your training, a vlog-type video you made yourself, or something inspiring or motivating, why not share it with the other
                    members of the FitRivals Community.</p>

                <p>**IMPORTANT** for submitting a YouTube clip, click "Share" on the video, click "Embed", and paste the whole link in the 
                    "YouTube URL" box below. There is a tutorial available <a href="https://support.google.com/youtube/answer/171780?hl=en">here</a>.</p>

                <label for="blog_type"><b>Blog type:</b></label>
                <select name="blog_type" id="blog_type">
                    <option>Personal Blog Post</option>
                    <option>YouTube Video</option>
                </select>        

                <br/>
                <br/>

                <%--All details to be inserted into SQL table for blog posts--%> 
                <label for="blog_title"><b>Post title:</b></label>
                <input type="text" name="blog_title" placeholder="Post Title"/>

                <br/>
                <br/>

                <div id="textareastuff">
                    <label for="blog_content"><b>Your blog/video description:</b></label>
                    <textarea placeholder="Blog Content/Video Description" name="blog_content" cols="50" rows="10"></textarea>
                </div>

                <br/>

                <label for="youtube_url"><b>YouTube Embed URL:</b></label>
                <input type="text" name="youtube_url" placeholder="YouTube embed link"/>

                <br/>
                <br/>

                <input type="submit" value="Submit Blog"/>

            </form>
        </body>
    </div>
                
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/bs-init.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js"></script>
    <script src="assets/js/creative.js"></script>
</html>
