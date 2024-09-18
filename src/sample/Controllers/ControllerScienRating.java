package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import sample.Methods;

import java.sql.ResultSet;
import java.sql.SQLException;

import static sample.Main.statement;

public class ControllerScienRating {

    @FXML
    private Button buttonNext, buttonBack;

    @FXML
    private CheckBox AvtorIzobret;

    @FXML
    private CheckBox AvtorOfProgramm;

    @FXML
    private Label ColBallsOfScienRating;

    @FXML
    private CheckBox CollectInformation;

    @FXML
    private CheckBox InsideVuzPartic;

    @FXML
    private CheckBox InsideVuzWin;

    @FXML
    private CheckBox InternationalPartic;

    @FXML
    private CheckBox InternationalWin;

    @FXML
    private CheckBox ParticKafedraPartic;

    @FXML
    private CheckBox ParticKafedraPrize;

    @FXML
    private CheckBox ParticRFPartic;

    @FXML
    private CheckBox ParticRFPrize;

    @FXML
    private CheckBox ParticRegionPartic;

    @FXML
    private CheckBox ParticRegionPrize;

    @FXML
    private CheckBox ParticRubezhPartic;

    @FXML
    private CheckBox ParticRubezhPrize;

    @FXML
    private CheckBox ParticWithStandInKonferenc;

    @FXML
    private CheckBox PublicationInBAK;

    @FXML
    private CheckBox PublicationInOther;

    @FXML
    private CheckBox PublicationInRinc;

    @FXML
    private CheckBox PublicationInScopusWos;

    @FXML
    private CheckBox RegionPartic;

    @FXML
    private CheckBox RegionWin;

    @FXML
    private CheckBox ResearchWork;

    @FXML
    private CheckBox WorkInScientificCircles;

    int final_ScienRating = 0,MVU_winner, MVU_participant, RMU_winner, RMU_participant, VU_winner, VU_participant, Foreign_report_participant,
            Foreign_report_prize_place, RF_report_participant, RF_report_prize_place, Region_report_participant,
            Region_report_prize_place, Department_report_participant, Department_report_prize_place, Conference_report_participant,
            NIR_research_work, NIR_collecting_information,NIR_science_club, Publication_Scopus_Wos, Publication_VAK, Publication_RINC,
            Publication_other, Author_invention, Author_program;

    @FXML
    void initialize() {

        try {
            ResultSet RS = statement.executeQuery("Select * from ScientificRating");
            if (RS.next()) {
                MVU_winner = RS.getInt("MVU_winner");
                MVU_participant = RS.getInt("MVU_participant");
                RMU_winner = RS.getInt("RMU_winner");
                RMU_participant = RS.getInt("RMU_participant");
                VU_winner = RS.getInt("VU_winner");
                VU_participant = RS.getInt("VU_participant");
                Foreign_report_participant = RS.getInt("Foreign_report_participant");
                Foreign_report_prize_place = RS.getInt("Foreign_report_prize_place");
                RF_report_participant = RS.getInt("RF_report_participant");
                RF_report_prize_place = RS.getInt("RF_report_prize_place");
                Region_report_participant = RS.getInt("Region_report_participant");
                Region_report_prize_place = RS.getInt("Region_report_prize_place");
                Department_report_participant = RS.getInt("Department_report_participant");
                Department_report_prize_place = RS.getInt("Department_report_prize_place");
                Conference_report_participant = RS.getInt("Conference_report_participant");
                NIR_research_work = RS.getInt("NIR_research_work");
                NIR_collecting_information = RS.getInt("NIR_collecting_information");
                NIR_science_club = RS.getInt("NIR_science_club");
                Publication_Scopus_Wos = RS.getInt("Publication_Scopus_Wos");
                Publication_VAK = RS.getInt("Publication_VAK");
                Publication_RINC = RS.getInt("Publication_RINC");
                Publication_other = RS.getInt("Publication_other");
                Author_invention = RS.getInt("Author_invention");
                Author_program = RS.getInt("Author_program");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void ClickOnButtonBack(ActionEvent event) {
        Methods.transition("/sample/FailFXML/Disciplines.fxml", buttonBack);
    }

    @FXML
    void ClickOnButtonNext(ActionEvent event) {
        Methods.transition("/sample/FailFXML/SocRating.fxml", buttonNext);
        ClickOnButtonCalculate(event);
    }

    @FXML
    void ClickOnButtonCalculate(ActionEvent event) {
        final_ScienRating = 0;
        if(InternationalWin.isSelected())
            final_ScienRating+=MVU_winner;
        if(InternationalPartic.isSelected())
            final_ScienRating+=MVU_participant;

        if(RegionWin.isSelected())
            final_ScienRating+=RMU_winner;
        if(RegionPartic.isSelected())
            final_ScienRating+=RMU_participant;

        if(InsideVuzWin.isSelected())
            final_ScienRating+=VU_winner;
        if(InsideVuzPartic.isSelected())
            final_ScienRating+=VU_participant;

        if(ParticRubezhPartic.isSelected())
            final_ScienRating+=Foreign_report_participant;
        if(ParticRubezhPrize.isSelected())
            final_ScienRating+=Foreign_report_prize_place;

        if(ParticRFPartic.isSelected())
            final_ScienRating+=RF_report_participant;
        if(ParticRFPrize.isSelected())
            final_ScienRating+=RF_report_prize_place;

        if(ParticRegionPartic.isSelected())
            final_ScienRating+=Region_report_participant;
        if(ParticRegionPrize.isSelected())
            final_ScienRating+=Region_report_prize_place;

        if(ParticKafedraPartic.isSelected())
            final_ScienRating+=Department_report_participant;
        if(ParticKafedraPrize.isSelected())
            final_ScienRating+=Department_report_prize_place;

        if(ParticWithStandInKonferenc.isSelected())
            final_ScienRating+=Conference_report_participant;

        if(ResearchWork.isSelected())
            final_ScienRating+=NIR_research_work;
        if(CollectInformation.isSelected())
            final_ScienRating+=NIR_collecting_information;
        if(WorkInScientificCircles.isSelected())
            final_ScienRating+=NIR_science_club;

        if(PublicationInScopusWos.isSelected())
            final_ScienRating+=Publication_Scopus_Wos;
        if(PublicationInBAK.isSelected())
            final_ScienRating+=Publication_VAK;
        if(PublicationInRinc.isSelected())
            final_ScienRating+=Publication_RINC;
        if(PublicationInOther.isSelected())
            final_ScienRating+=Publication_other;

        if(AvtorIzobret.isSelected())
            final_ScienRating+=Author_invention;
        if(AvtorOfProgramm.isSelected())
            final_ScienRating+=Author_program;

        if (final_ScienRating > 200) {
            final_ScienRating = 200;
        }
        ControllerEnd.scoreOfScienRating = final_ScienRating;
        ColBallsOfScienRating.setText("Количество баллов научного рейтинга: " + final_ScienRating);

    }

}