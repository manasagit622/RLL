package com.icin.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icin.bean.Account;
import com.icin.bean.Loan;
import com.icin.bean.Transaction;
import com.icin.bean.User;
import com.icin.repository.AccountRepository;
import com.icin.repository.LoanRepository;
import com.icin.repository.TransactionRepository;
import com.icin.repository.UserRepository;

@Service
public class UserService {
	
	
	@Autowired
	UserRepository ur;
	@Autowired
	AccountRepository ar;
	
	@Autowired
	LoanRepository lr;
	
	@Autowired
	TransactionRepository tr;
	
	
	public void register(User u,Account a )
	{

		ar.save(a);
		u.getAccountno().add(a);
		ur.save(u);

		System.out.println(ur.findById(2).get().getAccountno());
	}
	public String userreg(User u) {
		Optional<User> res = ur.findById(u.getUserid());
		if(res.isPresent()) {
			return "User already present";
		}else {
			ur.save(u);
			return "User added";
		}
		
	}
	public void acctrans(Account a, Transaction t) {
		tr.save(t);
		a.getTransactionid().add(t);
		ar.save(a);
		
	}
	
	
	public String  userlogin(User us) {
		System.out.println(us);
		List<User> objects = ur.findAll();
		for (User obj : objects) {
			if(us.getEmail().equals(obj.getEmail())) {
				if(us.getPassword().equals(obj.getPassword())) {
					return "successful";
				}else {
					return "Invalid password";
				}
			} 
			
		}
		return "Invalid emailid";
	}
	public String cgpass(User us) {
		List<User> objects = ur.findAll();
		for(User obj : objects) {
			System.out.println("-----------------------------------------------------------------"+us.getPassword());
			System.out.println("-----------------------------------------------------------------"+us.getEmail());
			System.out.println("------------------------------------------------------------------"+obj.getEmail());
			if(us.getEmail().equals(obj.getEmail())) {
				System.out.println("-----------------------------------------------------------------"+us.getPassword());
				System.out.println("-----------------------------------------------------------------"+us.getEmail());
				obj.setPassword(us.getPassword());
				ur.saveAndFlush(obj);
				return "Password changed";
			}
			
		}
			return "Password not changed";
		}
	
		
	
	public String cgdetails(User us) {
		Optional<User> res = ur.findById(us.getUserid());
		if(res.isPresent()) {
			User u = res.get();
			u.setAddress(us.getAddress());
			u.setEmail(us.getEmail());
			u.setPhno(us.getPhno());
			ur.saveAndFlush(u);
			return "Details updated";
		}else {
			return "Details not updated";
		}
	}
	
	
	public String applyLoan(Loan l,long accountno)
	{
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1"+l);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+accountno);
		
		try {
			System.out.println(l.getInterest());
			
				lr.save(l);
				Optional<Account> result= ar.findById(accountno);
				
				result.get().setLoanstatus("requested");
				
				System.out.println("done!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				//Account arr= result.get();
				ar.save(result.get());
				System.out.println("done!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				return "Loan Application Submitted";
			
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "Request Unsuccessful";
		}
		
		
	}
	
	public Optional<User> getUserDetail(int userid){
		return ur.finduser(userid);
	}
	
	public Optional<Account> getAccountDetails(Long accountno){
		return ar.findaccount(accountno);
	}
	public Optional<Loan> getLoanDetails(int loanid){
		return lr.findloan(loanid);
	}
	public Optional<Transaction> getTransactionDetails(int transactionid){
		return tr.findtrans(transactionid);
	}
	
	
	

	public String transferFund(Transaction t,int userid,String accountType)
	{
		tr.save(t);
		Optional<User> result= ur.findById(userid);
		if(result.isPresent()) {
			User user = result.get();
			
			for(Account a:user.getAccountno())
			{
				if (a.getAccounttype().equals(accountType) && t.getAmount()<=a.getBalance())
				{
					t.setTimestamp(LocalDateTime.now());
					tr.save(t);
					a.setBalance(a.getBalance()-t.getAmount());
					a.getTransactionid().add(t);
				//	a.getUserid();
					ar.save(a);
					
					return "Funds transfered successfully";
				}
			}
		
			
			return "Balance in your account is insufficient";
		}
		
		return "Funds transfered unccessful";
	}
	
	
	
	public List<Transaction> getByMonth(String month,long accountno)
	{
		Optional<Account> result= ar.findById(accountno);
		List<Transaction> result1= result.get().getTransactionid();
		if(result.isPresent())
		{	
			for (Transaction transaction : result1 ) {
				if(month.equalsIgnoreCase(transaction.getTimestamp().getMonth().name()))
				{
					System.out.println(month);
					result1.add(transaction);
				}
				
			}
						
			
			}
			
			return result1;
	}
	public List<Transaction> getByYear(int year,long accountno)
	{
		Optional<Account> result= ar.findById(accountno);
		List<Transaction> result1= result.get().getTransactionid();
		if(result.isPresent())
		{	
			for (Transaction transaction : result1 ) {
				if(year==(transaction.getTimestamp().getYear()))
				{
					System.out.println(year);
					result1.add(transaction);
				}
				
			}
						
			
			}
			
			return result1;
	}
	public List<Transaction> getByDate(String date,long accountno)
	{
		Optional<Account> result= ar.findById(accountno);
		List<Transaction> result1= result.get().getTransactionid();
		if(result.isPresent())
		{	
			for (Transaction transaction : result1 ) {
				if(transaction.getTimestamp().toLocalDate().toString().equals(date))
				{
					System.out.println(date);
					result1.add(transaction);
				}
				
			}
						
			
			}
			
			return result1;
	}
	
	public int getUserid(String email)
	{
		for(User u:ur.findAll())
		{
			if(u.getEmail().equals(email))
			{
				return u.getUserid();
			}
		}
		
		return 0;
	}
	public String createAccount(int userid,Account a)
	{
		Optional<User>result= ur.findById(userid);
		if(result.isPresent())
		{
			System.out.println("Hello.......hhh"+userid);
			System.out.println(a);
			ar.save(a);
			result.get().getAccountno().add(a);
			
			ur.save(result.get());
			return "Account Created Successfully";
		}
		return "failed";
	}
	

}
