package cn.util;

import com.google.gson.JsonObject;

public class WXMessageUtil {
	public static String getTextJson(String toUser,String message)
	{
		JsonObject jo=new JsonObject();
		JsonObject mes=new JsonObject();
		mes.addProperty("content", message);
		jo.addProperty("touser", toUser);
		jo.addProperty("msgtype", "text");
		jo.addProperty("agentid", 0);
		jo.add("text", mes);
		jo.addProperty("safe",0);
		return jo.toString();
	}

}
