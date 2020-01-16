package control;

import elevator.control.Elevator;
import elevator.control.ElevatorRunStrategy;
import elevator.control.RunStrategy;
import java.util.Scanner;

/**
 *
 * @author Albert Flex
 */
public class TestElevatorStrategy{
    public static void main(String[] args) {
        Elevator elevator=new Elevator(1,1,"E1",1,6,1);
        elevator.start();
        Scanner scan=new Scanner(System.in);
        ElevatorRunStrategy run=new RunStrategy();
        boolean exit=false;
        while(!exit){
            String word=scan.nextLine();           
            if(word.equals("exit")){
                exit=true;
            }else{
                handleWord(word,elevator,run);
            }
            System.out.println(elevator.getState().toString()+" - "+elevator.getCurrentFloor());
            boolean[] up=elevator.getUpClicks();
            boolean[] down=elevator.getDownClicks();
            boolean[] dest=elevator.getDestClicks();
            System.out.print("[name]:\t");
            for(int i=0;i!=up.length;++i)
                System.out.print(i+"\t");
            System.out.println("");
            System.out.print("[up]:\t");
            for(boolean u: up){
                System.out.print(u+"\t");
            }
            System.out.println("");
            System.out.print("[down]:\t");
            for(boolean u: down){
                System.out.print(u+"\t");
            }
            System.out.println("");
            System.out.print("[dest]:\t");
            for(boolean u: dest){
                System.out.print(u+"\t");
            }
            System.out.println("");
        }
        
    }
    private static void handleWord(String word,Elevator ele,ElevatorRunStrategy run){
        
        if(word.equals("update")){
            run.run(ele);
        }else if(word.startsWith("upclick")){
            String[] cmds=word.split(" ");
            if(cmds.length!=2)return;
            int floor=Integer.parseInt(cmds[1]);
            ele.getUpClicks()[floor]=true;
        }else if(word.startsWith("downclick")){
            String[] cmds=word.split(" ");
            if(cmds.length!=2)return;
            int floor=Integer.parseInt(cmds[1]);
            ele.getDownClicks()[floor]=true;
        }else if(word.startsWith("destclick")){
            String[] cmds=word.split(" ");
            if(cmds.length!=2)return;
            int floor=Integer.parseInt(cmds[1]);
            ele.getDestClicks()[floor]=true;
        }else;
    }

}
