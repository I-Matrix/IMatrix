package com.imatrix.backend.util.resources;

/**
 * <h1>Conversions</h1>
 * <p>
 * 	All string manipulations
 * 	used in this app
 * </p>
 *
 * <p>
 * This is the main class where
 * all string manipulations done
 * are coded
 * </p>
 *
 *
 * @author  Amanuel
 * @version 0.1
 * @since   2020-08-20
 */
public class StringManipulation {

	private static String extension = "png"; //default

	/**
	 * Main function that returns
	 * a final path to download
	 * the images to
	 * @param Path inital path
	 * @param k k value conversion
	 * @return final path
	 */
	// blahblah/blah/example.jpg
	// blahblah/blah/example-k200.jpg
	// blahblah/blah/example-k100.jpg
	public static String finalize(String Path, int k) {
		String NewPath = new String(Path);
		NewPath = removeExtension(NewPath);
		NewPath = addKEnd(NewPath,k);
		NewPath = addExtension(NewPath);
		return NewPath;
	}

	/**
	 * Returns the filename from the full
	 * final path
	 * @param path final path
	 * @return only <b>file name</b>
	 * @throws Exception
	 */
	public static String getFileName(String path) throws Exception {
		int i;
		char deli = ' ';
		
		if(OSValidator.isUnix()) deli = '/';
		else if(OSValidator.isWindows()) deli = '\\';
		else throw new Exception("OS Not SUPPORTED(ONLY UNIX AND Windows Based OS's are supported!)");


		for(i=path.length()-1; path.charAt(i)!=deli; i--) {}
		
		return path.substring(i+1, path.length());
	}


	/**
	 * Private member that
	 * adds K to the end of path
	 * @param Path old path
	 * @param k k value to add
	 * @return newly formed path
	 */
	private static String addKEnd(String Path, Integer k) {
		String newString = new String(Path);
		newString+='k';
		newString+='-';
		newString+=k.toString();
		return newString;
	}

	/**
	 * Private member that
	 * adds extension to the end of path
	 * @param Path old path
	 * @return newly formed path
	 */
	private static String addExtension(String Path) {
		String newString = new String(Path);
		newString += '.';
		newString += extension;
		return newString;
	}

	/**
	 * Private member that
	 * removes extension to the end of path
	 * @param Path old path
	 * @return newly formed path
	 */
	private static String removeExtension(String Path) {
		String newPath = new String();
		String ext = new String();
		int i;
		for(i=0; i<Path.length()-4; i++) 
			newPath+=Path.charAt(i);
		for(i+=1; i<Path.length(); i++) 
			ext+=Path.charAt(i);
		extension = ext;
		return newPath;
	}
}
