package dungeondatabase.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataImport {
	
	public static void main(String[] args) {
		importFromFile(null);
	}
	
	public static boolean importFromFile(DatabaseConnectionService db) {
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
		for (String strBlock : textArray) {
			String[] individualRows = strBlock.split("\n");
			for (String strRow : individualRows) {
				String[] individualElements = strRow.split(", ");
				for (String str : individualElements) {
					System.out.println(str);
					System.out.println("----------------------------------");
				}
			}
		}
		
		return false;
	}

}
