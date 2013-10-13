package com.trsvax.modules.services;

import java.util.Map;

import com.trsvax.modules.TapestryModule;

public interface GitHub {
	
	public Map<String,TapestryModule> getModules();
	public Map<String,TapestryModule> getCachedModules();
	public TapestryModule getModule(String name);

}
