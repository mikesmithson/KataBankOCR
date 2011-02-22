package com.pillar.katabankocr.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {"classpath:app-context.xml"})
public class AccountCreatorTests extends AbstractJUnit4SpringContextTests {

	@Autowired
	@Qualifier("accountcreator")
	private AccountCreator accountCreator;
	
	@Test
	public void testAccountCreator() throws Exception
	{
		List<Account> accounts = accountCreator.createAccountListFrom("classpath:accounts-uc3.txt");
		assertFalse(accounts.isEmpty());
		assertEquals(3, accounts.size());
		assertEquals("000000051", accounts.get(0).getAccountNumber());
		assertEquals("   ", accounts.get(0).getAccountStauts());
		assertEquals("49006771?", accounts.get(1).getAccountNumber());
		assertEquals("ILL", accounts.get(1).getAccountStauts());
	}
}
