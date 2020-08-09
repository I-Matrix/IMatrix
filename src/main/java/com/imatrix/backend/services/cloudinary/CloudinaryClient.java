package com.imatrix.backend.services.cloudinary;

import java.io.File;
import java.util.Map;

import com.imatrix.backend.util.image.Image;

public interface CloudinaryClient {

	public Map uploadFile(File cloudinaryFile, Image image);
	
}
