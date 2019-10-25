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
public enum ElevatorState {
    UNSETUP,//未启动
    SETUP,//已启动但未进入运行
    EXCEPTION,//状态异常
    PAUSED,//已强制暂停
    
    IDLE,//闲置    
    RUN_UP,//向上运行
    RUN_DOWN,//向下运行
    
    DOOR_CLOSING,//正关门
    DOOR_OPENNING,//正开门
}
