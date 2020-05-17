package dungeondatabase.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

	
	public ArrayList<ArrayList<String>> getStatBlock(String user) {
		ArrayList<ArrayList<String>> Statblocks = new ArrayList<ArrayList<String>>();
		for (int i = 0;i < 12;i++) {
			Statblocks.add(new ArrayList<String>());
		}
		
		String s=String.format("Select SB.Name, SB.AC, SB.Speed, SB.StatID, SB.Race, SB.STR, SB.DEX, SB.CON, SB.INT, SB.WIS, SB.CHA, SB.Languages \r\n" + 
				"From %s.dbo.StatBlock as SB \r\n" + 
				"Where StatID IN \r\n" + 
				"(Select statID\r\n" + 
				"From Player_Character pc\r\n" + 
				"Where pc.Username = '%s')", this.dbService.databaseName, user);

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
	
	public ArrayList<ArrayList<String>> getPlayerAll(String user) {
		ArrayList<ArrayList<String>> players = new ArrayList<ArrayList<String>>();
		for (int i = 0;i < 3;i++) {
			players.add(new ArrayList<String>());
		}
		String g = String.format("USE %s ",dbService.databaseName);
		CallableStatement cs = null;
			
		try{
			this.dbService.connect("Dungeon19", "Password123");
			Connection c = this.dbService.getConnection();
			cs = c.prepareCall(g + "Select PC.Name, PC.PlayerID, PC.back_story From Player_Character PC \r\n Where PC.Username = ?");
			cs.setString(1, user);
			ResultSet r = cs.executeQuery();
			while(r.next()) {
				players.get(0).add(r.getString("Name"));
				players.get(1).add(r.getString("PlayerID"));
				players.get(2).add(r.getString("back_story"));
			}
			c.close();
			return players;
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}
	

	public ArrayList<String> getPlayerCharacter(String user) {	
		ArrayList<String> characters = new ArrayList<String>();
		java.sql.Statement stat = null;
		String s=String.format("Select Name From %s.dbo.Player_Character Where Username = '%s'", this.dbService.databaseName, user);
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
	
	
	public ArrayList<String> getParty(String user) {	
		ArrayList<String> pty = new ArrayList<String>();
		java.sql.Statement stat = null;
		String s = String.format("Select PartyID from %s.dbo.Player_Character Where Username = '%s'", this.dbService.databaseName, user);
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
	
	public ArrayList<String> getBackStory(String user) {	
		ArrayList<String> stories = new ArrayList<String>();
		java.sql.Statement stat = null;
		String s=String.format("Select back_story from %s.dbo.Player_Character Where Username = '%s'", this.dbService.databaseName, user);
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
	
	public ArrayList<ArrayList<String>> getAllCharacters(String user) {
		ArrayList<ArrayList<String>> characters = new ArrayList<ArrayList<String>>();
		CallableStatement cs = null;
		try{
			this.dbService.connect("Dungeon19", "Password123");
			Connection c = this.dbService.getConnection();
			cs = c.prepareCall("Select Name, PlayerID\r\n" + 
					"FROM Player_Character\r\n"
					+ "WHERE Username = ?");
			cs.setString(1, user);
			ResultSet r = cs.executeQuery();
			while(r.next()) {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(r.getString("Name"));
				temp.add(r.getString("PlayerID"));
				characters.add(temp);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return characters;
	}

	public ArrayList<ArrayList<String>> getItems(String PlayerID) {
		ArrayList<ArrayList<String>> characters = new ArrayList<ArrayList<String>>();
		CallableStatement cs = null;
		try{
			this.dbService.connect("Dungeon19", "Password123");
			Connection c = this.dbService.getConnection();
			cs = c.prepareCall("SELECT Items.Name as [Name], Items.Description as [Description]\r\n" + 
					"FROM Player_Character as P join StatItems as S on S.StatID = P.StatID\r\n" + 
					"JOIN Items on Items.ItemID = S.ItemID\r\n" + 
					"WHERE P.PlayerID = ?");
			cs.setString(1, PlayerID);
			ResultSet r = cs.executeQuery();
			while(r.next()) {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(r.getString("Name"));
				temp.add(r.getString("Description"));
				characters.add(temp);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return characters;
	}
	
	public String getStatID(String PlayerID) {
		String id = "";
		CallableStatement cs = null;
		try{
			this.dbService.connect("Dungeon19", "Password123");
			Connection c = this.dbService.getConnection();
			cs = c.prepareCall("SELECT StatID\r\n" + 
					"FROM Player_Character\r\n" + 
					"WHERE PlayerID = ?");
			cs.setString(1, PlayerID);
			ResultSet r = cs.executeQuery();
			r.next();
			id = r.getString("StatID");
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return id;
		
	}

	public ArrayList<ArrayList<String>> getSkills(String PlayerID) {
		ArrayList<ArrayList<String>> characters = new ArrayList<ArrayList<String>>();
		CallableStatement cs = null;
		try{
			this.dbService.connect("Dungeon19", "Password123");
			Connection c = this.dbService.getConnection();
			cs = c.prepareCall("SELECT Skills.Name as [Name], Skills.Description as [Description]\r\n" + 
					"FROM Player_Character as P join StatSkills as S on S.StatID = P.StatID\r\n" + 
					"JOIN Skills on Skills.SkillID = S.SkillID\r\n" + 
					"WHERE P.PlayerID = ?");
			cs.setString(1, PlayerID);
			ResultSet r = cs.executeQuery();
			while(r.next()) {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(r.getString("Name"));
				temp.add(r.getString("Description"));
				characters.add(temp);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return characters;
	}

	public ArrayList<ArrayList<String>> getSpells(String PlayerID) {
		ArrayList<ArrayList<String>> characters = new ArrayList<ArrayList<String>>();
		CallableStatement cs = null;
		try{
			this.dbService.connect("Dungeon19", "Password123");
			Connection c = this.dbService.getConnection();
			cs = c.prepareCall("SELECT Spells.Name as [Name], Spells.Description as [Description]\r\n" + 
					"FROM Player_Character as P join StatSpells as S on S.StatID = P.StatID\r\n" + 
					"JOIN Spells on Spells.SpellID = S.SpellID\r\n" + 
					"WHERE P.PlayerID = ?");
			cs.setString(1, PlayerID);
			ResultSet r = cs.executeQuery();
			while(r.next()) {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(r.getString("Name"));
				temp.add(r.getString("Description"));
				characters.add(temp);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return characters;
	}
}