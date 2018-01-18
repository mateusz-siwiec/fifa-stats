package fifa.stats;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class FXMLDocumentController implements Initializable {
    
     

    @FXML
    private TextField tfHostName;
    @FXML
    private TextField tfGuestName;
    @FXML
    private TextField tfHostSurname;
    @FXML
    private TextField tfGuestSurname;
    @FXML
    private TextField tfHostTeam;
    @FXML
    private TextField tfGuestTeam;
    @FXML
    private TextField tfHostGoals;
    @FXML
    private TextField tfGuestGoals;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfSurname;
    @FXML
    private TextField tfTeamName;
   
    

    @FXML
    private ImageView btn_home;
    @FXML
    private ImageView btn_user;
    @FXML
    private ImageView btn_results;
    @FXML
    private ImageView btn_team;
    @FXML
    private AnchorPane h_home;
    @FXML
    private AnchorPane h_user;
    @FXML
    private AnchorPane h_results;
    @FXML
    private AnchorPane h_team;

    @FXML
    private void addResult(ActionEvent event) {

        System.out.println("|" + tfHostName.getText() + " " + tfHostSurname.getText() + "|" + " " + tfHostTeam.getText() + " " + tfHostGoals.getText() + " " + "  :  " + " " + tfGuestGoals.getText()
                + " " + tfGuestTeam.getText() + " " + "|" + tfGuestName.getText() + " " + tfGuestSurname.getText() + "|");

        Team hostTeam = new Team(tfHostTeam.getText());
        Team guestTeam = new Team(tfGuestTeam.getText());
        Player host = new Player(tfHostName.getText(), tfHostSurname.getText());
        Player guest = new Player(tfGuestName.getText(), tfGuestSurname.getText());
        Date today = new Date();
        PlayerResult hostResult = new PlayerResult(host, hostTeam, Integer.parseInt(tfHostGoals.getText()));
        PlayerResult guestResult = new PlayerResult(guest, guestTeam, Integer.parseInt(tfGuestGoals.getText()));

        Match matchResult = new Match(hostResult, guestResult, today);
       
        
        

    }

    @FXML
    private void addPlayer(ActionEvent event) {

        Player player = new Player(tfName.getText(), tfSurname.getText());
        PlayerRepository playerRepo = new PlayerRepository();
        Player insertedPlayer = playerRepo.insert(player);
        System.out.println(insertedPlayer);
    }
    
    @FXML
    private void addTeam(ActionEvent event){
        Team team = new Team(tfTeamName.getText());
        TeamRepository teamRepo = new TeamRepository();
        Team insertedTeam = teamRepo.insert(team);
        System.out.println(insertedTeam);
    }
     
    
    

    @FXML
    private void handleButtonAction(MouseEvent event) {
        if (event.getTarget() == btn_home) {
            h_home.setVisible(true);
            h_user.setVisible(false);
            h_results.setVisible(false);
            h_team.setVisible(false);
        } else if (event.getTarget() == btn_user) {
            h_user.setVisible(true);
            h_home.setVisible(false);
            h_results.setVisible(false);
            h_team.setVisible(false);
        } else if (event.getTarget() == btn_results) {
            h_results.setVisible(true);
            h_user.setVisible(false);
            h_home.setVisible(false);
            h_team.setVisible(false);
        } else if (event.getTarget() == btn_team) {
            h_results.setVisible(false);
            h_user.setVisible(false);
            h_home.setVisible(false);
            h_team.setVisible(true);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    
}



