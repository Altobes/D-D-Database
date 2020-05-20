package dungeondatabase.services;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class Frag_Create_NPC {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnNewButton;
	private JLabel lblNewLabel_1;
	private DatabaseConnectionService dbService = new DatabaseConnectionService(Dataclass.SNAME,Dataclass.DBNAME);
	private NPC npc = new NPC(dbService);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frag_Create_NPC window = new Frag_Create_NPC();
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
	public Frag_Create_NPC() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dbService.connect(Dataclass.USER, Dataclass.PASS);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NPC Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(25, 40, 100, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNpcCr = new JLabel("NPC CR");
		lblNpcCr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNpcCr.setBounds(25, 85, 100, 27);
		frame.getContentPane().add(lblNpcCr);
		
		JLabel lblStatId = new JLabel("Stat ID");
		lblStatId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStatId.setBounds(25, 130, 100, 27);
		frame.getContentPane().add(lblStatId);
		
		JLabel lblCampagin = new JLabel("Campaign ID");
		lblCampagin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCampagin.setBounds(25, 175, 100, 27);
		frame.getContentPane().add(lblCampagin);
		
		textField = new JTextField();
		textField.setBounds(140, 40, 229, 27);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(140, 85, 229, 27);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(140, 130, 229, 27);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(140, 175, 229, 27);
		frame.getContentPane().add(textField_3);
		
		btnNewButton = new JButton("Create");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(250, 220, 119, 27);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String npc_name = textField.getText();
				int npc_cr = Integer.parseInt(textField_1.getText());
				int statID = Integer.parseInt(textField_2.getText());
				int campagignID = Integer.parseInt(textField_3.getText());
				System.out.println(npc_name);
				System.out.println(npc_cr);
				System.out.println(statID);
				System.out.println(campagignID);
				npc.addNPC(npc_name, npc_cr, statID, campagignID);
				frame.setVisible(false);
			}	
		});
		frame.getContentPane().add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("Create New NPC");
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(150, 5, 168, 20);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
