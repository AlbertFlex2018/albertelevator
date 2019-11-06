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
public class Elevator implements ElevatorStrategy{
           
    private String elevatorName;

    public final int elevatorId,buildingId,globalId;

    private final int startFloor,endFloor;
    private int currentFloor;

    /**
     * The click buttons for this elevator above up,down,and innerclick(dest)
     */
    private final boolean[] upClicks,downClicks,DestClicks;
    
    private ElevatorState runState,preState;
    private ElevatorStrategy strategy;

    public Elevator(String elevatorName, int elevatorId, int buildingId,int globalId, int startFloor, int endFloor, 
            int currentFloor, boolean[] upClicks, boolean[] downClicks, boolean[] DestClicks,ElevatorState runState) {
        this.elevatorName = elevatorName;
        this.elevatorId = elevatorId;
        this.buildingId = buildingId;
        this.globalId=globalId;
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        this.currentFloor = currentFloor;
        this.upClicks = upClicks;
        this.downClicks = downClicks;
        this.DestClicks = DestClicks;
        this.runState=this.preState=runState;
    }

    public String getElevatorName() {
        return elevatorName;
    }

    public void setElevatorName(String elevatorName) {
        this.elevatorName = elevatorName;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int  getFloorLength(){
        return endFloor-startFloor+1;
    }
    public int getStartFloor() {
        return startFloor;
    }

    public int getEndFloor() {
        return endFloor;
    }

    public boolean[] getUpClicks() {
        return upClicks;
    }

    public boolean[] getDownClicks() {
        return downClicks;
    }

    public boolean[] getDestClicks() {
        return DestClicks;
    }        

    public ElevatorState getRunState() {
        return runState;
    }

    public ElevatorStrategy getStrategy() {
        return strategy;
    }

    public void setRunState(ElevatorState runState) {
        this.preState=this.runState;
        this.runState = runState;
    }

    public ElevatorState getPreState() {
        return preState;
    }

    public void setStrategy(ElevatorStrategy strategy) {
        this.strategy = strategy;
    }       

    @Override
    public void update(Elevator elevator) {
        if(this.strategy!=null){
            this.strategy.update(elevator);
        }
    }

    @Override
    public void init(Elevator elevator) {
        if(this.strategy!=null){
            this.strategy.init(elevator);
        }
    }

    @Override
    public void start(Elevator elevator) {
        if(this.strategy!=null){
            this.strategy.start(elevator);
        }
    }

    @Override
    public void shutdown(Elevator elevator) {
        if(this.strategy!=null){
            this.strategy.shutdown(elevator);
        }
    }
    @Override
    public String toString(){
        return elevatorName;
    }    
}
