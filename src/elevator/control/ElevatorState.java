/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevator.control;

public enum ElevatorState {
    UNSETUP,//未启动
    IDLE,//空闲
    MOVEUP,//向上移动
    MOVEDOWN,//向下移动
    OPENDOOR,//打开门
    CLOSIGDOOR,//关闭门
}
