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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import models.User;

public class GameGridController extends GraphicalUserInterface {
	
	private static Game game;
	private int playerOnePasses = 0;
	private int playerTwoPasses = 0;
	
	@FXML
	private Label playerOneTag;
	@FXML
	private Label playerTwoTag;
	
	public void initData(ArrayList<User> obj) {
        game = new Game(obj.get(0), obj.get(1));
        playerOneTag.setText(obj.get(0).getProfile().getUserName());
        playerTwoTag.setText(obj.get(1).getProfile().getUserName());
	}
	
	@FXML
	private GridPane Grid;
    @FXML
    public void initialize() {
      	    
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
    
    @FXML
    public void clickGrid(MouseEvent e) {

    	int y = (int) e.getY();
    	int x = (int) e.getX();
        
    	int nodeWidth = (int) (Grid.getWidth()/Grid.getColumnCount());
        int yIndex = y / nodeWidth;
        int xIndex = x / nodeWidth;
        Circle c = new Circle();
		c.setRadius((int) (Grid.getWidth()/Grid.getColumnCount())/4);
		int oppositePlayer = 0;
		if(game.getUserMove().equals("Player 1")) {
			playerOnePasses = 0;
			oppositePlayer = 2;
			c.setFill(javafx.scene.paint.Color.BLACK);
		} else { 
			oppositePlayer = 1;
			playerTwoPasses = 0;
			c.setFill(javafx.scene.paint.Color.WHITE);
		}
        if(game.placeStone(yIndex, xIndex).equals("SUCCESS")) {
    		GridPane.setHalignment(c, HPos.valueOf("CENTER"));
    		GridPane.setValignment(c, VPos.valueOf("CENTER"));

    		Grid.add(c, xIndex, yIndex);
    		
        	
        } else {
        	alertUser("Move", "Invalid move", "Please choose a different intersection");
        }
        Board b = game.getBoard();
		boolean[][] connected = b.connectedStones(oppositePlayer);
		if(connected != null) {
		  b.replaceConnectedStones(connected);
		  for(int i = 0; i < connected.length; i++) {
		    for(int j = 0; j < connected[i].length; j++) {
			  if(connected[i][j]) {
				removeCircle(Grid, i, j);
			  }
			}		
		  }
	   }
    }
    
    public void removeCircle(GridPane grid, int row, int column) {
    	ObservableList<Node> childrens = grid.getChildren();
    	for(Node node : childrens) {
    	    if(node instanceof Circle && grid.getRowIndex(node) == row && grid.getColumnIndex(node) == column) {
    	        grid.getChildren().remove(node);
    	        break;
    	    }
    	}
    }
    
    @FXML
    public void forfeitPlayerOne(ActionEvent event) {
    	
    	game.setLoser(game.getplayerOne().getProfile().getUserName());
    	game.setWinner(game.getPlayerTwo().getProfile().getUserName());
    	updateUsers(game.getPlayerTwo().getProfile().getUserName(), game.getplayerOne().getProfile().getUserName());
    	saveGame(game);
    	alertUser("Forfeit", "Forfeit", "Forfeit player one");
    	exitGame(event);
    }
    
    @FXML
    public void forfeitPlayerTwo(ActionEvent event) {
    	
    	game.setLoser(game.getPlayerTwo().getProfile().getUserName());
    	game.setWinner(game.getplayerOne().getProfile().getUserName());
    	updateUsers(game.getplayerOne().getProfile().getUserName(), game.getPlayerTwo().getProfile().getUserName());
    	saveGame(game);
    	alertUser("Forfeit", "Forfeit", "Forfeit player two");
    	exitGame(event);
    }
    
    @FXML
    public void exitGame(ActionEvent event) {
    	try {
			goToView("EntryDashboard", event);
		} catch (IOException e) {
			alertUser("End Game", "Error", "Cannot leave game, system error");
		}
    }
    
    private boolean saveGame(Game game) {
    	try {
			return MainStorage.saveGame(game);
		} catch (Exception e) {
			alertUser("End Game", "Error", "Cannot save game, system error");
			e.printStackTrace();
		}
    	return true;
    }
    
    private void updateUsers(String winner, String loser) {
    	try {
			ArrayList<User> userList = MainStorage.getUserList();
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
			e.printStackTrace();
		}
    }
    
    @FXML
    public void playerOnePass() {
    	
    }
    
    @FXML
    public void playerTwoPass() {
    	
    }
    
}
