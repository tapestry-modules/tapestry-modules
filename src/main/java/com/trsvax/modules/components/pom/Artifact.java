package com.trsvax.modules.components.pom;

import org.apache.maven.model.Model;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.ioc.annotations.Inject;

@SupportsInformalParameters
public class Artifact {
	
	@Parameter
	private Model model;
	
	@Inject
	private ComponentResources resources;
	
	private Element div;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		div = writer.element("pre");
		resources.renderInformalParameters(writer);
		writer.write("<dependency>\n");
		writer.write(String.format("<groupId>%s</groupId>\n", model.getGroupId()));
		writer.write(String.format("<artifactId>%s</artifactId>\n", model.getArtifactId()));
		writer.write(String.format("<version>%s</version>\n", model.getVersion()));
		writer.write("</dependency>\n");
	}
	
	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
	}

}
