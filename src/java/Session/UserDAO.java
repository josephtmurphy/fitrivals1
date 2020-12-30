/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

/**
 *
 * @author josep
 */
import Database.dbcon;
import java.sql.*;
 
public class UserDAO {
 
    public User checkLogin(String email, String password) throws SQLException,
            ClassNotFoundException {
        //connecting to our db
        dbcon db = new dbcon();
        Connection con = db.getCon();
 

        String sql = "SELECT * FROM users WHERE user_email = ? and password = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
 
        ResultSet result = statement.executeQuery();
 
        User user = null;
 
        if (result.next()) {
            user = new User();
            user.setUsername(result.getString("username"));
            user.setEmail(email);
        }
 
        con.close();
 
        return user;
    }
}