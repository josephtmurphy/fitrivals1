<%-- 
    Document   : createScoreGroup
    Created on : 03-Dec-2020, 09:20:32
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
        <form action="createScoreGroup" method="post">
            <pre>
            <input type="text" name="groupname" placeholder="Group Name"/>
            <br/>
            <input type="text" name="name" placeholder="Your name in this group..."/>
            <br/>
            <input type="number" name="pointsperkm" placeholder="How Many Point Awarded per km"/>
            <br/>
            <input type="number" name="pointspermin" placeholder="How Many Point Awarded per min"/>
            <br/>
            <input type="submit" value="Log Activity"/>

            </pre>
        </form>
    </body>
</html>
