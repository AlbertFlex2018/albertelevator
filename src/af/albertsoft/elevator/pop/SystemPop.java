/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.pop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class SystemPop implements Initializable {

    public static final String CHOICE_NEW="New";
    public static final String CHOICE_SAVE="Save";
    public static final String CHOICE_OPEN="Open";
    
    @FXML
    private ChoiceBox<String> operateChoice;
    @FXML
    private TextField NameField;
    @FXML
    private TextField pathField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void Find(ActionEvent event) {        
    }

    @FXML
    private void Submit(ActionEvent event) {
    }
    
}
