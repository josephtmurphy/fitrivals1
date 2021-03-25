<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>FitRivals - Create User</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/createUser_css.css" rel="stylesheet">
    </head>
    <body>
        <form action="createUser" method="post">
            <div style="background-color: peachpuff" class="container">
                <h1>Create a FitRivals account</h1>
                <p>Fill in the boxes below to sign up to FitRivals - you may leave the physical statistics blank if you wish.</p>
                <hr>

                <label for="fullname"><b>Full Name</b></label>
                <input type="text" placeholder="Enter Name" name="fullname">

                <label for="username"><b>Username</b></label>
                <input type="text" placeholder="Enter Name" name="username">

                <label for="email"><b>E-mail Address</b></label>
                <input type="text" placeholder="Enter E-mail" name="email" id="email" required>

                <label for="height"><b>Height (cm)</b></label>
                <input type="text" placeholder="Enter Height" name="height" id="height">  

                <label for="weight"><b>Weight (lbs)</b></label>
                <input type="text" placeholder="Enter Weight" name="weight" id="weight"> 

                <label for="thigh"><b>Thigh (cm)</b></label>
                <input type="text" placeholder="Enter Thigh Measurement" name="thigh" id="thigh"> 

                <label for="bicep"><b>Bicep (cm)</b></label>
                <input type="text" placeholder="Enter Bicep Measurement" name="bicep" id="bicep"> 

                <label for="waist"><b>Waist (cm)</b></label>
                <input type="text" placeholder="Enter Waist Measurement" name="waist" id="waist">     

                <label for="dob"><b>Date of Birth</b></label>
                <br/>
                <input type="date" placeholder="Enter DOB" name="dob" id="dob" required> 
                <br/>
                <br/>

                <label for="password"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" id="password" required>
                <hr>

                <label for="psw-repeat"><b>Repeat Password</b></label>
                <input type="password" placeholder="Repeat Password" name="password-repeat" id="password-repeat" required>
                <hr>

                <button type="submit" class="registerbtn">Register</button>
            </div>

            <div class="container signin">
                <p>Already have an account? <a href="login.jsp">Sign in</a>.</p>
            </div>
        </form>
    </body>
</html>
