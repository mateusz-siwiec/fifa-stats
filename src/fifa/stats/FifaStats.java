package fifa.stats;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FifaStats extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override 
 
    public void start(Stage primaryStage) throws Exception {

        TextField gospodarzImie = new TextField("Podaj imię");
        TextField goscImie = new TextField("Podaj imie");

        gospodarzImie.setMinWidth(200);
        goscImie.setMinWidth(200);
        HBox hbox = new HBox(100);

        TextField gospodarzNazwisko = new TextField("Podaj nazwisko");
        TextField goscNazwisko = new TextField("Podaj nazwisko22222");

        VBox vbox = new VBox(10);
        hbox.getChildren().add(vbox);

        hbox.getChildren().add(gospodarzImie);
        hbox.getChildren().add(goscNazwisko);

       vbox.getChildren().add(goscImie);
       vbox.getChildren().add(gospodarzNazwisko);
        

        hbox.setAlignment(Pos.TOP_CENTER);
        vbox.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(hbox, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
      
        primaryStage.setTitle("FifaStats");
        primaryStage.show();
    }

}
