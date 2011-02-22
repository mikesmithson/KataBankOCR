package com.pillar.katabankocr.account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.pillar.katabankocr.validator.AccountValidator;

public class Account {

	@Autowired
	@Qualifier("accountvalidator")
	private AccountValidator accountValidator;

	private String accountNumber;
	private List<String> reconciledAccountArray = new ArrayList<String>();

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setReconciledAccountArray(List<String> reconciledAccountArray) {
		this.reconciledAccountArray = reconciledAccountArray;
	}
	
	public List<String> getReconciledAccountArray()
	{
		Collections.sort(reconciledAccountArray, new Comparator<String>(){
			public int compare(String accountOne, String accountTwo)
			{
				if(accountNumberOneIsGreaterThanAccountNumberTwo(accountOne, accountTwo))
				{
					return 1;
				}
				else if(!accountNumberOneIsGreaterThanAccountNumberTwo(accountOne, accountTwo))
				{
					return -1;
				}
				else
				{
					return 0;
				}
			}

			private boolean accountNumberOneIsGreaterThanAccountNumberTwo(String accountOne, String accountTwo) {
				return Integer.parseInt(accountOne) > Integer.parseInt(accountTwo);
			}
			
		});
		return reconciledAccountArray;
	}

	public String getAccountStauts() {
		if (this.getAccountNumber().contains("?")) {
			return AccountStatusEnum.ILLEGIBLE.status();
		} else if (!accountValidator.validateAccountFor(this.getAccountNumber()) && reconciledAccountArray.isEmpty()) {
			return AccountStatusEnum.ERROR.status();
		} else if (!accountValidator.validateAccountFor(this.getAccountNumber()) && reconciledAccountArray.size() > 1) {
			return AccountStatusEnum.AMBIVALENT.status();
		} else {
			return AccountStatusEnum.VALID.status();
		}

	}

	public boolean isValid() {
		return StringUtils.trimToNull(this.getAccountStauts()) == null;
	}

	public String toString() {
		return new StringBuilder().append(this.getAccountNumber())
				.append(" ")
				.append(this.getAccountStauts())
				.append(this.getAccountStauts().equals(AccountStatusEnum.AMBIVALENT.status()) ? this.formatReconciledAccounts() : "")
				.toString()
				.trim();
	}

	private String formatReconciledAccounts()
	{
		
		StringBuilder reconciledAccountStringBuilder = new StringBuilder(" ").append('[');
		for (String possibleAccount : this.getReconciledAccountArray()) {
			reconciledAccountStringBuilder.append("'").append(possibleAccount.toString()).append("', ");
		}
		reconciledAccountStringBuilder.replace(reconciledAccountStringBuilder.lastIndexOf(","), reconciledAccountStringBuilder.lastIndexOf(",") + 2, "");
		reconciledAccountStringBuilder.append(']');
		return reconciledAccountStringBuilder.toString();
	}
}
