package com.imatrix.backend.util.resources;

/**
 * <h1>Constants</h1>
 * <p>
 * 	All constants used in this app
 * </p>
 *
 * @author  Amanuel
 * @version 0.1
 * @since   2020-08-20
 */
public class Constants {
	
	public final static int SUCCESS = 1;
	public final static int FAILURE = 0;
	public final static String UPLOAD_DIR = System.getProperty("user.home") +"/Downloads/.uploadIMATRIX";
	public final static String BUCKET_NAME = "imatrix-999";	
	public       static boolean JAVA_FX_DONE=false;
	public final static String[] Cloud = {
			"dvlthumb9",
            "288979924134751",
            "q99k0lAGcgQYKWd5GWEYOO19VqM"
	};
	
	private Constants() {
		throw new AssertionError();
	}
}
