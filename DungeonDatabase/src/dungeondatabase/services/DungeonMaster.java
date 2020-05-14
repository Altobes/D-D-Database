package dungeondatabase.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DungeonMaster {

	

	private DatabaseConnectionService dbService = null;
	private String user;
	
	public DungeonMaster(DatabaseConnectionService dbService, String user) {
		this.dbService = dbService;
		this.user = user;
	}
	
	public boolean addResturant(String restName, String addr, String contact) {
		//TO DO: Task 5
//			try{
//				Connection c = this.dbService.getConnection();
//				CallableStatement proc = c.prepareCall("{?=call AddRestaurant(?,?,?)}");
//				proc.registerOutParameter(1, Types.INTEGER);
//				proc.setString(2, restName);
//				proc.setString(3, addr);
//				proc.setString(4, contact);
//				proc.execute();
//				switch(proc.getInt(1)) {
//				case 1:
//					JOptionPane.showMessageDialog(null, "Please Enter a Restaurant Name");
//					return false;
//				case 2:
//					JOptionPane.showMessageDialog(null, "Restaurant Name Exist, Please try another one");
//					return false;
//				}
//				JOptionPane.showMessageDialog(null, "Successful adding: "+ restName);
//				return true;
//			}catch(SQLException e){
//				JOptionPane.showMessageDialog(null, "Error!!!");
//				return false;
//			}
		return true;
	}
	
	public ArrayList<ArrayList<String>> getDMCampaigns() {
		
		ArrayList<ArrayList<String>> cams = new ArrayList<ArrayList<String>>();
		
		CallableStatement cs = null;
		try{
			this.dbService.connect("Dungeon19", "Password123");
			Connection c = this.dbService.getConnection();
			//cs = c.prepareCall(s);
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

