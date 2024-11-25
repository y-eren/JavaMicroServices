package com.eazybytes.accounts.service;


import org.springframework.stereotype.Service;

import com.eazybytes.accounts.dto.CustomerDto;


public interface IAccountsService {

	void createAccount(CustomerDto customerDTO);
	CustomerDto fetchAccount(String mobileNumber);
	
	boolean updateAccount(CustomerDto customerDto);
	
	boolean deleteAccount(String mobileNumber);
}
