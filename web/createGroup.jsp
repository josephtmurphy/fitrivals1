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
        <title>Create Group</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h2>Create a group</h2>
        <p>Here, you can create your own FitRivals group.</p>
        <p>Just enter a name for the group, your own name/nickname within the group, and the focus of the group.</p>
        <p>DISTANCE groups: the focus is to cover the most distance in  your group.</p>
        <p>SCORE groups: here, you can design your own points/scoring system based on exercise activities.</p>
        <p>TIME groups: the focus is to spend the most time exercising in  your group.</p>
        <br/>
        <p>Please enter the details below:</p>
        <form action="createGroup" method="post">
            <pre>
            <input type="text" name="groupname" placeholder="Group Name"/>
            <br/>
            <input type="text" name="name" value="${user.fullname}" readonly="readonly"/>

            <select name="grouptype1" id="grouptype">
            <option>Distance</option>
            <option>Score</option>
            <option>Time</option>
            </select>

            <input type="submit" value="Create Group"/>

            </pre>
        </form>
    </body>
</html>
