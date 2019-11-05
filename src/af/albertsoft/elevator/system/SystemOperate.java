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
public interface SystemOperate {
    
    public SystemData create(String systemName,String systempath);
    public void switchTo(String datapath);
    public void save(SystemData data);
}
