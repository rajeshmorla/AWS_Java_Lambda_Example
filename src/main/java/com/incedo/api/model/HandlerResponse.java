package com.incedo.api.model;

import com.google.gson.JsonObject;

public class HandlerResponse 
{
	private boolean isBase64Encoded;
	private int statusCode;
	private String body;
	private JsonObject headers;
	public boolean isBase64Encoded() {
		return isBase64Encoded;
	}
	public void setBase64Encoded(boolean isBase64Encoded) {
		this.isBase64Encoded = isBase64Encoded;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public JsonObject getHeaders() {
		return headers;
	}
	public void setHeaders(JsonObject headers) {
		this.headers = headers;
	}
	
	
	
	
}
