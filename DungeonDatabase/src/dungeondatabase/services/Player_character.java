package dungeondatabase.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Player_character {

	private DatabaseConnectionService dbService = null;
	
	public Player_character(DatabaseConnectionService dbService) {
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
	
	public ArrayList<ArrayList<String>> getStatBlock() {
		ArrayList<ArrayList<String>> Statblocks = new ArrayList<ArrayList<String>>();
		for (int i = 0;i < 12;i++) {
			Statblocks.add(new ArrayList<String>());
		}
		
		String s=String.format("Select * from %s.dbo.StatBlock", this.dbService.databaseName);
		CallableStatement cs = null;
			
		try{
			this.dbService.connect("Dungeon19", "Password123");
			Connection c = this.dbService.getConnection();
			cs = c.prepareCall(s);
			ResultSet r1 = cs.executeQuery();
			//results.add(r1);
			while(r1.next()) {
				Statblocks.get(0).add(r1.getString("Name"));
				Statblocks.get(1).add(r1.getString("StatID"));
				Statblocks.get(2).add(r1.getString("Languages"));
				Statblocks.get(3).add(r1.getString("AC"));
				Statblocks.get(4).add(r1.getString("Speed"));
				Statblocks.get(5).add(r1.getString("Race"));
				Statblocks.get(6).add(r1.getString("STR"));
				Statblocks.get(7).add(r1.getString("DEX"));
				Statblocks.get(8).add(r1.getString("CON"));
				Statblocks.get(9).add(r1.getString("INT"));
				Statblocks.get(10).add(r1.getString("WIS"));
				Statblocks.get(11).add(r1.getString("CHA"));
			}
			
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return Statblocks;
	}
	

	public ArrayList<String> getPlayerCharacter() {	
		//TO DO: Task 2 
		ArrayList<String> characters = new ArrayList<String>();
		java.sql.Statement stat = null;
		String s=String.format("Select Name from %s.dbo.Player_Character", this.dbService.databaseName);
		try{
			Connection c = this.dbService.getConnection();
			stat = c.createStatement();
			ResultSet r = ((java.sql.Statement) stat).executeQuery(s);
			while(r.next()){
				String name = r.getString("Name");
				characters.add(name);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return characters;
	}
	
	public ArrayList<String> getParty() {	
		//TO DO: Task 2 
		ArrayList<String> pty = new ArrayList<String>();
		java.sql.Statement stat = null;
		String s = String.format("Select PartyID from %s.dbo.Player_Character", this.dbService.databaseName);
		try{
			Connection c = this.dbService.getConnection();
			stat = c.createStatement();
			ResultSet r = ((java.sql.Statement) stat).executeQuery(s);
			while(r.next()){
				String PartyID = r.getString("PartyID");
				if (PartyID == null || PartyID == "") {
					PartyID = "Null";
				}
				pty.add(PartyID);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return  pty;
	}
	
	public ArrayList<String> getBackStory() {	
		//TO DO: Task 2 
		ArrayList<String> stories = new ArrayList<String>();
		java.sql.Statement stat = null;
		String s=String.format("Select back_story from %s.dbo.Player_Character", this.dbService.databaseName);
		try{
			Connection c = this.dbService.getConnection();
			stat = c.createStatement();
			ResultSet r = ((java.sql.Statement) stat).executeQuery(s);
			while(r.next()){
				String bc = r.getString("back_story");
				stories.add(bc);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return stories;
	}
}