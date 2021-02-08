package app.user;

import java.util.concurrent.ThreadLocalRandom;

public class PasswordGenerator {
	
	public static final String alphanum = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
	
	public static String generatePassword() {
		StringBuilder sb = new StringBuilder();
		int randNum;
		
		for (int i = 0; i < 8; i++) {
			randNum = ThreadLocalRandom.current().nextInt(0, 61);
			sb.append(alphanum.charAt(randNum));
			//System.out.println(sb.toString());
		}
		
		return sb.toString();
		
	}

}
