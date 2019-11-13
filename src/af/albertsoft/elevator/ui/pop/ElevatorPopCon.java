/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.ui.pop;

import af.albertsoft.elevator.system.Building;
import af.albertsoft.elevator.system.Elevator;
import af.albertsoft.elevator.ui.UIFaceContain;
import static af.albertsoft.elevator.ui.pop.BuildingPopCon.CON_NAME;
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
public class ElevatorPopCon implements Initializable {
    public static final String CON_NAME="ElevatorPop_";
    public static final String OPERATE_ADD="Add";
    public static final String OPERATE_MODIFY="Modify";
    public static final String OPERATE_REMOVE="Remove";
    @FXML
    private ChoiceBox<String> operateChoice;
    @FXML
    private ChoiceBox<Building> buildChoice;
    @FXML
    private ChoiceBox<Integer> StartChoice;
    @FXML
    private ChoiceBox<Integer> EndChoice;
    @FXML
    private ChoiceBox<Elevator> ElevatorChoice;
    @FXML
    private TextField ElevatorNameField;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UIFaceContain contain = UIFaceContain.getInstance();
        contain.addUI(CON_NAME+"EndChoice", EndChoice);
        contain.addUI(CON_NAME+"StartChoice", StartChoice);                
        contain.addUI(CON_NAME+"buildChoice", buildChoice);                
        contain.addUI(CON_NAME+"operateChoice", operateChoice);                
        contain.addUI(CON_NAME+"ElevatorNameField", ElevatorNameField);                
        contain.addUI(CON_NAME+"ElevatorChoice", ElevatorChoice);                        

        operateChoice.getItems().add(OPERATE_ADD);
        operateChoice.getItems().add(OPERATE_MODIFY);
        operateChoice.getItems().add(OPERATE_REMOVE);
    }    
    @FXML
    private void Submit(ActionEvent event) {
        boolean result;
        switch(operateChoice.getValue()){
            case OPERATE_ADD:
                result=handleAdd();break;
            case OPERATE_MODIFY:
                result=handleModify();break;
            case OPERATE_REMOVE:
                result=handleRemove();break;
            default:
                result=false;break;
        }
        UIFaceContain.getInstance().popupMap.get(CON_NAME).hide();
    }    

    private boolean handleAdd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean handleModify() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean handleRemove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
