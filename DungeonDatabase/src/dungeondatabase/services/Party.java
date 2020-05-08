package dungeondatabase.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Party {

	private DatabaseConnectionService dbService = null;
	
	public Party(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}
	
	public boolean addResturant(String restName, String addr, String contact) {
		//TO DO: Task 5
//		try{
//			Connection c = this.dbService.getConnection();
//			CallableStatement proc = c.prepareCall("{?=call AddRestaurant(?,?,?)}");
//			proc.registerOutParameter(1, Types.INTEGER);
//			proc.setString(2, restName);
//			proc.setString(3, addr);
//			proc.setString(4, contact);
//			proc.execute();
//			switch(proc.getInt(1)) {
//			case 1:
//				JOptionPane.showMessageDialog(null, "Please Enter a Restaurant Name");
//				return false;
//			case 2:
//				JOptionPane.showMessageDialog(null, "Restaurant Name Exist, Please try another one");
//				return false;
//			}
//			JOptionPane.showMessageDialog(null, "Successful adding: "+ restName);
//			return true;
//		}catch(SQLException e){
//			JOptionPane.showMessageDialog(null, "Error!!!");
//			return false;
//		}
		return true;
	}
	

	public ArrayList<String> getPartyName() {	
		ArrayList<String> Parties = new ArrayList<String>();
		java.sql.Statement stat = null;
		String s = String.format("Select Name from %s.dbo.Party", this.dbService.databaseName);
		try{
			Connection c = this.dbService.getConnection();
			stat = c.createStatement();
			ResultSet r = ((java.sql.Statement) stat).executeQuery(s);
			while(r.next()){
				String name = r.getString("Name");
				Parties.add(name);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return Parties;
	}
	
	public ArrayList<String> getPartyID() {	
		ArrayList<String> PartyIDs = new ArrayList<String>();
		java.sql.Statement stat = null;
		String s = String.format("Select PartyID from %s.dbo.Party", this.dbService.databaseName);
		try{
			Connection c = this.dbService.getConnection();
			stat = c.createStatement();
			ResultSet r = ((java.sql.Statement) stat).executeQuery(s);
			while(r.next()){
				String PartyID = r.getString("PartyID");
				PartyIDs.add(PartyID);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return  PartyIDs;
	}
	
	public ArrayList<String> getPartyLevel() {	
		ArrayList<String> Levels = new ArrayList<String>();
		java.sql.Statement stat = null;
		String s = String.format("Select Level from %s.dbo.Party", this.dbService.databaseName);
		try{
			Connection c = this.dbService.getConnection();
			stat = c.createStatement();
			ResultSet r = ((java.sql.Statement) stat).executeQuery(s);
			while(r.next()){
				String bc = r.getString("Level");
				Levels.add(bc);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return Levels;
	}
	
	public ArrayList<String> getCurrentLocation() {
		ArrayList<String> Places = new ArrayList<String>();
		java.sql.Statement stat = null;
		String s = String.format("Select [Current Location] from %s.dbo.Party", this.dbService.databaseName);
		try{
			Connection c = this.dbService.getConnection();
			stat = c.createStatement();
			ResultSet r = ((java.sql.Statement) stat).executeQuery(s);
			while(r.next()){
				String bc = r.getString("Current Location");
				Places.add(bc);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return Places;
	}
}
