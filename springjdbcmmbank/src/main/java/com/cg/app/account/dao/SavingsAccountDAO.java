package com.cg.app.account.dao;

import java.util.List;

import com.cg.app.account.SavingsAccount;
import com.cg.app.exception.AccountNotFoundException;

public interface SavingsAccountDAO {
	
	SavingsAccount createNewAccount(SavingsAccount account);
	
	SavingsAccount getAccountById(int accountNumber)throws AccountNotFoundException;
	
	SavingsAccount deleteAccount(int accountNumber) throws AccountNotFoundException;
	
	List<SavingsAccount> getAllSavingsAccount();
	
	void updateBalance(int accountNumber, double currentBalance);
	
	double checkBalance(int accountNumber) throws AccountNotFoundException;
	
	boolean updateAccountType(SavingsAccount account);
	
	List<SavingsAccount> sortByAccountHolderName();
	
	List<SavingsAccount> sortByBalanceRange(int minimumBalance, int maximumBalance);
	
	List<SavingsAccount> sortByAccountHolderNameDescending();
	
	List<SavingsAccount> sortByBalanceRangeDescending(int minimumBalanceDescending, int maximumBalanceDescending);
	
	List<SavingsAccount> getAccountByName(String accountHolderName) throws AccountNotFoundException;
	
	List<SavingsAccount> getAllBelowBalance(int balanceNumber);
	
	List<SavingsAccount> getAllAboveBalance(int balanceNumber);

	List<SavingsAccount> sortByAccountBalance() ;

	List<SavingsAccount> sortByAccountBalanceDescending();
}
