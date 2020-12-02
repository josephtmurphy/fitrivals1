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
public class createGroup extends HttpServlet {

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
                
                //connecting to our db
                dbcon db = new dbcon();
                Connection con = db.getCon();
                
                //SQL syntax to create the group's table and inserting its first user.
                Statement stmt = con.createStatement();
                stmt.executeUpdate("CREATE TABLE d_" + groupname + "(user_id int NOT NULL AUTO_INCREMENT, name varchar(35), distance int, score int, time int, PRIMARY KEY(user_id));");
                stmt.executeUpdate("CREATE TABLE d_" + groupname + "_log(user_id int NOT NULL AUTO_INCREMENT, name varchar(35), activity varchar(35), log_distance int, log_time int, log_comment text, PRIMARY KEY(user_id));");
                stmt.executeUpdate("INSERT INTO d_" + groupname + "(name,distance,score,time) VALUES('" + name + "',0,0,0);");
                
                //shows that operation has been successful
                out.println("New group '"+groupname+"' has been successfully created, and "+name+" is the first member. Good luck!");
                
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {    
        }
        
        //SCORE if statement
        
        if (groupType.equals("Score")) {    
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            //gets the data from the joinGroup html file
            String groupname = request.getParameter("groupname");            
            String name = request.getParameter("name");
            
            //connecting to our db
            dbcon db = new dbcon();
            Connection con = db.getCon();
            
            //SQL syntax to create the group's table and inserting its first user.
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE TABLE S_" + groupname + "(user_id int NOT NULL AUTO_INCREMENT, name varchar(35), distance int, score int, time int, PRIMARY KEY(user_id));");
            stmt.executeUpdate("CREATE TABLE s_" + groupname + "_log(user_id int NOT NULL AUTO_INCREMENT, name varchar(35), activity varchar(35), log_distance int, log_time int, log_comment text, PRIMARY KEY(user_id));");
            stmt.executeUpdate("INSERT INTO s_" + groupname + "(name,distance,score,time) VALUES('" + name + "',0,0,0);");
            stmt.executeUpdate("INSERT INTO s_" + groupname + "_log(name,log_distance,log_time) VALUES('" + name + "',0,0);");
            
            //shows that operation has been successful
            out.println("New group '"+groupname+"' has been successfully created, and "+name+" is the first member. Good luck!");
            
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
            
            //connecting to our db
            dbcon db = new dbcon();
            Connection con = db.getCon();
            
            //SQL syntax to create the group's table and inserting its first user.
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE TABLE T_" + groupname + "(user_id int NOT NULL AUTO_INCREMENT, name varchar(35), distance int, score int, time int, PRIMARY KEY(user_id));");
            stmt.executeUpdate("CREATE TABLE t_" + groupname + "_log(loguser_id int NOT NULL AUTO_INCREMENT, name varchar(35), activity varchar(35), log_distance int, log_time int, log_comment text, PRIMARY KEY(loguser_id));");
            stmt.executeUpdate("INSERT INTO t_" + groupname + "(name,distance,score,time) VALUES('" + name + "',0,0,0);");
            stmt.executeUpdate("INSERT INTO t_" + groupname + "_log(name,log_distance,log_time) VALUES('" + name + "',0,0);");
            
            //shows that operation has been successful
            out.println("New group '"+groupname+"' has been successfully created, and "+name+" is the first member. Good luck!");
            
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
