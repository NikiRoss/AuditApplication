package com.fdm04.auditApp.database;

import java.sql.Connection;
import java.sql.SQLException;

import com.fdm04.auditApp.gui.Login;
import com.fdm04.auditApp.model.Audit;
import com.fdm04.auditApp.model.Users;

public class Main {
	

	public static void main(String[] args) {
		
		DBconnectionManager dbmg = new DBconnectionManager("localhost:3306", "AuditApplication", "root", "efil4zaggin");
		
		try {
			Connection connection = dbmg.getConnection();
			
			AuditDAO dao = new AuditDAO(connection);
			Audit audit = new Audit();
			Users users = new Users();
			
			audit = dao.findById(3);
			users.setAudit(audit);
			users.applyMajorNonCon(audit);
			
			dao.update(audit);
			
			System.out.println(audit);
			
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}

//Login login = new Login(connection);
//login.initialize();



