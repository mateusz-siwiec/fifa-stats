
package fifa.stats;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class FXMLDocumentController implements Initializable {
    
    
    @FXML 
    private TextField tfGospodarzImie;
    @FXML
    private TextField tfGoscImie;
    @FXML
    private TextField tfGospodarzNazwisko;
    @FXML
    private TextField tfGoscNazwisko;
    @FXML
    private TextField tfGospodarzDruzyna;
    @FXML
    private TextField tfGoscDruzyna;
    @FXML
    private TextField tfGospodarzBramki;
    @FXML
    private TextField tfGoscBramki;
    
    @FXML
    private ImageView btn_home;
     @FXML
    private ImageView btn_user;
     @FXML
    private ImageView btn_results;
     @FXML
     private AnchorPane h_home;
      @FXML
     private AnchorPane h_user;
       @FXML
     private AnchorPane h_results;
    
    
    @FXML
    private void dodajWynik(ActionEvent event) {
        
             
        System.out.println("|"+tfGospodarzImie.getText()+" "+tfGospodarzNazwisko.getText()+"|"+" "+tfGospodarzDruzyna.getText()+" "+tfGospodarzBramki.getText()+ " " + "  :  "+ " "+ tfGoscBramki.getText()
               + " " +tfGoscDruzyna.getText()+ " " + "|" +tfGoscImie.getText()+" "+ tfGoscNazwisko.getText()+"|");
        
       
   
    }
    @FXML
    private void handleButtonAction(MouseEvent event){
        if(event.getTarget()==btn_home){
            h_home.setVisible(true);
             h_user.setVisible(false);
             h_results.setVisible(false);
        }
        else if(event.getTarget()==btn_user){
            h_user.setVisible(true);
            h_home.setVisible(false);
            h_results.setVisible(false);
        }
        else if(event.getTarget()==btn_results){
            h_results.setVisible(true);
            h_user.setVisible(false);
            h_home.setVisible(false);
        }
             
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}