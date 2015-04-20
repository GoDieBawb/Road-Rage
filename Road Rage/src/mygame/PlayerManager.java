/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;

/**
 *
 * @author Bawb
 */
public class PlayerManager {
    
    private Player player;
    private SimpleApplication app;
    
    public PlayerManager(SimpleApplication app) {
        this.app = app;
        createPlayer();
        placePlayer();
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
    
    public void update(float tpf) {
        
        app.getCamera().setLocation(player.getWorldTranslation().clone().add(-10,4,0));
        float turnSpeed = 10;
        
        if (player.getRight()) {
        
            player.move(0,0,turnSpeed*tpf);
            
        }
        
        if (player.getLeft()) {
        
            player.move(0,0,-turnSpeed*tpf);
            
        }
        
    }
    
}
