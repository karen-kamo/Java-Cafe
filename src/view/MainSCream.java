package view;

import controller.POSController;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MainSCream extends JFrame {

	private static final long serialVersionUID = 1L;

	private POSController controller;

	private JPanel contentPane;
	private JTextField textField_Item;
	private JTextField textField_Quantity;
	private JTable table;
	private JTextField textField_Tax;
	private JTextField textField_Subtotal;
	private JTextField textField_total;
	private JTable table_1;
	private JTextField textField;
	private JTable table_2;
	private JTextField textField_Data;
	private JTextField textField_TotalRevenue;
	private JTextField textField_NumberOfTransitions;
	private JButton botaoEspresso;
	private JButton botaoCappuccino;
	private JButton botaoMatchaLatte;
	private JButton botaoCheesecake;
	private JButton botaoSandwich;
	private JButton botaoCroissant;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POSController posController = new POSController();
					MainSCream frame = new MainSCream(posController);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainSCream(POSController posController) {
		this.controller = posController;

		// configuração da janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// para adicionar as abas
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1097, 761);
		contentPane.add(tabbedPane);
		
		////////////////////////////////////////
		/// 					ABA DE VENDAS
		////////////////////////////////////////
		JPanel salePanel = new JPanel();
		salePanel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Sale", null, salePanel, null);
		salePanel.setLayout(null);
		
		// para mostrar o produto clicado
		// configuração do label
		JLabel lbl_Item = new JLabel("Item:");
		lbl_Item.setBounds(10, 248, 210, 14);
		salePanel.add(lbl_Item);

		// configuração do textField de Item
		textField_Item = new JTextField();
		textField_Item.setEnabled(false);
		textField_Item.setBounds(67, 245, 153, 20);
		salePanel.add(textField_Item);
		textField_Item.setColumns(10);
		
		// os itens do menu com a foto do produto
		botaoCheesecake = new JButton("");
		botaoCheesecake.setIcon(new ImageIcon(MainSCream.class.getResource("imagens/5.png")));
		botaoCheesecake.setBounds(10, 122, 100, 100);
		salePanel.add(botaoCheesecake);
		
		botaoSandwich = new JButton("");
		botaoSandwich.setIcon(new ImageIcon(MainSCream.class.getResource("imagens/6.png")));
		botaoSandwich.setBounds(120, 122, 100, 100);
		salePanel.add(botaoSandwich);
		
		botaoCroissant = new JButton("");
		botaoCroissant.setIcon(new ImageIcon(MainSCream.class.getResource("imagens/7.png")));
		botaoCroissant.setBounds(231, 122, 100, 100);
		salePanel.add(botaoCroissant);
		
		botaoMatchaLatte = new JButton("");
		botaoMatchaLatte.setIcon(new ImageIcon(MainSCream.class.getResource("imagens/4.png")));
		botaoMatchaLatte.setBounds(231, 11, 100, 100);
		salePanel.add(botaoMatchaLatte);
		
		botaoCappuccino = new JButton("");
		botaoCappuccino.setIcon(new ImageIcon(MainSCream.class.getResource("imagens/3.png")));
		botaoCappuccino.setBounds(120, 11, 100, 100);
		salePanel.add(botaoCappuccino);
		
		botaoEspresso = new JButton("");
		botaoEspresso.setIcon(new ImageIcon(MainSCream.class.getResource("imagens/2.png")));
		botaoEspresso.setBounds(10, 11, 100, 100);
		salePanel.add(botaoEspresso);

		ActionListener menuListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				String nameProduct = ""; // para guardar nome do produto

				// verificando qual item do menu foi clicado
				// e salvar o nome
				if (e.getSource() == botaoEspresso){
					nameProduct = "Espresso";
				}  else if (e.getSource() == botaoCappuccino){
					nameProduct = "Cappuccino";
				} else if (e.getSource() == botaoMatchaLatte){
					nameProduct = "Matcha Latte";
				} else if (e.getSource() == botaoCheesecake){
					nameProduct = "Cheesecake";
				} else if (e.getSource() == botaoSandwich){
					nameProduct = "Sandwich";
				} else if (e.getSource() == botaoCroissant){
					nameProduct = "Croissant";
				}

				// colocando o nome do produto no TextField de Item
				textField_Item.setText(nameProduct);
				textField_Quantity.setText("1");
			}
		};

		botaoEspresso.addActionListener(menuListener);
		botaoCappuccino.addActionListener(menuListener);
		botaoMatchaLatte.addActionListener(menuListener);
		botaoCheesecake.addActionListener(menuListener);
		botaoSandwich.addActionListener(menuListener);
		botaoCroissant.addActionListener(menuListener);
		
		JLabel lbl_Quantity = new JLabel("Quantity:");
		lbl_Quantity.setBounds(10, 279, 58, 14);
		salePanel.add(lbl_Quantity);
		
		textField_Quantity = new JTextField();
		textField_Quantity.setColumns(10);
		textField_Quantity.setBounds(67, 276, 153, 20);
		salePanel.add(textField_Quantity);
		
		// configuração para a tabela de itens do pedido
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(442, 18, 310, 146);
		salePanel.add(scrollPane);

		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item", "Price", "Quantity"
			}
		));

		scrollPane.setViewportView(table);
		
		// configuração do textField Tax - desabilitado
		JLabel lbl_Quantity_1 = new JLabel("Tax:");
		lbl_Quantity_1.setBounds(10, 313, 58, 14);
		salePanel.add(lbl_Quantity_1);
		
		textField_Tax = new JTextField();
		textField_Tax.setEnabled(false);
		textField_Tax.setColumns(10);
		textField_Tax.setBounds(67, 310, 153, 20);
		salePanel.add(textField_Tax);
		
		// configuração do textField Subtotal - desabilitado
		JLabel lbl_Quantity_1_1 = new JLabel("Subtotal:");
		lbl_Quantity_1_1.setBounds(10, 346, 58, 14);
		salePanel.add(lbl_Quantity_1_1);
		
		textField_Subtotal = new JTextField();
		textField_Subtotal.setEnabled(false);
		textField_Subtotal.setColumns(10);
		textField_Subtotal.setBounds(67, 343, 153, 20);
		salePanel.add(textField_Subtotal);
		
		// configuração do textField Total - desabilitado
		textField_total = new JTextField();
		textField_total.setEnabled(false);
		textField_total.setColumns(10);
		textField_total.setBounds(67, 375, 153, 20);
		salePanel.add(textField_total);
		
		JLabel lbl_Quantity_1_1_1 = new JLabel("Total:");
		lbl_Quantity_1_1_1.setBounds(10, 378, 58, 14);
		salePanel.add(lbl_Quantity_1_1_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JScrollPane scrollPane2 = new JScrollPane(textArea);
		scrollPane2.setBounds(442, 200, 310, 59);

		salePanel.add(scrollPane2);

		//////////////////////////////////////

		// Configuração do botão de adicionar produto no pedido
		JButton btnAdd = new JButton("Add Product");
		btnAdd.setBounds(592, 280, 163, 23);
		salePanel.add(btnAdd);

		// Adicionando um produto selecionado na tabela
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				String nameProduct = textField_Item.getText(); // pega o nome do produto

				// se ainda não foi selecionado, manda aviso
				if (nameProduct.isEmpty()){
					textArea.append("\nWARNING: Please, select a product from the menu first.");
					return;
				}

				try {
					// tenta converter o texto para int
					int quantity = Integer.parseInt(textField_Quantity.getText());

					// verifica se a quantidade é menor ou igual a zero
					if (quantity <= 0){
						textArea.append("\nWARNING: Quantity must be greater than zero in the quantity field.");
						return;
					} 

					// verificando se tem no estoque
					if (!controller.hasEnoughStock(nameProduct, quantity)) {
        		textArea.append(String.format("\nWARNING: Insufficient stock for %s.", nameProduct));
						return;
					}

					// para adicionar a quantidade desejada no controller
					controller.addItemToOrder(nameProduct, quantity);

					// atualiza a parte financeira na tela
					textField_Subtotal.setText(String.format("%.2f", controller.getSubtotal()));
					textField_Tax.setText(String.format("%.2f", controller.getTax()));
					textField_total.setText(String.format("%.2f", controller.getTotal()));

					// atualiza as linhas da tabela de pedido
					updateTableOrder();

					// envia mensagem de sucesso
					textArea.append(String.format("\nSUCESS: Added %dx %s to your order.", quantity, nameProduct));

					// limpa os seletores para próximo produto
					textField_Item.setText("");
					textField_Quantity.setText("");
					
				} catch (NumberFormatException ex) {
					// se for digitado algo que não é número
					textArea.append("\nERROR: Please enter a whole number.");
				}
			}
		});

		//////////////////////////////////////
		
		// Configuração do botão de remover produto do pedido
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(592, 309, 163, 23);
		salePanel.add(btnRemove);

		// Removendo um produto selecionado na tabela
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				// guarda a linha selecionada
				int selectedRow = table.getSelectedRow();

				// verifica se tem alguma linha selecionada
				if (selectedRow == -1){
					textArea.append("\nWARNING: Please, select a line from the table to remove.");
					return;
				}

				DefaultTableModel modelTable = (DefaultTableModel) table.getModel();
        String productName = modelTable.getValueAt(selectedRow, 0).toString();

        // aciona o controller para remover
        controller.removeSingleItemFromOrder(productName);

        // atualiza a parte financeira
        textField_Subtotal.setText(String.format("%.2f", controller.getSubtotal()));
        textField_Tax.setText(String.format("%.2f", controller.getTax()));
        textField_total.setText(String.format("%.2f", controller.getTotal()));

        // atualiza a tabela
        updateTableOrder();

        textArea.append(String.format("\nSUCCESS: Removed 1x %s from your order.", productName));
			}
		});

		//////////////////////////////////////
		
		// Configuração do botão de confirmar o pedido
		JButton btnNewButton_Confirm = new JButton("Confirm");
		btnNewButton_Confirm.setBounds(592, 346, 163, 51);
		salePanel.add(btnNewButton_Confirm);

		// Confirmando a venda do pedido
		btnNewButton_Confirm.addActionListener((new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				// verifica se o pedido não está vazio
				if (controller.getCurrentOrderProducts().isEmpty()){
					textArea.append("\nWARNING: Cannot confirm an empty order.");
					return;
				}

				try {
					// chama controller para processar baixa no estoque e salvar
					controller.checkoutCurrentOrder();

					// zera os campos financeiros
					textField_Subtotal.setText("0.00");
					textField_Tax.setText("0.00");
					textField_total.setText("0.00");

					// limpa a tabela
					updateTableOrder();

					// atualiza a tabela de estoque
					updateTableInventory();

					// exibe mensagem de venda bem sucedida
					textArea.append("\nSUCCESS: Transaction completed! Stock updated successfully.");


				} catch (Exception ex) {
					// se der algum erro inesperado
					textArea.append("\nERROR: Failed to finalize purchase. " + ex.getMessage());
				}
			}
		}));

		//////////////////////////////////////
		
		// Configuração do fundo da janela de venda
		JLabel fundo = new JLabel("");
		fundo.setIcon(new ImageIcon(MainSCream.class.getResource("imagens/fundoJavaCafe.png")));
		fundo.setBounds(0, -29, 795, 506);
		salePanel.add(fundo);

		////////////////////////////////////////
		/// 					ABA DE ESTOQUE
		////////////////////////////////////////
		
		JPanel inventoryPanel = new JPanel();
		tabbedPane.addTab("Inventory", null, inventoryPanel, null);
		inventoryPanel.setLayout(null);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Item", "Price", "Quantity"
			}
		));

		JScrollPane scrollPane3 = new JScrollPane(table_1);
		scrollPane3.setBounds(29, 48, 720, 226);

		inventoryPanel.add(scrollPane3);
		
		JLabel lblNewLabel = new JLabel("Quantity:");
		lblNewLabel.setBounds(100, 310, 69, 20);
		inventoryPanel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(157, 309, 135, 23);
		inventoryPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnAddQuantity = new JButton("Add");
		btnAddQuantity.setBounds(411, 307, 135, 23);
		inventoryPanel.add(btnAddQuantity);
		
		JButton btnRemoveQuantity = new JButton("Remove");
		btnRemoveQuantity.setBounds(560, 307, 135, 23);
		inventoryPanel.add(btnRemoveQuantity);

		JLabel lblMsg = new JLabel("");
		lblMsg.setBounds(100, 310, 200, 200);
		inventoryPanel.add(lblMsg);

		ActionListener inventoryListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				// verificar se alguma linha da tabela foi selecionada
				int selectedRow = table_1.getSelectedRow();
				if (selectedRow == -1){
					lblMsg.setText("WARNING: Please, select an item from the inventory table first.");
					return;
				}

				// tenta pegar quantidade desejada no textField
				int quantityInput = 0;
				try {
					quantityInput = Integer.parseInt(textField.getText());
					if (quantityInput <= 0){
						lblMsg.setText("WARNING: Quantity must be greater than zero.");
						return;
					}
				} catch (NumberFormatException ex) {
					lblMsg.setText("ERROR: Please enter a whole number in the quantity field.");
					return;
				}

				// pega o nome do produto da linha selecionada
				DefaultTableModel modelTable = (DefaultTableModel) table_1.getModel();
				String productName = modelTable.getValueAt(selectedRow, 0).toString();

				// procura o produto correspodene na lista do controller
				model.Product productSelected = null;
				for (model.Product p : controller.getInventoryList()){
					if (p.getName().equalsIgnoreCase(productName)){
						productSelected = p;
						break;
					}
				}

				// verifica se não encontrou
				if (productSelected == null) return;

				// verificando qual botão da aba de estoque foi clicado
				if (e.getSource() == btnAddQuantity){
					int newQuantity = productSelected.getStockQuantity() + quantityInput;
					productSelected.setStockQuantity(newQuantity);

					// salvas as alterações no arquivo csv
					persistence.DataManager.saveCompleteInventory(controller.getInventory());

					// atualiza os componentes visuais e limpa o texto
					updateTableInventory();

					// atualiza a tela
					textField.setText("");
					lblMsg.setText(String.format("SUCCESS: Added %d units to %s stock.", quantityInput, productName));

				}  else if (e.getSource() == btnRemoveQuantity){
					// verifica se a quantidade que temos é suficiente para remover
					if (productSelected.getStockQuantity() < quantityInput){
						lblMsg.setText("WARNING: Cannot remove more items than available in stock.");
						return;
					}

					// reduz a quantidade desejada
					productSelected.deductStock(quantityInput);
					lblMsg.setText(String.format("SUCCESS: Removed %d units from %s stock.", quantityInput, productName));

					// salvas as alterações no arquivo csv
					persistence.DataManager.saveCompleteInventory(controller.getInventory());

					// atualiza os componentes visuais e limpa o texto
					updateTableInventory();
					textField.setText("");
				}
			}
		};

		btnAddQuantity.addActionListener(inventoryListener);
    btnRemoveQuantity.addActionListener(inventoryListener);

		
		////////////////////////////////////////
		/// 					ABA DO ADMIN
		////////////////////////////////////////
		
		JPanel admPanel = new JPanel();
		tabbedPane.addTab("Manager Area", null, admPanel, null);
		admPanel.setLayout(null);
		
		table_2 = new JTable();
		table_2.setBounds(21, 78, 720, 167);
		admPanel.add(table_2);

		JLabel lblMsg2 = new JLabel("");
		lblMsg2.setBounds(100, 310, 200, 200);
		admPanel.add(lblMsg2);
		
		JLabel lblNewLabel_1 = new JLabel("Date:");
		lblNewLabel_1.setBounds(21, 37, 59, 14);
		admPanel.add(lblNewLabel_1);
		
		textField_Data = new JTextField();
		
		textField_Data.setBounds(59, 34, 243, 20);
		admPanel.add(textField_Data);
		textField_Data.setColumns(10);
		
		JButton btnNewButton = new JButton("Research");
		btnNewButton.setBounds(652, 33, 89, 23);
		admPanel.add(btnNewButton);
		
		textField_TotalRevenue = new JTextField();
		textField_TotalRevenue.setEnabled(false);
		textField_TotalRevenue.setColumns(10);
		textField_TotalRevenue.setBounds(109, 273, 224, 20);
		admPanel.add(textField_TotalRevenue);
		
		JLabel lblNewLabel_1_1 = new JLabel("Total revenue:");
		lblNewLabel_1_1.setBounds(21, 276, 112, 14);
		admPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Number of transitions:");
		lblNewLabel_1_1_1.setBounds(384, 276, 161, 14);
		admPanel.add(lblNewLabel_1_1_1);
		
		textField_NumberOfTransitions = new JTextField();
		textField_NumberOfTransitions.setEnabled(false);
		textField_NumberOfTransitions.setColumns(10);
		textField_NumberOfTransitions.setBounds(517, 273, 224, 20);
		admPanel.add(textField_NumberOfTransitions);

		updateTableInventory();
	}

	// método para atualizar a tabela de pedidos
	private void updateTableOrder(){
		// pegamos o modelo de dados relacionado ao JTable
		DefaultTableModel modelTable = (DefaultTableModel) table.getModel();

		// limpamos todas as linhas antigas
		modelTable.setRowCount(0);

		// chamamos o controller para pegar a lista atual
		for (model.Product p : controller.getCurrentOrderProducts()){
			// adiciona linha com as 3 colunas configuradas
			modelTable.addRow(new Object[]{
				p.getName(),
				String.format("%.2f", p.getPrice()),
				1
			});
		}
  }

	// método para atualizar a tela de estoque
	private void updateTableInventory(){
		DefaultTableModel modelTable = (DefaultTableModel) table_1.getModel();
		modelTable.setRowCount(0); // limpando as linhas antigas da tabela

		// varre todo o estoque real que o controller carregou
		for (model.Product p : controller.getInventoryList()){
			modelTable.addRow(new Object[]{
				p.getName(),
				String.format("%.2f", p.getPrice()),
				p.getStockQuantity()
			});
		}
	}
}
