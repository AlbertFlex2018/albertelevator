/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevator.control;

/**
 *
 * @author Albert Flex
 */
public class RunStrategy implements ElevatorRunStrategy{
    @Override
    public void run(Elevator elevator){
        //handle idle
        //handle up
        //handle down
        ElevatorState state=elevator.getState();
        switch(state){
            case IDLE:
                handleIdle(elevator);
                break;
            case MOVEUP:
                handleUp(elevator);
                break;
            case MOVEDOWN:
                handleDown(elevator);
                break;
            case UNSETUP:
                elevator.start();
                break;
            default:break;
        }
    }    
    private void handleIdle(Elevator elevator){
        //如果在下层的按钮有按下，则设置目标为该最低的楼层，设置目标状态为该楼层按钮的方向，并设置运行状态为down
        //如果在上层的按钮有按下，则设置目标为该最高的楼层，设置目标状态，并设置运行状态为up
    }
    private void handleUp(Elevator elevator){
        //如果没有到达目标，则继续运行
        //如果到达，则更改状态为目标状态
        //如果up中有这一层，则开门
        //如果dest中有这一层，则打开门
    }
    private void handleDown(Elevator elevator){
        //如果没有到达目标，则继续运行
        //如果到达 ，则更改状态为目标状态
        //如果dest中有这一层，则打开门        
    }
}
