/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Database.dbcon;
import Database.login;
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
public class joinGroup extends HttpServlet {

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

        //gets the data from the joinGroup html file
        String groupname = request.getParameter("groupname");
        String name = request.getParameter("name");

        //DISTANCE if statement
        if (groupType.equals("Distance")) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */

                //session handling
                out.println("<input hidden type=\"text\" name=\"groupname\" value=\"" + groupname + "\" readonly=\"readonly\"/>");

                dbcon db = new dbcon();
                Connection con = db.getCon();

                //SQL statement to enter a new user in to the group
                Statement stmt = con.createStatement();
                stmt.executeUpdate("INSERT INTO d_" + groupname + "(name,distance,time,score) VALUES('" + name + "',0,0,0);");
                stmt.executeUpdate("INSERT INTO group_members(username,groupname) VALUES('" + name + "','d_" + groupname + "');");

                //landing page - displays group after session is logged
                RequestDispatcher rd = request.getRequestDispatcher("viewGroups");
                rd.forward(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //SCORE if statement
        if (groupType.equals("Score")) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */

                //session handling
                out.println("<input hidden type=\"text\" name=\"groupname\" value=\"" + groupname + "\" readonly=\"readonly\"/>");

                dbcon db = new dbcon();
                Connection con = db.getCon();

                //SQL statement to enter a new user in to the group
                Statement stmt = con.createStatement();
                stmt.executeUpdate("INSERT INTO s_" + groupname + "(name,distance,score,time) VALUES('" + name + "',0,0,0);");
                stmt.executeUpdate("INSERT INTO group_members(username,groupname) VALUES('" + name + "','s_" + groupname + "');");

                //landing page - displays group after session is logged
                RequestDispatcher rd = request.getRequestDispatcher("viewGroups");
                rd.forward(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //TIME if statement
        if (groupType.equals("Time")) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */

                //session handling
                out.println("<input hidden type=\"text\" name=\"groupname\" value=\"" + groupname + "\" readonly=\"readonly\"/>");

                dbcon db = new dbcon();
                Connection con = db.getCon();

                //SQL statement to enter a new user in to the group
                Statement stmt = con.createStatement();
                stmt.executeUpdate("INSERT INTO t_" + groupname + "(name,distance,score,time) VALUES('" + name + "',0,0,0);");
                stmt.executeUpdate("INSERT INTO group_members(username,groupname) VALUES('" + name + "','t_" + groupname + "');");

                //landing page - displays group after session is logged
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
