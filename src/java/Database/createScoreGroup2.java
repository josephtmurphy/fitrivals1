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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author josep
 */
public class createScoreGroup2 extends HttpServlet {

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

        //get data from jsp
        String groupname = request.getParameter("groupname");
        String name = request.getParameter("name");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            //gets the data from the jsp file
            String run_points = request.getParameter("run_points");
            String cycle_points = request.getParameter("cycle_points");
            String walk_points = request.getParameter("walk_points");
            String strength_points = request.getParameter("strength_points");

            //connecting to our db
            dbcon db = new dbcon();
            Connection con = db.getCon();

            //SQL syntax to create the group's table and inserting its first user.
            //also contains syntax to create a log for the table which contains a record of each activity, and a record of the user being in the group
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE TABLE TS_" + groupname + "(user_id int NOT NULL AUTO_INCREMENT, name varchar(35), distance int, score int, time int, PRIMARY KEY(user_id));");
            stmt.executeUpdate("CREATE TABLE TS_" + groupname + "_log(user_id int NOT NULL AUTO_INCREMENT, name varchar(35), activity varchar(35), date date, log_distance int, log_time int, log_score int, log_muscle1 varchar(10), log_muscle2 varchar(10), log_comment text, PRIMARY KEY(user_id));");
            stmt.executeUpdate("INSERT INTO ts_" + groupname + "(name,distance,score,time) VALUES('" + name + "',0,0,0);");
            stmt.executeUpdate("INSERT INTO group_members(username,groupname) VALUES('" + name + "','ts_" + groupname + "');");

            //SQL syntax to log the groups scoring system
            stmt.executeUpdate("INSERT INTO time_scoring_systems (groupname,run_points,cycle_points,walk_points,strength_points) VALUES('ts_" + groupname + "'," + run_points + "," + cycle_points + "," + walk_points + "," + strength_points + ");");

            //success statement
            out.println("<div style=\"background-color: paleturquoise; padding: 10px; padding-left: 50px;\">");
            out.println("<form action=\"viewGroups\">");
            out.println("Success!");
            out.println("<br/>");
            //session handling
            out.println("<input hidden type=\"text\" name=\"groupname\" value=\"ts_" + groupname + "\" readonly=\"readonly\"/>");
            out.println("<input type=\"submit\" value=\"View Group\"/>");
            out.println("</form");
            out.println("</div");

        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
