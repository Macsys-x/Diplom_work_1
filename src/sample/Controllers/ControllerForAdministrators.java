package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Methods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static sample.Controllers.ConnectingToBD.GetValuesFromBD;
import static sample.Main.statement;
import static sample.Methods.isNumeric;

public class ControllerForAdministrators {

    @FXML
    private TextField EditCountIndependentWork, EditCountLessons, EditCountPresentation, EditCountProject, EditCountReport,
            EditDisciplineLink, EditMarkIndependentWork, EditMarkPresentation, EditMarkProject, EditMarkReport, EditType1Control,
            EditType1ControlCountMark, EditType1ControlCountWorks, EditType2Control, EditType2ControlCountMark, EditType2ControlCountWorks;

    @FXML
    private MenuButton EditDirection;
    @FXML
    private MenuButton EditKurs;
    @FXML
    private MenuButton EditNameDiscipline;
    @FXML
    private Button buttonBack;

    static String direction, name, type1_control, type2_control, link_discipline;

    static int  kurs, count_lessons, type1_control_count_works, type1_control_count_mark, type2_control_count_works, type2_control_count_mark, count_project,
            mark_project, count_presentation, mark_presentation, count_report, mark_report, count_independent_work, mark_independent_work;

    @FXML
    void initialize() throws SQLException {

        ResultSet RS = statement.executeQuery("Select kurs from Disciplines");
        GetValuesFromBD(RS, "kurs", EditKurs, eventSelectKurs);
    }
        @FXML
    void ClickOnButtonBack() {
        Methods.transition("/sample/FailFXML/Select.fxml", buttonBack);
    }

