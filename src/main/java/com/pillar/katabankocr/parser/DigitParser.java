package com.pillar.katabankocr.parser;

import java.util.Map;

public class DigitParser {

	private Map<String, String> digitLookupMap;
	
	public String parseDigitFor(String digit) {
		
		return digitLookupMap.containsKey(String.valueOf(digit.hashCode())) 
				?  digitLookupMap.get(String.valueOf(digit.hashCode()))
				:  "?";
	}
	
	public void setDigitLookupMap(Map digitLookupMap)
	{
		this.digitLookupMap = digitLookupMap;
	}
}
