package com.trsvax.modules.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.slf4j.Logger;

import com.trsvax.modules.TapestryModule;

public class GitHubImpl implements GitHub {
	
	private final ContentsService contentsService;
	private final RepositoryService repositoryService;
	private final Logger logger;
	
	Map<String,TapestryModule> modules = new HashMap<String,TapestryModule>();
	
	public GitHubImpl(ContentsService contentsService, RepositoryService repositoryService, Logger logger) {
		this.contentsService = contentsService;
		this.repositoryService = repositoryService;
		this.logger = logger;
	}

	public Map<String,TapestryModule> getModules() {
		modules.clear();
		try {
			List<Repository> repositories = repositoryService.getRepositories("tapestry-modules");
			
			for ( Repository repository : repositories ) {
				if ( "list".equals(repository.getName()) ) {
					for ( RepositoryContents content : getContents(repository)) {
						if ( "dir".equals(content.getType())) {							
							modules.put(content.getName(), getModuleContent(repository, content));
						}
					}
				}
			}
			
		} catch (IOException e) {
			logger.warn("can't get modules {}",e.getMessage());
		}
		
		return modules;
	}

	
	private List<RepositoryContents> getContents(Repository repository) throws IOException {
		return contentsService.getContents(repository);
	}
	
	private TapestryModule getModuleContent(Repository repository, RepositoryContents content) throws IOException {
		TapestryModule module = new TapestryModule();
		module.setName(content.getName());
		List<RepositoryContents> contents = contentsService.getContents(repository, content.getPath() );
		for ( RepositoryContents c : contents ) {
			logger.info("content {}",c.getPath());
			if ( c.getName().equals("description.markdown")) {
				List<RepositoryContents> description = contentsService.getContents(repository, c.getPath() );
				module.setDescription(decode(description.get(0).getContent()));
			}
			if ( c.getName().equals("snapshots")) {
				List<RepositoryContents> pom = contentsService.getContents(repository, c.getPath() );
				module.getPoms().put("snapshots", decode(pom.get(0).getContent()));
			}
			if ( c.getName().equals("src.pom")) {
				List<RepositoryContents> pom = contentsService.getContents(repository, c.getPath() );
				module.getPoms().put("src", decode(pom.get(0).getContent()));
			}
		}
		return module;
	}
	
	private String decode(String s) {
		return StringUtils.newStringUtf8(Base64.decodeBase64(s));
	}

	public Map<String, TapestryModule> getCachedModules() {
		if ( modules.size() == 0 ) {
			getModules();
		}
		return modules;
	}

	public TapestryModule getModule(String name) {
		return getCachedModules().get(name);
	}
}
