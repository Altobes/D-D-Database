package dungeondatabase.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class NPC {

	private DatabaseConnectionService dbService = null;
	
	public NPC(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}
	
	public boolean addNPC(String name, int cr, int statID, int campaignID) {
		//TO DO: Task 5
		try{
			Connection c = this.dbService.getConnection();
			CallableStatement proc = c.prepareCall("{?=call CreateNPC(?,?,?,?)}");
			proc.registerOutParameter(1, Types.INTEGER);
			proc.setString(2, name);
			proc.setInt(3, cr);
			proc.setInt(4, statID);
			proc.setInt(5, campaignID);
			proc.execute();
			switch(proc.getInt(1)) {
			case 1:
				JOptionPane.showMessageDialog(null, "Please Enter a NPC Name");
				return false;
			case 2:
				JOptionPane.showMessageDialog(null, "NPC Name Exist, Please try another one");
				return false;
			}
			JOptionPane.showMessageDialog(null, "Successful adding: "+ name);
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!!");
			return false;
		}
	}
	

	public ArrayList<String> getNPC() {	
		ArrayList<String> characters = new ArrayList<String>();
		java.sql.Statement stat = null;
		String s=String.format("Select Name from %s.dbo.NPC", this.dbService.databaseName);
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
	
	public ArrayList<String> getCR() {	
		ArrayList<String> cr = new ArrayList<String>();
		java.sql.Statement stat = null;
		String s = String.format("Select CR from %s.dbo.NPC", this.dbService.databaseName);
		try{
			Connection c = this.dbService.getConnection();
			stat = c.createStatement();
			ResultSet r = ((java.sql.Statement) stat).executeQuery(s);
			while(r.next()){
				String cr_temp = r.getString("CR");
				cr.add(cr_temp);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return  cr;
	}
}
