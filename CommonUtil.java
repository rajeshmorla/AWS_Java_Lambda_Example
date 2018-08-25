package com.incedo.api.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class CommonUtil 
{
	public static File base64Decode(LambdaLogger logger, String base64Text, String filename)
	{
		File file = null;
		try 
		{
			//File decoding...
			logger.log("File decoding..."); 		
			byte[] base64EncodedData = Base64.decodeBase64(base64Text.getBytes());

			logger.log("Preparing input stream...");
			InputStream stream = new ByteArrayInputStream(base64EncodedData); 

			logger.log("Creating file from stream...");
			
			file = File.createTempFile(filename, ".jpeg");
			
			FileUtils.copyInputStreamToFile(stream, file);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;		
	}
}
