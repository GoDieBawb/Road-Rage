/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.scene.Node;

/**
 *
 * @author Bawb
 */
public class BuildingManager {
    
    private Node              buildingNode;
    private SimpleApplication app;
    
    public BuildingManager(SimpleApplication app) {
        createBuildingNode();
    }
    
    private void createBuildingNode() {
        buildingNode = new Node();
    }
    
    public Node getBuildingNode() {
        return buildingNode;
    }
    
    private void createBuilding() {
    
    }
    
    private void placeBuilding() {
    
    }
    
    public void update(float tpf) {
    
    }
    
}
