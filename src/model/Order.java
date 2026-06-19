package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
  // attributes of Order
  private int orderId;
  private ArrayList<Product> listProducts; // lista de produtos que serão comprados
  private LocalDate date; // salvar data da compra

  // static counter to generate globally unique IDs
  private static int countId = 0;

  // builder construtor
  public Order(){
    this.orderId = countId;
    countId++; // criar ID automaticamente

    // inicia a lista de pedidos vazia
    listProducts = new ArrayList<>();
  }

  // métodos de acesso 
  public int getIdOrder(){
    return this.orderId;
  }

  public LocalDate getDate(){
    return date;
  }

  // método para retornar a lista de produtos do pedido
  public ArrayList<Product> getListProducts(){
    return this.listProducts;
  }

  // método para adicionar um produto ao pedido
  public void addProduct(Product product, int quantity){
    for (int i = 0; i < quantity; i++){
      listProducts.add(product);
    }
  }

  // método para retirar um produto do pedido
  public void removeProduct(Product product){
    listProducts.remove(product);
  }

  // método para pegar a data do pedido
  public void finalizeOrder(){
    this.date = LocalDate.now();
  }

  // método para calcular o subtotal dos pedidos
  public double calculateSubtotal(){
    double sumSubtotal = 0;

    for (int i = 0; i < listProducts.size(); i++) {
        double price = listProducts.get(i).getPrice();
        sumSubtotal += price;
    }

    return sumSubtotal;
  }

  // método para calcular a taxa de serviço
  // será uma taxa de 5% do valor do subtotal
  public double calculateTax(){
    double subtotalValue = calculateSubtotal();
    return (subtotalValue * 5) / 100;
  }

  // método para calcular o total a ser pago
  public double calculateTotal(){
    double subtotalValue = calculateSubtotal();
    double taxValue = calculateTax();
    return subtotalValue + taxValue;
  }
}
