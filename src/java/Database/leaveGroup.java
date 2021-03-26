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
public class leaveGroup extends HttpServlet {

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

        //gets the data from the joinGroup html file
        String groupname = request.getParameter("groupname");
        String name = request.getParameter("name");

            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */

                //session handling
                out.println("<input hidden type=\"text\" name=\"groupname\" value=\"" + groupname + "\" readonly=\"readonly\"/>");

                dbcon db = new dbcon();
                Connection con = db.getCon();

                //SQL statement to enter a new user in to the group
                Statement stmt = con.createStatement();
                stmt.executeUpdate("DELETE FROM " + groupname + " WHERE NAME = '" + name + "';");
                stmt.executeUpdate("DELETE FROM group_members WHERE USERNAME = '" + name + "' AND GROUPNAME = '" + groupname + "';");

            //success statement
            out.println("<div style=\"background-color: hotpink; padding: 10px; padding-left: 50px;\">");;
            out.println("<form action=\"frHomepage.jsp#groups\">");
            out.println(name + ", you have left " + groupname + ".");
            out.println("<br/>");
            //session handling
            out.println("<input type=\"submit\" value=\"Return to Groups\"/>");
            out.println("</form>");
            out.println("</div>");
                        

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
