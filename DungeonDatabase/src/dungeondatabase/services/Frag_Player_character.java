package dungeondatabase.services;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Frag_Player_character {

	JFrame frame;
	private JTable table;
	private DatabaseConnectionService dbService = 
			new DatabaseConnectionService("golem.csse.rose-hulman.edu", "DungeonDatabase");
	private Player_character pc = new Player_character(dbService);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frag_Player_character window = new Frag_Player_character();
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
	public Frag_Player_character() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dbService.connect("username", "password"); // replace "username" and "password" with your own rose login
		ArrayList<String> pc_temp = pc.getPlayerCharacter();
		ArrayList<String> bs_temp = pc.getBackStory();
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dungeon Database");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(101, 0, 250, 42);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Player Character");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 40, 184, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		table = new JTable();
		DefaultTableModel tb = new DefaultTableModel(
				new Object[][] {
					{"Name", "Back Story"},
				},
				new String[] {
					"New column", "New column"
				}
			);
		for(int i = 0; i<pc_temp.size(); i++) {
			tb.addRow(new Object[] {
				pc_temp.get(i)
			});
		}
		for(int i = 0; i<bs_temp.size(); i++) {
			tb.setValueAt(bs_temp.get(i), i+1, 1);
		}
		table.setModel(tb);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.setBounds(10, 75, 416, 178);
		frame.getContentPane().add(table);
	}

}
