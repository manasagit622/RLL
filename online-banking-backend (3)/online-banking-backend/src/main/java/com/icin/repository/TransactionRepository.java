package com.icin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.icin.bean.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
	
	@Query("select t from Transaction t where t.transactionid=:transactionid")
	Optional<Transaction>findtrans(@Param("transactionid") int transactionid);

}