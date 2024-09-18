package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Methods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static sample.Main.statement;
import static sample.Methods.isNumeric;

public class ControllerForTeachers {

    @FXML
    private TextField AddCountIndependentWork, AddCountLessons, AddCountPresentation, AddCountProject, AddCountReport,
            AddDirection, AddDisciplineLink, AddKurs, AddMarkIndependentWork, AddMarkPresentation, AddMarkProject,
            AddMarkReport, AddNameDiscipline, AddType1Control,AddType1ControlCountMark, AddType1ControlCountWorks,
            AddType2Control, AddType2ControlCountMark, AddType2ControlCountWorks;


    @FXML
    private Button buttonBack;

    static String direction, name, type1_control = null, type2_control = null, link_discipline = null;

    static int  kurs, count_lessons, type1_control_count_works, type1_control_count_mark, type2_control_count_works, type2_control_count_mark, count_project,
            mark_project, count_presentation, mark_presentation, count_report, mark_report, count_independent_work, mark_independent_work;


    @FXML
    void ClickOnButtonBack() {
        Methods.transition("/sample/FailFXML/Select.fxml", buttonBack);
    }

    @FXML
    void ClickOnButtonAddDiscipline() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (AddKurs.getText().equals("") || AddDirection.getText().equals("") || AddNameDiscipline.getText().equals("") || AddCountLessons.getText().equals("")) {
            alert.setContentText("Не все обязательные поля были заполнены!");
            alert.showAndWait();
            AddKurs.setPromptText("Необходимо заполнить поле");
            AddDirection.setPromptText("Необходимо заполнить поле");
            AddNameDiscipline.setPromptText("Необходимо заполнить поле");
            AddCountLessons.setPromptText("Необходимо заполнить поле");
        }
        else {

            direction = AddDirection.getText();
            name = AddNameDiscipline.getText();

            if (isNumeric(AddKurs.getText().trim()) && Integer.parseInt(AddKurs.getText().trim())>0){
                kurs = Integer.parseInt(AddKurs.getText().trim());
            }
            else {
                alert.setContentText("Неверное значение для поля 'Курс'");
                alert.showAndWait();
                AddKurs.setText("");
                return;
            }

            if (isNumeric(AddCountLessons.getText().trim())) {
                count_lessons = Integer.parseInt(AddCountLessons.getText().trim());
            }
            else {
                alert.setContentText("Неверное значение для поля 'Количество занятий'");
                alert.showAndWait();
                AddCountLessons.setText("");
                return;
            }

            if (!AddType1Control.getText().isEmpty())
                type1_control = AddType1Control.getText();
            else {
                alert.setContentText("Неверное значение для поля 'Тип 1-ой работы текущего и рубежного контроля'");
                alert.showAndWait();
            }

            if (!AddType1ControlCountWorks.getText().isEmpty()) {
                if (isNumeric(AddType1ControlCountWorks.getText().trim())) {
                    type1_control_count_works = Integer.parseInt(AddType1ControlCountWorks.getText().trim());
                }
            else{
                    alert.setContentText("Неверное значение для поля 'Количество работ 1-ого типа'");
                    alert.showAndWait();
                    AddType1ControlCountWorks.setText("");
                    return;
                }
            }

            if (!AddType1ControlCountMark.getText().isEmpty()) {
                if (isNumeric(AddType1ControlCountMark.getText().trim())) {
                    type1_control_count_mark = Integer.parseInt(AddType1ControlCountMark.getText().trim());
                }
                else{
                    alert.setContentText("Неверное значение для поля 'Количество баллов за работу 1-ого типа'");
                    alert.showAndWait();
                    AddType1ControlCountMark.setText("");
                    return;
                }
            }

            if (!AddType2Control.getText().isEmpty())
                type2_control = AddType2Control.getText();


            if (!AddType2ControlCountWorks.getText().isEmpty()) {
                if (isNumeric(AddType2ControlCountWorks.getText().trim())) {
                    type2_control_count_works = Integer.parseInt(AddType2ControlCountWorks.getText().trim());
                    if (AddType2Control.getText().isEmpty()){
                        alert.setContentText("Заполните поле 'Тип 2-ой работы текущего и рубежного контроля'");
                        alert.showAndWait();
                    }
                }
                else{
                    alert.setContentText("Неверное значение для поля 'Количество работ 2-ого типа'");
                    alert.showAndWait();
                    AddType2ControlCountWorks.setText("");
                    return;
                }
            }

            if (!AddType2ControlCountMark.getText().isEmpty() ) {
                if (isNumeric(AddType2ControlCountMark.getText().trim())) {
                    type2_control_count_mark = Integer.parseInt(AddType2ControlCountMark.getText().trim());
                    if (AddType2Control.getText().isEmpty()){
                        alert.setContentText("Заполните поле 'Тип 2-ой работы текущего и рубежного контроля'");
                        alert.showAndWait();
                    }
                }
                else{
                    alert.setContentText("Неверное значение для поля 'Количество баллов за работу 2-ого типа'");
                    alert.showAndWait();
                    AddType2ControlCountMark.setText("");
                    return;
                }
            }

            if (!AddCountProject.getText().isEmpty()) {
                if (isNumeric(AddCountProject.getText().trim())) {
                    count_project = Integer.parseInt(AddCountProject.getText().trim());
                }
                else {
                    alert.setContentText("Неверное значение для поля 'Количество проектов'");
                    alert.showAndWait();
                    AddCountProject.setText("");
                    return;
                }
            }

            if (!AddMarkProject.getText().isEmpty()) {
                if (isNumeric(AddMarkProject.getText().trim())) {
                    mark_project = Integer.parseInt(AddMarkProject.getText().trim());
                }
                else {
                    alert.setContentText("Неверное значение для поля 'Количество баллов за проект'");
                    alert.showAndWait();
                    AddMarkProject.setText("");
                    return;
                }
            }


            if (!AddCountPresentation.getText().isEmpty()) {
                if (isNumeric(AddCountPresentation.getText().trim())) {
                    count_presentation = Integer.parseInt(AddCountPresentation.getText().trim());
                }
                else {
                    alert.setContentText("Неверное значение для поля 'Количество презентаций'");
                    alert.showAndWait();
                    AddCountPresentation.setText("");
                    return;
                }
            }

            if (!AddMarkPresentation.getText().isEmpty()) {
                if (isNumeric(AddMarkPresentation.getText().trim())) {
                    mark_presentation = Integer.parseInt(AddMarkPresentation.getText().trim());
                }
                else {
                    alert.setContentText("Неверное значение для поля 'Количество баллов за презентацию'");
                    alert.showAndWait();
                    AddMarkPresentation.setText("");
                    return;
                }
            }

            if (!AddCountReport.getText().isEmpty()) {
                if (isNumeric(AddCountReport.getText().trim())) {
                    count_report = Integer.parseInt(AddCountReport.getText().trim());
                }
                else {
                    alert.setContentText("Неверное значение для поля 'Количество докладов'");
                    alert.showAndWait();
                    AddCountReport.setText("");
                    return;
                }
            }

            if (!AddMarkReport.getText().isEmpty()) {
                if (isNumeric(AddMarkReport.getText().trim())) {
                    mark_report = Integer.parseInt(AddMarkReport.getText().trim());
                }
                else {
                    alert.setContentText("Неверное значение для поля 'Количество баллов за доклад'");
                    alert.showAndWait();
                    AddMarkReport.setText("");
                    return;
                }
            }


            if (!AddCountIndependentWork.getText().isEmpty()) {
                if (isNumeric(AddCountIndependentWork.getText().trim())) {
                    count_independent_work = Integer.parseInt(AddCountIndependentWork.getText().trim());
                }
                else {
                    alert.setContentText("Неверное значение для поля 'Количество самостоятельных работ'");
                    alert.showAndWait();
                    AddCountIndependentWork.setText("");
                    return;
                }
            }

            if (!AddMarkIndependentWork.getText().isEmpty()) {
                if (isNumeric(AddMarkIndependentWork.getText().trim())) {
                    mark_independent_work = Integer.parseInt(AddMarkIndependentWork.getText().trim());
                }
                else {
                    alert.setContentText("Неверное значение для поля 'Количество баллов за самостоятельную работу'");
                    alert.showAndWait();
                    AddMarkIndependentWork.setText("");
                    return;
                }
            }

            if (!AddDisciplineLink.getText().isEmpty())
                link_discipline = AddDisciplineLink.getText();


            if (type1_control_count_works*type1_control_count_mark + type2_control_count_works*type2_control_count_mark != 20) {
                alert.setContentText("Количество баллов за текущий и рубежный контроль должно составлять 20 баллов");
                alert.showAndWait();
                return;
            }

            if (count_project*mark_project + count_presentation*mark_presentation + count_report*mark_report + count_independent_work*mark_independent_work != 20) {
                alert.setContentText("Количество баллов за творческий рейтинг должно составлять 20 баллов");
                alert.showAndWait();
                return;
            }

            try {
                Connection connection = statement.getConnection();
                PreparedStatement ps = connection.prepareStatement("INSERT INTO Disciplines (kurs, direction, name_discipline, count_lessons, type1_control, " +
                        "type1_control_count_works, type1_control_count_mark, type2_control, type2_control_count_works, type2_control_count_mark, " +
                        "count_project, mark_project, count_presentation, mark_presentation, count_report, mark_report, count_independent_work," +
                        "mark_independent_work,discipline_link) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                ps.setInt(1, kurs);
                ps.setString(2, direction);
                ps.setString(3, name);
                ps.setInt(4, count_lessons);
                ps.setString(5, type1_control);
                ps.setInt(6, type1_control_count_works);
                ps.setInt(7, type1_control_count_mark);
                ps.setString(8, type2_control);
                ps.setInt(9, type2_control_count_works);
                ps.setInt(10, type2_control_count_mark);
                ps.setInt(11, count_project);
                ps.setInt(12, mark_project);
                ps.setInt(13, count_presentation);
                ps.setInt(14, mark_presentation);
                ps.setInt(15, count_report);
                ps.setInt(16, mark_report);
                ps.setInt(17, count_independent_work);
                ps.setInt(18, mark_independent_work);
                ps.setString(19, link_discipline);
                ps.executeUpdate();

            } catch (SQLException e) {
                alert.setContentText("Произошла ошибка при добавлении дисциплины!");
                alert.showAndWait();
                throw new RuntimeException(e);
            }

            AddKurs.setText("");
            AddDirection.setText("");
            AddNameDiscipline.setText("");
            AddCountLessons.setText("");
            AddType1Control.setText("");
            AddType1ControlCountWorks.setText("");
            AddType1ControlCountMark.setText("");
            AddType2Control.setText("");
            AddType2ControlCountWorks.setText("");
            AddType2ControlCountMark.setText("");
            AddCountProject.setText("");
            AddMarkProject.setText("");
            AddCountPresentation.setText("");
            AddMarkPresentation.setText("");
            AddCountReport.setText("");
            AddMarkReport.setText("");
            AddCountIndependentWork.setText("");
            AddMarkIndependentWork.setText("");
            AddDisciplineLink.setText("");
        }
    }
}