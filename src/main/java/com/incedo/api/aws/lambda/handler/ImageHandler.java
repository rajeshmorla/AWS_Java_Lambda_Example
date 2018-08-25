package com.incedo.api.aws.lambda.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.incedo.api.action.ImageProcessAction;
import com.incedo.api.common.AppConstants;
import com.incedo.api.helper.RequestHelper;
import com.incedo.api.model.HandlerRequest;
import com.incedo.api.model.HandlerResponse;

public class ImageHandler implements RequestStreamHandler {

	@SuppressWarnings("deprecation")
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException 
    {
    	LambdaLogger logger = context.getLogger();
    	JsonParser parser = new JsonParser(); 
    	Gson gson = new Gson();    	    	
		HandlerResponse response = null;
    	
    	try
    	{
    		JsonObject jsonRequest = parser.parse(IOUtils.toString(input)).getAsJsonObject(); 
    		
    		logger.log("Full Proxy Request:: "+jsonRequest);

    		//Preparing Request from API Gateway Proxy request params
    		HandlerRequest handlerRequest = RequestHelper.processRequestParams(jsonRequest);    		
    		
    		if(null != handlerRequest)
    		{
    			//Action invocations
        		if(handlerRequest.getResource().equals(AppConstants.IMAGE_PROCESS))
        			response = ImageProcessAction.invokeAction(logger, handlerRequest);    		
        		
        		
        		//To perform the various actions in same lambda function, we can use swithc case to differentiate the actions by using resource and httpMethod
        		/*switch(handlerRequest.getResource())
        		{
        			case AppConstants.IMAGE_PROCESS: response = ImageProcessAction.invokeAction(logger, handlerRequest);
        		}*/
    		}
    		else
    		{
    			response = new HandlerResponse();
    			response.setStatusCode(500);
        		response.setErrorMessage("Error while processing image...");
        		IOUtils.write(gson.toJson(response, HandlerResponse.class), output);
    		}    		            
    	}
    	catch(Exception e)
    	{  
    		response = new HandlerResponse();
    		response.setStatusCode(500);
    		response.setErrorMessage("Error while processing image...");
    		IOUtils.write(gson.toJson(response, HandlerResponse.class), output);
    	}

    	IOUtils.write(gson.toJson(response, HandlerResponse.class), output);
    }
    
}