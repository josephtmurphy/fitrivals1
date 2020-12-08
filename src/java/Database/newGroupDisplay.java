/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import static Database.viewGroups.isNullOrEmpty;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author josep
 */
public class newGroupDisplay extends HttpServlet {

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
        
        //decides the nature of the group's "goal"
        String groupType = request.getParameter("grouptype");
        
        //connecting to our db
        dbcon db = new dbcon();
        Connection con = db.getCon();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
                
        
        if (groupType.equals("Distance")) {
        try {            
            
            //gets the data from the viewGroup html file
            String groupname = request.getParameter("groupname");
            
            //SQL statement to get the data from the group's mySQL table
            String sql = "Select user_id,name,distance from d_"+groupname + " ORDER BY distance desc;";
            
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
            ResultSet rs2 = stmt2.executeQuery("Select name,activity,log_distance,log_time,log_comment from d_"+groupname+"_log WHERE activity IS NOT NULL;");
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
            ResultSet rs3 = stmt3.executeQuery("Select * from d_"+groupname+"_log WHERE activity IS NULL;");
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