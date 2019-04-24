/**
* Author: Nathan Forester
*/
package models; // connected to the game package

import java.io.Serializable; // Import libraries allowing the functions within this class to work
import java.time.LocalDateTime;

/**
* This class represents various datatypes in constructing the game class
*/
public class Game implements Serializable { //all constructors and datatypes used in the Game class
  
  private LocalDateTime gameDate;
  private Board board;
  private String userMove; 
  private User storeplayerOne;
  private int PlayerOnePasses;
  private int PlayerTwoPasses; 
  private User storeplayerTwo;
  private String gameID;
  private String Winner;
  private String Loser;
  private static final long serialVersionUID = 1L;
  
  /**
  * Store users as players accessing user profile data.
  */
  public Game (User U1, User U2){ //Stores user values as players and allows subsequent get and set functions to be called
	  this.storeplayerOne = U1;
	  this.storeplayerTwo = U2;
	  this.board = new Board();
	  this.gameID = U1.getProfile().getUserName() + " vs " + U2.getProfile().getUserName();
	  this.gameDate = LocalDateTime.now();
	  userMove = "Player 1";
  }
  /**
  * Create a coordinate from an integer pair (x, y).
  * @param x The new x coordinate.
  * @param y The new y coordinate.
  * @return The res value.
  */
  public String placeStone(int x, int y) { //two integers representing coordinates on the board, with x representing the first user and y representing the second
    String res = board.placeStones(x, y, userMove);
    if(res.equals("SUCCESS")) {
        if(userMove.equals("Player 1")) userMove = "Player 2";
        else userMove = "Player 1";
    }
    return res;
  }
  
  /**
  * Check passes.
  * @return The string argument. 
  */
  public String checkPasses() { //checks number of legal passes and does not allow any player to pass more than three times, else the game ends. If all moves are legal, the game continues
    if (PlayerOnePasses >= 3) {
      return "Player one reached 3 passes";
    }
    if (PlayerTwoPasses >= 3) {
      return "Player two reached 3 passes";
    }
    return "Ok";
  }
  
  /**
  * Get the date.
  * @return The date.
  */
  public LocalDateTime getGameDate() { //finds the time and date of the game currently being played or about to be played
	  return this.gameDate;
  }
  
  /**
  * Get the next user.
  * @return Current user turn. 
  */
  public String getUserMove() { //allows the application to know whose turn it is next
	  return userMove;
  }
  
  /**
  * Set next or current user move.
  */
  public void setUserMove(String move) { //sets the value of the new move
	  this.userMove = move;
  }
  
  /**
  * Get player one.
  * @return The current player one.
  */
  public User getplayerOne() { // stores player one within the corresponding user profile
    return this.storeplayerOne;
  }
  
  /**
  * Get player two.
  * @return The current player two.
  */
  public User getPlayerTwo() { // stores player two within the corresponding user profile
    return this.storeplayerTwo;
  }
  
  /**
  * Get game identification number.
  * @return The current game identification number.
  */
  public String getGameID () { //obtains a game identification number
  return this.gameID;
  }
  
 /**
  * Get and set game identification number.
  */
  public void getGameID(String GameID){ // assigns game identification number
    this.gameID = GameID;
  }
  
  /**
  * Get winning player data. 
  * @return The current winner (player one or player two).
  */
  public String getWinner(){ // obtains the value of the winner
    return this.Winner;
  }
  
  /**
  * Get the losing player data. 
  * @return The current losing player (player one or player two).
  */
  public String getLoser(){ //obtains the value of the losing player
    return this.Loser;
  }
  /**
  * Set winner.
  */
  public void setWinner(String Winner){ // assigns a value to the winning player
    this.Winner = Winner;
  }
  /**
  * Set losing player.
  */
  public void setLoser(String Loser){ // assigns a value to the losing player
    this.Loser = Loser;
  }
  
  /**
  * Get board.
  * @return The value of the board.
  */
  public Board getBoard() { // obtains the value of the board
	  return this.board;
  }
  /**
  * Get all data and return it to the user interface.
  * @return All inputs and outputs. 
  */
  public String toString() {
	  return storeplayerOne+"=="+storeplayerTwo+"=="+Winner+"=="+Loser+"=="+board+"=="+userMove+"=="+PlayerOnePasses+"=="+PlayerTwoPasses+"=="+gameID;  }
}
