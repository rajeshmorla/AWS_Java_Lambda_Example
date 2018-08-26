package com.incedo.api.action;

import java.io.File;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
		Gson gson = new Gson();
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
				AmazonSNSAPI.sendMesssage(handlerRequest.getMobile(), "Hi, Image URL: "+iMetadata.getLink(), logger);     
                        
			JsonObject body = new JsonObject();
			body.addProperty("link", iMetadata.getLink());
			
			
			
            handlerResponse.setStatusCode(200);
            //handlerResponse.setBase64Encoded(false);
            handlerResponse.setBody(body.toString());
            handlerResponse.setHeaders(new JsonObject());
		}
		catch(Exception e)
		{            
            handlerResponse.setStatusCode(500);
    		return handlerResponse;
		}		
		return handlerResponse;
	}
}
