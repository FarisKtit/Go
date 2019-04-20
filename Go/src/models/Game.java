package models;

class Game {
  private Board board;
  private String userMove; 
  private User storeplayerOne;
  private int PlayerOnePasses;
  private int PlayerTwoPasses; 
  private User storeplayerTwo;
  private String gameID;
  private String Winner;
  private String Loser;  
  public Game (User U1, User U2){
	  this.storeplayerOne = U1;
	  this.storeplayerTwo = U2;
	  userMove = "P1";
 }
  public String placeStone(int x, int y) {
    if (userMove.equals("P1")){ 
    //return board.placeStone("P1", x, y);
    	return "";
    } else {
    //return board.placeStone("P2", x, y);
    	return "";
   }
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
}
