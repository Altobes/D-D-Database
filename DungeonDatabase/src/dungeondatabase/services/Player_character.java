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
		for (int i = 0;i < 11;i++) {
			Statblocks.add(new ArrayList<String>());
		}
		//java.sql.Statement state = null;
		ArrayList<String> states = new ArrayList<String>();
		
		
		String s=String.format("Select * from %s.dbo.StatBlock", this.dbService.databaseName);
		String s1=String.format("Select Name from %s.dbo.StatBlock", this.dbService.databaseName);   states.add(s1);
		String s2=String.format("Select AC from %s.dbo.StatBlock", this.dbService.databaseName);     states.add(s2);
		String s3=String.format("Select Speed from %s.dbo.StatBlock", this.dbService.databaseName);  states.add(s3);
		String s4=String.format("Select StatID from %s.dbo.StatBlock", this.dbService.databaseName); states.add(s4);
		String s5=String.format("Select Race from %s.dbo.StatBlock", this.dbService.databaseName);   states.add(s5);
		String s6=String.format("Select STR from %s.dbo.StatBlock", this.dbService.databaseName);    states.add(s6);
		String s7=String.format("Select DEX from %s.dbo.StatBlock", this.dbService.databaseName);    states.add(s7);
		String s8=String.format("Select CON from %s.dbo.StatBlock", this.dbService.databaseName);    states.add(s8);
		String s9=String.format("Select INT from %s.dbo.StatBlock", this.dbService.databaseName);    states.add(s9);
		String s10=String.format("Select WIS from %s.dbo.StatBlock", this.dbService.databaseName);   states.add(s10);
		String s11=String.format("Select CHA from %s.dbo.StatBlock", this.dbService.databaseName);   states.add(s11);
		CallableStatement cs = null;
			
		try{
			//Connection c = this.dbService.getConnection();
			//state = c.createStatement();
			ArrayList<ResultSet> results = new ArrayList<ResultSet>();
			
			/*for (int i = 1;i < states.size();i++) {
				this.dbService.connect("Dungeon19", "Password123");
				Connection c = this.dbService.getConnection();
				cs = c.prepareCall(states.get(i));
				ResultSet r1 = cs.executeQuery();
				results.add(r1);
				System.out.println(r1.getRow());
			} */
			this.dbService.connect("Dungeon19", "Password123");
			Connection c = this.dbService.getConnection();
			cs = c.prepareCall(s);
			ResultSet r1 = cs.executeQuery();
			//results.add(r1);
			while(r1.next()) {
				Statblocks.get(0).add(r1.getString("Name"));
				Statblocks.get(1).add(r1.getString("AC"));
				Statblocks.get(2).add(r1.getString("Speed"));
				Statblocks.get(3).add(r1.getString("StatID"));
				Statblocks.get(4).add(r1.getString("Race"));
				Statblocks.get(5).add(r1.getString("STR"));
				Statblocks.get(6).add(r1.getString("DEX"));
				Statblocks.get(7).add(r1.getString("CON"));
				Statblocks.get(8).add(r1.getString("INT"));
				Statblocks.get(9).add(r1.getString("WIS"));
				Statblocks.get(10).add(r1.getString("CHA"));
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
