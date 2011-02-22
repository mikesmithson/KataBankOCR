package com.pillar.katabankocr.account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.pillar.katabankocr.account.reader.AccountFileReader;
import com.pillar.katabankocr.parser.AccountNumberParser;

public class AccountCreator implements ApplicationContextAware {
	
	private ApplicationContext ctx;

	public List<Account> createAccountListFrom(String fileName) throws IOException {
		List<Account> accountsList = new ArrayList<Account>();
		List<String> accountCharacterList = ctx.getBean(AccountFileReader.class).parseAccountFileToList(fileName);
		for (String accountString : accountCharacterList) {
			Account account = ctx.getBean(Account.class);
			account.setAccountNumber(ctx.getBean(AccountNumberParser.class).parseAccountFor(accountString));
			accountsList.add(account);
		}
		return accountsList;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext;
	}
}
