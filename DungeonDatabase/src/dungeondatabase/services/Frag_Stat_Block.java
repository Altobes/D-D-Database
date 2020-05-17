package dungeondatabase.services;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class Frag_Stat_Block {

	JFrame frame;
	private JTable table;
	private DatabaseConnectionService dbService = 
			new DatabaseConnectionService("golem.csse.rose-hulman.edu", "DungeonDatabase");
	private Player_character pc = new Player_character(dbService);
	
	private String user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frag_Stat_Block window = new Frag_Stat_Block();
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
	public Frag_Stat_Block() {
		initialize();
	}
	public Frag_Stat_Block(String user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 700, 500);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Dungeon Database");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(210, 0, 242, 40);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Stat Block");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 40, 114, 31);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
		
		
		table = new JTable();
		DefaultTableModel tb = new DefaultTableModel(
			new Object[][] {
				{"Name", "StatID", "Language", "AC", "Speed", "Race", "STR", "DEX", "CON", "INT", "WIS", "CHA"},
			},
			new String[] {
				"New column","New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		);
		table.setBounds(10, 81, 666, 372);
		
		fillTable(tb);
		
		table.setModel(tb);
		frame.getContentPane().add(table);
		
		JButton btnNewButton = new JButton("Modify Statblock");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(450, 39, 200, 25);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { //Open create character menu
				Frag_Modify_Statblock window = new Frag_Modify_Statblock();
				window.frame.setVisible(true);
				
			}
		});
		frame.getContentPane().add(btnNewButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addWindowListener(new WindowListener() {
			private boolean deactivated;
			@Override
			public void windowActivated(java.awt.event.WindowEvent e) {
				if (deactivated) {
					tb.setRowCount(1);
					fillTable(tb);
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
		
	}
	
	void fillTable(DefaultTableModel tb) {
		ArrayList<ArrayList<String>> stats = pc.getStatBlock(user);
		
		for(int i = 0; i < stats.get(0).size(); i++) {
			tb.addRow(new Object[] {
				stats.get(0).get(i)
			});
		}
		for (int i = 0; i < stats.size(); i++) {
			for (int j = 0;j < stats.get(i).size();j++)
				tb.setValueAt(stats.get(i).get(j), j+1, i);
		}
		
	}
}
