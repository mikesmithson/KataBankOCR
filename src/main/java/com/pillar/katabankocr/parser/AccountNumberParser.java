package com.pillar.katabankocr.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.pillar.katabankocr.account.AccountPropertiesEnum;


public class AccountNumberParser {
	@Autowired
	@Qualifier("digitparser")
	private DigitParser digitParser;
	
	public String parseAccountFor(String accountNumber) {
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < AccountPropertiesEnum.NUMBER_OF_DIGITS_PER_ACCOUNT_NUMBER.getValue(); i++)
		{
			StringBuilder accountDigitBuilder = new StringBuilder();
			for (int j = 0 ; j <= 2* AccountPropertiesEnum.NUMBER_OF_CHARACTERS_PER_LINE.getValue(); j += AccountPropertiesEnum.NUMBER_OF_CHARACTERS_PER_LINE.getValue())
			{
				accountDigitBuilder.append(accountNumber.substring(AccountPropertiesEnum.CHARACTER_WIDTH_PER_DIGIT.getValue() * i + j, AccountPropertiesEnum.CHARACTER_WIDTH_PER_DIGIT.getValue() * (i + 1) + j));
			}
			result.append(digitParser.parseDigitFor(accountDigitBuilder.toString()));
		}
		
		return result.toString();
	}
	
}
