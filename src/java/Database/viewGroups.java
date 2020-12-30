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
        
        if (groupname.startsWith("d")) {
        try {            
            
            
            //SQL statement to get the data from the group's mySQL table
            String sql = "Select user_id,name,distance from "+groupname + " ORDER BY distance desc;";
            
            //1. HTML code to create a table to display the group data [LEADERBOARD]
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            String str = "<table border=1><tr><th>Position</th><th>Name</th><th>Total Distance (km)</th></tr>";
            
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
            String str2 = "<table border=1><tr><th>Name</th><th>Activity</th><th>Distance (km)</th><th>Time (mins)</th><th>Comment</th></tr>";
            
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
            String str3 = "<table border=1><tr><th>Name</th><th>Comment</th></tr>";
            
            //loop that decides whether data is an activity or a comment, and prints it
            while(rs3.next()) { 
                str3 += "<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(6)+"</td></tr>";
                }

            str3 += "</table>";
            out.println(str3);
            
            out.println("<br/>");            
            
            con.close();
            
        } catch(Exception e) {
            System.err.println(e);
        }
        }
        
        //SCORE if statmement
        
        if (groupname.startsWith("s")) {
        try {            
            
            
            //SQL statement to get the data from the group's mySQL table
            String sql = "Select user_id,name,score from s_"+groupname + " ORDER BY score desc;";
            
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
            ResultSet rs2 = stmt2.executeQuery("Select name,activity,log_distance,log_time,log_comment,log_score from s_"+groupname+"_log WHERE log_distance IS NOT NULL;");
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
            ResultSet rs4 = stmt4.executeQuery("Select name,activity,log_muscle1,log_muscle2,log_time,log_comment,log_score from s_"+groupname+"_log WHERE log_muscle1 IS NOT NULL;");
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
            ResultSet rs3 = stmt3.executeQuery("Select * from s_"+groupname+"_log WHERE activity IS NULL;");
            String str3 = "<table border=1><tr><th>Name</th><th>Comment</th></tr>";
            
            //loop that decides whether data is an activity or a comment, and prints it
            while(rs3.next()) { 
                str3 += "<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(9)+"</td></tr>";
                }

            str3 += "</table>";
            out.println(str3);
            
            out.println("<br/>");            
            
            con.close();
            
        } catch(Exception e) {
            System.err.println(e);
        }
        }
        
        //TIME if statmement
        
        if (groupname.startsWith("t")) {
        try {            
            
            
            //SQL statement to get the data from the group's mySQL table
            String sql = "Select user_id,name,time from t_"+groupname + " ORDER BY time desc;";
            
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
            ResultSet rs2 = stmt2.executeQuery("Select name,activity,log_distance,log_time,log_comment from t_"+groupname+"_log WHERE log_distance IS NOT NULL;");
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
            ResultSet rs4 = stmt4.executeQuery("Select name,activity,log_muscle1,log_muscle2,log_time,log_comment from t_"+groupname+"_log WHERE log_muscle1 IS NOT NULL;");
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
            ResultSet rs3 = stmt3.executeQuery("Select * from t_"+groupname+"_log WHERE activity IS NULL;");
            String str3 = "<table border=1><tr><th>Name</th><th>Comment</th></tr>";
            
            //loop that decides whether data is an activity or a comment, and prints it
            while(rs3.next()) { 
                str3 += "<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(9)+"</td></tr>";
                }

            str3 += "</table>";
            out.println(str3);
            
            out.println("<br/>");            
            
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
