
package fifa.stats;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML 
    private TextField tfGospodarzImie;
    private TextField tfGoscImie;
    private TextField tfGospodarzNazwisko;
    private TextField tfGoscNazwisko;
    private TextField tfGospodarzDruzyna;
    private TextField tfGoscDruzyna;
    private TextField tfGospodarzBramki;
    private TextField tfGoscBramki;
    
    
    @FXML
    private void dodajWynik(ActionEvent event) {
        
               
        System.out.println(tfGospodarzImie.getText()+tfGospodarzNazwisko.getText()+tfGospodarzDruzyna.getText()+tfGospodarzBramki.getText()+ " " + "  :  "+ tfGoscBramki.getText()
        + tfGoscDruzyna.getText() + tfGoscImie.getText()+ tfGoscNazwisko.getText());
        
       
   
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}