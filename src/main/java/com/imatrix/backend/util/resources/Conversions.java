package com.imatrix.backend.util.resources;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Conversions</h1>
 * <p>
 * 	All conversions from lists
 * 	to primitive arrays used
 * </p>
 *
 * <p>
 * This is the main function of this
 * utility is in order to aid converting
 * from primitive arrays to lists
 * </p>
 *
 *
 * @author  Amanuel
 * @version 0.1
 * @since   2020-08-20
 */

public class Conversions {

	/**
	 * Multi Dimens arraylist of doubles
	 * to a primitive multi dimens array
	 * of doubles
	 * @param arr Arraylist to convert
	 * @return the primitive array
	 */
	public static double[][] ListDoubletodouble(ArrayList<List<Double>> arr){
		double[][] ret = new double[arr.size()][];
		for(int i=0; i<arr.size(); i++) {
			double[] target = new double[arr.get(i).size()];
			 for (int j = 0; j < target.length; j++) {
				target[j] = arr.get(i).get(j);
			 }
			 ret[i] = target;
		}
		return ret;
	}

	/**
	 * Multi dimens array of doubles
	 * to an multi arraylist of doubles
	 * @param arr primitive double array
	 * @return the arraylist
	 */
	public static ArrayList<List<Integer>> doubleArrToList(Double[][] arr){
		ArrayList<List<Integer>> ret = new ArrayList<List<Integer>>();
		for(int i=0; i<arr.length; i++) {
			ArrayList<Integer> target = new ArrayList<Integer>();
			for(int j=0; j<arr[i].length; j++) {
				target.add((Integer)arr[i][j].intValue());
			}
			ret.add(target);
		}
		return ret;
	}

	/**
	 * Multi Dimens arraylist of doubles
	 * to a primitive multi dimens array
	 * of doubles
	 * @param arr Arraylist to convert
	 * @return the primitive array
	 */
	public static Double[][] listIntegerToMatrix(ArrayList<List<Integer>> arr) {
		Double[][] ret = new Double[arr.size()][];
		for(int i=0; i<arr.size(); i++) {
			Double[] target = new Double[arr.get(i).size()];
			 for (int j = 0; j < target.length; j++) {
			    target[j] = arr.get(i).get(j).doubleValue();               
			 }
			 ret[i] = target;
		}
		return ret;
	}

	/**
	 * Conversion between
	 * primitive to object
	 * types
	 * @param arr array of Doubles
	 * @return an array of doubles
	 */
	public static double[][] Doubletodouble(Double[][] arr){
		double[][] ret = new double[arr.length][];
		for(int i=0; i<arr.length; i++) {
			double[] target = new double[arr[i].length];
			for(int j=0; j<arr[i].length; j++) {
				target[j] = arr[i][j];
			}
			ret[i] = target;
		}
		return ret;
	}

	/**
	 * Conversion between
	 * primitive to object
	 * types
	 * @param arr array of doubles
	 * @return an array of Doubles
	 */
	public static Double[][] doubletoDouble(double[][] arr){
		Double[][] ret = new Double[arr.length][];
		for(int i=0; i<arr.length; i++) {
			Double[] target = new Double[arr[i].length];
			for(int j=0; j<arr[i].length; j++) {
				target[j] = arr[i][j];
			}
			ret[i] = target;
		}
		return ret;
	}
	
}
