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
public interface AdminOperate {

    public Admin getRegistedAdmin(int adminid);
    public Admin createAdmin(int adminid,String username,String password); 
    public Admin getNowAdmin();    
    public void switchTo(int adminid,String password);
    public void modifyAdmin(Admin admin,String newname,String newpassword);
    public void registAdmin(Admin admin);
    public void removeAdmin(int adminid);
}
