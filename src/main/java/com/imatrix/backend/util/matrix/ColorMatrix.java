package com.imatrix.backend.util.matrix;



/**
 * 
 * Given a matrix of color integers(rbg combined in 1 int), this class compress the matrix
 * using compress(k) and returns a matrix of colored integers(rgb combined in 1 int)
 *
 */
public class ColorMatrix {
	private double[][] green, blue, red;
	private int numCol, numRow;
	public ColorMatrix(int[][] matrix)
	{
		this.numRow = matrix.length;
		this.numCol = matrix[0].length;
		this.green = new double[this.numRow][this.numCol];
		this.blue = new double[this.numRow][this.numCol];
		this.red = new double[this.numRow][this.numCol];
		for(int i = 0; i < numRow; i ++) {
			for(int j = 0; j < numCol; j++) {
				blue[i][j] = matrix[i][j] & 0xff;
				green[i][j] = (matrix[i][j] & 0xff00) >> 8;
			red[i][j] = (matrix[i][j] & 0xff0000) >> 16;
			}
		}
	}
	public int[][] compress(int k) {
		int[][] ret = new int[numRow][numCol];
		Matrix gMatrix = new Matrix(green);
		Matrix bMatrix = new Matrix(blue);
		Matrix rMatrix = new Matrix(red);
		green = gMatrix.reConstruct(k);
		blue = bMatrix.reConstruct(k);
		red = rMatrix.reConstruct(k);
		for(int i = 0; i < numRow; i++) {
			for(int j = 0; j < numCol; j++) {
				ret[i][j] = ((int) red[i][j]) << 8;
				ret[i][j] = (ret[i][j] | (int) green[i][j]) << 8;
				ret[i][j] |= (int) blue[i][j];
			}
		}
		return ret;
	}
	
}
