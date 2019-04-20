package controllers;

import java.util.ArrayList;
import models.Game;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import models.User;

public class GameGridController extends GraphicalUserInterface {
	
	private static Game game;
	
	public void initData(ArrayList<User> obj) {
        game = new Game(obj.get(0), obj.get(1));
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

        Integer yIndex = y / 30;
        Integer xIndex = x / 30;
        Circle c = new Circle();
		c.setRadius(7.5f);
		if(game.getUserMove().equals("Player 1")) c.setFill(javafx.scene.paint.Color.BLACK);
		else c.setFill(javafx.scene.paint.Color.WHITE);
        if(game.placeStone(xIndex, yIndex).equals("SUCCESS")) {

    		GridPane.setHalignment(c, HPos.valueOf("CENTER"));
    		GridPane.setValignment(c, VPos.valueOf("CENTER"));

    		Grid.add(c, xIndex, yIndex);
        	
        } else {
        	alertUser("Move", "Invalid move", "Please choose a different intersection");
        }
    }    
}
