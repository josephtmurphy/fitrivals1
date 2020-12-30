<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%><%-- 
    Document   : createStrength
    Created on : 04-Dec-2020, 09:22:40
    Author     : josep
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
        <form action="createStrength" method="post">
            <pre>
            <input type="text" name="groupname" placeholder="Group Name"/>
            <br/>
            <input type="text" name="name" value="${user.fullname}" readonly="readonly"/>
            <select name="grouptype1" id="grouptype">
            <option>Score</option>
            <option>Time</option>
            </select>

            <h3>Muscle Group Worked:</h3>
            <select name="muscleGroup1" id="muscleGroup1">
            <option>Arms</option>
            <option>Back</option>
            <option>Chest</option>
            <option>Legs</option>
            <option>Shoulders</option>
            <option>Full Body</option>
            </select>

            <h3>Secondary Muscle Group Worked:</h3>
            <select name="muscleGroup2" id="muscleGroup2">
            <option></option>
            <option>Arms</option>
            <option>Back</option>
            <option>Chest</option>
            <option>Legs</option>
            <option>Shoulders</option>
            </select>
            
            <h3>Activity Details</h3>
            <input type="number" name="time" placeholder="Time (mins)"/>

            <input type="text" name="comment" placeholder="Your comment on this activity..."/>

            <input type="submit" value="Log Activity"/>

            </pre>
        </form>
</html>
