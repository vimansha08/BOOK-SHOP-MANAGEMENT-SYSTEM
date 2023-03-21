package guiprogramming.bookshop;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class BookShopForm {

	private JFrame frame;
	private JTextField txtBookName;
	private JTextField txtAuthor;
	private JTextField txtEdition;
	private JTextField txtPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookShopForm window = new BookShopForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	private BookDao daoBook;
	private JTable table;
	
	public BookShopForm() {
		
		daoBook = new BookDao();
		initialize();
		loadTable();
	}
	
	public void clearForm() {
		
		txtAuthor.setText("");
		txtBookName.setText("");
		txtEdition.setText("");
		txtPrice.setText("");
	}
	
	
	public void loadTable() {
		
		ResultSet rs = daoBook.loadTableData();
		
		table.setModel(DbUtils.resultSetToTableModel(rs));
		
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 976, 568);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRegistration = new JLabel("Book Shop Management");
		lblRegistration.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblRegistration.setBounds(82, 42, 508, 49);
		frame.getContentPane().add(lblRegistration);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Book Registration Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 108, 423, 335);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAuthor = new JLabel("Author Name :-");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAuthor.setBounds(10, 35, 150, 30);
		panel.add(lblAuthor);
		
		txtBookName = new JTextField();
		txtBookName.setBounds(155, 99, 171, 30);
		panel.add(txtBookName);
		txtBookName.setColumns(10);
		
		JLabel lblBook = new JLabel("Book Name :-");
		lblBook.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBook.setBounds(10, 94, 150, 30);
		panel.add(lblBook);
		
		txtAuthor = new JTextField();
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(155, 40, 171, 30);
		panel.add(txtAuthor);
		
		JLabel lblEdition = new JLabel("Edition :-");
		lblEdition.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEdition.setBounds(10, 158, 150, 30);
		panel.add(lblEdition);
		
		txtEdition = new JTextField();
		txtEdition.setColumns(10);
		txtEdition.setBounds(155, 158, 171, 30);
		panel.add(txtEdition);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(155, 215, 171, 30);
		panel.add(txtPrice);
		
		JLabel lblPrice = new JLabel("Price :-");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrice.setBounds(10, 215, 150, 30);
		panel.add(lblPrice);
		
		JButton btnRegister = new JButton("Register");
		
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String author = txtAuthor.getText();
				String book = txtBookName.getText();
				String edition = txtEdition.getText();
				String price = txtPrice.getText();
				
				Book bookObject = new Book(author,book, edition, price );
				daoBook.insertBook(bookObject);
				
				clearForm();
				loadTable();

			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegister.setBounds(155, 276, 85, 30);
		panel.add(btnRegister);
		
		JButton btnReset = new JButton("Reset");
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clearForm();
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnReset.setBounds(260, 276, 71, 30);
		panel.add(btnReset);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(468, 115, 471, 321);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
