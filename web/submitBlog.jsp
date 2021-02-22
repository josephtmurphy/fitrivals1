<%-- 
    Document   : submitBlog
    Created on : 20-Feb-2021, 13:18:35
    Author     : josep
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="submitBlog" method="post">
        <input type="text" name="name" value="${user.username}" readonly="readonly"/>
        <br/>
        <br/>
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
        <label for="blog_title"><b>Post title:</b></label>
        <input type="text" name="blog_title" placeholder="Post Title"/>
        <br/>
        <br/>
        <label for="blog_content"><b>Your blog/video description:</b></label>
        <textarea placeholder="Blog Content/Video Description" name="blog_content" cols="50" rows="12"></textarea>
        <br/>
        <br/>
        <label for="youtube_url"><b>YouTube Embed URL:</b></label>
        <input type="text" name="youtube_url" placeholder="YouTube embed link"/>
        <br/>
        <br/>
        <input type="submit" value="Submit Blog"/>
        </form>
    </body>
</html>
