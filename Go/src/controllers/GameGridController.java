package controllers;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class GameGridController {
	
	private static int[][] game;
	
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
    	game = new int[9][9];

    	

    }
    
    @FXML
    public void clickGrid(MouseEvent e) {
    	
    	
        
    	int y = (int) e.getY();
    	int x = (int) e.getX();

        Integer yIndex = y / 30;
        Integer xIndex = x / 30;
        
        if(game[yIndex][xIndex] == 1) {
        	alertUser("Game", "Click", "This intersection is already occupied");
        	return;
        }

        Circle c = new Circle();
        
		c.setRadius(7.5f);

		
		
		GridPane.setHalignment(c, HPos.valueOf("CENTER"));
		GridPane.setValignment(c, VPos.valueOf("CENTER"));

		Grid.add(c, xIndex, yIndex);
        game[yIndex][xIndex] = 1;
    }
    
    
    private void alertUser(String title, String header, String content) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
    }
    
}
