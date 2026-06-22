package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class SaleSummary {
  // SaleSummary attributes
  ArrayList<Order> sales; // order list

  // constructor method
  public SaleSummary(){
    // starts the Order list empty
    this.sales = new ArrayList<>();
  }

  // attribute access method
  public ArrayList<Order> getSales(){
    return this.sales;
  }

  // method to add Order to SaleSummary
  public void addSale(Order order){
    sales.add(order);
  }

  // method to return all orders with the searched date
  public ArrayList<Order> getSalesByDate(LocalDate date){
    ArrayList<Order> filteredSales = new ArrayList<>();
    for (Order order : sales){
      // if the searched date matches what exists
      if (order.getDate().equals(date)){
        filteredSales.add(order);
      }
    }
    return filteredSales;
  }

  // method to return the total Orders for a specific day
  public int getTransactionCountDaily(LocalDate date){
    // generates a sales list for the desired day
    ArrayList<Order> filteredSales = getSalesByDate(date);
    return filteredSales.size(); // returns the size of the list
  }

  // method to return the daily total
  public double getTotalRevenueDaily(LocalDate date){
    double dailyTotal = 0;
    // generates a sales list for the desired day
    ArrayList<Order> filteredSales = getSalesByDate(date);
    for (int i = 0; i < filteredSales.size(); i++){
      // add up the total of each sale
      dailyTotal += filteredSales.get(i).calculateTotal();
    }

    return dailyTotal;
  }
}
