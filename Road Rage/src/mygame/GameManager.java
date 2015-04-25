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
    private YamlManager         yamlManager;
    
    public GameManager(SimpleApplication app) {
        this.app = app;
        createPlayerManager();
        createInteractionManager();
        createSceneManager();
        createYamlManager();
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
    
    private void createYamlManager() {
        yamlManager = new YamlManager();
    }
    
    public YamlManager getYamlManager() {
        return yamlManager;
    }
    
    @Override
    public void update(float tpf) {
        
        playerManager.getPlayer().getGui().update(tpf);
        
        if (playerManager.getPlayer().isDead()) {
            return;
        }
        
        sceneManager.update(tpf);
        playerManager.update(tpf);
        
    }
    
}
