package albertsoft.elevator.control.cmd;

import albertsoft.elevator.control.Building;
import albertsoft.elevator.control.BuildingCenter;
import albertsoft.elevator.control.CmdInterface;
import albertsoft.elevator.control.Elevator;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ElevatorCmd implements CmdInterface{

    //elevator look-elevators [buildingid]
    //elevator look-id [buildingid] [elevatorid]
    //elevator look-name [buildingid] [elevatorname]
    //elevator add [buildingid] [id] [name] [start] [end]
    //elevator modify [buildingid] [id] [newname]
    //elevator remove [buildingid] [id] 
    @Override
    public String cmd(String[] cmds) {
        return "";
    }
    @Override
    public String getName() {
        return "elevator";
    }    
    private String look_elevators(String buildingid){
        BuildingCenter center=BuildingCenter.getInstance();
        long lid=Long.parseLong(buildingid);
        Building build=center.getBuildingById(lid);
        List<Elevator> elevatorlist=build.getElevatorList();
        System.out.println("===============");
        System.out.println("buildingdid\tid\tname\tstart\tend");
        elevatorlist.forEach((elevator)->{
            System.out.print(elevator.getBuildid()+"\t");
            System.out.print(elevator.getId()+"\t");
            System.out.print(elevator.getName()+"\t");
            System.out.print(elevator.getStartFloor()+"\t");
            System.out.println(elevator.getEndFloor());            
        });
        System.out.println("===============");
        return "look elevators end.";
    }
    private String look_id(String buildingid,String elevatorid){
        return "";
    }
    private String look_name(String buildingid,String elevatorname){
        return "";
    }
    private String add(String buildingid,String elevatorid,String name,String start,String end){
        return "";
    }
    private String modify(String buildingid,String id,String newname){
        return "";
    }
    private String remove(String buildingid,String id){
        return "";
    }    
}