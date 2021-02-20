<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>FitRivals - Home</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link href="css/homePage_css.css" rel="stylesheet">
    </head>
    
    <body>
        
        <ul>
            <li><a href="default.asp">Home</a></li>
            <li><a href="submitBlog.jsp">Write Blog</a></li>
            <li><a href="blogHome.jsp">Blog</a></li>
            <li><a href="<%=request.getContextPath()%>/UserLogoutServlet">Logout</a></li>
        </ul>
        
        <br/>
      
        <h2>FitRivals Home</h2>
        <input type="text" name="loggedname" value="Welcome ${user.username}" readonly="readonly"/>
        <br/>
        <br/>
        <b>Please select an action:</b>
        
        <div class="homepagedivs">
            
        <p class="font1">Group actions:</p>        
        
        <a href="createGroup.jsp">Create Group</a>
        
        <br/>
        
        <a href="createScoreGroup.jsp">Create Score Group</a>
        
        <br/>        
        
        <a href="joinGroup.jsp">Join Group</a>
        
        <br/>       
        
        <form action="testdropdown.jsp">
        <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
        <input class="button1" type="submit" value="View Groups"/>
        </form>          
        
        <br/>            
        
        </div>
        
        <br/>
        <br/>
        
        <div class="homepagedivs">
        
        <p class="font1">Activity actions:</p>        
          
        <form action="createCardio.jsp">
        <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
        <input class="button2" type="submit" value="Log Cardio Activity"/>
        </form>         
        
        <br/>
        
        <form action="createStrength.jsp">
        <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
        <input class="button2" type="submit" value="Log Strength Activity"/>
        </form> 
        
        <br/>          
        
        <form action="writeComment.jsp">
        <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
        <input class="button2" type="submit" value="Write Comment"/>
        </form> 
                
        <br/>     
        
        </div>
        
        <br/>
        
        <div class="homepagedivs">
        
        <p class="font1">Your profile:</p>             
        
        <form action="updateAccount.jsp">
        <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
        <input class="button3" type="submit" value="Account Details"/>
        </form>
        
        <br/>
        
        <form action="updatePhysique.jsp">
        <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
        <input class="button3" type="submit" value="Update Physique"/>
        </form>
        
        <br/>
        
        <form action="userActivities">
        <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
        <input class="button3" type="submit" value="Your Activity Summary"/>
        </form>        
        
        <br/>            
        
        </div>
        
        <br/>          
        <br/>             
        
        <a href="<%=request.getContextPath()%>/UserLogoutServlet">Logout</a>        
        
        <br/>          
        <br/>       
        
    </form>    
    </body>
</html>
