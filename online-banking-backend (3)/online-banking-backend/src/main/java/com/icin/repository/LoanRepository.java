package com.icin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.icin.bean.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
	@Query("select l from Loan l where l.loanid=:loanid")
	Optional<Loan> findloan(@Param("loanid") int loanid);
	

}
