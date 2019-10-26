/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import af.albertsoft.elevator.system.AllAboveSystem;
import af.albertsoft.elevator.system.Building;
import af.albertsoft.elevator.system.Elevator;
import af.albertsoft.elevator.system.SystemData;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class ElevatoruiController implements Initializable {

    public static ElevatoruiController controller;
    
    @FXML
    public TreeView<String> buildingTree;
    @FXML
    public Label loginMessage;
    @FXML
    public Button loginButton;
    @FXML
    public Label runstateLabel;
    @FXML
    public Label elevatorIdLabel;
    @FXML
    public Label doorStateLabel;
    @FXML
    public Label buildingIdLabel;
    @FXML
    public AnchorPane buttonPanel;
    @FXML
    public TextField cmdLine;
    @FXML
    public TextArea consoleText;
    @FXML
    public TreeView<String> userTree;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ElevatoruiController.controller=this;     
        
        //init buildingTree
        initBuilding();
        
        
        //init userTree        
        initUser();
        //init 
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
            Elevator ele = AllAboveSystem.getInstance().getElevator(inte);
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
            TreeItem<String> item = new TreeItem<String>("F"+i);
            treeMap.put(""+i,item);            
            buildroot.getChildren().add(item);
        }
        Map<Integer,Elevator> elemap=AllAboveSystem.getInstance().getControl().getSystemData().getElevatorMap();
        SystemData data=AllAboveSystem.getInstance().getControl().getSystemData();
        System.out.println("size of eles:"+elemap.size());
//        while(eleeiter.hasNext()){
//            Elevator el2 = eleeiter.next();
//            System.out.println("elevator:"+el2.getElevatorName()+" id:"+el2.globalId);
//        }
        Iterator<Integer> eleiter=building.getElevatorIdMap().values().iterator();
        while(eleiter.hasNext()){            
                Integer id = eleiter.next();
                System.out.println("id:"+id);
                Elevator ele = AllAboveSystem.getInstance().getControl().getSystemData().getElevatorMap().get(id);
                if(ele==null)continue;
                if(ele.getCurrentFloor()==count)
                {
                    treeMap.get(""+ele.getStartFloor()).getChildren().add(new TreeItem<String>(ele.getElevatorName()));
                }
        }            
    }

    @FXML
    private void newSystem(ActionEvent event){
    }

    @FXML
    private void openSystem(ActionEvent event) {
    }

    @FXML
    private void saveSystem(ActionEvent event) {
    }

    @FXML
    private void addElevator(ActionEvent event) {
    }

    @FXML
    private void addBuilding(ActionEvent event) {
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
    private void loginAction(ActionEvent event) {
    }

    @FXML
    private void runComand(ActionEvent event) {
    }

    @FXML
    private void buildingTreeEditStart(TreeView.EditEvent<String> event) {
    }

    @FXML
    private void buildingTreeEditCommit(TreeView.EditEvent<String> event) {
    }    
}
