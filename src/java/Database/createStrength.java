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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author josep
 */
public class createStrength extends HttpServlet {

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
        //differentiates between the types of group
        String groupname = request.getParameter("groupname");

        //"time: score" if statement
        if (groupname.startsWith("ts")) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */

                //session handling
                out.println("<input hidden type=\"text\" name=\"groupname\" value=\"" + groupname + "\" readonly=\"readonly\"/>");

                //gets the data from the createCardio jsp
                String name = request.getParameter("name");
                String date = request.getParameter("date");
                String muscleGroup1 = request.getParameter("muscleGroup1");
                String muscleGroup2 = request.getParameter("muscleGroup2");
                int time = Integer.parseInt(request.getParameter("time"));
                String comment = request.getParameter("comment");

                //connecting to our db
                dbcon db = new dbcon();
                Connection con = db.getCon();

                //SQL syntax to call on the scoring system outlined by the user who created the group
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("Select strength_points from time_scoring_systems where groupname = '" + groupname + "';");
                rs.next();
                int minScore = time * rs.getInt(1);

                //SQL syntax to create the activity in the log, and update the score within the group
                stmt.executeUpdate("update " + groupname + " set time = time + " + time + ", score = score + " + minScore + " where name = '" + name + "';");
                stmt.executeUpdate("INSERT INTO " + groupname + "_log(name,activity,date,log_muscle1,log_muscle2,log_time,log_score,log_comment) VALUES('" + name + "','" + "Strength" + "','" + date + "','" + muscleGroup1 + "','" + muscleGroup2 + "'," + time + "," + minScore + ",'" + comment + "');");
                stmt.executeUpdate("INSERT INTO all_strengthactivities (username,groupname,activity,date,time,muscles,comment) VALUES('" + name + "','" + groupname + "', 'Strength" + "','" + date + "'," + time + ",'" + muscleGroup1 + ", " + muscleGroup2 + "','" + comment + "');");

                //redirect to view group page
                RequestDispatcher rd = request.getRequestDispatcher("viewGroups");
                rd.forward(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //"time" if statement       
        if (groupname.startsWith("t_")) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */

                //session handling
                out.println("<input hidden type=\"text\" name=\"groupname\" value=\"" + groupname + "\" readonly=\"readonly\"/>");

                //gets the data from the createCardio jsp
                String name = request.getParameter("name");
                String date = request.getParameter("date");
                String muscleGroup1 = request.getParameter("muscleGroup1");
                String muscleGroup2 = request.getParameter("muscleGroup2");
                int time = Integer.parseInt(request.getParameter("time"));
                String comment = request.getParameter("comment");

                //connecting to our db
                dbcon db = new dbcon();
                Connection con = db.getCon();

                //SQL syntax to create the activity in the log, and update the score within the group
                Statement stmt = con.createStatement();
                stmt.executeUpdate("update " + groupname + " set time = time + " + time + " where name = '" + name + "';");
                stmt.executeUpdate("INSERT INTO " + groupname + "_log(name,activity,date,log_muscle1,log_muscle2,log_time,log_comment) VALUES('" + name + "','" + "Strength" + "','" + date + "','" + muscleGroup1 + "','" + muscleGroup2 + "'," + time + ",'" + comment + "');");
                stmt.executeUpdate("INSERT INTO all_strengthactivities (username,groupname,activity,date,time,muscles,comment) VALUES('" + name + "','" + groupname + "', 'Strength" + "','" + date + "'," + time + ",'" + muscleGroup1 + " , " + muscleGroup2 + "', '" + comment + "');");

                //redirects to view group
                RequestDispatcher rd = request.getRequestDispatcher("viewGroups");
                rd.forward(request, response);

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
