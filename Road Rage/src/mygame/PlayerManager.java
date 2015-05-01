/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import java.util.Random;

/**
 *
 * @author Bawb
 */
public class PlayerManager {
    
    private Player            player;
    private SimpleApplication app;
    private Long              lastShakeChange;
    
    public PlayerManager(SimpleApplication app) {
        this.app = app;
        createPlayer();
        placePlayer();
        lastShakeChange = System.currentTimeMillis();
    }
    
    private void placePlayer() {
        app.getRootNode().attachChild(player);
        player.setLocalTranslation(-5,2f,0);
    }
    
    private void createPlayer() {
        player = new Player(app);
    }
    
    public Player getPlayer() {
        return player;
    }
    
    private int randInt(int min, int max) {
        
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
        
    }  
    
    public void update(float tpf) {
        
        if(player.getTopDown()) {
            app.getCamera().setLocation(player.getWorldTranslation().clone().add(0,60,0));
            app.getCamera().lookAt(player.getWorldTranslation(), new Vector3f(0,0,-1));
        }
        
        else {
            app.getCamera().setLocation(player.getWorldTranslation().clone().add(-10,4,0));
            app.getCamera().lookAt(player.getWorldTranslation(), new Vector3f(0,1,0));
        }
        
        float turnSpeed = 10;
        
        if (player.getRight()) {
        
            if (player.getWorldTranslation().z < 6)
                player.move(0,0,turnSpeed*tpf);
            
        }
        
        if (player.getLeft()) {
        
            if (player.getWorldTranslation().z > -6)
                player.move(0,0,-turnSpeed*tpf);
            
        }
        
        if (System.currentTimeMillis()/1000 - lastShakeChange/1000 > 3) {
            
            int chance      = randInt(1,2);
            float shakeDir  = 1;
            lastShakeChange = System.currentTimeMillis();
            
            if (chance==1)
                shakeDir = -1;
            
            float shake     = randInt(0,1)*shakeDir; 
            player.setShake(shake);
            
        }   
        
        
    }
    
}
