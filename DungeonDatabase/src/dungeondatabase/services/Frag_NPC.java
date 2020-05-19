package dungeondatabase.services;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;


public class Frag_NPC {

	JFrame frame;
	private JTable table;
	private DatabaseConnectionService dbService = new DatabaseConnectionService(Dataclass.SNAME,Dataclass.DBNAME);
	private NPC npc = new NPC(dbService);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frag_NPC window = new Frag_NPC();
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
	public Frag_NPC() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dbService.connect(Dataclass.USER, Dataclass.PASS);
		ArrayList<String> npc_name = npc.getNPC();
		ArrayList<String> npc_cr = npc.getCR();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dungeon Database");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(100, 0, 250, 42);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NPC");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 40, 184, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		table = new JTable();
		DefaultTableModel tb = new DefaultTableModel(
				new Object[][] {
				}, new String[] {
					"New column", "New column"
		});
		tb.addRow(new Object[]{"Name", "CR"});
		
		for(int i = 0; i<npc_name.size(); i++) {
			tb.addRow(new Object[] {
				npc_name.get(i), npc_cr.get(i)
			});
		}
		table.setModel(tb);
		table.setBounds(10, 75, 416, 178);
		frame.getContentPane().add(table);
		
		JButton btnNewButton = new JButton("Create New NPC");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(95, 44, 193, 21);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frag_Create_NPC window = new Frag_Create_NPC();
				window.frame.setVisible(true);

			}	
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(330, 44, 85, 21);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> new_name = npc.getNPC();
				ArrayList<String> new_cr = npc.getCR();
				table.setVisible(false);
				updateTable(new_name, new_cr);
			}	
		});
		frame.getContentPane().add(btnNewButton_1);
	}
	
	private void updateTable(ArrayList<String> npc_name, ArrayList<String> npc_cr) {
		table = new JTable();
		DefaultTableModel tb = new DefaultTableModel(
				new Object[][] {
				}, new String[] {
					"New column", "New column"
		});
		tb.addRow(new Object[]{"Name", "CR"});
		
		for(int i = 0; i<npc_name.size(); i++) {
			tb.addRow(new Object[] {
				npc_name.get(i), npc_cr.get(i)
			});
		}
		table.setModel(tb);
		table.setBounds(10, 75, 416, 178);
		frame.getContentPane().add(table);
	}
}
