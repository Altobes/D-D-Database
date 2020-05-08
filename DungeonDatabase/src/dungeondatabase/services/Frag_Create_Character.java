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

public class Frag_Create_Character {
		JFrame frame;
		private DatabaseConnectionService dbService = 
				new DatabaseConnectionService("golem.csse.rose-hulman.edu", "DungeonDatabase");
		
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Frag_Create_Character window = new Frag_Create_Character();
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
		public Frag_Create_Character() {
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
			
			JLabel lblNewLabel_1 = new JLabel("Player Character");
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

			JLabel lblNewLabel_3 = new JLabel("Party");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_3.setBounds(10, 120, 184, 25);
			frame.getContentPane().add(lblNewLabel_3);
			
			JTextField partyField = new JTextField();
			partyField.setBounds(200, 120, 200, 25);
			frame.getContentPane().add(partyField);
			partyField.setColumns(10);
			
			JLabel lblNewLabel_4 = new JLabel("Stat");
			lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_4.setBounds(10, 160, 184, 25);
			frame.getContentPane().add(lblNewLabel_4);
			
			JTextField statField = new JTextField();
			statField.setBounds(200, 160, 200, 25);
			frame.getContentPane().add(statField);
			statField.setColumns(10);
			
			JLabel lblNewLabel_5 = new JLabel("Backstory");
			lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_5.setBounds(10, 200, 184, 25);
			frame.getContentPane().add(lblNewLabel_5);
			
			JTextField storyField = new JTextField();
			storyField.setBounds(200, 200, 200, 25);
			frame.getContentPane().add(storyField);
			storyField.setColumns(10);
			
			JButton btnNewButton = new JButton("Create");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnNewButton.setBounds(200, 45, 200, 20);
			btnNewButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) { //Open create character menu
					CallableStatement cs = null;
					try {
						cs = dbService.getConnection().prepareCall("{? = call Create_PlayerCharacter(?, ?, ?, ?, ?)}");
						
						String Name = new String(nameField.getText());
						cs.setString(2, Name);
						
						int party = -1;
						
						try {
					         party = Integer.parseInt(partwait sal me or yField.getText());
					      } catch (NumberFormatException e1) {
					      }
						cs.setInt(5, party);
						
						int Stat = -1;
						try {
					         Stat = Integer.parseInt(statField.getText());
					      } catch (NumberFormatException e1) {
					    	  JOptionPane.showMessageDialog(null, "ERROR: Need Valid Statblock ID");
					    	  return;
					      }
						cs.setInt(6, Stat);
						
						String Story = new String(storyField.getText());
						cs.setString(3, Story);
						
						String User = "altobes";  //UserID
						cs.setString(4, User);

						cs.registerOutParameter(1, Types.INTEGER);
						cs.execute();
						
						int result = cs.getInt(1);
						if (result == 3) {
							JOptionPane.showMessageDialog(null, "ERROR: Need Valid User ID");
						}
						else if (result == 4) {
							JOptionPane.showMessageDialog(null, "ERROR: Need Valid Party ID");
						}
						else if (result == 5) {
							JOptionPane.showMessageDialog(null, "ERROR: Need Valid Statblock ID");
						}
						else if (result == 6) {
							JOptionPane.showMessageDialog(null, "ERROR: Need Valid Name");
						}
						else if (result == 0) {
							JOptionPane.showMessageDialog(null, "Successfully Created Player");
						}
				
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
