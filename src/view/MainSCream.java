package view;

import controller.POSController;
import model.Order;
import model.Product;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	private JButton btnEspresso;
	private JButton btnCappuccino;
	private JButton btnMatchaLatte;
	private JButton btnCheesecake;
	private JButton btnSandwich;
	private JButton btnCroissant;
	private JTextField textFieldItem;
	private JTextField textFieldQuantity;
	private JTextField textFieldTax;
	private JTextField textFieldSubtotal;
	private JTextField textFieldTotal;
	private JTable tableOrder;
	private JTable tableInventory;
	private JTextField textFieldQuantityInv;
	private JTable tableSales;
	private JTextField textFieldDate;
	private JTextField textFieldTotalRevenue;
	private JTextField textFieldNumberTransactions;
	private JLabel lblMsgSales;

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

	public MainSCream(POSController posController) {
		this.controller = posController;

		// configuração da janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// para adicionar as abas
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1097, 665);
		contentPane.add(tabbedPane);
		
		////////////////////////////////////////
		/// 					ABA DE VENDAS
		////////////////////////////////////////
		JPanel salePanel = new JPanel();
		salePanel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Sale", null, salePanel, null);
		salePanel.setLayout(null);
		
		//////////////////////////////////////
		// para mostrar o produto clicado
		// configuração do label e textField de Item
		JLabel lblItem = new JLabel("Item:");
		lblItem.setFont(new Font("Dialog", Font.BOLD, 18));
		lblItem.setBounds(10, 407, 119, 22);
		salePanel.add(lblItem);

		textFieldItem = new JTextField();
		textFieldItem.setEnabled(false);
		textFieldItem.setFont(new Font("Dialog", Font.PLAIN, 18));
		textFieldItem.setBounds(120, 406, 252, 23);
		salePanel.add(textFieldItem);
		textFieldItem.setColumns(10);

		//////////////////////////////////////
		// os itens do menu com a foto do produto
		btnEspresso = new JButton("");
		btnEspresso.setIcon(new ImageIcon(MainSCream.class.getResource("imagens/2.png")));
		btnEspresso.setBounds(10, 11, 175, 175);
		salePanel.add(btnEspresso);

		btnCappuccino = new JButton("");
		btnCappuccino.setIcon(new ImageIcon(MainSCream.class.getResource("imagens/3.png")));
		btnCappuccino.setBounds(197, 11, 175, 175);
		salePanel.add(btnCappuccino);

		btnMatchaLatte = new JButton("");
		btnMatchaLatte.setIcon(new ImageIcon(MainSCream.class.getResource("imagens/4.png")));
		btnMatchaLatte.setBounds(384, 11, 175, 175);
		salePanel.add(btnMatchaLatte);

		btnCheesecake = new JButton("");
		btnCheesecake.setIcon(new ImageIcon(MainSCream.class.getResource("imagens/5.png")));
		btnCheesecake.setBounds(10, 198, 175, 175);
		salePanel.add(btnCheesecake);
		
		btnSandwich = new JButton("");
		btnSandwich.setIcon(new ImageIcon(MainSCream.class.getResource("imagens/6.png")));
		btnSandwich.setBounds(197, 198, 175, 175);
		salePanel.add(btnSandwich);
		
		btnCroissant = new JButton("");
		btnCroissant.setIcon(new ImageIcon(MainSCream.class.getResource("imagens/7.png")));
		btnCroissant.setBounds(384, 198, 175, 175);
		salePanel.add(btnCroissant);
		ActionListener menuListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				String nameProduct = ""; // para guardar nome do produto

				// verificando qual item do menu foi clicado e salvar o nome
				if (e.getSource() == btnEspresso) nameProduct = "Espresso";
				else if (e.getSource() == btnCappuccino) nameProduct = "Cappuccino";
				else if (e.getSource() == btnMatchaLatte) nameProduct = "Matcha Latte";
				else if (e.getSource() == btnCheesecake) nameProduct = "Cheesecake";
				else if (e.getSource() == btnSandwich) nameProduct = "Sandwich";
				else if (e.getSource() == btnCroissant) nameProduct = "Croissant";

				// colocando o nome do produto no TextField de Item
				textFieldItem.setText(nameProduct);
				textFieldQuantity.setText("1");
			}
		};

		btnEspresso.addActionListener(menuListener);
		btnCappuccino.addActionListener(menuListener);
		btnMatchaLatte.addActionListener(menuListener);
		btnCheesecake.addActionListener(menuListener);
		btnSandwich.addActionListener(menuListener);
		btnCroissant.addActionListener(menuListener);
		
		//////////////////////////////////////
		// configuração do label e textField de Quantity
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Dialog", Font.BOLD, 18));
		lblQuantity.setBounds(10, 435, 138, 32);
		salePanel.add(lblQuantity);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setFont(new Font("Dialog", Font.PLAIN, 18));
		textFieldQuantity.setColumns(10);
		textFieldQuantity.setBounds(120, 441, 252, 23);
		salePanel.add(textFieldQuantity);
		
		//////////////////////////////////////
		/// organizando a parte financeira
		// configuração do label e textField de Tax - desabilitado
		JLabel lblTax = new JLabel("Tax:");
		lblTax.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTax.setBounds(10, 476, 138, 21);
		salePanel.add(lblTax);
		
		textFieldTax = new JTextField();
		textFieldTax.setEnabled(false);
		textFieldTax.setFont(new Font("Dialog", Font.PLAIN, 18));
		textFieldTax.setColumns(10);
		textFieldTax.setBounds(120, 476, 252, 23);
		salePanel.add(textFieldTax);
		
		// configuração do label e textField de Subtotal - desabilitado
		JLabel lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setFont(new Font("Dialog", Font.BOLD, 18));
		lblSubtotal.setBounds(10, 515, 150, 14);
		salePanel.add(lblSubtotal);
		
		textFieldSubtotal = new JTextField();
		textFieldSubtotal.setEnabled(false);
		textFieldSubtotal.setFont(new Font("Dialog", Font.PLAIN, 18));
		textFieldSubtotal.setColumns(10);
		textFieldSubtotal.setBounds(120, 511, 252, 23);
		salePanel.add(textFieldSubtotal);
		
		// configuração do label e textField de Total - desabilitado
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTotal.setBounds(10, 547, 119, 23);
		salePanel.add(lblTotal);

		textFieldTotal = new JTextField();
		textFieldTotal.setEnabled(false);
		textFieldTotal.setFont(new Font("Dialog", Font.PLAIN, 18));
		textFieldTotal.setColumns(10);
		textFieldTotal.setBounds(120, 546, 252, 23);
		salePanel.add(textFieldTotal);
		
		//////////////////////////////////////
		// configuração para a tabela de itens do pedido - como recibo
		// para scrollar a tabela
		JScrollPane scrollPaneOrder = new JScrollPane();
		scrollPaneOrder.setBounds(616, 11, 438, 175);
		salePanel.add(scrollPaneOrder);

		tableOrder = new JTable();
		tableOrder.setBackground(new Color(255, 255, 255));
		// definindo o modelo da tabela - colunas
		tableOrder.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item", "Price", "Quantity"
			}
		));
		tableOrder.setFont(new Font("Dialog", Font.PLAIN, 18));
		tableOrder.setRowHeight(32);
		tableOrder.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 18));

		scrollPaneOrder.setViewportView(tableOrder);
		
		//////////////////////////////////////
		// configuração do textArea para mensagens
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		// para scrollar o textArea de mensagens
		JScrollPane scrollPaneTextArea = new JScrollPane(textArea);
		scrollPaneTextArea.setBounds(616, 292, 438, 80);
		salePanel.add(scrollPaneTextArea);

		//////////////////////////////////////
		// Configuração do botão de adicionar produto no pedido
		JButton btnAdd = new JButton("Add Product");
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 18));
		btnAdd.setBounds(802, 441, 252, 23);
		salePanel.add(btnAdd);

		// Adicionando um produto selecionado na tabela
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				String nameProduct = textFieldItem.getText(); // pega o nome do produto

				// se ainda não foi selecionado, manda aviso
				if (nameProduct.isEmpty()){
					textArea.append("\nWARNING: Please, select a product from the menu first.");
					return;
				}

				try {
					// tenta converter o texto para int
					int quantity = Integer.parseInt(textFieldQuantity.getText());

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
					textFieldSubtotal.setText(String.format("%.2f", controller.getSubtotal()));
					textFieldTax.setText(String.format("%.2f", controller.getTax()));
					textFieldTotal.setText(String.format("%.2f", controller.getTotal()));

					// atualiza as linhas da tabela de pedido
					updateTableOrder();

					// envia mensagem de sucesso
					textArea.append(String.format("\nSUCCESS: Added %dx %s to your order.", quantity, nameProduct));

					// limpa os seletores para próximo produto
					textFieldItem.setText("");
					textFieldQuantity.setText("");
					
				} catch (NumberFormatException ex) {
					// se for digitado algo que não é número
					textArea.append("\nERROR: Please enter a whole number on Quantity.");
				}
			}
		});

		//////////////////////////////////////
		// Configuração do botão de remover produto do pedido
		JButton btnRemove = new JButton("Remove");
		btnRemove.setFont(new Font("Dialog", Font.BOLD, 18));
		btnRemove.setBounds(802, 476, 252, 23);
		salePanel.add(btnRemove);

		// Removendo um item pelo evento disparado pelo botão
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				// guarda a linha selecionada
				int selectedRow = tableOrder.getSelectedRow();

				// verifica se tem alguma linha selecionada
				if (selectedRow == -1){
					textArea.append("\nWARNING: Please, select a line from the table to remove.");
					return;
				}

				DefaultTableModel modelTable = (DefaultTableModel) tableOrder.getModel();
        String productName = modelTable.getValueAt(selectedRow, 0).toString();

        // aciona o controller para remover
        controller.removeSingleItemFromOrder(productName);

        // atualiza a parte financeira
        textFieldSubtotal.setText(String.format("%.2f", controller.getSubtotal()));
        textFieldTax.setText(String.format("%.2f", controller.getTax()));
        textFieldTotal.setText(String.format("%.2f", controller.getTotal()));

        // atualiza a tabela
        updateTableOrder();

        textArea.append(String.format("\nSUCCESS: Removed 1x %s from your order.", productName));
			}
		});

		//////////////////////////////////////
		// Configuração do botão de confirmar o pedido e evento
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Dialog", Font.BOLD, 18));
		btnConfirm.setBounds(802, 511, 252, 58);
		salePanel.add(btnConfirm);

		// Realizando o pedido pelo evento disparado pelo botão
		btnConfirm.addActionListener((new ActionListener() {
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
					textFieldSubtotal.setText("0.00");
					textFieldTax.setText("0.00");
					textFieldTotal.setText("0.00");

					// limpa a tabela
					updateTableOrder();

					// atualiza a tabela de estoque
					updateTableInventory();

					// exibe mensagem de venda bem sucedida
					textArea.append("\nSUCCESS: Transaction completed! Stock updated successfully.");


				} catch (Exception ex) {
					// se der algum erro inesperado
					textArea.append("\nERROR: Failed to finalize purchase.");
				}
			}
		}));

		//////////////////////////////////////
		// configuração do fundo da janela de venda
		JLabel backgroundSaleTab = new JLabel("");
		backgroundSaleTab.setIcon(new ImageIcon(MainSCream.class.getResource("imagens/FundoJavaCafe_Venda.png")));
		backgroundSaleTab.setBounds(-68, -34, 1200, 713);
		salePanel.add(backgroundSaleTab);

		////////////////////////////////////////
		/// 					ABA DE ESTOQUE
		////////////////////////////////////////
		// configurando a aba de inventory
		JPanel inventoryPanel = new JPanel();
		tabbedPane.addTab("Inventory", null, inventoryPanel, null);
		inventoryPanel.setLayout(null);
		
		// configuração da tabela de estoque
		tableInventory = new JTable();
		tableInventory.setFont(new Font("Dialog", Font.PLAIN, 18));
		tableInventory.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 18));
		tableInventory.setRowHeight(35);
		// terá 3 colunas - Item, Price e Quantity
		tableInventory.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Item", "Price", "Quantity"
			}
		));

		// adicionando um scroll para a tabela
		JScrollPane scrollPaneInventory = new JScrollPane(tableInventory);
		scrollPaneInventory.setBounds(61, 48, 952, 251);
		inventoryPanel.add(scrollPaneInventory);
		
		//////////////////////////////////////
		// configuração do label e textField de Quantity
		JLabel lblQuantityInv = new JLabel("Quantity:");
		lblQuantityInv.setFont(new Font("Dialog", Font.BOLD, 18));
		lblQuantityInv.setBounds(61, 323, 146, 35);
		inventoryPanel.add(lblQuantityInv);
		
		textFieldQuantityInv = new JTextField();
		textFieldQuantityInv.setFont(new Font("Dialog", Font.PLAIN, 18));
		textFieldQuantityInv.setBounds(165, 330, 252, 23);
		inventoryPanel.add(textFieldQuantityInv);
		textFieldQuantityInv.setColumns(10);
		
		//////////////////////////////////////
		// configuração do botão de Add do Quantity
		JButton btnAddQuantity = new JButton("Add");
		btnAddQuantity.setFont(new Font("Dialog", Font.BOLD, 18));
		btnAddQuantity.setBounds(499, 330, 252, 23);
		inventoryPanel.add(btnAddQuantity);
		
		//////////////////////////////////////
		// configuração do botão de Remove do Quantity
		JButton btnRemoveQuantity = new JButton("Remove");
		btnRemoveQuantity.setFont(new Font("Dialog", Font.BOLD, 18));
		btnRemoveQuantity.setBounds(761, 330, 252, 23);
		inventoryPanel.add(btnRemoveQuantity);

		//////////////////////////////////////
		// configuração do label para exibir mensagens ao usuário
		JLabel lblMsgInv = new JLabel("");
		lblMsgInv.setBounds(100, 310, 500, 200);
		inventoryPanel.add(lblMsgInv);

		// associando ações do usuário ao botão com os eventos
		ActionListener inventoryListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				// verificar se alguma linha da tabela foi selecionada
				int selectedRow = tableInventory.getSelectedRow();
				if (selectedRow == -1){
					lblMsgInv.setText("<html><font color='orange'><b>WARNING:</b></font> Please, select an item from the inventory table first.</html>");
					return;
				}

				// tenta pegar quantidade desejada no textField
				int quantityInput = 0;
				try {
					quantityInput = Integer.parseInt(textFieldQuantityInv.getText());
					if (quantityInput <= 0){
						lblMsgInv.setText("<html><font color='orange'><b>WARNING:</b></font> Quantity must be greater than zero.</html>");
						return;
					}
				} catch (NumberFormatException ex) {
					lblMsgInv.setText("<html><font color='red'><b>ERROR:</b></font> Please enter a whole number in the quantity field.</html>");
					return;
				}

				// pega o nome do produto da linha selecionada
				DefaultTableModel modelTable = (DefaultTableModel) tableInventory.getModel();
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
					textFieldQuantityInv.setText("");
					lblMsgInv.setText(String.format("<html><font color='green'><b>SUCCESS:</b></font> Added %d units to %s stock.</html>", quantityInput, productName));

				}  else if (e.getSource() == btnRemoveQuantity){
					// verifica se a quantidade que temos é suficiente para remover
					if (productSelected.getStockQuantity() < quantityInput){
						lblMsgInv.setText("<html><font color='orange'><b>WARNING:</b></font> Cannot remove more items than available in stock.</html>");
						return;
					}

					// reduz a quantidade desejada
					productSelected.deductStock(quantityInput);
					lblMsgInv.setText(String.format("<html><font color='green'><b>SUCCESS:</b></font> Removed %d units from %s stock.</html>", quantityInput, productName));

					// salvas as alterações no arquivo csv
					persistence.DataManager.saveCompleteInventory(controller.getInventory());

					// atualiza os componentes visuais e limpa o texto
					updateTableInventory();
					textFieldQuantityInv.setText("");
				}
			}
		};

		btnAddQuantity.addActionListener(inventoryListener);
    btnRemoveQuantity.addActionListener(inventoryListener);
		
		//////////////////////////////////////
		// configuração do fundo da janela de inventory
		JLabel backgroundInventoryTab = new JLabel("");
		backgroundInventoryTab.setIcon(new ImageIcon(MainSCream.class.getResource("imagens/FundoJavaCafe_Estoque.png")));
		backgroundInventoryTab.setBounds(-68, -34, 1200, 713);
		inventoryPanel.add(backgroundInventoryTab);
		
		////////////////////////////////////////
		/// 					ABA DO ADMIN
		////////////////////////////////////////
		// configurando a aba de Manager Area
		JPanel admPanel = new JPanel();
		tabbedPane.addTab("Manager Area", null, admPanel, null);
		admPanel.setLayout(null);
	
		//////////////////////////////////////
		// configuração da tabela de sales
		tableSales = new JTable();
		tableSales.setBounds(21, 78, 720, 167);
		tableSales.setFont(new Font("Dialog", Font.PLAIN, 18));
		tableSales.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 18));
		tableSales.setRowHeight(35);
		// terá 3 colunas - Date, Product Name and Price 
		tableSales.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Date", "Product", "Price"
			}
		));
		admPanel.add(tableSales);

		// para scrollar a tabela de sales
		JScrollPane scrollPaneTableSales = new JScrollPane(tableSales);
		scrollPaneTableSales.setBounds(21, 78, 1044, 360);
		admPanel.add(scrollPaneTableSales);

		//////////////////////////////////////
		// configuração da label para exigir mensagens ao usuário
		lblMsgSales = new JLabel("");
		lblMsgSales.setBounds(10, 547, 500, 30);
		admPanel.add(lblMsgSales);
		
		//////////////////////////////////////
		// configuração do label e textField de Date - para busca das vendas
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDate.setBounds(21, 37, 89, 14);
		admPanel.add(lblDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setFont(new Font("Dialog", Font.PLAIN, 18));
		textFieldDate.setBounds(83, 33, 252, 23);
		textFieldDate.setColumns(10);
		admPanel.add(textFieldDate);
		
		//////////////////////////////////////
		// configuração do botão de Research para buscar por date
		JButton btnResearch = new JButton("Research");
		btnResearch.setFont(new Font("Dialog", Font.BOLD, 18));
		btnResearch.setBounds(813, 33, 252, 23);
		admPanel.add(btnResearch);

		btnResearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == btnResearch){
					// pega a data digitada
					String inputDate = textFieldDate.getText().trim(); 

					// verifica se o campo está vazio
					if (inputDate.isEmpty()){
						lblMsgSales.setText("<html><font color='orange'><b>WARNING:</b></font> Please enter a date (YYYY-MM-DD).</html>");
						return;
					}

					try {
						// tenta converter o texto para uma data válida
						java.time.LocalDate searchDate = java.time.LocalDate.parse(inputDate);
						lblMsgSales.setText(""); // limpa a mensagem anterior

						// chama a função que busca e atualiza a tabela
						updateDashboard(searchDate);
					} catch (java.time.format.DateTimeParseException ex) {
						lblMsgSales.setText("<html><font color='red'><b>ERROR:</b></font> Invalid format. Use YYYY-MM-DD.</html>");
					}
				}
		}});
		
		//////////////////////////////////////
		// configuração do label e textField de Total Revenue - desabilitado
		JLabel lblTotalRevenue = new JLabel("Total revenue:");
		lblTotalRevenue.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTotalRevenue.setBounds(21, 465, 193, 14);
		admPanel.add(lblTotalRevenue);

		textFieldTotalRevenue = new JTextField();
		textFieldTotalRevenue.setEnabled(false);
		textFieldTotalRevenue.setFont(new Font("Dialog", Font.PLAIN, 18));
		textFieldTotalRevenue.setColumns(10);
		textFieldTotalRevenue.setBounds(175, 460, 252, 23);
		admPanel.add(textFieldTotalRevenue);
		
		//////////////////////////////////////
		// configuração do label e textField de NumberTransactions - desabilitado
		JLabel lblNumberTransactions = new JLabel("Number of transactions:");
		lblNumberTransactions.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNumberTransactions.setBounds(586, 465, 226, 14);
		admPanel.add(lblNumberTransactions);
		
		textFieldNumberTransactions = new JTextField();
		textFieldNumberTransactions.setEnabled(false);
		textFieldNumberTransactions.setFont(new Font("Dialog", Font.PLAIN, 18));
		textFieldNumberTransactions.setColumns(10);
		textFieldNumberTransactions.setBounds(813, 460, 252, 23);
		admPanel.add(textFieldNumberTransactions);

		//////////////////////////////////////
		// configuração do fundo da janela de Manager Area
		JLabel backgroundAdminTab = new JLabel("");
		backgroundAdminTab.setIcon(new ImageIcon(MainSCream.class.getResource("imagens/FundoJavaCafe_Adm.png")));
		backgroundAdminTab.setBounds(-68, -34, 1200, 713);
		admPanel.add(backgroundAdminTab);

		// carregando a tabela de estoque pelo primeira vez
		updateTableInventory();
	}

	// método para atualizar a tabela de pedidos
	private void updateTableOrder(){
		// pegamos o modelo de dados relacionado ao JTable
		DefaultTableModel modelTable = (DefaultTableModel) tableOrder.getModel();

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
		DefaultTableModel modelTable = (DefaultTableModel) tableInventory.getModel();
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

	// método que vai atualizar a aba de admin dependendo da data procurada pelo manager
	private void updateDashboard(java.time.LocalDate searchDate){
		DefaultTableModel modelSales = (DefaultTableModel) tableSales.getModel(); // pega modelo da tabela Sales
		modelSales.setRowCount(0); // limpa a tabela antes de buscar

		// pega a lista de pedidos que ocorreu na data buscada
		ArrayList<Order> filteredOrders = controller.getSaleSummary().getSalesByDate(searchDate);

		// percorre os pedidos e insere cada produto nas linhas da tabela
		for (Order order : filteredOrders){
			for (Product p : order.getListProducts()){
				modelSales.addRow(new Object[]{
					order.getDate().toString(),
					p.getName(),
					String.format("%.2f", p.getPrice())
				});
			}
		}

		// atualiza os campos financeiros
		double totalRevenue = controller.getSaleSummary().getTotalRevenueDaily(searchDate);
		int totalTransactions = controller.getSaleSummary().getTransactionCountDaily(searchDate);

		textFieldTotalRevenue.setText(String.format("$ %.2f", totalRevenue));
		textFieldNumberTransactions.setText(String.valueOf(totalTransactions));

		if (totalTransactions == 0){
			lblMsgSales.setText("No sales records found for this date.");
		} else {
			lblMsgSales.setText(""); // limpa o aviso se estiver tudo OK
		}
	}
}
		
		

