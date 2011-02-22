package com.pillar.katabankocr.account.reconcile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.pillar.katabankocr.account.Account;
import com.pillar.katabankocr.parser.AccountNumberParser;
import com.pillar.katabankocr.validator.AccountValidator;

public class AccountReconciler implements ApplicationContextAware {

	private Map<String, String> accountReconcileMap;
	private ApplicationContext applicationContext;

	public Account reconcileAccountFor(String accountNumberRepresentation) {
		String parsedAccountNumber = applicationContext.getBean(AccountNumberParser.class).parseAccountFor(accountNumberRepresentation);
		List<String> possibleResolvedAccounts = new ArrayList<String>();

		Account account = applicationContext.getBean(Account.class);
		account.setAccountNumber(parsedAccountNumber);
		account.setReconciledAccountArray(possibleResolvedAccounts);

		for (int i = 0; i < parsedAccountNumber.length(); i++) 
		{
			String digit = parsedAccountNumber.substring(i, (i + 1));
			StringBuilder builder = new StringBuilder(parsedAccountNumber);
			List<String> replacedDigits = Arrays.asList(accountReconcileMap.get(digit).split(","));

			for (String replacedDigit : replacedDigits) 
			{
				builder.replace(i, (i + 1), replacedDigit);
				parsedAccountNumber = builder.toString();
				if (applicationContext.getBean(AccountValidator.class).validateAccountFor(parsedAccountNumber)) 
				{
					possibleResolvedAccounts.add(parsedAccountNumber);
				} 
				builder.replace(i, (i + 1), digit);
				parsedAccountNumber = builder.toString();
			}
		}

		if (!possibleResolvedAccounts.isEmpty() && possibleResolvedAccounts.size() == 1) 
		{
			account.setAccountNumber(possibleResolvedAccounts.get(0));
		}

		return account;
	}

	public void setAccountReconcileMap(Map<String, String> accountReconcileMap) 
	{
		this.accountReconcileMap = accountReconcileMap;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException 
	{
		this.applicationContext = applicationContext;
	}

}
