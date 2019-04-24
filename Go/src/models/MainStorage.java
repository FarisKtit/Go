package models;

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
	//Store games retrieved from disk
    private static ArrayList<Game> games;
	
    /**
     * Provides the ability to read saved games from files.
     * @throws Exception
     */
	public static ArrayList<Game> getGameList() throws Exception {
		//Any other class requires a list of users can use this method.
		loadGames();
		return games;
	}
	
	/**
	 * Provides the ability to save a game to a file.
	 * @param Game
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean saveGame(Game game) throws Exception {
		//Save game to disk so it is permanent.
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
	public static boolean writeUsersToMemory(ArrayList<User> userList) throws Exception {
		//Throw exception and let calling method respond, usually controller
		//will manage this with the GUI.
		//Write arraylist of users to file users.txt
		OutputStream ops = null;
		ObjectOutputStream objOps = null;
		boolean successful = false;
		try {
			//Check if file exists, if not create a new one.
			File fileChecker = new File("users.txt");
			if(!fileChecker.exists()) {
				fileChecker.createNewFile();
			}
			//Write to disk
			ops = new FileOutputStream("users.txt");
			objOps = new ObjectOutputStream(ops);
			objOps.writeObject(userList);
			objOps.flush();
			successful = true;
		} finally {
			try {
				//Close byte streams
				if (objOps != null) objOps.close();
			} catch (Exception ex) {
				throw new Exception();
			}
		}
		return successful;
	}
	
	/**
	 * Provides the ability to write the game directly to permanent storage.
	 * @return boolean
	 * @throws IOException
	 */
	public static boolean writeGamesToMemory() throws Exception {
		//Throw exception and let calling method respond, usually controller
		//will manage this with the GUI.
		//Write arraylist of games to file games.txt
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
		} finally {
			try {
				//Close byte streams
				if (objOps != null) objOps.close();
			} catch (Exception ex) {
				throw new Exception();
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
		//Throw exception and let calling method respond, usually controller
		//will manage this with the GUI.
		//Read either arraylist of games or users from disk and return as object
		//This is then cast
		InputStream fileIs = null;
		ObjectInputStream objIs = null;
		Object data = new ArrayList<Object>();
		try {
			//Create new file if it does not exist
			File fileChecker = new File(dataType + ".txt");
			if (!fileChecker.exists()) {
				fileChecker.createNewFile();
			}
			if ((fileChecker.isFile() == true) && (fileChecker.length() > 0)) {
				fileIs = new FileInputStream(dataType + ".txt");
				objIs = new ObjectInputStream(fileIs);
				data = objIs.readObject();
			}
		} finally {
			try {
				//Close byte streams
				if (fileIs != null) fileIs.close();
				if (objIs != null) objIs.close();
			} catch(Exception ex) {
				throw new Exception();
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
