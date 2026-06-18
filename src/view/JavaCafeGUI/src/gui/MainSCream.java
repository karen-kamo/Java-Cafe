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
import java.awt.ScrollPane;
import javax.swing.JTextArea;

public class MainSCream extends JFrame {

	private static final long serialVersionUID = 1L;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 784, 461);
		contentPane.add(tabbedPane);
		
		JPanel salePanel = new JPanel();
		salePanel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Sale", null, salePanel, null);
		salePanel.setLayout(null);
		
		textField_Item = new JTextField();
		textField_Item.setBounds(67, 245, 153, 20);
		salePanel.add(textField_Item);
		textField_Item.setColumns(10);
		
		JLabel lbl_Item = new JLabel("Item:");
		lbl_Item.setBounds(10, 248, 210, 14);
		salePanel.add(lbl_Item);
		
		JButton botaoCheesecake = new JButton("");
		botaoCheesecake.setIcon(new ImageIcon(MainSCream.class.getResource("/imagens/5.png")));
		botaoCheesecake.setBounds(10, 122, 100, 100);
		salePanel.add(botaoCheesecake);
		
		JButton botaoSandwich = new JButton("");
		botaoSandwich.setIcon(new ImageIcon(MainSCream.class.getResource("/imagens/6.png")));
		botaoSandwich.setBounds(120, 122, 100, 100);
		salePanel.add(botaoSandwich);
		
		JButton botaoCroissant = new JButton("");
		botaoCroissant.setIcon(new ImageIcon(MainSCream.class.getResource("/imagens/7.png")));
		botaoCroissant.setBounds(231, 122, 100, 100);
		salePanel.add(botaoCroissant);
		
		JButton botaoMatchaLatte = new JButton("");
		botaoMatchaLatte.setIcon(new ImageIcon(MainSCream.class.getResource("/imagens/4.png")));
		botaoMatchaLatte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		botaoMatchaLatte.setBounds(231, 11, 100, 100);
		salePanel.add(botaoMatchaLatte);
		
		JButton botaoCappuccino = new JButton("");
		botaoCappuccino.setIcon(new ImageIcon(MainSCream.class.getResource("/imagens/3.png")));
		botaoCappuccino.setBounds(120, 11, 100, 100);
		salePanel.add(botaoCappuccino);
		
		JButton botaoEspresso = new JButton("");
		botaoEspresso.setIcon(new ImageIcon(MainSCream.class.getResource("/imagens/2.png")));
		botaoEspresso.setBounds(10, 11, 100, 100);
		salePanel.add(botaoEspresso);
		
		JLabel lbl_Quantity = new JLabel("Quantity:");
		lbl_Quantity.setBounds(10, 279, 58, 14);
		salePanel.add(lbl_Quantity);
		
		textField_Quantity = new JTextField();
		textField_Quantity.setColumns(10);
		textField_Quantity.setBounds(67, 276, 153, 20);
		salePanel.add(textField_Quantity);
		
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
		
		JLabel lbl_Quantity_1 = new JLabel("Tax:");
		lbl_Quantity_1.setBounds(10, 313, 58, 14);
		salePanel.add(lbl_Quantity_1);
		
		textField_Tax = new JTextField();
		textField_Tax.setColumns(10);
		textField_Tax.setBounds(67, 310, 153, 20);
		salePanel.add(textField_Tax);
		
		JLabel lbl_Quantity_1_1 = new JLabel("Subtotal:");
		lbl_Quantity_1_1.setBounds(10, 346, 58, 14);
		salePanel.add(lbl_Quantity_1_1);
		
		textField_Subtotal = new JTextField();
		textField_Subtotal.setColumns(10);
		textField_Subtotal.setBounds(67, 343, 153, 20);
		salePanel.add(textField_Subtotal);
		
		textField_total = new JTextField();
		textField_total.setColumns(10);
		textField_total.setBounds(67, 375, 153, 20);
		salePanel.add(textField_total);
		
		JLabel lbl_Quantity_1_1_1 = new JLabel("Total:");
		lbl_Quantity_1_1_1.setBounds(10, 378, 58, 14);
		salePanel.add(lbl_Quantity_1_1_1);
		
		JButton btnNewButton_Confirm = new JButton("Confirm");
		btnNewButton_Confirm.setBounds(592, 346, 163, 51);
		salePanel.add(btnNewButton_Confirm);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JScrollPane scrollPane2 = new JScrollPane(textArea);
		scrollPane2.setBounds(442, 200, 310, 59);

		salePanel.add(scrollPane2);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(592, 309, 163, 23);
		salePanel.add(btnRemove);
		
		JLabel fundo = new JLabel("");
		fundo.setIcon(new ImageIcon(MainSCream.class.getResource("/imagens/fundoJavaCafe.png")));
		fundo.setBounds(0, -29, 795, 506);
		salePanel.add(fundo);
		
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
}