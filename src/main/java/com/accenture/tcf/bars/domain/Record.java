package com.accenture.tcf.bars.domain;

import java.sql.Date;

public class Record {
	
	
	private int billingCycle;
	private Date startDate;
	private Date endDate;
	private String accountName;
	private String customerLastName;
	private String customerFirstName;
	private double amount;
	
	
	public Record() {
		
	}
	
	public Record(int billingCycle, Date startDate, Date endDate,  String customerLastName,
			String customerFirstName, double amount) {
		super();
		this.billingCycle = billingCycle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.customerLastName = customerLastName;
		this.customerFirstName = customerFirstName;
		this.amount = amount;
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
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getCustomerLastName() {
		return customerLastName;
	}
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}
	public String getCustomerFirstName() {
		return customerFirstName;
	}
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
