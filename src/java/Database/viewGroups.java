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
    
    //helps to differentiate between comments and activities in the log when they are being printed
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //decides the nature of the group's "goal"
        String groupType = request.getParameter("grouptype");
        
        //connecting to our db
        dbcon db = new dbcon();
        Connection con = db.getCon();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        //gets the data from the viewGroup html file
        String groupname = request.getParameter("groupname");        
        
        //DISTANCE if statmement
        
        if (groupname.startsWith("d_")) {
        try {            
          
        String cssTag="<link href=\"css/homePage_css.css\" rel=\"stylesheet\">";
        out.println("<html>");
        out.println("<head><title>Title Name</title>"+cssTag+"</head>");
        out.println("<body>");            
            
            //SQL statement to get the data from the group's mySQL table
            String sql = "Select user_id,name,distance from "+groupname + " ORDER BY distance desc;";
            
            //1. HTML code to create a table to display the group data [LEADERBOARD]
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            String str = "<table id=\"groups\" border=1><tr><th>Position</th><th>Name</th><th>Total Distance (km)</th></tr>";
            
            //prints table
            int i = 1;
            out.println("Current standings in "+groupname);
            while(rs.next()) {
                str+= "<tr><td>"+i+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>";
                i++;
            }
            str += "</table>";
            out.println(str);
          
            out.println("<br/>");

            
            //HTML code to create a table to display the group data [CARDIO ACTIVITY]
            out.println(groupname + " cardio activities");
            //SQL and HTML syntax to create the activity log table
            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("Select name,activity,log_distance,log_time,log_comment from "+groupname+"_log WHERE activity IS NOT NULL;");
            String str2 = "<table id=\"groups\" border=1><tr><th>Name</th><th>Activity</th><th>Distance (km)</th><th>Time (mins)</th><th>Comment</th></tr>";
            
            //loop that decides whether data is an activity or a comment, and prints it
            while(rs2.next()) { 
                str2 += "<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(3)+"</td><td>"+rs2.getString(4)+"</td><td>"+rs2.getString(5)+"</td></tr>";
                }

            str2 += "</table>";
            out.println(str2);
            
            out.println("<br/>");
            
           
            out.println(groupname + " comments/forum");
            //SQL and HTML syntax to create the comments [FORUM]
            Statement stmt3 = con.createStatement();
            ResultSet rs3 = stmt3.executeQuery("Select * from "+groupname+"_log WHERE activity IS NULL;");
            String str3 = "<table id=\"groups\" border=1><tr><th>Name</th><th>Comment</th></tr>";
            
            //loop that decides whether data is an activity or a comment, and prints it
            while(rs3.next()) { 
                str3 += "<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(6)+"</td></tr>";
                }

            str3 += "</table>";
            out.println(str3);
            
            out.println("<br/>");     
            out.println("<a href=\"homepage.jsp\">Return home</a>");
            
            con.close();
            
        } catch(Exception e) {
            System.err.println(e);
        }
        }

        //DISTANCE-SCORE if statmement
        
        if (groupname.startsWith("ds")) {
        try {            
            
            //selects details of group's scoring system
            String scoring = "Select groupname,run_points,cycle_points,walk_points from distance_scoring_systems WHERE groupname = '"+groupname+"';";
            
            Statement scoreStmt = con.createStatement();
            ResultSet scoreRs = scoreStmt.executeQuery(scoring);
            String scoreString = "<table border=1><tr><th>Group Name</th><th>Points per km Ran</th><th>Points per km Cycled</th><th>Points per km Walked</th></tr>";
            
            scoreRs.next();
            //creates table which acts as a key/legend for the group's scorecard
            scoreString+= "<tr><td>"+scoreRs.getString(1)+"</td><td>"+scoreRs.getString(2)+"</td><td>"+scoreRs.getString(3)+"</td><td>"+scoreRs.getString(4)+"</td></tr>";
            scoreString += "</table>";      
            
            out.println(scoreString);
            out.println("<br/>");
            
            //SQL statement to get the data from the group's mySQL table
            String sql = "Select user_id,name,score from "+groupname + " ORDER BY score desc;";
            
            //1. HTML code to create a table to display the group data [LEADERBOARD]
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            String str = "<table border=1><tr><th>Position</th><th>Name</th><th>Total Score</th></tr>";
            
            //prints table
            int i = 1;
            out.println("Current standings in "+groupname);
            while(rs.next()) {
                str+= "<tr><td>"+i+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>";
                i++;
            }
            str += "</table>";
            out.println(str);
          
            out.println("<br/>");

            
            //HTML code to create a table to display the group data [CARDIO ACTIVITY]
            out.println(groupname + " cardio activities");
            //SQL and HTML syntax to create the activity log table
            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("Select name,activity,log_distance,log_time,log_comment,log_score from "+groupname+"_log WHERE log_distance IS NOT NULL;");
            String str2 = "<table border=1><tr><th>Name</th><th>Activity</th><th>Distance (km)</th><th>Time (mins)</th><th>Score</th><th>Comment</th></tr>";
            
            //loop that decides whether data is an activity or a comment, and prints it
            while(rs2.next()) { 
                str2 += "<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(3)+"</td><td>"+rs2.getString(4)+"</td><td>"+rs2.getString(6)+"</td><td>"+rs2.getString(5)+"</td></tr>";
                }

            str2 += "</table>";
            out.println(str2);
            
            out.println("<br/>");           
           
            out.println(groupname + " comments/forum");
            //SQL and HTML syntax to create the comments [FORUM]
            Statement stmt3 = con.createStatement();
            ResultSet rs3 = stmt3.executeQuery("Select * from "+groupname+"_log WHERE activity IS NULL;");
            String str3 = "<table border=1><tr><th>Name</th><th>Comment</th></tr>";
            
            //loop that decides whether data is an activity or a comment, and prints it
            while(rs3.next()) { 
                str3 += "<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(9)+"</td></tr>";
                }

            str3 += "</table>";
            out.println(str3);
            
            out.println("<br/>");
            out.println("<a href=\"homepage.jsp\">Return home</a>");
            
            con.close();
            
        } catch(Exception e) {
            System.err.println(e);
        }
        }
        
        //TIME-SCORE if statmement
        
        if (groupname.startsWith("ts")) {
        try {            
            
            //selects details of group's scoring system
            String scoring = "Select groupname,run_points,cycle_points,walk_points,strength_points from time_scoring_systems WHERE groupname = '"+groupname+"';";
            
            Statement scoreStmt = con.createStatement();
            ResultSet scoreRs = scoreStmt.executeQuery(scoring);
            String scoreString = "<table border=1><tr><th>Group Name</th><th>Points per min Ran</th><th>Points per min Cycled</th><th>Points per min Walked</th><th>Points per min Strength</th></tr>";
            
            scoreRs.next();
            //creates table which acts as a key/legend for the group's scorecard
            scoreString+= "<tr><td>"+scoreRs.getString(1)+"</td><td>"+scoreRs.getString(2)+"</td><td>"+scoreRs.getString(3)+"</td><td>"+scoreRs.getString(4)+"</td><td>"+scoreRs.getString(5)+"</td></tr>";
            scoreString += "</table>";      
            
            out.println(scoreString);
            out.println("<br/>");            
            
            //SQL statement to get the data from the group's mySQL table
            String sql = "Select user_id,name,score from "+groupname + " ORDER BY score desc;";
            
            //1. HTML code to create a table to display the group data [LEADERBOARD]
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            String str = "<table border=1><tr><th>Position</th><th>Name</th><th>Total Score</th></tr>";
            
            //prints table
            int i = 1;
            out.println("Current standings in "+groupname);
            while(rs.next()) {
                str+= "<tr><td>"+i+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>";
                i++;
            }
            str += "</table>";
            out.println(str);
          
            out.println("<br/>");

            
            //HTML code to create a table to display the group data [CARDIO ACTIVITY]
            out.println(groupname + " cardio activities");
            //SQL and HTML syntax to create the activity log table
            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("Select name,activity,log_distance,log_time,log_comment,log_score from "+groupname+"_log WHERE log_distance IS NOT NULL;");
            String str2 = "<table border=1><tr><th>Name</th><th>Activity</th><th>Distance (km)</th><th>Time (mins)</th><th>Score</th><th>Comment</th></tr>";
            
            //loop that decides whether data is an activity or a comment, and prints it
            while(rs2.next()) { 
                str2 += "<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(3)+"</td><td>"+rs2.getString(4)+"</td><td>"+rs2.getString(6)+"</td><td>"+rs2.getString(5)+"</td></tr>";
                }

            str2 += "</table>";
            out.println(str2);
            
            out.println("<br/>");
            
            
            //HTML code to create a table to display the group data [STRENGTH]
            out.println(groupname + " strength activities");
            //SQL and HTML syntax to create the activity log table
            Statement stmt4 = con.createStatement();
            ResultSet rs4 = stmt4.executeQuery("Select name,activity,log_muscle1,log_muscle2,log_time,log_comment,log_score from "+groupname+"_log WHERE log_muscle1 IS NOT NULL;");
            String str4 = "<table border=1><tr><th>Name</th><th>Activity</th><th>Muscles Worked</th><th>Time (mins)</th><th>Score</th><th>Comment</th></tr>";
            
            //loop that decides whether data is an activity or a comment, and prints it
            while(rs4.next()) { 
                str4 += "<tr><td>"+rs4.getString(1)+"</td><td>"+rs4.getString(2)+"</td><td>"+rs4.getString(3)+", "+rs4.getString(4)+"</td><td>"+rs4.getString(5)+"</td><td>"+rs4.getString(7)+"</td><td>"+rs4.getString(6)+"</td></tr>";
                }

            str4 += "</table>";
            out.println(str4);
            
            out.println("<br/>");
            
           
            out.println(groupname + " comments/forum");
            //SQL and HTML syntax to create the comments [FORUM]
            Statement stmt3 = con.createStatement();
            ResultSet rs3 = stmt3.executeQuery("Select * from "+groupname+"_log WHERE activity IS NULL;");
            String str3 = "<table border=1><tr><th>Name</th><th>Comment</th></tr>";
            
            //loop that decides whether data is an activity or a comment, and prints it
            while(rs3.next()) { 
                str3 += "<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(9)+"</td></tr>";
                }

            str3 += "</table>";
            out.println(str3);
            
            out.println("<br/>");
            out.println("<a href=\"homepage.jsp\">Return home</a>");
            
            con.close();
            
        } catch(Exception e) {
            System.err.println(e);
        }
        }
        
        //TIME if statmement
        
        if (groupname.startsWith("t")) {
        try {            
            
            
            //SQL statement to get the data from the group's mySQL table
            String sql = "Select user_id,name,time from "+groupname + " ORDER BY time desc;";
            
            //1. HTML code to create a table to display the group data [LEADERBOARD]
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            String str = "<table border=1><tr><th>Position</th><th>Name</th><th>Total Mins</th></tr>";
            
            //prints table
            int i = 1;
            out.println("Current standings in "+groupname);
            while(rs.next()) {
                str+= "<tr><td>"+i+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>";
                i++;
            }
            str += "</table>";
            out.println(str);
          
            out.println("<br/>");

            
            //HTML code to create a table to display the group data [CARDIO ACTIVITY]
            out.println(groupname + " cardio activities");
            //SQL and HTML syntax to create the activity log table
            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("Select name,activity,log_distance,log_time,log_comment from "+groupname+"_log WHERE log_distance IS NOT NULL;");
            String str2 = "<table border=1><tr><th>Name</th><th>Activity</th><th>Distance (km)</th><th>Time (mins)</th><th>Comment</th></tr>";
            
            //loop that decides whether data is an activity or a comment, and prints it
            while(rs2.next()) { 
                str2 += "<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(3)+"</td><td>"+rs2.getString(4)+"</td><td>"+rs2.getString(5)+"</td></tr>";
                }

            str2 += "</table>";
            out.println(str2);
            
            out.println("<br/>");
            
            
            //HTML code to create a table to display the group data [STRENGTH]
            out.println(groupname + " strength activities");
            //SQL and HTML syntax to create the activity log table
            Statement stmt4 = con.createStatement();
            ResultSet rs4 = stmt4.executeQuery("Select name,activity,log_muscle1,log_muscle2,log_time,log_comment from "+groupname+"_log WHERE log_muscle1 IS NOT NULL;");
            String str4 = "<table border=1><tr><th>Name</th><th>Activity</th><th>Muscles Worked</th><th>Time (mins)</th><th>Comment</th></tr>";
            
            //loop that decides whether data is an activity or a comment, and prints it
            while(rs4.next()) { 
                str4 += "<tr><td>"+rs4.getString(1)+"</td><td>"+rs4.getString(2)+"</td><td>"+rs4.getString(3)+", "+rs4.getString(4)+"</td><td>"+rs4.getString(5)+"</td><td>"+rs4.getString(6)+"</td></tr>";
                }

            str4 += "</table>";
            out.println(str4);
            
            out.println("<br/>");
            
           
            out.println(groupname + " comments/forum");
            //SQL and HTML syntax to create the comments [FORUM]
            Statement stmt3 = con.createStatement();
            ResultSet rs3 = stmt3.executeQuery("Select * from "+groupname+"_log WHERE activity IS NULL;");
            String str3 = "<table border=1><tr><th>Name</th><th>Comment</th></tr>";
            
            //loop that decides whether data is an activity or a comment, and prints it
            while(rs3.next()) { 
                str3 += "<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(9)+"</td></tr>";
                }

            str3 += "</table>";
            out.println(str3);
            
            out.println("<br/>");
            out.println("<a href=\"homepage.jsp\">Return home</a>");
            
            con.close();
            
        } catch(Exception e) {
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
