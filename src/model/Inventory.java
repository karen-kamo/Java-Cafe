package model;

import java.util.ArrayList;

public class Inventory {
  // atributos de Inventory
  private ArrayList<Product> listInventory; // lista do estoque

  // builder method
  public Inventory(){
    // inicia a lista de produtos vazia
    listInventory = new ArrayList<>();
  }

  // método de acesso
  public ArrayList<Product> getListInventory(){
    return this.listInventory;
  }

  // método para adicionar um produto novo ao estoque
  public void addProduct(Product product){
    listInventory.add(product);
  }

  // método para procurar um produto pelo nome
  public Product findProduct(String name){
    for (int i = 0; i < listInventory.size(); i++){
      if (listInventory.get(i).getName().equalsIgnoreCase(name)){
        return listInventory.get(i);
      }
    }
    return null; // se não achar, retorna null
  }

  // método para atualizar a quantidade de um produto
  public void updateStockLevel(String name, int newQuantity){
    Product p = findProduct(name);

    // se foi encontrado
    if (p != null){
      p.setStockQuantity(newQuantity);
    }
  }
}
