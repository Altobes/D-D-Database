package dungeondatabase.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dataclass {
	public final static String USER = getUsername();
	public final static String PASS = getPassword();
	public final static String DBNAME = getDatabase();
	public final static String SNAME = getServer();
	
	public static void main(String[] args) {
		System.out.println(USER);
		System.out.println(PASS);
		System.out.println(DBNAME);
		System.out.println(SNAME);
	}
	
	public static String getUsername() {
		File f = new File("./src/dungeondatabase.properties");
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		String serverLine = "";
		String type = "";
		String[] server = new String[2];
		while(!type.equals("serverUsername") && s.hasNext()) {
			serverLine = s.nextLine();
			server = serverLine.split("=");
			type = server[0];
		}
		s.close();
		return server[1];
	}
	public static String getPassword() {
		File f = new File("./src/dungeondatabase.properties");
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		String serverLine = "";
		String type = "";
		String[] server = new String[2];
		while(!type.equals("serverPassword") && s.hasNext()) {
			serverLine = s.nextLine();
			server = serverLine.split("=");
			type = server[0];
		}
		s.close();
		return server[1];
	}
	public static String getDatabase() {
		File f = new File("./src/dungeondatabase.properties");
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		String serverLine = "";
		String type = "";
		String[] server = new String[2];
		while(!type.equals("databaseName") && s.hasNext()) {
			serverLine = s.nextLine();
			server = serverLine.split("=");
			type = server[0];
		}
		s.close();
		return server[1];
	}
	public static String getServer() {
		File f = new File("./src/dungeondatabase.properties");
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		String serverLine = "";
		String type = "";
		String[] server = new String[2];
		while(!type.equals("serverName") && s.hasNext()) {
			serverLine = s.nextLine();
			server = serverLine.split("=");
			type = server[0];
		}
		s.close();
		return server[1];
	}
}
