package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
  // Order attributes
  private int orderId; // ID by order
  private ArrayList<Product> listProducts; // list of products to be purchased
  private LocalDate date; // save purchase date

  // static counter to generate globally unique IDs
  private static int countId = 0;

  // builder construtor
  public Order(){
    this.orderId = countId;
    countId++; // every time a new order is generated, the ID is incremented
    listProducts = new ArrayList<>(); // starts the empty order list
  }

  // a constructor to rebuild sales file data without
  // altering the countId stored in memory.
  public Order(int orderId, LocalDate date){
    this.orderId = orderId;
    this.date = date;
    this.listProducts = new ArrayList<>();
  }

  // attribute access method
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

  // method to return the list of products from the order
  public ArrayList<Product> getListProducts(){
    return this.listProducts;
  }

  // method to update the global ID counter
  public static void updateGlobalCounter(int maxId){
    countId = maxId + 1;
  }

  // method for adding a product to an order
  public void addProduct(Product product, int quantity){
    for (int i = 0; i < quantity; i++){
      listProducts.add(product);
    }
  }

  // method for removing a product from an order
  public void removeProduct(Product product){
    listProducts.remove(product);
  }

  // method for stamping the order date
  public void finalizeOrder(){
    this.date = LocalDate.now();
  }

  // method for calculating the subtotal of products ordered
  public double calculateSubtotal(){
    double sumSubtotal = 0;

    for (int i = 0; i < listProducts.size(); i++) {
        double price = listProducts.get(i).getPrice();
        sumSubtotal += price;
    }

    return sumSubtotal;
  }

  // method for calculating the service fee
  // it will be a rate of 5% of the subtotal value.
  public double calculateTax(){
    double subtotalValue = calculateSubtotal();
    return (subtotalValue * 5) / 100;
  }

  // method for calculating the total to be paid
  public double calculateTotal(){
    double subtotalValue = calculateSubtotal();
    double taxValue = calculateTax();
    return subtotalValue + taxValue;
  }
}
