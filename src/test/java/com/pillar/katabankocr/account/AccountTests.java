package com.pillar.katabankocr.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {"classpath:app-context.xml"})
public class AccountTests extends AbstractJUnit4SpringContextTests {

	@Autowired
	@Qualifier("account")
	private Account account;
	
	@Test
	public void testGetAccountStatusForValidAccount() throws Exception
	{
		account.setAccountNumber("457508000");
		assertEquals("   ", account.getAccountStauts());
		assertTrue(account.isValid());
	}
	
	@Test
	public void testGetAccountStatusForInvalidCheckSumAccount() throws Exception
	{
		account.setAccountNumber("664371495");
		assertEquals("ERR", account.getAccountStauts());
		assertFalse(account.isValid());
	}
	
	@Test
	public void testGetAccountStatusForIllegibleCheckSumAccount() throws Exception
	{
		account.setAccountNumber("86110??36");
		assertEquals("ILL", account.getAccountStauts());
		assertFalse(account.isValid());
	}
	
	
	@Test
	public void testToStringValidAccount() throws Exception
	{
		account.setAccountNumber("457508000");
		assertEquals("457508000", account.toString());
		assertTrue(account.isValid());
	}
	
	@Test
	public void testToStringAccountInError() throws Exception
	{
		account.setAccountNumber("664371495");
		assertEquals("664371495 ERR", account.toString());
		assertFalse(account.isValid());
	}
	
	@Test
	public void testToStringAccountIllegible() throws Exception
	{
		account.setAccountNumber("86110??36");
		assertEquals("86110??36 ILL", account.toString());
		assertFalse(account.isValid());
	}
	
	@Test
	public void testToStringAmbivalentThreeAccounts() throws Exception
	{
		List<String> reconcileAccounts = new ArrayList<String>();
		reconcileAccounts.add("888886888");
		reconcileAccounts.add("888888880");
		reconcileAccounts.add("888888988");
		
		account.setAccountNumber("888888888");
		account.setReconciledAccountArray(reconcileAccounts);
		assertEquals(AccountStatusEnum.AMBIVALENT.status(), account.getAccountStauts());
		assertEquals("888888888 AMB ['888886888', '888888880', '888888988']", account.toString());
		
	}
	
	@Test
	public void testToStringAmbivalentTwoAccounts() throws Exception
	{
		List<String> reconcileAccounts = new ArrayList<String>();
		reconcileAccounts.add("666566666");
		reconcileAccounts.add("686666666");
		
		account.setAccountNumber("666666666");
		account.setReconciledAccountArray(reconcileAccounts);
		assertEquals(AccountStatusEnum.AMBIVALENT.status(), account.getAccountStauts());
		assertEquals("666666666 AMB ['666566666', '686666666']", account.toString());
	}

}
