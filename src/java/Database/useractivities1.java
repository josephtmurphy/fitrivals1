/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author josep
 */
public class useractivities1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //gets the logged username to handle session
        String username = request.getParameter("loggedname");

        //connecting to our db
        dbcon db = new dbcon();
        Connection con = db.getCon();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {

            String cssTag = "<link rel=\"stylesheet\" href=\"assets/bootstrap/css/bootstrap.min.css\">\n"
                    + "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800\">\n"
                    + "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic\">\n"
                    + "    <link rel=\"stylesheet\" href=\"assets/fonts/font-awesome.min.css\">\n"
                    + "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css\">\n"
                    + "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css\">";
            out.println("<html>");
            out.println("<head><title>Your Activity</title>" + cssTag + "</head>");
            out.println("<div style=\"backgroundcolor: hotpink;\"");
            out.println("<body style=\"backgroundcolor: hotpink;\">");

            String strA = "    <nav class=\"navbar navbar-dark navbar-expand-lg fixed-top\" id=\"mainNav\">\n"
                    + "        <div class=\"container\"><a class=\"navbar-brand js-scroll-trigger\" href=\"frHomepage.jsp\">fitrivals</a><button data-toggle=\"collapse\" data-target=\"#navbarResponsive\" class=\"navbar-toggler navbar-toggler-right\" type=\"button\" aria-controls=\"navbarResponsive\" aria-expanded=\"false\" aria-label=\"Toggle navigation\"><i class=\"fa fa-align-justify\"></i></button>\n"
                    + "            <div class=\"collapse navbar-collapse\" id=\"navbarResponsive\">\n"
                    + "<ul class=\"navbar-nav ml-auto\">\n"
                    + "                        <li class=\"nav-item\"><a class=\"nav-link js-scroll-trigger\" href=\"frHomepage.jsp#groups\">Groups</a></li>\n"
                    + "                        <li class=\"nav-item\"><a class=\"nav-link js-scroll-trigger\" href=\"frHomepage.jsp#blog\">Blog</a></li>\n"
                    + "                        <li class=\"nav-item\"><a class=\"nav-link js-scroll-trigger\" href=\"frHomepage.jsp#activity\">Activity</a></li>\n"
                    + "                        <li class=\"nav-item\"><a class=\"nav-link js-scroll-trigger\" href=\"frHomepage.jsp#myaccount\">My Account</a></li>\n"
                    + "                        <li class=\"nav-item\"><form action=\"UserLogoutServlet\"><input type=\"submit\" style=\"  background: none!important;border: none;color:crimson;font-weight: bold;display:block;padding:.5rem 1rem\" value=\"LOG OUT\"></button></form></li>\n"
                    + "                    </ul>"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "    </nav>    <header class=\"text-center text-white\" style=\"background-color: lightseagreen;\">\n"
                    + "        <div class=\"container my-auto\">\n"
                    + "            <div class=\"row\">\n"
                    + "                <div class=\"col-lg-10 mx-auto\">\n";

            //SQL statement to get the data from the group's mySQL table
            String sql1 = "Select username,user_height,user_weight,user_thigh,user_bicep,user_waist,date,physique_id from user_physique WHERE username ='" + username + "' ORDER BY physique_id asc;";

            //1. HTML code to create a table to display the users physique logs/updates
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql1);
            String str1 = "<br/><br/><br/><h1 class=\"text-uppercase\"><strong>Your physique updates</strong></h1><table id=\"summary\" border=1><tr><th>Name</th><th>Date</th><th>Height (cm)</th><th>Weight (lbs)</th><th>Thigh (cm)</th><th>Bicep (cm)</th><th>Waist (cm)</th></tr>";

            //prints table of physique updates
            while (rs.next()) {
                str1 += "<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(7) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>" + rs.getString(5) + "</td><td>" + rs.getString(6) + "</td></tr>";
            }
            str1 += "</table>";

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
            String sqlweight = "SELECT (SELECT MAX(user_weight) FROM user_physique WHERE username ='" + username + "') - (SELECT MIN(user_weight) FROM user_physique"
                    + " WHERE username ='" + username + "');";

            //1. HTML code to output line with total time spent on strength activities
            ResultSet rsS = stmt.executeQuery(sqlweight);
            rsS.next();
            String str2 = "<br/><p class=\"useractivitybody\">Your total weight lost: " + rsS.getInt(1) + " pounds.</p>";

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////              
            //SQL statement to get the user's activity data
            String sql2 = "Select username,groupname,activity,time,distance,comment,date from all_cardioactivities WHERE username = '" + username + "';";

            //1. HTML code to create a table to display the activity data
            ResultSet rs2 = stmt.executeQuery(sql2);
            String str3 = "<h1 class=\"text-uppercase\"><strong>Your cardio activity</strong></h1><table id=\"summary\" border=1><tr><th>Name</th><th>Group</th><th>Activity Type</th><th>Date</th><th>Distance (km)</th><th>Time (min)</th><th>Comment</th></tr>";

            //prints table
            while (rs2.next()) {
                str3 += "<tr><td>" + rs2.getString(1) + "</td><td>" + rs2.getString(2) + "</td><td>" + rs2.getString(3) + "</td><td>" + rs2.getString(7) + "</td><td>" + rs2.getString(5) + "</td><td>" + rs2.getString(4) + "</td><td>" + rs2.getString(6) + "</td></tr>";
            }
            str3 += "</table>";

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////              
            //SQL statement to get the sum of the distance that the user has covered in their activities
            String sql0 = "SELECT SUM(distance) FROM all_cardioactivities where username = '" + username + "';";

            //prints line with total distance
            ResultSet rs0 = stmt.executeQuery(sql0);
            rs0.next();
            String str4 = "<br/><p class=\"useractivitybody\">Your total distance covered: " + rs0.getInt(1) + "km.</p>";

            //////////////////////////////////////////////////////////////////////////////////
            //calculates total time spent performing cardio activities
            String sql9 = "SELECT SUM(time) FROM all_cardioactivities where username = '" + username + "';";

            //prints line with total time
            ResultSet rs9 = stmt.executeQuery(sql9);
            rs9.next();
            String str5 = ("<p class=\"useractivitybody\">Your total time spent on cardio activity: " + rs9.getInt(1) + " mins.</p>");

            /////////////////////////////////////////////////////////////////////////////////////
            //selects total number of cardio activities logged with the application
            String sql3 = "SELECT COUNT(time) FROM all_cardioactivities where username = '" + username + "';";

            //1. HTML code to print a line which says the total number of activities logged with the application
            ResultSet rs3 = stmt.executeQuery(sql3);
            rs3.next();
            String str6 = "<p class=\"useractivitybody\">Your total cardio sessions: " + rs3.getInt(1) + ".</p>";

            //SQL statement to get the data from the users activity table
            String sql8 = "Select username,groupname,activity,muscles,time,comment,date from all_strengthactivities WHERE username = '" + username + "';";

            //1. HTML code to create a table to display the users strength activity data
            ResultSet rs8 = stmt.executeQuery(sql8);
            String str7 = "<h1 class=\"text-uppercase\"><strong>Your strength activity</strong></h1><table id=\"summary\" border=1><tr><th>Name</th><th>Group</th><th>Activity Type</th><th>Date</th><th>Muscles Worked</th><th>Time (mins)</th><th>Comment</th></tr>";

            //prints table
            while (rs8.next()) {
                str7 += "<tr><td>" + rs8.getString(1) + "</td><td>" + rs8.getString(2) + "</td><td>" + rs8.getString(3) + "</td><td>" + rs8.getString(7) + "</td><td>" + rs8.getString(4) + "</td><td>" + rs8.getString(5) + "</td><td>" + rs8.getString(6) + "</td></tr>";
            }
            str7 += "</table>";

            /////////////////////////////////////////////////////////////////////////////////////
            //selects total amount of time spent on strength activities
            String sql4 = "SELECT SUM(time) FROM all_strengthactivities where username = '" + username + "';";

            //1. HTML code to output line with total time spent on strength activities
            ResultSet rs4 = stmt.executeQuery(sql4);
            rs4.next();
            String str8 = "<br/><p class=\"useractivitybody\">Your total time spent strength training: " + rs4.getInt(1) + " mins.</p>";

            /////////////////////////////////////////////////////////////////////////////////////
            //selects total amount of strength activities logged
            String sql5 = "SELECT COUNT(time) FROM all_strengthactivities where username = '" + username + "';";

            //1. HTML code to output a line describing total amount of strength activities logged
            ResultSet rs5 = stmt.executeQuery(sql5);
            rs5.next();
            String str9 = "<p class=\"useractivitybody\">Your total strength training sessions: " + rs5.getInt(1) + ".</p>";

            String str10
                    = "               <br/><br/> </div>" + "            </div>"
                    + "        </div>"
                    + "    </header>";

            String str11 = "    <script src=\"assets/js/jquery.min.js\"></script>"
                    + "    <script src=\"assets/bootstrap/js/bootstrap.min.js\"></script>"
                    + "    <script src=\"assets/js/bs-init.js\"></script>"
                    + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.js\"></script>"
                    + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js\"></script>"
                    + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js\"></script>"
                    + "    <script src=\"assets/js/creative.js\"></script>";

            String str = strA + str1 + str2 + str3 + str4 + str5 + str6 + str7 + str8 + str9 + str10 + str11;
            out.println(str);

            out.println("<a href=\"frHomepage.jsp\">Return home</a>");

            out.println("</body>");
            out.println("</div>");

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(useractivities1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
