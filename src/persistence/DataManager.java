package persistence;

import java.io.*;
import model.Inventory;
import model.Product;

public class DataManager {
  // método para carregar o estoque
  // vai ser chamado no início da execução
  public static void loadInventory(Inventory inventory) {
    String line = ""; // string com os dados da linha atual
    try (BufferedReader br = new BufferedReader(new FileReader("inventory.csv"))) {
      while ((line = br.readLine()) != null) {
        String[] datas = line.split(","); // separa pelas vírgulas
        String name = datas[1];
        double price = Double.parseDouble(datas[2]);
        int quantity = Integer.parseInt(datas[3]);
        
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
        String line = p.getIdProduct() + "," + p.getName() + "," + p.getPrice() + "," + p.getStockQuantity() + "\n";
        bw.write(line);
      }
        
    } catch (IOException e) {
      System.out.println("Error saving inventory.: " + e.getMessage());
    }
  }
}
