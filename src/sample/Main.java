package sample;

import javafx.application.Application; 
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.*;

import static sample.Controllers.ConnectingToBD.*;

public class Main extends Application {

    public static Statement statement = null;

    static {
        try {
            statement = connectToBd();
        } catch (ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Ошибка");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Ошибка подключения к базе данных");
            alert.showAndWait();
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FailFXML/Authorization.fxml"));
        primaryStage.setTitle("Дорожная карта студента");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        launch(args);
    }
}
