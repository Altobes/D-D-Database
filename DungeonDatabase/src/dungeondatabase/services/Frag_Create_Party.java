package dungeondatabase.services;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.awt.Color;
import javax.swing.JTextField;

public class Frag_Create_Party {
	JFrame frame;
	private DatabaseConnectionService dbService = 
			new DatabaseConnectionService("golem.csse.rose-hulman.edu", "DungeonDatabase");
	//private Player_character pc = new Player_character(dbService);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frag_Create_Party window = new Frag_Create_Party();
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
	public Frag_Create_Party() {
		initialize();
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
		
		JLabel lblNewLabel_1 = new JLabel("Party");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 40, 184, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 80, 184, 25);
		frame.getContentPane().add(lblNewLabel_2);
		
		JTextField nameField = new JTextField();
		nameField.setBounds(200, 80, 200, 25);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Level");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(10, 120, 184, 25);
		frame.getContentPane().add(lblNewLabel_3);
		
		JTextField levelField = new JTextField();
		levelField.setBounds(200, 120, 200, 25);
		frame.getContentPane().add(levelField);
		levelField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Location");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(10, 160, 184, 25);
		frame.getContentPane().add(lblNewLabel_4);
		
		JTextField locationField = new JTextField();
		locationField.setBounds(200, 160, 200, 25);
		frame.getContentPane().add(locationField);
		locationField.setColumns(10);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(200, 45, 200, 20);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { //Open create character menu
				CallableStatement cs = null;
				try {
					cs = dbService.getConnection().prepareCall("{? = call Create_Party(?, ?, ?)}");
					
					String Name = new String(nameField.getText());
					cs.setString(2, Name);
					
					int level = Integer.parseInt(levelField.getText());
					cs.setInt(3, level);
					
					String location = locationField.getText();
					cs.setString(4, location);

					cs.registerOutParameter(1, Types.INTEGER);
					cs.execute();
					
					int result = cs.getInt(1);
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "ERROR: Need Valid Party Name");
					}
					else if (result == 0) {
						JOptionPane.showMessageDialog(null, "Successfully Created Party");
					}
			
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnNewButton);
		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

}

