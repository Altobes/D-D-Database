package dungeondatabase.services;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Frag_Create_Campaign {
	JFrame frame;
	JFrame frame2;
	private DatabaseConnectionService dbService = new DatabaseConnectionService("golem.csse.rose-hulman.edu",
			"DungeonDatabase");
	private String user = "altobes";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frag_Create_Campaign window = new Frag_Create_Campaign();
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
	public Frag_Create_Campaign() {
		initialize();
	}
	
	public Frag_Create_Campaign(String user) {
		initialize();
		this.user = user;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dbService.connect("Dungeon19", "Password123"); // replace "username" and "password" with your own rose login
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 500, 300);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Dungeon Database");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(101, 0, 250, 42);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 80, 184, 25);
		frame.getContentPane().add(lblNewLabel_2);

		JTextField nameField = new JTextField();
		nameField.setBounds(200, 80, 200, 25);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Party ID");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(10, 160, 184, 25);
		frame.getContentPane().add(lblNewLabel_4);

		JTextField partyID = new JTextField();
		partyID.setBounds(200, 160, 200, 25);
		frame.getContentPane().add(partyID);
		partyID.setColumns(10);

		JButton buttonCreate = new JButton("Create");
		buttonCreate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		buttonCreate.setBounds(200, 45, 200, 20);
		buttonCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // Open create character menu
				CallableStatement cs = null;
				try {
					cs = dbService.getConnection().prepareCall("{? = call Create_Campaign(?, ?, ?)}");

					
					String Name = new String(nameField.getText());
					cs.setString(2, Name);
					cs.setString(4, user); //DM User

					
					int party = Integer.parseInt(partyID.getText());
					
					cs.setInt(3, party);

					cs.registerOutParameter(1, Types.INTEGER);
					cs.execute();

					int result = cs.getInt(1);
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "ERROR: Must provide valid party id");
					} else if (result == 2) {
						JOptionPane.showMessageDialog(null, "ERROR: Party does not exist");
					} else if (result == 3) {
						JOptionPane.showMessageDialog(null, "ERROR: User not listed");
					} else if (result == 4) {
						JOptionPane.showMessageDialog(null, "ERROR: user not DM");
					} else if (result == 0) {
						JOptionPane.showMessageDialog(null, "Successfully Created Campaign");
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(buttonCreate);
		
		JButton create_party = new JButton("Create New Party");
		create_party.setFont(new Font("Tahoma", Font.PLAIN, 16));
		create_party.setBounds(200, 200, 200, 20);
		create_party.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // Open create character menu
				frame.dispose();
				//frame.setVisible(false);
				initializeParty();
			}
		});
		frame.getContentPane().add(create_party);

//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initializeParty() {
		dbService.connect("Dungeon19", "Password123"); // replace "username" and "password" with your own rose login
		frame2 = new JFrame();
		frame2.setAlwaysOnTop(true);
		frame2.setBounds(100, 100, 500, 300);
		frame2.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Dungeon Database");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(101, 0, 250, 42);
		frame2.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Campaign Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 80, 184, 25);
		frame2.getContentPane().add(lblNewLabel_2);

		JTextField nameField = new JTextField();
		nameField.setBounds(200, 80, 200, 25);
		frame2.getContentPane().add(nameField);
		nameField.setColumns(10);

		
		
		
		JLabel lblNewLabel1 = new JLabel("Dungeon Database");
		lblNewLabel1.setForeground(Color.ORANGE);
		lblNewLabel1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel1.setBounds(101, 0, 250, 42);
		frame2.getContentPane().add(lblNewLabel1);
		
		JLabel lblNewLabel_21 = new JLabel("Party Name");
		lblNewLabel_21.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_21.setBounds(10, 120, 184, 25);
		frame2.getContentPane().add(lblNewLabel_21);
		
		JTextField partyField = new JTextField();
		partyField.setBounds(200, 120, 200, 25);
		frame2.getContentPane().add(partyField);
		partyField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Level");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(10, 200, 184, 25);
		frame2.getContentPane().add(lblNewLabel_3);
		
		JTextField levelField = new JTextField();
		levelField.setBounds(200, 200, 200, 25);
		frame2.getContentPane().add(levelField);
		levelField.setColumns(10);
		
		JLabel lblNewLabel_41 = new JLabel("Location");
		lblNewLabel_41.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_41.setBounds(10, 160, 184, 25);
		frame2.getContentPane().add(lblNewLabel_41);
		
		JTextField locationField = new JTextField();
		locationField.setBounds(200, 160, 200, 25);
		frame2.getContentPane().add(locationField);
		locationField.setColumns(10);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(200, 45, 200, 20);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // Open create character menu
				CallableStatement cs = null;
				int party = 1;
				try {
					cs = dbService.getConnection().prepareCall("{? = call Create_Party(?, ?, ?, ?)}");
					
					String Name = new String(partyField.getText());
					cs.setString(2, Name);
					
					int level = Integer.parseInt(levelField.getText());
					cs.setInt(3, level);
					
					String location = locationField.getText();
					cs.setString(4, location);
					
					cs.registerOutParameter(5, Types.INTEGER);
					cs.registerOutParameter(1, Types.INTEGER);
					cs.execute();
					party = cs.getInt(5);
					int result = cs.getInt(1);
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "ERROR: Need Valid Party Name");
						return;
					}
					else if (result == 0) {
						JOptionPane.showMessageDialog(null, "Successfully Created Party");
					}
			
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				cs = null;
				try {
					cs = dbService.getConnection().prepareCall("{? = call Create_Campaign(?, ?, ?)}");

					
					String Name = new String(nameField.getText());
					cs.setString(2, Name);
					cs.setString(4, user); //DM User
					cs.setInt(3, party);

					cs.registerOutParameter(1, Types.INTEGER);
					cs.execute();

					int result = cs.getInt(1);
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "ERROR: Must provide valid party id");
					} else if (result == 2) {
						JOptionPane.showMessageDialog(null, "ERROR: Party does not exist");
					} else if (result == 3) {
						JOptionPane.showMessageDialog(null, "ERROR: User not listed");
					} else if (result == 4) {
						JOptionPane.showMessageDialog(null, "ERROR: user not DM");
					} else if (result == 0) {
						JOptionPane.showMessageDialog(null, "Successfully Created Campaign");
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame2.getContentPane().add(btnNewButton);
		
		frame2.setVisible(true);

//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
