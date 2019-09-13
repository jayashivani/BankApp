package com.bv.service;

import com.exceptions.InsufficientBalanceException;

public interface ITransaction {

	public void withdraw(double amount) throws InsufficientBalanceException;
	public void deposite(double amount);
	
}
