/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Vector2f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import java.util.Random;

/**
 *
 * @author Bawb
 */
public class BuildingManager {
    
    private Node              buildingNode;
    private Node              previousSet;
    private SimpleApplication app;
    private float             buildingLength;
    
    public BuildingManager(SimpleApplication app) {
        this.app = app;
        createBuildingNode();
        app.getRootNode().attachChild(buildingNode);
    }
    
    private void createBuildingNode() {
        buildingNode = new Node();
    }
    
    public Node getBuildingNode() {
        return buildingNode;
    }
    
    private void createBuilding() {
        buildingLength     = 50f;
        Box b              = new Box(buildingLength,5,10);
        Geometry geom      = new Geometry("Geometry", b);
        Geometry geom1     = new Geometry("Geometry", b);
        Node leftBuilding  = new Node();
        Node rightBuilding = new Node();
        Node buildingSet   = new Node();
        b.scaleTextureCoordinates(new Vector2f(1,1));
        geom.setMaterial(app.getAssetManager().loadMaterial("Materials/Building" + randInt(1,4) + ".j3m"));
        geom1.setMaterial(app.getAssetManager().loadMaterial("Materials/Building" + randInt(1,4) + ".j3m"));
        leftBuilding.attachChild(geom);
        rightBuilding.attachChild(geom1);
        rightBuilding.setLocalTranslation(0,6,16 - randInt(0,4));
        leftBuilding.setLocalTranslation(0,6,-16 + randInt(0,4));
        buildingSet.attachChild(leftBuilding);
        buildingSet.attachChild(rightBuilding);
        placeBuildingSet(buildingSet);
    }
    
    private void placeBuildingSet(Node buildingSet) {
        
        buildingNode.attachChild(buildingSet);
        buildingSet.setLocalTranslation(200,0,0);
        
        previousSet = buildingSet;
        
    }
    
    private int randInt(int min, int max) {
        
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
        
    }    
    
    public void update(float tpf) {
        
        Player p        = app.getStateManager().getState(GameManager.class)
                        .getPlayerManager().getPlayer();
        
        CollisionResults results = new CollisionResults();
        
        buildingNode.collideWith(p.getWorldBound(), results);
        
        if (results.size() > 0) {
            p.setIsDead(true);
            p.getGui().showMenu();
            buildingNode.detachAllChildren();
            previousSet = null;
            p.setScore(0);
            p.setMoveSpeed(50);
        }
        
        float moveSpeed = p.getMoveSpeed();
        
        if (buildingNode.getQuantity() < 15) {
            
            if (previousSet == null) {
                createBuilding();
            }
            
            else if (previousSet.getWorldTranslation().x < 200 - buildingLength*2) {
                createBuilding();
            }
            
        }
        
        for (int i = 0; i < buildingNode.getQuantity(); i++) {
            
            Node currentBuilding = (Node) buildingNode.getChild(i);
            
            currentBuilding.move(-moveSpeed*tpf,0,p.getShake()*tpf);
            
            if (currentBuilding.getWorldTranslation().x < -200) {
                currentBuilding.removeFromParent();
                //placeBuildingSet(currentBuilding);
                p.setScore(p.getScore()+1);
                p.setMoveSpeed(p.getMoveSpeed()+1f);
                
            } 
            
        }
        
    }
    
}
