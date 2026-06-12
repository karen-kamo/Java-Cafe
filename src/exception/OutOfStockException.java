package exception;

public class OutOfStockException extends Exception{
  // método construtor que vai receber mensagem de erro
  public OutOfStockException(String message){
    super(message); // passa a mensagem para a classe pai do Java
  }
}
