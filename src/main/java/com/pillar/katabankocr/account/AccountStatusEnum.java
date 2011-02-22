package com.pillar.katabankocr.account;

public enum AccountStatusEnum {
	ILLEGIBLE("ILL"),
	ERROR("ERR"),
	AMBIVALENT("AMB"),
	VALID("   ");

	private final String status;
	
	private AccountStatusEnum(String status)
	{
		this.status = status;
	}
	
	public String status()
	{
		return this.status;
	}
}
