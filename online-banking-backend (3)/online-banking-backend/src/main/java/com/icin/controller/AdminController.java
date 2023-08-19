package com.icin.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icin.bean.Account;
import com.icin.bean.Admin;
import com.icin.bean.Loan;
import com.icin.bean.Transaction;
import com.icin.bean.User;
import com.icin.service.AccountService;
import com.icin.service.AdminService;

@RestController
@RequestMapping
@CrossOrigin
public class AdminController {
	@Autowired
	AdminService ads;
	
	@Autowired
	AccountService as;
	
	@PostMapping(value="/adlog",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String adlog(@RequestBody Admin ad) {
		return ads.alog(ad);
	}
	@PutMapping(value="loanstatus", consumes=MediaType.APPLICATION_JSON_VALUE)
	public String loanstatus(@RequestBody Account ad) {
		return ads.aprloan(ad);
	}
	@GetMapping(value="month/{timestamp}",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> month(@PathVariable("timestamp")LocalDateTime timestamp ){
		return ads.getTransactionByMonth(timestamp);
	}
	@PostMapping(value="adloan",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String adloan(@RequestBody Loan l) {
		return ads.adloan(l);
	}
	@PutMapping(value="grantacs/{accountno}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String grantacs(@PathVariable("accountno") long accountno) {
		return as.grantAccess(accountno);
	}
	@PutMapping(value="blockacs/{accountno}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String blockacs(@PathVariable long accountno) {
		return as.blockAccess(accountno);
	}
	@PutMapping(value="blockacc", consumes=MediaType.APPLICATION_JSON_VALUE)
	public String blockacc(@RequestBody Account ad) {
		return as.blockAccount(ad);
	}
	@PutMapping(value="reactacc", consumes=MediaType.APPLICATION_JSON_VALUE)
	public String reactacc(@RequestBody Account ad) {
		return as.ReactivateAccount(ad);
	}
	
	@PutMapping(value="chqstat/{accountno}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public String chqacc(@PathVariable("accountno") long accountno) {
		return ads.chqstatus(accountno);//
	}
	
	@GetMapping(value="adgetuser", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> adgetuser(){
		return ads.getUserDetails();
	}
	@GetMapping(value="adgetaccount", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getAccountDetails(){
		return ads.getAccountDetails();
	}
	@GetMapping(value="adgetloan", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Loan> getLoanDetails(){
		return ads.getLoanDetails();
	}
	
	@GetMapping(value="adgettransaction", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getTransactionDetails(){
		return ads.getTransactionDetails();
	}
	
	
	
	@GetMapping(value = "bymonth/{month}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getByMonth(@PathVariable("month") String month)
	{
		System.out.println(month);
		return ads.getTransactionByMonth(month);
	}
	
	@GetMapping(value = "byyear/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getByYear(@PathVariable("year") int year)
	{
		System.out.println(year);
		 return ads.getTransactionByYear(year);
	}
	@GetMapping(value="bydate/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getByYear(@PathVariable("date") String date)
	{
		System.out.println(date);
		 return ads.getTransactionByDate(date);
	}
	
	@GetMapping(value="ApprovedRejectedLoan", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Loan> getApprovedRejectedLoan()
	{
		return ads.getApprovedRejectedLoan();
	}

	
	@GetMapping(value="accountrequest", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getAccountrequest()
	{
		return as.getAllAccReq();
	}
	
	@GetMapping(value="getchqrequest", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getAllchqRequest()
	{
		return as.chequeReq();
	}

	
	
	

}
