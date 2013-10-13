package com.trsvax.modules;

import java.util.HashMap;
import java.util.Map;

public class TapestryModule {
	
	private String description;
	private String name;
	
	private Map<String,String> poms = new HashMap<String, String>();

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getPoms() {
		return poms;
	}

	public void setPoms(Map<String, String> poms) {
		this.poms = poms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		if ( description == null ) {
			return "";
		}
		int beginIndex = description.indexOf("title:") + 6;
		int endIndex = description.indexOf("\n", beginIndex);
		if ( beginIndex == -1 ) {
			return "";
		}
		return description.substring(beginIndex, endIndex);
	}
	
	

}
