# AWS_Java_Lambda_Example
An example project to implement a image file processing in AWS Lambda, store the same in S3, read the file metadata from S3, Store the same in DynamoDB and send acknowledgement SMS to user mobile...

Installation / Setup:::

Lambda Function Setup: (ap-southeast-1, Singapore region)
1. Clone the project
2. run command 'maven clean install'
3. Build/JAR file will be generated in 'target' directory with name: 'ImageProcessing-0.0.1-SNAPSHOT'
4. Upload the file in AWS Lambda to the region: 'ap-southeast-1' (Asia Pacific (Singapore)) and choose the role access (S3, API-Gateway, SNS, DynamoDB)
5. Make sure to have a Lambda Environtment Variables: X_Incedo_Security_Token = 12345 and APP_REGION = ap-southeast-1 
6. Click on save..

You have succesfully uploaded Lambda function :)

API-Gateway Endpoint Setup: (ap-southeast-1, Singapore region)
1. Download the swagger file from here: AWS_Java_Lambda_Example/ImageProcess-incedo-swagger-apigateway.json
2. Import the same in Amazon API-Gateway
3. Deploy it to new stage

Note: You must change the 'uri' value from the swagger file to your own lambda function arn

DynamoDB Table: (ap-southeast-1, Singapore region)
1. Go to DynamoDB console
2. Create A table (IMAGE_META_DATA) with primary key (ID) and save it

S3 Bucket:
1. Go to S3 console
2. Create a bucket with name: 'rajeshmorla.online'
3. create a folder inside, name: 'ImageStorage'

Note: If you are not comfortable with the names, kinldy change thise from 'AppConstants.Java' file

After all done, you are ready to test the service with any of the REST Client (Postman, SwaggerBuilder, etc,.)

Try to invoke your API Endpoint URL as follows:
1. URI: https://ai01ajt3kg.execute-api.ap-southeast-1.amazonaws.com/incedo/api/imageprocess?filename=rajeshDP&mobile=+919884239XXX
2. Method: POST
3. Headers: Content-Type=image/jpeg and X-Incedo-Security-Token=12345
4. Query Params: filename = xyz, mobile=+919494495XXX (Dont forget to give proper country code, which is manditory)
5. body: Binary file(Image)

Note: Mobile number is an optional param, only if you wanna recieve acknowledge SMS
