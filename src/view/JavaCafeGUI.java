package view;

import controller.POSController;
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
import model.Order;
import model.Product;

public class JavaCafeGUI extends JFrame {

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
					JavaCafeGUI frame = new JavaCafeGUI(posController);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JavaCafeGUI(POSController posController) {
		this.controller = posController;

		// window configuration
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// to add the tabs - Sale, Inventory, Manager Area 
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1097, 665);
		contentPane.add(tabbedPane);
		
		////////////////////////////////////////
		/// 	SALES TAB				
		////////////////////////////////////////
		JPanel salePanel = new JPanel();
		salePanel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Sale", null, salePanel, null);
		salePanel.setLayout(null);
		
		//////////////////////////////////////
		// to display the clicked product
		// item label and text field configuration
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
		// menu items with product photos
		btnEspresso = new JButton("");
		btnEspresso.setIcon(new ImageIcon(JavaCafeGUI.class.getResource("imagens/2.png")));
		btnEspresso.setBounds(10, 11, 175, 175);
		salePanel.add(btnEspresso);

		btnCappuccino = new JButton("");
		btnCappuccino.setIcon(new ImageIcon(JavaCafeGUI.class.getResource("imagens/3.png")));
		btnCappuccino.setBounds(197, 11, 175, 175);
		salePanel.add(btnCappuccino);

		btnMatchaLatte = new JButton("");
		btnMatchaLatte.setIcon(new ImageIcon(JavaCafeGUI.class.getResource("imagens/4.png")));
		btnMatchaLatte.setBounds(384, 11, 175, 175);
		salePanel.add(btnMatchaLatte);

		btnCheesecake = new JButton("");
		btnCheesecake.setIcon(new ImageIcon(JavaCafeGUI.class.getResource("imagens/5.png")));
		btnCheesecake.setBounds(10, 198, 175, 175);
		salePanel.add(btnCheesecake);
		
		btnSandwich = new JButton("");
		btnSandwich.setIcon(new ImageIcon(JavaCafeGUI.class.getResource("imagens/6.png")));
		btnSandwich.setBounds(197, 198, 175, 175);
		salePanel.add(btnSandwich);
		
		btnCroissant = new JButton("");
		btnCroissant.setIcon(new ImageIcon(JavaCafeGUI.class.getResource("imagens/7.png")));
		btnCroissant.setBounds(384, 198, 175, 175);
		salePanel.add(btnCroissant);

		// events triggered by clicking menu items
		ActionListener menuListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				String nameProduct = ""; // to store the product name

				// checking which menu item was clicked and saving the name
				if (e.getSource() == btnEspresso) nameProduct = "Espresso";
				else if (e.getSource() == btnCappuccino) nameProduct = "Cappuccino";
				else if (e.getSource() == btnMatchaLatte) nameProduct = "Matcha Latte";
				else if (e.getSource() == btnCheesecake) nameProduct = "Cheesecake";
				else if (e.getSource() == btnSandwich) nameProduct = "Sandwich";
				else if (e.getSource() == btnCroissant) nameProduct = "Croissant";

				// placing the product name in the Item TextField
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
		// quantity label and text field configuration
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
		/// organizing the financial side
		// tax label and text field configuration - disabled
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
		
		// subtotal label and text field configuration - disabled
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
		
		// total label and text field configuration - disabled
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
		// configuration for the order items table - as a receipt.
		// to scroll the table
		JScrollPane scrollPaneOrder = new JScrollPane();
		scrollPaneOrder.setBounds(616, 11, 438, 175);
		salePanel.add(scrollPaneOrder);

		tableOrder = new JTable();
		tableOrder.setBackground(new Color(255, 255, 255));
		// defining the table model - columns Item, Price, Quantity
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
		// textArea configuration for messages
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		// to scroll the message textArea
		JScrollPane scrollPaneTextArea = new JScrollPane(textArea);
		scrollPaneTextArea.setBounds(616, 292, 438, 80);
		salePanel.add(scrollPaneTextArea);

