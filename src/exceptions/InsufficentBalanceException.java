package exceptions;


public class InsufficentBalanceException extends RuntimeException{
  public  InsufficentBalanceException(String msg) {
   super(msg);
  }
  
}
