package com.pillar.katabankocr.account.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.pillar.katabankocr.parser.AccountNumberParser;
import com.pillar.katabankocr.account.reader.AccountFileReader;


@ContextConfiguration(locations = {"classpath:app-context.xml"})
public class AccountFileReaderTests extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	@Qualifier("accountfilereader")
	private AccountFileReader accountFileReader;
	
	@Autowired
	@Qualifier("accountnumberparser")
	private AccountNumberParser accountNumberParser;
	
	
	@Test
	public void testAccountFileReader() throws Exception
	{
		List<String> accountNumbers = accountFileReader.parseAccountFileToList("classpath:accounts-uc1.txt");
		assertFalse(accountNumbers.isEmpty());
		assertEquals(10, accountNumbers.size());
		assertEquals("000000000", accountNumberParser.parseAccountFor(accountNumbers.get(0)));
		assertEquals("111111111", accountNumberParser.parseAccountFor(accountNumbers.get(1)));
		assertEquals("222222222", accountNumberParser.parseAccountFor(accountNumbers.get(2)));
		assertEquals("333333333", accountNumberParser.parseAccountFor(accountNumbers.get(3)));
		assertEquals("444444444", accountNumberParser.parseAccountFor(accountNumbers.get(4)));
		assertEquals("555555555", accountNumberParser.parseAccountFor(accountNumbers.get(5)));
		assertEquals("666666666", accountNumberParser.parseAccountFor(accountNumbers.get(6)));
		assertEquals("777777777", accountNumberParser.parseAccountFor(accountNumbers.get(7)));
		assertEquals("888888888", accountNumberParser.parseAccountFor(accountNumbers.get(8)));
		assertEquals("999999999", accountNumberParser.parseAccountFor(accountNumbers.get(9)));
	}

}
