/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.admin;

import java.util.Map;

/**
 *
 * @author Admin
 */
public class AdminSystem implements LoginAndOut{
    
    private final Map<Integer,Admin> idAdminMap;    
    private Admin loginedAdmin;

    public AdminSystem(Map<Integer,Admin> idAdminMap) {
        this.idAdminMap=idAdminMap;
    }        

    public Map<Integer, Admin> getIdAdminMap() {
        return idAdminMap;
    }

    public Admin getLoginedAdmin() {
        return loginedAdmin;
    }

    
    @Override
    public boolean login(int id,String passw) {        
        
        if(!idAdminMap.containsKey(id)){
            System.out.println("login error: id not found.");
            return false; 
        }
        
        String password = idAdminMap.get(id).getPassword();
        if(!password.equals(passw)){
            System.out.println("login error: id not found.");
            return false; 
        }
        else{
            loginedAdmin=idAdminMap.get(id);
            return true;
        }                
    }

    @Override
    public void logout() {
        if(loginedAdmin!=null){
            loginedAdmin=null;
        }
    }

    @Override
    public boolean isLogined() {
        return loginedAdmin!=null;
    }    
}
