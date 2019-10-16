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
public interface ElevatorStrategy{

    /**
     * This Interface is used to update Elevator,we can set the strategy for the elevator logic
     * @param elevator
     */
      public void init(Elevator elevator);
      public void start(Elevator elevator);
      public void update(Elevator elevator);
      public void shutdown(Elevator elevator);
}