package model;

import java.util.ArrayList;

public class Order {
  // attributes of Order
  private int id;
  private ArrayList<Product> listProducts; // lista de produtos que serão comprados

  // static counter to generate globally unique IDs
  private static int countId = 0;

  // builder construtor
  public Order(){
    this.id = countId;
    countId++; // criar ID automaticamente

    // inicia a lista de pedidos vazia
    listProducts = new ArrayList<>();
  }

  // método de acesso 
  public int getIdOrder(){
    return this.id;
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

  // método para limpar o pedido
  public void clearOrder(){
    listProducts.clear();
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

  // método para gerar o recibo
  public String generateReceipt(){
    String receipt = "======== Java Café ========\n";
    for (int i = 0; i < listProducts.size(); i++){
      receipt += listProducts.get(i).getName() + " - R$ " + listProducts.get(i).getPrice() + "\n";
    }
    receipt += String.format("\nSub-total: R$ %.2f",calculateSubtotal());
    receipt += String.format("\nTax: R$ %.2f", calculateTax());
    receipt += String.format("\nTotal: R$ %.2f", calculateTotal());
    receipt += "\n===========================";
    return receipt;
  }
}
