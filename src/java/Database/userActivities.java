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
public class userActivities extends HttpServlet {

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
        String username = request.getParameter("loggedname");
        
        //connecting to our db
        dbcon db = new dbcon();
        Connection con = db.getCon();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
            
        
        try {            
                
            //SQL statement to get the data from the group's mySQL table
            String sql = "Select username,user_height,user_weight,user_thigh,user_bicep,user_waist from user_physique WHERE username ='"+username+"' ORDER BY user_weight asc;";
            
            //1. HTML code to create a table to display the group data [LEADERBOARD]
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            String str = "<table border=1><tr><th>Name</th><th>Height (cm)</th><th>Weight (lbs)</th><th>Thigh (cm)</th><th>Bicep (cm)</th><th>Waist (cm)</th></tr>";
            
            //prints table
            int i = 1;
            out.println("Your physique updates");
            while(rs.next()) {
                str+= "<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td></tr>";
                i++;
            }
            str += "</table>";
            out.println(str);
          
            out.println("<br/>");     
            
            
        } catch(Exception e) {
            System.err.println(e);
        }
        
        try {            
                
            //SQL statement to get the data from the group's mySQL table
            String sql = "Select username,groupname,activity,time,distance,comment from all_cardioactivities WHERE username = '"+username+"';";
            
            //1. HTML code to create a table to display the group data [LEADERBOARD]
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            String str = "<table border=1><tr><th>Name</th><th>Group</th><th>Activity Type</th><th>Distance (km)</th><th>Time (min)</th><th>Comment</th></tr>";
            
            //prints table
            int i = 1;
            out.println("Your cardio activity");
            while(rs.next()) {
                str+= "<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(6)+"</td></tr>";
                i++;
            }
            str += "</table>";
            out.println(str);
          
            out.println("<br/>");     

            
        } catch(Exception e) {
            System.err.println(e);
        } 
        
        try {            
                
            //SQL statement to get the data from the group's mySQL table
            String sql = "Select username,groupname,activity,muscles,time,comment from all_strengthactivities WHERE username = '"+username+"';";
            
            //1. HTML code to create a table to display the group data [LEADERBOARD]
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            String str = "<table border=1><tr><th>Name</th><th>Group</th><th>Activity Type</th><th>Muscles Worked</th><th>Time (mins)</th><th>Comment</th></tr>";
            
            //prints table
            int i = 1;
            out.println("Your strength activity");
            while(rs.next()) {
                str+= "<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td></tr>";
                i++;
            }
            str += "</table>";
            out.println(str);
          
            out.println("<br/>");
            
        } catch(Exception e) {
            System.err.println(e);
        }          
        
        try {            
                          
            //SQL statement to get the data from the group's mySQL table
            String sql = "SELECT SUM(distance) FROM all_cardioactivities where username = '" + username + "';";
            
            //1. HTML code to create a table to display the group data [LEADERBOARD]
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            rs.next();
            out.println("Your total distance covered: " + rs.getInt(1) + "km.");
            out.println("<br/>"); 
            
            //////////////////////////////////////////////////////////////////////////////////
            
            String sql2 = "SELECT SUM(time) FROM all_cardioactivities where username = '" + username + "';";
            
            //1. HTML code to create a table to display the group data [LEADERBOARD]
            ResultSet rs2 =  stmt.executeQuery(sql2);
            rs2.next();
            out.println("Your total time spent on cardio activity: " + rs2.getInt(1) + " mins.");
            out.println("<br/>");                
                      
            /////////////////////////////////////////////////////////////////////////////////////
            
            String sql3 = "SELECT COUNT(time) FROM all_cardioactivities where username = '" + username + "';";
            
            //1. HTML code to create a table to display the group data [LEADERBOARD]
            ResultSet rs3 =  stmt.executeQuery(sql3);
            rs3.next();
            out.println("Your total cardio sessions: " + rs3.getInt(1) + ".");
            out.println("<br/>"); 
            
            out.println("<br/>"); 
            
            /////////////////////////////////////////////////////////////////////////////////////
            
            String sql4 = "SELECT SUM(time) FROM all_strengthactivities where username = '" + username + "';";
            
            //1. HTML code to create a table to display the group data [LEADERBOARD]
            ResultSet rs4 =  stmt.executeQuery(sql4);
            rs4.next();
            out.println("Your total time spent strength training: " + rs4.getInt(1) + " mins.");
            out.println("<br/>"); 
            
            /////////////////////////////////////////////////////////////////////////////////////
            
            String sql5 = "SELECT COUNT(time) FROM all_strengthactivities where username = '" + username + "';";
            
            //1. HTML code to create a table to display the group data [LEADERBOARD]
            ResultSet rs5 =  stmt.executeQuery(sql5);
            rs5.next();
            out.println("Your total strength training sessions: " + rs5.getInt(1) + ".");
            out.println("<br/>"); 
            
            out.println("<br/>");            
            out.println("<a href=\"homepage.jsp\">Return home</a>");
            
            con.close();
            
        } catch(Exception e) {
            System.err.println(e);
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
