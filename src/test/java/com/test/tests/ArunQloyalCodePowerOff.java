package com.test.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.base.BaseClass;
import com.test.base.JsonParser;
import com.test.base.SwitchOnOff;
import com.test.client.RestClient;

public class ArunQloyalCodePowerOff extends BaseClass {
	BaseClass baseClass;
	private static final String baseUrl = "https://qa-challenges-lightbulb.atlassian.io";
	private static final String endPointOff = "/api/allmethods/off";
	String power;
	String uri;
	RestClient restClient;
	CloseableHttpResponse response;
	
	@Test
	public void powerSwitchedOff() throws ClientProtocolException, IOException {
		uri = baseUrl + endPointOff;
		power = null;
		String expected = "Switch set successfully";
		baseClass = new BaseClass();
		restClient = new RestClient();

		HashMap<String, String> headerDetails = new HashMap<>();
		headerDetails.put("Content-Type", "application/json");
		headerDetails.put("userId", "arun");
		response = restClient.getUrlResponse(uri, headerDetails);

		ObjectMapper mapper = new ObjectMapper();

		SwitchOnOff powerDetails = new SwitchOnOff(power);

		mapper.writeValue(new File(
				"./src/test/java/data/Status.json"),
				powerDetails);
		String jsonData = mapper.writeValueAsString(powerDetails);

		response = restClient.postJsonMsg(uri, jsonData, headerDetails);

		// Validating status code in response
		int statusCode = response.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, BaseClass.CODE_200, "Vlidating response code");
		String responsJson = EntityUtils.toString(response.getEntity(), "UTF-8");
		
		JSONObject jsonObj = new JSONObject(responsJson);
		
		System.out.println(jsonObj);	
		
		String parsingJson = JsonParser.getValueByJPath(jsonObj, "/Result");
		
		System.out.println(parsingJson);
		
		Assert.assertEquals(parsingJson, expected, "Validating Response Result");

	}
}
