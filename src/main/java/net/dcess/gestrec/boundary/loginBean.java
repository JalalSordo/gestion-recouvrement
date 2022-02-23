/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.dcess.gestrec.boundary;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import net.dcess.gestrec.control.LoginManager;
import net.dcess.gestrec.entity.Account;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author aaytouna
 */
@SessionScoped
@Data
@Named("loginBean")
public class loginBean implements Serializable {
     
    private static final long serialVersionUID = 1L;
    private String password;
    private String message, uname;
    
    @Inject
    private LoginManager loginManager;
    
     public String loginProject() {
        Account result = loginManager.GetAccount(uname, password);
        if (result != null) {

            // get Http Session and store username
            HttpSession session = Util.getSession();
            session.setAttribute("username", uname);            
            return "index";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalid Login!",
                    "Please Try Again!"));
            
             // invalidate session, and redirect to other pages
             //message = "Invalid Login. Please Try Again!";
            return "login";
        }
    }
 
    public String logout() {
      HttpSession session = Util.getSession();
      session.invalidate();
      return "login";
   }
}
