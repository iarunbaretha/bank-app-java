package domain;
import java.util.*;

import services.*;
public class Account {
 private String accountNumber;
 private String customerId;
 private double balance;
 private String accType; 
   Scanner sc =new Scanner(System.in);
  public Account(String accountNumber ,String customerId, double balance, String accType){
  
 this.accountNumber=accountNumber;
 this.customerId=customerId;
 this.balance=balance;
 this.accType=accType; 
  }
  public String getAccountNumber(){
      return accountNumber;
    }
      public void setAccountNumber(String accountNumber){
      this.accountNumber= accountNumber;
    }
     public String getCustomerId(){
      return customerId;
    }
    
     public void setCustomerId(String customerId){
      this.customerId= customerId;
    }
    
     public Double getBalance(){
      return balance;
    }
    
     public void setBalance(double balance){
      this.balance=balance;
    }
    
     public String getAccountType(){
      return accType;
    }
     public void SetAccountType(String accType){
      this.accType=accType;
    }
   
    
    
  
    
    
   
   
  }
    
  
  
  


