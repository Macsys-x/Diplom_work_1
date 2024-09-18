package sample.Controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sample.Methods;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class ControllerEnd {

    static String name_discipline, link_discipline;
    static double scoreOfDiscipline, scoreOfScienRating, scoreOfSocRating;

    @FXML
    private Button buttonBack;

    @FXML
    private Label ScoreOfDiscipline,ScoreOfScienRating, ScoreOfSocRating;

    @FXML
    private Hyperlink Link;

    @FXML
    void initialize() {
        ClickOnButtonShowResult();
    }

    @FXML
    void ClickOnButtonOutputResult() {

        try {
            File file = new File("Disciplines.xlsx");
            if (!file.exists()) {

                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Дисциплины");
                Row row = sheet.createRow(0);
                row.createCell(0).setCellValue("Название дисциплины");
                row.createCell(1).setCellValue("Количество баллов по дисциплине");
                row.createCell(2).setCellValue("Количество баллов по научному рейтингу");
                row.createCell(3).setCellValue("Количество баллов по социальному рейтингу");
                sheet.autoSizeColumn(0);
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.autoSizeColumn(3);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                workbook.write(fileOutputStream);
                workbook.close();
                fileOutputStream.close();
            }

            FileInputStream fileInputStream = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int rowLast = sheet.getLastRowNum() + 1;
            fileInputStream.close();


            Row row = sheet.createRow(rowLast);
            row.createCell(0).setCellValue(name_discipline);
            row.createCell(1).setCellValue(scoreOfDiscipline);
            row.createCell(2).setCellValue(scoreOfScienRating);
            row.createCell(3).setCellValue(scoreOfSocRating);
            sheet.autoSizeColumn(0);


            FileOutputStream fileOutputStream = new FileOutputStream("Disciplines.xlsx");
            workbook.write(fileOutputStream);
            workbook.close();
            fileOutputStream.close();
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Произошла ошибка выгрузки данных!");
            alert.showAndWait();
        }

    }

    @FXML
    void ClickOnButtonBack() {
        Methods.transition("/sample/FailFXML/SocRating.fxml", buttonBack);
    }

    @FXML
    void ClickOnButtonShowResult() {
        ScoreOfDiscipline.setText("Количество баллов по дисциплине «" + name_discipline + "»: " + scoreOfDiscipline);
        ScoreOfScienRating.setText("Количество баллов научного рейтинга: " + scoreOfScienRating);
        ScoreOfSocRating.setText("Количество баллов социального рейтинга: " + scoreOfSocRating);
    }

    @FXML
    private void openLink() throws URISyntaxException, IOException {
        try {
            Desktop.getDesktop().browse(new URI(link_discipline));
        }
        catch (NullPointerException e){
            Link.setText("Ссылка отсутствует");
        }
    }

}