		//////////////////////////////////////
		// configuring the "add product to order" button
		JButton btnAdd = new JButton("Add Product");
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 18));
		btnAdd.setBounds(802, 441, 252, 23);
		salePanel.add(btnAdd);

		// adding a selected product to the table
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				String nameProduct = textFieldItem.getText(); // get the product name

				// if no product is selected yet, show a warning
				if (nameProduct.isEmpty()){
					textArea.append("\nWARNING: Please, select a product from the menu first.");
					return;
				}

				try {
					// try to parse the text into an integer
					int quantity = Integer.parseInt(textFieldQuantity.getText());

					// checks if the quantity is less than or equal to zero
					if (quantity <= 0){
						textArea.append("\nWARNING: Quantity must be greater than zero in the quantity field.");
						return;
					} 

					// checking if it's in stock
					if (!controller.hasEnoughStock(nameProduct, quantity)) {
        		textArea.append(String.format("\nWARNING: Insufficient stock for %s.", nameProduct));
						return;
					}

					// to add the desired quantity to the controller
					controller.addItemToOrder(nameProduct, quantity);

					// update the financial information on the screen
					textFieldSubtotal.setText(String.format("%.2f", controller.getSubtotal()));
					textFieldTax.setText(String.format("%.2f", controller.getTax()));
					textFieldTotal.setText(String.format("%.2f", controller.getTotal()));

					// updates the order table rows
					updateTableOrder();

					// send success message
					textArea.append(String.format("\nSUCCESS: Added %dx %s to your order.", quantity, nameProduct));

					// clear the selectors for the next product
					textFieldItem.setText("");
					textFieldQuantity.setText("");
					
				} catch (NumberFormatException ex) {
					// if something that is not a number is entered
					textArea.append("\nERROR: Please enter a valid whole number for Quantity.");
				}
			}
		});

		//////////////////////////////////////
		// configuring the "Remove product from order" button
		JButton btnRemove = new JButton("Remove");
		btnRemove.setFont(new Font("Dialog", Font.BOLD, 18));
		btnRemove.setBounds(802, 476, 252, 23);
		salePanel.add(btnRemove);

		// removing an item via an event triggered by the button
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				// saves the selected line
				int selectedRow = tableOrder.getSelectedRow();

				// checks if any row is selected
				if (selectedRow == -1){
					textArea.append("\nWARNING: Please, select a line from the table to remove.");
					return;
				}

				DefaultTableModel modelTable = (DefaultTableModel) tableOrder.getModel(); // get the order table model
        String productName = modelTable.getValueAt(selectedRow, 0).toString(); // get the name of the selected product

        // triggers the controller to remove
        controller.removeSingleItemFromOrder(productName);

        // update the financial part
        textFieldSubtotal.setText(String.format("%.2f", controller.getSubtotal()));
        textFieldTax.setText(String.format("%.2f", controller.getTax()));
        textFieldTotal.setText(String.format("%.2f", controller.getTotal()));

        // update the table
        updateTableOrder();

        textArea.append(String.format("\nSUCCESS: Removed 1x %s from your order.", productName));
			}
		});

		//////////////////////////////////////
		// configuration of the order confirmation button and event
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Dialog", Font.BOLD, 18));
		btnConfirm.setBounds(802, 511, 252, 58);
		salePanel.add(btnConfirm);

		// placing the order through the event triggered by the button
		btnConfirm.addActionListener((new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				// checks if the current order is not empty
				if (controller.getCurrentOrderProducts().isEmpty()){
					textArea.append("\nWARNING: Cannot confirm an empty order.");
					return;
				}

				try {
					// call the controller to process the stock reduction and save the data
					controller.checkoutCurrentOrder();

					// clear the financial fields
					textFieldSubtotal.setText("0.00");
					textFieldTax.setText("0.00");
					textFieldTotal.setText("0.00");

					// clear the table
					updateTableOrder();

					// update the stock table
					updateTableInventory();

					// displays a successful sale message
					textArea.append("\nSUCCESS: Transaction completed! Stock updated successfully.");


				} catch (Exception ex) {
					// if any unexpected error occurs
					textArea.append("\nERROR: Failed to finalize purchase.");
				}
			}
		}));

		//////////////////////////////////////
		// sales window background configuration
		JLabel backgroundSaleTab = new JLabel("");
		backgroundSaleTab.setIcon(new ImageIcon(JavaCafeGUI.class.getResource("imagens/FundoJavaCafe_Venda.png")));
		backgroundSaleTab.setBounds(-68, -34, 1200, 713);
		salePanel.add(backgroundSaleTab);

		////////////////////////////////////////
		/// 	STOCK TAB				
		////////////////////////////////////////
		// configuring the inventory tab
		JPanel inventoryPanel = new JPanel();
		tabbedPane.addTab("Inventory", null, inventoryPanel, null);
		inventoryPanel.setLayout(null);
		
		// stock table configuration
		tableInventory = new JTable();
		tableInventory.setFont(new Font("Dialog", Font.PLAIN, 18));
		tableInventory.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 18));
		tableInventory.setRowHeight(35);
		// will have 3 columns - Item, Price e Quantity
		tableInventory.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Item", "Price", "Quantity"
			}
		));

		// adding a scroll bar to the table
		JScrollPane scrollPaneInventory = new JScrollPane(tableInventory);
		scrollPaneInventory.setBounds(61, 48, 952, 251);
		inventoryPanel.add(scrollPaneInventory);
		
		//////////////////////////////////////
		// quantity label and text field configuration
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
		// quantity Add button configuration
		JButton btnAddQuantity = new JButton("Add");
		btnAddQuantity.setFont(new Font("Dialog", Font.BOLD, 18));
		btnAddQuantity.setBounds(499, 330, 252, 23);
		inventoryPanel.add(btnAddQuantity);
		
		//////////////////////////////////////
		// configuring the Remove button for Quantity
		JButton btnRemoveQuantity = new JButton("Remove");
		btnRemoveQuantity.setFont(new Font("Dialog", Font.BOLD, 18));
		btnRemoveQuantity.setBounds(761, 330, 252, 23);
		inventoryPanel.add(btnRemoveQuantity);

		//////////////////////////////////////
		// configuring the label to display messages to the user
		JLabel lblMsgInv = new JLabel("");
		lblMsgInv.setBounds(100, 310, 500, 200);
		inventoryPanel.add(lblMsgInv);

		// associating user actions with the button using events
		ActionListener inventoryListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				// check if any row in the table has been selected
				int selectedRow = tableInventory.getSelectedRow();
				if (selectedRow == -1){
					// display formatted HTML message
					lblMsgInv.setText("<html><font color='orange'><b>WARNING:</b></font> Please, select an item from the inventory table first.</html>");
					return;
				}

				// try to get the desired quantity in the textField
				int quantityInput = 0;
				try {
					quantityInput = Integer.parseInt(textFieldQuantityInv.getText());
					if (quantityInput <= 0){
						// display formatted HTML message
						lblMsgInv.setText("<html><font color='orange'><b>WARNING:</b></font> Quantity must be greater than zero.</html>");
						return;
					}
				} catch (NumberFormatException ex) {
					// display formatted HTML message
					lblMsgInv.setText("<html><font color='red'><b>ERROR:</b></font> Please enter a whole number in the quantity field.</html>");
					return;
				}

				DefaultTableModel modelTable = (DefaultTableModel) tableInventory.getModel(); // get the inventory table model
				String productName = modelTable.getValueAt(selectedRow, 0).toString(); // retrieve the product name from the selected line.

				// search for the corresponding product in the inventory list
				model.Product productSelected = null;
				for (model.Product p : controller.getInventoryList()){
					if (p.getName().equalsIgnoreCase(productName)){
						productSelected = p;
						break;
					}
				}

				// check if you didn't find it
				if (productSelected == null) return;

				// checking which button on the inventory tab was clicked
				if (e.getSource() == btnAddQuantity){
					int newQuantity = productSelected.getStockQuantity() + quantityInput;
					productSelected.setStockQuantity(newQuantity);

					// the changes are saved in the CSV file
					persistence.DataManager.saveCompleteInventory(controller.getInventory());

					// update visual components and clear the input field
					updateTableInventory();

					// refresh the screen
					textFieldQuantityInv.setText("");
					lblMsgInv.setText(String.format("<html><font color='green'><b>SUCCESS:</b></font> Added %d units to %s stock.</html>", quantityInput, productName));

				}  else if (e.getSource() == btnRemoveQuantity){
					// check if the quantity we have is sufficient to remove
					if (productSelected.getStockQuantity() < quantityInput){
						lblMsgInv.setText("<html><font color='orange'><b>WARNING:</b></font> Cannot remove more items than available in stock.</html>");
						return;
					}

					// reduce the desired amount
					productSelected.deductStock(quantityInput);
					lblMsgInv.setText(String.format("<html><font color='green'><b>SUCCESS:</b></font> Removed %d units from %s stock.</html>", quantityInput, productName));

					// the changes are saved in the CSV file
					persistence.DataManager.saveCompleteInventory(controller.getInventory());

					// Update visual components and clear the input field
					updateTableInventory();
					textFieldQuantityInv.setText("");
				}
			}
		};

		btnAddQuantity.addActionListener(inventoryListener);
    btnRemoveQuantity.addActionListener(inventoryListener);
		
		//////////////////////////////////////
		// inventory window background configuration
		JLabel backgroundInventoryTab = new JLabel("");
		backgroundInventoryTab.setIcon(new ImageIcon(JavaCafeGUI.class.getResource("imagens/FundoJavaCafe_Estoque.png")));
		backgroundInventoryTab.setBounds(-68, -34, 1200, 713);
		inventoryPanel.add(backgroundInventoryTab);
		
		////////////////////////////////////////
		/// 	ADMIN TAB				
		////////////////////////////////////////
		// configuring the Manager Area tab
		JPanel admPanel = new JPanel();
		tabbedPane.addTab("Manager Area", null, admPanel, null);
		admPanel.setLayout(null);
	
		//////////////////////////////////////
		// sales table configuration
		tableSales = new JTable();
		tableSales.setBounds(21, 78, 720, 167);
		tableSales.setFont(new Font("Dialog", Font.PLAIN, 18));
		tableSales.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 18));
		tableSales.setRowHeight(35);
		// will have 3 columns - Date, Product Name and Price 
		tableSales.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Date", "Product", "Price"
			}
		));
		admPanel.add(tableSales);

		// to scroll the sales table
		JScrollPane scrollPaneTableSales = new JScrollPane(tableSales);
		scrollPaneTableSales.setBounds(21, 78, 1044, 360);
		admPanel.add(scrollPaneTableSales);

		//////////////////////////////////////
		// configuring the label to require messages from the user
		lblMsgSales = new JLabel("");
		lblMsgSales.setBounds(10, 547, 500, 30);
		admPanel.add(lblMsgSales);
		
		//////////////////////////////////////
		// setting up the Date label and text field - for searching sales
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
		// configuring the Search button to search by date
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Dialog", Font.BOLD, 18));
		btnSearch.setBounds(813, 33, 252, 23);
		admPanel.add(btnSearch);

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == btnSearch){
					// get the entered date
					String inputDate = textFieldDate.getText().trim(); 

					// checks if the field is empty
					if (inputDate.isEmpty()){
						lblMsgSales.setText("<html><font color='orange'><b>WARNING:</b></font> Please enter a date (YYYY-MM-DD).</html>");
						return;
					}

					try {
						// try converting the text to a valid date
						java.time.LocalDate searchDate = java.time.LocalDate.parse(inputDate);
						lblMsgSales.setText(""); // clear the previous message

						// calls the function that searches and updates the table
						updateDashboard(searchDate);
					} catch (java.time.format.DateTimeParseException ex) {
						lblMsgSales.setText("<html><font color='red'><b>ERROR:</b></font> Invalid format. Use YYYY-MM-DD.</html>");
					}
				}
		}});
		
		//////////////////////////////////////
		// Total Revenue label and text field configuration - disabled
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
		// NumberTransactions label and textField configuration - disabled
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
		// Manager Area window background configuration
		JLabel backgroundAdminTab = new JLabel("");
		backgroundAdminTab.setIcon(new ImageIcon(JavaCafeGUI.class.getResource("imagens/FundoJavaCafe_Adm.png")));
		backgroundAdminTab.setBounds(-68, -34, 1200, 713);
		admPanel.add(backgroundAdminTab);

		// loading the stock table for the first time
		updateTableInventory();
	}

	// method to update the order table
	private void updateTableOrder(){
		// retrieve the table model associated with the JTable
		DefaultTableModel modelTable = (DefaultTableModel) tableOrder.getModel();

		// clear all existing rows
		modelTable.setRowCount(0);

		// we call the controller to get the current list
		for (model.Product p : controller.getCurrentOrderProducts()){
			// add a row with the 3 configured columns
			modelTable.addRow(new Object[]{
				p.getName(),
				String.format("%.2f", p.getPrice()),
				1
			});
		}
  }

	// method to update the stock screen
	private void updateTableInventory(){
		DefaultTableModel modelTable = (DefaultTableModel) tableInventory.getModel(); // get the inventory table model
		modelTable.setRowCount(0); // clearing old table rows

		// iterates through all the actual inventory that the controller has loaded
		for (model.Product p : controller.getInventoryList()){
			modelTable.addRow(new Object[]{
				p.getName(),
				String.format("%.2f", p.getPrice()),
				p.getStockQuantity()
			});
		}
	}

	// this method will update the admin tab depending on the date searched by the manager
	private void updateDashboard(java.time.LocalDate searchDate){
		DefaultTableModel modelSales = (DefaultTableModel) tableSales.getModel(); // get the sales table model
		modelSales.setRowCount(0); // clearing old table rows

		// retrieves the list of orders that occurred on the searched date
		ArrayList<Order> filteredOrders = controller.getSaleSummary().getSalesByDate(searchDate);

		// it goes through the orders and inserts each product into the rows of the table
		for (Order order : filteredOrders){
			for (Product p : order.getListProducts()){
				modelSales.addRow(new Object[]{
					order.getDate().toString(),
					p.getName(),
					String.format("%.2f", p.getPrice())
				});
			}
		}

		// updates financial fields
		double totalRevenue = controller.getSaleSummary().getTotalRevenueDaily(searchDate);
		int totalTransactions = controller.getSaleSummary().getTransactionCountDaily(searchDate);

		textFieldTotalRevenue.setText(String.format("$ %.2f", totalRevenue));
		textFieldNumberTransactions.setText(String.valueOf(totalTransactions));

		if (totalTransactions == 0){
			lblMsgSales.setText("No sales records found for this date.");
		} else {
			lblMsgSales.setText(""); // clear the notification if everything is OK
		}
	}
}
		
		

