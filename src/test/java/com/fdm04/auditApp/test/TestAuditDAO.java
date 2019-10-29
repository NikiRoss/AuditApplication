package com.fdm04.auditApp.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.fdm04.auditApp.dao.AuditDAO;
import com.fdm04.auditApp.database.util.DBconnectionManager;
import com.fdm04.auditApp.model.Audit;

public class TestAuditDAO {
	
    DBconnectionManager dbcmg = new DBconnectionManager("localhost:3306", "AuditApplication", "root", "efil4zaggin");

    
		@Test  
		public void create_New_Audit() {
			
			try {
	            Connection connection = dbcmg.getConnection();
	            AuditDAO dao = new AuditDAO(connection);
	            Audit audit = new Audit();
	            audit.setProjectName("Test_ Name");
	            audit.setProjectManager("TestManager");
	            audit.setAuditor("Test_Auditor");
	            dao.create(audit);
	            audit = dao.getID(audit);
	            String result = audit.getProjectName();
	            assertEquals(result, "Test_ Name");

	        }catch (SQLException e){
	            e.printStackTrace();
	            throw new RuntimeException();
	        }
		}
		
		@Test  
		public void update_Audit() {
		
		try {
            Connection connection = dbcmg.getConnection();
            AuditDAO auditDAO = new AuditDAO(connection);
            Audit audit = new Audit();
            audit = auditDAO.findById(1);
            audit.setProjectName("NEW_PROJECT_NAME");
            auditDAO.update(audit);
            String result = audit.getProjectName();
            assertEquals(result, "NEW_PROJECT_NAME");

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
	}
		
		@Test
		public void find_Audit() {
			
			try {
	            Connection connection = dbcmg.getConnection();
	            AuditDAO auditDAO = new AuditDAO(connection);
	            Audit audit = new Audit();
	            audit = auditDAO.findById(6);
	            String result = audit.getProjectName();
	            assertEquals("Nine Elms", result);
	            
			}catch (SQLException e){
	            e.printStackTrace();
	            throw new RuntimeException();
	        }
		}
		
		@Test
		public void delete_Audit(){
			
			try {
	            Connection connection = dbcmg.getConnection();
	            AuditDAO auditDAO = new AuditDAO(connection);
	            Audit audit = new Audit();
	            auditDAO.delete(4);
	            audit = auditDAO.findById(4);
	            String result = audit.getProjectName();
	            assertEquals(null, result);
	            
			} catch (SQLException e){
	            e.printStackTrace();
	            throw new RuntimeException();
	        }
		}
}
