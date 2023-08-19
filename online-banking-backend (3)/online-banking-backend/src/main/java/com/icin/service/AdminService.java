package com.icin.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icin.bean.Account;
import com.icin.bean.Admin;
import com.icin.bean.Loan;
import com.icin.bean.Transaction;
import com.icin.bean.User;
import com.icin.repository.AccountRepository;
import com.icin.repository.AdminRepository;
import com.icin.repository.LoanRepository;
import com.icin.repository.TransactionRepository;
import com.icin.repository.UserRepository;

@Service
public class AdminService {
	@Autowired
	TransactionRepository tr;
	
	@Autowired
	AdminRepository ar;
	
	@Autowired
	AccountRepository acr;
	
	@Autowired
	LoanRepository lr;
	
	@Autowired
	UserRepository ur;
	
	public List<Transaction> getTransactionByMonth(LocalDateTime timestamp) {
		
		List<Transaction> result= new ArrayList<Transaction>();
		for (Transaction transaction : tr.findAll()) {
			if(transaction.getTimestamp().getMonth().equals(timestamp))
			{
			result.add(transaction);
			}
			
		}
		System.out.println(result);
		return result;
		
		
	}
public String alog(Admin ad) {
	Optional<Admin> res = ar.findById(ad.getEmail());
	if(res.isPresent()) {
		Admin a = res.get();
		if(a.getPassword().equals(ad.getPassword())) {
			return "successful";
		}else {
			return "Invalid password";
		}
	}
	else {
		return "Invalid emailid";
	}
}
public String adloan(Loan lo) {
	Optional<Loan> res = lr.findById(lo.getLoanid());
	if(res.isPresent()) {
		return "Already added";
	}else {
		lr.save(lo);
		return "Loan added successfully";
	}
}
public String chqstatus(long ac) {
	Optional<Account> res = acr.findById(ac);
	if(res.isPresent()) {
		Account a = res.get();
		a.setCheque("approved");
		acr.saveAndFlush(a);
		return "Cheque Status updated";
	}else {
		return "Cheque status not updated";
	}
}
public String aprloan(Account ac) {
	Optional<Account> res = acr.findById(ac.getAccountno());
	if(res.isPresent()) {
		Account a = res.get();
		a.setLoanstatus(ac.getLoanstatus());
		acr.saveAndFlush(a);
		return "Status Updated";
	}else {
		return "Invalid account no";
	}
	
}
public List<User> getUserDetails(){
	return ur.findAll();
}
public List<Account> getAccountDetails(){
	return acr.findAll();
}
public List<Loan> getLoanDetails(){
	return lr.findAll();
}
public List<Transaction> getTransactionDetails(){
	return tr.findAll();
}



public List<Transaction> getTransactionByMonth(String month) {
	

	System.out.println(month);
	List<Transaction> result= new ArrayList<Transaction>();
	for (Transaction transaction : tr.findAll()) {
		if(month.equalsIgnoreCase(transaction.getTimestamp().getMonth().name()))
		{
			System.out.println(month);
		result.add(transaction);
		}
		
	}
	
	return result;
	
}

public List<Transaction> getTransactionByYear(int year)
{
	List<Transaction> result= new ArrayList<Transaction>();
	for (Transaction transaction : tr.findAll()) {
		if(year==transaction.getTimestamp().getYear())
		{
			System.out.println(year);
		result.add(transaction);
		}
		
	}
	System.out.println(result);
	return result;
}

public List<Transaction> getTransactionByDate(String d)
{
	
	List<Transaction> result= new ArrayList<Transaction>();
	for (Transaction transaction : tr.findAll()) {
			if(transaction.getTimestamp().toLocalDate().toString().equals(d))
			{
				result.add(transaction);
			}
		
		
	}
	System.out.println(result);
	return result;
}

public List<Loan> getApprovedRejectedLoan()
{
	List<Loan> result= new ArrayList<Loan>();
	for ( Account ac : acr.findAll()) {
		if(ac.getLoanstatus().equals("approved")||ac.getLoanstatus().equals("rejected"))
		{
			for (Loan loan : ac.getLoanid()) {
				
				result.add(loan);
			}
		}
	}
	return result;
	
}
	
}
