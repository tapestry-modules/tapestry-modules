package com.trsvax.modules.services;

import java.io.IOException;
import java.io.Reader;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.slf4j.Logger;

public class POMImpl implements POM {
	
	private Logger logger;
	
	public POMImpl(Logger logger) {
		this.logger = logger;
	}

	public Model getModel(Reader reader) {
		try {
		    MavenXpp3Reader xpp3Reader = new MavenXpp3Reader();
		    return xpp3Reader.read(reader);
		} catch (Exception e) {
		    logger.warn("Read failed {}",e.getMessage());
		} finally {
		    try {
				reader.close();
			} catch (IOException e) {
				logger.warn("close failed {}",e.getMessage());
			}
		}
		return null;
	}

}
