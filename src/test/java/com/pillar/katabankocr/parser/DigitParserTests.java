package com.pillar.katabankocr.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.pillar.katabankocr.parser.DigitParser;


@ContextConfiguration(locations = {"classpath:app-context.xml"})
public class DigitParserTests extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	@Qualifier("digitparser")
	private DigitParser digitParser;
	
	
	@Test
	public void testParseZeroDigitRepresentation() throws Exception
	{
		String zero = " _ " +
					  "| |" +
					  "|_|";
		
		assertEquals("0", digitParser.parseDigitFor(zero));
	}
	
	
	@Test
	public void testParseOneDigitRepresentation() throws Exception
	{
		String one = "   " +
					 "  |" +
					 "  |";	
		assertEquals("1", digitParser.parseDigitFor(one));
	}
	
	@Test
	public void testParseTwoDigitRepresentation() throws Exception
	{
		String two = " _ " +
					 " _|" +
					 "|_ ";
		assertEquals("2", digitParser.parseDigitFor(two));
	}
	
	@Test
	public void testParseThreeDigitRepresentation() throws Exception
	{
		String three = 	" _ " +
						" _|" +
						" _|";
		assertEquals("3", digitParser.parseDigitFor(three));
	}
	
	@Test
	public void testParseFourDigitRepresentation() throws Exception
	{
		String four = "   " +
					  "|_|" +
					  "  |";
		assertEquals("4", digitParser.parseDigitFor(four));

	}
	
	@Test
	public void testParseFiveDigitRepresentation() throws Exception
	{
		String five = " _ " +
		              "|_ " +
		              " _|";
		assertEquals("5", digitParser.parseDigitFor(five));
	}
	
	@Test
	public void testParseSixDigitRepresentation() throws Exception
	{
		String six = " _ " +
        			 "|_ " +
        			 "|_|";
		
		assertEquals("6", digitParser.parseDigitFor(six));
	}
	
	@Test
	public void testParseSevenDigitRepresentation() throws Exception
	{
		String seven = 	" _ " +
		 				"  |" +
		 				"  |";
		
		assertEquals("7", digitParser.parseDigitFor(seven));
	}
	
	@Test
	public void testParseEightDigitRepresentation() throws Exception
	{
		String eight = 	" _ " +
		 			 	"|_|" +
		 			 	"|_|";
		assertEquals("8", digitParser.parseDigitFor(eight));		
	}

	
	@Test
	public void testParseNineDigitRepresentation() throws Exception
	{
		String nine = 	" _ " +
						"|_|" +
						" _|";
		assertEquals("9", digitParser.parseDigitFor(nine));
	}
	
	@Test
	public void testParseIllegibleDigitRepresentation() throws Exception
	{
		String illegible = 	" _ " +
					  		"| |" +
					  		"  |";
		
		assertEquals("?", digitParser.parseDigitFor(illegible));
		
	}
	
	
	
}
