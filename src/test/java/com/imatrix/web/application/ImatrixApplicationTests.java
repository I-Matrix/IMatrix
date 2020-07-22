package com.imatrix.web.application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import com.imatrix.backend.*;
import com.imatrix.backend.util.image.Image;
import com.imatrix.backend.util.resources.Constants;

@SpringBootTest
class ImatrixApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	public void testImage() {
		/* Variable Declarations */

		/** SET PATHS! **/ 
	    String home = System.getProperty("user.home");
	    String fileNameRead = "tom";
	    String fileNameWrite = "updated";
	    
		Image image = new Image(home+"/Downloads/" + fileNameRead + ".jpg");
		
		System.out.println("About to compress image");
		if(image.compressImage(400) == Constants.SUCCESS) {
			System.out.println("Sucessfully Compressed!");
			if(image.updateImage(image.getPath()) == Constants.SUCCESS) {
				System.out.println("Successfully Updated!");
			} else {
				System.out.println("Unsucessfull in Update");
			}
		}
	}

}
