package com.incedo.api.action;

import java.io.File;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.incedo.api.aws.dyndb.AmazonDynDBAPI;
import com.incedo.api.aws.s3.AmazonS3API;
import com.incedo.api.aws.sns.AmazonSNSAPI;
import com.incedo.api.common.CommonUtil;
import com.incedo.api.model.HandlerRequest;
import com.incedo.api.model.HandlerResponse;
import com.incedo.api.model.ImageMetadata;

public class ImageProcessAction 
{
	public static HandlerResponse invokeAction(LambdaLogger logger, HandlerRequest handlerRequest)
	{
		HandlerResponse handlerResponse = new HandlerResponse();
		try
		{
			//Decoding base64 text to file
    		File file = CommonUtil.base64Decode(logger, handlerRequest.getBody(), handlerRequest.getFilename());

    		//Upload image file to S3 bucket
			AmazonS3API.uploadImage(logger, handlerRequest, file);
    		
			//Fetch Image metadata from S3
			ImageMetadata iMetadata = AmazonS3API.fetchImageFromS3(logger, handlerRequest.getFilename());
			    		 	
			//Save Image Metadata to DynamoDB			
			AmazonDynDBAPI.getMapper().save(iMetadata);
            
            //Send the acknowledge SMS
			if(null != handlerRequest.getMobile())
				AmazonSNSAPI.sendMesssage(handlerRequest.getMobile(), "Hi, Your Image Processed Successfully to S3 Bucket and DynamoDB !", logger);     
                        
            handlerResponse.setStatusCode(200);
            handlerResponse.setMessage("Image Processed Succefully !");
		}
		catch(Exception e)
		{            
            handlerResponse.setStatusCode(500);
            handlerResponse.setErrorMessage("Error while processing image...");
    		return handlerResponse;
		}		
		return handlerResponse;
	}
}
