package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import dungeondatabase.services.DBInit;
import dungeondatabase.services.DatabaseConnectionService;
import dungeondatabase.services.Dataclass;
import dungeondatabase.services.UserService;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class main {

	private JFrame frame;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JPasswordField passwordField;
	private JTextField textField;
	private String databaseName = Dataclass.DBNAME;
	private DatabaseConnectionService dbService = new DatabaseConnectionService(Dataclass.SNAME,Dataclass.DBNAME);
	private UserService us = new UserService(dbService);
	private static String user = Dataclass.USER;
	private static String pass = Dataclass.PASS;

	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frame.setVisible(true);
				} catch (Exception e) {
//					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

//		dbService.connect("Dungeon19", "Password123"); // replace "username" and "password" with your own rose login
		dbService.connect(user, pass);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel_1 = new JLabel("UserName");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 56, 83, 29);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(10, 115, 77, 29);
		frame.getContentPane().add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(120, 117, 229, 30);
		
		frame.getContentPane().add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(120, 58, 229, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(88, 180, 100, 45);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String password = new String(passwordField.getPassword());
				String username = textField.getText();
				if(us.login(username, password)) {
					frame.dispose();
				}
			}
			
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sign Up");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(260, 180, 100, 45);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String password = new String(passwordField.getPassword());
				System.out.println(password);
				System.out.println(textField.getText());
				us.register(textField.getText(), password);
			}	
		});
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("DungeonDatabase");
		btnNewButton_2.setForeground(Color.ORANGE);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNewButton_2.setBounds(120, 5, 229, 30);
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DBInit db = new DBInit();
				// Create Database
				try {
					JOptionPane.showMessageDialog(null, "Creating Database: " + databaseName);
					db.createNewDatabase("./Queries/CreateDB.sql");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// Create Table
				JOptionPane.showMessageDialog(null, "Creating Tables...");
				db.runSql("./Queries/CreateTable/Create_Party.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateTable/Create_Campaign.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateTable/Create_DM.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateTable/DM_Manages_Campaign.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateTable/Create_StatBlock.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateTable/Create_NPC.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateTable/Create_Player_Character.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateTable/Create_user.sql", databaseName, user, pass);
				
				// Create Store Procedures
				JOptionPane.showMessageDialog(null, "Creating Store Procedures...");
				db.runSql("./Queries/CreateStoreProcedure/Create/Add_Player_To_Party_proc.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Create/Add_Statblock.sql", databaseName, user, pass);
//				db.runSql("./Queries/CreateStoreProcedure/Create/AddDM_Proc.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Create/Change_Backstory.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Create/Change_Player_Name.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Create/Create_Campaign_Proc.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Create/Create_DM.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Create/Create_Item_Proc.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Create/Create_NPC_Proc.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Create/Create_Party.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Create/Create_Player.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Create/Create_Skill_Proc.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Create/Create_Spell_Proc.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Create/Create_User.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Create/CreateStatBlock_Proc.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Create/Insert_DM.sql", databaseName, user, pass);
				
				db.runSql("./Queries/CreateStoreProcedure/Delete/Delete_Campaign_Proc.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Delete/Delete_DM_Proc.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Delete/Delete_Items_Proc.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Delete/Delete_Party_proc.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Delete/Delete_Player.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Delete/Delete_Skills_proc.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Delete/Delete_Spells_proc.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Delete/Delete_User.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Delete/DM_Manages.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Delete/Drop_DM.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Delete/Drop_Player_From_Party_proc.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Delete/Drop_Statblock_Player.sql", databaseName, user, pass);
				
				db.runSql("./Queries/CreateStoreProcedure/Increment_Level_proc.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Modify_StatBlock_Proc.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Register.sql", databaseName, user, pass);
				db.runSql("./Queries/CreateStoreProcedure/Update_DM.sql", databaseName, user, pass);
				
				// Create View
				db.runSql("./Queries/CharacterSheet_View.sql", databaseName, user, pass);
				
				
				JOptionPane.showMessageDialog(null, "Finish Initializing Database: [" + databaseName + "] ^v^");
				
			}	
		});
		frame.getContentPane().add(btnNewButton_2);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void runApplication(String[] args) {
		// TODO Auto-generated method stub
		initialize();
		
	}
}
