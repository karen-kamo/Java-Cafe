import exception.OutOfStockException;
import model.Inventory;
import model.Order;
import model.Product;
import model.SaleSummary;
import persistence.DataManager;

public class Main {
  public static void mostrarEstoque(Inventory inventory){
    for (Product p : inventory.getListInventory()){
      System.out.println("ID: " + p.getIdProduct() + " | " + p.getName() + " | Qtd: " + p.getStockQuantity());
    }
  }

  public static void main(String[] args) {
    Inventory inventory = new Inventory();
    SaleSummary saleSummary = new SaleSummary();

    DataManager.loadInventory(inventory);
    mostrarEstoque(inventory);

    System.out.println("=== Teste 1: fazer um pedido ====\n");
    Order pedido = new Order();

    // buscar produto no estoque
    Product espresso = inventory.findProduct(0);
    Product cap = inventory.findProduct(1);

    if (espresso != null && cap != null) {
      pedido.addProduct(espresso, 1);
      pedido.addProduct(cap, 1);
    }

    System.out.println(pedido.generateReceipt());

    try {
        for (Product item : pedido .getListProducts()){
          if (item.getStockQuantity() < 1){
            throw new OutOfStockException("Estoque insuficiente para: " + item.getName());
          }
          item.deductStock(1);
        }

        saleSummary.addSale(pedido);
        System.out.println("Venda 1 finalizada com sucesso!");
    } catch (OutOfStockException e) {
      System.out.println("Erro inesperado na venda 1: " + e.getMessage());
    }

    DataManager.saveCompleteInventory(inventory);
    mostrarEstoque(inventory);
   

    System.out.println("\n--- TESTE 3: EXIBINDO O RELATÓRIO FINANCEIRO DO DIA ---");
    System.out.println("Total de transações realizadas: " + saleSummary.getTransactionCount());
    System.out.println(String.format("Faturamento Bruto Total: R$ %.2f", saleSummary.getTotalRevenue()));
    System.out.println("=======================================================");
  }
}