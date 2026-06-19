package model;

public class Product {
  // attributes of Product
  private String name;
  private double price;
  private int stockQuantity;

  // builder method
  public Product(String name, double price, int stockQuantity){
    this.name = name;
    this.price = price;
    this.stockQuantity  = stockQuantity;
  }

  // access methods for attributes
  public String getName(){
    return this.name;
  }

  public double getPrice(){
    return this.price;
  }

  public int getStockQuantity(){
    return this.stockQuantity;
  }

  public void setStockQuantity(int stockQuantity){
    this.stockQuantity = stockQuantity;
  }

  // method to reduce inventory quantity
  // when product is purchased
  public void deductStock(int quantity){
    this.stockQuantity -= quantity;
  }
}
