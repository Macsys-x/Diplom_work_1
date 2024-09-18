package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Methods;

public class ControllerSelect {

    @FXML
    private Button ButtonRatingSystem;

    @FXML
    private Button ButtonAddDisciplines;

    @FXML
    private Button ButtonEditDisciplines;

    @FXML
    private Button SelectBack;

    @FXML
    void initialize() {

        if((ControllerAuthorization.status).equals("administrator")) {
            ButtonRatingSystem.setDisable(false);
            ButtonAddDisciplines.setDisable(false);
            ButtonEditDisciplines.setDisable(false);
        }
        else if((ControllerAuthorization.status).equals("teacher")) {
            ButtonRatingSystem.setDisable(false);
            ButtonAddDisciplines.setDisable(false);
            ButtonEditDisciplines.setDisable(true);
        }
        else {
            ButtonRatingSystem.setDisable(false);
            ButtonAddDisciplines.setDisable(true);
            ButtonEditDisciplines.setDisable(true);
        }

    }
    @FXML
    void ClickOnButtonRatingSystem(ActionEvent event) {
        Methods.transition("/sample/FailFXML/Disciplines.fxml", ButtonRatingSystem);
    }

    @FXML
    void ClickOnButtonAddDisciplines(ActionEvent event) {
        Methods.transition("/sample/FailFXML/ForTeachers.fxml", ButtonAddDisciplines);
    }

    @FXML
    void ClickOnButtonEditDisciplines(ActionEvent event) {
        Methods.transition("/sample/FailFXML/ForAdministrators.fxml", ButtonEditDisciplines);
    }

    @FXML
    void ClickOnSelectBack(ActionEvent event) {
        Methods.transition("/sample/FailFXML/Authorization.fxml", SelectBack);
    }

}
