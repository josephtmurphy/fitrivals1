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
    </head>
    
    <body>
        <h2>FitRivals Home</h2>
        <input type="text" name="loggedname" value="Welcome ${user.username}" readonly="readonly"/>
        <br/>
        <br/>
        <b>Please select an action:</b>
             
        <p>Group actions:</p>        
        
        <a href="createGroup.jsp">Create a group</a>
        
        <br/>
        <br/>
        
        <a href="createScoreGroup.jsp">Create a "score" (points-based) group</a>
        
        <br/>
        <br/>        
        
        <a href="joinGroup.jsp">Join an existing group</a>
        
        <br/>
        <br/>          
        
        <form action="testdropdown.jsp">
        <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
        <input type="submit" value="View Groups"/>
        </form>          
        
        <br/>
        <br/>
           
        <p>Activity actions:</p>        
          
        <form action="createCardio.jsp">
        <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
        <input type="submit" value="Log Cardio Activity"/>
        </form>         
        
        <br/>
        
        <form action="createStrength.jsp">
        <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
        <input type="submit" value="Log Strength Activity"/>
        </form> 
        
        <br/>          
        
        <form action="logScoreActivity.jsp">
        <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
        <input type="submit" value="Log Score Activity"/>
        </form> 
        
        <br/>        
        
        <form action="writeComment.jsp">
        <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
        <input type="submit" value="Write Comment"/>
        </form> 
                
        <br/>     
        
        <p>Your profile:</p>             
        
        <form action="userDetails.jsp">
        <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
        <input type="submit" value="User Details"/>
        
        <br/>          
        <br/>             
        
        <a href="<%=request.getContextPath()%>/UserLogoutServlet">Logout</a>        
        
        <br/>          
        <br/>       
        
    </form>    
    </body>
</html>
