/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.system;

import java.util.Map;

/**
 *
 * @author Admin
 */
public class Building {
    
    private String buildingName;
    public final int buildingId;
    
    private final int startFloor,endFloor;
    
    private final Map<Integer,Integer> elevatorIdMap;

    public Building(String buildingName,int buildingId, int startFloor, int endFloor,
            Map<Integer, Integer> elevatorIdMap) {
        this.buildingName = buildingName;
        this.buildingId = buildingId;
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        this.elevatorIdMap = elevatorIdMap;
    }
        
    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getStartFloor() {
        return startFloor;
    }

    public int getBuildingLength(){
        return endFloor-startFloor+1;
    }
    
    public int getEndFloor() {
        return endFloor;
    }

    public Map<Integer, Integer> getElevatorIdMap() {
        return elevatorIdMap;
    }        
    
    @Override
    public String toString(){
        return buildingName;
    }
}
