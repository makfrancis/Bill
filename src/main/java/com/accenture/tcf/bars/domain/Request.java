package com.accenture.tcf.bars.domain;

import java.sql.Date;



public class Request {
	
	
	private int billingCycle;
	
	private Date startDate;
	
	private Date endDate;
	
	
	
	
	public Request(int billingCycle, Date startDate, Date endDate) {
		this.billingCycle = billingCycle;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Request() {
	}
	public int getBillingCycle() {
		return billingCycle;
	}
	public void setBillingCycle(int billingCycle) {
		this.billingCycle = billingCycle;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}
