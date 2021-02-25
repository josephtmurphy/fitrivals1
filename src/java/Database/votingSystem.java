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
public class votingSystem extends HttpServlet {

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
      
      String post_id = request.getParameter("post_id");
      String username = request.getParameter("name");
      
                  //db connection
            dbcon db = new dbcon();
            Connection con = db.getCon();
    
    if (request.getParameter("upvote") != null) {
        //upvote button is clicked
            //updates user table to current values, logs record of change into separate physique table
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO votes (submission_id,username,vote) "
            + "SELECT "+post_id+",'"+username+"',1 "
            + "FROM DUAL "
            + "WHERE NOT EXISTS (SELECT * FROM votes WHERE username = '"+username+"' AND submission_id = "+post_id+" AND vote = 1)");   
            stmt.executeUpdate("update blog_submissions set score = "
            + "(select sum(vote) from votes where submission_id = "+post_id+") where submission_id = "+post_id+";");
            //success statement
            out.println(username +", you upvoted this post.");
            out.println("<a href=\"blogHome.jsp\">Return to blog</a>");             
            
    } else if (request.getParameter("downvote") != null) {
          //downvote button is clicked
            //updates user table to current values, logs record of change into separate physique table
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO votes (submission_id,username,vote) "
            + "SELECT "+post_id+",'"+username+"',-1 "
            + "FROM DUAL "
            + "WHERE NOT EXISTS (SELECT * FROM votes WHERE username = '"+username+"' AND submission_id = "+post_id+" AND vote = -1)");   
            stmt.executeUpdate("update blog_submissions set score = "
            + "(select sum(vote) from votes where submission_id = "+post_id+") where submission_id = "+post_id+";");
            //success statement
            out.println(username +", you downvoted this post.");
            out.println("<a href=\"blogHome.jsp\">Return to blog</a>");              
            
    }  

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
