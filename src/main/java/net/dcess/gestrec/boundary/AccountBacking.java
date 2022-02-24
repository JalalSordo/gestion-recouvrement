package net.dcess.gestrec.boundary;

import net.dcess.gestrec.entity.Account;
package net.dcess.gestrec.control.AccountManager;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import lombok.Data;


@Data
@Named
@ViewScoped
public class AccountBacking implements serializable{

private List<Account> accounts;

    private Account account = new Account();

    @Inject
    private AccountManager accountManager;

    @PostConstruct
    public void init() {
        this.accounts = accountManager.loadAllAccounts();
    }

    public void delete(Account account) {
        accountManager.delete(account);
        accounts.remove(account);
    }

    public void add() {
        accountManager.addNewAccount(account);
        this.accounts = accountManager.loadAllAccounts();
        this.account = new Account();
    }

    public void update() {
        accountManager.update(accounts);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Update successful"));
    }
}
