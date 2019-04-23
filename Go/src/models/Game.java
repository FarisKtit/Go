package models;

import java.io.Serializable;

public class Game implements Serializable {
  
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
  
  public Game (User U1, User U2){
	  this.storeplayerOne = U1;
	  this.storeplayerTwo = U2;
	  this.board = new Board();
	  userMove = "Player 1";
  }
  
  public String placeStone(int x, int y) {
    String res = board.placeStones(x, y, userMove);
    if(res.equals("SUCCESS")) {
        if(userMove.equals("Player 1")) userMove = "Player 2";
        else userMove = "Player 1";
    }
    return res;
  }
  
  public String checkPasses() {
    if (PlayerOnePasses >= 3) {
      return "Player one reached 3 passes";
    }
    if (PlayerTwoPasses >= 3) {
      return "Player two reached 3 passes";
    }
    return "Ok";
  }
  
  public String getUserMove() {
	  return userMove;
  }
  
  public User getplayerOne() {
    return this.storeplayerOne;
  }
  
  public User getPlayerTwo() {
    return this.storeplayerTwo;
  }
  
  public String getGameID () {
  return this.gameID;
  }
  
  public void getGameID(String GameID){
    this.gameID = GameID;
  }
  
  public String getWinner(){
    return this.Winner;
  }
  
  public String getLoser(){
    return this.Loser;
  }
  
  public void setWinner(String Winner){
    this.Winner = Winner;
  }
  
  public void setLoser(String Loser){
    this.Loser = Loser;
  }
  public Board getBoard() {
	  return this.board;
  }
  public String toString() {
	  return storeplayerOne+"=="+storeplayerTwo+"=="+Winner+"=="+Loser+"=="+board+"=="+userMove+"=="+PlayerOnePasses+"=="+PlayerTwoPasses+"=="+gameID;  }
}
