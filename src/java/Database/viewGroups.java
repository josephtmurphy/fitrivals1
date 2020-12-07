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
        
        
        
        //DISTANCE if statmement
        
        if (groupType.equals("Distance")) {
        try {            
            
            //gets the data from the viewGroup html file
            String groupname = request.getParameter("groupname");
            
            //SQL statement to get the data from the group's mySQL table
            String sql = "Select user_id,name,distance from d_"+groupname + " ORDER BY distance desc;";
            
            //HTML code to create a table to display the group data
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            String str = "<table border=1><tr><th>ID</th><th>Name</th><th>Distance</th></tr>";
            
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
            
            //HTML code to create a table to display the group log data (individual activities and comments)
            
            //SQL and HTML syntax to create the table
            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("Select user_id,name,activity,log_distance,log_time,log_comment from d_"+groupname+"_log;");
            String str2 = "<table border=1><tr><th>Activity Log</th></tr>";
            
            //loop that decides whether data is an activity or a comment, and prints it
            while(rs2.next()) { 
                String activity = rs2.getString(3);
                if(isNullOrEmpty(activity)) {
                str2 += "<tr><td>"+rs2.getString(2)+ ": "+rs2.getString(6)+"</td></tr>";
                }
                else {
                str2 += "<tr><td>"+rs2.getString(2)+ " has completed a "+rs2.getString(3)+", covering "+rs2.getInt(4)+"km in " +rs2.getInt(5)+ " minutes. Comment: "+rs2.getString(6)+"</td></tr>";
                }
                }
            str2 += "</table>";
            out.println(str2);
                     
            
            con.close();
            
        } catch(Exception e) {
            System.err.println(e);
        }
    }
        
        //SCORE if statmement
        
        if (groupType.equals("Score")) {
        try {            
            
            //gets the data from the viewGroup html file
            String groupname = request.getParameter("groupname");
            
            //SQL statement to get the data from the group's mySQL table
            String sql = "Select user_id,name,score from s_"+groupname + " ORDER BY score desc;";
            
            //HTML code to create a table to display the group data
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            String str = "<table border=1><tr><th>ID</th><th>Name</th><th>Score</th></tr>";
            
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
            
            //HTML code to create a table to display the group data
            
            //SQL and HTML syntax to create the table
            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("Select * from s_"+groupname+"_log;");
            String str2 = "<table border=1><tr><th>Activity Log</th></tr>";
            
            //loop that decides whether data is an activity or a comment, and prints it
            while(rs2.next()) { 
                
                String activity = rs2.getString(3);
                String distance = rs2.getString(4);
                String muscleGroup2 = rs2.getString(8);
                
                if(isNullOrEmpty(activity)) {
                str2 += "<tr><td>"+rs2.getString(2)+ ": "+rs2.getString(9)+"</td></tr>";
                }
                
                else if(isNullOrEmpty(distance)) {
                    
                if(isNullOrEmpty(muscleGroup2)) {
                str2 += "<tr><td>"+rs2.getString(2)+ " has completed a gym session, training "+rs2.getString(7)+" for " +rs2.getInt(5)+ " minutes, and earning " +rs2.getInt(6)+ " points. Comment: "+rs2.getString(9)+"</td></tr>";
                }
                else {
                    str2 += "<tr><td>"+rs2.getString(2)+ " has completed a gym session, training "+rs2.getString(7)+" and " +rs2.getString(8)+" for " +rs2.getInt(5)+ " minutes, and earning " +rs2.getInt(6)+ " points. Comment: "+rs2.getString(9)+"</td></tr>";
                }
                }
                
                else {
                str2 += "<tr><td>"+rs2.getString(2)+ " has completed a "+rs2.getString(3)+", covering "+rs2.getInt(4)+"km in " +rs2.getInt(5)+ " minutes, earning a total of "+rs2.getString(6)+" points. Comment: "+rs2.getString(9)+"</td></tr>";    
                }}
            str2 += "</table>";
            out.println(str2);
            
            con.close();
            
        } catch(Exception e) {
            System.err.println(e);
        }
    } 
        
        //TIME if statmement
        
        if (groupType.equals("Time")) {
        try {            
            
            //gets the data from the viewGroup html file
            String groupname = request.getParameter("groupname");
            
            //SQL statement to get the data from the group's mySQL table
            String sql = "Select user_id,name,time from t_"+groupname + " ORDER BY time desc;";
            
            //HTML code to create a table to display the group data
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            String str = "<table border=1><tr><th>Position</th><th>Name</th><th>Time</th></tr>";
            
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
            
            //HTML code to create a table to display the group data
            
            //SQL and HTML syntax to create the table
            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("Select * from t_"+groupname+"_log;");
            String str2 = "<table border=1><tr><th>Activity Log</th></tr>";
            
            //loop that decides whether data is an activity or a comment, and prints it
            while(rs2.next()) { 
                String activity = rs2.getString(3);
                String distance = rs2.getString(4);
                String muscleGroup2 = rs2.getString(8);
                
                if(isNullOrEmpty(activity)) {
                str2 += "<tr><td>"+rs2.getString(2)+ ": "+rs2.getString(9)+"</td></tr>";
                }
                
                else if(isNullOrEmpty(distance)) {
                    
                if(isNullOrEmpty(muscleGroup2)) {
                str2 += "<tr><td>"+rs2.getString(2)+ " has completed a gym session, training "+rs2.getString(7)+" for " +rs2.getInt(5)+ " minutes. Comment: "+rs2.getString(9)+"</td></tr>";
                }
                else {
                    str2 += "<tr><td>"+rs2.getString(2)+ " has completed a gym session, training "+rs2.getString(7)+" and " +rs2.getString(8)+" for " +rs2.getInt(5)+ " minutes. Comment: "+rs2.getString(9)+"</td></tr>";
                }
                }
                
                else {
                str2 += "<tr><td>"+rs2.getString(2)+ " has completed a "+rs2.getString(3)+", covering "+rs2.getInt(4)+"km in " +rs2.getInt(5)+ " minutes. Comment: "+rs2.getString(9)+"</td></tr>";    
                }}
            str2 += "</table>";
            out.println(str2);
            
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
