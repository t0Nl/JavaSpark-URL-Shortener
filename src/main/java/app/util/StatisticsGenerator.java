package app.util;

import java.util.Map;

import com.google.gson.JsonObject;

import app.url.Url;

public class StatisticsGenerator {
	
	public static JsonObject generateJson(Map<Long, Url> urls, String userId) {
		JsonObject stats = new JsonObject();
		
		for(Map.Entry<Long, Url> it : urls.entrySet()) {
			if(it.getValue().getUrlUserId().equals(userId)) {
				stats.addProperty(it.getValue().getLngUrl(), it.getValue().getVisitNum());
			}
		}
		
		return stats;
	}

}
