/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;

/**
 *
 * @author Bawb
 */
public class GameManager extends AbstractAppState {
    
    private SimpleApplication   app;
    private PlayerManager       playerManager;
    private InteractionManager  interactionManager;
    private SceneManager        sceneManager;
    
    public GameManager(SimpleApplication app) {
        this.app = app;
        createPlayerManager();
        createInteractionManager();
        createSceneManager();
    }
    
    private void createPlayerManager() {
        playerManager = new PlayerManager(app);
    }
    
    public PlayerManager getPlayerManager() {
        return playerManager;
    }
    
    private void createInteractionManager() {
        interactionManager = new InteractionManager(app, playerManager);
    }
    
    private void createSceneManager() {
        sceneManager = new SceneManager(app, playerManager);
    }
    
    @Override
    public void update(float tpf) {
        sceneManager.update(tpf);
        playerManager.update(tpf);
    }
    
}
