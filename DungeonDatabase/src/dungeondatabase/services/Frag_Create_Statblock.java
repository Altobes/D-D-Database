package dungeondatabase.services;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.awt.Color;
import javax.swing.JTextField;

public class Frag_Create_Statblock {
		JFrame frame;
		private DatabaseConnectionService dbService = 
				new DatabaseConnectionService("golem.csse.rose-hulman.edu", "DungeonDatabase");
		//private StatBlock pc = new StatBlock(dbService);
			
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Frag_Create_Statblock window = new Frag_Create_Statblock();
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
		public Frag_Create_Statblock() {
			initialize();
		}

		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize() {
			dbService.connect("Dungeon19", "Password123"); // replace "username" and "password" with your own rose login
			frame = new JFrame();
			frame.setAlwaysOnTop(true);
			frame.setBounds(100, 100, 500, 600);
			frame.getContentPane().setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Dungeon Database");
			lblNewLabel.setForeground(Color.ORANGE);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
			lblNewLabel.setBounds(101, 0, 250, 42);
			frame.getContentPane().add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Statblock");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_1.setBounds(10, 40, 184, 25);
			frame.getContentPane().add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Name");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_2.setBounds(10, 80, 184, 25);
			frame.getContentPane().add(lblNewLabel_2);
			
			JTextField NameField = new JTextField();
			NameField.setBounds(200, 80, 200, 25);
			frame.getContentPane().add(NameField);
			NameField.setColumns(10);

			JLabel lblNewLabel_3 = new JLabel("Race");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_3.setBounds(10, 120, 184, 25);
			frame.getContentPane().add(lblNewLabel_3);
			
			JTextField raceField = new JTextField();
		    raceField.setBounds(200, 120, 200, 25);
			frame.getContentPane().add(raceField);
			raceField.setColumns(10);
			
			JLabel lblNewLabel_4 = new JLabel("Speed");
			lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_4.setBounds(10, 160, 184, 25);
			frame.getContentPane().add(lblNewLabel_4);
			
			JTextField speedField = new JTextField();
			speedField.setBounds(200, 160, 200, 25);
			frame.getContentPane().add(speedField);
			speedField.setColumns(10);
			
			JLabel lblNewLabel_5 = new JLabel("AC");
			lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_5.setBounds(10, 200, 184, 25);
			frame.getContentPane().add(lblNewLabel_5);
			
			JTextField acField = new JTextField();
			acField.setBounds(200, 200, 200, 25);
			frame.getContentPane().add(acField);
			acField.setColumns(10);
			
			JLabel lblNewLabel_6 = new JLabel("Strength");
			lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_6.setBounds(10, 240, 184, 25);
			frame.getContentPane().add(lblNewLabel_6);
			
			JTextField strengthField = new JTextField();
			strengthField.setBounds(200, 240, 200, 25);
			frame.getContentPane().add(strengthField);
			strengthField.setColumns(10);
			
			JLabel lblNewLabel_7 = new JLabel("Dexterity");
			lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_7.setBounds(10, 280, 184, 25);
			frame.getContentPane().add(lblNewLabel_7);
			
			JTextField dexField = new JTextField();
			dexField.setBounds(200, 280, 200, 25);
			frame.getContentPane().add(dexField);
			dexField.setColumns(10);
			
			JLabel conField = new JLabel("Constitution");
			conField.setFont(new Font("Tahoma", Font.PLAIN, 20));
			conField.setBounds(10, 320, 184, 25);
			frame.getContentPane().add(conField);
			
			JTextField textField_8 = new JTextField();
			textField_8.setBounds(200, 320, 200, 25);
			frame.getContentPane().add(textField_8);
			textField_8.setColumns(10);
			
			JLabel intField = new JLabel("Intelligence");
			intField.setFont(new Font("Tahoma", Font.PLAIN, 20));
			intField.setBounds(10, 360, 184, 25);
			frame.getContentPane().add(intField);
			
			JTextField textField_9 = new JTextField();
			textField_9.setBounds(200, 360, 200, 25);
			frame.getContentPane().add(textField_9);
			textField_9.setColumns(10);
			
			JLabel wisField = new JLabel("Wisdom");
			wisField.setFont(new Font("Tahoma", Font.PLAIN, 20));
			wisField.setBounds(10, 400, 184, 25);
			frame.getContentPane().add(wisField);
			
			JTextField textField_10 = new JTextField();
			textField_10.setBounds(200, 400, 200, 25);
			frame.getContentPane().add(textField_10);
			textField_10.setColumns(10);
			
			JLabel chaField = new JLabel("Charisma");
			chaField.setFont(new Font("Tahoma", Font.PLAIN, 20));
			chaField.setBounds(10, 440, 184, 25);
			frame.getContentPane().add(chaField);
			
			JTextField textField_11 = new JTextField();
			textField_11.setBounds(200, 440, 200, 25);
			frame.getContentPane().add(textField_11);
			textField_11.setColumns(10);
			
			JLabel lblNewLabel_12 = new JLabel("Languages");
			lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_12.setBounds(10, 480, 184, 25);
			frame.getContentPane().add(lblNewLabel_12);
			
			JTextField langField = new JTextField();
			langField.setBounds(200, 480, 200, 25);
			frame.getContentPane().add(langField);
			langField.setColumns(10);
			
			JButton btnNewButton = new JButton("Create");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnNewButton.setBounds(200, 45, 200, 20);
			btnNewButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) { //Open create statblock menu
					CallableStatement cs = null;
					try {
						cs = dbService.getConnection().prepareCall("{? = call CreateStatblock(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

						String Name = new String(NameField.getText());
						cs.setString(2, Name.trim());
						
						String Race = new String(raceField.getText());
						cs.setString(6, Race.trim());
						
						int Speed = Integer.parseInt(speedField.getText());
						cs.setInt(5, Speed);
						
						int AC = Integer.parseInt(acField.getText());
						cs.setInt(4, AC);
						
						int Str = Integer.parseInt(strengthField.getText());
						cs.setInt(7, Str);
						
						int Dex = Integer.parseInt(dexField.getText());
						cs.setInt(8, Dex);
						
						int Con = Integer.parseInt(conField.getText());
						cs.setInt(9, Con);
						
						int Int = Integer.parseInt(intField.getText());
						cs.setInt(10, Int);
						
						int Wis = Integer.parseInt(wisField.getText());
						cs.setInt(11, Wis);
						
						int Cha = Integer.parseInt(chaField.getText());
						cs.setInt(12, Cha);
						
						String Languages = new String(langField.getText());
						cs.setString(3, Languages.trim());
						
						cs.registerOutParameter(1, Types.INTEGER);
						
						cs.execute();
						//int result = cs.getInt(1);
						/*
						if (result == 1) {
							JOptionPane.showMessageDialog(null, "ERROR: Restaurant name cannot be null or empty");
							
						}
						else if (result == 2) {
							JOptionPane.showMessageDialog(null, "ERROR: Restaurant name already exists.");
						}
						*/
				
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
				}
			});
			frame.getContentPane().add(btnNewButton);
			
//			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
