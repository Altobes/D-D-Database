package dungeondatabase.services;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.awt.Choice;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Frag_Skills {

	JFrame frame;
	private JTable table;
	public String user;
	private DatabaseConnectionService dbService = new DatabaseConnectionService(Dataclass.SNAME,Dataclass.DBNAME);
	private String character_num;
	private DefaultTableModel tb;
	private Player_character p;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frag_Skills window = new Frag_Skills();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frag_Skills() {
		this.user = "altobes";
		initialize();
	}
	
	public Frag_Skills(String username) {
		this.user = username;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dbService.connect(Dataclass.USER, Dataclass.PASS);
		p = new Player_character(dbService);
		ArrayList<ArrayList<String>> characters = p.getAllCharacters(user);
		
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 650, 300);
		frame.getContentPane().setLayout(null);
		tb = new DefaultTableModel(new Object[][] {{"ID", "Name", "Description"},},
				new String[] {"New Column", "New column", "New column"}) 
			{
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
		
		
		JLabel lblNewLabel = new JLabel("Dungeon Database");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(100, 0, 250, 42);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Skills");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 40, 70, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Add Skill");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(200, 39, 200, 25);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { //Open create character menu
				Frag_Create_Skill window = new Frag_Create_Skill(character_num);
				window.frame.setVisible(true);
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton btnDelete = new JButton("Delete Skill");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(410, 39, 200, 25);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (deleteSkill()) {
					JOptionPane.showMessageDialog(null, "Successfully deleted Skill");
				}
				else {
					JOptionPane.showMessageDialog(null, "ERROR: Could not delete Skill");
				}
			}
		});
		frame.getContentPane().add(btnDelete);
		
		Choice choice_1 = new Choice();
		choice_1.add("None");
		for(int i = 0; i<characters.size(); i++) {
			choice_1.add(characters.get(i).get(0) + ": #" + characters.get(i).get(1));
		}	
		choice_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		choice_1.setBounds(90, 47, 100, 20);
		choice_1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent a) {
				String temp = choice_1.getSelectedItem();
				if (temp.equals("None")) {
					character_num = "None";
					return;
				}
				
				int t = -1;
				for (int i = temp.length()-1;i > 0;i--) {
					if (temp.substring(i, i+1).equals("#")) {
						t = i+1;
						break;
					}
				}
				
				character_num = temp.substring(t).trim();
				System.out.println(character_num);
//				int tempchar = p.getCharacter(character_num);
				
				fillTable(character_num);
//				if (tempchar == -1) {
//					JOptionPane.showMessageDialog(null, "No party ID found");
//					return;
//				}
//				Character = tempchar;
				
			}	
		});
		frame.getContentPane().add(choice_1);
		
		frame.addWindowListener(new WindowListener() {
			private boolean deactivated;
			@Override
			public void windowActivated(java.awt.event.WindowEvent e) {
				if (deactivated) {
					tb.setRowCount(1);
					fillTable(character_num);
				}
				deactivated = false;
			}
			@Override
			public void windowClosed(java.awt.event.WindowEvent e) {
			}
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
			}
			@Override
			public void windowDeactivated(java.awt.event.WindowEvent e) {
				deactivated = true;
			}
			@Override
			public void windowDeiconified(java.awt.event.WindowEvent e) {
			}
			@Override
			public void windowIconified(java.awt.event.WindowEvent e) {
			}
			@Override
			public void windowOpened(java.awt.event.WindowEvent e) {
			}
		});
		
		table = new JTable();
		table.setModel(tb);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(600);
		table.setBounds(10, 75, 716, 178);
		frame.getContentPane().add(table);
		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void fillTable(String character) {
		tb.setRowCount(1);
		if (character == "None") {
			return;
		}
		ArrayList<ArrayList<String>> Skills = p.getSkills(character);
		if (Skills.size() == 0) {
			System.out.println("Empty Skill List");
			return;
		}
		
		for(int i = 0; i < Skills.size(); i++) {
			tb.addRow(new Object[] {
				Skills.get(i).get(0), Skills.get(i).get(1), Skills.get(i).get(2)
			});
		}
	}
	
	private boolean deleteSkill() {
		CallableStatement cs = null;
		if (table.getSelectedRowCount() != 1) {
			return false;
		}
		try {
			Connection c = this.dbService.getConnection();
			cs = dbService.getConnection().prepareCall("{? = call Delete_Skill(?, ?)}");
			Object SkillID = table.getValueAt(table.getSelectedRow(), 0);
			cs.setString(2, SkillID.toString());
			cs.setString(3, p.getStatID(character_num));
			
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
