package app.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import app.user.*;

public class RequestValidator {
	
	public static RequestResponse validateRequest(String body, UserDao users) {
		
		JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
		String username = jsonObject.get("AccountId").toString();
		username = username.substring(1, username.length() - 1);
		
		if(username == null) {
			return buildNegativeResponseJSON();
		}
		
		User temp = users.addUser(username);
		
		if(temp != null) {
			return buildPositiveResponse((String) temp.getHashedPassword());
		}
		
		else {
			return buildNegativeResponseUser();
		}
	}

	private static RequestResponse buildNegativeResponseUser() {
		return new RequestResponse(false, "Given UserId already exists", null);
	}

	private static RequestResponse buildPositiveResponse(String pass) {
		return new RequestResponse(true, "Account successfully generated", pass);
	}

	private static RequestResponse buildNegativeResponseJSON() {
		return new RequestResponse(false, "Wrong contents in body of request", null);
	}

}
