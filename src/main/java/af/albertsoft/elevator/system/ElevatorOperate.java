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
public interface ElevatorOperate {
    
    public Elevator createElevator(String elevatorName, int elevatorId, int buildingId,int globalId, int startFloor, int endFloor);
    public void addElevator(Elevator elevator);
    public Elevator getElevator(int globalid);
    public void modifyElevator(Elevator elevator,String newname);
    public void removeElevator(int globalid);
    
    public void stop(Elevator elevator);
    public void resume(Elevator elevator);
    public void start(Elevator elevator);
    public void shutdown(Elevator elevator);

    public void click(Elevator elevator,String clickcmd,int destfloor);
}
