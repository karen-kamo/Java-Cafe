package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
//import java.awt.ScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;

public class MainSCream extends JFrame {

	private static final long serialVersionUID = 1L;

	//private controller.POSController controller;

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
					MainSCream frame = new MainSCream();
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
	public MainSCream() {
		// configuração da janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// para adicionar as abas
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1097, 765);
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
		lbl_Item.setFont(new Font("Dialog", Font.BOLD, 18));
		lbl_Item.setBounds(10, 468, 119, 23);
		salePanel.add(lbl_Item);

		// configuração do textField
		textField_Item = new JTextField();
		textField_Item.setFont(new Font("Dialog", Font.PLAIN, 18));
		textField_Item.setBounds(120, 467, 252, 23);
		salePanel.add(textField_Item);
		textField_Item.setColumns(10);
		
		// os itens do menu com a foto do produto
		botaoCheesecake = new JButton("");
		botaoCheesecake.setIcon(new ImageIcon(MainSCream.class.getResource("/imagens/5.png")));
		botaoCheesecake.setBounds(10, 198, 175, 175);
		salePanel.add(botaoCheesecake);
		
		botaoSandwich = new JButton("");
		botaoSandwich.setIcon(new ImageIcon(MainSCream.class.getResource("/imagens/6.png")));
		botaoSandwich.setBounds(197, 198, 175, 175);
		salePanel.add(botaoSandwich);
		
		botaoCroissant = new JButton("");
		botaoCroissant.setIcon(new ImageIcon(MainSCream.class.getResource("/imagens/7.png")));
		botaoCroissant.setBounds(384, 198, 175, 175);
		salePanel.add(botaoCroissant);
		
		botaoMatchaLatte = new JButton("");
		botaoMatchaLatte.setIcon(new ImageIcon(MainSCream.class.getResource("/imagens/4.png")));
		botaoMatchaLatte.setBounds(384, 11, 175, 175);
		salePanel.add(botaoMatchaLatte);
		
		botaoCappuccino = new JButton("");
		botaoCappuccino.setIcon(new ImageIcon(MainSCream.class.getResource("/imagens/3.png")));
		botaoCappuccino.setBounds(197, 11, 175, 175);
		salePanel.add(botaoCappuccino);
		
		botaoEspresso = new JButton("");
		botaoEspresso.setIcon(new ImageIcon(MainSCream.class.getResource("/imagens/2.png")));
		botaoEspresso.setBounds(10, 11, 175, 175);
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
		lbl_Quantity.setFont(new Font("Dialog", Font.BOLD, 18));
		lbl_Quantity.setBounds(10, 501, 138, 20);
		salePanel.add(lbl_Quantity);
		
		textField_Quantity = new JTextField();
		textField_Quantity.setColumns(10);
		textField_Quantity.setBounds(120, 498, 252, 23);
		salePanel.add(textField_Quantity);
		
		// configuração para a tabela de itens do pedido
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(744, 26, 310, 146);
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
		
		JLabel lbl_Quantity_1 = new JLabel("Tax:");
		lbl_Quantity_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lbl_Quantity_1.setBounds(10, 533, 138, 21);
		salePanel.add(lbl_Quantity_1);
		
		textField_Tax = new JTextField();
		textField_Tax.setColumns(10);
		textField_Tax.setBounds(120, 532, 252, 23);
		salePanel.add(textField_Tax);
		
		JLabel lbl_Quantity_1_1 = new JLabel("Subtotal:");
		lbl_Quantity_1_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lbl_Quantity_1_1.setBounds(10, 567, 150, 14);
		salePanel.add(lbl_Quantity_1_1);
		
		textField_Subtotal = new JTextField();
		textField_Subtotal.setColumns(10);
		textField_Subtotal.setBounds(120, 566, 252, 23);
		salePanel.add(textField_Subtotal);
		
		textField_total = new JTextField();
		textField_total.setColumns(10);
		textField_total.setBounds(120, 601, 252, 23);
		salePanel.add(textField_total);
		
