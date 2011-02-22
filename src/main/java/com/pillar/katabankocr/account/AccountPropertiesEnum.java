package com.pillar.katabankocr.account;

public enum AccountPropertiesEnum {
	NUMBER_OF_LINES_PER_ACCOUNT_NUMBER(4),
	CHARACTER_WIDTH_PER_DIGIT(3),
	NUMBER_OF_DIGITS_PER_ACCOUNT_NUMBER(9),
	NUMBER_OF_CHARACTERS_PER_LINE(27);
	
	
	private final int value;
	private AccountPropertiesEnum(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
}


