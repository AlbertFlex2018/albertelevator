/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.pop;

import static af.albertsoft.elevator.pop.AdminPop.CHOICE_ADD;
import static af.albertsoft.elevator.pop.AdminPop.CHOICE_MODIFY;
import static af.albertsoft.elevator.pop.AdminPop.CHOICE_REMOVE;
import af.albertsoft.elevator.system.Building;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class BuildingPop implements Initializable {

    public static final String CHOICE_ADD="Add";
    public static final String CHOICE_REMOVE="Remove";
    public static final String CHOICE_MODIFY="Modify";

    
    @FXML
    private ChoiceBox<String> operateChoice;
    @FXML
    private ChoiceBox<Building> buildingChoice;
    @FXML
    private Label nameLabel;
    @FXML
    private TextField NameField;
    @FXML
    private Label startLabel;
    @FXML
    private TextField StartField;
    @FXML
    private TextField EndField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        operateChoice.getItems().add(CHOICE_ADD);
        operateChoice.getItems().add(CHOICE_REMOVE);
        operateChoice.getItems().add(CHOICE_MODIFY);                        
        
    }    

    @FXML
    private void Submit(ActionEvent event){
        switch(operateChoice.getValue()){
            case CHOICE_ADD:
                handleAdd();
                break;
            case CHOICE_REMOVE:
                handleRemove();
                break;
            case CHOICE_MODIFY:
                handleModify();
                break;
            default:break;
        }
        
    }    

    private void handleAdd() {
    }

    private void handleRemove() {
    }

    private void handleModify() {
    }
}
