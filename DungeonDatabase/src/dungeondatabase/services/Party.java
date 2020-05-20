package dungeondatabase.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Party {

	private DatabaseConnectionService dbService = null;
	
	public Party(DatabaseConnectionService dbService) {
		this.dbService = dbService;
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
