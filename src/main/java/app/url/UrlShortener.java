package app.url;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static app.Application.urls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UrlShortener {
	
	private static final String coder = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	
	public static String urlShortener(String body, long reqNum, String authentication) {
		
		JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
		String url = jsonObject.get("url").toString();
		url = url.substring(1, url.length() - 1);
		
		JsonElement reqType = jsonObject.get("redirectType");
		int reqTypeInt;
		
		String userData[] = authentication.split(":");
		userData[0].trim();
		
		if(reqType == null) {
			reqTypeInt = 302;
		}
		else {
			reqTypeInt = reqType.getAsInt();
		}
		
		for(Map.Entry<Long, Url> it : urls.getUrls().entrySet()) {
			if(it.getValue().getUrlUserId().equals(userData[0]) && it.getValue().getLngUrl().equals(url)) {
				return null;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		//sb.append("http://short.com/");
		
		sb.append("http://localhost:4128/");
		
		long algNum = reqNum;
		long mod;
		
		while(true) {
			mod = algNum % 62;
			algNum = algNum / 62;
			sb.append(coder.charAt((int) mod));
			if(algNum == 0) {
				break;
			}
		}
		
		
		
		Url newUrl = new Url(url, userData[0], reqTypeInt);
		urls.getUrls().put(reqNum, newUrl);
		return sb.toString();
	}
	
	public static Url getUrlFromShortUrl(String shrtUrl) {
		String shrtUrlTrimmed[] = shrtUrl.split("/");
		shrtUrl = shrtUrlTrimmed[shrtUrlTrimmed.length - 1];
		System.out.println(shrtUrl);
		
		List<Integer> codecArr = new ArrayList<>();
		
		for(int i = 0; i < shrtUrl.length(); i++) {
			codecArr.add(coder.indexOf(shrtUrl.charAt(i)));
		}
		
		long key = 0;
		int arrSize = codecArr.size() - 1;
		
		for(int i = 0; i < codecArr.size(); i++) {
			key += codecArr.get(i) * Math.pow(62,arrSize - i);
		}
		
		Url urlFromKey = urls.getUrls().get(key);
		urlFromKey.increaseVisitNum();
		
		return urlFromKey;
	}

}
