package com.test.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
	public CloseableHttpResponse getUrlResponse(String uri) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet getUri = new HttpGet(uri);
		CloseableHttpResponse response = httpClient.execute(getUri);
		return response;
		
	}
	
	public CloseableHttpResponse getUrlResponse(String uri, HashMap<String, String> headers) throws ClientProtocolException, IOException {

		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet getUri = new HttpGet(uri);
		for (Map.Entry<String, String> header : headers.entrySet())
		{
			getUri.addHeader(header.getKey(), header.getValue());
		}
		CloseableHttpResponse response = client.execute(getUri);
		return response;
		
	}
	
	public CloseableHttpResponse postJsonMsg(String uri, String jsonMsg, HashMap<String, String> headers) throws ClientProtocolException, IOException
	{
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(uri);
		post.setEntity(new StringEntity(jsonMsg));
		for(Map.Entry<String, String> header : headers.entrySet())
		{
			post.addHeader(header.getKey(), header.getValue());
		}
		CloseableHttpResponse response = client.execute(post);
		return response ;
	}

	
}
