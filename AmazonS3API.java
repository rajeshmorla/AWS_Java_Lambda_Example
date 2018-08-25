package com.incedo.api.aws.s3;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.incedo.api.common.AppConstants;
import com.incedo.api.model.HandlerRequest;
import com.incedo.api.model.ImageMetadata;

public class AmazonS3API 
{
	@SuppressWarnings("deprecation")
	static AmazonS3 s3client = new AmazonS3Client(new DefaultAWSCredentialsProviderChain());
	
	public static void uploadImage(LambdaLogger logger, HandlerRequest handlerRequest, File file)
	{
		try
		{
			String keyName = AppConstants.S3_BUCKET_FOLDER+handlerRequest.getFilename();
			
			logger.log("Preparing for S3 upload... S3 Path: "+AppConstants.S3_BUCKET_NAME+keyName);
			
			PutObjectRequest objectRequest = new PutObjectRequest(AppConstants.S3_BUCKET_NAME, keyName, file);				
			objectRequest.setCannedAcl(CannedAccessControlList.PublicRead);		

			logger.log("Uploading to S3 bucket..."); 
			s3client.putObject(objectRequest);

			logger.log("File uploaded to S3...");			
		}
	    catch(AmazonServiceException e) {
	        // The call was transmitted successfully, but Amazon S3 couldn't process 
	        // it, so it returned an error response.
	        e.printStackTrace();
	    }
	    catch(SdkClientException e) {
	        // Amazon S3 couldn't be contacted for a response, or the client
	        // couldn't parse the response from Amazon S3.
	        e.printStackTrace();
	    }
		
	}
	
	
    @SuppressWarnings("deprecation")
	public static ImageMetadata fetchImageFromS3(LambdaLogger logger, String fileName) throws IOException 
    {
    	ImageMetadata iMetadata = new ImageMetadata();
        try 
        {
			String keyName = AppConstants.S3_BUCKET_FOLDER+fileName;
        	S3Object imageFileFromS3 = s3client.getObject(new GetObjectRequest(AppConstants.S3_BUCKET_NAME, keyName)); 
			            
            long fileSizeInKB = imageFileFromS3.getObjectMetadata().getContentLength() / 1024;
            // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
            long fileSizeInMB = fileSizeInKB / 1024;
                     
            iMetadata.setId(UUID.randomUUID().toString());
            iMetadata.setBucketName(imageFileFromS3.getBucketName());
            iMetadata.setContentType(imageFileFromS3.getObjectMetadata().getContentType());
            iMetadata.seteTag(imageFileFromS3.getObjectMetadata().getETag());
            iMetadata.setFileName(fileName);
            iMetadata.setKey(imageFileFromS3.getKey());
            iMetadata.setLastModified(imageFileFromS3.getObjectMetadata().getLastModified().toString());
            iMetadata.setLink("https://s3-ap-southeast-1.amazonaws.com/"+iMetadata.getBucketName()+"/"+iMetadata.getKey());
            iMetadata.setServerSideEncryption(imageFileFromS3.getObjectMetadata().getServerSideEncryption());
            iMetadata.setSize(String.valueOf(fileSizeInMB));
            iMetadata.setStorageClass(imageFileFromS3.getObjectMetadata().getStorageClass());
            iMetadata.setVersionID(imageFileFromS3.getObjectMetadata().getVersionId());
            
        }
        catch(AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process 
            // it, so it returned an error response.
            e.printStackTrace();
        }
        catch(SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }        
        return iMetadata;
    }
}