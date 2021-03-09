<%-- 
    Document   : groupHome
    Created on : 04-Mar-2021, 13:03:08
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
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/homePage_css.css" rel="stylesheet">
        <title>Groups</title>
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
        </div>        
    </head>

        <div id="allgroups">
        <br/>
        <div id="create" class="groupdiv" style="background-color: lightblue;">
        <form action="createGroup" method="post">
            <pre>
            <h2>Create a group here:</h2>
            <label for="groupname"><b>Group Name:</b></label>
            <input type="text" name="groupname" placeholder="Group Name"/>
            <label for="name"><b>Username:</b></label>
            <input type="text" name="name" value="${user.username}" readonly="readonly"/>      
            <label for="grouptype"><b>Group Type:</b></label>
            <select name="grouptype1" id="grouptype">
            <option>Distance</option>
            <option>Time</option>
            </select>
            <a class="btn btn-light btn-xl js-scroll-trigger" role="button" type="submit">Create Group</a>
            </pre>
        </form>            
        </div>
               
            
        <div id="join" class="groupdiv" style="background-color: pink;">
        <form action="joinGroup" method="post">
            <pre>
            <h2>Join a group here:</h2>
            <label for="groupname"><b>Group Name:</b></label>
            <input type="text" name="groupname" placeholder="Group Name"/>
            <label for="name"><b>Username:</b></label>
            <input type="text" name="name" value="${user.username}" readonly="readonly"/>
            <label for="grouptype"><b>Group Type:</b></label>
            <select name="grouptype1" id="grouptype">
            <option>Distance</option>
            <option>Time</option>
            </select>
            <input type="submit" value="Join"/>
            </pre>
        </form>                
        </div>
            
        <div id="view" class="groupdiv" style="background-color: yellowgreen;">
        <form action="viewGroups" method="POST">
            <pre>
            <h2>View your groups here:</h2>
            <input type="text" name="name12" value="${user.username}" readonly="readonly"/>
<%   
    try {
    dbcon db = new dbcon();
    Connection con = db.getCon();
    String username = request.getParameter("loggedname");
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM group_members WHERE username = '" + username + "';");
%>
            <label for="groupname"><b>Select Group:</b></label>
            <select name="groupname" id="groupname">
<%
while(rs.next()) {
String groupname = rs.getString(3); 
%>
            <option value="<%=groupname %>"><%=groupname %></option>
<%
}
%>
            </select>
<%
}
catch(SQLException sqe)
{ 
out.println(sqe);
}
%>
            <input type="submit" value="View"/>
            </pre>
            </div>
        </form>                
        </div>
        <div id="score" class="biggroupdiv" style="background-color: blueviolet;">
                        <h2>Create a "score" group here:</h2>
            <div class="dscore">
        <form action="createScoreGroup" method="post">
            <pre>
            <%--insert the values you want your group to weigh towards different activities--%>
            <h3>Score Group - Distance</h3>
            <input type="text" name="groupname" placeholder="Group Name"/>
            <br/>
            <input type="text" name="name" value="${user.username}" readonly="readonly"/>
            <br/>
            <input type="number" name="run_points" placeholder="Points per KM Ran"/>
            <br/>
            <input type="number" name="cycle_points" placeholder="Points per KM Cycled"/>
            <br/>
            <input type="number" name="walk_points" placeholder="Points per KM Walked"/>
            <br/>
            <input type="submit" value="Create Group"/>
            <br/>
            </pre>
        </form>
            </div>            
            <div class="dscore">
        <form action="createScoreGroup2" method="post">
            <pre>
            <h3>Score Group - Time</h3>
            <input type="text" name="groupname" placeholder="Group Name"/>
            <br/>
            <input type="text" name="name" value="${user.username}" readonly="readonly"/>
            <br/>
            <input type="number" name="run_points" placeholder="Points per minute Ran"/>
            <br/>
            <input type="number" name="cycle_points" placeholder="Points per minute Cycled"/>
            <br/>
            <input type="number" name="walk_points" placeholder="Points per minute Walked"/>
            <br/>
            <input type="number" name="strength_points" placeholder="Points per minute Strength"/>
            <br/>
            <input type="submit" value="Create Group"/>
            </pre>
        </form>            
            </div>
        </div>                    
            
    </body>
</html>
