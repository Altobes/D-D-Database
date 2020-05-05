package dungeondatabase.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionService {

	//DO NOT EDIT THIS STRING, YOU WILL RECEIVE NO CREDIT FOR THIS TASK IF THIS STRING IS EDITED
	private final String SampleURL = "jdbc:sqlserver://${dbServer};databaseName=${dbName};user=${user};password={${pass}}";

	private Connection connection = null;

	public String databaseName;
	public String serverName;

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
			System.out.println("Error!!!");
			e.printStackTrace();
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
