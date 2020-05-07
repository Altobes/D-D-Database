package dungeondatabase.services;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Frag_Party {

	JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frag_Party window = new Frag_Party();
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
	public Frag_Party() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel lblNewLabel = new JLabel("Dungeon Database");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(100, 0, 250, 42);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Party");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 40, 184, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton crtPrty = new JButton("Create New Party");
		crtPrty.setFont(new Font("Tahoma", Font.PLAIN, 16));
		crtPrty.setBounds(225, 39, 175, 25);
		crtPrty.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { //Open create character menu
				Frag_Create_Party window = new Frag_Create_Party();
				window.frame.setVisible(true);
			}
		});
		frame.getContentPane().add(crtPrty);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Name", "Current   Location", "Level", "PartyID"},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.setBounds(10, 75, 416, 178);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		frame.getContentPane().add(table);
		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
}
