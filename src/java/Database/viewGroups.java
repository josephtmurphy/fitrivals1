package Database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Database.dbcon;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author josep
 */
public class viewGroups extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param str
     * @param request servlet request
     * @param response servlet response
     * @return
     */
    //helps to differentiate between comments and activities in the log when they are being printed, taken from https://www.programiz.com/java-programming/examples/string-empty-null
    public static boolean isNullOrEmpty(String str) {
        if (str != null && !str.isEmpty()) {
            return false;
        }
        return true;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //connecting to our db
        dbcon db = new dbcon();
        Connection con = db.getCon();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //gets the data from the viewGroup html file
        String groupname = request.getParameter("groupname");
        String username = request.getParameter("name12");

        //DISTANCE if statmement
        if (groupname.startsWith("d_")) {
            try {

                //applies bootstrap css
                String cssTag = "<link rel=\"stylesheet\" href=\"assets/bootstrap/css/bootstrap.min.css\">\n"
                        + "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800\">\n"
                        + "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic\">\n"
                        + "    <link rel=\"stylesheet\" href=\"assets/fonts/font-awesome.min.css\">\n"
                        + "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css\">\n"
                        + "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css\">";
                out.println("<html>");
                out.println("<head><title>View " + groupname + "</title>" + cssTag + "</head>");
                out.println("<div style=\"backgroundcolor: hotpink;\"");
                out.println("<body style=\"backgroundcolor: hotpink;\">");

                //prints nav bar
                String str0 = ("    <nav class=\"navbar navbar-dark navbar-expand-lg fixed-top\" id=\"mainNav\">\n"
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
                        + "    </nav>    <header class=\"text-center text-white\" style=\"background-color: hotpink;\">\n"
                        + "        <div class=\"container my-auto\">\n"
                        + "            <div class=\"row\">\n"
                        + "                <div class=\"col-lg-10 mx-auto\">\n");

                //SQL statement to get the data from the group's mySQL table
                String sql = "Select user_id,name,distance from " + groupname + " ORDER BY distance desc;";

                //1. HTML code to create a table to display the group data [LEADERBOARD]
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                String str1 = "<table id=\"groups\" border=1><tr><th>Position</th><th>Name</th><th>Total Distance (km)</th></tr>";

                //prints table
                int i = 1;
                str1 += ("<br/><br/><br/><h1 class=\"text-uppercase\"><strong>Current standings in " + groupname + "</strong></h1>");
                while (rs.next()) {
                    str1 += "<tr><td>" + i + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td></tr>";
                    i++;
                }
                str1 += "</table>";

                //HTML code to create a table to display the group data [CARDIO ACTIVITY]
                //SQL and HTML syntax to create the activity log table
                Statement stmt2 = con.createStatement();
                ResultSet rs2 = stmt2.executeQuery("Select name,activity,log_distance,log_time,log_comment,date from " + groupname + "_log WHERE activity IS NOT NULL;");
                String str2 = "<table id=\"groups\" border=1><tr><th>Name</th><th>Activity</th><th>Date</th><th>Distance (km)</th><th>Time (mins)</th><th>Comment</th></tr>";
                str2 += ("<br/><h1 class=\"text-uppercase\"><strong>" + groupname + " cardio activities</strong></h1>");

                //loop that decides whether data is an activity or a comment, and prints it
                while (rs2.next()) {
                    str2 += "<tr><td>" + rs2.getString(1) + "</td><td>" + rs2.getString(2) + "</td><td>" + rs2.getString(6) + "</td><td>" + rs2.getString(3) + "</td><td>" + rs2.getString(4) + "</td><td>" + rs2.getString(5) + "</td></tr>";
                }

                str2 += "</table>";

                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////           
                //SQL and HTML syntax to create the comments [FORUM]
                Statement stmt3 = con.createStatement();
                ResultSet rs3 = stmt3.executeQuery("Select * from " + groupname + "_log WHERE activity IS NULL;");
                String str3 = "<table id=\"groups\" border=1><tr><th>Name</th><th>Comment</th></tr>";
                str3 += ("<br/><br/><h1 class=\"text-uppercase\"><strong>" + groupname + " comments and forum</strong></h1>");

                //loop that decides whether data is an activity or a comment, and prints it
                while (rs3.next()) {
                    str3 += "<tr><td>" + rs3.getString(2) + "</td><td>" + rs3.getString(7) + "</td></tr>";
                }

                str3 += "</table>";

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
                String strLeave = "<form action=\"leaveGroup\">"
                        + "<input hidden type=\"text\" name=\"groupname\" value=\"" + groupname + "\" readonly=\"readonly\"/>"
                        + "<input hidden type=\"text\" name=\"name\" value=\"" + username + "\" readonly=\"readonly\"/>"
                        + "<br/><input type=\"submit\" value=\"Leave Group\"/>"
                        + "</form>";

                String str4
                        = "               <br/><br/><br/> </div>" + "            </div>"
                        + "        </div>"
                        + "    </header>";

                //prints javascript tags
                String str5 = "    <script src=\"assets/js/jquery.min.js\"></script>"
                        + "    <script src=\"assets/bootstrap/js/bootstrap.min.js\"></script>"
                        + "    <script src=\"assets/js/bs-init.js\"></script>"
                        + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.js\"></script>"
                        + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js\"></script>"
                        + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js\"></script>"
                        + "    <script src=\"assets/js/creative.js\"></script>";

                //puts all strings together (e.g. navbar, leaderboard, activity summary, comments) before printing
                String str = str0 + str1 + str2 + str3 + strLeave + str4 + str5;
                out.println(str);

                //option to return home
                out.println("<a href=\"frHomepage.jsp\">Return home</a>");

                out.println("</body>");
                out.println("</div>");

                con.close();

            } catch (Exception e) {
                System.err.println(e);
            }
        }

        //DISTANCE-SCORE if statmement
        if (groupname.startsWith("ds")) {
            try {

                //prints css
                String cssTag = "<link rel=\"stylesheet\" href=\"assets/bootstrap/css/bootstrap.min.css\">\n"
                        + "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800\">\n"
                        + "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic\">\n"
                        + "    <link rel=\"stylesheet\" href=\"assets/fonts/font-awesome.min.css\">\n"
                        + "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css\">\n"
                        + "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css\">";
                out.println("<html>");
                out.println("<head><title>View " + groupname + "</title>" + cssTag + "</head>");
                out.println("<div style=\"backgroundcolor: hotpink;\"");
                out.println("<body style=\"backgroundcolor: hotpink;\">");

                //prints the nav bar
                String str0 = ("    <nav class=\"navbar navbar-dark navbar-expand-lg fixed-top\" id=\"mainNav\">\n"
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
                        + "    </nav>    <header class=\"text-center text-white\" style=\"background-color: hotpink;\">\n"
                        + "        <div class=\"container my-auto\">\n"
                        + "            <div class=\"row\">\n"
                        + "                <div class=\"col-lg-10 mx-auto\">\n");

                //selects details of group's scoring system
                String scoring = "Select groupname,run_points,cycle_points,walk_points from distance_scoring_systems WHERE groupname = '" + groupname + "';";

                //selects key/legend summarising the groups scoring system
                Statement scoreStmt = con.createStatement();
                ResultSet scoreRs = scoreStmt.executeQuery(scoring);
                String scoreString = "<br/><br/><br/><h1 class=\"text-uppercase\"><strong>" + groupname + ": key/legend</strong></h1><table id=\"groupskey\" border=1><tr><th>Group Name</th><th>Points per km Ran</th><th>Points per km Cycled</th><th>Points per km Walked</th></tr>";

                scoreRs.next();
                //creates table which acts as a key/legend for the group's scorecard
                scoreString += "<tr><td>" + scoreRs.getString(1) + "</td><td>" + scoreRs.getString(2) + "</td><td>" + scoreRs.getString(3) + "</td><td>" + scoreRs.getString(4) + "</td></tr>";
                scoreString += "</table>";

                //SQL statement to get the data from the group's mySQL table
                String sql = "Select user_id,name,score from " + groupname + " ORDER BY score desc;";

                //1. HTML code to create a table to display the group data [LEADERBOARD]
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                String str1 = "<br/><h1 class=\"text-uppercase\"><strong>Current standings in " + groupname + "</strong></h1><table id=\"groups\" border=1><tr><th>Position</th><th>Name</th><th>Total Score</th></tr>";

                //prints table
                int i = 1;
                while (rs.next()) {
                    str1 += "<tr><td>" + i + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td></tr>";
                    i++;
                }
                str1 += "</table>";

                //HTML code to create a table to display the group data [CARDIO ACTIVITY]
                //SQL and HTML syntax to create the activity log table
                Statement stmt2 = con.createStatement();
                ResultSet rs2 = stmt2.executeQuery("Select name,activity,log_distance,log_time,log_comment,log_score,date from " + groupname + "_log WHERE log_distance IS NOT NULL;");
                String str2 = "<br/><h1 class=\"text-uppercase\"><strong>" + groupname + " cardio activities</strong></h1><table id=\"groups\" border=1><tr><th>Name</th><th>Activity</th><th>Date</th><th>Distance (km)</th><th>Time (mins)</th><th>Score</th><th>Comment</th></tr>";

                //loop that decides whether data is an activity or a comment, and prints it
                while (rs2.next()) {
                    str2 += "<tr><td>" + rs2.getString(1) + "</td><td>" + rs2.getString(2) + "</td><td>" + rs2.getString(7) + "</td><td>" + rs2.getString(3) + "</td><td>" + rs2.getString(4) + "</td><td>" + rs2.getString(6) + "</td><td>" + rs2.getString(5) + "</td></tr>";
                }

                str2 += "</table>";

                //SQL and HTML syntax to create the comments [FORUM]
                Statement stmt3 = con.createStatement();
                ResultSet rs3 = stmt3.executeQuery("Select * from " + groupname + "_log WHERE activity IS NULL;");
                String str3 = "<br/><h1 class=\"text-uppercase\"><strong>" + groupname + " comments/forum</strong></h1><table id=\"groups\" border=1><tr><th>Name</th><th>Comment</th></tr>";

                //loop that decides whether data is an activity or a comment, and prints it
                while (rs3.next()) {
                    str3 += "<tr><td>" + rs3.getString(2) + "</td><td>" + rs3.getString(10) + "</td></tr>";
                }

                str3 += "</table>";

                String strLeave = "<form action=\"leaveGroup\">"
                        + "<input hidden type=\"text\" name=\"groupname\" value=\"" + groupname + "\" readonly=\"readonly\"/>"
                        + "<input hidden type=\"text\" name=\"name\" value=\"" + username + "\" readonly=\"readonly\"/>"
                        + "<br/><input type=\"submit\" value=\"Leave Group\"/>"
                        + "</form>";

                String str4
                        = "               <br/><br/><br/> </div>" + "            </div>"
                        + "        </div>"
                        + "    </header>";

                //prints javascript code
                String str5 = "    <script src=\"assets/js/jquery.min.js\"></script>"
                        + "    <script src=\"assets/bootstrap/js/bootstrap.min.js\"></script>"
                        + "    <script src=\"assets/js/bs-init.js\"></script>"
                        + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.js\"></script>"
                        + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js\"></script>"
                        + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js\"></script>"
                        + "    <script src=\"assets/js/creative.js\"></script>";

                //combines all the strings of HTML (nav bar, key, leaderboard, activity summary, comments) and prints
                String str = str0 + scoreString + str1 + str2 + str3 + strLeave + str4 + str5;
                out.println(str);

                //return to homepage
                out.println("<a href=\"frHomepage.jsp\">Return home</a>");

                out.println("</body>");
                out.println("</div>");

                con.close();

            } catch (Exception e) {
                System.err.println(e);
            }
        }

        //TIME-SCORE if statmement
        if (groupname.startsWith("ts")) {
            try {

                //calls bootstrap css
                String cssTag = "<link rel=\"stylesheet\" href=\"assets/bootstrap/css/bootstrap.min.css\">\n"
                        + "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800\">\n"
                        + "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic\">\n"
                        + "    <link rel=\"stylesheet\" href=\"assets/fonts/font-awesome.min.css\">\n"
                        + "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css\">\n"
                        + "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css\">";
                out.println("<html>");
                out.println("<head><title>View " + groupname + "</title>" + cssTag + "</head>");
                out.println("<div style=\"backgroundcolor: hotpink;\"");
                out.println("<body style=\"backgroundcolor: hotpink;\">");

                //prints nav bar
                String str0 = ("    <nav class=\"navbar navbar-dark navbar-expand-lg fixed-top\" id=\"mainNav\">\n"
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
                        + "    </nav>    <header class=\"text-center text-white\" style=\"background-color: hotpink;\">\n"
                        + "        <div class=\"container my-auto\">\n"
                        + "            <div class=\"row\">\n"
                        + "                <div class=\"col-lg-10 mx-auto\">\n");

                //selects details of group's scoring system
                String scoring = "Select groupname,run_points,cycle_points,walk_points,strength_points from time_scoring_systems WHERE groupname = '" + groupname + "';";

                //selects scoring system from db
                Statement scoreStmt = con.createStatement();
                ResultSet scoreRs = scoreStmt.executeQuery(scoring);
                String scoreString = "<br/><br/><br/><h1 class=\"text-uppercase\"><strong>" + groupname + ": key/legend</strong></h1><table id=\"groupskey\" border=1><tr><th>Group Name</th><th>Points per min Ran</th><th>Points per min Cycled</th><th>Points per min Walked</th><th>Points per min Strength</th></tr>";

                scoreRs.next();
                //creates table which acts as a key/legend for the group's scorecard
                scoreString += "<tr><td>" + scoreRs.getString(1) + "</td><td>" + scoreRs.getString(2) + "</td><td>" + scoreRs.getString(3) + "</td><td>" + scoreRs.getString(4) + "</td><td>" + scoreRs.getString(5) + "</td></tr>";
                scoreString += "</table>";

                //SQL statement to get the data from the group's mySQL table
                String sql = "Select user_id,name,score from " + groupname + " ORDER BY score desc;";

                //1. HTML code to create a table to display the group data [LEADERBOARD]
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                String str1 = "<br/><h1 class=\"text-uppercase\"><strong>Current standings in " + groupname + "</strong></h1><table id=\"groups\" border=1><tr><th>Position</th><th>Name</th><th>Total Score</th></tr>";

                //prints table
                int i = 1;
                while (rs.next()) {
                    str1 += "<tr><td>" + i + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td></tr>";
                    i++;
                }
                str1 += "</table>";

                //HTML code to create a table to display the group data [CARDIO ACTIVITY]
                //SQL and HTML syntax to create the activity log table
                Statement stmt2 = con.createStatement();
                ResultSet rs2 = stmt2.executeQuery("Select name,activity,log_distance,log_time,log_comment,log_score,date from " + groupname + "_log WHERE log_distance IS NOT NULL;");
                String str2 = "<br/><h1 class=\"text-uppercase\"><strong>" + groupname + " cardio activities</strong></h1><table id=\"groups\" border=1><tr><th>Name</th><th>Activity</th><th>Date</th><th>Distance (km)</th><th>Time (mins)</th><th>Score</th><th>Comment</th></tr>";

                //loop that decides whether data is an activity or a comment, and prints it
                while (rs2.next()) {
                    str2 += "<tr><td>" + rs2.getString(1) + "</td><td>" + rs2.getString(2) + "</td><td>" + rs2.getString(7) + "</td><td>" + rs2.getString(3) + "</td><td>" + rs2.getString(4) + "</td><td>" + rs2.getString(6) + "</td><td>" + rs2.getString(5) + "</td></tr>";
                }

                str2 += "</table>";

                //HTML code to create a table to display the group data [STRENGTH]
                //SQL and HTML syntax to create the activity log table
                Statement stmt4 = con.createStatement();
                ResultSet rs4 = stmt4.executeQuery("Select name,activity,log_muscle1,log_muscle2,log_time,log_comment,log_score,date from " + groupname + "_log WHERE log_muscle1 IS NOT NULL;");
                String str4 = "<br/><h1 class=\"text-uppercase\"><strong>" + groupname + " strength activities</strong></h1><table id=\"groups\" border=1><tr><th>Name</th><th>Activity</th><th>Date</th><th>Muscles Worked</th><th>Time (mins)</th><th>Score</th><th>Comment</th></tr>";

                //loop that decides whether data is an activity or a comment, and prints it
                while (rs4.next()) {
                    str4 += "<tr><td>" + rs4.getString(1) + "</td><td>" + rs4.getString(2) + "</td><td>" + rs4.getString(8) + "</td><td>" + rs4.getString(3) + ", " + rs4.getString(4) + "</td><td>" + rs4.getString(5) + "</td><td>" + rs4.getString(7) + "</td><td>" + rs4.getString(6) + "</td></tr>";
                }

                str4 += "</table>";

                //SQL and HTML syntax to create the comments [FORUM]
                Statement stmt3 = con.createStatement();
                ResultSet rs3 = stmt3.executeQuery("Select * from " + groupname + "_log WHERE activity IS NULL;");
                String str3 = "<br/><h1 class=\"text-uppercase\"><strong>" + groupname + " comments/forum</strong></h1><table id=\"groups\" border=1><tr><th>Name</th><th>Comment</th></tr>";

                //loop that decides whether data is an activity or a comment, and prints it
                while (rs3.next()) {
                    str3 += "<tr><td>" + rs3.getString(2) + "</td><td>" + rs3.getString(10) + "</td></tr>";
                }

                str3 += "</table>";

                String strLeave = "<form action=\"leaveGroup\">"
                        + "<input hidden type=\"text\" name=\"groupname\" value=\"" + groupname + "\" readonly=\"readonly\"/>"
                        + "<input hidden type=\"text\" name=\"name\" value=\"" + username + "\" readonly=\"readonly\"/>"
                        + "<br/><input type=\"submit\" value=\"Leave Group\"/>"
                        + "</form>";

                String str5
                        = "               <br/><br/><br/> </div>" + "            </div>"
                        + "        </div>"
                        + "    </header>";

                //javascript code
                String str6 = "    <script src=\"assets/js/jquery.min.js\"></script>"
                        + "    <script src=\"assets/bootstrap/js/bootstrap.min.js\"></script>"
                        + "    <script src=\"assets/js/bs-init.js\"></script>"
                        + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.js\"></script>"
                        + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js\"></script>"
                        + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js\"></script>"
                        + "    <script src=\"assets/js/creative.js\"></script>";

                //combines all strings of different tables/ html syntax and prints 
                String str = str0 + scoreString + str1 + str2 + str4 + str3 + strLeave + str5 + str6;
                out.println(str);

                //return home
                out.println("<a href=\"frHomepage.jsp\">Return home</a>");

                out.println("</body>");
                out.println("</div>");

                con.close();

            } catch (Exception e) {
                System.err.println(e);
            }
        }

        //TIME if statmement
        if (groupname.startsWith("t_")) {
            try {

                //calls bootstrap css
                String cssTag = "<link rel=\"stylesheet\" href=\"assets/bootstrap/css/bootstrap.min.css\">\n"
                        + "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800\">\n"
                        + "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic\">\n"
                        + "    <link rel=\"stylesheet\" href=\"assets/fonts/font-awesome.min.css\">\n"
                        + "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css\">\n"
                        + "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css\">";
                out.println("<html>");
                out.println("<head><title>View " + groupname + "</title>" + cssTag + "</head>");
                out.println("<div style=\"backgroundcolor: hotpink;\"");
                out.println("<body style=\"backgroundcolor: hotpink;\">");

                //navbar code
                String str0 = ("    <nav class=\"navbar navbar-dark navbar-expand-lg fixed-top\" id=\"mainNav\">\n"
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
                        + "    </nav>    <header class=\"text-center text-white\" style=\"background-color: hotpink;\">\n"
                        + "        <div class=\"container my-auto\">\n"
                        + "            <div class=\"row\">\n"
                        + "                <div class=\"col-lg-10 mx-auto\">\n");

                //SQL statement to get the data from the group's mySQL table
                String sql = "Select user_id,name,time from " + groupname + " ORDER BY time desc;";

                //1. HTML code to create a table to display the group data [LEADERBOARD]
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                String str1 = "<br/><br/><br/><h1 class=\"text-uppercase\"><strong>Current standings in " + groupname + "</strong></h1><table id=\"groups\" border=1><tr><th>Position</th><th>Name</th><th>Total Mins</th></tr>";

                //prints table
                int i = 1;
                while (rs.next()) {
                    str1 += "<tr><td>" + i + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td></tr>";
                    i++;
                }
                str1 += "</table>";

                //HTML code to create a table to display the group data [CARDIO ACTIVITY]
                //SQL and HTML syntax to create the activity log table
                Statement stmt2 = con.createStatement();
                ResultSet rs2 = stmt2.executeQuery("Select name,activity,log_distance,log_time,log_comment,date from " + groupname + "_log WHERE log_distance IS NOT NULL;");
                String str2 = "<br/><h1 class=\"text-uppercase\"><strong>" + groupname + " cardio activities</strong></h1><table id=\"groups\" border=1><tr><th>Name</th><th>Activity</th><th>Date</th><th>Distance (km)</th><th>Time (mins)</th><th>Comment</th></tr>";

                //loop that decides whether data is an activity or a comment, and prints it
                while (rs2.next()) {
                    str2 += "<tr><td>" + rs2.getString(1) + "</td><td>" + rs2.getString(2) + "</td><td>" + rs2.getString(6) + "</td><td>" + rs2.getString(3) + "</td><td>" + rs2.getString(4) + "</td><td>" + rs2.getString(5) + "</td></tr>";
                }

                str2 += "</table>";

                //HTML code to create a table to display the group data [STRENGTH]
                //SQL and HTML syntax to create the activity log table
                Statement stmt4 = con.createStatement();
                ResultSet rs4 = stmt4.executeQuery("Select name,activity,log_muscle1,log_muscle2,log_time,log_comment,date from " + groupname + "_log WHERE log_muscle1 IS NOT NULL;");
                String str4 = "<br/><h1 class=\"text-uppercase\"><strong>" + groupname + " strength activities</strong></h1><table id=\"groups\" border=1><tr><th>Name</th><th>Activity</th><th>Date</th><th>Muscles Worked</th><th>Time (mins)</th><th>Comment</th></tr>";

                //loop that decides whether data is an activity or a comment, and prints it
                while (rs4.next()) {
                    str4 += "<tr><td>" + rs4.getString(1) + "</td><td>" + rs4.getString(2) + "</td><td>" + rs4.getString(7) + "</td><td>" + rs4.getString(3) + ", " + rs4.getString(4) + "</td><td>" + rs4.getString(5) + "</td><td>" + rs4.getString(6) + "</td></tr>";
                }

                str4 += "</table>";

                //SQL and HTML syntax to create the comments [FORUM]
                Statement stmt3 = con.createStatement();
                ResultSet rs3 = stmt3.executeQuery("Select * from " + groupname + "_log WHERE activity IS NULL;");
                String str3 = "<br/><h1 class=\"text-uppercase\"><strong>" + groupname + " comments/forum</strong></h1><table id=\"groups\" border=1><tr><th>Name</th><th>Comment</th></tr>";

                //loop that decides whether data is an activity or a comment, and prints it
                while (rs3.next()) {
                    str3 += "<tr><td>" + rs3.getString(2) + "</td><td>" + rs3.getString(10) + "</td></tr>";
                }

                str3 += "</table>";

                String strLeave = "<form action=\"leaveGroup\">"
                        + "<input hidden type=\"text\" name=\"groupname\" value=\"" + groupname + "\" readonly=\"readonly\"/>"
                        + "<input hidden type=\"text\" name=\"name\" value=\"" + username + "\" readonly=\"readonly\"/>"
                        + "<br/><input type=\"submit\" value=\"Leave Group\"/>"
                        + "</form>";

                String str5
                        = "               <br/><br/><br/> </div>" + "            </div>"
                        + "        </div>"
                        + "    </header>";

                //javascript code
                String str6 = "    <script src=\"assets/js/jquery.min.js\"></script>"
                        + "    <script src=\"assets/bootstrap/js/bootstrap.min.js\"></script>"
                        + "    <script src=\"assets/js/bs-init.js\"></script>"
                        + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.js\"></script>"
                        + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js\"></script>"
                        + "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js\"></script>"
                        + "    <script src=\"assets/js/creative.js\"></script>";

                //combines all strings and html syntax before printing
                String str = str0 + str1 + str2 + str4 + str3 + strLeave + str5 + str6;
                out.println(str);

                //return home
                out.println("<a href=\"frHomepage.jsp\">Return home</a>");

                out.println("</body>");
                out.println("</div>");

                con.close();

            } catch (Exception e) {
                System.err.println(e);
            }
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
