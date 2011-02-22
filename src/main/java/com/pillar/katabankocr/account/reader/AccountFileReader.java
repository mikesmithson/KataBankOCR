package com.pillar.katabankocr.account.reader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.pillar.katabankocr.account.AccountPropertiesEnum;

public class AccountFileReader implements ResourceLoaderAware {
	private ResourceLoader resourceLoader;
	
	public List<String> parseAccountFileToList(String accountFileResource) throws IOException {
		Resource resource = resourceLoader.getResource(accountFileResource);
		LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(resource.getInputStream()));
		List<String> listofAccounts = new ArrayList<String>();
		String lineOfText = null;
		try
		{
			StringBuilder accountNumberBuilder = new StringBuilder();
			while((lineOfText = lineNumberReader.readLine()) != null)
			{
				accountNumberBuilder.append(lineOfText);
				if(isFinishedReadingAccountNumber(lineNumberReader))
				{
				    listofAccounts.add(accountNumberBuilder.toString());
					accountNumberBuilder.setLength(0);
					continue;
				}
			}
		}
		finally
		{ 
			lineNumberReader.close();
		}
		return listofAccounts;
	}


	private boolean isFinishedReadingAccountNumber(LineNumberReader lineNumberReader) {
		return lineNumberReader.getLineNumber() % AccountPropertiesEnum.NUMBER_OF_LINES_PER_ACCOUNT_NUMBER.getValue() == 0;
	}


	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
}
