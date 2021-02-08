package app.user;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    
	String username;
    String salt;
    String hashedPassword;
    
    public User(String username) {
		this.username = username;
		this.salt = BCrypt.gensalt();
		this.hashedPassword = PasswordGenerator.generatePassword();
	}
    
    public User(String username, String salt, String hashedPassword) {
    	this.username = username;
    	this.salt = salt;
    	this.hashedPassword = hashedPassword;
    }
    
	public String getUsername() {
		return this.username;
	}

	public String getSalt() {
		return this.salt;
	}

	public String getHashedPassword() {
		return this.hashedPassword;
	}
}
