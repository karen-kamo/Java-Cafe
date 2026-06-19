package persistence;

import java.io.*;
import model.Inventory;
import model.Order;
import model.Product;
import model.SaleSummary;

public class DataManager {
  // método para carregar o estoque
  // vai ser chamado no início da execução
  public static void loadInventory(Inventory inventory) {
    String line = ""; // string com os dados da linha atual
    try (BufferedReader br = new BufferedReader(new FileReader("inventory.csv"))) {
      while ((line = br.readLine()) != null) {
        // proteção para linhas em branco
        if (line.trim().isEmpty()) {
          continue;
        }

        String[] datas = line.split(","); // separa pelas vírgulas

        // deve ter 3 colunas
        if (datas.length < 3) {
          continue;
        }

        String name = datas[0];
        double price = Double.parseDouble(datas[1]);
        int quantity = Integer.parseInt(datas[2]);
        
        Product p = new Product(name, price, quantity);
        inventory.addProduct(p);
      }
    } catch (IOException e) {
      System.out.println("Warning: Stock file not found. Starting empty.");
    }
  }

  // método para reescrever o estoque atualizado
  // vai sobreescrever o arquivo antigo
  public static void saveCompleteInventory(Inventory inventory) {
    // com false para limpar  o arquivo antigo e salva o estoque atual por cima
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("inventory.csv", false))) {
        
      for (Product p : inventory.getListInventory()) {
        // monta a line no formato: id,name,price,quantityStock
        String line = p.getName() + "," + p.getPrice() + "," + p.getStockQuantity() + "\n";
        bw.write(line);
      }
        
    } catch (IOException e) {
      System.out.println("Error saving inventory.: " + e.getMessage());
    }
  }

  // método para carregar as vendas do csv sale_history
  public static void loadSalesHistory(SaleSummary saleSummary, Inventory inventory) {
    String line = ""; // string com os dados da linha atual
    try (BufferedReader br = new BufferedReader(new FileReader("sales_history.csv"))) {
      Order currentOrder = null; // para controlar qual era o ID do último pedido
      int maxIdFound = -1; // para guardar o maior id encontrado

      while ((line = br.readLine()) != null) {
        if (line.trim().isEmpty()) continue; // proteção para linhas em branco

        String[] datas = line.split(","); // separa pelas vírgulas

        if (datas.length < 5) continue; // deve ter 5 colunas

        // extrai dados das linhas
        int orderId = Integer.parseInt(datas[0]);
        java.time.LocalDate saleDate = java.time.LocalDate.parse(datas[1]);
        String productName = datas[2];
        double price = Double.parseDouble(datas[3]);

        // guardar o maior Id visto
        if (orderId > maxIdFound) maxIdFound = orderId;

        // se primeira linha mudouou ID mudou, quer dizer que começõu novo pedido
        if (currentOrder == null || currentOrder.getIdOrder() != orderId){
          currentOrder = new Order(orderId, saleDate);

          saleSummary.addSale(currentOrder); // adiciona pedido ao histórico de vendas
        }

        // coloca produto da linha atual no order
        currentOrder.getListProducts().add(new Product(productName, price, 1));
      }

      // dedpois de ler todo o arquivo, se encontrar id, atualiza
      if (maxIdFound != -1){
        Order.updateGlobalCounter(maxIdFound);
      }

    } catch (IOException e) {
      System.out.println("Warning: Sales history file not found. Starting with zero revenue.");
    }
  } 

  // método para armazenar uma venda no csv de sale_history
  public static void saveSaleRecord(Order order){
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("sales_history.csv", true))){
      // pega a data salva da venda
      String dateStr = order.getDate().toString();

      // varre todos os produtos do pedido
      // armazena ID de order, data de order, o nome do produto e preço
      for (Product p : order.getListProducts()){
        String line = order.getIdOrder() + "," + dateStr + "," + p.getName() + "," + p.getPrice() + ",1\n";
        bw.write(line);
      }
    } catch (IOException e){
      System.out.println("Error saving sale record: " + e.getMessage());
    }
  }
}
