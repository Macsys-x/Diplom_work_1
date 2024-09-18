package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Methods;

import java.sql.ResultSet;
import java.sql.SQLException;

import static sample.Main.statement;

public class ControllerSocRating {

    @FXML
    private CheckBox Central_admissions_committee_CheckBox;

    @FXML
    private CheckBox Chairman_ss_CheckBox;

    @FXML
    private Label ColBallsOfSocRating;

    @FXML
    private TextField ColCentralSectionCommittee;

    @FXML
    private TextField ColFighter;

    @FXML
    private TextField ColSelectionCommissions;

    @FXML
    private TextField ColTimLider;

    @FXML
    private TextField ColVolonterReu;

    @FXML
    private CheckBox Commander_commissar_CheckBox;

    @FXML
    private CheckBox Deputy_chairman_ss_CheckBox;

    @FXML
    private CheckBox Deputy_main_organizer_committee_event_CheckBox;

    @FXML
    private CheckBox Deputy_main_organizer_universitywide_event_CheckBox;

    @FXML
    private CheckBox Dorm_captain_CheckBox;

    @FXML
    private CheckBox Fighter_CheckBox;

    @FXML
    private CheckBox Head_committee_CheckBox;

    @FXML
    private CheckBox Head_group_CheckBox;

    @FXML
    private CheckBox Main_organizer_committee_event_CheckBox;

    @FXML
    private CheckBox Main_organizer_universitywide_event_CheckBox;

    @FXML
    private CheckBox Selection_committee_CheckBox;

    @FXML
    private CheckBox Student_curator_CheckBox;

    @FXML
    private CheckBox Team_leader_CheckBox;

    @FXML
    private CheckBox Team_main_committee_event_CheckBox;

    @FXML
    private CheckBox Team_main_universitywide_event_CheckBox;

    @FXML
    private CheckBox Volunteer_CheckBox;

    @FXML
    private Button buttonBack, buttonNext;

    int Student_curator, Head_group, Chairman_ss, Deputy_chairman_ss, Head_committee, Dorm_captain, Main_organizer_universitywide_event,
            Deputy_main_organizer_universitywide_event, Team_main_universitywide_event, Main_organizer_committee_event,
            Deputy_main_organizer_committee_event, Team_main_committee_event, Commander_commissar,Central_admissions_committee, Selection_committee;
    double final_score_socRating, Team_leader, Volunteer, Fighter;


    @FXML
    void initialize() {

        try {
            ResultSet RS = statement.executeQuery("Select * from SocialRating");
            if (RS.next()) {
                Student_curator = RS.getInt("Student_curator");
                Head_group = RS.getInt("Head_group");
                Chairman_ss = RS.getInt("Chairman_ss");
                Deputy_chairman_ss = RS.getInt("Deputy_chairman_ss");
                Head_committee = RS.getInt("Head_committee");
                Dorm_captain = RS.getInt("Dorm_captain");
                Team_leader = RS.getDouble("Team_leader");
                Volunteer = RS.getDouble("Volunteer");
                Main_organizer_universitywide_event = RS.getInt("Main_organizer_universitywide_event");
                Deputy_main_organizer_universitywide_event = RS.getInt("Deputy_main_organizer_universitywide_event");
                Team_main_universitywide_event = RS.getInt("Team_main_universitywide_event");
                Main_organizer_committee_event = RS.getInt("Main_organizer_committee_event");
                Deputy_main_organizer_committee_event = RS.getInt("Deputy_main_organizer_committee_event");
                Team_main_committee_event = RS.getInt("Team_main_committee_event");
                Commander_commissar = RS.getInt("Commander_commissar");
                Fighter = RS.getDouble("Fighter");
                Central_admissions_committee = RS.getInt("Central_admissions_committee");
                Selection_committee = RS.getInt("Selection_committee");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void ClickOnButtonBack(ActionEvent event) {
        Methods.transition("/sample/FailFXML/ScienRating.fxml", buttonBack);
    }

    @FXML
    void ClickOnButtonNext(ActionEvent event) {
        ClickOnButtonCalculate(event);
        Methods.transition("/sample/FailFXML/End.fxml", buttonNext);
    }

    @FXML
    void ClickOnButtonCalculate(ActionEvent event) {
        final_score_socRating = 0;

        if(Student_curator_CheckBox.isSelected())
            final_score_socRating+=Student_curator;

        if(Head_group_CheckBox.isSelected())
            final_score_socRating+=Head_group;

        if(Chairman_ss_CheckBox.isSelected())
            final_score_socRating+=Chairman_ss;

        if (Deputy_chairman_ss_CheckBox.isSelected())
            final_score_socRating+=Deputy_chairman_ss;

        if (Head_committee_CheckBox.isSelected())
            final_score_socRating+=Head_committee;

        if(Dorm_captain_CheckBox.isSelected())
            final_score_socRating+=Dorm_captain;

        if(Team_leader_CheckBox.isSelected()){
            try {
                final_score_socRating += (Team_leader * Double.parseDouble(ColTimLider.getText()));
            }
        catch (Exception e) {
            ColTimLider.setPromptText("Неверно введены данные");
            ColTimLider.setText("");

            }
        }

        if(Volunteer_CheckBox.isSelected()) {
            try {
                final_score_socRating+=(Volunteer * Double.parseDouble(ColVolonterReu.getText()));
            }
            catch (Exception e) {
                ColVolonterReu.setPromptText("Неверно введены данные");
                ColVolonterReu.setText("");
            }
        }

        if (Main_organizer_universitywide_event_CheckBox.isSelected())
            final_score_socRating+=Main_organizer_universitywide_event;

        if (Deputy_main_organizer_universitywide_event_CheckBox.isSelected())
            final_score_socRating+=Deputy_main_organizer_universitywide_event;

        if (Team_main_universitywide_event_CheckBox.isSelected())
            final_score_socRating+=Team_main_universitywide_event;

        if (Main_organizer_committee_event_CheckBox.isSelected())
            final_score_socRating+=Main_organizer_committee_event;

        if (Deputy_main_organizer_committee_event_CheckBox.isSelected())
            final_score_socRating+=Deputy_main_organizer_committee_event;

        if (Team_main_committee_event_CheckBox.isSelected())
            final_score_socRating+=Team_main_committee_event;

        if (Commander_commissar_CheckBox.isSelected())
            final_score_socRating+=Commander_commissar;

        if(Fighter_CheckBox.isSelected()) {
            try {
                final_score_socRating+=(Fighter * Double.parseDouble(ColFighter.getText()));
            }
            catch (Exception e) {
                ColFighter.setPromptText("Неверно введены данные");
                ColFighter.setText("");
            }
        }

        if (Central_admissions_committee_CheckBox.isSelected()){
            try {
                final_score_socRating += (Central_admissions_committee * Integer.parseInt(ColCentralSectionCommittee.getText()));
            }
            catch (Exception e) {
                ColCentralSectionCommittee.setPromptText("Неверно введены данные");
                ColCentralSectionCommittee.setText("");
            }
        }

        if (Selection_committee_CheckBox.isSelected()) {
            try {
                final_score_socRating += (Selection_committee * Integer.parseInt(ColSelectionCommissions.getText()));

            }
            catch (Exception e){
                ColSelectionCommissions.setPromptText("Неверно введены данные");
                ColSelectionCommissions.setText("");
            }
        }

        if (final_score_socRating > 100) {
            final_score_socRating = 100;
        }
        ControllerEnd.scoreOfSocRating = final_score_socRating;
        ColBallsOfSocRating.setText("Количество баллов социального рейтинга: " + final_score_socRating);
    }
}