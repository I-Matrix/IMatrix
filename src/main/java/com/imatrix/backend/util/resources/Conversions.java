package com.imatrix.backend.util.resources;

import java.util.ArrayList;
import java.util.List;

public class Conversions {
	
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
	
	//Double[][] -> ArrayList<List<Integer>>
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
	
	public static Double[][] listIntegerToMatrix(ArrayList<List<Integer>> arr) {
		Double[][] ret = new Double[arr.size()][];
		System.out.println("In Conversion");
		for(int i=0; i<arr.size(); i++) {
			Double[] target = new Double[arr.get(i).size()];
			 for (int j = 0; j < target.length; j++) {
			    target[j] = arr.get(i).get(j).doubleValue();               
			 }
			 ret[i] = target;
		}
		return ret;
	}
	
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
