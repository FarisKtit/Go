package models;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainStorage {
	
    public static ArrayList<User> users;
    public static ArrayList<Game> games;
	
	public static ArrayList<User> getUserList() throws FileNotFoundException, IOException {
		loadUsers();
		return users;
	}
	
	public static ArrayList<Game> getGameList() throws FileNotFoundException, IOException {
		loadGames();
		return games;
	}
	
	public static void loadUsers() throws IOException {
		users = new ArrayList<User>();
		users = (ArrayList<User>) readFromMemory("users");
	}
	
	public static void loadGames() throws IOException {
		games = new ArrayList<Game>();
		games = (ArrayList<Game>) readFromMemory("games");
	}
	
	public static boolean saveGame(Game game) throws FileNotFoundException, IOException {
		ArrayList<Game> games = getGameList();
		games.add(game);
		return writeGamesToMemory(games, "games");
	}
	
	public static String createUser(User user) throws FileNotFoundException, IOException {
		String username = user.getProfile().getUserName();
		if(getUser(username) != null) {
			return "User exists";
		}
		users.add(user);
		if(writeUsersToMemory()) {
			return "Success";
		} else {
			return "Error";
		}
	}
	
	public static User getUser(String username) throws IOException {
		loadUsers();
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getProfile().getUserName().equals(username)) {
				return users.get(i);
			}
		}
		return null;
	}
	
	public static String deleteUser(String userName) throws FileNotFoundException, IOException {
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
		if(!writeUsersToMemory()) {
			deleted = "Error";
		}
		return deleted;
	}
	
	private static Object readFromMemory(String dataType) throws IOException {
		InputStream fileIs = null;
		ObjectInputStream objIs = null;
		Object data = new ArrayList<Object>();
		try {
			File fileChecker = new File(dataType + ".txt");
			if(!fileChecker.exists()) {
				fileChecker.createNewFile();
			}
			if((fileChecker.isFile() == true) && (fileChecker.length() > 0)) {
				fileIs = new FileInputStream(dataType + ".txt");
				objIs = new ObjectInputStream(fileIs);
				data = objIs.readObject();
			}
		} catch (EOFException exc) {
		    exc.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fileIs != null) fileIs.close();
				if(objIs != null) objIs.close();
			} catch(Exception ex) {
				
			}
		}
		return data;
	}
	
	private static boolean writeUsersToMemory() throws IOException {
		OutputStream ops = null;
		ObjectOutputStream objOps = null;
		boolean successful = false;
		try {
			File fileChecker = new File("users.txt");
			if(!fileChecker.exists()) {
				fileChecker.createNewFile();
			}
			ops = new FileOutputStream("users.txt");
			objOps = new ObjectOutputStream(ops);
			objOps.writeObject(users);
			objOps.flush();
			successful = true;
		} catch (EOFException exc) {
			successful = false;
		    exc.printStackTrace();
		} finally {
			try {
				if(objOps != null) objOps.close();
			} catch (Exception ex) {
				
			}
		}
		return successful;
	}
	
	private static boolean writeGamesToMemory(ArrayList<Game> obj, String filename) throws IOException {
		OutputStream ops = null;
		ObjectOutputStream objOps = null;
		boolean successful = false;
		try {
			File fileChecker = new File(filename + ".txt");
			if(!fileChecker.exists()) {
				fileChecker.createNewFile();
			}
			ops = new FileOutputStream(filename + ".txt");
			objOps = new ObjectOutputStream(ops);
			objOps.writeObject(obj);
			objOps.flush();
			successful = true;
		} catch (EOFException exc) {
			successful = false;
		    exc.printStackTrace();
		} finally {
			try {
				if(objOps != null) objOps.close();
			} catch (Exception ex) {
				
			}
		}
		return successful;
	}
}
