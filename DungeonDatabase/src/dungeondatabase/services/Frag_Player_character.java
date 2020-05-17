package dungeondatabase.services;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	private String user = "altobes";
	private DefaultTableModel tb;

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
	public Frag_Player_character(String user) {
		this.user = user;
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dbService.connect("Dungeon19", "Password123"); // replace "username" and "password" with your own rose login
		//ArrayList<String> pc_temp = pc.getPlayerCharacter(user);
		//ArrayList<String> bs_temp = pc.getBackStory(user);
		ArrayList<ArrayList<String>> players = pc.getPlayerAll(user);
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 600, 300);
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
		
		JButton btnNewButton = new JButton("Create New Character");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(200, 39, 200, 25);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { //Open create character menu
//				Frag_Create_Character window = new Frag_Create_Character();
//				window.frame.setVisible(true);
				
				Frag_Create_Statblock window = new Frag_Create_Statblock(user);
				window.frame.setVisible(true);
				
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		frame.addWindowListener(new WindowListener() {
			private boolean deactivated;
			@Override
			public void windowActivated(java.awt.event.WindowEvent e) {
				if (deactivated) {
					tb.setRowCount(1);
					fillTable(tb);
				}
				deactivated = false;
			}
			@Override
			public void windowClosed(java.awt.event.WindowEvent e) {
			}
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
			}
			@Override
			public void windowDeactivated(java.awt.event.WindowEvent e) {
				deactivated = true;
			}
			@Override
			public void windowDeiconified(java.awt.event.WindowEvent e) {
			}
			@Override
			public void windowIconified(java.awt.event.WindowEvent e) {
			}
			@Override
			public void windowOpened(java.awt.event.WindowEvent e) {
			}
		});
		
		table = new JTable();
		tb = new DefaultTableModel(
				new Object[][] {
					{"Name", "ID", "Back Story"},
				},
				new String[] {
					"New column", "New column" , "New Column"
				}
			);
		for (int i = 0;i < players.get(0).size();i++) {
			tb.addRow(new Object[] {
					players.get(0).get(i)
				});
			tb.setValueAt(players.get(1).get(i), i+1, 1);
			tb.setValueAt(players.get(2).get(i), i+1, 2);
		}
		table.setModel(tb);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(5);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.setBounds(10, 75, 570, 178);
		frame.getContentPane().add(table);
		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void fillTable(DefaultTableModel tb) {
		ArrayList<String> names = pc.getPlayerCharacter(user);
		ArrayList<String> backstories = pc.getBackStory(user);
		
		for(int i = 0; i<names.size(); i++) {
			tb.addRow(new Object[] {
				names.get(i)
			});
		}
		for(int i = 0; i<backstories.size(); i++) {
			tb.setValueAt(backstories.get(i), i+1, 1);
		}
		
		
	}

}
