package dungeondatabase.services;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import javax.swing.JOptionPane;

public class DBInit {

	private static final String URL = "jdbc:sqlserver://golem.csse.rose-hulman.edu";
	private static String USER = Dataclass.USER;
	private static String PASSWORD = Dataclass.PASS;
	private static String INSTRUCTIONS = new String();

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	public static void createNewDatabase(String f) throws SQLException {
		String s = new String();
		StringBuffer sb = new StringBuffer();

		try {
			FileReader fr = new FileReader(new File(f));
			BufferedReader br = new BufferedReader(fr);
			while ((s = br.readLine()) != null)
				sb.append(s);
			br.close();
			String[] temp = sb.toString().split(";");
			Connection c = DBInit.getConnection();
			Statement st = c.createStatement();

			for (int i = 0; i < temp.length; i++) {
				if (!temp[i].trim().equals("")) {
					st.executeUpdate(temp[i]);
					System.out.println(">>" + temp[i]);
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}

	}

	public static void runSql(String fileName, String databaseName, String user, String pass) {
		// SQLite connection string
		String url = "jdbc:sqlserver://golem.csse.rose-hulman.edu" + ";" + "database=" + databaseName + ";" + "user="
				+ user + ";" + "password=" + pass;
		final String EoL = System.getProperty("line.separator");
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(fileName), Charset.defaultCharset());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StringBuilder sb = new StringBuilder();
		for (String line : lines) {
			sb.append(line).append(EoL);
		}
		final String content = sb.toString();

		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(content);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}
}