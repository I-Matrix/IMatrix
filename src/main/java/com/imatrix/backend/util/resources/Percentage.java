package com.imatrix.backend.util.resources;

import com.imatrix.backend.util.image.Image;

public class Percentage {

	
	private Percentage() {}
	
	/**
	 * 
	 * @param percentage : (percentage)/100
	 * @return k to optimize to
	 */
	public static double convertPercentageToK(Image image,double percentage) {
		double height= image.getHeight();
		double width = image.getWidth();
		double total = height*width;
		// 1000 * 1000
		//2kn 2(200) * 1000
		//mxn
		for(double k=0; k<height; k++) {
		  if(inRange((2*k*width)/total,(percentage/100),0.05)) {
			  System.out.println(k);
			  return k;
		  }
		}
		System.out.println("DIDNT HIT");
		return 0;
//		return (((percentage/100)*(height*width))/(2*width));
		
	}
	
	private static boolean inRange(double n1,double n2, double offset) {
		return (n1 <= n2+offset && n1 >= n2-offset);
	}
}