    @FXML
    void ClickOnButtonDelete() {
        try {
            statement.executeUpdate("DELETE FROM Disciplines WHERE kurs = " + kurs + " and direction = '" + direction + "' and name_discipline = '" +
                    name + "'");
            UpdateInformation();


        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Ошибка удаления дисциплины");
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }

    EventHandler<ActionEvent> eventSelectDiscipline = event -> {
        MenuItem menuItem = (MenuItem) event.getSource();
        MenuButton parentMenuButton = (MenuButton) menuItem.getParentPopup().getOwnerNode();
        parentMenuButton.setText(menuItem.getText());
        name = menuItem.getText();
        try {
            ResultSet RS = statement.executeQuery("Select * from Disciplines where kurs = " + kurs + " and direction = '" + direction + "' and name_discipline = '" +
                    name + "'");
            if (RS.next()) {
                count_lessons =  RS.getInt("count_lessons");
                EditCountLessons.setText(String.valueOf(count_lessons));
                type1_control = RS.getString("type1_control");
                EditType1Control.setText(String.valueOf(type1_control));
                type1_control_count_works = RS.getInt("type1_control_count_works");
                EditType1ControlCountWorks.setText(String.valueOf(type1_control_count_works));
                type1_control_count_mark = RS.getInt("type1_control_count_mark");
                EditType1ControlCountMark.setText(String.valueOf(type1_control_count_mark));
                if(RS.getString("type2_control")!=null) {
                    type2_control = RS.getString("type2_control");
                    EditType2Control.setText(String.valueOf(type2_control));
                }
                if(RS.getInt("type2_control_count_works")!=0){
                    type2_control_count_works = RS.getInt("type2_control_count_works");
                    EditType2ControlCountWorks.setText(String.valueOf(type2_control_count_works));
                }

                if(RS.getInt("type2_control_count_mark")!=0) {
                    type2_control_count_mark = RS.getInt("type2_control_count_mark");
                    EditType2ControlCountMark.setText(String.valueOf(type2_control_count_mark));
                }

                if(RS.getInt("count_project")!=0) {
                    count_project = RS.getInt("count_project");
                    EditCountProject.setText(String.valueOf(count_project));
                }

                if(RS.getInt("mark_project")!=0) {
                    mark_project = RS.getInt("mark_project");
                    EditMarkProject.setText(String.valueOf(mark_project));
                }

                if(RS.getInt("count_presentation")!=0) {
                    count_presentation = RS.getInt("count_presentation");
                    EditCountPresentation.setText(String.valueOf(count_presentation));
                }

                if(RS.getInt("mark_presentation")!=0) {
                    mark_presentation = RS.getInt("mark_presentation");
                    EditMarkPresentation.setText(String.valueOf(mark_presentation));
                }

                if(RS.getInt("count_report")!=0) {
                    count_report = RS.getInt("count_report");
                    EditCountReport.setText(String.valueOf(count_report));
                }

                if(RS.getInt("mark_report")!=0) {
                    mark_report = RS.getInt("mark_report");
                    EditMarkReport.setText(String.valueOf(mark_report));
                }

                if(RS.getInt("count_independent_work")!=0) {
                    count_independent_work = RS.getInt("count_independent_work");
                    EditCountIndependentWork.setText(String.valueOf(count_independent_work));
                }

                if(RS.getInt("mark_independent_work")!=0) {
                    mark_independent_work = RS.getInt("mark_independent_work");
                    EditMarkIndependentWork.setText(String.valueOf(mark_independent_work));
                }

                if(RS.getString("discipline_link")!=null) {
                    link_discipline = RS.getString("discipline_link");
                    EditDisciplineLink.setText(String.valueOf(link_discipline));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    EventHandler<ActionEvent> eventSelectDirection = event -> {
        EditNameDiscipline.setText("Дисциплина");
        EditCountLessons.setText("");
        EditType1Control.setText("");
        EditType1ControlCountWorks.setText("");
        EditType1ControlCountMark.setText("");
        EditType2Control.setText("");
        EditType2ControlCountWorks.setText("");
        EditType2ControlCountMark.setText("");
        EditCountProject.setText("");
        EditMarkProject.setText("");
        EditCountPresentation.setText("");
        EditMarkPresentation.setText("");
        EditCountReport.setText("");
        EditMarkReport.setText("");
        EditCountIndependentWork.setText("");
        EditMarkIndependentWork.setText("");
        EditDisciplineLink.setText("");
        MenuItem menuItem = (MenuItem) event.getSource();
        MenuButton parentMenuButton = (MenuButton) menuItem.getParentPopup().getOwnerNode();
        parentMenuButton.setText(menuItem.getText());
        direction = menuItem.getText();
        ResultSet RS;
        String select = "Select name_discipline from Disciplines where kurs = " + kurs + " and direction = '" +direction+"'";
        try {
            RS = statement.executeQuery(select);
            GetValuesFromBD(RS, "name_discipline", EditNameDiscipline, eventSelectDiscipline);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };


    EventHandler<ActionEvent> eventSelectKurs = event -> {
        EditDirection.setText("Направление обучения");
        EditNameDiscipline.setText("Дисциплина");
        MenuItem menuItem = (MenuItem) event.getSource();
        MenuButton parentMenuButton = (MenuButton) menuItem.getParentPopup().getOwnerNode();
        parentMenuButton.setText(menuItem.getText());
        kurs = Integer.parseInt(menuItem.getText());
        ResultSet RS;
        try {
            RS = statement.executeQuery("Select direction from Disciplines where kurs = "+kurs);
            GetValuesFromBD(RS, "direction", EditDirection, eventSelectDirection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    };

    @FXML
    void UpdateInformation() throws SQLException {
        EditKurs.setText("Курс");
        EditDirection.setText("Направление обучения");
        EditNameDiscipline.setText("Дисциплина");
        EditCountLessons.setText("");
        EditType1Control.setText("");
        EditType1ControlCountWorks.setText("");
        EditType1ControlCountMark.setText("");
        EditType2Control.setText("");
        EditType2ControlCountWorks.setText("");
        EditType2ControlCountMark.setText("");
        EditCountProject.setText("");
        EditMarkProject.setText("");
        EditCountPresentation.setText("");
        EditMarkPresentation.setText("");
        EditCountReport.setText("");
        EditMarkReport.setText("");
        EditCountIndependentWork.setText("");
        EditMarkIndependentWork.setText("");
        EditDisciplineLink.setText("");
        ResultSet RS = statement.executeQuery("Select kurs from Disciplines");
        GetValuesFromBD(RS, "kurs", EditKurs, eventSelectKurs);
    }

    @FXML
    void ClickOnButtonSaveEdits() {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (EditCountLessons.getText().equals("")) {
            alert.setContentText("Не все обязательные поля были заполнены!");
            alert.showAndWait();
            EditCountLessons.setPromptText("Необходимо заполнить поле");
        }
        else {

            direction = EditDirection.getText();
            name = EditNameDiscipline.getText();

            if (isNumeric(EditKurs.getText().trim()) && Integer.parseInt(EditKurs.getText().trim())>0){
                kurs = Integer.parseInt(EditKurs.getText().trim());
            }
            else {
                alert.setContentText("Неверное значение для поля 'Курс'");
                alert.showAndWait();
                EditKurs.setText("");
                return;
            }

            if (isNumeric(EditCountLessons.getText().trim())) {
                count_lessons = Integer.parseInt(EditCountLessons.getText().trim());
            }
            else {
                alert.setContentText("Неверное значение для поля 'Количество занятий'");
                alert.showAndWait();
                EditCountLessons.setText("");
                return;
            }

            type1_control = EditType1Control.getText();

            if (!EditType1ControlCountWorks.getText().isEmpty()) {
                if (isNumeric(EditType1ControlCountWorks.getText().trim())) {
                    type1_control_count_works = Integer.parseInt(EditType1ControlCountWorks.getText().trim());
                }
                else{
                    alert.setContentText("Неверное значение для поля 'Количество работ 1-ого типа'");
                    alert.showAndWait();
                    EditType1ControlCountWorks.setText("");
                    return;
                }
            }
            else {
                type1_control_count_works = 0;
            }

            if (!EditType1ControlCountMark.getText().isEmpty()) {
                if (isNumeric(EditType1ControlCountMark.getText().trim())) {
                    type1_control_count_mark = Integer.parseInt(EditType1ControlCountMark.getText().trim());
                }
                else{
                    alert.setContentText("Неверное значение для поля 'Количество баллов за работу 1-ого типа'");
                    alert.showAndWait();
                    EditType1ControlCountMark.setText("");
                    return;
                }
            }
            else {
                type1_control_count_mark = 0;
            }

            if (!EditType2Control.getText().isEmpty()){
                type2_control = EditType2Control.getText();
            }
            else {
                type2_control = null;
            }


            if (!EditType2ControlCountWorks.getText().isEmpty()) {
                if (isNumeric(EditType2ControlCountWorks.getText().trim())) {
                    type2_control_count_works = Integer.parseInt(EditType2ControlCountWorks.getText().trim());
                }
                else{
                    alert.setContentText("Неверное значение для поля 'Количество работ 2-ого типа'");
                    alert.showAndWait();
                    EditType2ControlCountWorks.setText("");
                    return;
                }
            }
            else {
                type2_control_count_works = 0;
            }


            if (!EditType2ControlCountMark.getText().isEmpty() ) {
                if (isNumeric(EditType2ControlCountMark.getText().trim())) {
                    type2_control_count_mark = Integer.parseInt(EditType2ControlCountMark.getText().trim());
                }
                else{
                    alert.setContentText("Неверное значение для поля 'Количество баллов за работу 2-ого типа'");
                    alert.showAndWait();
                    EditType2ControlCountMark.setText("");
                    return;
                }
            }
            else {
                type2_control_count_mark = 0;
            }


            if (!EditCountProject.getText().isEmpty()) {
                if (isNumeric(EditCountProject.getText().trim())) {
                    count_project = Integer.parseInt(EditCountProject.getText().trim());
                }
                else {
                    alert.setContentText("Неверное значение для поля 'Количество проектов'");
                    alert.showAndWait();
                    EditCountProject.setText("");
                    return;
                }
            }
            else {
                count_project = 0;
            }



            if (!EditMarkProject.getText().isEmpty()) {
                if (isNumeric(EditMarkProject.getText().trim())) {
                    mark_project = Integer.parseInt(EditMarkProject.getText().trim());
                }
                else {
                    alert.setContentText("Неверное значение для поля 'Количество баллов за проект'");
                    alert.showAndWait();
                    EditMarkProject.setText("");
                    return;
                }
            }
            else {
                mark_project = 0;
            }


            if (!EditCountPresentation.getText().isEmpty()) {
                if (isNumeric(EditCountPresentation.getText().trim())) {
                    count_presentation = Integer.parseInt(EditCountPresentation.getText().trim());
                }
                else {
                    alert.setContentText("Неверное значение для поля 'Количество презентаций'");
                    alert.showAndWait();
                    EditCountPresentation.setText("");
                    return;
                }
            }
            else {
                count_presentation = 0;
            }


            if (!EditMarkPresentation.getText().isEmpty()) {
                if (isNumeric(EditMarkPresentation.getText().trim())) {
                    mark_presentation = Integer.parseInt(EditMarkPresentation.getText().trim());
                }
                else {
                    alert.setContentText("Неверное значение для поля 'Количество баллов за презентацию'");
                    alert.showAndWait();
                    EditMarkPresentation.setText("");
                    return;
                }
            }
            else {
                mark_presentation = 0;
            }


            if (!EditCountReport.getText().isEmpty()) {
                if (isNumeric(EditCountReport.getText().trim())) {
                    count_report = Integer.parseInt(EditCountReport.getText().trim());
                }
                else {
                    alert.setContentText("Неверное значение для поля 'Количество докладов'");
                    alert.showAndWait();
                    EditCountReport.setText("");
                    return;
                }
            }
            else {
                count_report = 0;
            }

            if (!EditMarkReport.getText().isEmpty()) {
                if (isNumeric(EditMarkReport.getText().trim())) {
                    mark_report = Integer.parseInt(EditMarkReport.getText().trim());
                }
                else {
                    alert.setContentText("Неверное значение для поля 'Количество баллов за доклад'");
                    alert.showAndWait();
                    EditMarkReport.setText("");
                    return;
                }
            }
            else {
                mark_report = 0;
            }


            if (!EditCountIndependentWork.getText().isEmpty()) {
                if (isNumeric(EditCountIndependentWork.getText().trim())) {
                    count_independent_work = Integer.parseInt(EditCountIndependentWork.getText().trim());
                }
                else {
                    alert.setContentText("Неверное значение для поля 'Количество самостоятельных работ'");
                    alert.showAndWait();
                    EditCountIndependentWork.setText("");
                    return;
                }
            }
            else {
                count_independent_work = 0;
            }

            if (!EditMarkIndependentWork.getText().isEmpty()) {
                if (isNumeric(EditMarkIndependentWork.getText().trim())) {
                    mark_independent_work = Integer.parseInt(EditMarkIndependentWork.getText().trim());
                }
                else {
                    alert.setContentText("Неверное значение для поля 'Количество баллов за самостоятельную работу'");
                    alert.showAndWait();
                    EditMarkIndependentWork.setText("");
                    return;
                }
            }
            else {
                mark_independent_work = 0;
            }


            if (!EditDisciplineLink.getText().isEmpty())
                link_discipline = EditDisciplineLink.getText();
            else
                link_discipline = null;


            if (type1_control_count_works * type1_control_count_mark + type2_control_count_works * type2_control_count_mark != 20) {
                alert.setContentText("Количество баллов за текущий и рубежный контроль должно составлять 20 баллов");
                alert.showAndWait();
                return;
            }

            if (count_project * mark_project + count_presentation * mark_presentation + count_report * mark_report + count_independent_work * mark_independent_work != 20) {
                alert.setContentText("Количество баллов за творческий рейтинг должно составлять 20 баллов");
                alert.showAndWait();
                return;
            }

            try {
                Connection connection = statement.getConnection();
                PreparedStatement ps = connection.prepareStatement("UPDATE Disciplines SET count_lessons = ?, type1_control = ?, " +
                        "type1_control_count_works = ?, type1_control_count_mark = ?, type2_control = ?, type2_control_count_works = ?, type2_control_count_mark = ?, " +
                        "count_project = ?, mark_project = ?, count_presentation = ?, mark_presentation = ?, count_report = ?, mark_report = ?, count_independent_work = ?," +
                        "mark_independent_work = ?, discipline_link = ? WHERE kurs = " + kurs +
                        " and direction = '" + direction + "' and name_discipline = '" + name + "';");
                ps.setInt(1, count_lessons);
                ps.setString(2, type1_control);
                ps.setInt(3, type1_control_count_works);
                ps.setInt(4, type1_control_count_mark);
                ps.setString(5, type2_control);
                ps.setInt(6, type2_control_count_works);
                ps.setInt(7, type2_control_count_mark);
                ps.setInt(8, count_project);
                ps.setInt(9, mark_project);
                ps.setInt(10, count_presentation);
                ps.setInt(11, mark_presentation);
                ps.setInt(12, count_report);
                ps.setInt(13, mark_report);
                ps.setInt(14, count_independent_work);
                ps.setInt(15, mark_independent_work);
                ps.setString(16, link_discipline);
                ps.executeUpdate();
                UpdateInformation();

            } catch (SQLException e) {
                alert.setContentText("Произошла ошибка при сохранении дисциплины!");
                alert.showAndWait();
                throw new RuntimeException(e);
            }
        }
    }
}
