package app;

import static spark.Spark.*;
import app.user.*;

import app.url.*;
import app.user.*;
import app.util.*;

import static app.util.JsonConverter.*;
import static spark.Spark.*;
import static spark.debug.DebugScreen.*;

import java.util.Base64;
import java.util.Base64.Decoder;

import com.google.gson.JsonObject;

public class Application {
	
	public static UserDao users;
	public static UrlDao urls; 
	private static long requestNum;
	
	public static void main(String[] args) {
        users = new UserDao();
        urls = new UrlDao();
        
        port(4128);
        enableDebugScreen();
        
        get("/users", "application/json", (request,response) -> {
        	response.type("application/json");
        	return users.getUserByUsername("davidase");
        }, json());
        
        post("/account", "application/json", (request,response) -> {
        	response.type("application/json");
        	RequestResponse resp = RequestValidator.validateRequest(request.body(), users);
        	
        	if(resp.getSuccess()) {
        		response.status(201);
        	}
        	else {
        		response.status(409);
        	}
        	
        	return resp;
        }, json());
        
        post("/register", "application/json", (request,response) -> {
        	response.type("application/json");
        	
        	if(!(UserController.AuthorizeUser(request.headers("Authorization")))) {
        		response.status(401);
        		return "Wrong authorization credentials";
        	}
        	
        	String shortenedUrl = UrlShortener.urlShortener(request.body(), requestNum, request.headers("Authorization"));
        	
        	if(shortenedUrl == null) {
        		response.status(200);
        		return "URL already registered";
        	}
        	
        	requestNum++;
        	response.status(201);
        	JsonObject jsonObject = new JsonObject();
        	jsonObject.addProperty("shortUrl", shortenedUrl);
        	return jsonObject;
        }, json());
        
        post("/statistic/:AccountId", "application/json", (request,response) -> {
        	response.type("application/json");
        	String accId = request.params(":AccountId");
        	
        	if(!(UserController.AuthorizeUser(request.headers("Authorization")))) {
        		response.status(401);
        		return "Wrong authorization credentials";
        	}
        	
        	if(!(UserController.idMatching(request.headers("Authorization"), accId))) {
        		response.status(401);
        		return "Id mismatch";
        	}
        	
        	JsonObject statistics = StatisticsGenerator.generateJson(urls.getUrls(), accId);
        	response.status(200);
        	return statistics;
        }, json());
        
        get("/:id", (request,response) -> {
        	Url longUrl = UrlShortener.getUrlFromShortUrl(request.params(":id"));
        	response.status(longUrl.getRedirecttype());
        	response.redirect(longUrl.getLngUrl());
        	return null;
        });
        
        get("/help", (request,response) -> {
        	return null;
        });
        
    }
}
