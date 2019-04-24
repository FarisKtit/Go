package models;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainStorage {
	
    public static ArrayList<Game> games;
	
	public static ArrayList<Game> getGameList() throws Exception {
		loadGames();
		return games;
	}
	
	public static void loadGames() throws Exception {
		games = new ArrayList<Game>();
		games = (ArrayList<Game>) readFromMemory("games");
	}
	
	public static boolean saveGame(Game game) throws Exception {
		ArrayList<Game> games = getGameList();
		games.add(game);
		return writeGamesToMemory();
	}
	
	public static boolean writeUsersToMemory(ArrayList<User> userList) throws IOException {
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
			objOps.writeObject(userList);
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
	
	public static boolean writeGamesToMemory() throws IOException {
		OutputStream ops = null;
		ObjectOutputStream objOps = null;
		boolean successful = false;
		try {
			File fileChecker = new File("games.txt");
			if(!fileChecker.exists()) {
				fileChecker.createNewFile();
			}
			ops = new FileOutputStream("games.txt");
			objOps = new ObjectOutputStream(ops);
			objOps.writeObject(games);
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
	
	protected static Object readFromMemory(String dataType) throws Exception {
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
		    throw new EOFException();
		} finally {
			try {
				if(fileIs != null) fileIs.close();
				if(objIs != null) objIs.close();
			} catch(Exception ex) {
				
			}
		}
		return data;
	}
}
