/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.ui.pop;

import af.albertsoft.elevator.admin.Admin;
import af.albertsoft.elevator.admin.LoginAndOut;
import af.albertsoft.elevator.system.AllAboveSystem;
import af.albertsoft.elevator.ui.UIFaceContain;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Popup;

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
    private void Submit(ActionEvent event){
        boolean result;
        switch(operateChoice.getValue()){
            case OPERATE_ADD:
                result=handleAdd();break;
            case OPERATE_MODIFY:
                result=handleModify();break;
            case OPERATE_REMOVE:
                result=handleRemove();break;
            case OPERATE_LOGIN:
                result=handleLogin();break;
            case OPERATE_LOGOUT:
                result=handleLogout();break;
            default:
                result=false;break;
        }
        UIFaceContain.getInstance().popup.hide();
    }    
    
    private boolean handleAdd(){
        String name = NameField.getText();
        String password=passwordField.getText();
        if(password.length()<6||name==null||name.equals("")){
            System.out.println("password or name error!");
            return false;
        }
        
        int id = AllAboveSystem.createId()+100000;
        Admin admin = new Admin(id,name,password);
        AllAboveSystem.getInstance().getControl().
                getSystemData().getAdminSystem().getIdAdminMap().put(admin.id, admin);
        return true;
    }
    private boolean handleRemove(){
        if(IDField.getText().length()<6){
            System.out.println("Length of ID can not be less than 6!");            
            return false;
        }
        
        int id = Integer.parseInt(IDField.getText());
        AllAboveSystem.getInstance().getControl().
                getSystemData().getAdminSystem().getIdAdminMap().remove(id);
        return true;
    }    
    
    private boolean handleModify(){
        if(IDField.getText().length()<6){
            System.out.println("Length of ID can not be less than 6!");            
            return false;
        }
        
        String name = NameField.getText();
        String password = passwordField.getText();
        if(password.length()<6||name==null||name.equals("")){
            System.out.println("password or name error!");
            return false;
        }
        
        int id = Integer.parseInt(IDField.getText());
        Admin admin = AllAboveSystem.getInstance().getControl().
                getSystemData().getAdminSystem().getIdAdminMap().get(id);
        if(admin==null){
            System.out.println("Can not find this admin:"+id);
            return false;
        }
        
        admin.setPassword(password);
        admin.setUserName(name);
        return true;
    }        
    private boolean handleLogin(){
        String  id = IDField.getText();
        if(id==null||id.length()<6){
            System.out.println("handleLogin Error:id error!");
            return false;
        }
        String password = passwordField.getText();
        if(password==null||password.length()<6){
            System.out.println("handleLogin Error:password error!");
            return false;            
        }
        
        LoginAndOut log =AllAboveSystem.getInstance().getControl().getSystemData().getAdminSystem();
        return log.login(Integer.parseInt(id), password);
    }
    private boolean handleLogout(){
        LoginAndOut log =AllAboveSystem.getInstance().getControl().getSystemData().getAdminSystem();
        log.logout();
        return true;
    }
}
