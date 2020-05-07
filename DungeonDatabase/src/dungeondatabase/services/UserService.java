package dungeondatabase.services;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JOptionPane;

public class UserService {
	private static final Random RANDOM = new SecureRandom();
	private static final Base64.Encoder enc = Base64.getEncoder();
	private static final Base64.Decoder dec = Base64.getDecoder();
	private DatabaseConnectionService dbService = null;

	public UserService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}

	public boolean useApplicationLogins() {
		return true;
	}
	
	public boolean login(String username, String password) {
		//TO DO: Complete this method.
		java.sql.Statement stat = null;
		String text=String.format("Select PasswordSalt, PasswordHash "
				                + "From %s.dbo.[User] "
				                + "Where Username = '%s'", this.dbService.databaseName,username);
		try{
			Connection c = this.dbService.getConnection();
			stat=c.createStatement();
			ResultSet r = ((java.sql.Statement) stat).executeQuery(text);
			r.next();
			String s = r.getString("PasswordSalt");
			String h = r.getString("PasswordHash");
			byte[] sb = dec.decode(s);
			String temp = hashPassword(sb, password);
			if(temp.equals(h)){
				JOptionPane.showMessageDialog(null, "Login Success");
				Frag_Database window = new Frag_Database();
				window.frame.setVisible(true);
				return true;
			}else{
				JOptionPane.showMessageDialog(null, "Login Failed: incorrect hash");
				return false;
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Login Failed: sql exception");
			return false;	
		}
	}

	public boolean register(String username, String password) {
		//TO DO: Task 6
		try{
			Connection c = this.dbService.getConnection();
			CallableStatement proc = c.prepareCall("{?=call dbo.Register(?,?,?)}");
			System.out.println("???");
			proc.registerOutParameter(1, Types.INTEGER);
			byte[] psb=this.getNewSalt();
			String ps=this.getStringFromBytes(psb);
			String ph=this.hashPassword(psb, password);
			proc.setString(2, username);
			proc.setString(3, ps);
			proc.setString(4, ph);
			proc.execute();
			if(proc.getInt(1)!=0) throw new SQLException();
			JOptionPane.showMessageDialog(null, "Registration Success");
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Registration Failed");
			
		}
		return false;
	}
	
	public byte[] getNewSalt() {
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		return salt;
	}
	
	public String getStringFromBytes(byte[] data) {
		return enc.encodeToString(data);
	}

	public String hashPassword(byte[] salt, String password) {

		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKeyFactory f;
		byte[] hash = null;
		try {
			f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = f.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		}
		return getStringFromBytes(hash);
	}

}
