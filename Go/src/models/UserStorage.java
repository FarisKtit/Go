package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserStorage extends MainStorage {
	
	public static ArrayList<User> users;
	
	public static ArrayList<User> getUserList() throws Exception {
		loadUsers();
		return users;
	}
	
	public static void loadUsers() throws Exception {
		users = new ArrayList<User>();
		users = (ArrayList<User>) readFromMemory("users");
	}
	
	public static String createUser(User user) throws Exception {
		String username = user.getProfile().getUserName();
		if(getUser(username) != null) {
			return "User exists";
		}
		users.add(user);
		if(writeUsersToMemory(users)) {
			return "Success";
		} else {
			return "Error";
		}
	}
	
	public static User getUser(String username) throws Exception {
		loadUsers();
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getProfile().getUserName().equals(username)) {
				return users.get(i);
			}
		}
		return null;
	}
	
	public static String deleteUser(String userName) throws Exception {
		loadUsers();
		String deleted = "No user";
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getProfile().getUserName().equals(userName)) {
				users.remove(i);
				deleted = "Success";
			}
		}
		if(deleted.equals("No user")) {
			return deleted;
		}
		if(!writeUsersToMemory(users)) {
			deleted = "Error";
		}
		return deleted;
	}
	
    public static ArrayList<User> newUserSinceLastLogin(LocalDateTime lastLoggedIn) throws Exception {
        ArrayList<User> users = getUserList();
        ArrayList<User> result = new ArrayList<User>();
        for (int i = 0; i < users.size(); i++)
        {
            if (users.get(i).getJoinDate().isAfter(lastLoggedIn)){
                result.add(users.get(i));
            }
        }
        return result;
    }

    public static ArrayList<Game> gamesPlayedSinceLastLogin(LocalDateTime lastLoggedIn) throws Exception {
        ArrayList<Game> result = getGameList();
        for (int i = 0; i < games.size(); i++)
        {
            if (games.get(i).getGameDate().isAfter(lastLoggedIn)){
                result.add(games.get(i));
            }
        }
        return result;
    }
}
