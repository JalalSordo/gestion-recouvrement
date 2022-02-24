package net.dcess.gestrec.boundary;

/**
 *
 * @author jalal sordo
 */
import net.dcess.gestrec.entity.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import javax.inject.Named;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.dcess.gestrec.dao.AuthenticationDao;

@Data
@Named
@Slf4j
@SessionScoped
public class LoginBacking implements Serializable {

    private static final long serialVersionUID = -9107952969237527819L;

    public static final String HOME_PAGE_REDIRECT
            = "/secured/index.xhtml?faces-redirect=true";
    public static final String LOGOUT_PAGE_REDIRECT
            = "/login.xhtml?faces-redirect=true";

    private String userId;
    private String userPassword;

    private User currentUser;

    @Inject
    private AuthenticationDao authenticationDao;

    public String login() {
        // lookup the user based on user name and user password
        currentUser = authenticationDao.getUser(userId, userPassword);

        if (currentUser != null) {
            log.info("login successful for '{}'", userId);

            return HOME_PAGE_REDIRECT;
        } else {
            log.info("login failed for '{}'", userId);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Login failed",
                            "Invalid or unknown credentials."));

            return null;
        }
    }

    public String logout() {
        String identifier = userId;

        // invalidate the session
        log.debug("invalidating session for '{}'", identifier);
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();

        log.info("logout successful for '{}'", identifier);
        return LOGOUT_PAGE_REDIRECT;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public String isLoggedInForwardHome() {
        if (isLoggedIn()) {
            return HOME_PAGE_REDIRECT;
        }

        return null;
    }

}
