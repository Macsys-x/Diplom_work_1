package sample;

import javafx.fxml.FXMLLoader; 
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Methods {

    public static void transition(String fxml, Button button)
    {
        button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = Methods.class.getResource(fxml);
        loader.setLocation(xmlUrl);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Рейтинговая информационная система студента");
        stage.setScene(new Scene(root));
        stage.show();

    }

    public static double ScoreOfWork(int count_work, int mark_work, TextField field_score_work) {
        double final_mark_work = 0;
        double temp = 0;
        if (!field_score_work.getText().isEmpty()) {
            try {
                temp = Double.parseDouble(field_score_work.getText());
                if (temp <= count_work && temp >= 0) {
                    final_mark_work = temp * mark_work;
                } else {
                    throw new NumberFormatException();
                }
            }
            catch (NumberFormatException e) {
                field_score_work.setPromptText("Неверно введены данные или таких заданий нет в данной дисциплине");
                field_score_work.setText("");
            }
            finally {
                return final_mark_work;
            }
        }
        else {
            return 0;
        }
    }

    public static void IsOrNotDisable(int count, TextField field) {
        if(count<1)
            field.setDisable(true);
    }

    public static double AmountVisit( int count_lessons, Label labelVisit, TextField textFieldVisit) {
        double final_score = 0, get_visit = 0;
        try {
            get_visit = Double.parseDouble(textFieldVisit.getText());
            final_score = (20.00 / count_lessons) * get_visit;

            if (0 <= final_score && final_score <= 20) {
                labelVisit.setText("Количество баллов за посещение: " + String.valueOf(final_score));
            } else {
                throw new Exception();
            }
        }
        catch (Exception e) {
            labelVisit.setText("Вы ввели некорректные данные!");
        }
        finally {
            return final_score;
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
