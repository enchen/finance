package cn.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.protocol.Protocol;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public class AccessTokenTool {
	
	private static String accessToken;
	public static String refreshAccessToken()
	{
		String respStr=HttpUtils.httpsGet(Constants.AccessTokenUrl);
		if(!"".equals(respStr)){
			 ObjectMapper mapper = new ObjectMapper(); 
			try{
				JsonNode rootNode = mapper.readTree(respStr); 
				accessToken= rootNode.get("access_token").asText(); 
			} catch (Exception e) {
				return "";
			}
		}
		return accessToken;
	}
	public static String getAccessToken()
	{
		if(null==accessToken||accessToken.equals(""))
		{
			return refreshAccessToken();
		}
		else
		{
			return accessToken;
		}
	}
	
	
}
