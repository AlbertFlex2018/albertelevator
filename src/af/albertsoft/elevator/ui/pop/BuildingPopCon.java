/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.ui.pop;

import af.albertsoft.elevator.system.Building;
import af.albertsoft.elevator.ui.UIFaceContain;
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
public class BuildingPopCon implements Initializable {
    public static final String CON_NAME="BuildingPop_";
    public static final String OPERATE_ADD="Add";
    public static final String OPERATE_REMOVE="Remove";
    public static final String OPERATE_MODIFY="Modify";
    
    @FXML
    private ChoiceBox<String> operateChoice;
    @FXML
    private ChoiceBox<Building> buildingChoice;
    @FXML
    private TextField NameField;
    @FXML
    private TextField StartField;
    @FXML
    private TextField EndField;

    /**
     * Initializes the controller class.
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       UIFaceContain contain = UIFaceContain.getInstance();
       contain.addUI(CON_NAME+"operateChoice", operateChoice);
       contain.addUI(CON_NAME+"buildingChoice", buildingChoice);
       contain.addUI(CON_NAME+"NameField", NameField);
       contain.addUI(CON_NAME+"StartField",StartField);
       contain.addUI(CON_NAME+"EndField",EndField);     
       
       operateChoice.getItems().add(OPERATE_ADD);
       operateChoice.getItems().add(OPERATE_REMOVE);
       operateChoice.getItems().add(OPERATE_MODIFY);
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
