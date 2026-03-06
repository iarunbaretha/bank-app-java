package app.main;

import java.util.*;
import services.*;
import domain.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
class Main {
  public static void main(String[] args) {
   
    Scanner sc =new Scanner(System.in);
    BankServices bankService =new ServiceImpl();
    boolean running =true;
    System.out.println("Welcome TO console Bank");
   /* System.out.println(""" Choose on of the Option :
           1) Create Account
           2) Debit 
           3) Deposite
           4) Transfer
           5) Accounts List
           6) Search Account by the name of customer
           7) Exit
      """);
      */
    while(running){
     System.out.println( "      1) Create Account");
     System.out.println( "      2) Withdraw");
     System.out.println( "      3) Deposit");
     System.out.println( "      4) Transfer");
     System.out.println( "      5) Accounts Statement");
     System.out.println( "      6) List Accounts ");
     System.out.println( "      7) Search Account by the name of customer");
     System.out.println( "      0) Exit");
     System.out.println( "          Thank you ");
     int input;
    do{
     input =sc.nextInt();
      if(input>7)
        System.out.println("you enters an wrong option");
    }
    while(input>7);
    sc.nextLine();
      
    switch(input){
         case 1 -> openAccount(sc,bankService);
         case 2 -> withdraw(sc,bankService);
         case 3 -> deposit(sc,bankService);
         case 4 -> transfer(sc,bankService);
         case 5 -> statements(sc,bankService);
         case 6 -> listAccount(sc,bankService); 
         case 7 -> searchAccount(sc,bankService);
        
         case 0 -> running=false;
    }
    }
  }
    //1
    
    public static void openAccount(Scanner sc,BankServices bankService){
    
     System.out.println("Enter your name");
     String name =sc.nextLine().trim();
      System.out.println("Enter email address");
     String email =sc.nextLine().trim();
      System.out.println("Enter Account type(Saving /Current)");
     String accType =sc.nextLine().trim(); 
      System.out.println("How much intial amount you want to deposite ?");
      double intialDeposit =sc.nextDouble();
       sc.nextLine();
     String accountNumber= bankService.openAccount(name,email,accType);
       if(intialDeposit>0){
        bankService.deposit(accountNumber,intialDeposit,"Intial deposit");
      }
      System.out.println("Account Opened :"+accountNumber);
   
  }
  //2
   
  public static void deposit(Scanner sc , BankServices bankService){
    System.out.println("Account Number :");
    String accountNumber =sc.nextLine();
    System.out.println("Amount");
    double amount =sc.nextDouble();
    sc.nextLine();
     String note ="Deposit";
     bankService.deposit(accountNumber,amount,note);
    System.out.println("......Deposited.....");
  }
   
  //3
  
   public static void withdraw(Scanner sc , BankServices bankService){
    System.out.println("Account Number :");
    String accountNumber =sc.nextLine();
    System.out.println("Amount");
    double amount =sc.nextDouble();
     String note ="Withdraw";
     sc.nextLine();
    bankService.withdraw(accountNumber,amount,note);
    System.out.println("......Withdrowed.....");
    
    //System.out.println("Transection ID :"+ bankService.transactionId + " |Account No :"+accountNumber +" | Balance :"+Account.getBalance);
    
  }
  //4
  public static void transfer(Scanner sc ,BankServices bankService){
    System.out.println("Enter the Account your number");
    String accountNumber=sc.nextLine().trim();
    System.out.println("Enter the Account Number in which you want to Transfer amount");
    String reciverAccNo =sc.nextLine().trim();
    System.out.println("Enter the Amount :");
    double amount =sc.nextDouble();
    sc.nextLine();    
    String note ="Transfer";
    bankService.transferAmount(accountNumber,reciverAccNo,amount,note);
    System.out.println("Amount Transfered");
  }
  
  //6 LIST ACCOUNT
  
  public static void listAccount(Scanner sc ,BankServices bankService){
   bankService.listAccounts().forEach( a ->{
     System.out.println(a.getAccountNumber()+"|"+a.getAccountType()+"|"+a.getBalance());
   });
  }
  //5 STATEMENT
  public static void statements(Scanner sc,BankServices bankService){
    System.out.println("Enter AccountNumber :");
    String accountNumber =sc.nextLine().trim();
    bankService.findStatements(accountNumber).forEach(  t-> {
      System.out.println(t.getTimeStamp()+" |"+t.getType()+" |"+t.getAmount()+" |"+t.getNote());
    });
    
  }
  //7 SEARCH ACCOUNT BY NAME
  public static void searchAccount(Scanner sc,BankServices bankService){
    System.out.println("Enter the Customer Name:");
    String name = sc.nextLine().trim();
    
    bankService.searchAccountByName(name).forEach(account ->
    System.out.println(" |AcNo :"+account.getAccountNumber()+" |AcType :"+
                   account.getAccountType()+" |Balance :"+account.getBalance() +"|")
  );
   }
  
  

    
}

