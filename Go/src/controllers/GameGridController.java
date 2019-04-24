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
 * @author farisktit
 * @version 1.9
 */
public class GameGridController extends GraphicalUserInterface {
	
	private final int NODE_WIDTH = 30;
	private Game game;
	private int playerOnePasses = 0;
	private int playerTwoPasses = 0;
	private int playerOneCaptures = 0;
	private int playerTwoCaptures = 0;
	private int playerOneDeadStones = 0;
	private int playerTwoDeadStones = 0;
	
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
        game = new Game(obj.get(0), obj.get(1));
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
    	int y = (int) e.getY();
    	int x = (int) e.getX();
        
    	int nodeWidth = 30;
        int yIndex = y / nodeWidth;
        int xIndex = x / nodeWidth;
        
        Circle c = new Circle();
		c.setRadius(7.5);
		
		int currentPlayer = 0;
		int oppositePlayer = 0;
		if(game.getUserMove().equals("Player 1")) {
			currentPlayer = 1;
			oppositePlayer = 2;
			playerOnePasses = 0;
			playerOnePassesLabel.setText("Passes: " + playerOnePasses);
			
			c.setFill(javafx.scene.paint.Color.BLACK);
		} else { 
			currentPlayer = 2;
			oppositePlayer = 1;
			playerTwoPasses = 0;
			playerTwoPassesLabel.setText("Passes: " + playerTwoPasses);
			c.setFill(javafx.scene.paint.Color.WHITE);
		}
        if(game.placeStone(yIndex, xIndex).equals("SUCCESS")) {
    		GridPane.setHalignment(c, HPos.valueOf("CENTER"));
    		GridPane.setValignment(c, VPos.valueOf("CENTER"));
    		Grid.add(c, xIndex, yIndex);	
        } else {
        	alertUser("Move", "Invalid move", "Please choose a different intersection");
        }
        Board board = game.getBoard();
        removeCapturedStones(board, currentPlayer, oppositePlayer);
    }
    
    /**
     * Manages the recreation of the board after every stone placed by a player.
     * @param grid
     */
    public void recreateBoard(GridPane grid) {
    	ObservableList<Node> children = grid.getChildren();
    	grid.getChildren().removeAll(children);
    	createGrid();
    	Board board = game.getBoard();
    	int[][] occupiedBoard = board.getBoard();
    	for(int i = 0; i < occupiedBoard.length; i++) {
    		for(int j = 0; j < occupiedBoard[i].length; j++) {
    			if(occupiedBoard[i][j] != 0) {
    				Circle c = new Circle();
        			c.setRadius(7.5);
    				if(occupiedBoard[i][j] == 1) {
    					c.setFill(javafx.scene.paint.Color.BLACK);
    				} else {
    					c.setFill(javafx.scene.paint.Color.WHITE);
    				}
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
		boolean[][] connected = board.connectedStones(oppositePlayer);
		if(connected != null) {
		  board.replaceConnectedStones(connected);
		  for(int i = 0; i < connected.length; i++) {
		    for(int j = 0; j < connected[i].length; j++) {
			  if(connected[i][j]) {
				  recreateBoard(Grid);
				  if(currentPlayer == 1) {
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
    	if(game.getUserMove().equals("Player 2")) {
    		return;
    	}
    	playerOnePasses++;
    	if((playerOnePasses + playerTwoPasses) >= 4) {
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
    	if(game.getUserMove().equals("Player 1")) {
    		return;
    	}
    	playerTwoPasses++;
    	if((playerOnePasses + playerTwoPasses) >= 4) {
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
    	try {
			return MainStorage.saveGame(game);
		} catch (Exception e) {
			alertUser("End Game", "Error", "Cannot save game, system error");
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
			for(int i = 0; i < userList.size(); i++) {
				if(userList.get(i).getProfile().getUserName().equals(winner)) {
					userList.get(i).addGamePlayed(game.getGameID(), true);
				}
				if(userList.get(i).getProfile().getUserName().equals(loser)) {
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
    	int playerOneDeadStones = game.getBoard().countDeadStones(1);
    	int playerTwoDeadStones = game.getBoard().countDeadStones(2);
    	int playerOneScore = playerTwoDeadStones + playerOneCaptures;
    	int playerTwoScore = playerOneDeadStones + playerTwoCaptures;
    	if(playerTwoScore == playerOneScore) {
    		alertUser("Draw", "Draw", "Both players have the same points");
    	} else if(playerTwoScore > playerOneScore) {
    		alertUser(playerTwoTag.getText() + " wins", "Score: " + (playerOneDeadStones + playerTwoCaptures), "Player two captures: " 
    	    + playerTwoCaptures + ", opposition deadstones: " + playerOneDeadStones);
        	updateUsers(game.getPlayerTwo().getProfile().getUserName(), game.getplayerOne().getProfile().getUserName());
    	} else {
    		alertUser(playerOneTag.getText() + " wins", "Score: " + (playerTwoDeadStones + playerOneCaptures), "Player one captures: " 
    	    + playerOneCaptures + ", opposition deadstones: " + playerTwoDeadStones);
        	updateUsers(game.getplayerOne().getProfile().getUserName(), game.getPlayerTwo().getProfile().getUserName());
    	}
    	saveGame(game);
    }
    
    /**
     * Creates the grid intersections for the board to play the game Go94.
     */
    public void createGrid() {
    	for(int i = 0; i < 9; ++i) {
    		for(int j = 0; j < 9; j++) {	
    			Line line = new Line();
    			line.setStartX(15.0f);
    			line.setStartY(0.0f);
    			line.setEndX(15.0f);
    			line.setEndY(30.0f);
    			Insets inset = new Insets(0.0d, 0d, 0d, 15.0d);
    			GridPane.setMargin(line, inset);
    			Grid.add(line, i, j);
    			
    			Line liney = new Line();
    			liney.setStartX(0.0f);
    			liney.setStartY(15.0f);
    			liney.setEndX(30.0f);
    			liney.setEndY(15.0f);
    			Insets inset2 = new Insets(0.0d, 0d, 0d, 0.0d);
    			GridPane.setMargin(liney, inset2);
    			Grid.add(liney, i, j);
    		}
    	}
    }
    
}
