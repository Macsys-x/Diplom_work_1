package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Methods;

import java.sql.ResultSet;
import java.sql.SQLException;

import static sample.Controllers.ConnectingToBD.GetValuesFromBD;
import static sample.Main.statement;
import static sample.Methods.*;

public class ControllerDisciplines {
    @FXML
    private Label AllColOfScore;

    @FXML
    private Label MARK3;

    @FXML
    private Label MARK4;

    @FXML
    private Label MARK5;

    @FXML
    private TextField amountReport;

    @FXML
    private TextField amountIndependentWork;

    @FXML
    private TextField amountPresentation;

    @FXML
    private TextField amountProject;

    @FXML
    private TextField amountWork_1;

    @FXML
    private TextField amountWork_2;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonNext;

    @FXML
    private TextField countVisits;

    @FXML
    private MenuButton discipline;

    @FXML
    private MenuButton direction_discipline;

    @FXML
    private MenuButton kurs_disciplines;

    @FXML
    private Label textCountVisitScore, Label_work1, Label_work2;

    static String direction, name, type1_control, type2_control, link_discipline;

    static int  kurs, count_lessons, type1_control_count_works, type1_control_count_mark, type2_control_count_works, type2_control_count_mark, count_project,
    mark_project, count_presentation, mark_presentation, count_report, mark_report, count_independent_work, mark_independent_work;

    static double final_score, final_control_work1, final_control_work2, final_project, final_presentation, final_report, final_independent_work, all_score;

    @FXML
    void initialize() throws SQLException {

        ResultSet RS = statement.executeQuery("Select kurs from Disciplines");
        GetValuesFromBD(RS, "kurs", kurs_disciplines, eventSelectKurs);

        FieldAction();
        buttonBack.setOnAction(event -> transition("/sample/FailFXML/Select.fxml", buttonBack));

    }

    @FXML
    void ClickOnButtonNext() {
        Methods.transition("/sample/FailFXML/ScienRating.fxml", buttonNext);
        ClickOnButtonCalculate();
    }

    @FXML
    void FieldAction() {
        amountWork_1.setOnAction(event -> final_control_work1 = ScoreOfWork(type1_control_count_works, type1_control_count_mark, amountWork_1));
        amountWork_2.setOnAction(event -> final_control_work2 = ScoreOfWork(type2_control_count_works, type2_control_count_mark, amountWork_2));
        amountProject.setOnAction(event -> final_project = ScoreOfWork(count_project, mark_project, amountProject));
        amountPresentation.setOnAction(event -> final_presentation = ScoreOfWork(count_presentation, mark_presentation, amountPresentation));
        amountReport.setOnAction(event -> final_report = ScoreOfWork(count_report, mark_report, amountReport));
        amountIndependentWork.setOnAction(event -> final_independent_work = ScoreOfWork(count_independent_work, mark_independent_work, amountIndependentWork));
    }

    @FXML
    void ClickOnButtonCalculate() {
        eventScoreVisit();
        final_control_work1 = ScoreOfWork(type1_control_count_works, type1_control_count_mark, amountWork_1);
        final_control_work2 = ScoreOfWork(type2_control_count_works, type2_control_count_mark, amountWork_2);
        final_project = ScoreOfWork(count_project, mark_project, amountProject);
        final_presentation = ScoreOfWork(count_presentation, mark_presentation, amountPresentation);
        final_report = ScoreOfWork(count_report, mark_report, amountReport);
        final_independent_work = ScoreOfWork(count_independent_work, mark_independent_work, amountIndependentWork);
        all_score = final_score+final_control_work1 + final_control_work2 + final_project + final_presentation + final_report + final_independent_work;
        AllColOfScore.setText("Общее количество баллов: "+ all_score);
        if (all_score < 50){
            MARK3.setText(String.valueOf(50-all_score) + " баллов");
            MARK4.setText(String.valueOf(70-all_score) + " баллов");
            MARK5.setText(String.valueOf(85-all_score) + " баллов");
        }

        if (all_score >=50 && all_score < 70)
        {
            MARK3.setText("0 баллов. Баллов хватает для оценки!");
            MARK4.setText(String.valueOf(70-all_score) + " баллов");
            MARK5.setText(String.valueOf(85-all_score) + " баллов");
        }
        if (all_score < 85 && all_score >= 70)
        {
            MARK3.setText("0 баллов. Баллов хватает для оценки!");
            MARK4.setText("0 баллов. Баллов хватает для оценки!");
            MARK5.setText(String.valueOf(85-all_score) + " баллов");
        }

        ControllerEnd.scoreOfDiscipline = all_score;
        ControllerEnd.name_discipline = name;
        ControllerEnd.link_discipline = link_discipline;

    }

