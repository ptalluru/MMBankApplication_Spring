package com.cg.app.account.service;

import java.sql.SQLException;
import java.util.List;

import com.cg.app.account.SavingsAccount;
import com.cg.app.exception.AccountNotFoundException;

public interface SavingsAccountService {

	SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary);

	boolean updateAccount(SavingsAccount account);

	SavingsAccount getAccountById(int accountNumber) throws AccountNotFoundException;

	SavingsAccount deleteAccount(int accountNumber) throws  AccountNotFoundException;
	
	List<SavingsAccount> getAllSavingsAccount() ;

	void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount);
	
	void deposit(SavingsAccount account, double amount) ;
	
	void withdraw(SavingsAccount account, double amount) ;
	
	double checkBalance(int accountNumber) throws AccountNotFoundException;

	List<SavingsAccount> sortByAccountHolderName();
	
	List<SavingsAccount> sortByBalanceRange(int minimumBalance,int maximumBalance);

	List<SavingsAccount> sortByAccountHolderNameDescending();

	List<SavingsAccount> sortByBalanceRangeDescending(int minimumBalanceDescending, int maximumBalanceDescending);

	List<SavingsAccount> getAccountByName(String accountHolderName) throws AccountNotFoundException;

	List<SavingsAccount> getAllBelowBalance(int balanceNumber) ;

	List<SavingsAccount> getAllAboveBalance(int balanceNumber);

	List<SavingsAccount> sortByAccountBalance();

	List<SavingsAccount> sortByAccountBalanceDescending();
}