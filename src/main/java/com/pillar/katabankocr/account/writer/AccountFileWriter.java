package com.pillar.katabankocr.account.writer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

import com.pillar.katabankocr.account.Account;


public class AccountFileWriter implements ResourceLoaderAware {
	private ResourceLoader resourceLoader;
	
	public void writeResultsTo(List<Account> listOfAccounts, String outputFile) throws IOException {
		File file = resourceLoader.getResource(outputFile).getFile();
		FileUtils.writeLines(file, listOfAccounts);	
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

}
