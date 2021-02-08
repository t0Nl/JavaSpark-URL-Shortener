package app.url;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

import app.user.User;

public class UrlDao {
	
	public static Map<Long,Url> urlDictionary = new HashMap<>();
	
	public Map<Long,Url> getUrls(){
		return urlDictionary;
	}

}
