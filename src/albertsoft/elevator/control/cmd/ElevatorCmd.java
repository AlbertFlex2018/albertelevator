package albertsoft.elevator.control.cmd;

import albertsoft.elevator.control.CmdInterface;

/**
 *
 * @author Administrator
 */
public class ElevatorCmd implements CmdInterface{

    //elevator look-all
    //elevator look-elevators [buildingid]
    //elevator look-id [buildingid] [elevatorid]
    //elevator look-name [buildingid] [elevatorname]
    //elevator add [buildingid] [id] [name] [start] [end]
    //elevator modify [buildingid] [id] [newname] [newstart] [newend]
    //elevator remove [buildingid] [id] 
    @Override
    public String cmd(String[] cmds) {
        return "";
    }
    @Override
    public String getName() {
        return "elevator";
    }    
}