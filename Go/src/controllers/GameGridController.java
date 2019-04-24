package controllers;

import java.io.IOException;
import java.util.ArrayList;
import models.Board;
import models.Game;
import models.MainStorage;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import models.User;
import models.UserStorage;

/**
 * This class manages all interactions with the board and manages the scoring system for the game Go94.
 * @author Faris Ktit
 * @version 1.9
 */
public class GameGridController extends GraphicalUserInterface {
	
	//Store node width and half width of each node on board.
	private final int NODE_WIDTH = 30;
	private final int HALF_NODE_WIDTH = 15;
	//Two successive passes by each player equal a total of four successive passes.
	private final int PASS_LIMIT = 4;
	//Store stone radius and Grid dimension as it is 9x9.
	private final float STONE_RADIUS = 7.5f;
	private final int GRID_DIMENSION = 9;
	private Game game;
	private int playerOnePasses = 0;
	private int playerTwoPasses = 0;
	private int playerOneCaptures = 0;
	private int playerTwoCaptures = 0;
	private int playerOneDeadStones = 0;
	private int playerTwoDeadStones = 0;
	
	//Store all GUI elements to attach event handlers and and update.
	@FXML
	private Label playerOneTag;
	@FXML
	private Label playerTwoTag;
	@FXML
	private Label playerOneStoneCaptures;
	@FXML
	private Label playerTwoStoneCaptures;
	@FXML
	private Label playerOnePassesLabel;
	@FXML
	private Label playerTwoPassesLabel;
	@FXML
	private GridPane Grid;
	
	/**
	 * Establishes player one and player two and creates the grid for the game.
	 * @param obj
	 */
	public void initData(ArrayList<User> obj) {
		//Create new game instance for this game.
        game = new Game(obj.get(0), obj.get(1));
        //Add both players to game then create the grid with the intersections.
        playerOneTag.setText(obj.get(0).getProfile().getUserName());
        playerTwoTag.setText(obj.get(1).getProfile().getUserName());
        createGrid();
	}
	
	/**
	 * Manages the placing of the stones on the board by the player and removing captured stones.
	 * @param e
	 */
    @FXML
    public void clickGrid(MouseEvent e) {
    	//Store x, y co-ordinates of mouse click
    	int y = (int) e.getY();
    	int x = (int) e.getX();
        //Divide by node width to get which column and row was clicked.
        int yIndex = y / NODE_WIDTH;
        int xIndex = x / NODE_WIDTH;
        //Circle represents a stone.
        Circle c = new Circle();
		c.setRadius(STONE_RADIUS);
		
		int currentPlayer = 0;
		int oppositePlayer = 0;
		//deduce the current player and opposite player
		if (game.getUserMove().equals("Player 1")) {
			currentPlayer = 1;
			oppositePlayer = 2;
			//Reset passes by user if stone is placed.
			playerOnePasses = 0;
			playerOnePassesLabel.setText("Passes: " + playerOnePasses);
			//User one is black
			c.setFill(javafx.scene.paint.Color.BLACK);
		} else { 
			currentPlayer = 2;
			oppositePlayer = 1;
			//Reset passes by user if stone is placed.
			playerTwoPasses = 0;
			playerTwoPassesLabel.setText("Passes: " + playerTwoPasses);
			//User two is white
			c.setFill(javafx.scene.paint.Color.WHITE);
		}
		//if place stone is successful, add stone to GUI
        if (game.placeStone(yIndex, xIndex).equals("SUCCESS")) {
    		GridPane.setHalignment(c, HPos.valueOf("CENTER"));
    		GridPane.setValignment(c, VPos.valueOf("CENTER"));
    		Grid.add(c, xIndex, yIndex);	
        } else {
        	//Otherwise it is an invalid move
        	alertUser("Move", "Invalid move", "Please choose a different intersection");
        }
        Board board = game.getBoard();
        //Remove all captured stones if they exists.
        removeCapturedStones(board, currentPlayer, oppositePlayer);
    }
    
