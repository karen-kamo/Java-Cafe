package model;

import java.util.ArrayList;

public class Inventory {
  // Inventory attributes
  private ArrayList<Product> listInventory; // stock list

  // builder method
  public Inventory(){
    // starts the empty product list
    listInventory = new ArrayList<>();
  }

  // attribute access method
  public ArrayList<Product> getListInventory(){
    return this.listInventory;
  }

  // method for adding a new product to inventory
  public void addProduct(Product product){
    listInventory.add(product);
  }

  // method for searching for a product by name
  public Product findProduct(String name){
    for (int i = 0; i < listInventory.size(); i++){
      if (listInventory.get(i).getName().equalsIgnoreCase(name)){
        return listInventory.get(i);
      }
    }
    return null; // if it doesn't find it, it returns null.
  }

  // method for updating the quantity of a product
  public void updateStockLevel(String name, int newQuantity){
    Product p = findProduct(name); // use the method of searching by name.

    // if it was found
    if (p != null){
      p.setStockQuantity(newQuantity);
    }
  }
}
