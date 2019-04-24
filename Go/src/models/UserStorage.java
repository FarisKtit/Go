package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author cerriannesantos UserStorage class responsible for containing user information and methods to access this information
 */

public class UserStorage extends MainStorage{

	public static ArrayList<User> users;

	/**
	 * Gets a list of all users in system
	 * @return A list of all users in system
	 * @throws Exception
	 */

    public static ArrayList<User> getUserList() throws Exception{
        loadUsers();
        return users;
    }

	/**
	 * Loads users from memory
	 * @throws Exception
	 */

	public static void loadUsers() throws Exception{
		users = new ArrayList<User>();
		users = (ArrayList<User>) readFromMemory("users");
	}

	/**
	 * Creates a user if not already exists
	 * @param user Takes in a user
	 * @return Success or error depending on whether a new user was created
	 * @throws Exception
	 */

	public static String createUser(User user) throws Exception{
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

	/**
	 * Gets a user by taking in user name
	 * @param username username
	 * @return A user is returned
	 * @throws Exception
	 */

	public static User getUser(String username) throws Exception{
		loadUsers();
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getProfile().getUserName().equals(username)){
				return users.get(i);
			}
		}
		return null;
	}

	/**
	 * Updates a user
	 * @param obj User
	 * @return an updated user
	 * @throws Exception
	 */

	public static boolean updateUser(User obj) throws Exception{
		String userName = obj.getProfile().getUserName();
		ArrayList<User> users = getUserList();
		boolean updated = false;
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getProfile().getUserName().equals(userName)){
				users.set(i, obj);
				updated = true;
			}
		}
		writeUsersToMemory(users);
		return updated;
	}

	/**
	 * Method to delete a user
	 * @param userName takes in user name
	 * @return success, no user or error depending on whether user was successfully deleted
	 * @throws Exception
	 */

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


	/**
	 * Returns a list of new users since user last login
	 * @param lastLoggedIn last logged in by user
	 * @return list of new users since user last login
	 * @throws Exception
	 */
  
    public static ArrayList<User> newUserSinceLastLogin(LocalDateTime lastLoggedIn) throws Exception{
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

	/**
	 * Returns a list of new games played since user last logged in
	 * @param lastLoggedIn last logged in
	 * @return list of new games played since user last logged in
	 * @throws Exception
	 */

    public static ArrayList<Game> gamesPlayedSinceLastLogin(LocalDateTime lastLoggedIn) throws Exception{
		ArrayList<Game> games = getGameList();
		ArrayList<Game> result = new ArrayList<Game>();
        for (int i = 0; i < games.size(); i++)
        {
            if (games.get(i).getGameDate().isAfter(lastLoggedIn)){
                result.add(games.get(i));
            }
        }
        return result;
    }
}
