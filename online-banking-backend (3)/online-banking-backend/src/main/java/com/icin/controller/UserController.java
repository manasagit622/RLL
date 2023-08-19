package com.icin.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.icin.bean.Account;
import com.icin.bean.AccountLoan;
import com.icin.bean.User;
import com.icin.bean.UserRegister;
import com.icin.service.AccountService;
import com.icin.service.UserService;
import com.icin.bean.AccountTransaction;
import com.icin.bean.Loan;
import com.icin.bean.Transaction;

@RestController
@CrossOrigin


public class UserController {
	
	@Autowired
	UserService us;
	
	@Autowired
	AccountService as;
	
	@PostMapping(value = "register")
	public void register(@RequestBody UserRegister data)
	{
		System.out.println(data.getUser());
		System.out.println(data.getAccount());
//		us.register(data.getUser(), data.getAccount());
		us.register(data.getUser(), data.getAccount());
		
	}
	@PostMapping(value="acctr")
	public void acctr(@RequestBody AccountTransaction atd)
	{
		System.out.println(atd.getAccount());
		System.out.println(atd.getTransaction());
		us.acctrans(atd.getAccount(), atd.getTransaction());
	}
	
	@PatchMapping("addloan/{accountno}")
	public String applyLoan(@RequestBody Loan l,@PathVariable("accountno") long accountno)
	{
		return us.applyLoan(l, accountno);
	}
	
	@PostMapping("ulog")
	public String ulog(@RequestBody User u) {
		return  us.userlogin(u);
	}
	@PostMapping("adduser")
	public String adduser(@RequestBody User u) {
		return us.userreg(u);
	}
	@PatchMapping("createaccount/{userid}")
	public String createAccount(@PathVariable("userid") int userid,@RequestBody Account a)
	{
		return us.createAccount(userid, a);
	}
	@PutMapping(value="cpass",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String cpass(@RequestBody User u) {
		return us.cgpass(u);
	}
	@PatchMapping(value="cdet",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String cdet(@RequestBody User u) {
		return us.cgdetails(u);
	}
	@PutMapping(value="deposit",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String deposit(@RequestBody Account a) {
		return as.deposit(a);
	}
	@PutMapping(value="withdraw",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String withdraw(@RequestBody Account a) {
		return as.withdraw(a);
	}
	@PutMapping(value="apchq",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String apchq(@RequestBody Account a) {
		return as.reqchq(a);
	}
	@GetMapping(value="primbal/{accountno}",produces = MediaType.APPLICATION_JSON_VALUE)
	public float primbal(@PathVariable("accountno") Long accountno){
		return as.pribal(accountno);
	}
	@GetMapping(value="savbal/{accountno}",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Object[]> savbal(@PathVariable("accountno") Long accountno){
		return as.savebal(accountno);
	}
	
	@GetMapping(value="getuser/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<User> getUserDetails(@PathVariable("userid") int userid){
		return us.getUserDetail(userid);
		
	}
	@GetMapping(value="getaccount/{accountno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Account> getAccountDetails(@PathVariable("accountno") Long accountid){
		return us.getAccountDetails(accountid);
	}
	@GetMapping(value="getloan/{loanid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Loan> getLoanDetails(@PathVariable("loanid") int loanid){
		return us.getLoanDetails(loanid);
	}
	
	@GetMapping(value="gettransaction/{transactionid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Transaction> getTransactionDetails(@PathVariable("transactionid") int transactionid){
		return us.getTransactionDetails(transactionid);
	}
	
	//nirmal
	@PatchMapping(value="fundtransfer/{userid}/{accounttype}",produces =MediaType.APPLICATION_JSON_VALUE )
	public String fundTransfer(@RequestBody Transaction t,@PathVariable("userid") int userid,@PathVariable("accounttype") String accounttype)
	{
		
		return us.transferFund(t, userid,accounttype);
	}
	
	@GetMapping(value = "bymonth/{month}/{accountno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getByMonth(@PathVariable("month") String month,@PathVariable("accountno") long accountno)
	{
		System.out.println(month);
		return us.getByMonth(month,accountno);
	}
	
	@GetMapping(value = "byyear/{year}/{accountno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getByYear(@PathVariable("year") int year,@PathVariable("accountno") long accountno)
	{
		System.out.println(year);
		 return us.getByYear(year,accountno);
	}
	@GetMapping(value="bydate/{date}/{accountno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getByDate(@PathVariable("date") String date,@PathVariable("accountno") long accountno)
	{
		System.out.println(date);
		 return us.getByDate(date,accountno);
	}
	@GetMapping(value="getid/{email}")
	public int getuserid(@PathVariable("email") String email)
	{
		return us.getUserid(email);
	}
	
	

}
