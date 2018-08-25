# AWS_Java_Lambda_Example
An example project to implement a image file processing in AWS Lambda, store the same in S3, read the file metadata from S3, Store the same in DynamoDB and send acknowledgement SMS to user mobile...

Installation / Setup:
1. Clone the project
2. run command 'maven clean install'
3. Build/JAR file will be generated in 'target' directory with name: 'ImageProcessing-0.0.1-SNAPSHOT'
4. Upload the file in AWS Lambda to the region: 'ap-southeast-1' (Asia Pacific (Singapore))
5. Make sure to have a Lambda Environtment Variables: X_Incedo_Security_Token = 12345 and APP_REGION = ap-southeast-1 
6. Click on save..

You have succesfully uploaded Lambda function :)

