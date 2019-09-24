package com.fdm04.auditApp.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.fdm04.auditApp.database.DBconnectionManager;
import com.fdm04.auditApp.database.UsersDAO;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class Login {

	public UsersDAO dao;
	Connection  connection;
	private JFrame frame;
	private JTextField username;
	private JPasswordField password;
	
	

	public Login() { 
		
	}

	public Login(Connection connection) {
		this.connection = connection;
		dao = new UsersDAO(connection);
		initialize();
	}


	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 375, 300);
		frame.setSize(550, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.lightGray);
		frame.getContentPane().setLayout(null);
	
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String uname = username.getText();
				
				@SuppressWarnings("deprecation")
				String pwd = password.getText();
				
				boolean result = dao.verifyUser(uname, pwd);
				
				if (result) {
					CreateAudit createAudit = new CreateAudit(connection);
					createAudit.frame.setVisible(true);
				
				}else {
					JOptionPane.showMessageDialog(null, "Invalid details entered");					
				}	
			}
			
		});
		
		
		loginBtn.setBounds(168, 155, 117, 29);
		frame.getContentPane().add(loginBtn);
		
		JLabel unameLbl = new JLabel("Username");
		unameLbl.setBounds(37, 55, 91, 16);
		frame.getContentPane().add(unameLbl);
		
		JLabel pwdLbl = new JLabel("Password");
		pwdLbl.setBounds(37, 104, 61, 16);
		frame.getContentPane().add(pwdLbl);
		
		username = new JTextField();
		username.setBounds(155, 50, 138, 26);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(155, 99, 138, 26);
		frame.getContentPane().add(password);
		password.setColumns(10);
	}

	
	
	
	
	
	public static void main(String[] args) {
		
		final DBconnectionManager dbmg = new DBconnectionManager("localhost:3306", "AuditApplication", "root", "efil4zaggin");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final Connection connection = dbmg.getConnection();
					Login window = new Login(connection);
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}