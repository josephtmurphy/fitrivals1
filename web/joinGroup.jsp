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
        <title>Join Group</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h2>Join a group</h2>
        <p>Here, you can join an existing FitRivals group.</p>
        <p>Just enter the group's name, your own name/nickname within the group, and the focus of the group.</p>        
        <form action="joinGroup" method="post">
            <pre>
            <input type="text" name="groupname" placeholder="Group Name"/>
            <br/>
            <input type="text" name="name" value="${user.fullname}" readonly="readonly"/>
            <br/>
            <select name="grouptype" id="grouptype">
            <option>Distance</option>
            <option>Score</option>
            <option>Time</option>
            </select>

            <input type="submit" value="Join"/>
            </pre>
        </form>
    </body>
</html>
