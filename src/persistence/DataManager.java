package persistence;

import java.io.*;
import model.Inventory;
import model.Order;
import model.Product;
import model.SaleSummary;

public class DataManager {
  // method for loading the stock
  public static void loadInventory(Inventory inventory) {
    String line = ""; // it will be a string containing the data from the current row
    try (BufferedReader br = new BufferedReader(new FileReader("inventory.csv"))) {
      while ((line = br.readLine()) != null) {
        // protection for blank lines
        if (line.trim().isEmpty()) continue;

        String[] datas = line.split(","); // separate with commas

        if (datas.length < 3) continue; // it should have 3 columns

        String name = datas[0]; // product name
        double price = Double.parseDouble(datas[1]); // product price
        int quantity = Integer.parseInt(datas[2]); // product stock quantity
        
        Product p = new Product(name, price, quantity);
        inventory.addProduct(p); // add product to inventory
      }
    } catch (IOException e) {
      System.out.println("Warning: Stock file not found. Starting empty.");
    }
  }

  // method for rewriting the updated inventory
  // it will overwrite the old file.
  public static void saveCompleteInventory(Inventory inventory) {
    // use `false` to clear the old file and save the current stock over it.
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("inventory.csv", false))) {
        
      for (Product p : inventory.getListInventory()) {
        // assemble the line in the following format: id,name,price,quantityStock
        String line = p.getName() + "," + p.getPrice() + "," + p.getStockQuantity() + "\n";
        bw.write(line);
      }
        
    } catch (IOException e) {
      System.out.println("Error saving inventory.: " + e.getMessage());
    }
  }

  // method to load sales from csv sales_history
  public static void loadSalesHistory(SaleSummary saleSummary, Inventory inventory) {
    String line = ""; // it will be a string containing the data from the current row

    try (BufferedReader br = new BufferedReader(new FileReader("sales_history.csv"))) {
      Order currentOrder = null; // to check what the ID of the last order was
      int maxIdFound = -1; // to store the highest ID found

      while ((line = br.readLine()) != null) {
        if (line.trim().isEmpty()) continue; //protection for blank lines

        String[] datas = line.split(","); // separate with commas

        if (datas.length < 5) continue; // it should have 5 columns

        // extracts data from the rows
        int orderId = Integer.parseInt(datas[0]); // order ID
        java.time.LocalDate saleDate = java.time.LocalDate.parse(datas[1]); // order date
        String productName = datas[2]; // product name
        double price = Double.parseDouble(datas[3]); // product price

        // save the highest viewed ID
        if (orderId > maxIdFound) maxIdFound = orderId;

        // if the first line has changed or the ID has changed, it means a new request has been started
        if (currentOrder == null || currentOrder.getIdOrder() != orderId){
          currentOrder = new Order(orderId, saleDate);

          saleSummary.addSale(currentOrder); // add order to sales history
        }

        // add product from the current line to the order
        currentOrder.getListProducts().add(new Product(productName, price, 1));
      }

      // after reading the entire file, if it finds an ID, it updates it
      if (maxIdFound != -1) Order.updateGlobalCounter(maxIdFound);

    } catch (IOException e) {
      System.out.println("Warning: Sales history file not found. Starting with zero revenue.");
    }
  } 

  // method to store a sale in the sales_history csv
  public static void saveSaleRecord(Order order){
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("sales_history.csv", true))){
      // retrieves the saved date of the sale
      String dateStr = order.getDate().toString();

      // it goes through all the products in the order
      // stores order ID, order date, product name, and price.
      for (Product p : order.getListProducts()){
        String line = order.getIdOrder() + "," + dateStr + "," + p.getName() + "," + p.getPrice() + ",1\n";
        bw.write(line);
      }
    } catch (IOException e){
      System.out.println("Error saving sale record: " + e.getMessage());
    }
  }
}
