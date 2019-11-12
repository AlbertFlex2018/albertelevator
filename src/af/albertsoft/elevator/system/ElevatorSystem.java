/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.system;

import java.util.Iterator;

/**
 *
 * @author Admin
 */
public class ElevatorSystem implements SystemControl{
    
    private SystemData data;

    public ElevatorSystem(SystemData data) {
        this.data = data;
    }            

    @Override
    public void setSystemData(SystemData data) {
        this.data=data;
    }

    private enum OperateSwitch{
        INIT,
        START,
        UPDATE,
        SHUTDOWN,
    }
    private void operateSystem(OperateSwitch operate)
    {
        Iterator<Elevator> elevatorIter = data.getElevatorMap().values().iterator();
        while(elevatorIter.hasNext())
        {
            Elevator elevator = elevatorIter.next();
            switch(operate)
            {
                case INIT:elevator.init(elevator);break;
                case START:elevator.start(elevator);break;
                case UPDATE:elevator.update(elevator);break;
                case SHUTDOWN:elevator.shutdown(elevator);break;
                default:break;
            }            
        }        
    }
    
    @Override
    public void initSystem() {
        this.operateSystem(OperateSwitch.INIT);
    }

    @Override
    public void startSystem() {
        this.operateSystem(OperateSwitch.START);
    }

    @Override
    public void updateSystem() {
        this.operateSystem(OperateSwitch.UPDATE);
    }

    @Override
    public void shutdownSystem() {
        this.operateSystem(OperateSwitch.SHUTDOWN);
    }

    @Override
    public SystemData getSystemData() {
        return data;
    }
}
