package com.incedo.api.helper;

import com.google.gson.JsonObject;
import com.incedo.api.model.HandlerRequest;

public class RequestHelper 
{
	public static HandlerRequest processRequestParams(JsonObject request)
	{
		HandlerRequest handlerRequest = new HandlerRequest();
		boolean validProxyRequest = true;
		
		try
		{			
			if(null != request.get("resource"))
				handlerRequest.setResource(request.get("resource").getAsString());
			else
				validProxyRequest = false;
			
			
			if(null != request.get("httpMethod"))
				handlerRequest.setHttpMethod(request.get("httpMethod").getAsString());
			else
				validProxyRequest = false;
			
			
			if(null != request.get("headers"))
				if(null != request.get("headers").getAsJsonObject().get("X-Incedo-Security-Token"))
					handlerRequest.setSecurityToken(request.get("headers").getAsJsonObject().get("X-Incedo-Security-Token").getAsString());
				else
					validProxyRequest = false;
			else
				validProxyRequest = false;
			
			
			if(null != request.get("queryStringParameters"))
			{
				if(null != request.get("queryStringParameters").getAsJsonObject().get("filename"))
					handlerRequest.setFilename(request.get("queryStringParameters").getAsJsonObject().get("filename").getAsString());
				else
					validProxyRequest = false;
				
				if(null != request.get("queryStringParameters").getAsJsonObject().get("mobile"))
					handlerRequest.setMobile(request.get("queryStringParameters").getAsJsonObject().get("mobile").getAsString());				
			}				
			else
				validProxyRequest = false;
			
			
			
			
			if(null != request.get("isBase64Encoded"))
				handlerRequest.setBase64Encoded(request.get("isBase64Encoded").getAsBoolean());
			else
				validProxyRequest = false;
			
			
			if(null != request.get("body"))
				handlerRequest.setBody(request.get("body").getAsString());
			else
				validProxyRequest = false;	
			
			
			if(!validProxyRequest)
				return null;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return handlerRequest;
	}
}
