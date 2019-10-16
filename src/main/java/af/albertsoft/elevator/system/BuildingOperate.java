/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.system;

/**
 *
 * @author Admin
 */
public interface BuildingOperate {
    
    public Building createBuilding(String buildingName, int buildingId, int startFloor, int endFloor);
    public void addBuilding(Building building);
    public Building getBuilding(int buildingid);
    public void modifyBuilding(Building building,String newname);
    public void removeBuilding(int buildingid);
}
