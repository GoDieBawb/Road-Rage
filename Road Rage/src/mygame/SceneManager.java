/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Bawb
 */
public class SceneManager {
    
    private SimpleApplication app;
    private AppStateManager   stateManager;
    private Node              ground1;
    private Node              ground2;
    private Player            player;
    private float             roadLength;
    private float             moveSpeed;
    
    public SceneManager(Application app, PlayerManager playerManager) {
        this.app     = (SimpleApplication) app;
        stateManager = app.getStateManager();
        player       = playerManager.getPlayer();
        app.getViewPort().setBackgroundColor(ColorRGBA.DarkGray);
        createGround();
    }
    
    private void createGround() {
    
        roadLength       = 100;
        Material   mat   = app.getAssetManager().loadMaterial("Materials/Road.j3m");
        Box box          = new Box(roadLength,2,6);
        Geometry cube1   = new Geometry("Geometry", box);
        Geometry cube2   = new Geometry("Geometry", box);
                 ground1 = new Node("Ground One");
                 ground2 = new Node("Ground Two");
        
        //mat1.setColor("Color", ColorRGBA.Blue);         
        
        box.scaleTextureCoordinates(new Vector2f(1,10));
                 
        ground1.attachChild(cube1);
        ground2.attachChild(cube2);
        
        ground1.setMaterial(mat);
        ground2.setMaterial(mat);
        
        app.getRootNode().attachChild(ground1);
        app.getRootNode().attachChild(ground2);
        
        ground1.move(roadLength*2,0,0);
        
    }
    
    public void update(float tpf) {
        
        int dirMult = 1;
        moveSpeed   = 50;
        
        ground1.move(-moveSpeed*tpf*dirMult,0,0);
        ground2.move(-moveSpeed*tpf*dirMult,0,0);
        
        if (ground1.getWorldTranslation().x < -roadLength*2)
            ground1.setLocalTranslation(roadLength*2,0,0);
        
        if (ground2.getWorldTranslation().x < -roadLength*2)
            ground2.setLocalTranslation(roadLength*2,0,0);
        
        if (ground1.getWorldTranslation().x > roadLength*2)
            ground1.setLocalTranslation(-roadLength*2,0,0);
        
        if (ground2.getWorldTranslation().x > roadLength*2)
            ground2.setLocalTranslation(-roadLength*2,0,0);
        
        if (ground2.getWorldTranslation().distance(ground1.getWorldTranslation()) > roadLength*2 || ground2.getWorldTranslation().distance(ground1.getWorldTranslation()) < roadLength*2) {
            
            if (ground2.getWorldTranslation().x > ground1.getWorldTranslation().x) {
                ground2.setLocalTranslation(ground1.getWorldTranslation().x + roadLength*2,0,0);
            }
            
            else {
                ground1.setLocalTranslation(ground2.getWorldTranslation().x + roadLength*2,0,0);
            }
            
        }
        
    }
    
}

