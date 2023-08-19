package com.icin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.icin.bean.Account;
import com.icin.bean.Loan;
import com.icin.bean.Transaction;
import com.icin.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select u.email from User as u")
	List<Object> getAllEmail();
	
	@Query("select u from User u where u.userid=:userid")
	Optional<User>finduser(@Param("userid") int userid);

	
	

	
	
 
}
