package com.pillar.katabankocr.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.pillar.katabankocr.validator.AccountValidator;

@ContextConfiguration(locations = {"classpath:app-context.xml"})
public class AccountNumberCheckSumTests extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	@Qualifier("accountvalidator")
	private AccountValidator accountValidator;
	
	@Test
	public void testCheckSumValidForAccountNumber() throws Exception
	{
		String validAccount = "000000051";
		assertTrue(accountValidator.validateAccountFor(validAccount));
		
	}
	
	@Test
	public void testCheckSumInvValidForAccountNumber() throws Exception
	{
		String validAccount = "111111111";
		assertFalse(accountValidator.validateAccountFor(validAccount));
	}
	
	

}
