/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.ui.pop;

import af.albertsoft.elevator.ui.UIFaceContain;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AdminPopCon implements Initializable {
    
    public static final String CON_NAME="AdminPop_";
    public static final String OPERATE_ADD="Add";
    public static final String OPERATE_REMOVE="Remove";
    public static final String OPERATE_MODIFY="Modify";
    public static final String OPERATE_LOGIN="Login";
    public static final String OPERATE_LOGOUT="Logout";
    
    @FXML
    private ChoiceBox<String> operateChoice;
    @FXML
    private TextField IDField;
    @FXML
    private TextField NameField;
    @FXML
    private PasswordField passwordField;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UIFaceContain contain = UIFaceContain.getInstance();
        contain.addUI(CON_NAME+"operateChoice", operateChoice);
        contain.addUI(CON_NAME+"IDField",IDField);
        contain.addUI(CON_NAME+"NameField",NameField);
        contain.addUI(CON_NAME+"passwordField", passwordField);        
        
        operateChoice.getItems().add(OPERATE_ADD);
        operateChoice.getItems().add(OPERATE_MODIFY);
        operateChoice.getItems().add(OPERATE_REMOVE);
        operateChoice.getItems().add(OPERATE_LOGIN);
        operateChoice.getItems().add(OPERATE_LOGOUT);
        
    }        

    @FXML
    private void Submit(ActionEvent event) {
    }    
}
