package com.icin.bean;

public class AccountLoan {
	
	Loan loan;
	int accountno;
	@Override
	public String toString() {
		return "AccountLoan [loan=" + loan + ", accountno=" + accountno + "]";
	}
	public Loan getLoan() {
		return loan;
	}
	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	public int getAccountno() {
		return accountno;
	}
	public void setAccountno(int accountno) {
		this.accountno = accountno;
	}
	
	

}
