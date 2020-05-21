package dungeondatabase.services;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.awt.Choice;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class Frag_DM_View {
	JFrame frame;
	private JLabel lblNewLabel_1;
	private JTable table;
/*
	private DatabaseConnectionService dbService = 
			new DatabaseConnectionService("golem.csse.rose-hulman.edu", "DungeonDatabase");
=======
*/
	private DatabaseConnectionService dbService = new DatabaseConnectionService(Dataclass.SNAME,Dataclass.DBNAME);
	private String user = "altobes";
	private DungeonMaster dm = null;
	private String camp;
	private DefaultTableModel tb;
	private int Party;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frag_DM_View window = new Frag_DM_View();
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
	public Frag_DM_View() {
		this.dm = new DungeonMaster(this.dbService, user);
		initialize();
		
	}
	
	public Frag_DM_View(String user) {
		this.user = user;
		this.dm = new DungeonMaster(this.dbService, user);
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
		dbService.connect(Dataclass.USER, Dataclass.PASS);
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dungeon Database");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(200, 10, 254, 31);
		frame.getContentPane().add(lblNewLabel);
		
		ArrayList<ArrayList<String>> camps = dm.getDMCampaigns();
		
		Choice choice_1 = new Choice();
		choice_1.add("None");
		for(int i = 0; i<camps.size(); i++) {
			choice_1.add(camps.get(i).get(0) + ": #" + camps.get(i).get(1));
		}	
		choice_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		choice_1.setBounds(119, 47, 200, 20);
		choice_1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent a) {
				String temp = choice_1.getSelectedItem();
				if (temp.equals("None")) {
					camp = "None";
					return;
				}
				
				int t = -1;
				for (int i = temp.length()-1;i > 0;i--) {
					if (temp.substring(i, i+1).equals("#")) {
						t = i+1;
						break;
					}
				}
				
				camp = temp.substring(t).trim();
				int temparty = dm.getParty(camp);
				
				fillTable(camp);
				if (temparty == -1) {
					JOptionPane.showMessageDialog(null, "No party ID found");
					return;
				}
				Party = temparty;
				
			}	
		});
		frame.getContentPane().add(choice_1);
		
		lblNewLabel_1 = new JLabel("Campaigns");
		lblNewLabel_1.setBounds(10, 52, 118, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
		
		JButton create_Campaign = new JButton("New Campaign");
		create_Campaign.setFont(new Font("Tahoma", Font.PLAIN, 16));
		create_Campaign.setBounds(350, 47, 175, 20);
		create_Campaign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // Open create character menu
				Frag_Create_Campaign window = new Frag_Create_Campaign(user);
				window.frame.setVisible(true);
			}

			
		});
		frame.getContentPane().add(create_Campaign);
		
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				updateCampaigns();
			}
			private void updateCampaigns() {
				choice_1.removeAll();
				ArrayList<ArrayList<String>> camps = dm.getDMCampaigns();
				choice_1.add("None");
				for(int i = 0; i<camps.size(); i++) {
					choice_1.add(camps.get(i).get(0) + ": #" + camps.get(i).get(1));
				}	
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
			}
			
		});
		
		
		
		JTextField characterField = new JTextField();
		characterField.setBounds(740, 60, 200, 25);
		frame.getContentPane().add(characterField);
		characterField.setColumns(10);
		
		JButton add_character = new JButton("Add Character");
		add_character.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add_character.setBounds(700, 20, 135, 20);
		add_character.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e1) { // Open create character menu
				String player = characterField.getText();
				if (player.equals("")) {
					JOptionPane.showMessageDialog(null, "ERROR: Need Player ID");
					return;
				}
				
				int playerID = Integer.parseInt(player);
				if (camp.equals("None")) {
					JOptionPane.showMessageDialog(null, "ERROR: Choose campaign");
					return;
				}
				
				CallableStatement cs = null;
				dbService.connect("Dungeon19", "Password123");
				Connection c = dbService.getConnection();
				try {
					cs = c.prepareCall("{? = call Add_Player(?, ?)}");
					cs.setInt(2, playerID);
					cs.setInt(3, Party);
					cs.registerOutParameter(1, Types.INTEGER);
					cs.execute();
					int result = cs.getInt(1);
					c.close();
					
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "Invalid Player ID");
						return;
					}
					else if (result == 2) {
						JOptionPane.showMessageDialog(null, "Invalid Party ID");
						return;
					}
					else if (result == 3) {
						JOptionPane.showMessageDialog(null, "Party Does not exist");
						return;
					}
					else if (result == 0) {
						JOptionPane.showMessageDialog(null, "Player Add Successful");
						fillTable(camp);
						return;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(add_character);
		
		
		JButton drop_character = new JButton("Drop Character");
		drop_character.setFont(new Font("Tahoma", Font.PLAIN, 16));
		drop_character.setBounds(835, 20, 135, 20);
		drop_character.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) { // Open create character menu
				String player = characterField.getText();
				if (player.equals("")) {
					JOptionPane.showMessageDialog(null, "ERROR: Need Player ID");
					return;
				}
				
				int playerID = Integer.parseInt(player);
				String campaignID = choice_1.getSelectedItem();
				if (campaignID.equals("None")) {
					JOptionPane.showMessageDialog(null, "ERROR: Choose campaign");
					return;
				}
				
				CallableStatement cs = null;
				dbService.connect("Dungeon19", "Password123");
				Connection c = dbService.getConnection();
				try {
					cs = c.prepareCall("{? = call Drop_Player(?, ?)}");
					cs.setInt(2, playerID);
					cs.setInt(3, Party);
					cs.registerOutParameter(1, Types.INTEGER);
					cs.execute();
					int result = cs.getInt(1);
					c.close();
					
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "Invalid Player ID");
						return;
					}
					else if (result == 2) {
						JOptionPane.showMessageDialog(null, "Invalid Party ID");
						return;
					}
					else if (result == 3) {
						JOptionPane.showMessageDialog(null, "Party Does not exist");
						return;
					}
					else if (result == 0) {
						JOptionPane.showMessageDialog(null, "Player Drop Successful");
						fillTable(camp);
						return;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(drop_character);
		
		
		
		
		JButton create_NPC = new JButton("Create NPC");
		create_NPC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		create_NPC.setBounds(565, 20, 135, 20);
		create_NPC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e1) { // Open create character menu

				Frag_Create_NPC npc = new Frag_Create_NPC();
				npc.frame.setVisible(true);
			}
		});
		frame.getContentPane().add(create_NPC);
		
		
		
		
		
		
		
		JButton deleteCamp = new JButton("Delete Campaign");
		deleteCamp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		deleteCamp.setBounds(10, 10, 160, 20);
		deleteCamp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) { // Open create character menu
				String campaignID = choice_1.getSelectedItem();
				if (campaignID.equals("None")) {
					JOptionPane.showMessageDialog(null, "ERROR: Choose campaign");
					return;
				}
				int campaign = Integer.parseInt(camp);
				
				CallableStatement cs = null;
				dbService.connect("Dungeon19", "Password123");
				Connection c = dbService.getConnection();
				try {
					cs = c.prepareCall("? = call Delete_Campaign(?)");
					cs.setInt(2, campaign);
					cs.registerOutParameter(1, Types.INTEGER);
					cs.execute();
					int result = cs.getInt(1);
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "ERROR: Invalid Campaign");
						return;
					}
					else {
						JOptionPane.showMessageDialog(null, "Campaign Deleted");
						choice_1.removeAll();
						ArrayList<ArrayList<String>> camps = dm.getDMCampaigns();
						choice_1.add("None");
						for(int i = 0; i<camps.size(); i++) {
							choice_1.add(camps.get(i).get(0) + ": #" + camps.get(i).get(1));
						}	
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(deleteCamp);
	
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setForeground(Color.BLACK);
		table.setBackground(Color.LIGHT_GRAY);
		table.setFillsViewportHeight(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/*DefaultTableModel*/tb = new DefaultTableModel(
				new Object[][] {
					{"Name", "PlayerID", "StatID", "Language", "AC", "Speed", "Race", "STR", "DEX", "CON", "INT", "WIS", "CHA"},
				},
				new String[] {
					"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
				}
			);
		
		table.setModel(tb);
		table.getColumnModel().getColumn(0).setPreferredWidth(89);
		table.setColumnSelectionAllowed(true);
		table.setBounds(10, 101, 960, 334);
		frame.getContentPane().add(table);
		
//			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void fillTable(String camp) {
		tb.setRowCount(1);
		if (camp == "None") {
			return;
		}
		ArrayList<ArrayList<String>> stats = dm.getStatBlock(camp);
		
		
		for(int i = 0; i < stats.get(0).size(); i++) {
			tb.addRow(new Object[] {
				stats.get(0).get(i)
			});
		}
		for (int i = 0; i < stats.size(); i++) {
			for (int j = 0;j < stats.get(i).size();j++)
				tb.setValueAt(stats.get(i).get(j), j+1, i);
		}
	}
}

