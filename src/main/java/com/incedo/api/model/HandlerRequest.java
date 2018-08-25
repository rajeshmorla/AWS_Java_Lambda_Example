package com.incedo.api.model;

public class HandlerRequest 
{
	private String resource;
	private String httpMethod;
	private String securityToken;
	private String filename;
	private boolean base64Encoded;
	private String body;
	private String mobile;
	
	
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	public String getSecurityToken() {
		return securityToken;
	}
	public void setSecurityToken(String securityToken) {
		this.securityToken = securityToken;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public boolean isBase64Encoded() {
		return base64Encoded;
	}
	public void setBase64Encoded(boolean base64Encoded) {
		this.base64Encoded = base64Encoded;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	

}
