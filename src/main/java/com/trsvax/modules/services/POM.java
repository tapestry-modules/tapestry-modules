package com.trsvax.modules.services;

import java.io.Reader;

import org.apache.maven.model.Model;

public interface POM {

	public Model getModel(Reader reader);
}
