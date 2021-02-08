package app.user;

import java.util.*;

public class UserDao {
	
	private List<User> users;
	
	public UserDao() {
		users = new ArrayList<>();
		users.add(new User("Marko", "nesto", "pass"));
		users.add(new User("Peso", "nesto", "pass"));
		users.add(new User("Ivica", "nesto", "pass"));
	}
	
	public User getUserByUsername(String username) {
		
        for(User usr : users) {
        	if(usr.getUsername().equals(username)) {
        		return usr;
        	}
        }
        
        return null;
    }
	
	public List<User> getAllUsers(){
		return users;
	}
	
	public User addUser(String username) {
		
		User test = getUserByUsername(username);
		
		if(test != null) {
			return null;
		}
		
		User temp = new User(username);
		users.add(temp);
		return temp;
	}

}
