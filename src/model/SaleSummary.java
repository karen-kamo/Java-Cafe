package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class SaleSummary {
  // atributos de SaleSummary
  ArrayList<Order> dailySales; // lista de Order

  // método construtor
  public SaleSummary(){
    // inicia a lista de Order do dia vazia
    this.dailySales = new ArrayList<>();
  }

  // métodos de acessos
  public ArrayList<Order> getDailySales(){
    return this.dailySales;
  }

  // método para adicionar Order ao SaleSummary
  public void addSale(Order order){
    dailySales.add(order);
  }

  // método para retornar todos os pedidos com a data buscada
  public ArrayList<Order> getSalesByDate(LocalDate date){
    ArrayList<Order> filteredSales = new ArrayList<>();
    for (Order order : dailySales){
      if (order.getDate().equals(date)){
        filteredSales.add(order);
      }
    }
    return filteredSales;
  }

  // método para retornar o total de Order
  public int getTransactionCount(){
    return dailySales.size();
  }

  // método para retornar o total do dia
  public double getTotalRevenue(){
    double dailyTotal = 0;
    for (int i = 0; i < dailySales.size(); i++){
      dailyTotal += dailySales.get(i).calculateTotal();
    }

    return dailyTotal;
  }
}
