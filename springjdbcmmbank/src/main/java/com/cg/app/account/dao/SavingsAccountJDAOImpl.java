package com.cg.app.account.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cg.app.account.SavingsAccount;
import com.cg.app.account.mapper.SavingsAccountDetailsMapper;
import com.cg.app.exception.AccountNotFoundException;

@Repository
public class SavingsAccountJDAOImpl implements SavingsAccountDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public SavingsAccount createNewAccount(SavingsAccount account){
		jdbcTemplate.update("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)",
				new Object[] { account.getBankAccount().getAccountNumber(),
						account.getBankAccount().getAccountHolderName(), account.getBankAccount().getAccountBalance(),
						account.isSalary(), null, "SA" });
		return account;
	}

	public List<SavingsAccount> getAllSavingsAccount()  {

		return jdbcTemplate.query("SELECT * FROM ACCOUNT", new SavingsAccountDetailsMapper());
	}

	public void updateBalance(int accountNumber, double currentBalance) {

		jdbcTemplate.update("UPDATE ACCOUNT SET account_bal=? where account_id=?",
				new Object[] { currentBalance, accountNumber });

	}

	public SavingsAccount getAccountById(int accountNumber)
			throws  AccountNotFoundException {

		return jdbcTemplate.queryForObject("SELECT * FROM account where account_id=?", new Object[] { accountNumber },
				new SavingsAccountDetailsMapper());

	}

	public SavingsAccount deleteAccount(int accountNumber)
			throws AccountNotFoundException {

		jdbcTemplate.update("DELETE FROM account WHERE account_id=?", new Object[] { accountNumber });
		return null;
	}

	public double checkBalance(int accountNumber)
			throws AccountNotFoundException{

		return jdbcTemplate.queryForObject("SELECT account_bal FROM account where account_id=?",
				new Object[] { accountNumber }, Double.class);
	}

	public boolean updateAccountType(SavingsAccount account){

		jdbcTemplate.update("UPDATE ACCOUNT SET account_hn=?,salary=? where account_id=?",
				new Object[] { account.getBankAccount().getAccountHolderName(), account.isSalary(),
						account.getBankAccount().getAccountNumber() });

		return false;
	}

	public List<SavingsAccount> sortByAccountHolderName() {

		return jdbcTemplate.query("SELECT * FROM account ORDER BY account_hn", new SavingsAccountDetailsMapper());
	}

	public List<SavingsAccount> sortByBalanceRange(int minimumBalance, int maximumBalance){

		return jdbcTemplate.query("SELECT * FROM account WHERE account_bal BETWEEN ? and ? ORDER BY account_bal",
				new Object[] { minimumBalance, maximumBalance }, new SavingsAccountDetailsMapper());
	}

	public List<SavingsAccount> sortByAccountHolderNameDescending(){

		return jdbcTemplate.query("SELECT * FROM account ORDER BY account_hn DESC", new SavingsAccountDetailsMapper());
	}

	public List<SavingsAccount> sortByBalanceRangeDescending(int minimumBalanceDescending, int maximumBalanceDescending){

		return jdbcTemplate.query("SELECT * FROM account WHERE account_bal BETWEEN ? and ? ORDER BY account_bal DESC",
				new Object[] { minimumBalanceDescending, maximumBalanceDescending }, new SavingsAccountDetailsMapper());
	}

	public List<SavingsAccount> getAccountByName(String accountHolderName)
			throws AccountNotFoundException{

		return jdbcTemplate.query("SELECT * FROM account where account_hn=?", new Object[] { accountHolderName },
				new SavingsAccountDetailsMapper());
	}

	public List<SavingsAccount> getAllBelowBalance(int balanceNumber) {

		return jdbcTemplate.query("SELECT * FROM ACCOUNT WHERE account_bal<=?", new Object[] { balanceNumber },
				new SavingsAccountDetailsMapper());
	}

	public List<SavingsAccount> getAllAboveBalance(int balanceNumber) {

		return jdbcTemplate.query("SELECT * FROM ACCOUNT WHERE account_bal>=?", new Object[] { balanceNumber },
				new SavingsAccountDetailsMapper());
	}

	public List<SavingsAccount> sortByAccountBalance(){

		return jdbcTemplate.query("SELECT * FROM account ORDER BY account_bal", new SavingsAccountDetailsMapper());
	}

	public List<SavingsAccount> sortByAccountBalanceDescending() {

		return jdbcTemplate.query("SELECT * FROM account ORDER BY account_bal DESC", new SavingsAccountDetailsMapper());
	}
}