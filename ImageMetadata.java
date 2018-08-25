package com.incedo.api.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "IMAGE_META_DATA")
public class ImageMetadata 
{
	private String id;
	private String bucketName;
	private String key;
	private String fileName;
	private String contentType;
	private String lastModified;
	private String eTag;
	private String storageClass;
	private String serverSideEncryption;
	private String size;
	private String link;
	private String versionID;
	
	
	@DynamoDBHashKey(attributeName = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@DynamoDBAttribute(attributeName = "BUCKET_NAME")
	public String getBucketName() {
		return bucketName;
	}
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	
	@DynamoDBAttribute(attributeName = "KEY")
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	@DynamoDBAttribute(attributeName = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@DynamoDBAttribute(attributeName = "CONTENT_TYPE")
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	@DynamoDBAttribute(attributeName = "LAST_MODIFIED")
	public String getLastModified() {
		return lastModified;
	}
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	
	@DynamoDBAttribute(attributeName = "E_TAG")
	public String geteTag() {
		return eTag;
	}
	public void seteTag(String eTag) {
		this.eTag = eTag;
	}
	
	@DynamoDBAttribute(attributeName = "STORAGE_CLASS")
	public String getStorageClass() {
		return storageClass;
	}
	public void setStorageClass(String storageClass) {
		this.storageClass = storageClass;
	}
	
	@DynamoDBAttribute(attributeName = "SERVER_SIDE_ENCRYPTION")
	public String getServerSideEncryption() {
		return serverSideEncryption;
	}
	public void setServerSideEncryption(String serverSideEncryption) {
		this.serverSideEncryption = serverSideEncryption;
	}
	
	@DynamoDBAttribute(attributeName = "SIZE")
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	@DynamoDBAttribute(attributeName = "LINK")
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	@DynamoDBAttribute(attributeName = "VERSION_ID")
	public String getVersionID() {
		return versionID;
	}
	public void setVersionID(String versionID) {
		this.versionID = versionID;
	}
	
	
	@Override
	public String toString() {
		return "ImageMetadata [id=" + id + ", bucketName=" + bucketName + ", key=" + key + ", fileName=" + fileName
				+ ", contentType=" + contentType + ", lastModified=" + lastModified + ", eTag=" + eTag
				+ ", storageClass=" + storageClass + ", serverSideEncryption=" + serverSideEncryption + ", size=" + size
				+ ", link=" + link + ", versionID=" + versionID + "]";
	}	
	
		
}
