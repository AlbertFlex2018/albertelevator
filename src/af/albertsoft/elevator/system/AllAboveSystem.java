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
public class AllAboveSystem{

    private static int staticId;
    
    public static int createId(){
        return ++staticId;
    }
    
    private static AllAboveSystem _system;
        
    public static void initSystem(SystemControl control){
        if(_system==null){
            _system=new AllAboveSystem(control);            
        }
    }        
    
    public static AllAboveSystem getInstance(){
        if(_system==null){
            _system=new AllAboveSystem(null);            
        }
        return _system;
    }

    private AllAboveSystem(SystemControl control) {
        this.control = control;
    }
       
    private SystemControl control;

    public SystemControl getControl() {
        return control;
    }

    public void setControl(SystemControl control) {
        this.control = control;
    }
}
