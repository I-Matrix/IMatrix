package com.imatrix.backend.util.resources;

public class StringManipulation {

	// blahblah/blah/example.jpg
	// blahblah/blah/example-k200.jpg
	// blahblah/blah/example-k100.jpg
	private static String extension = "png"; //default
	public static String finalize(String Path, int k) {
		String NewPath = new String(Path);
		NewPath = removeExtension(NewPath);
		NewPath = addKEnd(NewPath,k);
		NewPath = addExtension(NewPath);
		return NewPath;
	}
	
	public static String getFileName(String path) {
		int i;
		for(i=path.length()-1; path.charAt(i)!='\\'; i--) {}
		
		return path.substring(i+1, path.length());
	}
	
	
	private static String addKEnd(String Path, Integer k) {
		String newString = new String(Path);
		newString+='k';
		newString+='-';
		newString+=k.toString();
		return newString;
	}
	private static String addExtension(String Path) {
		String newString = new String(Path);
		newString += '.';
		newString += extension;
		return newString;
	}
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
