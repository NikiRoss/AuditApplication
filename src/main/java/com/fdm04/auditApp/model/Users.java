package com.fdm04.auditApp.model;

import com.fdm04.auditApp.database.util.DataTransferObject;

public class Users implements DataTransferObject{
	
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
		
	// Method used to apply a major non conformance to the audit score
	public double applyMajorNonCon(Audit audit) {
		
		double score = audit.getScore();
		double majorNonCon = 12;
		double newScore;
		if (score <= 15) {
			System.out.println("Stop all work on site");
			
		}		
		else {
			newScore = majorNonCon * score / 100;
			score = score - newScore;
			audit.setScore(score);			
		}		
		return score;		
	}
	
	// Method used to apply a minor non conformance to the audit score
	public double applyMinorNonCon(Audit audit) {
		
		double score = audit.getScore();
		double minorNonCon = 9;
		double newScore;
		
		if (score <= 15) {
			System.out.println("Stop all work on site");
			
		}		
		else {
			newScore = minorNonCon * score / 100;
			score = score - newScore;			
			audit.setScore(score);			
		}		
		return score;		
	}
	
	// Method used to apply a negative observation to the audit score
	public double applyNegativeObservation(Audit audit) {
		
		double score = audit.getScore();
		double negativeObservation = 7;
		double newScore;
		
		if (score <= 15) {
			System.out.println("Stop all work on site");
			
		}	
		else {
			newScore = negativeObservation * score / 100;
			score = score - newScore;			
			audit.setScore(score);			
		}		
		return score;		
	}
	
	// Method used to apply a positive observation to the audit score
	public double applyPositiveObservation(Audit audit) {
		
		double score = audit.getScore();
		double positiveObservation = 4;		
		if (score >= 96) {
			System.out.println("Apply positive observation at the end of the audit");
			
		}		
		else {
			score = positiveObservation + score;			
			audit.setScore(score);			
		}		
		return score;		
	}
	
	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", audit=" + audit + "]";
	}
	
}
