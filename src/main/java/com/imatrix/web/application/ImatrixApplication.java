package com.imatrix.web.application;


import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.imatrix.backend.services.amazon.AmazonClientImpl;
import com.imatrix.web.controllers.CustomCtrl;
import com.imatrix.web.controllers.HomeCtrl;
import com.imatrix.web.controllers.UploadCtrl;

import static com.imatrix.backend.util.resources.Constants.UPLOAD_DIR;

import java.io.File;;

/**
 * <h1>IMatrixApplication</h1>
 * <p>
 * 	Main function entry
 * 	of the entire application
 * </p>
 *
 * <p>
 * 	This is the base method for
 * 	this application / entry/
 * </p>
 *
 *
 * @author  Amanuel Bogale
 * @version 0.1
 * @since   2020-08-20
 */

@SpringBootApplication(scanBasePackageClasses = {HomeCtrl.class, UploadCtrl.class, AmazonClientImpl.class, CustomCtrl.class})
public class ImatrixApplication {

	/**
	 * Creates or Uses the tmp directory
	 * used by IMatrix.
	 *
	 * Currently supports UNIX and Windows
	 * Systems
	 * @param args N/A
	 */
	public static void main(String[] args) {
		File uploadDir = new File(UPLOAD_DIR);
		if(!uploadDir.exists()) {
			if(new File(UPLOAD_DIR).mkdir() == false) {
				System.err.println("FAILED CREATING UPLOAD_DIR!!");
				return;
			} else {
				System.out.println("SUCESSFULLY CREATED UPLOAD DIR!!");
			}
		}
		SpringApplicationBuilder builder = new SpringApplicationBuilder(ImatrixApplication.class);

		builder.headless(false);

		ConfigurableApplicationContext context = builder.run(args);
	}

}
