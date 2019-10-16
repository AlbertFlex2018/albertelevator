/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.admin;

/**
 *
 * @author Admin
 */
public interface LoginAndOut {
    
    public boolean isLogined();
    public boolean login(int id,String password);
    public void logout();
}
