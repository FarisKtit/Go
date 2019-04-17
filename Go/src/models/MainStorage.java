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
	
	public static ArrayList<User> getUserList() throws FileNotFoundException, IOException {
		users = new ArrayList<User>();
		loadUsers();
		return users;
	}
	
	public static void loadUsers() throws IOException {
		users = new ArrayList<User>();
		users = (ArrayList<User>) readDataFromMemory("users");
	}
	
	public static boolean createUser(User user) throws FileNotFoundException, IOException {
		loadUsers();
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getProfile().getUserName().equals(user.getProfile().getUserName())) {
				return false;
			}
		}
		users.add(user);
		return writeUsersToMemory();
	}
	
	public static boolean deleteUser(String userName) throws FileNotFoundException, IOException {
		loadUsers();
		boolean deleted = false;
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getProfile().getUserName().equals(userName)) {
				users.remove(i);
				deleted = true;
			}
		}
		if(!deleted) return deleted;
		return writeUsersToMemory();
	}
	
	private static Object readDataFromMemory(String dataType) throws IOException {
		InputStream fileIs = null;
		ObjectInputStream objIs = null;
		Object data = new Object();
		try {
			File fileChecker = new File(dataType + ".txt");
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
		try {
			ops = new FileOutputStream("users.txt");
			objOps = new ObjectOutputStream(ops);
			objOps.writeObject(users);
			objOps.flush();
		} catch (EOFException exc) {
		    exc.printStackTrace();
		} finally {
			try {
				if(objOps != null) objOps.close();
			} catch (Exception ex) {
				
			}
		}
		return true;
	}
}
