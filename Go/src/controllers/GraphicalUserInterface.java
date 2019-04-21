package controllers;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import models.User;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class GraphicalUserInterface {
    public void goToView(String view, ActionEvent e) throws IOException {
        Parent newView = FXMLLoader.load(getClass().getResource("/Views/" + view + ".fxml"));
        Scene s = new Scene(newView);
        Stage stage = (Stage) ((Node) e.getTarget()).getScene().getWindow();
        stage.setScene(s);
    }
    
    public void goToViewWithUsers(String view, ActionEvent e, ArrayList<User> obj) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/" + view + ".fxml"));
        Scene s = new Scene(loader.load());
        if(view.equals("GameGrid")) {
        	GameGridController controller = loader.<GameGridController>getController();
        	controller.initData(obj);
        } else if(view.equals("AdminDashboard")) {
        	AdminDashboardController controller = loader.<AdminDashboardController>getController();
        	controller.initData(obj);
        } else {
        	UserDashboardController controller = loader.<UserDashboardController>getController();
        	controller.initData(obj);
        }
        Stage stage = (Stage) ((Node) e.getTarget()).getScene().getWindow();
        stage.setScene(s);
    }

    public void alertUser(String title, String header, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
