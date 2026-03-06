package reposetory;
import java.util.*;
import domain.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class TransactionRepo {
  
  private  Map<String ,List<Transaction>> txByAccount=new HashMap<>();
  public void add(Transaction transaction){
   //txByAccount.computeIfAbsent(transaction.getAccountNumber(),
   //                k ->new ArrayList<>()).add(transaction);
 List <Transaction> list=txByAccount.computeIfAbsent(transaction.getAccountNumber(),
                   k ->new ArrayList<>());
    list.add(transaction);
  }
   public List<Transaction> findStatements(String accountNumber){
  
     return new ArrayList<>(txByAccount.getOrDefault(accountNumber,Collections.emptyList()));
  }
  
}
