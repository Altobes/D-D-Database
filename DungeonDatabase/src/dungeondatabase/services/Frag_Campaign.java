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
import javax.swing.table.DefaultTableModel;

public class Frag_Campaign {

	JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frag_Campaign window = new Frag_Campaign();
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
	public Frag_Campaign() {
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
		
		JLabel lblNewLabel = new JLabel("Dungeon Database");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(100, 0, 250, 42);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Campaign");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 40, 184, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton create_Campaign = new JButton("Create New Campaign");
		create_Campaign.setFont(new Font("Tahoma", Font.PLAIN, 16));
		create_Campaign.setBounds(225, 39, 175, 25);
		create_Campaign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { //Open create character menu
				Frag_Create_Campaign window = new Frag_Create_Campaign();
				window.frame.setVisible(true);
			}
		});
		frame.getContentPane().add(create_Campaign);
		
		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Name", "CampaignID", "PartyID"},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table.setBounds(10, 75, 416, 178);
		frame.getContentPane().add(table);
		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
