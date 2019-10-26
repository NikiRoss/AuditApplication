package com.fdm04.auditApp.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.fdm04.auditApp.database.AuditDAO;
import com.fdm04.auditApp.model.Audit;
import com.fdm04.auditApp.model.Users;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class CategoryHome {

	static Connection connection;
	public JFrame frame;
	public AuditDAO dao;
	public Users users;
	public Audit audit;
	private static String btnName;
	private JTextField comment;
	public JLabel lblAuditorName;
	public JLabel lblAuditScore;


	
	public CategoryHome() {
		initialize();
		
	}
	
	
	public CategoryHome(Connection connection) { 
		CategoryHome.connection = connection; 
		
	}
	 

	public CategoryHome(Connection connection, String btnName, Audit audit) {
		CategoryHome.connection = connection; 
		this.audit = audit;
		CategoryHome.btnName = btnName; 
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
		
		comment = new JTextField();
		comment.setBounds(50, 400, 350, 250);
		frame.getContentPane().add(comment);
		comment.setColumns(10);
		
		
		JButton btnMajNonCon = new JButton("Maj Non Con");
		btnMajNonCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				users = new Users();
				users.setAudit(audit);
				users.applyMajorNonCon(audit);
				dao.update(audit);
				
			}
		});
		btnMajNonCon.setBounds(169, 75, 117, 29);
		btnMajNonCon.setBackground(Color.RED);
		frame.getContentPane().add(btnMajNonCon);
		
		
		
		JButton btnMinNonCon = new JButton("Min Non Con");
		btnMinNonCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				users = new Users();
				users.setAudit(audit);
				users.applyMinorNonCon(audit);
				dao.update(audit);
				
			}
		});
		btnMinNonCon.setBounds(169, 125, 117, 29);
		frame.getContentPane().add(btnMinNonCon);
		
		
		
		JButton btnNegObs = new JButton("Neg Obs");
		btnNegObs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				users = new Users();
				users.setAudit(audit);
				users.applyNegativeObservation(audit);
				dao.update(audit);
				
				
			}
		});
		btnNegObs.setBounds(169, 175, 117, 29);
		frame.getContentPane().add(btnNegObs);
		
		
		
		JButton btnPosObs = new JButton("Pos Obs");
		btnPosObs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				users = new Users();
				users.setAudit(audit);
				users.applyPositiveObservation(audit);
				dao.update(audit);
				
				
			}
		});
		btnPosObs.setBounds(169, 225, 117, 29);
		frame.getContentPane().add(btnPosObs);
		
		JLabel categoryHead = new JLabel(btnName);
		categoryHead.setBounds(187, 25, 117, 29);
		frame.getContentPane().add(categoryHead);
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AuditHome auditHome = new AuditHome(connection, audit);
				auditHome.frame.setVisible(true);
			}
		});
		
		btnBack.setBounds(6, 682, 117, 29);
		frame.getContentPane().add(btnBack);
		
		JLabel lblAuditorName = new JLabel(audit.getAuditor());
		lblAuditorName.setBounds(39, 31, 61, 16);
		frame.getContentPane().add(lblAuditorName);
		
		double score = audit.getScore();
		JLabel lblAuditScore = new JLabel("Audit score: " + String.valueOf(score) + "%");
		lblAuditScore.setBounds(375, 31, 117, 16);
		frame.getContentPane().add(lblAuditScore);
		
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryHome window = new CategoryHome(connection, btnName, null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
