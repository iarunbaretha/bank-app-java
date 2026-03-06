package domain;
import java.util.*;
import java.time.LocalDateTime;

public class Transaction {
  
  private String id;
  private Type type;
  private String accountNumber;
  private double amount;
  private LocalDateTime timeStamp ;
  private String note;
  
  public Transaction(String id,Type type, String accountNumber,double amount, LocalDateTime timeStamp , String note){
    this.id=id;
    this.type=type;
    this.accountNumber=accountNumber;
    this.amount=amount;
    this. timeStamp=timeStamp ;
    this.note=note;
  }
  
  public String getId(){
    return id;
  }
 
     public void setId(String customerId){
      this.id= id;
    }
   public String getAccountNumber(){
    return accountNumber;
  }
  public void setAccountNumber(String accountNumber){
    this.accountNumber=accountNumber;
  }
  public Double getAmount(){
      return amount;
    }
    
     public void setAmount(double amount){
      this.amount=amount;
    }
    public Type getType(){
      return type;
    }
     public void SetType(Type type){
      this.type=type;
    }
     public  LocalDateTime getTimeStamp(){
       return timeStamp;
     }
     public void setTimeStamp( LocalDateTime timeStamp){
       this.timeStamp =timeStamp;
     }
   
    public String getNote(){
      return note;
    }
     public void SetNote(String note){
      this.note=note;
    }
}

