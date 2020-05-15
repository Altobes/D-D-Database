package dungeondatabase.services;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.JTree;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.awt.Choice;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class Frag_Database {

	JFrame frame;
	private JLabel lblNewLabel_1;
	private Choice choice_1;
	private JTable table;
	private JLabel lblNewLabel_2;
	private JTextField textField;
	private JLabel lblNewLabel_3;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JButton btnNewButton_7;
	private JButton btnNewButton_8;
	private JButton btnNewButton_9;
	private JButton btnNewButton_10;
	private DatabaseConnectionService dbService = new DatabaseConnectionService("golem.csse.rose-hulman.edu", "DungeonDatabase");
	private Player_character pc = new Player_character(dbService);

	private String user = "altobes";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frag_Database window = new Frag_Database();
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
	public Frag_Database() {
		initialize();
	}
	public Frag_Database(String user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
		dbService.connect("Dungeon19", "Password123"); // replace "username" and "password" with your own rose login
		ArrayList<String> pc_temp = pc.getPlayerCharacter(user);
		ArrayList<String> pty_temp = pc.getParty(user);
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dungeon Database for " + this.user);
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(225, 4, 500, 31);
		frame.getContentPane().add(lblNewLabel);
		
//		Choice choice = new Choice();
//		choice.add("None");
//		for(int i = 0; i<pc_temp.size(); i++) {
//			choice.add(pc_temp.get(i));
//		}	
//		choice.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		choice.setBounds(119, 47, 96, 20);
//		frame.getContentPane().add(choice);
//		
//		lblNewLabel_1 = new JLabel("Player Character");
//		lblNewLabel_1.setBounds(10, 52, 118, 13);
//		frame.getContentPane().add(lblNewLabel_1);
//		
//		choice_1 = new Choice();
//		choice_1.add("None");
//		for(int i = 0; i<pty_temp.size(); i++) {
//			choice.add(pty_temp.get(i));
//		}	
//
//		choice_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		choice_1.setBounds(278, 47, 96, 20);
//		frame.getContentPane().add(choice_1);
//		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setForeground(Color.BLACK);
		table.setBackground(Color.LIGHT_GRAY);
		table.setFillsViewportHeight(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel tb = new DefaultTableModel(
			new Object[][] {
			}, new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
		}); 
		for(int i = 0; i<pc_temp.size(); i++) {
			tb.addRow(new Object[] {
				pc_temp.get(i)
			});
		}
		table.setModel(tb);
		table.getColumnModel().getColumn(0).setPreferredWidth(89);
		table.setColumnSelectionAllowed(true);
		table.setBounds(10, 101, 666, 334);
		frame.getContentPane().add(table);
		
//		lblNewLabel_2 = new JLabel("Party");
//		lblNewLabel_2.setBounds(236, 51, 36, 13);
//		frame.getContentPane().add(lblNewLabel_2);
//		
//		textField = new JTextField();
//		textField.setBounds(473, 48, 96, 19);
//		frame.getContentPane().add(textField);
//		textField.setColumns(10);
//		
//		lblNewLabel_3 = new JLabel("Campaign");
//		lblNewLabel_3.setBounds(403, 51, 77, 13);
//		frame.getContentPane().add(lblNewLabel_3);
//		btnNewButton = new JButton("Search");
//		btnNewButton.setBounds(579, 47, 97, 21);
//		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Character");
		btnNewButton_1.setBounds(10, 75, 96, 20);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frag_Player_character window = new Frag_Player_character(user);
				window.frame.setVisible(true);

			}	
		});
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Stat");
		btnNewButton_2.setBounds(104, 75, 85, 20);
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frag_Stat_Block window = new Frag_Stat_Block();
				window.frame.setVisible(true);

			}	
		});
		frame.getContentPane().add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Items");
		btnNewButton_3.setBounds(187, 75, 85, 20);
		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frag_Items window = new Frag_Items(user);
				window.frame.setVisible(true);

			}	
		});
		frame.getContentPane().add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Spells");
		btnNewButton_4.setBounds(268, 75, 85, 20);
		btnNewButton_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frag_Spells window = new Frag_Spells();
				window.frame.setVisible(true);

			}	
		});
		frame.getContentPane().add(btnNewButton_4);
		
		btnNewButton_5 = new JButton("Skills");
		btnNewButton_5.setBounds(347, 75, 85, 20);
		btnNewButton_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frag_Skills window = new Frag_Skills();
				window.frame.setVisible(true);

			}	
		});
		frame.getContentPane().add(btnNewButton_5);
		
		btnNewButton_6 = new JButton("NPC");
		btnNewButton_6.setBounds(431, 75, 81, 20);
		btnNewButton_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frag_NPC window = new Frag_NPC();
				window.frame.setVisible(true);

			}	
		});
		frame.getContentPane().add(btnNewButton_6);
		
		btnNewButton_7 = new JButton("Campaign");
		btnNewButton_7.setBounds(512, 75, 82, 20);
		btnNewButton_7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frag_Campaign window = new Frag_Campaign();
				window.frame.setVisible(true);

			}	
		});
		frame.getContentPane().add(btnNewButton_7);
		
		btnNewButton_8 = new JButton("Party");
		btnNewButton_8.setBounds(594, 75, 85, 20);
		btnNewButton_8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frag_Party window = new Frag_Party();
				window.frame.setVisible(true);

			}	
		});
		frame.getContentPane().add(btnNewButton_8);
		
		btnNewButton_9 = new JButton("DM View");
		btnNewButton_9.setBounds(5, 10, 85, 20);
		btnNewButton_9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!checkDM()) {
						return;
					}
					
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Frag_DM_View window = new Frag_DM_View(user);
				window.frame.setVisible(true);
			}
		});
		frame.getContentPane().add(btnNewButton_9);
		
		btnNewButton_10 = new JButton("Register DM");
		btnNewButton_10.setBounds(95, 10, 110, 20);
		btnNewButton_10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					RegisterDM();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnNewButton_10);
		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private boolean checkDM() throws HeadlessException, SQLException {
		CallableStatement cs = null;
		this.dbService.connect("Dungeon19", "Password123");
		//String s=String.format("Select Username from %s.dbo.DM Where Username = %s", this.dbService.databaseName, "altobes");
		Connection c = this.dbService.getConnection();
		cs = c.prepareCall("Select Username from DM Where Username = ?");
		//cs.setString(1, this.dbService.databaseName);
		cs.setString(1, user);
		ResultSet r = cs.executeQuery();
		if (!r.next()) {
			JOptionPane.showMessageDialog(null, "User is not DM");
			return false;
		}
		return true;
	}	
	
	private boolean RegisterDM() throws SQLException {
		CallableStatement cs = null;
		this.dbService.connect("Dungeon19", "Password123");
		Connection c = this.dbService.getConnection();
		cs = c.prepareCall("{? = call CreateDM(?)}");
		cs.registerOutParameter(1, Types.INTEGER);
		cs.setString(2, user);
		cs.execute();
		int result = cs.getInt(1);
		
		if (result == 2) {
			JOptionPane.showMessageDialog(null, "User is not listed");
			return false;
		}
		else if (result == 3) {
			JOptionPane.showMessageDialog(null, "User is already DM");
			return false;
		}
		else if (result == 1) {
			JOptionPane.showMessageDialog(null, "User is now DM");
			return true;
		}
		return true;
	}
}

