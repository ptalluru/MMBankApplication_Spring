package com.cg.app.account.service;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logging {
	Logger logger = Logger.getLogger(Logging.class.getName());

	@After("execution(* com.cg.app.account.service.SavingsAccountServiceImpl.createNewAccount(..))")
	public void creatAccountLog(JoinPoint joinPoint) throws Exception {
		logger.info("Account created successfully!!");
	}

	@After("execution(* com.cg.app.account.service.SavingsAccountServiceImpl.deleteAccount(..))")
	public void deleteAccountLog(JoinPoint joinPoint) throws Exception {
		logger.info("Account deleted successfully!!");
	}

	@After("execution(* com.cg.app.account.service.SavingsAccountServiceImpl.updateAccount(..))")
	public void updateAccountLog(JoinPoint joinPoint) throws Exception {
		logger.info("Account updated successfully!!");
	}
	@After("execution(* com.cg.app.account.service.SavingsAccountServiceImpl.withdraw(..))")
	public void withdrawLog(JoinPoint joinPoint) throws Exception {
		logger.info("Withdrawl successfull!!");
	}
	@After("execution(* com.cg.app.account.service.SavingsAccountServiceImpl.deposit(..))")
	public void depositLog(JoinPoint joinPoint) throws Exception {
		logger.info("Deposit successfull!!");
	}
	@After("execution(* com.cg.app.account.service.SavingsAccountServiceImpl.fundTransfer(..))")
	public void fundTransferLog(JoinPoint joinPoint) throws Exception {
		logger.info("Fund Transfer successfull!!");
	}
}
