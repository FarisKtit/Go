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
	
	public static void loadUsers() throws FileNotFoundException, IOException {
		InputStream fileIs = null;
		ObjectInputStream objIs = null;
		try {
			File fileChecker = new File("users.txt");
			if(fileChecker.isFile() && fileChecker.length() == 0) {
				return;
			}
			fileIs = new FileInputStream("users.txt");
			objIs = new ObjectInputStream(fileIs);
			users = (ArrayList<User>) objIs.readObject();
		} catch (EOFException exc) {
		    exc.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(objIs != null) objIs.close();
			} catch(Exception ex) {
				
			}
		}
	}
	
	public static boolean createUser(User user) throws FileNotFoundException, IOException {
		OutputStream ops = null;
		ObjectOutputStream objOps = null;
		loadUsers();
		try {
			ops = new FileOutputStream("users.txt");
			objOps = new ObjectOutputStream(ops);
			for(int i = 0; i < users.size(); i++) {
				if(users.get(i).getProfile().getUserName().equals(user.getProfile().getUserName())) {
					return false;
				}
			}
			users.add(user);
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
	
	public static boolean deleteUser(String username) throws FileNotFoundException, IOException {
		OutputStream ops = null;
		ObjectOutputStream objOps = null;
		boolean deleted = false;
		loadUsers();
		try {
			ops = new FileOutputStream("users.txt");
			objOps = new ObjectOutputStream(ops);
			for(int i = 0; i < users.size(); i++) {
				if(users.get(i).getProfile().getUserName().equals(username)) {
					users.remove(i);
					deleted = true;
				}
			}
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
		return deleted;
	}
}
