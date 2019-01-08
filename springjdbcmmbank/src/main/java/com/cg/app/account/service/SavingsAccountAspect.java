package com.cg.app.account.service;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.cg.app.account.SavingsAccount;
import com.cg.app.exception.InsufficientFundsException;

@Aspect
@Component
public class SavingsAccountAspect {
	
	Logger logger = Logger.getLogger(SavingsAccountAspect.class.getName());
	
	@Around("execution(* com.cg.app.account.service.SavingsAccountServiceImpl.deposit(..))")
	public void depositValidation(ProceedingJoinPoint pjp) throws Throwable {
		Object[] param=pjp.getArgs();
		SavingsAccount savingsAccount=(SavingsAccount) param[0];
		double amount = (Double)param[1];
		if(savingsAccount==null) {
			logger.warning("Account number doesnot exists!!");
		}
		else if(amount>0) {
			pjp.proceed();
		}
		else {
			logger.warning("Deposit amount should be greater than 0");
		}
	}
	
	@Around("execution(* com.cg.app.account.service.SavingsAccountServiceImpl.withdraw(..))")
	public void withdrawValidation(ProceedingJoinPoint pjp) throws Throwable {
		Object[] param=pjp.getArgs();
		SavingsAccount savingsAccount = (SavingsAccount)param[0];
		
		double currentBalance = savingsAccount.getBankAccount().getAccountBalance();
		double amount = (Double)param[1];
		if(savingsAccount==null) {
			logger.warning("Account number doesnot exists!!");
		}
		else if (amount > 0 && currentBalance >= amount) {
			pjp.proceed();
		} else {
			logger.warning("Withdraw amount should begreater than 0 and ");
		}
	}
	@Around("execution(* com.cg.app.account.service.SavingsAccountServiceImpl.fundTransfer(..))")
	public void fundTransferValidation(ProceedingJoinPoint pjp) throws Throwable {
		Object[] param=pjp.getArgs();
		SavingsAccount sender = (SavingsAccount)param[0];
		double senderBalance = sender.getBankAccount().getAccountBalance();
		SavingsAccount receiver = (SavingsAccount)param[1];
		//double recieverBalance=receiver.getBankAccount().getAccountBalance();
		double amount = (Double)param[2];
		if(sender==null ||receiver==null) {
			logger.warning("Check the account numbers you have entered!!");
		}
		else if (amount<= senderBalance) {
			pjp.proceed();
		} else {
			logger.warning("Withdraw amount should begreater than 0 and ");
		}
	}
}
