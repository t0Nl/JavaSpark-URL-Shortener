package app.user;

import org.mindrot.jbcrypt.*;
import static app.Application.users;

import java.util.Base64;

public class UserController {
	
//	public static boolean authenticate(String authorizationHeader) {
//		
//		String header = authorizationHeader;
//    	header = header.substring(6);
//    	byte[] dec = Base64.getDecoder().decode(header);
//    	String authorization = (new String(dec) + "\n");
//    	String[] AuthorizationArr = authorization.split(":");
//    	String username = AuthorizationArr[0];
//    	String password = AuthorizationArr[1];
//    	username = username.trim();
//        password = password.trim();
//		
//        if (username.isEmpty() || password.isEmpty()) {
//            return false;
//        }
//        
//        User user = users.getUserByUsername(username);
//        if (user == null) {
//            return false;
//        }
//        
//        String hashedPassword = BCrypt.hashpw(password, user.getSalt());
//        return hashedPassword.equals(user.getHashedPassword());
//    }
	
	public static boolean AuthorizeUser(String authorizationHeader) {
		String header = authorizationHeader;
    	header = header.substring(6);
    	byte[] dec = Base64.getDecoder().decode(header);
    	String authorization = (new String(dec) + "\n");
    	String[] AuthorizationArr = authorization.split(":");
    	String userNm = AuthorizationArr[0];
    	String userPass = AuthorizationArr[1];
		
		userNm = userNm.trim();
		userPass = userPass.trim();
		for(User usr : users.getAllUsers()) {
			if(usr.getUsername().equals(userNm) && usr.getHashedPassword().equals(userPass)) {
				return true;
        	}
        }
		
		return false;
	}

	public static boolean idMatching(String headers, String userId) {
		String userIdHeader[] = headers.split(":");
		userIdHeader[0].trim();
		if(userIdHeader[0].equals(userId)) {
			return true;
		}
		return false;
	}

}
