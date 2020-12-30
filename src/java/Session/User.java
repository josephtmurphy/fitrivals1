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
public class User {
    private int id;
    private String email;
    public String username;
    private String fullname;
    private String password;
 
    
   // Getter
    public String getEmail() {
        return email;
    }   
    
    // Setter
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }        
    
   // Getter
    public String getUsername() {
        return username;
    }   

    // Setter
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }    
    
   // Getter
    public String getFullname() {
        return fullname;
    }
    
    // Setter
    public void setFullname(String newFullname) {
        this.fullname = newFullname;
    }        
    
   // Getter
    public String getPassword() {
        return password;
    }
    
    // Setter
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }        
    
// getters and setters are not shown for brevity   
     
}
