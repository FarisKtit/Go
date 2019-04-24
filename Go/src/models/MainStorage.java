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

/**
 * This class is responsible for writing and reading data to/from files.
 * @author Faris Ktit
 *
 */
public class MainStorage {
	
    public static ArrayList<Game> games;
	
    /**
     * Provides the ability to read saved games from files.
     * @return ArrayList<Game>
     * @throws Exception
     */
	public static ArrayList<Game> getGameList() throws Exception {
		loadGames();
		return games;
	}
	
	/**
	 * Provides the ability to save a game to a file.
	 * @param game
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean saveGame(Game game) throws Exception {
		ArrayList<Game> games = getGameList();
		games.add(game);
		return writeGamesToMemory();
	}
	
	/**
	 * 
	 * @param userList
	 * @return
	 * @throws IOException
	 */
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
	
	/**
	 * Provides the ability to write the game directly to permanent storage.
	 * @return boolean
	 * @throws IOException
	 */
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
	
	/**
	 * Provides the ability to read both games and users directly from permanent storage.
	 * @param dataType
	 * @return Object
	 * @throws Exception
	 */
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
	
	/**
	 * Provides the ability to load games directly from permanent storage into memory.
	 * @throws Exception
	 */
	private static void loadGames() throws Exception {
		games = new ArrayList<Game>();
		games = (ArrayList<Game>) readFromMemory("games");
	}
}
