package com.fdm04.auditApp.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollBar;

import com.fdm04.auditApp.database.AuditDAO;
import com.fdm04.auditApp.model.Audit;
import com.fdm04.auditApp.model.Users;

import java.awt.event.AdjustmentListener;
import java.sql.Connection;
import java.awt.event.AdjustmentEvent;
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

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 375, 300);
		frame.setSize(550, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.lightGray);
		frame.getContentPane().setLayout(null);
		
		
		final JButton btnAccessEgress = new JButton("Access/ Egress");
		btnAccessEgress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("deprecation")
				String btnName = btnAccessEgress.getLabel();
				CategoryHome catHome = new CategoryHome(connection, btnName, audit);
				catHome.frame.setVisible(true);
				
			}
		});
		btnAccessEgress.setBounds(169, 100, 117, 29);
		frame.getContentPane().add(btnAccessEgress);
		
		
		
		final JButton btnCOSHH = new JButton("COSHH");
		btnCOSHH.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				@SuppressWarnings("deprecation")
				String btnName = btnCOSHH.getLabel();
				CategoryHome catHome = new CategoryHome(connection, btnName, audit);
				catHome.frame.setVisible(true);
				
				/*
				 * String auditor = audit.getAuditor(); catHome.lblAuditorName.setText(auditor);
				 * double score = audit.getScore();
				 * catHome.lblAuditScore.setText(String.valueOf(score));
				 */
					
			}
			
		});
		btnCOSHH.setBounds(169, 200, 117, 29);
		frame.getContentPane().add(btnCOSHH);
		
		
		final JButton btnFirstAid = new JButton("First Aid");
		btnFirstAid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				@SuppressWarnings("deprecation")
				String btnName = btnFirstAid.getLabel();
				CategoryHome catHome = new CategoryHome(connection, btnName, audit);
				catHome.frame.setVisible(true);
				
				/*
				 * String auditor = audit.getAuditor(); catHome.lblAuditorName.setText(auditor);
				 * double score = audit.getScore();
				 * catHome.lblAuditScore.setText(String.valueOf(score));
				 */
			}
		});
		btnFirstAid.setBounds(169, 300, 117, 29);
		frame.getContentPane().add(btnFirstAid);
		
		
		final JButton btnRAMS = new JButton("RAMS");
		btnRAMS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("deprecation")
				String btnName = btnRAMS.getLabel();
				CategoryHome catHome = new CategoryHome(connection, btnName, audit);
				catHome.frame.setVisible(true);
				
				/*
				 * String auditor = audit.getAuditor(); catHome.lblAuditorName.setText(auditor);
				 * double score = audit.getScore();
				 * catHome.lblAuditScore.setText(String.valueOf(score));
				 */
			}
		});
		btnRAMS.setBounds(169, 400, 117, 29);
		frame.getContentPane().add(btnRAMS);
		
		
		final JButton btnWorkAtHeight = new JButton("Temp Works");
		btnWorkAtHeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("deprecation")
				String btnName = btnWorkAtHeight.getLabel();
				CategoryHome catHome = new CategoryHome(connection, btnName, audit);
				catHome.frame.setVisible(true);
				
				/*
				 * String auditor = audit.getAuditor(); catHome.lblAuditorName.setText(auditor);
				 * double score = audit.getScore();
				 * catHome.lblAuditScore.setText(String.valueOf(score));
				 */
			}
		});
		btnWorkAtHeight.setBounds(169, 500, 117, 29);
		frame.getContentPane().add(btnWorkAtHeight);
		
		
		final JButton btnTempWorks = new JButton("Work At Heigh");
		btnTempWorks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("deprecation")
				String btnName = btnTempWorks.getLabel();
				CategoryHome catHome = new CategoryHome(connection, btnName, audit);
				catHome.frame.setVisible(true);
				
				/*
				 * String auditor = audit.getAuditor(); catHome.lblAuditorName.setText(auditor);
				 * double score = audit.getScore();
				 * catHome.lblAuditScore.setText(String.valueOf(score));
				 */
			}
		});
		btnTempWorks.setBounds(169, 600, 117, 29);
		frame.getContentPane().add(btnTempWorks);
		
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
			}
		});
		scrollBar.setBounds(429, 84, 15, 96);
		frame.getContentPane().add(scrollBar);
		
		lblAuditScore = new JLabel("");
		lblAuditScore.setBounds(315, 6, 129, 16);
		frame.getContentPane().add(lblAuditScore);
		
	 
		double score = audit.getScore();
		 
		lblAuditScore.setText("Audit score: " + String.valueOf(score) + "%");
		
		lblAuditorName = new JLabel("");
		lblAuditorName.setBounds(15, 6, 129, 16);
		frame.getContentPane().add(lblAuditorName);

		String auditor = audit.getAuditor();
		lblAuditorName.setText("Auditor: " + auditor);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Are you sure you want to quit?");
				
				
			}
		});
		btnQuit.setBounds(315, 693, 117, 29);
		frame.getContentPane().add(btnQuit);
		
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
