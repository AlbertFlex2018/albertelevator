/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package albertsoft.elevator.control.cmd;

import albertsoft.elevator.control.CmdInterface;

/**
 *
 * @author Administrator
 */
public class SystemCmd implements CmdInterface{
    //system read [filepath]
    //system save [filepath]
    @Override
    public String cmd(String[] cmds) {
        return "";
    }
    @Override
    public String getName() {
        return "system";
    }    
}
