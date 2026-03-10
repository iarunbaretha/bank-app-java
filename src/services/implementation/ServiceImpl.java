package services;
import java.util.Comparator;

import domain.*;
import exceptions.*;
import reposetory.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class ServiceImpl implements BankServices{
  AccountRepo accountRepo =new AccountRepo();
  TransactionRepo transactionRepo =new TransactionRepo();
   CustomerRepo customerRepo =new CustomerRepo();

  public final Validations<String> validateName =name ->{
    if(name ==null || name.isBlank()) throw new ValidationException("PLEASE ENTER THE NAME IN CORRECT FORMATE");
      };
  public final Validations<String> validateEmail =email ->{
    if(email ==null || email.Contains("@")) throw new ValidationException("PLEASE ENTER THE EMAIL IN CORRECT FORMATE");
      };
  public final Validations<String> validateType =type ->{
    if(Type==null ||Type!=equalsIgnoreCase("SAVINGS")||Type!=equalsIgnoreCase("CURRENT")) throw new ValidationException("PLEASE ENTER THE TYPE  FROM GIVEN OPTIONS ");
      };
 @Override
 public String openAccount(String name ,String email, String accType){
    String customerId = UUID.randomUUID().toString();
   validateName.validate(name);
   validateEmail.validate(email);
   validateType.validate(type);
   Customer customer =new Customer(customerId,name,email);
    customerRepo.save(customer);
   
   String accountNumber =genrateAccNO();
   Account ac = new Account(accountNumber , customerId ,(double)0, accType );
   accountRepo.save(ac);
    return accountNumber;
  }
    private String genrateAccNO(){
         int temp = accountRepo.findAll().size() +1;
      return String.format("AC%06d",temp);
    }
    public List<Account> listAccounts(){
   return accountRepo.findAll();
  }
    public void deposit(String accountNumber ,double amount,String note){
    Account account =accountRepo.searchByNumber(accountNumber).
      orElseThrow(()-> new AccountNotFoundException("Account Not Found :"+accountNumber));
     account.setBalance(account.getBalance() + amount);
      //genrate an trensaction id
     // String transactionId = StringFormat("TID%70d",count);
      String transactionId =UUID.randomUUID().toString();
      Transaction transaction = new Transaction(transactionId,Type.DEPOSIT,account.getAccountNumber(),amount,LocalDateTime.now(),note);
      transactionRepo.add(transaction);
    }
  public void withdraw(String accountNumber ,double amount,String note){
    Account account =accountRepo.searchByNumber(accountNumber).
      orElseThrow(()-> new AccountNotFoundException("Account Not Found :"+accountNumber));
    if(account.getBalance()<amount)
       throw new InsufficentBalanceException("Insufficent Balance!!!");
    account.setBalance(account.getBalance()-amount);
    String transactionId =UUID.randomUUID().toString();
      Transaction transaction = new Transaction(transactionId,Type.WITHDRAW,account.getAccountNumber(),amount,LocalDateTime.now(),note);    
          transactionRepo.add(transaction);
    }
  
   public void transferAmount(String accountNumber,String reciverAccNo,double amount,String note){
     if(accountNumber.equals(reciverAccNo))
       throw new ValidationException("	You cannot transfer to your account");
     //SENDER Acc
      Account account =accountRepo.searchByNumber(accountNumber).
       orElseThrow(()-> new AccountNotFoundException("Account Not Found :"+accountNumber));
     if(account.getBalance()<amount)
       throw new InsufficentBalanceException("Insufficent Balance!!!");
     //RECIVER ACC
      Account recAc =accountRepo.searchByNumber(reciverAccNo).
      orElseThrow(()-> new AccountNotFoundException("Account Not Found :"+reciverAccNo));
      account.setBalance(account.getBalance()-amount);
      recAc.setBalance(recAc.getBalance()+amount);
     String transactionId =UUID.randomUUID().toString();
      Transaction transaction = new Transaction(transactionId,Type.TRANSFER_OUT,recAc.getAccountNumber(),amount,LocalDateTime.now(),note);
     Transaction transaction1 = new Transaction(transactionId,Type.TRANSFER_IN,account.getAccountNumber(),amount,LocalDateTime.now(),note);   
     transactionRepo.add(transaction);
     transactionRepo.add(transaction1);
    }
  
  public List<Account> searchAccountByName(String name){
    String quary = (name==null)?"":name.toLowerCase();
    
     return customerRepo.findAll().stream()
       .filter(c -> c.getName().toLowerCase().contains(quary))
       .flatMap(c -> accountRepo.findByCustomerId(c.getCustomerId()).stream())
       .sorted(Comparator.comparing(Account::getAccountNumber))
       .collect(Collectors.toList());
  }
  
   public List<Transaction>findStatements(String accountNumber){
    return transactionRepo.findStatements(accountNumber).stream()
      .sorted(Comparator.comparing(Transaction::getTimeStamp))
      .collect(Collectors.toList());
     
             }
    
}

