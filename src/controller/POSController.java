package controller;

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

  // ações que vão ser disparadas pelos botões

  // método para adicionar produto no pedido
  public void addItemToOrder(String productName, int quantity){
    // procura produto pelo nome
    for (Product p : inventory.getListInventory()) {
      if (p.getName().equalsIgnoreCase(productName)){
        currentOrder.addProduct(p, quantity); // adiciona product na order
        break;
      }
    }
  }

  // método para verificar se existe estoque para o produto
  public boolean hasEnoughStock(String productName, int quantityRequested){
    for (Product pInv : inventory.getListInventory()){
      if (pInv.getName().equalsIgnoreCase(productName)){

        // conta quantos produtos desse tem no carrinho
        int quantityOnOrder = 0;
        for (Product pOrder : currentOrder.getListProducts()){
          if (pOrder.getName().equalsIgnoreCase(productName)){
            // quer dizer que tem 
            quantityOnOrder++;
          }
        }

        // se já o que tem + o que quer colocar passa do estoque, retorn falso
        if (quantityOnOrder + quantityRequested > pInv.getStockQuantity()) return false;

        return true;
      }
    }
    return false;
  }

  // método para remover 1 item do pedido
  public void removeSingleItemFromOrder(String productName){
    // procura na lista a primeira ocorrência do produto e remove
    for (Product p : currentOrder.getListProducts()){
      if (p.getName().equalsIgnoreCase(productName)){
        currentOrder.removeProduct(p); // remove do pedido
        break;
      }
    }
  }

  // finalização da venda atual
  public void checkoutCurrentOrder(){
    // dando baixa no estoque produto a produto
    for (Product p : currentOrder.getListProducts()){
      for (Product stockProduct : inventory.getListInventory()){
        if (stockProduct.getName().equalsIgnoreCase(p.getName())){
          stockProduct.deductStock(1); // da baixa item por item
          break;
        }
      }
    }

    // pega a data do pedido
    currentOrder.finalizeOrder();

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

  public Inventory getInventory(){
    return this.inventory;
  }

  public double getSubtotal() { return currentOrder.calculateSubtotal(); }
  public double getTax() { return currentOrder.calculateTax(); }
  public double getTotal() { return currentOrder.calculateTotal(); }

  public double getTotalRevenue() { return saleSummary.getTotalRevenue(); }
  public int getTransactionCount() { return saleSummary.getTransactionCount();}

}
