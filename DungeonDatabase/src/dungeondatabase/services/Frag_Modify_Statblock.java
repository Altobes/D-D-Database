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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.awt.Color;
import javax.swing.JTextField;

public class Frag_Modify_Statblock {
	JFrame frame;
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
					Frag_Modify_Statblock window = new Frag_Modify_Statblock();
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
	public Frag_Modify_Statblock() {
		initialize();
	}

	public Frag_Modify_Statblock(String user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dbService.connect("Dungeon19", "Password123"); // replace "username" and "password" with your own rose login
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 500, 650);
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

		JLabel conLabel = new JLabel("Constitution");
		conLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		conLabel.setBounds(10, 320, 184, 25);
		frame.getContentPane().add(conLabel);

		JTextField conField = new JTextField();
		conField.setBounds(200, 320, 200, 25);
		frame.getContentPane().add(conField);
		conField.setColumns(10);

		JLabel intLabel = new JLabel("Intelligence");
		intLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		intLabel.setBounds(10, 360, 184, 25);
		frame.getContentPane().add(intLabel);

		JTextField intField = new JTextField();
		intField.setBounds(200, 360, 200, 25);
		frame.getContentPane().add(intField);
		intField.setColumns(10);

		JLabel wisLabel = new JLabel("Wisdom");
		wisLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		wisLabel.setBounds(10, 400, 184, 25);
		frame.getContentPane().add(wisLabel);

		JTextField wisField = new JTextField();
		wisField.setBounds(200, 400, 200, 25);
		frame.getContentPane().add(wisField);
		wisField.setColumns(10);

		JLabel chaLabel = new JLabel("Charisma");
		chaLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chaLabel.setBounds(10, 440, 184, 25);
		frame.getContentPane().add(chaLabel);

		JTextField chaField = new JTextField();
		chaField.setBounds(200, 440, 200, 25);
		frame.getContentPane().add(chaField);
		chaField.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Add Languages");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_12.setBounds(10, 480, 184, 25);
		frame.getContentPane().add(lblNewLabel_12);

		JTextField langField = new JTextField();
		langField.setBounds(200, 480, 200, 25);
		frame.getContentPane().add(langField);
		langField.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Delete Character");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_13.setBounds(10, 520, 184, 25);
		frame.getContentPane().add(lblNewLabel_13);
		
		JTextField delCharField = new JTextField();
		delCharField.setBounds(200, 520, 200, 25);
		frame.getContentPane().add(delCharField);
		delCharField.setColumns(10);
		
		JLabel statID = new JLabel("Stat ID");
		statID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		statID.setBounds(10, 560, 184, 25);
		frame.getContentPane().add(statID);

		JTextField statField = new JTextField();
		statField.setBounds(200, 560, 200, 25);
		frame.getContentPane().add(statField);
		statField.setColumns(10);

		JButton btnNewButton = new JButton("Modify");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(200, 45, 200, 20);
		
		class statListener implements ActionListener {
			
			String user;
			
			public statListener(String user) {
				System.out.println(user);
				this.user = user;
			}

			@Override
			public void actionPerformed(ActionEvent e) { //Open create statblock menu
				CallableStatement cs = null;
				try {
					cs = dbService.getConnection().prepareCall("{? = call Modify_StatBlock(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			//----------------------------------------------------------------------------------------------------------------------------------
					int StatID = -1;					
					try {
						StatID = Integer.parseInt(statField.getText());
					} catch (NumberFormatException exception) {					
						StatID = -1;				
					}
					if (StatID < 0) {
						JOptionPane.showMessageDialog(null, "Invalid Stat Block ID");
						return;
					}					
					String query = String.format("Select StatID From Player_Character Where Username = '%s' and StatID = %d", user, StatID);
					System.out.println("\n"+user+"\n");
					Statement st = cs.getConnection().createStatement();
					ResultSet rs = st.executeQuery(query);
					if (rs.getRow() == 0) {
						JOptionPane.showMessageDialog(null, "You do not have access to this stat block or it does not exist");
						return;
					}					
					setAttributes(cs, StatID, 2, statField);
			//----------------------------------------------------------------------------------------------------------------------------------
					
					String delChar = new String(delCharField.getText());
					if (!delChar.equals("")) {
						String query2 = String.format("Select Name From Player_Character Where Username = '%s' and StatID = %d and Name = '%s'", user, StatID, delChar);
						Statement st2 = cs.getConnection().createStatement();
						ResultSet rs2 = st2.executeQuery(query2);
						if (rs2.getRow() == 0) {
							JOptionPane.showMessageDialog(null, "You do not have access to this stat block or it does not exist");
							return;
						} else {
							cs.setString(8, delChar.trim());
						}
					}
					
					
					String Name = new String(NameField.getText());
					cs.setString(3, Name.trim());
					
					String Race = new String(raceField.getText());
					cs.setString(7, Race.trim());
					
					int Speed = -1;
					setAttributes(cs, Speed, 6, speedField);
					
					int AC = -1;
					setAttributes(cs, AC, 5, acField);
					
					int Str = -1;
					setAttributes(cs, Str, 9, strengthField);
					
					int Dex = -1;
					setAttributes(cs, Dex, 10, dexField);
					
					int Con = -1;
					setAttributes(cs, Con, 11, conField);
					
					int Int = -1;
					setAttributes(cs, Int, 12, intField);
					
					int Wis = -1;
					setAttributes(cs, Wis, 13, wisField);
					
					int Cha = -1;
					setAttributes(cs, Cha, 14, chaField);
					
					String Languages = new String(langField.getText());
					cs.setString(4, Languages.trim());
					
					cs.registerOutParameter(1, Types.INTEGER);
					
					cs.execute();
					
					JOptionPane.showMessageDialog(null, "Stat Block Created");
					frame.dispose();
					
			
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
			}
		}
		
		ActionListener stat = new statListener(user);
		btnNewButton.addActionListener(stat);
		
		
		frame.getContentPane().add(btnNewButton);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void setAttributes(CallableStatement cs, int attr, int parameterNumber, JTextField textField)
			throws SQLException {
		try {
			attr = Integer.parseInt(textField.getText());
		} catch (NumberFormatException exception) {
			attr = -1;
		}
		cs.setInt(parameterNumber, attr);
	}
}
