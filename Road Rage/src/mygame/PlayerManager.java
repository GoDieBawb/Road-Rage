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
    
    public PlayerManager(SimpleApplication app) {
        createPlayer(app);
        placePlayer(app);
    }
    
    private void placePlayer(SimpleApplication app) {
        app.getRootNode().attachChild(player);
        player.setLocalTranslation(-5,2f,0);
    }
    
    private void createPlayer(SimpleApplication app) {
        player = new Player(app);
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void update(float tpf) {
        
        if (player.getRight()) {
        
            player.move(0,0,5f*tpf);
            
        }
        
        if (player.getLeft()) {
        
            player.move(0,0,-5f*tpf);
            
        }
        
    }
    
}
