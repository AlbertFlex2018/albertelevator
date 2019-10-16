/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.system;

import af.albertsoft.elevator.admin.AdminSystem;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class SystemData {
    
    private String systemName;
    private String systemPath;
    
    private final Map<Integer,Building> buildingMap;
    private final Map<Integer,Elevator> elevatorMap;//is map for globalid
    private final AdminSystem adminsystem;

    public SystemData(String systemName, Map<Integer,Building> buildingMap, Map<Integer,Elevator> elevatorMap) {
        this.systemName = systemName;
        this.buildingMap = buildingMap;
        this.elevatorMap = elevatorMap;
        adminsystem=new AdminSystem(new HashMap<>());
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public Map<Integer, Building> getBuildingMap() {
        return buildingMap;
    }

    public Map<Integer, Elevator> getElevatorMap() {
        return elevatorMap;
    }            

    public AdminSystem getAdminsystem() {
        return adminsystem;
    }

    public static SystemData loaderSystem(String systemxmlPath){
        
        return null;
    }

    public static void loadoutSystem(SystemData data){
        
    }
    public String getSystemPath() {
        return systemPath;
    }

    public void setSystemPath(String systemPath) {
        this.systemPath = systemPath;
    }
    
}
