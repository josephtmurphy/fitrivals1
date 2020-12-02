/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
public class createCardio extends HttpServlet {

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
        String groupType = request.getParameter("grouptype1");
        
        //DISTANCE if statement
        
        if (groupType.equals("Distance")) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                
                //gets the data from the joinGroup html file
                String groupname = request.getParameter("groupname");
                String name = request.getParameter("name");
                int distance = Integer.parseInt(request.getParameter("distance"));
                int time = Integer.parseInt(request.getParameter("time"));
                String comment = request.getParameter("comment");
                String activityType = request.getParameter("activityType");
                
                //connecting to our db
                dbcon db = new dbcon();
                Connection con = db.getCon();
                
                //SQL syntax to create the group's table and inserting its first user.
                Statement stmt = con.createStatement();
                stmt.executeUpdate("update d_" + groupname + " set distance = distance +" + distance + ", time = time + " + time + " where name = '" + name + "';");
                stmt.executeUpdate("INSERT INTO d_" + groupname + "_log(name,activity,log_distance,log_time,log_comment) VALUES('" + name + "','" + activityType + "'," + distance + "," + time + ",'" + comment + "');");
                
                //shows that operation has been successful
                out.println(name + ", your " + activityType + " has been logged in " + groupname + ". Good work!");
                
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        //SCORE if statement
        
        if (groupType.equals("Score")) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                
                //gets the data from the joinGroup html file
                String groupname = request.getParameter("groupname");
                String name = request.getParameter("name");
                int distance = Integer.parseInt(request.getParameter("distance"));
                int time = Integer.parseInt(request.getParameter("time"));
                String comment = request.getParameter("comment");
                String activityType = request.getParameter("activityType");
                
                //connecting to our db
                dbcon db = new dbcon();
                Connection con = db.getCon();
                
                //SQL syntax to create the group's table and inserting its first user.
                Statement stmt = con.createStatement();
                stmt.executeUpdate("update s_" + groupname + " set distance = distance +" + distance + ", time = time + " + time + ", where name = '" + name + "';");
                stmt.executeUpdate("INSERT INTO s_" + groupname + "_log(name,activity,log_distance,log_time,log_comment) VALUES('" + name + "','" + activityType + "'," + distance + "," + time + ",'" + comment + "');");
                
                
                
                //shows that operation has been successful
                out.println(name + ", your " + activityType + " has been logged in " + groupname + ". Good work!");

            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        //TIME if statement
        
        if (groupType.equals("Time")) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                
                //gets the data from the joinGroup html file
                String groupname = request.getParameter("groupname");
                String name = request.getParameter("name");
                int distance = Integer.parseInt(request.getParameter("distance"));
                int time = Integer.parseInt(request.getParameter("time"));
                String comment = request.getParameter("comment");
                String activityType = request.getParameter("activityType");
                
                //connecting to our db
                dbcon db = new dbcon();
                Connection con = db.getCon();
                
                //SQL syntax to create the group's table and inserting its first user.
                Statement stmt = con.createStatement();
                stmt.executeUpdate("update t_" + groupname + " set distance = distance +" + distance + ", time = time + " + time + ", where name = '" + name + "';");
                stmt.executeUpdate("INSERT INTO t_" + groupname + "_log(name,activity,log_distance,log_time,log_comment) VALUES('" + name + "','" + activityType + "'," + distance + "," + time + ",'" + comment + "');");
                
                //shows that operation has been successful
                out.println(name + ", your " + activityType + " has been logged in " + groupname + ". Good work!");
                
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
