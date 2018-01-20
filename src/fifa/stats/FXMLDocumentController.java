package fifa.stats;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class FXMLDocumentController implements Initializable {

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
    private TableView<Team> teamTable;
    @FXML
    private TableView<Player> userTable;
    @FXML
    private TableView<Match> resultTable; 
    
    @FXML
    private TableColumn<Team, String> teamNameColumn;
    @FXML
    private TableColumn<Player, String> userNameColumn;
    @FXML
    private TableColumn<Player, String> userSurnameColumn;

    
    @FXML
    private TableColumn<Match, String> hostTeamResultTable; 
 
     @FXML
    private TableColumn<Match, String> hostNameResultTable;
    
     @FXML
    private TableColumn<Match, String> hostScoreResultTable;
     @FXML
    private TableColumn<Match, String> guestScoreResultTable;
     @FXML
    private TableColumn<Match, String> guestTeamResultTable;
     @FXML
    private TableColumn<Match, String> guestNameResultTable;
     @FXML
    private TableColumn<Match, String> dateResultTable;
    
    
    
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
    private ComboBox<Player> hostPlayerBox;
    @FXML
    private ComboBox<Player> guestPlayerBox;
    @FXML
    private ComboBox<Team> guestTeamBox;
    @FXML
    private ComboBox<Team> hostTeamBox;

    @FXML
    private void addResult(ActionEvent event) {

        Team hostTeam = hostTeamBox.getSelectionModel().getSelectedItem();
        Team guestTeam = guestTeamBox.getSelectionModel().getSelectedItem();
        Player host = hostPlayerBox.getSelectionModel().getSelectedItem();
        Player guest = guestPlayerBox.getSelectionModel().getSelectedItem();
       
        PlayerResult hostResult = new PlayerResult(host, hostTeam, Integer.parseInt(tfHostGoals.getText()));
        PlayerResult guestResult = new PlayerResult(guest, guestTeam, Integer.parseInt(tfGuestGoals.getText()));

        Match matchResult = new Match(hostResult, guestResult, LocalDate.now());
        GamesRepository gamesRepo = new GamesRepository();
        gamesRepo.insert(matchResult);
        refreshResultTable();
       
      
        
    }

    @FXML
    private void addPlayer(ActionEvent event) {

        Player player = new Player(tfName.getText(), tfSurname.getText());
        PlayerRepository playerRepo = new PlayerRepository();
        Player insertedPlayer = playerRepo.insert(player);
        System.out.println(insertedPlayer);
        refreshUserTable();
        refreshHostPlayersComboBox();
        refreshGuestPlayersComboBox();
    }
    
    @FXML
    private void deletePlayer(ActionEvent event) {
        Player selectedPlayer = userTable.getSelectionModel().getSelectedItem();
        PlayerRepository deleteTeam = new PlayerRepository();
        deleteTeam.removeById(selectedPlayer.getId());
        refreshUserTable();
        refreshHostPlayersComboBox();
        refreshGuestPlayersComboBox();

    }

    @FXML
    private void addTeam(ActionEvent event) {
        Team team = new Team(tfTeamName.getText());
        TeamRepository teamRepo = new TeamRepository();
        Team insertedTeam = teamRepo.insert(team);
        System.out.println(insertedTeam);
        refreshTeamTable();
        refreshHostTeamsComboBox();
        refreshGuestTeamsComboBox();
    }

    @FXML
    private void deleteTeam(ActionEvent event) {
        Team selectedTeam = teamTable.getSelectionModel().getSelectedItem();
        TeamRepository deleteTeam = new TeamRepository();
        deleteTeam.removeById(selectedTeam.getId());
        refreshTeamTable();
        refreshHostTeamsComboBox();
        refreshGuestTeamsComboBox();
        

    }
    @FXML
    private void deleteResult(ActionEvent event) {
        Match selectedGame = resultTable.getSelectionModel().getSelectedItem();
        GamesRepository deleteResult = new GamesRepository();
        deleteResult.removeById(selectedGame.getId());
        refreshResultTable();
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
        teamNameColumn.setCellValueFactory(new Callback<CellDataFeatures<Team, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Team, String> param) {
                return new SimpleStringProperty(param.getValue().getTeamName());
            }
        });
        refreshTeamTable();

        userNameColumn.setCellValueFactory(new Callback<CellDataFeatures<Player, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Player, String> param) {
                return new SimpleStringProperty(param.getValue().getName());
            }
        });
        refreshUserTable();

       userSurnameColumn.setCellValueFactory(new Callback<CellDataFeatures<Player, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Player, String> param) {
                return new SimpleStringProperty(param.getValue().getSurname());
            }
        });
        refreshUserTable();
       
        hostTeamResultTable.setCellValueFactory(new Callback<CellDataFeatures<Match, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Match, String> param) {
                return new SimpleStringProperty(param.getValue().getHostResult().getTeam().getTeamName());
            }
        });
        
        refreshResultTable();
        hostNameResultTable.setCellValueFactory(new Callback<CellDataFeatures<Match, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Match, String> param) {
                return new SimpleStringProperty(param.getValue().getHostResult().getPlayer().getName());
            }
        });
        
        refreshResultTable();
        
       hostScoreResultTable.setCellValueFactory(new Callback<CellDataFeatures<Match, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Match, String> param) {
                return new SimpleStringProperty (String.valueOf(param.getValue().getHostResult().getNumberOfGoals()) );
            }
        });
        
        refreshResultTable();
        
        
       guestScoreResultTable.setCellValueFactory(new Callback<CellDataFeatures<Match, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Match, String> param) {
                return new SimpleStringProperty(String.valueOf(param.getValue().getGuestResult().getNumberOfGoals()));
            }
        });
        
        refreshResultTable();
        
        
       guestTeamResultTable.setCellValueFactory(new Callback<CellDataFeatures<Match, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Match, String> param) {
                return new SimpleStringProperty(param.getValue().getGuestResult().getTeam().getTeamName());
            }
        });
        
        refreshResultTable();
        
        
       guestNameResultTable.setCellValueFactory(new Callback<CellDataFeatures<Match, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Match, String> param) {
                return new SimpleStringProperty(param.getValue().getGuestResult().getPlayer().getName());
            }
        });
        
        refreshResultTable();
        
       dateResultTable.setCellValueFactory(new Callback<CellDataFeatures<Match, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Match, String> param) {
                return new SimpleStringProperty(param.getValue().getDateOfTheMatch().toString());
            }
        });
        
        refreshResultTable(); 
        
      

        

        hostPlayerBox.setConverter(new StringConverter<Player>() {
            @Override
            public String toString(Player player) {
                return player.toString();
            }

            @Override
            public Player fromString(String comboBoxEntry) {
                List<Player> players = new PlayerRepository().findAll();
                for (Player player : players) {
                    if (player.toString().equals(comboBoxEntry)) {
                        return player;
                    }
                }
                return null;
            }
        });
        refreshHostPlayersComboBox();
        
        guestPlayerBox.setConverter(new StringConverter<Player>() {
            @Override
            public String toString(Player player) {
                return player.toString();
            }

            @Override
            public Player fromString(String comboBoxEntry) {
                List<Player> players = new PlayerRepository().findAll();
                for (Player player : players) {
                    if (player.toString().equals(comboBoxEntry)) {
                        return player;
                    }
                }
                return null;
            }
        });
        refreshGuestPlayersComboBox();

        
        hostTeamBox.setConverter(new StringConverter<Team>() {
            @Override
            public String toString(Team team) {
                return team.toString();
            }

            @Override
            public Team fromString(String comboBoxEntry) {
                List<Team>teams = new TeamRepository().findAll();
                for (Team team : teams) {
                    if (team.toString().equals(comboBoxEntry)) {
                        return team;
                    }
                }
                return null;
            }
        });
        refreshHostTeamsComboBox();
        
         guestTeamBox.setConverter(new StringConverter<Team>() {
            @Override
            public String toString(Team team) {
                return team.toString();
            }

            @Override
            public Team fromString(String comboBoxEntry) {
                List<Team>teams = new TeamRepository().findAll();
                for (Team team : teams) {
                    if (team.toString().equals(comboBoxEntry)) {
                        return team;
                    }
                }
                return null;
            }
        });
        refreshGuestTeamsComboBox();
    }

    private void refreshTeamTable() {
        List<Team> teamsFromDb = new TeamRepository().findAll();
        ObservableList<Team> teams = FXCollections.observableArrayList(teamsFromDb);
        teamTable.setItems(teams);
    }

    private void refreshUserTable() {
        List<Player> playersFromDb = new PlayerRepository().findAll();
        ObservableList<Player> players = FXCollections.observableArrayList(playersFromDb);
        userTable.setItems(players);
    }

    private void refreshHostPlayersComboBox() {
        List<Player> allPlayers = new PlayerRepository().findAll();
        ObservableList<Player> players = FXCollections.observableArrayList(allPlayers);
        hostPlayerBox.setItems(players);
    }
    private void refreshGuestPlayersComboBox() {
        List<Player> allPlayers = new PlayerRepository().findAll();
        ObservableList<Player> players = FXCollections.observableArrayList(allPlayers);
        guestPlayerBox.setItems(players);
    }
    private void refreshHostTeamsComboBox() {
        List<Team> allTeams = new TeamRepository().findAll();
        ObservableList<Team> teams = FXCollections.observableArrayList(allTeams);
        hostTeamBox.setItems(teams);
    }
    private void refreshGuestTeamsComboBox() {
        List<Team> allTeams = new TeamRepository().findAll();
        ObservableList<Team> teams = FXCollections.observableArrayList(allTeams);
        guestTeamBox.setItems(teams);
    }
   private void refreshResultTable() {
        List<Match> allGames = new GamesRepository().findAll();
        ObservableList<Match> games = FXCollections.observableArrayList(allGames);
        resultTable.setItems(games);
    }
}
