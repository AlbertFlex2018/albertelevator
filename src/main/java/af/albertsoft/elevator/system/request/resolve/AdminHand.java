/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.system.request.resolve;

import af.albertsoft.elevator.system.ElevatorOperate;
import af.albertsoft.elevator.system.SystemData;
import af.albertsoft.elevator.system.SystemOperate;
import af.albertsoft.elevator.system.control.AllAboveSystem;
import af.albertsoft.elevator.system.request.RequestResolve;
import af.albertsoft.elevator.system.request.RequestType;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class AdminHand implements RequestResolve{
    
    /**
     * cmdMap:
     * adminid,
     * type:(system)save,new,switchTo (elevator,building,admin)modify,add,remove (elevator,building)stop,resume,start,shutdown (elevator)click 
     * @param data
     * @param type
     * @param cmdMap
     */
    @Override
    public void resolveRequest(SystemData data, RequestType type, Map<String, String> cmdMap) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        /**
         * type=system
         * action=save
         * action=new need systemname savepath eg. systems.xml
         * action=switchTo need systempath eg.-system1.xml
         */
    private void solveSystem(Map<String,String> cmdMap){

        AllAboveSystem system = AllAboveSystem.getInstance();
        SystemOperate operate = system.getSystemOperate();
        switch(cmdMap.get("action")){
            case "save":
                operate.save(system.getControl().getSystemData());break;
            case "new":
                system.getControl().setSystemData(operate.create(cmdMap.get("systemname"),cmdMap.get("systempath")));break;                
            case "switchTo":
                operate.switchTo(cmdMap.get("systempath"));break;
            default:break;
        }
    }    
    
    /**
     * (elevator,building,admin)modify,add,remove
     * edittype=elevator,building,admin,
     * 
     * @param cmdMap 
     */
    private void solveEdit(Map<String,String> cmdMap){
        
       
       String edittype=cmdMap.get("edittype");
       switch(edittype){
           case "elevator":
               
       }
    }

    /**
     * add:String elevatorName, int elevatorId, int buildingId,int globalId, int startFloor, int endFloor
     * modify:globalId,String newName,
     * remove:int globalId
     * @param cmdMap 
     */
    private void elevatorSolve(Map<String,String> cmdMap){
        
        ElevatorOperate operate = AllAboveSystem.getInstance().getElevatorOperate();
        switch(cmdMap.get("action")){
            case "add":
                String elevatorName = cmdMap.get("elevatorName");
                int elevatorId=Integer.parseInt(cmdMap.get("elevatorId"));
                int buildingId=Integer.parseInt(cmdMap.get("buildingId"));
                int globalId=Integer.parseInt(cmdMap.get("globalId"));                
                int startFloor=Integer.parseInt(cmdMap.get("startFloor"));
                int endFloor=Integer.parseInt(cmdMap.get("endFloor"));                
                operate.addElevator(operate.createElevator(elevatorName, elevatorId, buildingId, globalId, startFloor, endFloor));
                break;
            case "modify":
                String newname=cmdMap.get("newName");
                int globalid = Integer.parseInt(cmdMap.get("globalId"));
                operate.modifyElevator(operate.getElevator(globalid), newname);
                break;
            case "remove":
                int globid = Integer.parseInt(cmdMap.get("globalId"));
                operate.removeElevator(globid);
                break;
            default:break;
        }
    }

    /**
     * add:String elevatorName,int buildingId,int startFloor, int endFloor
     * modify:globalId,String newName,
     * remove:int globalId
     * @param cmdMap 
     */
    
    private void solveBuilding(Map<String,String> cmdMap){
        
    }
}
