package com.imatrix.web.application;


import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.imatrix.backend.services.amazon.AmazonClientImpl;
import com.imatrix.web.controllers.CustomCtrl;
import com.imatrix.web.controllers.HomeCtrl;
import com.imatrix.web.controllers.UploadCtrl;

import static com.imatrix.backend.util.resources.Constants.UPLOAD_DIR;

import java.io.File;;


@SpringBootApplication(scanBasePackageClasses = {HomeCtrl.class, UploadCtrl.class, AmazonClientImpl.class, CustomCtrl.class})
public class ImatrixApplication {

	
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
		SpringApplication.run(ImatrixApplication.class, args);
	}

}
