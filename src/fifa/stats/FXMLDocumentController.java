package fifa.stats;

import fifa.stats.model.Team;
import fifa.stats.model.Match;
import fifa.stats.model.Player;
import fifa.stats.repositories.MatchRepositoryImpl;
import fifa.stats.repositories.PlayerRepositoryImpl;
import fifa.stats.repositories.TeamRepositoryImpl;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private Button editPlayerButton;
    @FXML
    private Button editTeamButton;

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
    private void closeApp(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    private void addResult(ActionEvent event) {
        Team hostTeam = hostTeamBox.getSelectionModel().getSelectedItem();
        Team guestTeam = guestTeamBox.getSelectionModel().getSelectedItem();
        Player host = hostPlayerBox.getSelectionModel().getSelectedItem();
        Player guest = guestPlayerBox.getSelectionModel().getSelectedItem();

        PlayerResult hostResult = new PlayerResult(host, hostTeam, Integer.parseInt(tfHostGoals.getText()));
        PlayerResult guestResult = new PlayerResult(guest, guestTeam, Integer.parseInt(tfGuestGoals.getText()));

        Match matchResult = new Match(hostResult, guestResult, LocalDate.now());
        MatchRepositoryImpl gamesRepo = new MatchRepositoryImpl();
        gamesRepo.insert(matchResult);
        refreshResultTable();

        tfHostGoals.clear();
        tfGuestGoals.clear();
        hostTeamBox.getSelectionModel().clearSelection();
        hostPlayerBox.getSelectionModel().clearSelection();
        guestTeamBox.getSelectionModel().clearSelection();
        guestPlayerBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void addPlayer(ActionEvent event) {
        Player player = new Player(tfName.getText(), tfSurname.getText());
        PlayerRepositoryImpl playerRepo = new PlayerRepositoryImpl();
        Player insertedPlayer = playerRepo.insert(player);

        refreshUserTable();
        refreshHostPlayersComboBox();
        refreshGuestPlayersComboBox();
        tfName.clear();
        tfSurname.clear();
    }

    @FXML
    private void deletePlayer(ActionEvent event) {
        Player selectedPlayer = userTable.getSelectionModel().getSelectedItem();
        if (selectedPlayer != null) {
            try {
                PlayerRepositoryImpl deleteTeam = new PlayerRepositoryImpl();
                deleteTeam.removeById(selectedPlayer.getId());
            } catch (RuntimeException exception) {
                System.err.println("Can't delete a player is referenced in any match.");
            }
            refreshUserTable();
            refreshHostPlayersComboBox();
            refreshGuestPlayersComboBox();
        }
    }

    @FXML
    private void addTeam(ActionEvent event) {
        Team team = new Team(tfTeamName.getText());
        TeamRepositoryImpl teamRepo = new TeamRepositoryImpl();
        Team insertedTeam = teamRepo.insert(team);

        refreshTeamTable();
        refreshHostTeamsComboBox();
        refreshGuestTeamsComboBox();
        tfTeamName.clear();
    }

    @FXML
    private void deleteTeam(ActionEvent event) {
        Team selectedTeam = teamTable.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            try {
                TeamRepositoryImpl deleteTeam = new TeamRepositoryImpl();
                deleteTeam.removeById(selectedTeam.getId());
            } catch (RuntimeException exception) {
                System.err.println("Can't delete a team is referenced in any match.");
            }
            refreshTeamTable();
            refreshHostTeamsComboBox();
            refreshGuestTeamsComboBox();
        }
    }

    @FXML
    private void deleteResult(ActionEvent event) {
        Match selectedGame = resultTable.getSelectionModel().getSelectedItem();
        MatchRepositoryImpl deleteResult = new MatchRepositoryImpl();
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

    @FXML
    private void editPlayer(ActionEvent event) {
        Player selectedPlayer = userTable.getSelectionModel().getSelectedItem();
        if (selectedPlayer != null) {
            Player updatedPlayer = new Player(selectedPlayer.getId(), tfName.getText(), tfSurname.getText());
            new PlayerRepositoryImpl().update(updatedPlayer);
            refreshUserTable();
            refreshGuestPlayersComboBox();
            refreshHostPlayersComboBox();
            refreshResultTable();
            tfName.clear();
            tfSurname.clear();
        }
    }

    @FXML
    private void editTeam(ActionEvent event) {
        Team selectedTeam = teamTable.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            Team updatedTeam = new Team(selectedTeam.getId(), tfTeamName.getText());
            new TeamRepositoryImpl().update(updatedTeam);
            refreshTeamTable();
            refreshGuestTeamsComboBox();
            refreshHostTeamsComboBox();
            refreshResultTable();
            tfTeamName.clear();
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
                Player player = param.getValue().getHostResult().getPlayer();
                return new SimpleStringProperty(player.getName() + " " + player.getSurname());
            }
        });
        refreshResultTable();
        hostScoreResultTable.setCellValueFactory(new Callback<CellDataFeatures<Match, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Match, String> param) {
                return new SimpleStringProperty(String.valueOf(param.getValue().getHostResult().getNumberOfGoals()));
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
                Player player = param.getValue().getGuestResult().getPlayer();
                return new SimpleStringProperty(player.getName() + " " + player.getSurname());
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
                List<Player> players = new PlayerRepositoryImpl().findAll();
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
                List<Player> players = new PlayerRepositoryImpl().findAll();
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
                List<Team> teams = new TeamRepositoryImpl().findAll();
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
                List<Team> teams = new TeamRepositoryImpl().findAll();
                for (Team team : teams) {
                    if (team.toString().equals(comboBoxEntry)) {
                        return team;
                    }
                }
                return null;
            }
        });
        refreshGuestTeamsComboBox();
        editPlayerButton.setDisable(true);
        userTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Player selectedPlayer = userTable.getSelectionModel().getSelectedItem();
                if (selectedPlayer != null) {
                    editPlayerButton.setDisable(false);
                    tfName.setText(selectedPlayer.getName());
                    tfSurname.setText(selectedPlayer.getSurname());
                }
            }
        });
        editTeamButton.setDisable(true);
        teamTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Team selectedTeam = teamTable.getSelectionModel().getSelectedItem();
                if (selectedTeam != null) {
                    editTeamButton.setDisable(false);
                    tfTeamName.setText(selectedTeam.getTeamName());
                }
            }
        });
    }

    private void refreshTeamTable() {
        List<Team> teamsFromDb = new TeamRepositoryImpl().findAll();
        ObservableList<Team> teams = FXCollections.observableArrayList(teamsFromDb);
        teamTable.setItems(teams);
    }

    private void refreshUserTable() {
        List<Player> playersFromDb = new PlayerRepositoryImpl().findAll();
        ObservableList<Player> players = FXCollections.observableArrayList(playersFromDb);
        userTable.setItems(players);
    }

    private void refreshHostPlayersComboBox() {
        List<Player> allPlayers = new PlayerRepositoryImpl().findAll();
        ObservableList<Player> players = FXCollections.observableArrayList(allPlayers);
        hostPlayerBox.setItems(players);
    }

    private void refreshGuestPlayersComboBox() {
        List<Player> allPlayers = new PlayerRepositoryImpl().findAll();
        ObservableList<Player> players = FXCollections.observableArrayList(allPlayers);
        guestPlayerBox.setItems(players);
    }

    private void refreshHostTeamsComboBox() {
        List<Team> allTeams = new TeamRepositoryImpl().findAll();
        ObservableList<Team> teams = FXCollections.observableArrayList(allTeams);
        hostTeamBox.setItems(teams);
    }

    private void refreshGuestTeamsComboBox() {
        List<Team> allTeams = new TeamRepositoryImpl().findAll();
        ObservableList<Team> teams = FXCollections.observableArrayList(allTeams);
        guestTeamBox.setItems(teams);
    }

    private void refreshResultTable() {
        List<Match> allGames = new MatchRepositoryImpl().findAll();
        ObservableList<Match> games = FXCollections.observableArrayList(allGames);
        resultTable.setItems(games);
    }
}
