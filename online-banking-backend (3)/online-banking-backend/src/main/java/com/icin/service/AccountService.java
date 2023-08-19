package com.icin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icin.bean.Account;
import com.icin.bean.User;
import com.icin.repository.AccountRepository;
import com.icin.repository.LoanRepository;
import com.icin.repository.UserRepository;
@Service
public class AccountService {
	@Autowired
	UserRepository ur;
	@Autowired
	AccountRepository ar;
	
	@Autowired
	LoanRepository lr;
	
	public String deposit(Account ac) {
		Optional<Account> res = ar.findById(ac.getAccountno());
		if(res.isPresent()) {
			Account a = res.get();
			if(a.getDeposit().equals("active") && a.getTransfer().equals("active") && a.getStatus().equals("active")){
			a.setBalance(a.getBalance()+ac.getBalance());
			ar.saveAndFlush(a);
			return "Deposit successful";
			}else {
				return "Not allowed to deposit";
				}
		}else {
			return "Invalid account no";
		}
	}
	public String withdraw(Account ac) {
		Optional<Account> res = ar.findById(ac.getAccountno());
		if(res.isPresent()) {
			Account a = res.get();
			if(a.getTransfer().equals("active") && a.getStatus().equals("active") && a.getWithdrawl().equals("active")){
			if(a.getBalance()>=ac.getBalance()) {
			a.setBalance(a.getBalance()-ac.getBalance());
			ar.saveAndFlush(a);
			return "Withdrawl successful";
			}else {
				return "Not enough balance to withdraw";
				}
		}else {
			return "You are not allowed to withdraw money";
		}
		}
			return "Invalid account number";
		
		
	}
	public String reqchq(Account ac) {
		Optional<Account>res = ar.findById(ac.getAccountno());
		if(res.isPresent()) {
			Account a = res.get();
			if(a.getStatus().equals("active")) {
			a.setCheque(ac.getCheque());
			ar.saveAndFlush(a);
			return "Applied for cheque book";
		}else {
			return "Your account is blocked request denied";
		}
	}else {
		return "Invalid account no";
	}
	}
	public String grantAccess(Long accountno) {
		Optional<Account> result=ar.findById(accountno);
		if(result.isPresent()) {
			Account acc=result.get();
					acc.setStatus("active");
					ar.saveAndFlush(acc);
					return "Access granted ";
				}else {
					return "Access not granted";
			}
	}

	
	public String blockAccess(long accountno) {
		Optional<Account> result=ar.findById(accountno);
		if(result.isPresent()) {
			Account acc=result.get();
					acc.setStatus("inactive");
					ar.saveAndFlush(acc);
			return "Blocked transfer, deposit and withdrawl activities";
		}else {
			return "Not Blocked";
	}
}
	public String blockAccount(Account account) {
	Optional<Account> result=ar.findById(account.getAccountno());
	if(result.isPresent()) {
		Account acc = result.get();
		acc.setStatus(account.getStatus());
		ar.saveAndFlush(acc);
		return "User account blocked";
	}else {
		return "User not blocked";
	}
	}
	public String ReactivateAccount(Account account) {
		Optional<Account> result=ar.findById(account.getAccountno());
		if(result.isPresent()) {
			Account acc = result.get();
			acc.setStatus(account.getStatus());
			ar.saveAndFlush(acc);
			return "User account re-activated";
		}else {
			return "User not re-activated";
		}
		}
	public float pribal(Long accountno){
		return ar.getprimbal(accountno);
	}
	public List<Object[]> savebal(Long accountno){
		return ar.getsavbal(accountno);
	}
	

		public List<Account> getAllAccReq()
		{
			List<Account> result = new ArrayList<Account>();
				for(Account u:ar.findAll())
				{
					if(u.getStatus().equals("pending"))
					{
						result.add(u);
						System.out.println("hi");
					}
				}
				
				return result;
		}
		
		public List<Account> chequeReq()
		{
			List<Account> result= new ArrayList<Account>();
				for(Account a:ar.findAll())
				{
					if(a.getCheque().equals("requested"))
					{
						result.add(a);
					}
				}
			
				
				return result;
		}

	
	
	

}
