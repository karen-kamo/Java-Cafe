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
    listProducts = new ArrayList<>(); // inicia a lista de pedidos vazia
  }

  // construtor para reconstruir dados do arquivo de vendas
  // para não mexer no countId da memória
  public Order(int orderId, LocalDate date){
    this.orderId = orderId;
    this.date = date;
    this.listProducts = new ArrayList<>();
  }

  // métodos de acesso 
  public int getIdOrder(){
    return this.orderId;
  }

  public void setIdOrder(int id){
    this.orderId = id;
  }

  public LocalDate getDate(){
    return date;
  }

  public void setDate(LocalDate date){
    this.date = date;
  }

  // método para retornar a lista de produtos do pedido
  public ArrayList<Product> getListProducts(){
    return this.listProducts;
  }

  // método para atualizar o contador global do ID
  public static void updateGlobalCounter(int maxId){
    countId = maxId + 1;
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
