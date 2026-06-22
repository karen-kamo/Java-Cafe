package exception;

public class OutOfStockException extends Exception{
  // constructor method that will receive error message
  public OutOfStockException(String message){
    super(message); // passes the message to the Java parent class
  }
}
