package com.fdm04.auditApp.test;

import static org.junit.Assert.*;

import org.junit.Test;
import com.fdm04.auditApp.model.Audit;
import com.fdm04.auditApp.model.Users;

public class AuditTest {

	@Test
	public void getAuditScore() {
		
		Audit audit = new Audit();
		double result = audit.getScore();
		assertEquals(100, result, 1);
	}
	
	@Test
	public void getProjectName() {
		
		Audit audit = new Audit();
		audit.setProjectName("Project Test Name");
		String result = audit.getProjectName();
		assertEquals("Project Test Name", result);
	}
	
	@Test
	public void getProjectManager() {
		
		Audit audit = new Audit();
		audit.setProjectManager("Test PM Name");
		String result = audit.getProjectManager();
		assertEquals("Test PM Name", result);
	}
	
	@Test
	public void getAuditor() {
		
		Audit audit = new Audit();
		audit.setAuditor("Test Auditor");
		String result = audit.getAuditor();
		assertEquals("Test Auditor", result);
	}
	
	@Test
	public void getSummary() {
		
		Audit audit = new Audit();
		audit.setSummary("This is a summary written for test purposes");
		String result = audit.getSummary();
		assertEquals("This is a summary written for test purposes", result);
	}
	
	@Test
    public void get_First_Category(){
		
        Audit audit = new Audit();
        String result = audit.getCategories().get(0);
        assertEquals(result, "Access/ Egress");
    }
	
	@Test
    public void get_Second_Category(){
		
		Audit audit = new Audit();
        String result = audit.getCategories().get(1);
        assertEquals(result, "COSHH");
    }
	
	@Test
    public void get_Third_Category() {
        
        Audit audit = new Audit();
        String result = audit.getCategories().get(2);
        assertEquals(result, "First Aid");
    }
	
	@Test
    public void get_Fourth_Category(){
		
        Audit audit = new Audit();
        String result = audit.getCategories().get(3);
        assertEquals(result, "RAMS");
    }
	
	@Test
	public void check_Audit_Score_Doesnt_Exceed_100(){
		Audit audit = new Audit();
        Users user = new Users();
        user.setAudit(audit);
        double result = user.applyPositiveObservation(audit);
        assertEquals(100, result, 0);
    }
	
	@Test
	public void check_Audit_Score_Doesnt_Drop_Below_12(){
		Audit audit = new Audit();
        Users user = new Users();
        user.setAudit(audit);
        audit.setScore(12.0);    
        double result = user.applyMajorNonCon(audit);
        assertEquals(12.0, result, 0);
    }

}
