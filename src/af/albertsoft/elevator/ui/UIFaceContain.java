/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.ui;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Node;
import javafx.stage.Popup;

/**
 *
 * @author Admin
 */
public class UIFaceContain {
    private static UIFaceContain _instance;
    public static UIFaceContain getInstance(){
        if(_instance==null){
            _instance=new UIFaceContain();
        }
        return _instance;
    }
    
    private final Map<String,Node> faceMap;
    public final Popup popup;
    private UIFaceContain() {
        faceMap=new HashMap<>();
        popup=new Popup();
    }    
    public Map<String, Node> getFaceMap() {
        return faceMap;
    }
    
    public Node getUI(String name){
        return faceMap.get(name);
    }
    public void addUI(String name,Node node){
        faceMap.put(name, node);
    }
    public void removeUI(String name){
        faceMap.remove(name);
    }
}
