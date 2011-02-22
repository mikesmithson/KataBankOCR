package com.pillar.katabankocr.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {"classpath:app-context.xml"})
public class AccountNumberParserTests extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	@Qualifier("accountnumberparser")
	private AccountNumberParser accountNumberParser;
	
	@Test
	public void testAllZeroAccountNumber() throws Exception
	{
		String zeroesAccountNumber = " _  _  _  _  _  _  _  _  _ " +
								 	 "| || || || || || || || || |" +
								 	 "|_||_||_||_||_||_||_||_||_|" + 
								 	 "                           ";
		assertEquals("000000000", accountNumberParser.parseAccountFor(zeroesAccountNumber));
	
	}
	
	@Test
	public void testAllOnesAccountNumber() throws Exception
	{
		String onesAccountNumber  =  "                           " +
									 "  |  |  |  |  |  |  |  |  |" + 
									 "  |  |  |  |  |  |  |  |  |" +
  									 "                           ";
		assertEquals("111111111", accountNumberParser.parseAccountFor(onesAccountNumber ));
	
	}
	
	
	@Test
	public void testAllTwosAccountNumber() throws Exception
	{
		String twos = " _  _  _  _  _  _  _  _  _ " +
					  " _| _| _| _| _| _| _| _| _|" + 
					  "|_ |_ |_ |_ |_ |_ |_ |_ |_ " +
					  "                           ";
		
		assertEquals("222222222", accountNumberParser.parseAccountFor(twos));
		                           
	}
	
	@Test
	public void testAllThreesAccountNumber() throws Exception
	{
		String threes = " _  _  _  _  _  _  _  _  _ " +
						" _| _| _| _| _| _| _| _| _|" +
						" _| _| _| _| _| _| _| _| _|" +
						"                           ";
		assertEquals("333333333", accountNumberParser.parseAccountFor(threes));
	}
	
	@Test
	public void testAllFoursAccountNumber() throws Exception
	{
		String fours = 	"                           " +
						"|_||_||_||_||_||_||_||_||_|" +
						"  |  |  |  |  |  |  |  |  |" +
						"                           ";
		assertEquals("444444444", accountNumberParser.parseAccountFor(fours));
	}
	
	@Test
	public void testAllFivesAccountNumber() throws Exception
	{
		String fives = 	" _  _  _  _  _  _  _  _  _ "+
						"|_ |_ |_ |_ |_ |_ |_ |_ |_ " + 
						" _| _| _| _| _| _| _| _| _|" +
						"                           ";
		
		assertEquals("555555555", accountNumberParser.parseAccountFor(fives));
	}
	
	
	@Test
	public void testAllSixesAccountNumber() throws Exception
	{
		String sixes = 	" _  _  _  _  _  _  _  _  _ " +
						"|_ |_ |_ |_ |_ |_ |_ |_ |_ " +
						"|_||_||_||_||_||_||_||_||_|" +
						"                           ";
		
		assertEquals("666666666", accountNumberParser.parseAccountFor(sixes));
	}
	
	@Test
	public void testAllSevensAccountNumber() throws Exception
	{
		String sevens =  " _  _  _  _  _  _  _  _  _ " +
						 "  |  |  |  |  |  |  |  |  |" +
						 "  |  |  |  |  |  |  |  |  |" +
						 "                           ";
		
		assertEquals("777777777", accountNumberParser.parseAccountFor(sevens));
	}
	
	@Test
	public void testAllEightsAccountNumber() throws Exception
	{
		String eights =  " _  _  _  _  _  _  _  _  _ " +
						 "|_||_||_||_||_||_||_||_||_|" +
						 "|_||_||_||_||_||_||_||_||_|" +
						 "                           ";
		
		assertEquals("888888888", accountNumberParser.parseAccountFor(eights));
	}
	
	@Test
	public void testAllNinesAccountNumber() throws Exception
	{
		String nines =  " _  _  _  _  _  _  _  _  _ " +
						"|_||_||_||_||_||_||_||_||_|" +
						" _| _| _| _| _| _| _| _| _|" +
						"                           ";
		
		assertEquals("999999999", accountNumberParser.parseAccountFor(nines));
	}
	
	@Test
	public void testAccountNumberOneThroughNince() throws Exception
	{
		String oneThroughNine = "    _  _     _  _  _  _  _ " +
								"  | _| _||_||_ |_   ||_||_|" +
								"  ||_  _|  | _||_|  ||_| _|" +
								"                           ";
		assertEquals("123456789", accountNumberParser.parseAccountFor(oneThroughNine));
		
	}
	
	
	
}
