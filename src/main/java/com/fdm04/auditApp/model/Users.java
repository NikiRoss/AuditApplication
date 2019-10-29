package com.fdm04.auditApp.model;

import com.fdm04.auditApp.database.util.DataTransferObject;

public class Users implements DataTransferObject, Factors{
	
	private int id;
	private String username;
	private String password;
	private Audit audit;
		
	 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Audit getAudit() {
		return audit;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}
	
    //implemented method from Factors interface for applying Major Non Conformance to Audit
	@Override
	public Audit applyMajorNonCon(Audit audit) {
		
		double auditScore = audit.getScore();
        double factor = 12.0;
        double newAuditScore;
        if (auditScore <= 15) {
			System.out.println("Stop the audit and shut down the site");
			
        }else {
        	newAuditScore = factor * auditScore /100;
            audit.setScore(auditScore - newAuditScore);
        } 
        return audit;
	}
	
    //implemented method from Factors interface for applying Minor Non Conformance to Audit
	@Override
	public Audit applyMinorNonCon(Audit audit) {
		
		double auditScore = audit.getScore();
        double factor = 9.0;
        double newAuditScore;
        if (auditScore <= 15) {
			System.out.println("Stop the audit and shut down the site");
			
        }else {
        	newAuditScore = factor * auditScore /100;
            audit.setScore(auditScore - newAuditScore);
        } 
        return audit;
	}
	
    //implemented method from Factors interface for applying Negative Observation to Audit
	@Override
	public Audit applyNegativeObservation(Audit audit) {
		
		double auditScore = audit.getScore();
        double factor = 7.0;
        double newAuditScore;
        if (auditScore <= 15) {
			System.out.println("Stop the audit and shut down the site");
			
        }else {
        	newAuditScore = factor * auditScore /100;
            audit.setScore(auditScore - newAuditScore);
        } 
        return audit;
	}
	
    //implemented method from Factors interface for applying Positive Observation to Audit
	@Override
	public Audit applyPositiveObservation(Audit audit) {
		
		double auditScore = audit.getScore();
		if (auditScore >= 96) {
			System.out.println("Apply positive observation at end of audit");
			
		}else {
			audit.setScore(auditScore + 4);
		}     
        return audit;
	}
		
	
	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", audit=" + audit + "]";
	}

	
}
