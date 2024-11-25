package com.eazybytes.loans.service;

import com.eazybytes.loans.dto.LoansDto;

public interface ILoansService {

	public void createLoans(String mobileNumber);
	public LoansDto fetchLoans(String mobileNumber);
	
	public boolean updateLoan(LoansDto loansDto);
	
	public boolean deleteLoan(String mobileNumber);
}
