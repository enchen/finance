package cn.beans;

import java.util.Date;

public class Project {
	private String projectName;
	private Date planEnd;
	private String state;
	private int incomeCount;
	private String describle;
	private String projectId;
	private double incomeFee;
	private String projectType;
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getPlanEnd() {
		return planEnd;
	}
	public void setPlanEnd(Date planEnd) {
		this.planEnd = planEnd;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getIncomeCount() {
		return incomeCount;
	}
	public void setIncomeCount(int incomeCount) {
		this.incomeCount = incomeCount;
	}
	public String getDescrible() {
		return describle;
	}
	public void setDescrible(String describle) {
		this.describle = describle;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public double getIncomeFee() {
		return incomeFee;
	}
	public void setIncomeFee(double incomeFee) {
		this.incomeFee = incomeFee;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

}
