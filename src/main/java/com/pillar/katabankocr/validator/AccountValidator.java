package com.pillar.katabankocr.validator;

import org.apache.commons.lang.StringUtils;

public class AccountValidator {
	
	public boolean validateAccountFor(String accountNumber)
	{
		try {
			int checksum = 0;
			char[] accountNumberCharArray = StringUtils.reverse(accountNumber).toCharArray();
			for(int i = 0; i < accountNumberCharArray.length; i++)
			{
				checksum += (i + 1) * Integer.parseInt(new StringBuilder().append(accountNumberCharArray[i]).toString()); 
			}
			return checksum % 11 == 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
