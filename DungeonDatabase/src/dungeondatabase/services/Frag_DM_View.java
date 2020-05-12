package dungeondatabase.services;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.JTree;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.Choice;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class Frag_DM_View {
	JFrame frame;
	private JLabel lblNewLabel_1;
	private Choice choice_1;
	private JTable table;
	private JLabel lblNewLabel_2;
	private JTextField textField;
	private JLabel lblNewLabel_3;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JButton btnNewButton_7;
	private JButton btnNewButton_8;
	private JButton btnNewButton_9;
	private DatabaseConnectionService dbService = 
			new DatabaseConnectionService("golem.csse.rose-hulman.edu", "DungeonDatabase");
	private DungeonMaster dm = new DungeonMaster(dbService);


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frag_DM_View window = new Frag_DM_View();
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
	public Frag_DM_View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
		dbService.connect("Dungeon19", "Password123"); // replace "username" and "password" with your own rose login
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dungeon Database");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(200, 10, 254, 31);
		frame.getContentPane().add(lblNewLabel);
		
		ArrayList<String> camps = dm.getDMCampaigns();
		
		Choice choice_1 = new Choice();
		choice_1.add("None");
		for(int i = 0; i<camps.size(); i++) {
			choice_1.add(camps.get(i));
		}	
		choice_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		choice_1.setBounds(119, 47, 200, 20);
		
		choice_1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent a) {
				String camp = a.getItemSelectable().getSelectedObjects().toString();
				System.out.println(camp);
				
			}	
		});
		frame.getContentPane().add(choice_1);
		
		lblNewLabel_1 = new JLabel("Campaigns");
		lblNewLabel_1.setBounds(10, 52, 118, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		JButton create_Campaign = new JButton("New Campaign");
		create_Campaign.setFont(new Font("Tahoma", Font.PLAIN, 16));
		create_Campaign.setBounds(350, 47, 175, 20);
		create_Campaign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // Open create character menu
				Frag_Create_Campaign window = new Frag_Create_Campaign();
				window.frame.setVisible(true);
			}
		});
		frame.getContentPane().add(create_Campaign);
		
		JButton create_party = new JButton("New Party");
		create_party.setFont(new Font("Tahoma", Font.PLAIN, 16));
		create_party.setBounds(535, 47, 125, 20);
		create_party.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // Open create character menu
				Frag_Create_Campaign window = new Frag_Create_Campaign();
				window.frame.setVisible(true);
			}
		});
		frame.getContentPane().add(create_party);

		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setForeground(Color.BLACK);
		table.setBackground(Color.LIGHT_GRAY);
		table.setFillsViewportHeight(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel tb = new DefaultTableModel(
			new Object[][] {
			}, new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
		}); 
		/*
		for(int i = 0; i<pc_temp.size(); i++) {
			tb.addRow(new Object[] {
				pc_temp.get(i)
			});
		}
		*/
		table.setModel(tb);
		table.getColumnModel().getColumn(0).setPreferredWidth(89);
		table.setColumnSelectionAllowed(true);
		table.setBounds(10, 101, 666, 334);
		frame.getContentPane().add(table);
		
		btnNewButton_1 = new JButton("Character");
		btnNewButton_1.setBounds(10, 75, 96, 20);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frag_Player_character window = new Frag_Player_character();
				window.frame.setVisible(true);

			}	
		});
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Stat");
		btnNewButton_2.setBounds(104, 75, 85, 20);
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frag_Stat_Block window = new Frag_Stat_Block();
				window.frame.setVisible(true);

			}	
		});
		frame.getContentPane().add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Items");
		btnNewButton_3.setBounds(187, 75, 85, 20);
		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frag_Items window = new Frag_Items();
				window.frame.setVisible(true);

			}	
		});
		frame.getContentPane().add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Spells");
		btnNewButton_4.setBounds(268, 75, 85, 20);
		btnNewButton_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frag_Spells window = new Frag_Spells();
				window.frame.setVisible(true);

			}	
		});
		frame.getContentPane().add(btnNewButton_4);
		
		btnNewButton_5 = new JButton("Skills");
		btnNewButton_5.setBounds(347, 75, 85, 20);
		btnNewButton_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frag_Skills window = new Frag_Skills();
				window.frame.setVisible(true);

			}	
		});
		frame.getContentPane().add(btnNewButton_5);
		
		btnNewButton_6 = new JButton("NPC");
		btnNewButton_6.setBounds(431, 75, 81, 20);
		btnNewButton_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frag_NPC window = new Frag_NPC();
				window.frame.setVisible(true);

			}	
		});
		frame.getContentPane().add(btnNewButton_6);
		
		btnNewButton_7 = new JButton("Campaign");
		btnNewButton_7.setBounds(512, 75, 82, 20);
		btnNewButton_7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frag_Campaign window = new Frag_Campaign();
				window.frame.setVisible(true);

			}	
		});
		frame.getContentPane().add(btnNewButton_7);
		
		btnNewButton_8 = new JButton("Party");
		btnNewButton_8.setBounds(594, 75, 85, 20);
		btnNewButton_8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frag_Party window = new Frag_Party();
				window.frame.setVisible(true);

			}	
		});
		frame.getContentPane().add(btnNewButton_8);
		
//			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

