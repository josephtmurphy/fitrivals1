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
public class createUser extends HttpServlet {

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

            //get data from create user jsp
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String fullname = request.getParameter("fullname");
            String password = request.getParameter("password");
            String height = request.getParameter("height");
            String weight = request.getParameter("weight");
            String thigh = request.getParameter("thigh");
            String bicep = request.getParameter("bicep");
            String waist = request.getParameter("waist");
            String dob = request.getParameter("dob");

            //connecting to our db
            dbcon db = new dbcon();
            Connection con = db.getCon();

            //inserts record of user into SQL tables
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO users (user_email,username,full_name,password,height,weight,thigh,bicep,waist,dob) VALUES('" + email + "','" + username + "','" + fullname + "','" + password + "','" + height + "','" + weight + "','" + thigh + "','" + bicep + "','" + waist + "','" + dob + "')");
            stmt.executeUpdate("INSERT INTO user_physique (username,user_height,user_weight,user_thigh,user_bicep,user_waist) values('" + username + "','" + height + "','" + weight + "','" + thigh + "','" + bicep + "','" + waist + "');");

            //success statement
            out.println("<div style=\"background-color: peachpuff; padding: 10px; padding-left: 50px;\">");
            out.println(fullname + ", thank you for signing up!");
            out.println("<br/>");
            out.println("<a href=\"login.jsp\">Log in to FitRivals here</a>");
            out.println("</div>");

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
