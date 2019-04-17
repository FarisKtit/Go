package models;

import java.util.ArrayList;

public class MainStorageModel {
	public static void sayHello() {
		System.out.println("Hellow");
	}
	public static void sayBye() {
		System.out.println("Bye");
	}
	/*
	public static ArrayList<UserModel> users;
	
	public static ArrayList<UserModel> getUserList() throws FileNotFoundException, IOException {
		users = new ArrayList<UserModel>();
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
			users = (ArrayList<UserModel>) objIs.readObject();
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
	
	public static boolean createUser(UserModel user) throws FileNotFoundException, IOException {
		OutputStream ops = null;
		ObjectOutputStream objOps = null;
		loadUsers();
		try {
			ops = new FileOutputStream("users.txt");
			objOps = new ObjectOutputStream(ops);
			for(int i = 0; i < users.size(); i++) {
				if(users.get(i).getUserName().equals(user.getUserName())) {
					return false;
				}
			}
			users.add(user);
			objOps.writeObject(users);
			objOps.flush();
		} catch (EOFException exc) {
		    //exc.printStackTrace();
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
				if(users.get(i).getUserName().equals(username)) {
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
	*/

}
