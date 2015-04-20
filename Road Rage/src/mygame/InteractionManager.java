/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;

/**
 *
 * @author Bawb
 */
public class InteractionManager implements ActionListener {
    
  public  InputManager   inputManager;
  private Player         player;
  public  boolean        left = false, right = false, click = false;
  
  public InteractionManager(Application app, PlayerManager playerManager) {
      
     this.inputManager = app.getInputManager();
     player            = playerManager.getPlayer(); 
     setUpKeys();
     
  }

    

  private void setUpKeys() {
  
      inputManager.addMapping("Left",  new KeyTrigger(KeyInput.KEY_A));
      inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
      inputManager.addMapping("Click", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
      inputManager.addListener(this, "Left");
      inputManager.addListener(this, "Right");
      inputManager.addListener(this, "Click");
  
  }
    
  @Override
  public void onAction(String binding, boolean isPressed, float tpf) {
  
      if (binding.equals("Click")) {
          click = isPressed;
      }
      
      else  if (binding.equals("Left")) {
          player.setLeft(isPressed);
      }
      
      else if (binding.equals("Right")) {
          player.setRight(isPressed);
      }
      
  }
    
}
