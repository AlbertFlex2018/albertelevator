/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.system;

import af.albertsoft.elevator.admin.AdminSystem;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class SystemData {
    
    /**
     * .sys.xml file to load.
     */
    private String systemName;
    private String systemPath;
    
    private final Map<Integer,Building> buildingMap;
    private final Map<Integer,Elevator> elevatorMap;//is map for globalid
    private final AdminSystem adminsystem;

    public SystemData(String systemName, Map<Integer,Building> buildingMap, 
            Map<Integer,Elevator> elevatorMap,AdminSystem adminSystem) {
        this.systemName = systemName;
        this.buildingMap = buildingMap;
        this.elevatorMap = elevatorMap;
        this.adminsystem=adminSystem;
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
    
    public Building getBuildingByName(String name)
    {
        Iterator<Building> builditer=buildingMap.values().iterator();
        while(builditer.hasNext())
        {
            Building building = builditer.next();
            if(building.getBuildingName().equals(name))
                return building;
        }
        return null;
    }
    
    
    public Elevator getElevatorByName(String name)
    {
        Iterator<Elevator> eleiter=elevatorMap.values().iterator();
        while(eleiter.hasNext())
        {
            Elevator ele = eleiter.next();
            if(ele.getElevatorName().equals(name))
                return ele;
        }
        return null;
    }
    
    
    
    public Map<Integer, Elevator> getElevatorMap() {
        return elevatorMap;
    }            

    public AdminSystem getAdminSystem() {
        return adminsystem;
    }

    public static SystemData loaderSystem(String systemxmlPath){
        
        return null;
    }

    public static void loadoutSystem(SystemData data){
        
    }
    
    public static SystemData loadfromSystem(String filepath){        
        
        return null;
    }

    public String getSystemPath() {
        return systemPath;
    }

    public void setSystemPath(String systemPath) {
        this.systemPath = systemPath;
    }    
}
