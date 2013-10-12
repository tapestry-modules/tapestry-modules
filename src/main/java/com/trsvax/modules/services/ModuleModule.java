package com.trsvax.modules.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.services.LibraryMapping;

public class ModuleModule {
	
	   public static void bind(ServiceBinder binder) {
		   binder.bind(POM.class,POMImpl.class);
	   }
	
    public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration) {		
    	configuration.add(new LibraryMapping("md", "com.trsvax.modules"));	
    	
    }

}
