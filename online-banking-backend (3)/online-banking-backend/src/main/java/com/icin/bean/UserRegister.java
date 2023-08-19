package com.icin.bean;

import java.beans.JavaBean;


public class UserRegister {
	
	User user;
	Account account;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User u) {
		this.user = u;
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "UserRegister [u=" + user + ", account=" + account + "]";
	}
	
	

}
