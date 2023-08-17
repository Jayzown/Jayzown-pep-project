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
        AccountDAO accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }
    /*------------------------------------------------------------------------------------------------------------------- */

    public Account userRegistration(Account account){
        if((account.getUsername()!=null) && (account.getPassword().length()>=4) && (accountDAO.getAccountByUsername(account)==null)){
            return accountDAO.userRegistration(account);
        } System.out.println(account.getUsername());
        return null;
    }


    
}
