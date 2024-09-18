package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import sample.Users.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class ConnectingToBD {
    final static String userName = "example";
    final static String password = "example";
    final static String connectionUrl = "jdbc:sqlserver://localhost:1433;database=RatingRea" + ";integratedSecurity=true;" +
            "encrypt=true;trustServerCertificate=true";

    public static Statement connectToBd() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection(connectionUrl, userName, password);
        Statement statement = conn.createStatement();
        return statement;
    }

    public static ArrayList<User> GetUsersFromBD(Statement statement, String login, String password, String table) throws SQLException {

        ArrayList<User> ListUser = new ArrayList<>();
            String select = "Select " + login + ", " + password + " from " + table;

        ResultSet RS = statement.executeQuery(select);

            while (RS.next()) {
                ListUser.add(new User(RS.getString(login), RS.getString(password)));
            }

        return ListUser;
    }


    public static void GetValuesFromBD(ResultSet RS, String field, MenuButton menuButton, EventHandler<ActionEvent> eventHandler) throws SQLException {
        menuButton.getItems().clear();
        ArrayList<String> ListField = new ArrayList<>();

        while(RS.next()) {
            if (!ListField.contains(RS.getString(field))) {
                ListField.add(RS.getString(field));
            }
        }
        Collections.sort(ListField);
        for (String item :ListField) {
            MenuItem menuItem = new MenuItem(item);
            menuItem.setOnAction(eventHandler);
            menuButton.getItems().add(menuItem);
        }

    }

}