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
public class DefaultStrategy implements ElevatorStrategy{

    @Override
    public void init(Elevator elevator) {
        elevator.setRunState(ElevatorState.SETUP);
    }

    @Override
    public void start(Elevator elevator) {
        elevator.setRunState(ElevatorState.IDLE);        
    }
    
    @Override
    public void update(Elevator elevator) {
        //这里是更新电梯状态的重要方法
        //3种状态idle,up,down
        switch(elevator.getRunState()){
            case SETUP:start(elevator);break;
            case IDLE:handleIdle(elevator);break;
            case RUN_UP:handleUp(elevator);break;
            case RUN_DOWN:handleDown(elevator);break;
            case DOOR_OPENNING:
                elevator.setRunState(ElevatorState.DOOR_CLOSING);
                break;
            case DOOR_CLOSING:
                if(closingDoortoState!=null)
                    elevator.setRunState(closingDoortoState);
                break;
            default:break;
        }
    }

    private int destfloor;    
    ElevatorState closingDoortoState=ElevatorState.EXCEPTION;

    private void handleIdle(Elevator elevator){
        boolean[] up = elevator.getUpClicks();
        boolean[] down=elevator.getDownClicks();
        boolean[] dest=elevator.getDestClicks();
        int destf=-10;
        for(int i=elevator.getStartFloor();i!=elevator.getEndFloor()+1;++i){
            if(up[i]||down[i]||dest[i]){
                destf=i;
                break;
            }
        }
        if(destf==-10){
            return;
        }
        
        //如果当前楼层有上或者下的请求
        if(destf==elevator.getCurrentFloor()){
             if(up[destf]){                 
                 elevator.setRunState(ElevatorState.DOOR_OPENNING);
                 closingDoortoState=ElevatorState.RUN_UP;                 
                 up[destf]=false;
                 return;
             }
             else if(down[destf]){
                 elevator.setRunState(ElevatorState.DOOR_OPENNING);
                 closingDoortoState=ElevatorState.RUN_DOWN;
                 down[destf]=false;
                 return;
             }
             destfloor=-10;            
        }
        
        //如果请求的楼层不是当前楼层,则修改目标楼层为目标楼层
        destfloor=destf;
        if(destf>elevator.getCurrentFloor()){
            elevator.setRunState(ElevatorState.RUN_UP);                                    
        }else{
            elevator.setRunState(ElevatorState.RUN_DOWN);
        }
        
    }
    
    private int findUpFloor(Elevator elevator){
        int cfloor=elevator.getCurrentFloor();
        boolean[] up = elevator.getUpClicks();
        boolean[] dest=elevator.getDestClicks();
        int findfloor=cfloor;
        for(int i=cfloor;i!=elevator.getEndFloor();++i){
            if(up[i]||dest[i]){
                findfloor=i;
            }
        }
        
        return findfloor;
    }
    private void handleUp(Elevator elevator){
        
        if(destfloor==-10){
            System.out.println("Haven't select dest floor");
            elevator.setRunState(ElevatorState.EXCEPTION);            
        }
        int cfloor=elevator.getCurrentFloor();
        if((cfloor+1)>elevator.getEndFloor()){
            System.out.println("Up is Wrong!");
            elevator.setRunState(ElevatorState.EXCEPTION);
            return;
        }
        
        elevator.setCurrentFloor(++cfloor);
        //若已经为目标楼层，则开门，设置下一状态为闲置
        if(destfloor!=-10&&destfloor==cfloor){            
             elevator.setRunState(ElevatorState.DOOR_OPENNING);
             closingDoortoState=ElevatorState.IDLE;                             
             destfloor=-10;
             return;
        }

        //若非目标楼层
            //若上楼层已按下，则开门，设置下一状态为up，重置上楼层按钮
            //若目标楼层已按下，则开门，设置...，重置目标按钮，
        if(elevator.getUpClicks()[cfloor]||elevator.getDestClicks()[cfloor])
        {                            
             elevator.setRunState(ElevatorState.DOOR_OPENNING);             
             elevator.getUpClicks()[cfloor]=false;
             elevator.getDestClicks()[cfloor]=false;
             closingDoortoState=ElevatorState.RUN_UP;            
        }
        //更新目标楼层
        destfloor=findUpFloor(elevator);
    }
    private void handleDown(Elevator elevator){
        
        //如果越界，则报异常
        int cfloor=elevator.getCurrentFloor();
        if((cfloor-1)<elevator.getStartFloor()){
            System.out.println("Down is Wrong!");
            elevator.setRunState(ElevatorState.EXCEPTION);
            return;
        }
        
        elevator.setCurrentFloor(--cfloor);
        //若已经为目标楼层，则开门，设置下一状态为闲置
        if(destfloor!=-10&&destfloor==cfloor){            
             elevator.setRunState(ElevatorState.DOOR_OPENNING);
             closingDoortoState=ElevatorState.IDLE;                             
             destfloor=-10;
             return;
        }
        
        //若非目标楼层，更新目标楼层,
        //如果沿路有向下有按钮，则开门，
        if(elevator.getDownClicks()[cfloor]||elevator.getDestClicks()[cfloor])
        {                            
             elevator.setRunState(ElevatorState.DOOR_OPENNING);             
             elevator.getDownClicks()[cfloor]=false;
             elevator.getDestClicks()[cfloor]=false;
             closingDoortoState=ElevatorState.RUN_DOWN;            
        }
        
        //更新目标楼层
        destfloor=findDownFloor(elevator);        
    }
    private int findDownFloor(Elevator elevator){
        int cfloor=elevator.getCurrentFloor();
        boolean[] down = elevator.getDownClicks();
        boolean[] dest=elevator.getDestClicks();
        int findfloor=cfloor;
        for(int i=cfloor;i>=elevator.getStartFloor();--i){
            if(down[i]||dest[i]){
                findfloor=i;
            }
        }
        
        return findfloor;        
    }
    

    @Override
    public void shutdown(Elevator elevator) {
        //重置所有的按键状态
        boolean[] up=elevator.getUpClicks();
        boolean[] down=elevator.getDownClicks();
        boolean[] dest=elevator.getDestClicks();

        for(int i=elevator.getStartFloor();i!=elevator.getEndFloor()+1;++i){
            up[i]=down[i]=dest[i]=false;
        }
        elevator.setRunState(ElevatorState.UNSETUP);
    }    
}
