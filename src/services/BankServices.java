
package services;
import java.util.*;
import domain.*;

public interface BankServices{
 
  public String openAccount(String name ,String email, String accType);
  public List<Account> listAccounts();
  public void deposit(String accountNmber ,double amount,String note);
   public void withdraw(String accountNmber ,double amount,String note);
   public void transferAmount(String accountNumber,String reciverAccNo,double amount,String note);
   public List<Account>searchAccountByName(String name);
   public List<Transaction>findStatements(String accountNumber);
    
  
}





