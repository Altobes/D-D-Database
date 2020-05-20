package dungeondatabase.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DungeonMaster {
	private DatabaseConnectionService dbService = null;
	private String user;
	
	public DungeonMaster(DatabaseConnectionService dbService, String user) {
		this.dbService = dbService;
		this.user = user;
	}
	
	public ArrayList<ArrayList<String>> getDMCampaigns() {
		ArrayList<ArrayList<String>> cams = new ArrayList<ArrayList<String>>();
		CallableStatement cs = null;
		try{
			this.dbService.connect("Dungeon19", "Password123");
			Connection c = this.dbService.getConnection();
			cs = c.prepareCall("Select Name, Campaign.CampaignID\r\n" + 
					"From Campaign\r\n" + 
					"Inner Join (Select CampaignID\r\n" + 
					"			From DM\r\n" + 
					"			Inner Join DM_Manages_Campaign as DMC on DMC.DM_ID = DM.DM_ID\r\n" + 
					"			Where Username = ?) as DMDM on DMDM.CampaignID = Campaign.CampaignID");
			cs.setString(1, user);
			ResultSet r = cs.executeQuery();
			while(r.next()) {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(r.getString("Name"));
				temp.add(r.getString("CampaignID"));
				cams.add(temp);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return cams;
	}
	
	public int getParty(String campaign) {
		
		int party = -1;
		
		try{
			this.dbService.connect("Dungeon19", "Password123");
			Connection c = this.dbService.getConnection();
			String s = String.format("USE %s ", dbService.databaseName);
			PreparedStatement cs = c.prepareStatement(s + "Select Campaign.PartyID From Campaign Where Campaign.CampaignID = ?");
			cs.setInt(1, Integer.parseInt(campaign));
			ResultSet r = cs.executeQuery();
			
			if (!r.next()) {
				c.close();
				return party;
			}
			
			
			party = r.getInt("PartyID");
			c.close();
			return party;
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return party;
			
			 
		
	}

	
	public ArrayList<ArrayList<String>> getStatBlock(String campaign) {
		ArrayList<ArrayList<String>> Statblocks = new ArrayList<ArrayList<String>>();
		for (int i = 0;i < 13;i++) {
			Statblocks.add(new ArrayList<String>());
		}
		//java.sql.Statement state = null;
		
		String g = String.format("USE %s Select PC.Name, PlayerID, S.StatID, Languages, AC, Speed, Race, STR, DEX, CON, INT, WIS, CHA\r\n" + 
				"From StatBlock as S\r\n" + 
				"Inner Join Player_Character as PC on PC.StatID = S.StatID\r\n" + 
				"Where PC.PartyID  = (Select PartyID\r\n" + 
				"					 From Campaign\r\n" + 
				"					 Where CampaignID = %s)",dbService.databaseName ,campaign);
		//String s=String.format("Select * from %s.dbo.StatBlock ", this.dbService.databaseName);
		CallableStatement cs = null;
			
		try{
			this.dbService.connect("Dungeon19", "Password123");
			Connection c = this.dbService.getConnection();
			cs = c.prepareCall(g);
			ResultSet r1 = cs.executeQuery();
			while(r1.next()) {
				Statblocks.get(0).add(r1.getString("Name"));
				Statblocks.get(1).add(r1.getString("PlayerID"));
				Statblocks.get(2).add(r1.getString("StatID"));
				Statblocks.get(3).add(r1.getString("Languages"));
				Statblocks.get(4).add(r1.getString("AC"));
				Statblocks.get(5).add(r1.getString("Speed"));
				Statblocks.get(6).add(r1.getString("Race"));
				Statblocks.get(7).add(r1.getString("STR"));
				Statblocks.get(8).add(r1.getString("DEX"));
				Statblocks.get(9).add(r1.getString("CON"));
				Statblocks.get(10).add(r1.getString("INT"));
				Statblocks.get(11).add(r1.getString("WIS"));
				Statblocks.get(12).add(r1.getString("CHA"));
			}
			
			c.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return Statblocks;
	}
	
	public ArrayList<String> getParty() {	
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

