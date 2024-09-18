package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Main;
import sample.Methods;
import sample.Users.User;
import java.sql.SQLException;
import java.util.ArrayList;

import static sample.Controllers.ConnectingToBD.GetUsersFromBD;

public class ControllerAuthorization {
    static String userName;
    static String password;
    static String status = "student";
    @FXML
    public TextField LabelUserName;
    @FXML
    public PasswordField LabelPassword;
    @FXML
    public Button ButtonConnection;
    ArrayList<User> teachers, administrators;
    @FXML
    void initialize() {

        ButtonConnection.setOnAction(event -> {

            userName = LabelUserName.getText();
            password = LabelPassword.getText();

            try {
                teachers = GetUsersFromBD(Main.statement, "Login_teacher", "Password_teacher", "Teachers");
                administrators = GetUsersFromBD(Main.statement, "Login_admin", "Password_admin","Administrators");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            for (User admin : administrators) {
                if (((admin.getLogin()).equals(userName)) && ((admin.getPassword()).equals(password))) {
                    status = "administrator";
                }
            }

            for (User teach : teachers) {
                if (((teach.getLogin()).equals(userName)) && ((teach.getPassword()).equals(password))) {
                    status = "teacher";
                }
            }

            Methods.transition("/sample/FailFXML/Select.fxml", ButtonConnection);

            });

            LabelUserName.setText(userName);
            LabelPassword.setText(password);

            }


}
