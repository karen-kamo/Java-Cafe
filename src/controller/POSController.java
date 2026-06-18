package controller;

import exception.OutOfStockException;
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
    this.inventory = new Inventory();
    this.currentOrder = new Order();
    this.saleSummary = new SaleSummary();

    // quando o caixa abre, carrega o estoque
    DataManager.loadInventory(inventory);
  }

  // ações que vão disparadas pelos botões
  public void addItemToOrder(String productName, int quantity){
    // procura produto pelo nome
    for (Product p : inventory.getListInventory()) {
      if (p.getName().equalsIgnoreCase(productName)){
        currentOrder.addProduct(p, quantity); // adiciona product na order
        break;
      }
    }
  }

  // finalização da venda atual
  public void checkoutCurrentOrder() throws OutOfStockException{
    // verifica se tem estoque de todos os produtos do pedido
    for (Product p : currentOrder.getListProducts()){
      if (p.getStockQuantity() < 1){
        throw new OutOfStockException("Insufficient stock to " + p.getName());
      }
    }

    // se possui estoque, tem que diminuir estoque
    for (Product p : currentOrder.getListProducts()){
      p.deductStock(1);
    }

    // adiciona a venda para o relatório do dia
    saleSummary.addSale(currentOrder);

    // salva o estoque atualizado de volta no csv 
    DataManager.saveCompleteInventory(inventory);

    // reseta o pedido criando um novo para próximo cliente
    this.currentOrder = new Order();
  }

  // métodos de retorno para preencher a tela de interface com dados
  public ArrayList<Product> getInventoryList(){
    return inventory.getListInventory();
  }

  public ArrayList<Product> getCurrentOrderProducts(){
    return currentOrder.getListProducts();
  }

  public double getSubtotal() { return currentOrder.calculateSubtotal(); }
  public double getTax() { return currentOrder.calculateTax(); }
  public double getTotal() { return currentOrder.calculateSubtotal(); }

  public double getTotalRevenue() { return saleSummary.getTotalRevenue(); }
  public int getTransactionCount() { return saleSummary.getTransactionCount();}

}
