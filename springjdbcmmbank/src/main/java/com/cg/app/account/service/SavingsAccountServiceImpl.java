package com.cg.app.account.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.app.account.SavingsAccount;
import com.cg.app.account.dao.SavingsAccountDAO;
import com.cg.app.account.factory.AccountFactory;
import com.cg.app.exception.AccountNotFoundException;
import com.cg.app.exception.InsufficientFundsException;
import com.cg.app.exception.InvalidInputException;


@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {

	private AccountFactory factory;
	@Autowired
	private SavingsAccountDAO savingsAccountDAO;

	public SavingsAccountServiceImpl(/* SavingsAccountDAO savingsAccountDAO */) {
		factory = AccountFactory.getInstance();
		//this.savingsAccountDAO= savingsAccountDAO;
	}

	public SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary) {
		SavingsAccount account = factory.createNewSavingsAccount(accountHolderName, accountBalance, salary);
		savingsAccountDAO.createNewAccount(account);
		return null;
	}

	public List<SavingsAccount> getAllSavingsAccount() {
		return savingsAccountDAO.getAllSavingsAccount();
	}

	public void deposit(SavingsAccount account, double amount) {
		/*if (amount > 0) {*/
			double currentBalance = account.getBankAccount().getAccountBalance();
			currentBalance += amount;
			savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
		/*
		 * }else { throw new InvalidInputException("Invalid Input Amount!"); }
		 */
	}
	public void withdraw(SavingsAccount account, double amount) {
		double currentBalance = account.getBankAccount().getAccountBalance();
		/* if (amount > 0 && currentBalance >= amount) { */
			currentBalance -= amount;
			savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
	}

	@Transactional
	public void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount) {
			withdraw(sender, amount);
			deposit(receiver, amount);
	}

	public boolean updateAccount(SavingsAccount account) {
		return savingsAccountDAO.updateAccountType(account);
	}


	public SavingsAccount deleteAccount(int accountNumber) throws AccountNotFoundException {
		return  savingsAccountDAO.deleteAccount(accountNumber);
	}
	
	public SavingsAccount getAccountById(int accountNumber) throws AccountNotFoundException {
		return savingsAccountDAO.getAccountById(accountNumber);
	}

	public double checkBalance(int accountNumber) throws AccountNotFoundException {
		return savingsAccountDAO.checkBalance(accountNumber);
	}

	public List<SavingsAccount> sortByAccountHolderName() {
		return savingsAccountDAO.sortByAccountHolderName();
	}

	public List<SavingsAccount> sortByBalanceRange(int minimumBalance,
			int maximumBalance) {
		
		return savingsAccountDAO.sortByBalanceRange(minimumBalance,maximumBalance);
	}

	public List<SavingsAccount> sortByAccountHolderNameDescending() {
		return savingsAccountDAO.sortByAccountHolderNameDescending();
	}

	public List<SavingsAccount> sortByBalanceRangeDescending(
			int minimumBalanceDescending, int maximumBalanceDescending) {
		
		return savingsAccountDAO.sortByBalanceRangeDescending(minimumBalanceDescending,maximumBalanceDescending);
	}

	public List<SavingsAccount> getAccountByName(String accountHolderName) throws AccountNotFoundException {
		return savingsAccountDAO.getAccountByName(accountHolderName);
	}

	public List<SavingsAccount> getAllBelowBalance(int balanceNumber) {
		
		return savingsAccountDAO.getAllBelowBalance(balanceNumber);
	}

	public List<SavingsAccount> getAllAboveBalance(int balanceNumber) {
		
		return savingsAccountDAO.getAllAboveBalance(balanceNumber);
	}

	public List<SavingsAccount> sortByAccountBalance() {
		return savingsAccountDAO.sortByAccountBalance();
	}

	public List<SavingsAccount> sortByAccountBalanceDescending() {
		return savingsAccountDAO.sortByAccountBalanceDescending();
	}
}
