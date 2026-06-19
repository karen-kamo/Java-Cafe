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
    try (BufferedReader br = new BufferedReader(new FileReader("inventory.csv"))) {
      while ((line = br.readLine()) != null) {
        // proteção para linhas em branco
        if (line.trim().isEmpty()) {
          continue;
        }

        String[] datas = line.split(","); // separa pelas vírgulas

        // deve ter 4 colunas
        if (datas.length < 4) {
          continue;
        }

        java.time.LocalDate saleDate = java.time.LocalDate.parse(datas[0]);
        String productName = datas[1];
        double price = Double.parseDouble(datas[2]);

        // cria um pedido para colocar no SaleSummary 
        Order oldOrder = new Order();

        // atribui a data e coloca os produtos relacionados com ela
        oldOrder.getListProducts().add(new Product(productName, price, 1));
        saleSummary.addSale(oldOrder);
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
      for (Product p : order.getListProducts()){
        String line = dateStr + "," + p.getName() + "," + p.getPrice() + ",1\n";
        bw.write(line);
      }
    } catch (IOException e){
      System.out.println("Error saving sale record: " + e.getMessage());
    }
  }
}
