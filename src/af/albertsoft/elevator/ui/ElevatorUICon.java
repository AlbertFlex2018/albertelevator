/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.ui;

import af.albertsoft.elevator.system.AllAboveSystem;
import af.albertsoft.elevator.system.Building;
import af.albertsoft.elevator.system.Elevator;
import af.albertsoft.elevator.system.SystemData;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ElevatorUICon implements Initializable {
    
    public static final String CON_NAME="System_";
    @FXML
    private TreeView<String> buildingTree;
    @FXML
    private Label loginMessage;
    @FXML
    private Button loginButton;
    @FXML
    private Label runstateLabel;
    @FXML
    private Label elevatorIdLabel;
    @FXML
    private Label doorStateLabel;
    @FXML
    private Label buildingIdLabel;
    @FXML
    private Label num1;
    @FXML
    private Label num0;
    @FXML
    private AnchorPane buttonPanel;
    @FXML
    private TextField cmdLine;
    @FXML
    private TextArea consoleText;
    @FXML
    private TreeView<String> userTree;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    private UIFaceContain contain;
    private Elevator selectElevator;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        contain = UIFaceContain.getInstance();
        contain.addUI(CON_NAME+"num1", num1);
        contain.addUI(CON_NAME+"num0", num0);
        contain.addUI(CON_NAME+"buildingTree", buildingTree);
        contain.addUI(CON_NAME+"userTree",userTree);    
        contain.addUI(CON_NAME+"cmdLine", cmdLine);
        contain.addUI(CON_NAME+"consoleText",consoleText);
        contain.addUI(CON_NAME+"buttonPanel",buttonPanel);
        contain.addUI(CON_NAME+"loginButton",loginButton);
        contain.addUI(CON_NAME+"loginMessage",loginMessage);
        contain.addUI(CON_NAME+"runstateLabel",runstateLabel);
        contain.addUI(CON_NAME+"elevatorIdLabel",elevatorIdLabel);
        contain.addUI(CON_NAME+"doorStateLabel",doorStateLabel);
        contain.addUI(CON_NAME+"buildingIdLabel",buildingIdLabel);
        
        initBuilding();
        
        initUser();        
        
//        initPops();should be called on the main methods.
    }        

    private void initBuilding()
    {
        Map<Integer,Building> buildMap=AllAboveSystem.getInstance().getControl().getSystemData().getBuildingMap();
        Iterator<Building> builditer=buildMap.values().iterator();
        TreeItem<String> root=new TreeItem<>((AllAboveSystem.getInstance().getControl().getSystemData().getSystemName()));
        buildingTree.setRoot(root);
        while(builditer.hasNext()){
            Building building=builditer.next();
            TreeItem<String> item = new TreeItem<>(building.getBuildingName());
            addBuilding(building,item);
            root.getChildren().add(item);            
        }
    }
    private void addBuilding(Building building,TreeItem<String> item)
    {
        Iterator<Integer> eleiter=building.getElevatorIdMap().values().iterator();
        while(eleiter.hasNext())
        {
            Integer inte = eleiter.next();            
            Elevator ele = AllAboveSystem.getInstance().getControl().
                    getSystemData().getElevatorMap().get(inte);
            TreeItem<String> eleitem = new TreeItem<>(ele.getElevatorName());                        
            item.getChildren().add(eleitem);
        }
    }
    
        
    private void initUser(){
        Map<Integer,Building> buildMap=AllAboveSystem.getInstance().getControl().getSystemData().getBuildingMap();
        Iterator<Building> builditer=buildMap.values().iterator();
        TreeItem<String> root=new TreeItem<>("User Interface");
        userTree.setRoot(root);
        while(builditer.hasNext()){
            Building building=builditer.next();
            TreeItem<String> item = new TreeItem<>(building.getBuildingName());            
            addLevel(building,item);
            root.getChildren().add(item);            
        }        
    }
    private void addLevel(Building building,TreeItem<String> buildroot)
    {        
        int start=building.getStartFloor();
        int end=building.getEndFloor();
        int count=start;        
        Map<String,TreeItem<String>> treeMap=new HashMap<>();
        for(int i=start;i!=end+1;++i){
            TreeItem<String> item = new TreeItem<>("F"+i);
            treeMap.put(""+i,item);            
            buildroot.getChildren().add(item);
        }
        Map<Integer,Elevator> elemap=AllAboveSystem.getInstance().getControl().getSystemData().getElevatorMap();
        System.out.println("size of eles:"+elemap.size());
        Iterator<Integer> eleiter=building.getElevatorIdMap().values().iterator();
        while(eleiter.hasNext()){            
                Integer id = eleiter.next();
                System.out.println("id:"+id);
                Elevator ele = AllAboveSystem.getInstance().getControl().getSystemData().getElevatorMap().get(id);
                if(ele==null)continue;
                if(ele.getCurrentFloor()==count)
                {
                    treeMap.get(""+ele.getStartFloor()).getChildren().add(new TreeItem<>(ele.getElevatorName()));
                }
        }            
    }
    
    @FXML
    private void newSystem(ActionEvent event){
        
    }

    @FXML
    private void openSystem(ActionEvent event){
        
    }

    @FXML
    private void saveSystem(ActionEvent event){
        
    }

    @FXML
    private void addElevator(ActionEvent event){
        
    }

    @FXML
    private void addBuilding(ActionEvent event){
    }

    @FXML
    private void addAdmin(ActionEvent event) {
    }

    @FXML
    private void modifyElevator(ActionEvent event) {
    }

    @FXML
    private void modifyBuilding(ActionEvent event) {
    }

    @FXML
    private void removeElevator(ActionEvent event) {
    }

    @FXML
    private void removeBuilding(ActionEvent event) {
    }

    @FXML
    private void removeAdmin(ActionEvent event) {
    }

    @FXML
    private void help(ActionEvent event) {
        
    }

    @FXML
    private void aboutAuthor(ActionEvent event) {
    }

    @FXML
    private void aboutOther(ActionEvent event) {
    }

    @FXML
    private void aboutSite(ActionEvent event) {
    }

    @FXML
    private void buildingTreeEditStart(TreeView.EditEvent<String> event) {
    }

    @FXML
    private void buildingTreeEditCommit(TreeView.EditEvent<String> event) {
    }

    @FXML
    private void loginAction(ActionEvent event) {
    }

    @FXML
    private void openDoor(ActionEvent event) {
    }

    @FXML
    private void closeDoor(ActionEvent event) {
        
    }

    @FXML
    private void runComand(ActionEvent event) {
        
    }

    private void initPops() {
        String[] names=new String[]{
          "admop","buildop","eleop","sysop",
        };
        UIFaceContain contain = UIFaceContain.getInstance();
        try {
            for(String s: names){
                Parent root = FXMLLoader.load(getClass().getResource("pop/"+s+".fxml"));
                contain.addUI("Pop_"+s, root);
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
