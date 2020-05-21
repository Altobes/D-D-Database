package dungeondatabase.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataImport {
	
	public static void main(String[] args) {
		DatabaseConnectionService dbService = new DatabaseConnectionService(Dataclass.SNAME, Dataclass.DBNAME);
		try {
			importFromFile(dbService);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean importFromFile(DatabaseConnectionService db) throws SQLException {
		
		String[] inserts = populateArray(); //Fill this with insert statements
		
		int[] params = new int[] {0, 0, 2, 12, 6, 2, 3, 3, 3, 4, 3, 5, 2, 2, 2, 2}; //Fill this with the number of 
											//parameters required for each statement
											//Number of ?'s
		
		int insertIndex = 0; //The index to keep track of which insert to run
		
		db.connect(Dataclass.USER, Dataclass.PASS);
		Connection c = db.getConnection();
		
		File text = new File("./src/import.txt");
		Scanner s = null;
		try {
			s = new Scanner(text);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		ArrayList<String> textArray = new ArrayList<>();
		
		s.useDelimiter(":");
		while(s.hasNext()) {
			textArray.add(s.next());
		}
		s.close();	
		
		ArrayList<String> args = new ArrayList<String>();
		for (String strBlock : textArray) {
			String[] individualRows = strBlock.split("\n");
			for (String strRow : individualRows) {
				if (insertIndex < 2) {
					insertIndex++;
					continue;
				}
				if (strRow.charAt(0) == ';') //Break if it's the end of the block
					break;
				if (strRow.charAt(0) == '\r') //Continue if just whitespace
					continue;
				String[] individualElements = strRow.split(", ");
				for (String str : individualElements) {
					if (str.charAt(0) == '\r') //Continue if just whitespace
						continue;
					str = str.trim();
					args.add(str);
					System.out.println(str + " " + inserts[insertIndex]);
				}
				System.out.println("----------------------------------");
				//Do stuff with insert statement while inserting arguments here
				CallableStatement cs = c.prepareCall(inserts[insertIndex]);
				for (int i = 1; i <= params[insertIndex]; i++) {
					cs.setString(i, args.get(i-1));
				}
//				cs.execute();
				cs.cancel();
				args.clear();
			}
			System.out.println("----------------------------------");
			insertIndex++;
		}
		return true;
	}
	
	private static String[] populateArray() {
		String[] s = new String[16];
		s[2] = String.format("{Call Register(?, ?)}");
		s[3] = String.format("{Insert into StatBlock * Values(?, ?, ?, ?,?,?,?,?,?,?,?,?)}");
		s[4] = String.format("{Insert into PlayerCharacter * Values(?,?,?,?,?,?)}");
		s[5] = String.format("{Insert into DM * Values (?, ?)}");
		s[6] = String.format("{Insert into Items * Values (?, ?, ?)}");
		s[7] = String.format("{Insert into Spells * Values(?, ?, ?)}");
		s[8] = String.format("{Insert into Skills * Values(?, ?, ?)}");
		s[9] = String.format("{Insert into Party * Values(?, ?, ?, ?)}");
		s[10] = String.format("{Insert into Campaign * Values(?, ?, ?)}");
		s[11] = String.format("{Insert into NPC * Values(?, ?, ?, ?, ?)}");
		s[12] = String.format("{Insert into DM_Manages_Campaign * Values(?, ?}");
		s[13] = String.format("{Insert into StatItems * Values (?, ?)}");
		s[14] = String.format("{Insert into StatSpells * Values (?, ?)}");
		s[15] = String.format("{Insert into StatSkills * Values (?, ?)}");
		//DM_Manages should be updated automatically 
		//Populate the array
		//In order of the import document
		//0th element is ignored
		//Also update the params with the number of parameters in each statement
		//Grab data from somewhere and return import statements or something
		return s;
	}

}
