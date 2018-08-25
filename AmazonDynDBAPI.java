package com.incedo.api.aws.dyndb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class AmazonDynDBAPI 
{
    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    
    public static DynamoDBMapper getMapper()
    {
    	return new DynamoDBMapper(client);
    }
}
