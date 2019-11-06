
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.pop;

import af.albertsoft.elevator.admin.Admin;
import af.albertsoft.elevator.system.AllAboveSystem;
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
public class AdminPop implements Initializable {

    public static final String CHOICE_ADD="Add";
    public static final String CHOICE_REMOVE="Remove";
    public static final String CHOICE_MODIFY="Modify";

    @FXML
    private ChoiceBox<String> operateChoice;
    @FXML
    public TextField IDField;
    @FXML
    private TextField NameField;
    @FXML
    private PasswordField passwordField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        IDField.setDisable(true);
        operateChoice.getItems().add(CHOICE_ADD);
        operateChoice.getItems().add(CHOICE_REMOVE);
        operateChoice.getItems().add(CHOICE_MODIFY);                
    }    
    @FXML
    private void Submit(ActionEvent event){
        switch(operateChoice.getValue()){
            case AdminPop.CHOICE_ADD:
                handleAdd();
                break;
            case AdminPop.CHOICE_REMOVE:
                handleRemove();
                break;
            case AdminPop.CHOICE_MODIFY:
                handleModify();
                break;
            default:break;
        }
    }   
    
    private void handleAdd(){
        String name = NameField.getText();
        String password=passwordField.getText();
        if(password.length()<6||name==null||name.equals("")){
            System.out.println("password or name error!");
            return;
        }
        
        int id = AllAboveSystem.createId()+100000;
        Admin admin = new Admin(id,name,password);
        AllAboveSystem.getInstance().getControl().
                getSystemData().getAdminsystem().getIdAdminMap().put(admin.id, admin);
    }
    private void handleRemove(){
        if(IDField.getText().length()<6){
            System.out.println("Length of ID can not be less than 6!");            
            return;
        }
        
        int id = Integer.parseInt(IDField.getText());
        AllAboveSystem.getInstance().getControl().
                getSystemData().getAdminsystem().getIdAdminMap().remove(id);
    }    
    
    private void handleModify(){
        if(IDField.getText().length()<6){
            System.out.println("Length of ID can not be less than 6!");            
            return;
        }
        
        String name = NameField.getText();
        String password = passwordField.getText();
        if(password.length()<6||name==null||name.equals("")){
            System.out.println("password or name error!");
            return;
        }
        
        int id = Integer.parseInt(IDField.getText());
        Admin admin = AllAboveSystem.getInstance().getControl().
                getSystemData().getAdminsystem().getIdAdminMap().get(id);
        if(admin==null){
            System.out.println("Can not find this admin:"+id);
            return;
        }
        
        admin.setPassword(password);
        admin.setUserName(name);
    }
    
}
