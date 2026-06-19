package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class SaleSummary {
  // atributos de SaleSummary
  ArrayList<Order> sales; // lista de Order

  // método construtor
  public SaleSummary(){
    // inicia a lista de Order vazia
    this.sales = new ArrayList<>();
  }

  // métodos de acessos
  public ArrayList<Order> getSales(){
    return this.sales;
  }

  // método para adicionar Order ao SaleSummary
  public void addSale(Order order){
    sales.add(order);
  }

  // método para retornar todos os pedidos com a data buscada
  public ArrayList<Order> getSalesByDate(LocalDate date){
    ArrayList<Order> filteredSales = new ArrayList<>();
    for (Order order : sales){
      if (order.getDate().equals(date)){
        filteredSales.add(order);
      }
    }
    return filteredSales;
  }

  // método para retornar o total de Order de um dia específico
  public int getTransactionCountDaily(LocalDate date){
    ArrayList<Order> filteredSales = getSalesByDate(date);
    return filteredSales.size();
  }

  // método para retornar o total do dia
  public double getTotalRevenueDaily(LocalDate date){
    double dailyTotal = 0;
    ArrayList<Order> filteredSales = getSalesByDate(date);
    for (int i = 0; i < filteredSales.size(); i++){
      dailyTotal += filteredSales.get(i).calculateTotal();
    }

    return dailyTotal;
  }
}
