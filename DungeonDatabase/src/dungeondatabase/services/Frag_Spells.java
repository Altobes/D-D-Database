package dungeondatabase.services;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.awt.Choice;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Frag_Spells {

	JFrame frame;
	private JTable table;
	public String user;
	private DatabaseConnectionService dbService = 
			new DatabaseConnectionService("golem.csse.rose-hulman.edu", "DungeonDatabase");
	private String character_num;
	private DefaultTableModel tb;
	private Player_character p;
	private int Character;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frag_Items window = new Frag_Items();
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
	public Frag_Spells() {
		this.user = "altobes";
		initialize();
	}
	
	public Frag_Spells(String username) {
		this.user = username;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dbService.connect("Dungeon19", "Password123");
		p = new Player_character(dbService);
		ArrayList<ArrayList<String>> characters = p.getAllCharacters(user);
		
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		tb = new DefaultTableModel(
				new Object[][] {
					{"Name", "Description"},
				},
				new String[] {
					"New column", "New column"
				}
			);
		
		JLabel lblNewLabel = new JLabel("Dungeon Database");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(100, 0, 250, 42);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Spells");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 40, 70, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Add Spell");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(200, 39, 200, 25);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { //Open create character menu
				Frag_Create_Spell window = new Frag_Create_Spell(character_num);
				window.frame.setVisible(true);
			}
		});
		frame.getContentPane().add(btnNewButton);
		
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
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.setBounds(10, 75, 416, 178);
		frame.getContentPane().add(table);
		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void fillTable(String character) {
		tb.setRowCount(1);
		if (character == "None") {
			return;
		}
		ArrayList<ArrayList<String>> items = p.getSpells(character);
		if (items.size() == 0) {
			System.out.println("Empty Skill List");
			return;
		}
		
		for(int i = 0; i < items.size(); i++) {
			tb.addRow(new Object[] {
				items.get(i).get(0), items.get(i).get(1)
			});
		}
	}


}
