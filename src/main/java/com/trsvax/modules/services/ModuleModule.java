package com.trsvax.modules.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.services.LibraryMapping;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.MarkdownService;
import org.eclipse.egit.github.core.service.RepositoryService;

public class ModuleModule {
	
	   public static void bind(ServiceBinder binder) {
		   binder.bind(POM.class,POMImpl.class);
		   binder.bind(ContentsService.class);
		   binder.bind(RepositoryService.class);
		   binder.bind(MarkdownService.class);
		   
		   binder.bind(GitHub.class,GitHubImpl.class);
	   }
	
    public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration) {		
    	configuration.add(new LibraryMapping("md", "com.trsvax.modules"));	
    	
    }

}
