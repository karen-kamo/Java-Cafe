package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import model.Inventory;
import model.Order;
import model.Product;
import model.SaleSummary;
import persistence.DataManager;

public class POSController {
  private Inventory inventory;
  private Order currentOrder;
  private SaleSummary saleSummary;

  // build constructor
  public POSController(){
    // creates objects of the inventory and SalesSummary classes
    this.inventory = new Inventory();
    this.saleSummary = new SaleSummary();

    // when the cash register opens, it loads the inventory and sales history
    DataManager.loadInventory(inventory);
    DataManager.loadSalesHistory(saleSummary, inventory);

    this.currentOrder = new Order();
  }

  // actions that will be triggered by the buttons

  // method for adding a product to an order
  public void addItemToOrder(String productName, int quantity){
    // goes through the entire stock
    for (Product p : inventory.getListInventory()) {
      // search for product by name
      if (p.getName().equalsIgnoreCase(productName)){
        currentOrder.addProduct(p, quantity); // add product to order
        break;
      }
    }
  }

  // method to check if there is stock for the product
  public boolean hasEnoughStock(String productName, int quantityRequested){
    // goes through the entire stock
    for (Product pInv : inventory.getListInventory()){
      // search for product by name
      if (pInv.getName().equalsIgnoreCase(productName)){
        // count how many of these products are in your cart
        int quantityOnOrder = 0;
        // browse the list of items in your order
        for (Product pOrder : currentOrder.getListProducts()){
          if (pOrder.getName().equalsIgnoreCase(productName)){
            // means you have
            quantityOnOrder++;
          }
        }

        // if what you already have plus what you want to add exceeds the stock, return false
        if (quantityOnOrder + quantityRequested > pInv.getStockQuantity()) return false;

        return true;
      }
    }
    return false;
  }

  // method to remove 1 item from the order
  public void removeSingleItemFromOrder(String productName){
    // find the first occurrence of the product in the list and remove it
    for (Product p : currentOrder.getListProducts()){
      if (p.getName().equalsIgnoreCase(productName)){
        currentOrder.removeProduct(p); // remove from order
        break;
      }
    }
  }

  // completion of the current sale
  public void checkoutCurrentOrder(){
    // updating inventory product by product
    for (Product p : currentOrder.getListProducts()){
      for (Product stockProduct : inventory.getListInventory()){
        if (stockProduct.getName().equalsIgnoreCase(p.getName())){
          stockProduct.deductStock(1); // decreases product by product
          break;
        }
      }
    }

    currentOrder.finalizeOrder(); // get the order date

    DataManager.saveSaleRecord(currentOrder); // save the order

    saleSummary.addSale(currentOrder); // add the sale to the daily report

    DataManager.saveCompleteInventory(inventory); // saves the updated inventory back to the csv

    this.currentOrder = new Order(); // resets the order, creating a new one for the next customer
  }

  // return methods for populating the interface screen with data
  public ArrayList<Product> getInventoryList(){
    return inventory.getListInventory();
  }

  public ArrayList<Product> getCurrentOrderProducts(){
    return currentOrder.getListProducts();
  }

  public Inventory getInventory(){
    return this.inventory;
  }

  public SaleSummary getSaleSummary(){
    return this.saleSummary;
  }

  // reuse of Order's financial methods
  public double getSubtotal() { return currentOrder.calculateSubtotal(); }
  public double getTax() { return currentOrder.calculateTax(); }
  public double getTotal() { return currentOrder.calculateTotal(); }

  // reuse of SaleSummary's financial methods
  public double getTotalRevenueDaily(LocalDate date) { return saleSummary.getTotalRevenueDaily(date); }
  public int getTransactionCountDaily(LocalDate date) { return saleSummary.getTransactionCountDaily(date);}

}
