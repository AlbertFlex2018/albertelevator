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
public interface SystemControl {

    public void initSystem();
    public void startSystem();
    public void updateSystem();
    public void shutdownSystem();
    public SystemData getSystemData();    
    public void setSystemData(SystemData data);
}
