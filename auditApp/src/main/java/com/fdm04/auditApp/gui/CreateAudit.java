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
	private JButton btnNewButton;
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

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 375, 300);
		frame.setSize(550, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.lightGray);
		frame.getContentPane().setLayout(null);

		projectManagerTF = new JTextField();
		projectManagerTF.setBounds(204, 32, 130, 26);
		frame.getContentPane().add(projectManagerTF);
		projectManagerTF.setColumns(10);

		projectNameTF = new JTextField();
		projectNameTF.setBounds(204, 70, 130, 26);
		frame.getContentPane().add(projectNameTF);
		projectNameTF.setColumns(10);

		auditorTF = new JTextField();
		auditorTF.setBounds(204, 108, 130, 26);
		frame.getContentPane().add(auditorTF);
		auditorTF.setColumns(10);

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

				AuditHome auditHome = new AuditHome(connection, audit);
				auditHome.frame.setVisible(true);

			}
		});

		createAuditBtn.setBounds(214, 146, 117, 29);
		frame.getContentPane().add(createAuditBtn);

		JLabel projectNameLbl = new JLabel("Project Name:");
		projectNameLbl.setBounds(49, 37, 102, 16);
		frame.getContentPane().add(projectNameLbl);

		JLabel projectManagerLbl = new JLabel("Project Manager:");
		projectManagerLbl.setBounds(49, 75, 143, 16);
		frame.getContentPane().add(projectManagerLbl);

		JLabel auditorLbl = new JLabel("Auditor:");
		auditorLbl.setBounds(49, 113, 61, 16);
		frame.getContentPane().add(auditorLbl);
		
		btnNewButton = new JButton("Retrieve");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int auditID = Integer.parseInt(getAuditTF.getText());
				audit = new Audit();
				audit = dao.findById(auditID);
				
				AuditHome auditHome = new AuditHome(connection, audit);
				auditHome.frame.setVisible(true);
				
				String auditor = audit.getAuditor();
				auditHome.lblAuditorName.setText(auditor);
				
				double score = audit.getScore();
				auditHome.lblAuditScore.setText(String.valueOf(score));
				
			}
		});
		
		
		btnNewButton.setBounds(217, 509, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		lblRetrieveSavedAudit = new JLabel("Retrieve saved audit");
		lblRetrieveSavedAudit.setBounds(212, 401, 143, 16);
		frame.getContentPane().add(lblRetrieveSavedAudit);
		
		getAuditTF = new JTextField();
		getAuditTF.setBounds(214, 445, 130, 26);
		frame.getContentPane().add(getAuditTF);
		getAuditTF.setColumns(10);
		
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
