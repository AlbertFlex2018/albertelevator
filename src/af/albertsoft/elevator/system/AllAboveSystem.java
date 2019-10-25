/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.system;

import af.albertsoft.elevator.system.ControlOperate;
import af.albertsoft.elevator.admin.Admin;
import af.albertsoft.elevator.admin.AdminOperate;
import af.albertsoft.elevator.admin.AdminSystem;
import af.albertsoft.elevator.system.Building;
import af.albertsoft.elevator.system.BuildingOperate;
import af.albertsoft.elevator.system.Elevator;
import af.albertsoft.elevator.system.ElevatorOperate;
import af.albertsoft.elevator.system.ElevatorState;
import af.albertsoft.elevator.system.SystemControl;
import af.albertsoft.elevator.system.SystemData;
import af.albertsoft.elevator.system.SystemOperate;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class AllAboveSystem implements BuildingOperate,SystemOperate,ElevatorOperate,AdminOperate,ControlOperate{

    private static int staticId;
    
    public static int createId(){
        return ++staticId;
    }
    
    private static AllAboveSystem _system;
        
    public static void initSystem(SystemControl control){
        if(_system==null){
            _system=new AllAboveSystem(control);            
        }
    }        
    
    public static AllAboveSystem getInstance(){
        if(_system==null){
            _system=new AllAboveSystem(null);            
        }
        return _system;
    }

    private AllAboveSystem(SystemControl control) {
        this.control = control;
    }
       
    private SystemControl control;

    public SystemControl getControl() {
        return control;
    }

    public void setControl(SystemControl control) {
        this.control = control;
    }

    
    
    @Override
    public Building createBuilding(String buildingName, int buildingId, int startFloor, int endFloor) {

        Building build = new Building(buildingName,buildingId,startFloor,endFloor,new HashMap<>());        
        return build;
    }

    @Override
    public void modifyBuilding(Building building, String newname) {
        
        if(building==null){
            System.out.println("Modify Building error:building is null!");
            return;
        }
        building.setBuildingName(newname);
    }

    @Override
    public void removeBuilding(int buildingid) {
        
        if(control==null){
            System.out.println("Remove Building error:SystemControl is null!");
            return;
        }
        
        Map<Integer,Building> buildingMap = control.getSystemData().getBuildingMap();

        if(buildingMap.containsKey(buildingid)==false){
            System.out.println("Remove Building error:id is not valid,please select a correct BuildingId");
            return;
        }
        
        buildingMap.remove(buildingid);
    }

    @Override
    public SystemData create(String systemName,String systemPath) {
        
        if(control==null){
            System.out.println("Create SystemData Error:SystemControl is null!");
            return null;
        }
        SystemData data = new SystemData(systemName,new HashMap<>(),new HashMap<>(),new AdminSystem(new HashMap<>()));
        data.setSystemPath(systemPath);
        return data;
    }

    @Override
    public void save(SystemData data) {        
        //should imple for save data to xml file.
        SystemData.loadoutSystem(data);
    }

    @Override
    public Elevator createElevator(String elevatorName, int elevatorId, int buildingId, int globalId, int startFloor, int endFloor) {

        Elevator elevator = new Elevator(elevatorName,elevatorId,buildingId,globalId,startFloor,endFloor,startFloor,
                new boolean[endFloor-startFloor+1],new boolean[endFloor-startFloor+1],new boolean[endFloor-startFloor+1],ElevatorState.UNSETUP);
        
        return elevator;
    }

    @Override
    public void modifyElevator(Elevator elevator, String newname) {

        if(elevator==null){
            System.out.println("Modify Elevator Error:elevator is null!");
            return;
        }
        elevator.setElevatorName(newname);
    }

    @Override
    public void removeElevator(int globalId){

        if(control==null){
            System.out.println("Remove Elevator Error:SystemControl is null!");
            return;
        }
        Map<Integer,Elevator> elevatorMap = control.getSystemData().getElevatorMap();
        if(elevatorMap.containsKey(globalId)==false){
            System.out.println("Remove Elevator Error:ElevatorId is invalid!");
            return;
        }
        elevatorMap.remove(globalId);
    }
    @Override
    public void stop(Elevator elevator) {
        if(elevator==null){
            System.out.println("Stop Elevator Error:elevator is null!");
            return;
        }
        elevator.setRunState(ElevatorState.PAUSED);
    }

    @Override
    public void resume(Elevator elevator) {
        
        if(elevator==null){
            System.out.println("Resume Elevator Error:elevator is null!");
            return;
        }        
        elevator.setRunState(elevator.getPreState());
    }

    @Override
    public void start(Elevator elevator) {
        
        if(elevator==null){
            System.out.println("Start Elevator Error:elevator is null!");
            return;
        }
        elevator.start(elevator);
    }

    @Override
    public void shutdown(Elevator elevator) {
        
        if(elevator==null){
            System.out.println("Shutdown Elevator Error:elevator is null!");
            return;
        }
        elevator.shutdown(elevator);
    }

    @Override
    public void click(Elevator elevator, String clickcmd, int destfloor) {
        
        if(elevator==null){
            System.out.println("Click Elevator Error:elevator is null!");
            return;
        }
        inclick(elevator,clickcmd,destfloor);
    }

    private void inclick(Elevator elevator,String clickswitch,int destfloor){
        
        boolean[] floors=null;
        switch(clickswitch){
            case "up":
                floors=elevator.getUpClicks();
                break;
            case "down":
                floors=elevator.getDownClicks();
                break;
            case "dest":
                floors=elevator.getDestClicks();
                break;
            default:break;
        }
        
        if(floors==null){
            System.out.println("Reslove for Click is wrong,cause click cmd is not from 'up'/'down'/'dest'");
            return; 
        }
        
        if(destfloor>floors.length-1||destfloor<0){
            System.out.println("Reslove for Click is wrong,cause destfloor is out the elevator range.");
            return; 
        }
        
        //swicth the state for click
        floors[destfloor]=!floors[destfloor];
    }
    

    @Override
    public BuildingOperate getBuildingOperate() {
        return (BuildingOperate)this;
    }

    @Override
    public ElevatorOperate getElevatorOperate() {
        return (ElevatorOperate)this;
    }

    @Override
    public AdminOperate getAdminOperate() {
        return (AdminOperate)this;
    }

    @Override
    public SystemOperate getSystemOperate() {
        return (SystemOperate)this;
    }

    @Override
    public Admin createAdmin(int adminid, String username, String password) {

        Admin admin = new Admin(adminid,username,password);        
        return admin;
    }

    @Override
    public void modifyAdmin(Admin admin, String newname, String newpassword) {
        
        if(admin==null){
            System.out.println("Modify Admin error:admin is null");
            return;
        }
        admin.setUserName(newname);
        admin.setPassword(newpassword);
    }

    @Override
    public void removeAdmin(int adminid) {
        
        if(control==null){
            System.out.println("Remove Admin error:control is null!");
            return;
        }
        
        AdminSystem adminsystem = control.getSystemData().getAdminsystem();
        if(adminsystem.getIdAdminMap().containsKey(adminid)==false){
            System.out.println("Remove Admin error:id for admin is not exsits!");
            return;
        }
        adminsystem.getIdAdminMap().remove(adminid);
    }

    @Override
    public void addBuilding(Building building) {
        
        if(control==null){
            System.out.println("Add Building error:control is null!");
            return;
        }        
        Map<Integer,Building> buildingMap = control.getSystemData().getBuildingMap();
        if(buildingMap.containsKey(building.buildingId)){
            System.out.println("Add Building error:the building is already exsit in buildingMap!");
            return;            
        }
        buildingMap.put(building.buildingId, building);
    }

    @Override
    public Building getBuilding(int buildingid) {

        if(control==null){
            System.out.println("Get Building error:control is null!");
            return null;
        }        
        Map<Integer,Building> buildingMap = control.getSystemData().getBuildingMap();
        if(buildingMap.containsKey(buildingid)==false){
            System.out.println("Get Building error:the building is not exsit in buildingMap!");
            return null;            
        }
        return buildingMap.get(buildingid);
    }

    @Override
    public void switchTo(String datapath) {

        if(control==null||datapath==null){
            System.out.println("Switch To SystemData error:control is null or the data is null!");
            return;
        }        
        
        control.setSystemData(SystemData.loaderSystem(datapath));
    }

    @Override
    public void addElevator(Elevator elevator) {

        if(control==null||elevator==null){
            System.out.println("Add Elevator Error:SystemControl or elevator is null!");
            return;
        }
        control.getSystemData().getElevatorMap().put(elevator.globalId, elevator);
    }

    @Override
    public Elevator getElevator(int globalid) {

        if(control==null){
            System.out.println("Get Elevator Error:SystemControl is null!");
            return null;
        }
        Map<Integer,Elevator> elevatorMap = control.getSystemData().getElevatorMap();
        if(elevatorMap.containsKey(globalid)==false){
            System.out.println("Get Elevator Error:ElevatorId is invalid!");
            return null;
        }
        return elevatorMap.remove(globalid);        
    }

    @Override
    public Admin getNowAdmin() {

        if(control==null){
            System.out.println("Get Elevator Error:SystemControl is null!");
            return null;
        }
        return control.getSystemData().getAdminsystem().getLoginedAdmin();
    }

    @Override
    public void switchTo(int adminid,String password) {
        
        if(control==null){
            System.out.println("Switch To Admin:SystemControl is null!");
            return;
        }
        control.getSystemData().getAdminsystem().login(adminid,password);
    }

    @Override
    public Admin getRegistedAdmin(int adminid) {
        
        if(control==null){
            System.out.println("Get Registed Admin:SystemControl is null!");
            return null;
        }
        Map<Integer,Admin> adminmap = control.getSystemData().getAdminsystem().getIdAdminMap();
        if(adminmap.containsKey(adminid)==false){
            System.out.println("Get Registed Admin:id for admin is invalid!");
            return null;            
        }
        return adminmap.get(adminid);
    }

    @Override
    public void registAdmin(Admin admin) {
        
        if(control==null){
            System.out.println("Registed Admin:SystemControl is null!");
            return;
        }
        Map<Integer,Admin> adminmap = control.getSystemData().getAdminsystem().getIdAdminMap();
        if(adminmap.containsKey(admin.id)){
            System.out.println("Registed Admin:admin is already exsits!");
            return;            
        }
        adminmap.put(admin.id, admin);
    }
}
