package com.fdm04.auditApp.model;

import java.util.ArrayList;

import com.fdm04.auditApp.database.util.DataTransferObject;
import com.fdm04.auditApp.util.AuditConstants;

public class Audit implements DataTransferObject{
	
	private int id;
	private double score = 100;
	private String projectName;
	private String projectManager;
	private String auditor;
	private String summary;
	private ArrayList<String> categories = new ArrayList<String>();

	public Audit() {
		
		categories.add(AuditConstants.ACCESS_EGRESS);
		categories.add(AuditConstants.COSHH);
		categories.add(AuditConstants.FIRST_AID);
		categories.add(AuditConstants.RAMS);
		categories.add(AuditConstants.TEMP_WORKS);
		categories.add(AuditConstants.WORK_AT_HEIGHT);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public ArrayList<String> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}
	

	@Override
	public String toString() {
		return "Audit [id=" + id + ", score=" + score + ", projectName=" + projectName + ", projectManager="
				+ projectManager + ", auditor=" + auditor + ", summary=" + summary + ", categories=" + categories + "]";
	}

	
}
