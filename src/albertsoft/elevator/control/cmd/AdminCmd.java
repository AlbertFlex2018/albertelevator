package albertsoft.elevator.control.cmd;

import albertsoft.elevator.control.CmdInterface;

/**
 *
 * @author Administrator
 */
public class AdminCmd implements CmdInterface{
    //admin look-registed
    //admin look-logined
    //admin look-id [adminid]
    //admin regist [id] [grunt] [password] [operateadminid]
    //admin unregist [id] [operatedadminid]
    //admin login [id] [password]
    //admin logout [id] [password]
    @Override
    public String cmd(String[] cmds) {
        return "";
    }    

    @Override
    public String getName() {
        return "admin";
    }
}