    /**
     * Manages the recreation of the board after every stone placed by a player.
     * @param grid
     */
    public void recreateBoard(GridPane grid) {
    	//Remove all nodes and recreate grid.
    	ObservableList<Node> children = grid.getChildren();
    	grid.getChildren().removeAll(children);
    	createGrid();
    	Board board = game.getBoard();
    	//Get 2D array which represents board from Board instance and update GUI.
    	int[][] occupiedBoard = board.getBoard();
    	for (int i = 0; i < occupiedBoard.length; i++) {
    		for (int j = 0; j < occupiedBoard[i].length; j++) {
    			if (occupiedBoard[i][j] != 0) {
    				//If occupied, place correct stone for corresponding player.
    				Circle c = new Circle();
        			c.setRadius(STONE_RADIUS);
    				if (occupiedBoard[i][j] == 1) {
    					c.setFill(javafx.scene.paint.Color.BLACK);
    				} else {
    					c.setFill(javafx.scene.paint.Color.WHITE);
    				}
    				//Update GUI
    	    		GridPane.setHalignment(c, HPos.valueOf("CENTER"));
    	    		GridPane.setValignment(c, VPos.valueOf("CENTER"));
    	    		Grid.add(c, j, i);
    			}	
    		}
    	}
    }
    
    /**
     * Manages the removal of captured stones from the board.
     * @param board
     * @param currentPlayer
     * @param oppositePlayer
     */
    public void removeCapturedStones(Board board, int currentPlayer, int oppositePlayer) {
    	//Board instance returns connected stones as 2D boolean array
		boolean[][] connected = board.connectedStones(oppositePlayer);
		if (connected != null) {
		  //If there are connected stones, replace it on board instance, then GUI.
		  board.replaceConnectedStones(connected);
		  for (int i = 0; i < connected.length; i++) {
		    for (int j = 0; j < connected[i].length; j++) {
			  if (connected[i][j]) {
				  recreateBoard(Grid);
				  if (currentPlayer == 1) {
					  playerOneCaptures++;
				  } else {
					  playerTwoCaptures++;
				  }
			  }
			}		
		  }
		  playerOneStoneCaptures.setText("Stones captured: " + playerOneCaptures);
		  playerTwoStoneCaptures.setText("Stones captured: " + playerTwoCaptures);
	   }
    }
    
    /**
     * Manages the navigation from the game Go94 back to the home page.
     * @param event
     */
    @FXML
    public void exitGame(ActionEvent event) {
    	try {
			goToView("EntryDashboard", event);
		} catch (IOException e) {
			alertUser("End Game", "Error", "Cannot leave game, system error");
		}
    }
    
    /**
     * Provides the ability for player one to pass and also keeps track of how many passes taken.
     * @param event
     */
    @FXML
    public void playerOnePass(ActionEvent event) {
    	//When combination of passes from both players are equal to or greater than
    	//four calculate winner and end game
    	if (game.getUserMove().equals("Player 2")) {
    		return;
    	}
    	playerOnePasses++;
    	if ((playerOnePasses + playerTwoPasses) >= PASS_LIMIT) {
    		calculateWinner();
    		exitGame(event);
    	}
    	game.setUserMove("Player 2");
    	playerOnePassesLabel.setText("Passes: " + playerOnePasses);
    }
    
    /**
     * Provides the ability for player two to pass and also keeps track of how many passes taken.
     * @param event
     */
    @FXML
    public void playerTwoPass(ActionEvent event) {
    	//When combination of passes from both players are equal to or greater than
    	//four calculate winner and end game
    	if (game.getUserMove().equals("Player 1")) {
    		return;
    	}
    	playerTwoPasses++;
    	if ((playerOnePasses + playerTwoPasses) >= PASS_LIMIT) {
    		calculateWinner();
    		exitGame(event);
    	}
    	game.setUserMove("Player 1");
    	playerTwoPassesLabel.setText("Passes: " + playerTwoPasses);
    }
    