		JLabel lbl_Quantity_1_1_1 = new JLabel("Total:");
		lbl_Quantity_1_1_1.setBounds(24, 617, 58, 14);
		salePanel.add(lbl_Quantity_1_1_1);
		
		JButton btnNewButton_Confirm = new JButton("Confirm");
		btnNewButton_Confirm.setBounds(592, 346, 163, 51);
		salePanel.add(btnNewButton_Confirm);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JScrollPane scrollPane2 = new JScrollPane(textArea);
		scrollPane2.setBounds(600, 200, 310, 59);

		salePanel.add(scrollPane2);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(592, 309, 163, 23);
		salePanel.add(btnRemove);

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

				// se ainda não foi selecionado, manda mensagem
				if (nameProduct.isEmpty()){
					textArea.append("\nWARNING: Please, select a product from the menu first.");
					return;
				}

				try {
					// tenta converter o texto para int
					int quantity = Integer.parseInt(textField_Quantity.getText());

					// verifica se a quantidade é maior que zero
					if (quantity <= 0){
						textArea.append("\nWARNING: Quantity must be greater than zero.");
					}

					// loop para adicionar a quantidade desejada no controller
					// for (int i = 0; i < quantity; i++){
					// 	controller.addItemToOrder(nameProduct);
					// }

					// atualiza a parte financeira na tela
					// textField_Subtotal.setText(String.format("%.2f", controller.getSubtotal()));
					// textField_Tax.setText(String.format("%.2f", controller.getTax()));
					// textField_total.setText(String.format("%.2f", controller.getTotal()));

					// atualiza as linhas da tabela de pedido
					//updateTableOrder();

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

		////////////////////////////////////////
		/// 					ABA DE ESTOQUE
		////////////////////////////////////////
		
		JPanel inventoryPanel = new JPanel();
		tabbedPane.addTab("Inventory", null, inventoryPanel, null);
		inventoryPanel.setLayout(null);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"Espresso", "1.00", "0"},
				{"Cappuccino", "1.70", "0"},
				{"Matcha Latte", "2.20", "0"},
				{"Cheesecake", "2.50", "0"},
				{"Sandwich", "3.00", "0"},
				{"Croissant", "3.00", "0"},
			},
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
		
		JButton btnNewButton_Add = new JButton("Add");
		btnNewButton_Add.setBounds(411, 307, 135, 23);
		inventoryPanel.add(btnNewButton_Add);
		
		JButton btnNewButton_Remove = new JButton("Remove");
		btnNewButton_Remove.setBounds(560, 307, 135, 23);
		inventoryPanel.add(btnNewButton_Remove);
		
		////////////////////////////////////////
		/// 					ABA Do ADMIN
		////////////////////////////////////////
		
		JPanel admPanel = new JPanel();
		tabbedPane.addTab("Manager area", null, admPanel, null);
		admPanel.setLayout(null);
		
		table_2 = new JTable();
		table_2.setBounds(21, 78, 720, 167);
		admPanel.add(table_2);
		
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
		textField_NumberOfTransitions.setColumns(10);
		textField_NumberOfTransitions.setBounds(517, 273, 224, 20);
		admPanel.add(textField_NumberOfTransitions);
	}

	// método para atualizar a tabela de pedidos
	// private void updateTableOrder(){
	// 	// pegamos o modelo de dados relacionado ao JTable
	// 	DefaultTableModel modelTable = (DefaultTableModel) table.getModel();

	// 	// limpamos todas as linhas antigas
	// 	modelTable.setRowCount(0);

	// 	// chamamos o controller para pegar a lista atual
	// 	for (model.Product p : controller.getCurrentOrderProducts()){
	// 		// adiciona linha com as 3 colunas configuradas
	// 		modelTable.addRow(new Object[]{
	// 			p.getName(),
	// 			String.format("%.2f", p.getPrice()),
	// 			p.getStockQuantity()
	// 		});
	// 	}
	// }
}