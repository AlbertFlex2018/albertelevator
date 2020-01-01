/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import elevator.admin.Admin;
import elevator.admin.AdminCenter;
import elevator.admin.AdminGrunt;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class AdminCenterTest {
    public static void main(String[] args) {
        AdminCenter center=AdminCenter.getInstance();
        Scanner scan = new Scanner(System.in);
        boolean exit=false;
        while(!exit){
            String word=scan.nextLine();
            if(word.equals("showreg")){
                showRegistedAdmins(center);
            }
            else if(word.equals("showlog")){
                showLoginedAdmins(center);
            }else if(word.startsWith("signup")){
                String[] cmd=word.split(" ");
                if(cmd.length==2){
                    String password=cmd[1];
                    System.out.println("created admin's id:"+center.registIn(password,AdminGrunt.LOOK));
                }else if(cmd.length==3){
                    String ps=cmd[1];
                    AdminGrunt grunt=AdminGrunt.valueOf(cmd[2]);                    
                    System.out.println("created admin's id:"+center.registIn(ps,grunt));
                }
            }else if(word.startsWith("signdown")){
                String[] cmd=word.split(" ");
                if(cmd.length==2){
                    String password=cmd[1];
                    System.out.println("signdown for-id:"+center.registIn(password,AdminGrunt.LOOK));
                }else if(cmd.length==3){
                    String ids=cmd[0];
                    String ps=cmd[1];                    
                    center.registOut(Long.parseLong(ids),ps);
                }                
            }
            else if(word.startsWith("signin")){
                String[] cmd=word.split(" ");
                if(cmd.length!=3){
                    System.err.println("password and userid is nesscessry");
                }else{
                    long userid=Long.parseLong(cmd[1]);
                    String ps=cmd[2];
                    center.logIn(userid, ps);
                }
            }else if(word.startsWith("signout")){
                String[] cmd=word.split(" ");
                if(cmd.length!=3){
                    System.err.println("password and userid is nesscessry");
                }else{
                    long userid=Long.parseLong(cmd[1]);
                    String ps=cmd[2];
                    center.logOut(userid, ps);
                }                
            }else if(word.equals("exit")){
                exit=true;
            }
        }
    }
    private static void showRegistedAdmins(AdminCenter center){
        System.out.println("== show registed admins ==");
        System.out.println("id\t password\t grunt");
        List<Admin> regslist=center.getRegistedAdmins();
        regslist.forEach((admin)->{
            long id=admin.getId();
            String pass=admin.getPassword();
            AdminGrunt grunt=admin.getGrunt();
            System.out.println(id+"\t"+pass+"\t"+grunt.name());
        });
    }
    private static void showLoginedAdmins(AdminCenter center){
        System.out.println("== show logined admins ==");
        System.out.println("id\tgrunt");
        List<Admin> regslist=center.getLogedAdmins();
        regslist.forEach((admin)->{
            long id=admin.getId();
            AdminGrunt grunt=admin.getGrunt();
            System.out.println(id+"\t"+grunt.name());
        });        
    }
}
