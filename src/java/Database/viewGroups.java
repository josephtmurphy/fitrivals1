package Database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Database.dbcon;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author josep
 */
public class viewGroups extends HttpServlet {

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
        
        String groupType = request.getParameter("grouptype");
        
        dbcon db = new dbcon();
        Connection con = db.getCon();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        if (groupType.equals("Distance")) {
        try {            
            
            String groupname = request.getParameter("groupname");
            String sql = "Select * from d_"+groupname;

            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            String str = "<table border=1><tr><th>ID</th><th>Name</th><th>Distance</th></tr>";
            
            out.println("Current standings in "+groupname);
            while(rs.next()) {
                str+= "<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>";
            }
            str += "</table>";
            out.println(str);
            con.close();
            
        } catch(Exception e) {
            System.err.println(e);
        }
    }
        
        if (groupType.equals("Score")) {
        try {            
            
            String groupname = request.getParameter("groupname");
            String sql = "Select * from s_"+groupname;

            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            String str = "<table border=1><tr><th>ID</th><th>Name</th><th>Score</th></tr>";
            
            out.println("Current standings in "+groupname);
            while(rs.next()) {
                str+= "<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>";
            }
            str += "</table>";
            out.println(str);
            con.close();
            
        } catch(Exception e) {
            System.err.println(e);
        }
    } 

        if (groupType.equals("Time")) {
        try {            
            
            String groupname = request.getParameter("groupname");
            String sql = "Select * from t_"+groupname;

            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            String str = "<table border=1><tr><th>ID</th><th>Name</th><th>Time</th></tr>";
            
            out.println("Current standings in "+groupname);
            while(rs.next()) {
                str+= "<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>";
            }
            str += "</table>";
            out.println(str);
            con.close();
            
        } catch(Exception e) {
            System.err.println(e);
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
