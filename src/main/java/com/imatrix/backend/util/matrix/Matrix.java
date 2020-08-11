package com.imatrix.backend.util.matrix;

 
public class Matrix {
	

	private Jama.Matrix matrix; //Non Compressed Matrix
	private int numCols, numRows; //Columns and Rows
	
	
	///// SVD Matricies
	private double[][] U;  //U Matrix
	private double[][] V;  //V Matrix
	private double[][] AP; //New Compressed Matrix
	private Integer k; // Compressing factor
	
	public double[][] getU() {
		return U;
	}

	public double[][] getV() {
		return V;
	}

	public double[][] getAP() {
		return AP;
	}

	public Jama.Matrix getMatrix(){
		return this.matrix;
	}
	
	//Forms a matrix out o
	public Matrix(double[][] array) {
		this.matrix = new Jama.Matrix(array);
		try {
			this.numCols = array[0].length;
			this.numRows = array.length;
			this.k = Math.min(this.numCols, this.numRows);
			this.compressWithK(this.k);
		}catch (NullPointerException e){
			System.out.println("You have provided an empty or null array to the Marix constructor");
		}
	}
	
	/**
	 * {@summary} : A = omega_1u_1v_1' + ... + omega_au_av_a', assuming that a = min(m,n)
	 *              A = (omega_1u_1)v_1' + ... + (omega_au_a)v_a', let w_i = omega_iu_i
	 *              (*) A = w_1v_1' + ... + w_av_a', assume that k < a then we can find a good 
	 *              approximation for A by removing the least important (a - k) terms that are at the tail of (*)
	 *              and keeping the most important k terms that are at the beginning of (*). Lets call the approximation AP
	 *              (**) AP = w_1v_1' + ... w_kv_k'
	 *              Instead of evaluating the right hand side of (**) we can store the left hand-side terms and evaluate AP later when we need it. 
	 *              we store the left had side in this.U and this.V, where this.U = [w_1, ... , w_k] and this.V = [v_1, ...,v_k].
	 *             	Notice that AP = this.U * this.V.transpose() (we do this in this.reConstruct())
	 *              
	 *              
	 *              
	 * @param k   : Number of singular values
	 *
	 */
	private void compressWithK(int k){
		Jama.SingularValueDecomposition svd = new Jama.SingularValueDecomposition(matrix);
		double S = svd.getSingularValues();;
		Jama.Matrix V = svd.getV();
		Jama.Matrix U = svd.getU();
		
		//saving omega_1u_1 + ... + omega_ku_k to retU in a numsRows X k matrix
		double[][] retU = new double[numRows][k];
		for(int i = 0; i < k; i ++) {
			for(int j = 0; j < numRows; j ++) {
				retU[j][i] = S[i] * U.get(j, i);
			}
		}
		this.U = retU.clone();
		//saving v_1 + ... + v_k to retV in a numCols X k matrix
		double[][] retV = new double[numCols][k];
		for(int i = 0; i < k; i ++) {
			for(int j = 0; j < numCols; j ++) {
				retV[j][i] = V.get(j, i);
			}
		}
		this.V = retV.clone();
	}
	
	
	public void printMatrix() {
		for(int i = 0; i < this.AP.length; i ++) {
			for(int j = 0; j < this.AP[i].length; j ++) {
				System.out.print( " | " + this.AP[i][j]);
			}
			System.out.println();
		}
	}
	
	
	/**
	 * {@summary} : Sets AP Matrix to newly
	 *              compressed Matrix via 
	 *              k singular values
	 *              
	 * @param k   : Number of singular values
	 *
	 */
	private void AP(int k){
		Jama.Matrix uTemp;
		Jama.Matrix vTemp;
		
		//this.compressWithK(k);
		uTemp = new Jama.Matrix((this.U));
		vTemp = new Jama.Matrix((this.V));
		//uTemp.getma
		uTemp = uTemp.getMatrix(0, this.numRows - 1, 0, k - 1); // saving the first k columns of U
		vTemp = vTemp.getMatrix(0, this.numCols - 1, 0, k - 1); // saving the first k columns of V
		this.AP = uTemp.times(vTemp.transpose()).getArray();
	}
	/**
	 * {@summary} : Compresses this.matrix and returns U and V
	 * 				To see how the compression is done look at 
	 *              this.compressWith().
	 *              
	 *              
	 * @param k   : Number of singular values or rank
	 *
	 */
	
	public double[][] reConstruct(int k){
		if(k <= this.numCols && k <= this.numRows && k > 0) {
			this.AP(k);
			return this.AP;
		}else {
			throw new IllegalStateException("Invalid compressing factor. K should be greater thatn zero and less than the dimensions of the matrix ");
		}
		
	}
}
