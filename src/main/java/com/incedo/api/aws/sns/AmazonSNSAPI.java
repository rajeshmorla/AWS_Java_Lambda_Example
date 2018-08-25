package com.incedo.api.aws.sns;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class AmazonSNSAPI 
{
	private static AmazonSNSClientBuilder snsClientBuilder = AmazonSNSClientBuilder.standard().withRegion(Regions.AP_SOUTHEAST_1);	 
	private static AmazonSNSClient  snsClient = (AmazonSNSClient) snsClientBuilder.build();    		
    private static Map<String, MessageAttributeValue> smsAttributes = new HashMap<String, MessageAttributeValue>();    
    
	public static String sendMesssage(String phoneNumber, String message, LambdaLogger logger)
	{	
		try
		{
			//Setting Message Preferences
			smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
			        .withStringValue("IncedoSMS") //The sender ID shown on the device.
			        .withDataType("String"));
			
			smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
			        .withStringValue("Transactional") //Sets the type to promotional.
			        .withDataType("String"));
			
			//Publishing message
			PublishResult result = snsClient.publish(new PublishRequest()
	                .withMessage(message)
	                .withPhoneNumber(phoneNumber)
	                .withMessageAttributes(smsAttributes));		
			
			logger.log("Message sent, ID:: "+result.getMessageId());
			
			return "SUCCESS";			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
				
		return "FAILURE";
	}	
}
