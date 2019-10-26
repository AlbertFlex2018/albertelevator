import af.albertsoft.elevator.admin.Admin;
import af.albertsoft.elevator.admin.AdminSystem;
import af.albertsoft.elevator.system.Building;
import af.albertsoft.elevator.system.Elevator;
import af.albertsoft.elevator.system.ElevatorState;
import af.albertsoft.elevator.system.SystemData;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class SystemPrototypeTest {
    
    public static void main(String[] args){
        SystemData data = getData("systemconfig/system1.sys.xml");
        data.setSystemPath("systemconfig/system2.sys.xml");
        writeData(data);
        Building building = data.getBuildingMap().get(1);
        building.setBuildingName("Changed BuildingName!!!2");
        modifyData(data);
    }   
    public static void modifyData(SystemData data){
        //写入并且更新系统数据到文件中，如果楼层或者电梯或者管理员不存在，则创建
        File file = new File(data.getSystemPath());
        SAXReader reader=new SAXReader();
        try
        {
            Document doc = reader.read(file);
            Element root=doc.getRootElement();
            //修改楼层
            Iterator<Building> builditer=data.getBuildingMap().values().iterator();
            Element buildingRoot=root.element("buildingList");
            if(buildingRoot==null){
                System.out.println("BuildingList is not found!!");
                return;
            }
            while(builditer.hasNext())
            {
                Building building  = builditer.next();
                Element buildele = getBuildingEle(buildingRoot,building.buildingId);
                if(buildele==null)
                {
                    buildele = buildingRoot.addElement("building");
                    Element namee=buildele.addElement("name");
                    namee.setText(building.getBuildingName());
                    Element id = buildele.addElement("id");
                    id.setText(Integer.toString(building.buildingId));
                    Element start=buildele.addElement("start");
                    start.setText(Integer.toString(building.getStartFloor()));
                    Element end=buildele.addElement("end");
                    end.setText(Integer.toString(building.getEndFloor()));                    
                }                
                else
                {                       
                    Element namee=buildele.element("name");
                    if(namee.getText().equals(building.getBuildingName())==false)
                    {
                        namee.setText(building.getBuildingName());;
                    }
                    Element start=buildele.element("start");
                    if(Integer.parseInt(start.getText())!=building.getStartFloor())
                    {
                        start.setText(Integer.toString(building.getStartFloor()));
                    }
                    Element end=buildele.element("end");
                    if(Integer.parseInt(end.getText())!=building.getEndFloor())
                    {
                        end.setText(Integer.toString(building.getEndFloor()));                                                                
                    }
                }                                
            }
            //删除多余的楼层
            Iterator<Element> buildeleiter=buildingRoot.elementIterator();
            Map<Integer,Building> buildMap=data.getBuildingMap();
            List<Element> needremove = new ArrayList<>();
            while(buildeleiter.hasNext())
            {
                Element buildele2 = buildeleiter.next();
                int id = Integer.parseInt(buildele2.elementText("id"));
                if(buildMap.containsKey(id)==false)
                {
                    needremove.add(buildele2);
                }
            }
            Iterator<Element> needremoveiter=needremove.iterator();
            while(needremoveiter.hasNext())
            {
                Element needrem = needremoveiter.next();
                buildingRoot.remove(needrem);
            }
            
            
            //修改电梯
            Element elevatorRoot = root.element("elevatorList");
            Iterator<Elevator> elevatoriter=data.getElevatorMap().values().iterator();
            while(elevatoriter.hasNext())
            {
                Elevator elevatorele=elevatoriter.next();
                Element eleEle = getElevatorEle(elevatorRoot,elevatorele.globalId);
                if(eleEle==null)
                {                    
                    eleEle = elevatorRoot.addElement("elevator");
                    Element namee2 = eleEle.addElement("name");
                    namee2.setText(elevatorele.getElevatorName());
                    Element buildingid = eleEle.addElement("buildingid");
                    buildingid.setText(Integer.toString(elevatorele.buildingId));
                    Element eleid = eleEle.addElement("elevatorid");
                    eleid.setText(Integer.toString(elevatorele.elevatorId));
                    Element globalid=eleEle.addElement("globalid");
                    globalid.setText(Integer.toString(elevatorele.globalId));
                    Element start=eleEle.addElement("start");
                    start.setText(Integer.toString(elevatorele.getStartFloor()));
                    Element end=eleEle.addElement("end");
                    end.setText(Integer.toString(elevatorele.getEndFloor()));                    
                }
                else
                {
                    Element namee2 = eleEle.element("name");
                    if(namee2.getText().equals(elevatorele.getElevatorName())==false)
                    {
                        namee2.setText(elevatorele.getElevatorName());                    
                    }
                    Element buildingid = eleEle.element("buildingid");
                    if(Integer.parseInt(buildingid.getText())!=elevatorele.buildingId)
                    {
                        buildingid.setText(Integer.toString(elevatorele.buildingId));                        
                    }
                    Element eleid = eleEle.element("elevatorid");
                    if(Integer.parseInt(eleid.getText())!=elevatorele.elevatorId)
                    {
                        buildingid.setText(Integer.toString(elevatorele.elevatorId));                        
                    }                                                            
                    Element start=eleEle.element("start");
                    if(Integer.parseInt(start.getText())!=elevatorele.getStartFloor())
                    {
                        start.setText(Integer.toString(elevatorele.getStartFloor()));
                    }                                                                            
                    Element end=eleEle.element("end");
                    if(Integer.parseInt(end.getText())!=elevatorele.getEndFloor())
                    {
                        end.setText(Integer.toString(elevatorele.getEndFloor()));                                        
                    }                                                                            
                }
            }                                        

            //删除多余电梯
            Iterator<Element> elevatoreleiter=elevatorRoot.elementIterator();
            Map<Integer,Elevator> elevatorMap=data.getElevatorMap();
            List<Element> needremove2 = new ArrayList<>();
            while(elevatoreleiter.hasNext())
            {
                Element elevatorele2 = elevatoreleiter.next();
                int id = Integer.parseInt(elevatorele2.elementText("globalid"));
                if(elevatorMap.containsKey(id)==false)
                {
                    needremove2.add(elevatorele2);
                }
            }
            Iterator<Element> needremoveiter2=needremove2.iterator();
            while(needremoveiter2.hasNext())
            {
                Element needrem = needremoveiter2.next();
                elevatorRoot.remove(needrem);
            }
            
            //修改管理员
            Element adminRoot=root.addElement("adminList");
            Iterator<Admin> adminiter=data.getAdminsystem().getIdAdminMap().values().iterator();
            while(adminiter.hasNext())
            {
                Admin admin = adminiter.next();
                Element adminele = getAdmin(adminRoot,admin.id);
                if(adminele==null)
                {
                    adminele = adminRoot.addElement("admin");
                    Element adminid = adminele.addElement("id");
                    adminid.setText(Integer.toString(admin.id));
                    Element username=adminele.addElement("username");
                    username.setText(admin.getUserName());
                    Element password=adminele.addElement("password");
                    password.setText(admin.getPassword());                    
                }
                else
                {                                                            
                    Element username=adminele.element("username");
                    if(username.getText().equals(admin.getUserName())==false)
                    {
                        username.setText(admin.getUserName());                        
                    }                    
                    Element password=adminele.element("password");
                    if(password.getText().equals(admin.getPassword())==false)
                    {
                        password.setText(admin.getPassword());                                                                
                    }
                }
            }
            
            //删除多余管理员
            Iterator<Element> admineleiter=adminRoot.elementIterator();
            Map<Integer,Admin> adminMap=data.getAdminsystem().getIdAdminMap();
            List<Element> needremove3 = new ArrayList<>();
            while(admineleiter.hasNext())
            {
                Element adminele2 = admineleiter.next();
                int id = Integer.parseInt(adminele2.elementText("id"));
                if(adminMap.containsKey(id)==false)
                {
                    needremove3.add(adminele2);
                }
            }
            Iterator<Element> needremoveiter3=needremove3.iterator();
            while(needremoveiter3.hasNext())
            {
                Element needrem = needremoveiter3.next();
                adminRoot.remove(needrem);
            }

            XMLWriter writer = null;
            
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
                writer=new XMLWriter(new FileWriter(file),format);
                writer.write(doc);
                writer.close();            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //最后开始验证，如果文件中的楼层或电梯或管理员在系统中不存在，则予以删除
    }
    private static Element getAdmin(Element adminRoot,int id)
    {
        Iterator<Element> adminiter=adminRoot.elementIterator();
        while(adminiter.hasNext())
        {
            Element admin = adminiter.next();
            if(Integer.parseInt(admin.elementText("id"))==id)
                return admin;
        }
        
        return null;
    }
    private static Element getElevatorEle(Element elevatorRoot,int globalid)
    {
        Iterator<Element> builditer=elevatorRoot.elementIterator();
        while(builditer.hasNext())
        {
            Element ele = builditer.next();
            if(Integer.parseInt(ele.elementText("globalid"))==globalid){
                return ele;
            }
        }
        
        return null;        
    }
    private static Element getBuildingEle(Element buildRoot,int id)
    {
        Iterator<Element> builditer=buildRoot.elementIterator();
        while(builditer.hasNext())
        {
            Element ele = builditer.next();
            if(Integer.parseInt(ele.elementText("id"))==id){
                return ele;
            }
        }
        
        return null;
    }
    public static void writeData(SystemData data){
        String filepath = data.getSystemPath();
        XMLWriter writer = null;
        try{
            SAXReader reader = new SAXReader();
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
                Document document = DocumentHelper.createDocument();
                Element root = document.addElement("System");
                document.setRootElement(root);
                Element name=root.addElement("name");
                name.setText(data.getSystemName());
                Element buildinglist=root.addElement("buildingList");
                
                Iterator<Building> builditer = data.getBuildingMap().values().iterator();
                while(builditer.hasNext())
                {
                    Building building = builditer.next();
                    Element buildv = buildinglist.addElement("building");
                    Element namee=buildv.addElement("name");
                    namee.setText(building.getBuildingName());
                    Element id = buildv.addElement("id");
                    id.setText(Integer.toString(building.buildingId));
                    Element start=buildv.addElement("start");
                    start.setText(Integer.toString(building.getStartFloor()));
                    Element end=buildv.addElement("end");
                    end.setText(Integer.toString(building.getEndFloor()));                    
                }
                
                Element elevatorlist=root.addElement("elevatorList");
                Iterator<Elevator> elevatoriter=data.getElevatorMap().values().iterator();
                while(elevatoriter.hasNext()){
                    Elevator elevator = elevatoriter.next();
                    Element elevatorv = elevatorlist.addElement("elevator");
                    Element namee2 = elevatorv.addElement("name");
                    namee2.setText(elevator.getElevatorName());
                    Element buildingid = elevatorv.addElement("buildingid");
                    buildingid.setText(Integer.toString(elevator.buildingId));
                    Element eleid = elevatorv.addElement("elevatorid");
                    eleid.setText(Integer.toString(elevator.elevatorId));
                    Element globalid=elevatorv.addElement("globalid");
                    globalid.setText(Integer.toString(elevator.globalId));
                    Element start=elevatorv.addElement("start");
                    start.setText(Integer.toString(elevator.getStartFloor()));
                    Element end=elevatorv.addElement("end");
                    end.setText(Integer.toString(elevator.getEndFloor()));
                }

                Element adminlist=root.addElement("adminList");
                Iterator<Admin> adminiter=data.getAdminsystem().getIdAdminMap().values().iterator();
                while(adminiter.hasNext()){
                    Admin admin = adminiter.next();
                    Element adminv = adminlist.addElement("admin");
                    Element adminid = adminv.addElement("id");
                    adminid.setText(Integer.toString(admin.id));
                    Element username=adminv.addElement("username");
                    username.setText(admin.getUserName());
                    Element password=adminv.addElement("password");
                    password.setText(admin.getPassword());
                }
                writer=new XMLWriter(new FileWriter(filepath),format);
                writer.write(document);
                writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static SystemData getData(String filepath){

        File file = new File(filepath);
        String name;
        Map<Integer,Building> buildMap=new HashMap<>();        
        Map<Integer,Elevator> elevatorMap=new HashMap<>();       
        Map<Integer,Admin> adminMap=new HashMap<>();
        SystemData data=null;
        SAXReader reader = new SAXReader();
        try {

            Document document = reader.read(file);
            
            //<system>
            Element systemRoot = document.getRootElement();
            //<name>
            Element namee = systemRoot.element("name");
            if(namee==null){
                System.out.println("Name for System is not found!!");
                name="defaultSystem";
            }
            else name=namee.getText();
            
            //<buildingList>
            Element buildlist=systemRoot.element("buildingList");
            Iterator<Element> iter = buildlist.elementIterator();
            while(iter.hasNext()){
                Element build = iter.next();
                String nname = build.elementText("name");
                int id = Integer.parseInt(build.elementText("id"));
                int start = Integer.parseInt(build.elementText("start"));
                int end = Integer.parseInt(build.elementText("end"));
                Building building = new Building(nname,id,start,end,new HashMap<>());
                buildMap.put(id,building);
            }
            
            //<elevatorList>
            Element elevatorList =systemRoot.element("elevatorList");
            iter=elevatorList.elementIterator();
            while(iter.hasNext()){
                Element build = iter.next();
                String nname = build.elementText("name");
                int buildingid = Integer.parseInt(build.elementText("buildingid"));
                int elevatorid = Integer.parseInt(build.elementText("elevatorid"));
                int globalid = Integer.parseInt(build.elementText("globalid"));
                int start = Integer.parseInt(build.elementText("start"));
                int end = Integer.parseInt(build.elementText("end"));
                //(String elevatorName, int elevatorId, int buildingId,int globalId, int startFloor, int endFloor, 
                //int currentFloor, boolean[] upClicks, boolean[] downClicks, boolean[] DestClicks,ElevatorState runState)
                Elevator elevator= new Elevator(nname,elevatorid,buildingid,globalid,start,end,
                    start,new boolean[end+1],new boolean[end+1],new boolean[end+1],ElevatorState.UNSETUP);
                Building building=buildMap.get(buildingid);
                if(building==null){
                    System.out.println("Building is not found! buildingId for elevator is wrong.");
                    continue;
                }
                building.getElevatorIdMap().put(elevatorid,globalid);                
                elevatorMap.put(globalid, elevator);
            }
            
            //<adminList>
            Element adminList=systemRoot.element("adminList");
            iter=adminList.elementIterator();
            while(iter.hasNext()){
                Element admin = iter.next();
                int id = Integer.parseInt(admin.elementText("id"));
                String username=admin.elementText("username");
                String password=admin.elementText("password");
                Admin aadmin = new Admin(id,username,password);
                adminMap.put(id, aadmin);
            }
            
            data = new SystemData(name,buildMap,elevatorMap,new AdminSystem(adminMap));            
            data.setSystemPath(filepath);
        } catch (DocumentException e) {
            e.printStackTrace();
        }                
        return data;        
    }    
    public static void printSystem(SystemData data){
        System.out.println("---OUTPUT ELEVATORSYSTEM---");
        System.out.println("SYSTEM FOR "+data.getSystemName());
        System.out.println("BUILDING LIST...");
        Iterator<Building> buildingiter=data.getBuildingMap().values().iterator();
        while(buildingiter.hasNext()){
            Building building = buildingiter.next();
            printBuilding(building);
        }

        System.out.println("ELEVATOR LIST...");
        Iterator<Elevator> elevatoriter = data.getElevatorMap().values().iterator();
        while(elevatoriter.hasNext()){
            Elevator elevator=elevatoriter.next();
            printElevator(elevator);
        }

        System.out.println("ADMIN LIST...");
        Iterator<Admin> adminiter=data.getAdminsystem().getIdAdminMap().values().iterator();
        while(adminiter.hasNext()){
            Admin admin = adminiter.next();
            printAdmin(admin);
        }
        System.out.println("---END ELEVATORSYSTEM---");        
    }
    private static void printBuilding(Building building){
        System.out.println("building - "+building.getBuildingName());
        System.out.println("id:"+building.buildingId);
        System.out.println("start:"+building.getStartFloor());
        System.out.println("end:"+building.getEndFloor());;
        System.out.println("");
    }
    private static void printElevator(Elevator elevator){
        System.out.println("elevator - "+elevator.getElevatorName());
        System.out.println("buildingid:"+elevator.buildingId);
        System.out.println("elevatorid:"+elevator.elevatorId);
        System.out.println("globalid:"+elevator.globalId);
        System.out.println("start:"+elevator.getStartFloor());
        System.out.println("end:"+elevator.getEndFloor());;
        System.out.println("");
    }
    private static void printAdmin(Admin admin){
        System.out.println("admin - "+admin.getUserName());
        System.out.println("id:"+admin.id);
        System.out.println("password:"+admin.getPassword());
        System.out.println("");        
    }
}
