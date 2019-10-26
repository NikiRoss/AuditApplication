package com.fdm04.auditApp.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import com.fdm04.auditApp.database.AuditDAO;
import com.fdm04.auditApp.model.Audit;
import com.fdm04.auditApp.model.Users;
import com.fdm04.auditApp.util.AuditConstants;
import java.sql.Connection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AuditHome {

	protected JFrame frame;
	public AuditDAO dao;
	static Connection connection;
	public Audit audit;
	public Users users;
	public JLabel lblAuditScore;
	public JLabel lblAuditorName;
	
	public AuditHome() {
		
	}
	
	public AuditHome(Connection connection, Audit audit) {
		AuditHome.connection = connection;
		this.audit = audit;
		dao = new AuditDAO(connection);
		initialize();
	}
	
	// Method used to create interface
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 375, 300);
		frame.setSize(550, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.lightGray);
		frame.getContentPane().setLayout(null);
			
		/*
		 * This is the set up for the "access/ egress" button including
		 * the button name, positioning within the frame 
		 * and action performed when pressed
		 */		
		final JButton btnAccessEgress = new JButton(AuditConstants.ACCESS_EGRESS);
		btnAccessEgress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String btnName = btnAccessEgress.getText();
				CategoryHome catHome = new CategoryHome(connection, btnName, audit);
				catHome.frame.setVisible(true);
				frame.dispose();				
			}
		});
		btnAccessEgress.setBounds(169, 100, 117, 29);
		frame.getContentPane().add(btnAccessEgress);
			
		/*
		 * This is the set up for the "COSHH" button including
		 * the button name, positioning within the frame 
		 * and action performed when pressed
		 */
		final JButton btnCOSHH = new JButton(AuditConstants.COSHH);
		btnCOSHH.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				String btnName = btnCOSHH.getText();
				CategoryHome catHome = new CategoryHome(connection, btnName, audit);
				catHome.frame.setVisible(true);
				frame.dispose();
			}			
		});
		btnCOSHH.setBounds(169, 200, 117, 29);
		frame.getContentPane().add(btnCOSHH);
		
		/*
		 * This is the set up for the "First Aid" button including
		 * the button name, positioning within the frame 
		 * and action performed when pressed
		 */
		final JButton btnFirstAid = new JButton(AuditConstants.FIRST_AID);
		btnFirstAid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String btnName = btnFirstAid.getText();
				CategoryHome catHome = new CategoryHome(connection, btnName, audit);
				catHome.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnFirstAid.setBounds(169, 300, 117, 29);
		frame.getContentPane().add(btnFirstAid);
		
		/*
		 * This is the set up for the "RAMS" button including
		 * the button name, positioning within the frame 
		 * and action performed when pressed
		 */
		final JButton btnRAMS = new JButton(AuditConstants.RAMS);
		btnRAMS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String btnName = btnRAMS.getText();// replace with getText
				CategoryHome catHome = new CategoryHome(connection, btnName, audit);
				catHome.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnRAMS.setBounds(169, 400, 117, 29);
		frame.getContentPane().add(btnRAMS);
		
		/*
		 * This is the set up for the "Temp Works" button including
		 * the button name, positioning within the frame 
		 * and action performed when pressed
		 */
		final JButton btnWorkAtHeight = new JButton(AuditConstants.TEMP_WORKS);
		btnWorkAtHeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String btnName = btnWorkAtHeight.getText();
				CategoryHome catHome = new CategoryHome(connection, btnName, audit);
				catHome.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnWorkAtHeight.setBounds(169, 500, 117, 29);
		frame.getContentPane().add(btnWorkAtHeight);
		
		/*
		 * This is the set up for the "Temp Works" button including
		 * the button name, positioning within the frame 
		 * and action performed when pressed
		 */
		final JButton btnTempWorks = new JButton(AuditConstants.WORK_AT_HEIGHT);
		btnTempWorks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String btnName = btnTempWorks.getText();
				CategoryHome catHome = new CategoryHome(connection, btnName, audit);
				catHome.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnTempWorks.setBounds(169, 600, 117, 29);
		frame.getContentPane().add(btnTempWorks);
		
		// This label displays the current audit score in the top right hand corner of the frame
		lblAuditScore = new JLabel("");
		lblAuditScore.setBounds(315, 6, 129, 16);
		frame.getContentPane().add(lblAuditScore);
		double score = audit.getScore();
		lblAuditScore.setText("Audit score: " + String.valueOf(score) + "%");
		
		// This label displays the auditors name in the top left hand corner of the frame
		lblAuditorName = new JLabel("");
		lblAuditorName.setBounds(15, 6, 129, 16);
		frame.getContentPane().add(lblAuditorName);
		String auditor = audit.getAuditor();
		lblAuditorName.setText("Auditor: " + auditor);
		
		/* This button is used to allow users to quit the current session
		 * saving to DB in its current state upon quitting
		 *  */
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = JOptionPane.showOptionDialog(null, "Are you sure you want to quit?", "Confirm Logout", 
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);				
				if(value == JOptionPane.OK_OPTION) {
					dao.update(audit);
					frame.dispose();
				} 				
			}
		});
		btnQuit.setBounds(315, 693, 117, 29);
		frame.getContentPane().add(btnQuit);
		
		/*This button takes the user to the summary section
		 * to complete the audit
		 */		
		JButton btnSummary = new JButton("Summary");
		btnSummary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Summary summary = new Summary(connection, audit);
				summary.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnSummary.setBounds(28, 693, 117, 29);
		frame.getContentPane().add(btnSummary);
		
	}	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuditHome window = new AuditHome(connection, null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}