/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import elevator.control.Building;
import elevator.control.BuildingCenter;
import elevator.control.Elevator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class BuildingCenterTest {
    public static void main(String[] args) {
        BuildingCenter center=BuildingCenter.getInstance();
        Scanner scan=new Scanner(System.in);
        boolean exit=false;
        while(!exit){
            String word=scan.nextLine();
            if(word.startsWith("addbuilding")){
                addBuilding(word,center);
            }else if(word.startsWith("addelevator")){
                addElevator(word,center);
            }else if(word.equals("showbuilds")){
                showBuildingsOnCenter(center);
            }else if(word.startsWith("showelevators")){
                showElevatorsOnBuilding(word,center);
            }else if(word.equals("exit")){
                exit=true;
            }                
        }
    }
    //addbuilding name last top
    private static void addBuilding(String word,BuildingCenter center){
        String[] cmd=word.split(" ");
        if(cmd.length!=4){
            System.err.println("input for add builing format error!");
            System.err.println("follow:addbuilding name last top");
            return;
        }
        String name=cmd[1];
        int last=Integer.parseInt(cmd[2]);
        int top=Integer.parseInt(cmd[3]);
        center.addBuilding(name, last, top);
    }            
    //addelevator buildid name start end
    private static void addElevator(String word,BuildingCenter center){
        String[] cmd=word.split(" ");
        if(cmd.length!=5){
            System.err.println("input for add elevator format error!");
            System.err.println("follow:addbuilding buildid name start end");
            return;            
        }
        
        
        long bid = Long.parseLong(cmd[1]);
        Building build=center.getBuildingById(bid);
        if(build==null){
            System.err.println("building for "+bid+" not found.");
            return;
        }
        
        String name=cmd[2];
        int start=Integer.parseInt(cmd[3]);
        int end=Integer.parseInt(cmd[4]);
        
        System.out.println("add elevator:"+name+" - "+start+" - "+end);
        build.addElevator(name, start, end);
    }
    //showelevators buildid
    private static void showElevatorsOnBuilding(String word,BuildingCenter center){
        String[] cmd=word.split(" ");
        if(cmd.length!=2){
            System.err.println("input for elevator format error!");
            System.err.println("follow:showelevator buildid");
            return;            
        }        
        Building build=center.getBuildingById(Long.parseLong(cmd[1]));
        List<Elevator> elist=build.getElevatorList();
        System.out.println("== elevators on "+build.getName()+" ==");
        System.out.println("id \t buildid \t name \t start \t end \t current");
        elist.forEach((ele) -> {
            System.out.println(ele.getId()+"\t"+ele.getBuildid()+"\t"+
                    ele.getName()+"\t"+ele.getStartFloor()+"\t"+ele.getEndFloor()+"\t"+ele.getCurrentFloor());
        });
    }
    private static void showBuildingsOnCenter(BuildingCenter center){
        List<Building> blist=center.getBuildList();
        System.out.println("== buildings on center");
        System.out.println("id \t name \t last \t top \t ");
        for(Building build : blist){
            System.out.println(build.getId()+"\t"+build.getName()+"\t"+build.getLastLevel()+"\t"+build.getTopLevel());
        }
    }
}
