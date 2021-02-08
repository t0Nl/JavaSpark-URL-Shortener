package app.util;

public class RequestResponse {
	
	boolean success;
	String description;
	String password;
	
	public RequestResponse(boolean success, String description, String password) {
		this.success = success;
		this.description = description;
		this.password = password;
	}
	
	public boolean getSuccess() {
		return this.success;
	}

}
