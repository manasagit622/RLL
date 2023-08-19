package com.icin.repository;

import org.springframework.stereotype.Repository;


import com.icin.bean.Account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
	
	@Query("select a.balance from Account a where a.accountno=:accountno")
    float getprimbal(@Param("accountno") Long accountno);
    
    @Query("select a.balance from Account a where a.accounttype='savings'")
    List<Object[]>getsavbal(@Param("accountno") Long accountno);
    
    
    @Query("select a from Account a where a.accountno=:accountno")
	Optional<Account>findaccount(@Param("accountno") Long accountno);
}
