/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.system;

import af.albertsoft.elevator.admin.AdminOperate;
import af.albertsoft.elevator.system.BuildingOperate;
import af.albertsoft.elevator.system.ElevatorOperate;
import af.albertsoft.elevator.system.SystemOperate;

/**
 *
 * @author Admin
 */
public interface ControlOperate {
    
    public BuildingOperate getBuildingOperate();
    public ElevatorOperate getElevatorOperate();
    public AdminOperate getAdminOperate();
    public SystemOperate getSystemOperate();    
}
