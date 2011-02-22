package com.pillar.katabankocr.account.reconcile;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {"classpath:app-context.xml"})
public class AccountReconcileTests extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	@Qualifier("accountreconciler")
	private AccountReconciler accountReconciler;

	
	@Test
	public void testReconcileAccountForAllOnes() throws Exception
	{
		String ones  =  "                           " +
		 				"  |  |  |  |  |  |  |  |  |" + 
		 				"  |  |  |  |  |  |  |  |  |" +
		 				"                           ";
		assertEquals("711111111", accountReconciler.reconcileAccountFor(ones).toString());
	}
	
	
	@Test
	public void testReconcileForTwoAndZeros() throws Exception
	{
		String twoAndZeroesAccountNumber = " _  _  _  _  _  _  _  _  _ " +
										   " _|| || || || || || || || |" +
										   "|_ |_||_||_||_||_||_||_||_|" +
										   "                           ";
		
		assertEquals("200800000", accountReconciler.reconcileAccountFor(twoAndZeroesAccountNumber).toString());
	}
	
	@Test
	public void testReconcileForAllThrees() throws Exception
	{
		String threes = " _  _  _  _  _  _  _  _  _ " +
						" _| _| _| _| _| _| _| _| _|" +
						" _| _| _| _| _| _| _| _| _|" +
						"                           ";
		assertEquals("333393333", accountReconciler.reconcileAccountFor(threes).toString());
	}
	
	
	@Test
	public void testReconcileForAllEights() throws Exception
	{
		String eights =  " _  _  _  _  _  _  _  _  _ " +
		 				 "|_||_||_||_||_||_||_||_||_|" +
		 				 "|_||_||_||_||_||_||_||_||_|" +
		 				 "                           ";
		assertEquals("888888888 AMB ['888886888', '888888880', '888888988']", accountReconciler.reconcileAccountFor(eights).toString());
	}
	
	@Test
	public void testReconcileForAllFives() throws Exception
	{
		String fives = 	" _  _  _  _  _  _  _  _  _ "+
						"|_ |_ |_ |_ |_ |_ |_ |_ |_ " + 
						" _| _| _| _| _| _| _| _| _|" +
						"                           ";
		
		assertEquals("555555555 AMB ['555655555', '559555555']", accountReconciler.reconcileAccountFor(fives).toString());
	}
	
	
	@Test
	public void testReconcileForAllSixes() throws Exception
	{
		String sixes = 	" _  _  _  _  _  _  _  _  _ " +
						"|_ |_ |_ |_ |_ |_ |_ |_ |_ " +
						"|_||_||_||_||_||_||_||_||_|" +
						"                           ";
		
		assertEquals("666666666 AMB ['666566666', '686666666']", accountReconciler.reconcileAccountFor(sixes).toString());
	}
	
	
	@Test
	public void testReconcileForAllNines() throws Exception
	{
		String nines =  " _  _  _  _  _  _  _  _  _ " +
						"|_||_||_||_||_||_||_||_||_|" +
						" _| _| _| _| _| _| _| _| _|" +
						"                           ";
		
		assertEquals("999999999 AMB ['899999999', '993999999', '999959999']", accountReconciler.reconcileAccountFor(nines).toString());
	}
	
	@Test
	public void testReconcileForAmbivalentAccount() throws Exception
	{
	    String ambivalent =	"    _  _  _  _  _  _     _ " +
	    					"|_||_|| || ||_   |  |  ||_ " +
	    					"  | _||_||_||_|  |  |  | _|" +
	    					"                           ";
	    assertEquals("490067715 AMB ['490067115', '490067719', '490867715']", accountReconciler.reconcileAccountFor(ambivalent).toString());
	}
	
	@Test
	public void testReconcileForMissingZero() throws Exception
	{
		 String missingZero = 	" _     _  _  _  _  _  _    " +
		 						"| || || || || || || ||_   |" +
		 						"|_||_||_||_||_||_||_| _|  |" +
		 						"                           ";
		 assertEquals("000000051", accountReconciler.reconcileAccountFor(missingZero).toString());
	}
	
	@Test
	public void testReconcileForValidAccountNumber() throws Exception
	{
		 String validAccount = 	" _  _  _  _  _  _  _  _    " +
		 						"| || || || || || || ||_   |" +
		 						"|_||_||_||_||_||_||_| _|  |" +
		 						"                           ";
		 assertEquals("000000051", accountReconciler.reconcileAccountFor(validAccount).toString());
	}
	
	@Test
	public void testShouldBeOneThroughNine() throws Exception
	{
		String oneThroughNine = "    _  _     _  _  _  _  _ " +
								" _| _| _||_||_ |_   ||_||_|" +
								"  ||_  _|  | _||_|  ||_| _|" +
								"                           ";
		assertEquals("123456789", accountReconciler.reconcileAccountFor(oneThroughNine).toString());
	}
	
	@Test
	public void testMissingFive() throws Exception
	{
		String missingFive =  "    _  _  _  _  _  _     _ " +
							  "|_||_|| ||_||_   |  |  | _ " +
							  "  | _||_||_||_|  |  |  | _|" +
							  "                           ";
		assertEquals("490867715", accountReconciler.reconcileAccountFor(missingFive).toString());
	}
	
}
