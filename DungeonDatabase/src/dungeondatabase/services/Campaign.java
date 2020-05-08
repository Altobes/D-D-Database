package dungeondatabase.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Campaign {
private DatabaseConnectionService dbService = null;
	
	public Campaign(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}

	public ArrayList<String> getCampaignName() {	
		ArrayList<String> CampaignName = new ArrayList<String>();
		java.sql.Statement stat = null;
		String s = String.format("Select Name from %s.dbo.Campaign", this.dbService.databaseName);
		try{
			Connection c = this.dbService.getConnection();
			stat = c.createStatement();
			ResultSet r = ((java.sql.Statement) stat).executeQuery(s);
			while(r.next()){
				String name = r.getString("Name");
				CampaignName.add(name);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return CampaignName;
	}
	
	public ArrayList<String> getCampaignID() {	
		ArrayList<String> CampaignIDs = new ArrayList<String>();
		java.sql.Statement stat = null;
		String s = String.format("Select CampaignID from %s.dbo.Campaign", this.dbService.databaseName);
		try{
			Connection c = this.dbService.getConnection();
			stat = c.createStatement();
			ResultSet r = ((java.sql.Statement) stat).executeQuery(s);
			while(r.next()){
				String CampaignID = r.getString("CampaignID");
				CampaignIDs.add(CampaignID);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return  CampaignIDs;
	}
	
	public ArrayList<String> getPartyID() {	
		ArrayList<String> PartyIDs = new ArrayList<String>();
		java.sql.Statement stat = null;
		String s = String.format("Select PartyID from %s.dbo.Campaign", this.dbService.databaseName);
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
}