    /**
     * Provides the ability to save the game once it has been completed.
     * @param game
     * @return
     */
    private boolean saveGame(Game game) {
    	//Attempt to save game to disk, then manage exception if raised by alerting through GUI.
    	try {
			MainStorage.saveGame(game);
		} catch (Exception e) {
			alertUser("End Game", "Error", "Cannot save game, system error");
			return false;
		}
    	return true;
    }
    
    /**
     * Provides the ability to update users as winners or losers and then saves their updated state.
     * @param winner
     * @param loser
     */
    private void updateUsers(String winner, String loser) {
    	try {
			ArrayList<User> userList = UserStorage.getUserList();
			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getProfile().getUserName().equals(winner)) {
					userList.get(i).addGamePlayed(game.getGameID(), true);
				}
				if (userList.get(i).getProfile().getUserName().equals(loser)) {
					userList.get(i).addGamePlayed(game.getGameID(), false);
				}
			}
			MainStorage.writeUsersToMemory(userList);
		} catch (Exception e) {
			alertUser("End Game", "Error", "Cannot update user data");
		}
    }
    
    /**
     * Calculates the score of each player when 2 successive passes have happened.
     */
    private void calculateWinner() {
    	//Calculate winner by taking their captured stones and adding the dead stones from 
    	//the opposition
    	int playerOneDeadStones = game.getBoard().countDeadStones(1);
    	int playerTwoDeadStones = game.getBoard().countDeadStones(2);
    	
    	int playerOneScore = playerTwoDeadStones + playerOneCaptures;
    	int playerTwoScore = playerOneDeadStones + playerTwoCaptures;
    	
    	String playerTwo = game.getPlayerTwo().getProfile().getUserName();
    	String playerOne = game.getplayerOne().getProfile().getUserName();
    	
    	//Compare scores to see who is the winner.
    	if (playerTwoScore == playerOneScore) {
    		alertUser("Draw", "Draw", "Both players have the same points");
    	} else if (playerTwoScore > playerOneScore) {
    		int finalScore = (playerOneDeadStones + playerTwoCaptures);
    		alertUser(playerTwo + " wins", "Score: " + finalScore, "Player two captures: " 
    	    + playerTwoCaptures + ", opposition deadstones: " + playerOneDeadStones);
        	updateUsers(playerTwo, playerOne);
    	} else {
    		int finalScore = (playerTwoDeadStones + playerOneCaptures);
    		alertUser(playerOne + " wins", "Score: " + finalScore, "Player one captures: " 
    	    + playerOneCaptures + ", opposition deadstones: " + playerTwoDeadStones);
        	updateUsers(playerOne, playerTwo);
    	}
    	//Save game to disk when finished.
    	saveGame(game);
    }
    
    /**
     * Creates the grid intersections for the board to play the game Go94.
     */
    public void createGrid() {
    	//Create new grid by adding intersections.
    	for (int i = 0; i < GRID_DIMENSION; ++i) {
    		for (int j = 0; j < GRID_DIMENSION; j++) {	
    			Line line = new Line();
    			line.setStartX(HALF_NODE_WIDTH);
    			line.setStartY(0.0f);
    			line.setEndX(HALF_NODE_WIDTH);
    			line.setEndY(NODE_WIDTH);
    			Insets inset = new Insets(0.0d, 0d, 0d, HALF_NODE_WIDTH);
    			GridPane.setMargin(line, inset);
    			Grid.add(line, i, j);
    			
    			Line liney = new Line();
    			liney.setStartX(0.0f);
    			liney.setStartY(HALF_NODE_WIDTH);
    			liney.setEndX(NODE_WIDTH);
    			liney.setEndY(HALF_NODE_WIDTH);
    			Insets inset2 = new Insets(0.0d, 0d, 0d, 0.0d);
    			GridPane.setMargin(liney, inset2);
    			Grid.add(liney, i, j);
    		}
    	}
    } 
}
