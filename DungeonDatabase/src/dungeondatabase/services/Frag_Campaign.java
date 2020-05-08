package dungeondatabase.services;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Frag_Campaign {

	JFrame frame;
	private JTable table;
	private DatabaseConnectionService dbService = new DatabaseConnectionService("golem.csse.rose-hulman.edu",
			"DungeonDatabase");
	private Campaign CampaignService = new Campaign(dbService);

	private ArrayList<String> CampaignIDs, CampaignNames, PartyIDs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frag_Campaign window = new Frag_Campaign();
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
	public Frag_Campaign() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dbService.connect("Dungeon19", "Password123");
		
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		JLabel lblNewLabel = new JLabel("Dungeon Database");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(100, 0, 250, 42);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Campaign");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 40, 184, 25);
		frame.getContentPane().add(lblNewLabel_1);

		JButton create_Campaign = new JButton("Create New Campaign");
		create_Campaign.setFont(new Font("Tahoma", Font.PLAIN, 16));
		create_Campaign.setBounds(225, 39, 175, 25);
		create_Campaign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // Open create character menu
				Frag_Create_Campaign window = new Frag_Create_Campaign();
				window.frame.setVisible(true);
			}
		});
		frame.getContentPane().add(create_Campaign);

		setData();

		frame.addWindowListener(new WindowListener() {
			private boolean deactivated;

			@Override
			public void windowActivated(WindowEvent e) {
//				if (deactivated) {
//					Frag_Campaign f = new Frag_Campaign();
//					f.frame.setVisible(true);
//					frame.dispose();
//				}
//				deactivated = false;
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				deactivated = true;
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowOpened(WindowEvent e) {
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.setBounds(10, 75, 416, 178);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		frame.getContentPane().add(table);

//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setData() {
		CampaignIDs = this.CampaignService.getCampaignID();
		CampaignNames = CampaignService.getCampaignName();
		PartyIDs = CampaignService.getPartyID();
		table = new JTable();
		DefaultTableModel tb = new DefaultTableModel(
				new Object[][] { { "CampaignIDs", "CampaignNames", "PartyIDs"}, },
				new String[] { "New column", "New column", "New column"});
		for (int i = 0; i < CampaignIDs.size(); i++) {
			tb.addRow(new Object[] { CampaignIDs.get(i), CampaignNames.get(i), PartyIDs.get(i) });
		}

		table.setModel(tb);
	}

}
