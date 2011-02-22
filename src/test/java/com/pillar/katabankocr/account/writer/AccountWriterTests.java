package com.pillar.katabankocr.account.writer;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.pillar.katabankocr.account.Account;
import com.pillar.katabankocr.account.AccountCreator;

@ContextConfiguration(locations = {"classpath:app-context.xml"})
public class AccountWriterTests extends AbstractJUnit4SpringContextTests { 
	
	@Autowired
	@Qualifier("accountfilewriter")
	private AccountFileWriter accountFileWriter;
	
	@Autowired
	@Qualifier("accountcreator")
	private AccountCreator accountCreator;
	

	@Test
	public void testAccountWriter() throws Exception
	{
		List<Account> accountList = accountCreator.createAccountListFrom("classpath:accounts-uc3.txt");
		accountFileWriter.writeResultsTo(accountList, "file:/temp/accounts-out-uc3.txt");
	}
	
}
