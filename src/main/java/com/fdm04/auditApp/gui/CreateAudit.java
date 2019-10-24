package com.fdm04.auditApp.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.fdm04.auditApp.database.AuditDAO;
import com.fdm04.auditApp.model.Audit;

public class CreateAudit {

	private static Connection connection;
	protected JFrame frame;
	private JTextField projectManagerTF;
	private JTextField projectNameTF;
	private JTextField auditorTF;
	public AuditDAO dao;
	private JButton retrieve;
	private JLabel lblRetrieveSavedAudit;
	private JTextField getAuditTF;
	public Audit audit;

	public CreateAudit() {
		
	}

	public CreateAudit(Connection connection) {
		CreateAudit.connection = connection;
		dao = new AuditDAO(connection);
		initialize();
	}

	// Method that creates the interface frame and all its components
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 375, 300);
		frame.setSize(550, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.lightGray);
		frame.getContentPane().setLayout(null);

		// Text field for entering project managers name
		projectManagerTF = new JTextField();
		projectManagerTF.setBounds(204, 70, 130, 26);
		frame.getContentPane().add(projectManagerTF);
		projectManagerTF.setColumns(10);

		// Text field for entering project name
		projectNameTF = new JTextField();
		projectNameTF.setBounds(204, 32, 130, 26);
		frame.getContentPane().add(projectNameTF);
		projectNameTF.setColumns(10);

		// Text field for entering project auditors name
		auditorTF = new JTextField();
		auditorTF.setBounds(204, 108, 130, 26);
		frame.getContentPane().add(auditorTF);
		auditorTF.setColumns(10);

		/*
		 * This button takes all details from the text fields and creates a new audit
		 * saving it to the DB
		 */		
		JButton createAuditBtn = new JButton("Create Audit");
		createAuditBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pName = projectNameTF.getText();     
				String pmName = projectManagerTF.getText();
				String auditor = auditorTF.getText();
				audit = new Audit();
				audit.setProjectName(pName);
				audit.setProjectManager(pmName);
				audit.setAuditor(auditor);
				dao.create(audit);			
				audit = dao.getID(audit);
				audit = dao.findById(audit.getId());				
				AuditHome auditHome = new AuditHome(connection, audit);
				auditHome.frame.setVisible(true);			
				frame.dispose();
			}
		});
		createAuditBtn.setBounds(214, 146, 117, 29);
		frame.getContentPane().add(createAuditBtn);

		// Descriptor for the "project name" text field
		JLabel projectNameLbl = new JLabel("Project Name:");
		projectNameLbl.setBounds(49, 37, 102, 16);
		frame.getContentPane().add(projectNameLbl);

		// Descriptor for the "project manager" text field
		JLabel projectManagerLbl = new JLabel("Project Manager:");
		projectManagerLbl.setBounds(49, 75, 143, 16);
		frame.getContentPane().add(projectManagerLbl);

		// Descriptor for the "auditor" text field
		JLabel auditorLbl = new JLabel("Auditor:");
		auditorLbl.setBounds(49, 113, 61, 16);
		frame.getContentPane().add(auditorLbl);
		
		/*
		 * This button calls the find audit by ID 
		 * method and returns the result
		 */		
		retrieve = new JButton("Retrieve");
		retrieve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				int auditID = Integer.parseInt(getAuditTF.getText());
				audit = new Audit();
				audit = dao.findById(auditID);				
				AuditHome auditHome = new AuditHome(connection, audit);
				auditHome.frame.setVisible(true);				
				String auditor = audit.getAuditor();
				auditHome.lblAuditorName.setText(auditor);
				auditHome.lblAuditScore.setText(String.valueOf(audit.getScore()));				
				frame.dispose();				
			}
		});		
		retrieve.setBounds(217, 509, 117, 29);
		frame.getContentPane().add(retrieve);
		
		// Descriptor for "retrieve" button
		lblRetrieveSavedAudit = new JLabel("Retrieve saved audit");
		lblRetrieveSavedAudit.setBounds(212, 401, 143, 16);
		frame.getContentPane().add(lblRetrieveSavedAudit);
		
		getAuditTF = new JTextField();
		getAuditTF.setBounds(214, 445, 130, 26);
		frame.getContentPane().add(getAuditTF);
		getAuditTF.setColumns(10);
		
		// Descriptor for the audit ID text field
		JLabel lblEnterAuditId = new JLabel("Enter audit id:");
		lblEnterAuditId.setBounds(70, 450, 102, 16);
		frame.getContentPane().add(lblEnterAuditId);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAudit window = new CreateAudit(connection);
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
