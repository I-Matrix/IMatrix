package com.imatrix.backend.services.amazon;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

@Service
public class AmazonClientImpl implements AmazonClient {

	private AmazonS3 s3client;
	
	@Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    
	@Value("${amazonProperties.bucketName}")
    private String bucketName;
    
	@Value("${amazonProperties.accessKey}")
    private String accessKey;
    
	@Value("${amazonProperties.secretKey}")
    private String secretKey;
	
    @PostConstruct
    private void initalizeAccount() {
		AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
		this.s3client = new AmazonS3Client(credentials);
    }
    
    
    public void createBucket(String bucketName) {
    	if(s3client.doesBucketExist(bucketName)) {
    		System.err.println("BUCKET ALREADY EXISTS");
    		return;
    	}
    	s3client.createBucket(new CreateBucketRequest(bucketName));
    }
    
    public void printBuckets() {
    	List<Bucket> buckets = s3client.listBuckets();
    	for(Bucket bucket: buckets) {
    		System.out.println(bucket.getName());
    	}
    }
    public void uploadFileToBucket(String bucket, String fileName, File file) {
    	this.s3client.putObject(new PutObjectRequest(
    							 bucket, 
    							 fileName,
    							 file
    						   ));
    }
    
    public void deleteFileToBucket(String fileName, String bucket) {
    	this.s3client.deleteObject(new DeleteObjectRequest(
    								bucket,
    								fileName
    							  ));
    }
    
    public String downloadFileFromBucket(String bucket, String fileName) throws IOException {    	
    	GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, fileName);
    	URL url = s3client.generatePresignedUrl(request);
    	return url.toString();
    }
    
}
