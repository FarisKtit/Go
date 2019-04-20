Public class Game {
  Private Board board;
  Private String userMove; 
  Private User storeplayerOne;
  Private int PlayerOnePasses;
  Private int PlayerTwoPasses; 
  Private User storeplayerTwo;
  Private String gameID;
  Private String Winner;
  Private String Loser;  
  Public Game (User U1, User U2){
	  this.storeplayerOne = U1;
	  this.storeplayerTwo = U2;
 }
  Public String placeStone (int x, int y) {
    if (UserMove.equals(“P1”)){ 
    return Board.placeStone(“P1”, x, y);
    } else {
    return Board.placeStone(“P2”, x, y);
   }
  }
  Public String checkPasses (player1, Player2) {
  if (PlayerOnePasses >= 3) {
  return “Player one reached 3 passes”;
  }
  if (PlayerTwoPasses >= 3) {
  return “Player two reached 3 passes”;
  }
  }
  Public User GetplayerOne  {
  return this.storePlayerOne;
  }
  Public User GetPlayerTwo{
  return this.storePlayerTwo;
  }
  Public String GetgameID () {
  return this.gameID;
  }
  Public void SetgameID(String GameID){
  this.gameID == GameID;
  }
  Public String GetWinner(){
  return this.winner;
  }
  Public String GetLoser(){
  return this.loser;
  }
  Public void setWinner(String Winner){
  this.Winner == Winner;
  }
  Public void setLoser(String Loser){
  this.Loser == Loser;
  }
}
