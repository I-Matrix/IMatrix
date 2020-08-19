package com.imatrix.backend.services.amazon;

import java.io.File;
import java.io.IOException;

import com.amazonaws.AmazonServiceException;

/**
* <h1>Amazon Client Interface</h1>
* <p>
* 	Implementation of this interface is posted
* 	on the same package renamed 'impl'
* </p>
* 
* <p>
* 	This interface allows users to create buckets,
* 	print the list of buckets in that s3 database
* 	upload as well as delete files to a particular bucket
* 	and finally download files from the bucket as 
* 	a String.
* </p>
* 
* <p>
* 	<b>This isn't the full width</b> of all functions
*   the Amazon interface allows us to utilize but only
*   necessary.
* </p>
* 
* @author  Amanuel Bogale
* @version 0.1
* @since   2020-08-20
* @see     AmazonClientImpl
*/
public interface AmazonClient {
	
  /**<p>
   * This method creates a bucket with a specific name
   * user specifies. If a bucket with same name is
   * already created, this will print an warning to the
   * standard error, {@code System.err}
   * </p>
   * 
   * @param  bucketName name of the bucket that will
   * 		 be added as specified up top. 
   * @return returns success if bucket doesnt already exist
   * 		 {@code Constants.SUCCESS} or a failure
   * 		 if otherwise {$code Constants.FAILURE}
   */
	public int createBucket(String bucketName);
	
  /**<p>
   * This method prints all buckets that are
   * available in the s3 database provided in
   * {@value application.yml} with the secret keys
   * {@code Impl.secretKey} and {@code Impl.accessKey}
   * </p>
   * 
   * @param  {@code (void)}
   * @return {@code (void)}
   */
	public void printBuckets();
	
  /**<p>
   * This method's main purpose is to upload a file
   * located under 
   * 	<pre>'{@code System.get(user.home)}/.uploadIMatrix'</pre>
   * to the s3 bucket specified by the user. 
   * </p>
   * 
   * @param  bucketName name of the bucket that will
   * 		 have the file added
   * @param  <b>filename only</b> of the file 
   * 		 being uploaded
   * @param  Specific file under above mentioned
   * 		 path to be uploaded
   * @return returns success if file uploaded successfully
   * 		 to bucket {@code Constants.SUCCESS} or a failure
   * 		 otherwise {@code Constants.FAILURE}
   * 
   * @throws AmazonServiceException
   *         If any errors occurred in Amazon S3 while processing the
   *         request.
   */
	public int uploadFileToBucket(String bucket, String fileName, File file);
	
	
  /**<p>
   * This methods main purpose is to delete
   * a specific file specified by the user
   * from the bucket. File located in :
   * 	<pre>'{@code System.get(user.home)}/.uploadIMatrix'</pre>
   * </p>
   * 
   * @param  bucketName name of the bucket that will
   * 		 have the file added
   * @param  <b>filename only</b> of the file 
   * 		 being uploaded
   * @param  Specific file under above mentioned
   * 		 path to be uploaded
   * @return {@code (void)}
   * 
   * @throws AmazonServiceException
   *         If any errors occurred in Amazon S3 while processing the
   *         request.
   */
	
	public void deleteFileToBucket(String bucket, String fileName);
	
	
  /**<p>
   * This methods main purpose is to download
   * a file from the bucket both specified by
   * the user as @param's
   * </p>
   * 
   * @param  bucketName name of the bucket that will
   * 		 have the file to download from
   * @param  <b>filename only</b> of the file 
   * 		 being downloaded
   * @return returns the string of the path where
   * 		 the file from the bucket has been
   * 		 downloaded to in the main server.
   * 		 Its main location is located:
   * 
   * 				<pre>'{@code System.get(user.home)}/.uploadIMatrix'</pre>
   * 
   * @throws IOException
   *         If any errors occurred of searching for the file
   *         specified by the fileName as a @param.
   */
	public String downloadFileFromBucket(String bucket, String fileName) throws IOException;
	
}
