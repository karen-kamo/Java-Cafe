package model;

public class Product {
  // attributes of Product
  private int id;
  private String name;
  private double price;
  private int stockQuantity;

  // static counter to generate globally unique IDs
  private static int countId = 0;

  // builder method
  public Product(String name, double price, int stockQuantity){
    this.id = countId;
    this.name = name;
    this.price = price;
    this.stockQuantity  = stockQuantity;

    countId++; // when instantiated, increment
  }

  // access methods for attributes
  public int getIdProduct(){
    return this.id;
  }

  public String getName(){
    return this.name;
  }

  public void setName(String name){
    this.name = name; 
  }

  public double getPrice(){
    return this.price;
  }

  public void setPrice(double price){
    this.price = price;
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
