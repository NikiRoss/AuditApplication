package com.fdm04.auditApp.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fdm04.auditApp.model.Audit;
import com.fdm04.auditApp.model.Users;

public class UsersTest {

	@Test
	public void getUsername(){
		
        Users user = new Users();
        user.setUsername("Test_Username_1");
        String result = user.getUsername();
        assertEquals("Test_Username_1", result);
    }
	
	@Test
	public void getPassword(){
		
        Users user = new Users();
        user.setPassword("Te3t_pAsSw05D");
        String result = user.getPassword();
        assertEquals("Te3t_pAsSw05D", result);
    }
	
	@Test
	public void apply_Major_Non_Conformance_To_Audit(){
		Audit a = new Audit();
        Users user = new Users();
        user.setAudit(a);
        double result = user.applyMajorNonCon(a);
        assertEquals(88.0, result, 0);
    }
	
	@Test
	public void apply_Minor_Non_Conformance_To_Audit(){
		Audit a = new Audit();
        Users user = new Users();
        user.setAudit(a);
        double result = user.applyMinorNonCon(a);
        assertEquals(91.0, result, 0);
    }
	
	@Test
	public void apply_Negative_Observation_To_Audit(){
		Audit a = new Audit();
        Users user = new Users();
        user.setAudit(a);
        double result = user.applyNegativeObservation(a);
        assertEquals(93.0, result, 0);
    }
	
	@Test
	public void apply_Positive_Observation_To_Audit(){
		Audit a = new Audit();
		a.setScore(50.0);
        Users user = new Users();
        user.setAudit(a);
        double result = user.applyPositiveObservation(a);
        assertEquals(54.0, result, 0);
    }
	
}
