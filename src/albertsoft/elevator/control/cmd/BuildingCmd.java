package albertsoft.elevator.control.cmd;

import albertsoft.elevator.control.CmdInterface;

/**
 *
 * @author Administrator
 */
public class BuildingCmd implements CmdInterface{
    //building look-all
    //building look-buildingsonly
    //building look-id [buildingid]
    //building look-name [buildingname]
    //building add [id] [name] [last] [top]
    //building modify [id] [newname] [newname] [newtop]
    //building remove [id] 
    @Override
    public String cmd(String[] cmds) {
        return "";
    }    
    @Override
    public String getName() {
        return "building";
    }    
}
