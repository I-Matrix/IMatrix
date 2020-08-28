package com.imatrix.backend.util.matrix;


/**
 * <h1>ColorMatrix</h1>
 * <p>
 * 	Implementation of Matrix Subclass
 * </p>
 *
 * <p>
 * Given a matrix of color
 * integers(rbg combined in 1 int),
 * this class compress the matrix
 * using compress(k) and
 * returns a matrix of colored
 * integers(rgb combined in 1 int)
 * </p>
 *
 *
 * @author  Abraham
 * @version 0.1
 * @since   2020-08-20
 * @see    com.imatrix.backend.util.matrix.Matrix
 */
public class ColorMatrix {
	/**
	 * private members
	 */
	private double[][] green, blue, red;
	private int numCol, numRow;

	/**
	 * Set up the matrices
	 * and the rgb values
	 * @param matrix
	 */
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

	/**
	 * Compress(reconstruct) the values
	 * using the Matrix class
	 * @param k K Value to compress it by
	 * @return return a multi array of compressed
	 * matrix.
	 */
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
