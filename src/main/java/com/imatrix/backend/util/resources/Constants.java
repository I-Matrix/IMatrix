package com.imatrix.backend.util.resources;

public class Constants {
	
	public final static int SUCCESS = 1;
	public final static int FAILURE = 0;
	public final static String UPLOAD_DIR = System.getProperty("user.home") +"/Downloads/.uploadIMATRIX";
	
	private Constants() {
		throw new AssertionError();
	}
}
