package dungeondatabase.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DatabaseConnectionService {

	//DO NOT EDIT THIS STRING, YOU WILL RECEIVE NO CREDIT FOR THIS TASK IF THIS STRING IS EDITED
	private final String SampleURL = "jdbc:sqlserver://${dbServer};databaseName=${dbName};user=${user};password={${pass}}";

	private Connection connection = null;

	public String databaseName;
	public String serverName;
	public String username;

	public DatabaseConnectionService(String serverName, String databaseName) {
		//DO NOT CHANGE THIS METHOD
		this.serverName = serverName;
		this.databaseName = databaseName;
	}

	public boolean connect(String user, String pass) {
		//TO DO: Task 1
		//BUILD YOUR CONNECTION STRING HERE USING THE SAMPLE URL ABOVE
//		System.out.println("Username:" + user);
//		System.out.println("Password:" + pass);
		String connectionUrl = "jdbc:sqlserver://" + serverName + ";"
                        		+ "database=" + databaseName + ";"
                        		+ "user=" + user + ";"
                        		+ "password=" + pass;
//		System.out.println("URL:" + connectionUrl);
		try{
			this.connection = DriverManager.getConnection(connectionUrl);
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Connot Find Database: " + databaseName + "\n Click on the title button to create a new database");
//			e.printStackTrace();
//				// Create View
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\CharacterSheet_View.sql");		
//				// Create Procedure
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Add_Player_To_Party_proc.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Add_Statblock.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Change_Backstory.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Change_Player_Name.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Create_Campaign_Proc.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Create_Item_Proc.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Create_NPC_Proc.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Create_Spell_Proc.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Create_User.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\CreateStatBlock_Proc.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Delete_Campaign_Proc.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Delete_NPC_proc.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Delete_Player.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Delete_User.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\DM_procedure.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Drop_DM_Proc.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Drop_Party_proc.sql");	
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Drop_Player_From_Party_proc.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Drop_Statblock_Player.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Increment_Level_proc.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Join_Date_Update.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Make_NPC_proc.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Make_Party_proc.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Modify_NPC_proc.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Modify_Party_proc.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Modify_StatBlock_Proc.sql");
//				db.runSQL("C:\\Users\\Administrator\\Desktop\\D-D-Database\\Queries\\Create_Player.sql");	
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			this.closeConnection();
			return false;
		}
		return true;
	}
	

	public Connection getConnection() {
		return this.connection;
	
	}
	
	public void closeConnection() {
		//TO DO: Task 1
		try {
			if(this.connection != null) this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
