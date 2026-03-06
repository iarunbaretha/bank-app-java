package reposetory;
import java.util.*;
import domain.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
public class AccountRepo {
 
 private Map<String ,Account> accountByNumber=new HashMap<>();
 public void save(Account ac){
   accountByNumber.put(ac.getAccountNumber(),ac);
 }
  public List<Account> findAll(){
  return new ArrayList<>(accountByNumber.values());
  }
  public Optional<Account>  searchByNumber(String accountNumber)
  {
    return  Optional.ofNullable(accountByNumber.get(accountNumber));
  }
  public Optional<Account>  searchByName(String name)
  {
    return  Optional.ofNullable(accountByNumber.get(name));
  }
  public List<Account> findByCustomerId(String customerId){
    List<Account> result=new ArrayList<>();
    for(Account a :accountByNumber.values()){
      if(a.getCustomerId().equals(customerId))
         result.add(a);
    }
    return result;
  }
}
