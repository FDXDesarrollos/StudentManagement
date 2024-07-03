package com.fdxdesarrollos.studentManagement.model;

public class Dashboard {
	private String totalFemale = "";
	private String totalMale = "";
	private String totalEnrolled = "";
	private String dateReg = "";
	
	
	public Dashboard() {
		super();
	}	
	
	public Dashboard(String totalFemale, String totalMale, String totalEnrolled) {
		super();
		this.totalFemale = totalFemale;
		this.totalMale = totalMale;
		this.totalEnrolled = totalEnrolled;
	}

	public String getTotalFemale() {
		return totalFemale;
	}

	public void setTotalFemale(String totalFemale) {
		this.totalFemale = totalFemale;
	}

	public String getTotalMale() {
		return totalMale;
	}

	public void setTotalMale(String totalMale) {
		this.totalMale = totalMale;
	}

	public String getTotalEnrolled() {
		return totalEnrolled;
	}

	public void setTotalEnrolled(String totalEnrolled) {
		this.totalEnrolled = totalEnrolled;
	}

	public String getDateReg() {
		return dateReg;
	}

	public void setDateReg(String dateReg) {
		this.dateReg = dateReg;
	}

	@Override
	public String toString() {
		return "Dashboard [totalFemale=" + totalFemale + ", totalMale=" + totalMale + ", totalEnrolled=" + totalEnrolled
				+ ", dateReg=" + dateReg + "]";
	}
	
}
