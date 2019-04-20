package controllers;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class GraphicalUserInterface {
    public void goToView(String view, ActionEvent e) throws IOException {
        Parent userDash = FXMLLoader.load(getClass().getResource("/Views/" + view + ".fxml"));
        Scene s = new Scene(userDash);
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
