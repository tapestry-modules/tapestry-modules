package com.trsvax.modules.components.pom;

import org.apache.maven.model.Contributor;
import org.apache.maven.model.Developer;
import org.apache.maven.model.License;
import org.apache.maven.model.MailingList;
import org.apache.maven.model.Model;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

public class POMDisplay {
	
	@Property
	@Parameter
	private Model model;

	
	@Property
	private Contributor contributor;
	
	@Property
	private Developer developer;
	
	@Property
	private License license;
	
	@Property
	private MailingList mailingList;

}
