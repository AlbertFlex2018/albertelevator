/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.system.request.resolve;

import af.albertsoft.elevator.system.Building;
import af.albertsoft.elevator.system.Elevator;
import af.albertsoft.elevator.system.SystemData;
import af.albertsoft.elevator.system.request.RequestResolve;
import af.albertsoft.elevator.system.request.RequestType;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class UserHand implements RequestResolve{
    
    /**
     * cmdMap:
     * buildingid,elevatorid(not globalid),click(up/down/dest),destfloor
     * 
     * @param data
     * @param type
     * @param cmdMap 
     */
    @Override
    public void resolveRequest(SystemData data, RequestType type, Map<String, String> cmdMap) {
        
        if(type!=RequestType.USER){
            System.out.println("Parse to a wrong request: from "+type.toString()+" to "+RequestType.USER.toString());
            return;
        }
                
        int buildingId = Integer.parseInt(cmdMap.get("buildingid"));
        int elevatorId = Integer.parseInt(cmdMap.get("elevatorid"));
        String clickswitch = cmdMap.get("click");
        int destfloor = Integer.parseInt(cmdMap.get("destfloor"));
        
        Building building = data.getBuildingMap().get(buildingId);
        if(building==null){
            System.out.println("Reslove for Request'"+type.toString()+"' is wrong,cause Building is not exsist.");
            return;
        }
        
        if(building.getElevatorIdMap().containsKey(elevatorId)==false){
            System.out.println("Reslove for Request'"+type.toString()+"' is wrong,cause Elevator is not exsist.");
            return;            
        }

        int globalId = building.getElevatorIdMap().get(elevatorId);
        Elevator elevator = data.getElevatorMap().get(globalId);
        if(elevator==null){
            System.out.println("Reslove for Request'"+type.toString()+"' is wrong,cause Elevator is not exsist.");
            return;                        
        }

        click(elevator,clickswitch,destfloor);
        
    }  
    
    private void click(Elevator elevator,String clickswitch,int destfloor){
        
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
}
