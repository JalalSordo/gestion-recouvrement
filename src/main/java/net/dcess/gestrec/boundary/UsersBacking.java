/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.dcess.gestrec.boundary;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import net.dcess.gestrec.dao.UserDao;
import net.dcess.gestrec.entity.User;

/**
 *
 * @author aaytouna
 */

@Data
@Named
@ViewScoped
public class UsersBacking implements Serializable {

    private List<User> users;

    private User user = new User();

    @Inject
    private UserDao userManager;

    @PostConstruct
    public void init() {
        this.users = userManager.loadAllUsers();
    }

    public void delete(User user) {
        userManager.delete(user);
        users.remove(user);
    }

    public void add() {
        userManager.addNewUser(user);
        this.users = userManager.loadAllUsers();
        this.user = new User();
    }

    public void update() {
        userManager.update(users);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Update successful"));
    }

}
