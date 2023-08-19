package com.icin.bean;

public class AccountTransaction {
	Account account;
	Transaction transaction;
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	@Override
	public String toString() {
		return "AccountTransaction [account=" + account + ", transaction=" + transaction + "]";
	}
	

}
