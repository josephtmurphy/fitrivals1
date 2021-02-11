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
        <style>
.homepagedivs {
    border: 1px dotted black;
    padding: 10px;
}

.font1 {
    font-size: 15px;
    font-family: tahoma;
    color: navy;
    font-weight: bold;
}
</style>
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
        
        <div class="homepagedivs">
            
        <p class="font1">Group actions:</p>        
        
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
        
        </div>
        
        <br/>
        <br/>
        
        <div class="homepagedivs">
        
        <p class="font1">Activity actions:</p>        
          
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
        
        <form action="writeComment.jsp">
        <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
        <input type="submit" value="Write Comment"/>
        </form> 
                
        <br/>     
        
        </div>
        
        <br/>
        
        <div class="homepagedivs">
        
        <p class="font1">Your profile:</p>             
        
        <form action="updateAccount.jsp">
        <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
        <input type="submit" value="Account Details"/>
        </form>
        
        <br/>
        
        <form action="updatePhysique.jsp">
        <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
        <input type="submit" value="Update Physique"/>
        </form>
        
        <br/>
        
        <form action="userActivities">
        <input hidden type="text" name="loggedname" value="${user.username}" readonly="readonly"/>
        <input type="submit" value="Your Activity Summary"/>
        </form>        
        
        </div>
        
        <br/>          
        <br/>             
        
        <a href="<%=request.getContextPath()%>/UserLogoutServlet">Logout</a>        
        
        <br/>          
        <br/>       
        
    </form>    
    </body>
</html>
