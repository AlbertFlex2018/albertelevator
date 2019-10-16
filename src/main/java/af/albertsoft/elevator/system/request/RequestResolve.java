/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.albertsoft.elevator.system.request;

import af.albertsoft.elevator.system.SystemData;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface RequestResolve {
    
    public void resolveRequest(SystemData data,RequestType type,Map<String,String> cmdMap);
}
