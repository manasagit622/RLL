package com.icin.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

@Entity
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long loanid;
	
	@ManyToOne
	@JoinColumn(name = "accountno")
	Account accountno;
	String type;
	String amount;
	int tenure;
	int interest;
	public Loan(Integer loanid, Account accountno, String type, String amount, int tenure, int interest) {
		super();
		this.loanid = loanid;
		this.accountno = accountno;
		this.type = type;
		this.amount = amount;
		this.tenure = tenure;
		this.interest = interest;
	}
	public Loan() {
		super();
	}
	public long getLoanid() {
		return loanid;
	}
	public void setLoanid(long loanid) {
		this.loanid = loanid;
	}
	public Account getAccountno() {
		return accountno;
	}
	public void setAccountno(Account accountno) {
		this.accountno = accountno;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public int getInterest() {
		return interest;
	}
	public void setInterest(int interest) {
		this.interest = interest;
	}
	
	
}
