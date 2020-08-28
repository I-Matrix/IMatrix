package com.imatrix.backend.util.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.imatrix.backend.services.cloudinary.CloudinaryClient;
import com.imatrix.backend.util.resources.*;
import com.imatrix.backend.util.matrix.ColorMatrix;

/**
 * <h1>Image</h1>
 * <p>
 * 	Implementation of an Image
 * </p>
 *
 * <p>
 * 	This is the base Image class
 * 	used throughout the program
 * </p>
 *
 *
 * @author  Amanuel Bogale
 * @version 0.1
 * @since   2020-08-20
 */
public class Image {

	/**
	 * private members
	 */
	private BufferedImage img;
	private int[][] rgbVals;
	
	//No change in resolution hence const;
	private final int width;
	private final int height;
	private final String PATH;
	private final ColorMatrix matrix;
	
	public BufferedImage getImg() {
		return this.img;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public String getPath() {
		return this.PATH;
	}

	/**
	 * Load image from path
	 * (already downloaded)
	 * @param PATH path specified
	 */
	public Image(String PATH) {
	      BufferedImage img = null;
	      File f = null;
	      
	      try {
	    	  f = new File(PATH);
	          img = ImageIO.read(f);
	      } catch (IOException e) {
	            System.out.println(e);
	            System.out.println("Image Not Loaded Correctly");
	      }

	  	this.img = img;
		width = this.img.getWidth();
		height = this.img.getHeight();
		this.convertToRGB();
		this.matrix = new ColorMatrix(this.rgbVals);
		this.PATH = PATH;
	}

	/**
	 * Convert the multi-dimens array
	 * into rgb values
	 */
    private void convertToRGB() {
    	int[][] rgb_Values = new int[width][height];
		for(int i = 0; i < width; i ++) {
			for(int j = 0; j < height; j ++) {
				rgb_Values[i][j] = img.getRGB(i, j);
			}
		}
		this.rgbVals = rgb_Values;
    }

	/**
	 * Returns an rgb at the specific
	 * width/height position
	 * @param width row of index
	 * @param height col of index
	 * @return returns a double[] of rgb in
	 * 		   that order
	 */
    public double[] returnRGBAtPos(int width, int height){
        Integer RGB = (int) this.rgbVals[height][width];
        int blue = RGB & 0xff;
        int green = (RGB & 0xff00) >> 8;
        int red = (RGB & 0xff0000) >> 16;
        return new double[]{red,blue,green};
    }

	/**
	 * Offets the RGB of each pixel
	 * by the amount specified by user
	 * @param offset amount to offset by
	 * @return returns a success or failure
	 * 	 	   depending on the task
	 */
	public int setImageOffset(Integer offset){
    	try {
    		int[][] RGB_Values = new int[this.height][];
	        for(int y=0; y<this.height; y++){
	        	int[] pixelRow = new int[this.width];
	            for(int x=0; x<this.width; x++){
	                Integer RGB = (int) this.rgbVals[y][x];
	                int blue = RGB & 0xff;
	                int green = (RGB & 0xff00) >> 8;
	                int red = (RGB & 0xff0000) >> 16;
	            
	                Integer newRGB = new Color(red+offset, blue+offset, green+offset).getRGB();
	                pixelRow[x] = (newRGB);
	            }
	            RGB_Values[y] = (pixelRow);
	        }
            this.rgbVals = RGB_Values;
    	} catch(Exception err) {
    		System.out.println(err);
    		return Constants.FAILURE;
    	}
        return Constants.SUCCESS;
    }
    
    public void setImageMatrix(int[][] rgbValues) {
        this.rgbVals =  rgbValues.clone();
    }

	/**
	 * Compresses an image using
	 * the Matrix class defined in
	 * IMatrix
	 * @param k K Value to compress it by
	 * @return returns success if successfully compressed
	 * 		   or failure otherwise
	 */
	public int compressImage(int k) {
    	try {
			this.rgbVals = this.matrix.compress(k);
		} catch(Exception e) {
			System.out.println(e);
			return Constants.FAILURE;
		}
		return Constants.SUCCESS;
    }

	/**
	 * Writes the current image in this class
	 * to the path specified by the user
	 * @param Path path specified
	 * @return returns sucess if sucessfully written
	 * 		   or failure otherwise
	 */
	public int updateImage(String Path) {
		for(int i = 0; i < this.width; i ++) {
			for(int j = 0; j < this.height; j ++) {
				img.setRGB(i, j, this.rgbVals[i][j]);
			}
		}
		try {
			File f = new File(Path);
			if(ImageIO.write(img, "jpg", f) == false) {
				return Constants.FAILURE;
			}
		} catch (IOException e) {
			System.out.println(e);
			return Constants.FAILURE;
		}
		return Constants.SUCCESS;
	}

	/**
	 * Print the image in sets of RGB for debugging
	 * purposes
	 * @param dimension dimension(WxH) to print by
	 */
	public void printImageRGB(int dimension){
        // Prints first dimensionxdimension square matrix rgb values 
        for(int y=0; y<dimension; y++){
            for(int x=0; x<dimension; x++){
            	
                /* Combined RGB VALUE */
                Integer RGB = (int) this.rgbVals[y][x];

                /* Evaluate red/blue/green values */
                int blue = RGB & 0xff;
                int green = (RGB & 0xff00) >> 8;
                int red = (RGB & 0xff0000) >> 16;
 
                System.out.print("[" + red + " " + blue + " " + green + "] ");
            }
            System.out.println();
        }
    }
	
}
