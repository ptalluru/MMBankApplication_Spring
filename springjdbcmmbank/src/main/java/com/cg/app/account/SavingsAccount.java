package com.cg.app.account;

public class SavingsAccount {
	private boolean salary;
	private BankAccount bankAccount;

	
	public SavingsAccount(int accountNumber,String accountHolderName, boolean salary) {
		bankAccount = new BankAccount(accountNumber,accountHolderName);
		this.salary = salary;
	}

	public SavingsAccount(int accountNumber, String accountHolderName, double accountBalance, boolean salary) {
		bankAccount = new BankAccount(accountNumber, accountHolderName, accountBalance);
		this.salary = salary;
	}
	public SavingsAccount(String accountHolderName, double accountBalance, boolean salary) {
		bankAccount = new BankAccount (accountHolderName,accountBalance);
		this.salary = salary;
	}
	public SavingsAccount(double accountBalance) {
	}
	public SavingsAccount(int accountNumber, String accountHolderName) {
	}
	public SavingsAccount(int accountId, boolean changeSalaryType) {
	}
	
	public SavingsAccount() {
	}

	public boolean isSalary() {
		return salary;
	}

	public void setSalary(boolean salary) {
		this.salary = salary;
	}
	
	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Override
	public String toString() {
		return "SavingsAccount [salary=" + salary + ", bankAccount=" + bankAccount + "]";
	}
}















