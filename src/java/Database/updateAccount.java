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
public class updateAccount extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            //obtains relevant data from updateAccount.jsp
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String fullname = request.getParameter("fullname");
            String password = request.getParameter("password");
            String dob = request.getParameter("dob");
            
            //connecting to our db
            dbcon db = new dbcon();
            Connection con = db.getCon();

            //update SQL statement that will make the changes outlined from the jsp
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE users SET user_email = '" + email + "', full_name = '" + fullname + "', password = '" + password + "', dob = '" + dob + "' WHERE username = '" + username + "'");

            out.println("<div style=\"background-color: palegreen; padding: 10px; padding-left: 50px;\">");
            out.println("<form action=\"updatePhysique.jsp\">");
            out.println("Success!");
            out.println("<br/>");
            //session handling
            out.println("<input hidden type=\"text\" name=\"loggedname\" value=\"" + username + "\" readonly=\"readonly\"/>");            
            out.println("<input type=\"submit\" value=\"Return\"/>");
            out.println("</form");
            out.println("</div");

        } catch (SQLException ex) {
            Logger.getLogger(createUser.class.getName()).log(Level.SEVERE, null, ex);
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
