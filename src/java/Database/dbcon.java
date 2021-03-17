/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josep
 */

//class which can be called in other forms to connect to FitRivals SQL db
public class dbcon {
    Connection con;    
    
    //method that connects the project to the mySQL database, referenced in most servlets
    public Connection getCon() 
    {
        try {
            //mysql address
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitrivals?useSSL=false","root","Supreme2010*");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbcon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(dbcon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
