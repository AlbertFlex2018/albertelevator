/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import elevator.admin.Admin;
import elevator.admin.AdminCenter;
import elevator.admin.AdminGrunt;
import elevator.control.Building;
import elevator.control.BuildingCenter;
import elevator.control.Elevator;
import elevator.control.XMLFileHelp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author Albert Flex
 */
public class FileTest{
    public static void main(String[] args) {
        Document doc=readXMLFileDocument("test/assets/test2.xml");
        if(doc==null){
            System.err.println("no xml document found.");
            System.exit(0);
        }
        Element root= doc.getRootElement();
        XMLFileHelp help=new XMLFileHelp();
        help.readSystemFromXML(root);
        showAdmins();
        showBuildings();
        Document doc2=getXMLWritableDocument("test/assets/test3.xml");
        Element root2=doc2.getRootElement();
        root2.setName("Configs");
        help.writeSystemToXML(root2);
        writeXMLFile("test/assets/test3.xml",doc2);
    }
    private static void showAdmins(){
        System.out.println("== print adminss ==");
        System.out.println("id \t password \t grunt");        
        AdminCenter center=AdminCenter.getInstance();
        List<Admin> reglist=center.getRegistedAdmins();
        System.out.println("- registed adminds -");
        reglist.forEach((admin)->{
            System.out.println(admin.getId()+"\t"+admin.getPassword()+"\t"+admin.getGrunt().toString());
        });
        System.out.println("- logined adminds -");        
        List<Admin> loglist=center.getLogedAdmins();
        loglist.forEach((admin)->{
            System.out.println(admin.getId()+"\t"+admin.getPassword()+"\t"+admin.getGrunt().toString());
        });
    }
    private static void showBuildings(){
        System.out.println("== print buildings ==");
        System.out.println("id \t name \t last \t top");
        List<Building> buildlist=BuildingCenter.getInstance().getBuildList();        
        buildlist.forEach((build)->{
            System.out.println(build.getId()+"\t"+build.getName()+"\t"+build.getLastLevel()+"\t"+build.getTopLevel());
            showElevatorsOnBuilding(build);
        });
    }
    private static void showElevatorsOnBuilding(Building build){
        List<Elevator> elist=build.getElevatorList();
        elist.forEach((elevator)->{
            System.out.println("+elevator:"+elevator.getId()+"\t"+elevator.getName()+"\t"+
                    elevator.getStartFloor()+"\t"+elevator.getEndFloor()+"\t"+elevator.getCurrentFloor());
        });
    }
    public static Document getXMLWritableDocument(String xmlpath){
        if(xmlpath==null){
            Document doc=DocumentHelper.createDocument();
            doc.addElement("root");
            return doc;
        }

        File file = new File(xmlpath);
        if(!file.exists()){
            Document doc=DocumentHelper.createDocument();
            doc.addElement("root");
            return doc;            
        }
        SAXReader reader =new SAXReader();
        Document doc;
        try{
            doc = reader.read(file);            
            System.out.println("root : "+doc.getRootElement().getName());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return doc;        
    }
    public static Document readXMLFileDocument(String xmlpath){
        if(xmlpath==null){
            System.err.println("read xml can not null!");
            return null;
        }

        File file = new File(xmlpath);
        if(!file.exists()){
            System.err.println("the xml file - "+xmlpath+" is not exist.");
            return null;
        }
        SAXReader reader =new SAXReader();
        Document doc;
        try{
            doc = reader.read(file);            
            System.out.println("root : "+doc.getRootElement().getName());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return doc;
    }
    public static void writeXMLFile(String xmlpath,Document doc){
        FileOutputStream out =null;
        try {
            out = new FileOutputStream(xmlpath);
            OutputFormat format=OutputFormat.createPrettyPrint();   //漂亮格式：有空格换行
            format.setEncoding("UTF-8");
            XMLWriter writer=new XMLWriter(out,format);
            //2.写出Document对象
            writer.write(doc);
            //3.关闭流
            writer.close();
        } catch (Exception ex) {
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(FileTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
