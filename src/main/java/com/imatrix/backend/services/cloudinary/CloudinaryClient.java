package com.imatrix.backend.services.cloudinary;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.amazonaws.AmazonServiceException;
import com.imatrix.backend.util.image.Image;

/**
 * <h1>Cloudinary Client Interface</h1>
 * <p>
 * 	Implementation of this interface is posted
 * 	on the same package renamed 'impl'
 * </p>
 *
 * <p>
 * 	This interface allows users to upload files
 * 	onto the cloudinary hosted database.
 * 	For more info on cloudinary
 *  @see https://cloudinary.com
 * </p>
 *
 *
 * @author  Amanuel Bogale
 * @version 0.1
 * @since   2020-08-20
 * @see     CloudinaryClientImpl
 */

public interface CloudinaryClient {

	/**<p>
	 * This method's main purpose is to upload a file
	 * located under
	 * 	<pre>'{@code System.get(user.home)}/.uploadIMatrix'</pre>
	 * to the cloudniary database specified by the user.
	 * </p>
	 *
	 * @param  cloudinaryFile The file to upload into
	 * 		 the database
	 * @return returns a {@object Cloudinary} map
	 *       which gives several info about the object
	 *       that has been uploaded to the cloudinary
	 *       server
	 *       	@example:
	 * 				   { "public_id":"tquyfignx5bxcbsupr6a",
	 * 				    "version":1375302801,
	 * 				    "signature":"52ecf23eeb987b3b5a72fa4ade51b1c7a1426a97",
	 * 				    "width":1920,
	 * 				    "height":1200,
	 * 				    "format":"jpg",
	 * 				    "resource_type":"image",
	 * 				    "created_at":"2017-07-31T20:33:21Z",
	 * 				    "bytes":737633,
	 * 				    "type":"upload",
	 * 				    "url":
	 * 				        "https://res.cloudinary.com/demo/image/upload/v1375302801/tquyfignx5bxcbsupr6a.jpg",
	 * 				    "secure_url":
	 * 				        "https://res.cloudinary.com/demo/image/upload/v1375302801/tquyfignx5bxcbsupr6a.jpg",
	 * 				    "etag":"1adf8d2ad3954f6270d698                   24"
	 * 				   }
	 *
	 * @throws IOException
	 *         If any errors occurred in uploading the file
	 *         where it didnt exist in the first place
	 *         @exception IOException thrown
	 */
	public Map uploadFile(File cloudinaryFile) throws IOException;
	
}
