package com.icin.bean;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {

	@Id
	Integer transactionid ;
	@ManyToOne
	@JoinColumn(name = "accountno")
	Account accountno;
	LocalDateTime timestamp;
	int recepientno;
	String recipient_name;
	float amount;
	public Integer getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(Integer transactionid) {
		this.transactionid = transactionid;
	}
	public Account getAccountno() {
		return accountno;
	}
	public void setAccountno(Account accountno) {
		this.accountno = accountno;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public int getRecepientno() {
		return recepientno;
	}
	public void setRecepientno(int recepientno) {
		this.recepientno = recepientno;
	}
	public String getRecipient_name() {
		return recipient_name;
	}
	public void setRecipient_name(String recipient_name) {
		this.recipient_name = recipient_name;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Transaction [transactionid=" + transactionid + ", accountno=" + accountno + ", timestamp=" + timestamp
				+ ", recepientno=" + recepientno + ", recipient_name=" + recipient_name + ", amount=" + amount + "]";
	}
	
	
}