    @FXML
    void eventScoreVisit()  { final_score = AmountVisit(count_lessons, textCountVisitScore, countVisits);    };

    EventHandler<ActionEvent> eventSelectDiscipline = event -> {
        MenuItem menuItem = (MenuItem) event.getSource();
        MenuButton parentMenuButton = (MenuButton) menuItem.getParentPopup().getOwnerNode();
        parentMenuButton.setText(menuItem.getText());
        name = menuItem.getText();
        try {
            ResultSet RS = statement.executeQuery("Select * from Disciplines where kurs = " + kurs + " and direction = '" + direction + "' and name_discipline = '" +
                    name + "'");
            if (RS.next()) {
                count_lessons = RS.getInt("count_lessons");
                type1_control = RS.getString("type1_control");
                type1_control_count_works = RS.getInt("type1_control_count_works");
                type1_control_count_mark = RS.getInt("type1_control_count_mark");
                type2_control = RS.getString("type2_control");
                type2_control_count_works = RS.getInt("type2_control_count_works");
                type2_control_count_mark = RS.getInt("type2_control_count_mark");
                count_project = RS.getInt("count_project");
                mark_project = RS.getInt("mark_project");
                count_presentation = RS.getInt("count_presentation");
                mark_presentation = RS.getInt("mark_presentation");
                count_report = RS.getInt("count_report");
                mark_report = RS.getInt("mark_report");
                count_independent_work = RS.getInt("count_independent_work");
                mark_independent_work = RS.getInt("mark_independent_work");
                link_discipline = RS.getString("discipline_link");

            }
            if (type1_control != null) {
                Label_work1.setText("Количество выполненных работ - " + type1_control);
            }
            else {
                amountWork_1.setDisable(true);
            }

            if ((type2_control != null) && (!type1_control.isEmpty())) {
                Label_work2.setText("Количество выполненных работ - " + type2_control);
            }
            else {
                amountWork_2.setDisable(true);
            }
            IsOrNotDisable(count_project, amountProject);
            IsOrNotDisable(count_presentation, amountPresentation);
            IsOrNotDisable(count_report, amountReport);
            IsOrNotDisable(count_independent_work, amountIndependentWork);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    };

    EventHandler<ActionEvent> eventSelectDirection = event -> {
        discipline.setText("Дисциплина");
        MenuItem menuItem = (MenuItem) event.getSource();
        MenuButton parentMenuButton = (MenuButton) menuItem.getParentPopup().getOwnerNode();
        parentMenuButton.setText(menuItem.getText());
        direction = menuItem.getText();
        ResultSet RS = null;
        String select = "Select name_discipline from Disciplines where kurs = " + kurs + " and direction = '" +direction+"'";
        try {
            RS = statement.executeQuery(select);
            GetValuesFromBD(RS, "name_discipline", discipline, eventSelectDiscipline);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };


    EventHandler<ActionEvent> eventSelectKurs = event -> {
        direction_discipline.setText("Направление обучения");
        discipline.setText("Дисциплина");
        MenuItem menuItem = (MenuItem) event.getSource();
        MenuButton parentMenuButton = (MenuButton) menuItem.getParentPopup().getOwnerNode();
        parentMenuButton.setText(menuItem.getText());
        kurs = Integer.parseInt(menuItem.getText());
        ResultSet RS = null;
        try {
            RS = statement.executeQuery("Select direction from Disciplines where kurs = "+kurs);
            GetValuesFromBD(RS, "direction", direction_discipline, eventSelectDirection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };
}

