/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.scene.Node;

/**
 *
 * @author Bawb
 */
public class Player extends Node {
    
    private boolean right, left;
    private Node    model;
    
    public Player(SimpleApplication app) {
        setModel(app);
    }
    
    public void setRight(boolean newVal) {
        right = newVal;
    }
    
    private void setModel(SimpleApplication app) {
        model      = (Node) app.getAssetManager().loadModel("Models/Ferrari.j3o");
        Material m = app.getAssetManager().loadMaterial("Materials/Car.j3m");
        model.setMaterial(m);
        attachChild(model);
    }
    
    public void setLeft(boolean newVal) {
        left = newVal;
    }
    
    public boolean getRight() {
        return right;
    }
    
    public boolean getLeft() {
        return left;
    }
    
    public void spinWheels() {
    
    }
    
}
