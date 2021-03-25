/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Session.User;
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
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

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

        //servlet calling code - https://stackoverflow.com/questions/20947806/how-can-i-call-from-one-servlet-file-to-another-servlet-file
        //differentiates between the types of group
        String groupname = request.getParameter("groupname");

        //DISTANCE if statement
        
        if (groupname.startsWith("d_")) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */

                //session handling
                out.println("<input hidden type=\"text\" name=\"groupname\" value=\"" + groupname + "\" readonly=\"readonly\"/>");

                //gets the data from the createCardio jsp
                String name = request.getParameter("name");
                String date = request.getParameter("date");
                int distance = Integer.parseInt(request.getParameter("distance"));
                int time = Integer.parseInt(request.getParameter("time"));
                String comment = request.getParameter("comment");
                String activityType = request.getParameter("activityType");

                //connecting to our db
                dbcon db = new dbcon();
                Connection con = db.getCon();

                //SQL syntax to create the activity in the log, and update the score within the group
                Statement stmt = con.createStatement();
                stmt.executeUpdate("update " + groupname + " set distance = distance +" + distance + ", time = time + " + time + " where name = '" + name + "';");
                stmt.executeUpdate("INSERT INTO " + groupname + "_log(name,activity,date,log_distance,log_time,log_comment) VALUES('" + name + "','" + activityType + "','" + date + "'," + distance + "," + time + ",'" + comment + "');");
                stmt.executeUpdate("INSERT INTO all_cardioactivities(username,groupname,activity,date,time,distance,comment) VALUES('" + name + "','" + groupname + "','" + activityType + "','" + date + "'," + time + "," + distance + ",'" + comment + "');");

                //landing page - displays group after session is logged
                RequestDispatcher rd = request.getRequestDispatcher("viewGroups");
                rd.forward(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        //SCORE if statement
        
        if (groupname.startsWith("ds")) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */

                //gets the data from the logScoreActivity jsp
                String name = request.getParameter("name");
                String date = request.getParameter("date");
                int distance = Integer.parseInt(request.getParameter("distance"));
                int time = Integer.parseInt(request.getParameter("time"));
                String comment = request.getParameter("comment");
                String activityType = request.getParameter("activityType");

                //connecting to our db
                dbcon db = new dbcon();
                Connection con = db.getCon();

                //SQL syntax to call on the scoring system outlined by the user who created the group
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("Select run_points,cycle_points,walk_points from distance_scoring_systems where groupname = '" + groupname + "';");

                //run syntax
                if (activityType.startsWith("R")) {

                    //session handling
                    out.println("<input hidden type=\"text\" name=\"groupname\" value=\"" + groupname + "\" readonly=\"readonly\"/>");

                    rs.next();
                    int kmScore = distance * rs.getInt(1);

                    //adds the new score into the group, inserts a record into the log
                    stmt.executeUpdate("update " + groupname + " set distance = distance +" + distance + ", time = time + " + time + ", score = score + " + kmScore + " where name = '" + name + "';");
                    stmt.executeUpdate("INSERT INTO " + groupname + "_log(name,activity,date,log_distance,log_time,log_score,log_comment) VALUES('" + name + "','" + activityType + "','" + date + "'," + distance + "," + time + "," + kmScore + ",'" + comment + "');");
                    stmt.executeUpdate("INSERT INTO all_cardioactivities (username,groupname,activity,date,time,distance,comment) VALUES('" + name + "','" + groupname + "','" + activityType + "','" + date + "'," + time + "," + distance + ",'" + comment + "');");

                    //landing page - displays group after session is logged
                    RequestDispatcher rd = request.getRequestDispatcher("viewGroups");
                    rd.forward(request, response);

                }

                //cycle syntax
                if (activityType.startsWith("C")) {

                    //session handling
                    out.println("<input hidden type=\"text\" name=\"groupname\" value=\"" + groupname + "\" readonly=\"readonly\"/>");

                    //ties the logged activity in with the scoring system 
                    rs.next();
                    int kmScore = distance * rs.getInt(2);

                    //adds the new score into the group, inserts a record into the log
                    stmt.executeUpdate("update " + groupname + " set distance = distance +" + distance + ", time = time + " + time + ", score = score + " + kmScore + " where name = '" + name + "';");
                    stmt.executeUpdate("INSERT INTO " + groupname + "_log(name,activity,date,log_distance,log_time,log_score,log_comment) VALUES('" + name + "','" + activityType + "','" + date + "'," + distance + "," + time + "," + kmScore + ",'" + comment + "');");
                    stmt.executeUpdate("INSERT INTO all_cardioactivities (username,groupname,activity,date,time,distance,comment) VALUES('" + name + "','" + groupname + "','" + activityType + "','" + date + "'," + time + "," + distance + ",'" + comment + "');");

                    //landing page - displays group after session is logged
                    RequestDispatcher rd = request.getRequestDispatcher("viewGroups");
                    rd.forward(request, response);

                }

                //walk syntax
                if (activityType.startsWith("W")) {

                    //session handling
                    out.println("<input hidden type=\"text\" name=\"groupname\" value=\"" + groupname + "\" readonly=\"readonly\"/>");

                    rs.next();
                    int kmScore = distance * rs.getInt(3);

                    //adds the new score into the group, inserts a record into the log
                    stmt.executeUpdate("update " + groupname + " set distance = distance +" + distance + ", time = time + " + time + ", score = score + " + kmScore + " where name = '" + name + "';");
                    stmt.executeUpdate("INSERT INTO " + groupname + "_log(name,activity,date,log_distance,log_time,log_score,log_comment) VALUES('" + name + "','" + activityType + "','" + date + "'," + distance + "," + time + "," + kmScore + ",'" + comment + "');");
                    stmt.executeUpdate("INSERT INTO all_cardioactivities (username,groupname,activity,date,time,distance,comment) VALUES('" + name + "','" + groupname + "','" + activityType + "','" + date + "'," + time + "," + distance + ",'" + comment + "');");

                    //landing page - displays group after session is logged
                    RequestDispatcher rd = request.getRequestDispatcher("viewGroups");
                    rd.forward(request, response);

                }

            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //TIME-SCORE if statement
        
        if (groupname.startsWith("ts")) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */

                //session handling
                out.println("<input hidden type=\"text\" name=\"groupname\" value=\"" + groupname + "\" readonly=\"readonly\"/>");

                //gets the data from the logScoreActivity jsp
                String name = request.getParameter("name");
                String date = request.getParameter("date");
                int distance = Integer.parseInt(request.getParameter("distance"));
                int time = Integer.parseInt(request.getParameter("time"));
                String comment = request.getParameter("comment");
                String activityType = request.getParameter("activityType");

                //connecting to our db
                dbcon db = new dbcon();
                Connection con = db.getCon();

                //SQL syntax to call on the scoring system outlined by the user who created the group
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("Select run_points,cycle_points,walk_points from time_scoring_systems where groupname = '" + groupname + "';");

                //run syntax
                if (activityType.startsWith("R")) {

                    //session handling
                    out.println("<input hidden type=\"text\" name=\"groupname\" value=\"" + groupname + "\" readonly=\"readonly\"/>");

                    //ties the logged activity in with the scoring system 
                    rs.next();
                    int minScore = time * rs.getInt(1);

                    //adds the new score into the group, inserts a record into the log
                    stmt.executeUpdate("update " + groupname + " set distance = distance +" + distance + ", time = time + " + time + ", score = score + " + minScore + " where name = '" + name + "';");
                    stmt.executeUpdate("INSERT INTO " + groupname + "_log(name,activity,date,log_distance,log_time,log_score,log_comment) VALUES('" + name + "','" + activityType + "','" + date + "'," + distance + "," + time + "," + minScore + ",'" + comment + "');");
                    stmt.executeUpdate("INSERT INTO all_cardioactivities (username,groupname,activity,date,time,distance,comment) VALUES('" + name + "','" + groupname + "','" + activityType + "','" + date + "'," + time + "," + distance + ",'" + comment + "');");

                    //landing page - displays group after session is logged
                    RequestDispatcher rd = request.getRequestDispatcher("viewGroups");
                    rd.forward(request, response);

                }

                //cycle syntax
                if (activityType.startsWith("C")) {
                    //ties the logged activity in with the scoring system 

                    //session handling
                    out.println("<input hidden type=\"text\" name=\"groupname\" value=\"" + groupname + "\" readonly=\"readonly\"/>");

                    rs.next();
                    int minScore = time * rs.getInt(2);

                    //adds the new score into the group, inserts a record into the log
                    stmt.executeUpdate("update " + groupname + " set distance = distance +" + distance + ", time = time + " + time + ", score = score + " + minScore + " where name = '" + name + "';");
                    stmt.executeUpdate("INSERT INTO " + groupname + "_log(name,activity,date,log_distance,log_time,log_score,log_comment) VALUES('" + name + "','" + activityType + "','" + date + "'," + distance + "," + time + "," + minScore + ",'" + comment + "');");
                    stmt.executeUpdate("INSERT INTO all_cardioactivities (username,groupname,activity,date,time,distance,comment) VALUES('" + name + "','" + groupname + "','" + activityType + "','" + date + "'," + time + "," + distance + ",'" + comment + "');");

                    //landing page - displays group after session is logged
                    RequestDispatcher rd = request.getRequestDispatcher("viewGroups");
                    rd.forward(request, response);

                }

                //walk syntax
                if (activityType.startsWith("W")) {
                    //ties the logged activity in with the scoring system 

                    out.println("<input hidden type=\"text\" name=\"groupname\" value=\"" + groupname + "\" readonly=\"readonly\"/>");

                    rs.next();
                    int minScore = time * rs.getInt(3);

                    //adds the new score into the group, inserts a record into the log
                    stmt.executeUpdate("update " + groupname + " set distance = distance +" + distance + ", time = time + " + time + ", score = score + " + minScore + " where name = '" + name + "';");
                    stmt.executeUpdate("INSERT INTO " + groupname + "_log(name,activity,date,log_distance,log_time,log_score,log_comment) VALUES('" + name + "','" + activityType + "','" + date + "'," + distance + "," + time + "," + minScore + ",'" + comment + "');");
                    stmt.executeUpdate("INSERT INTO all_cardioactivities (username,groupname,activity,date,time,distance,comment) VALUES('" + name + "','" + groupname + "','" + activityType + "','" + date + "'," + time + "," + distance + ",'" + comment + "');");

                    //landing page - displays group after session is logged
                    RequestDispatcher rd = request.getRequestDispatcher("viewGroups");
                    rd.forward(request, response);
                }

            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //TIME if statement
        
        if (groupname.startsWith("t_")) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */

                //session handling
                out.println("<input hidden type=\"text\" name=\"groupname\" value=\"" + groupname + "\" readonly=\"readonly\"/>");

                //gets the data from the createCardio jsp
                String name = request.getParameter("name");
                String date = request.getParameter("date");
                int distance = Integer.parseInt(request.getParameter("distance"));
                int time = Integer.parseInt(request.getParameter("time"));
                String comment = request.getParameter("comment");
                String activityType = request.getParameter("activityType");

                //connecting to our db
                dbcon db = new dbcon();
                Connection con = db.getCon();

                //SQL syntax to create the activity in the log, and update the score within the group
                Statement stmt = con.createStatement();
                stmt.executeUpdate("update " + groupname + " set distance = distance +" + distance + ", time = time + " + time + " where name = '" + name + "';");
                stmt.executeUpdate("INSERT INTO " + groupname + "_log(name,activity,date,log_distance,log_time,log_comment) VALUES('" + name + "','" + activityType + "','" + date + "'," + distance + "," + time + ",'" + comment + "');");
                stmt.executeUpdate("INSERT INTO all_cardioactivities (username,groupname,activity,time,distance,comment) VALUES('" + name + "','" + groupname + "','" + activityType + "','" + date + "'," + time + "," + distance + ",'" + comment + "');");

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
