package Service;
import DAO.AccountDAO;
import java.util.List;
import Model.Account;
import java.util.*;

public class AccountService {

    AccountDAO accountDAO;

    /*------------------------------------------------------------------------------------------------------------------ */
    /*Constructors for AccountService. One in which no arguments are taken and the other where an AccountDAO object is */
    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }
    /*------------------------------------------------------------------------------------------------------------------- */

    public Account userRegistration(Account account){
        String username = account.getUsername();
        Account returnAccount = null;
        if((account.getUsername()!="")&&(account.getPassword().length()>=4)&&(accountDAO.getAccountByUsername(username)==null)){
            returnAccount = accountDAO.userRegistration(account);
        }
        return returnAccount;
    }


    
}
