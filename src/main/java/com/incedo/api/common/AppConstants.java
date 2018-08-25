package com.incedo.api.common;

public class AppConstants 
{
	public static final String AWS_REGION = System.getenv("AWS_REGION");
	public static final String SECURITY_TOKEN = System.getenv("X_Incedo_Security_Token");
	public static final String S3_BUCKET_FOLDER = "ImageStorage/";
	public static final String S3_BUCKET_NAME = "rajeshmorla.online";
	
	
	//Resource paths
	public static final String IMAGE_PROCESS = "/api/imageprocess";

}
