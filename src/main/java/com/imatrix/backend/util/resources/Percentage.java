package com.imatrix.backend.util.resources;

import com.imatrix.backend.util.image.Image;

/**
 * <h1>Percentage</h1>
 *
 * <p>
 * This is a utility class in order to
 * convert K Values to percentages and
 * other way around.
 * </p>
 *
 *
 * @author  Amanuel
 * @version 0.1
 * @since   2020-08-20
 */

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
        percentage+=0.20;
        return helpConvert(height, width, percentage);
    }
    public static double helpConvert(double h, double w, double percentage){
        return (percentage*h*w)/(h+w);
    }

	/**
	 * Convert with an acceptable offset
	 * or margin of error
	 * @param image image that is being converted
	 * @param percentage percentage to find
	 * @param offset M.O.E
	 * @return k value of that percentage
	 */
	public static double convertPercentageToKOffset(Image image, double percentage, double offset) {
		double height= image.getHeight();
		double width = image.getWidth();
		double total = height*width;
		// 1000 * 1000
		// 2kn 2(200) * 1000
		// mxn
		for(double k=0; k<height; k++) {
		  if(inRange((2*k*width)/total,(percentage/100),0.05))
			  return k;
		}
		return 0;
	}
	
	private static boolean inRange(double n1,double n2, double offset) {
		return (n1 <= n2+offset && n1 >= n2-offset);
	}


	/**
	 * Convert a percentage to K value with
	 * a margin of error as minimal as possible
	 * @param m num of cols
	 * @param n num of rows
	 * @param percentage percentage to convert to K
	 * @return K value converted
	 */
	//m = width
	//n = height
	public static double convertFromDimensionToK(double m, double n, double percentage) {
        if(m>n) return convertFromDimensionToK(n,m, percentage);
        return (m*percentage);
	}
	
}
