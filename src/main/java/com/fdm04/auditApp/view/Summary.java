package com.fdm04.auditApp.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.fdm04.auditApp.database.AuditDAO;
import com.fdm04.auditApp.model.Audit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Summary {

	private static Connection connection;
	public JFrame frame;
	public Audit audit;
	public AuditDAO dao;
	public JLabel lblAuditScore;
	public JLabel lblAuditorName;
	
	public Summary() { }
	
	public Summary(Connection connection, Audit audit) {
		Summary.connection = connection;
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
		
		final JTextArea summary = new JTextArea();
		summary.setBounds(74, 91, 300, 132);
		frame.getContentPane().add(summary);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = JOptionPane.showOptionDialog(null, "Are you sure you want to submit audit?", "Confirm Submission", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				
				if(value == JOptionPane.OK_OPTION) {
					String smry = summary.getText();
					audit.setSummary(smry);
					dao.update(audit);
					frame.dispose();
				}
			}
		});
		btnSubmit.setBounds(327, 235, 117, 29);
		frame.getContentPane().add(btnSubmit);
		
		JLabel lblSummary = new JLabel("Summary");
		lblSummary.setBounds(186, 63, 61, 16);
		frame.getContentPane().add(lblSummary);
		
		// This label displays the score in the top right hand corner of the frame
		lblAuditScore = new JLabel("");
		lblAuditScore.setBounds(315, 6, 129, 16);
		frame.getContentPane().add(lblAuditScore);
		double auditScore = audit.getScore();
		lblAuditScore.setText("Audit score: " + String.valueOf(auditScore) + "%");
		
		// This label displays the auditors name in the top left hand corner of the frame
		lblAuditorName = new JLabel("");
		lblAuditorName.setBounds(15, 6, 129, 16);
		frame.getContentPane().add(lblAuditorName);
		String auditorName = audit.getAuditor();
		lblAuditorName.setText("Auditor: " + auditorName);
	}
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Summary window = new Summary(connection, null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
