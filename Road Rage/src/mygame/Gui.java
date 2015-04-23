/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapFont;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import tonegod.gui.controls.buttons.ButtonAdapter;
import tonegod.gui.controls.text.TextElement;
import tonegod.gui.core.Screen;
import tonegod.gui.effects.Effect;
import tonegod.gui.effects.Effect.EffectDirection;
import tonegod.gui.effects.Effect.EffectEvent;
import tonegod.gui.effects.Effect.EffectType;

/**
 *
 * @author Bawb
 */
public class Gui {
    
    private TextElement       scoreDisplay;
    private TextElement       titleText;
    private ButtonAdapter     startButton;
    private Screen            screen;
    private SimpleApplication app;
    private Player            player;
    private Long              startPressTime;
    private boolean           startPressed;
    
    public Gui(SimpleApplication app, Player player) {
        this.app    = app;
        this.player = player;
        createScreen();
        createTitleText();
        createScoreDisplay();
        createStartButton();
        
        startPressTime = System.currentTimeMillis();
        
    }
    
    private void createScreen() {
        
        screen = new Screen(app, "tonegod/gui/style/atlasdef/style_map.gui.xml");
        screen.setUseTextureAtlas(true,"tonegod/gui/style/atlasdef/atlas.png");
        screen.setUseMultiTouch(true);
        app.getInputManager().setSimulateMouse(true);
        app.getGuiNode().addControl(screen);
        
    }    
    
    private void createTitleText() {
        
        BitmapFont font = app.getAssetManager().loadFont("Interface/Impact.fnt");
        
        titleText = new TextElement(screen, "Title Text", new Vector2f(2,2), font) {
            
            @Override
            public void onUpdate(float tpf){}
            
            @Override
            public void onEffectStop(){
            }
            
            @Override
            public void onEffectStart(){
            }
            
        };
        
        Effect showEffect = new Effect(EffectType.SlideIn, EffectEvent.Show, 2.2f);
        Effect hideEffect = new Effect(EffectType.SlideOut, EffectEvent.Hide, 2.2f);
        
        showEffect.setEffectDirection(EffectDirection.Left);
        hideEffect.setEffectDirection(EffectDirection.Right);
        
        titleText.setFontSize(font.getPreferredSize()*1.5f);
        titleText.setFontColor(ColorRGBA.Red);
        titleText.addEffect(hideEffect);
        titleText.addEffect(showEffect);
        titleText.setTextAlign(BitmapFont.Align.Center);
        screen.addElement(titleText);
        titleText.setText("Road Rage"); 
        titleText.setDimensions(screen.getWidth()/2, screen.getWidth()/4);
        titleText.setPosition(screen.getWidth()/2f - titleText.getWidth()/5, screen.getHeight() / 2f + titleText.getHeight()/2);
        
    }
    
    private void createScoreDisplay() {
    
        BitmapFont font = app.getAssetManager().loadFont("Interface/Impact.fnt");
        
        scoreDisplay = new TextElement(screen, "Score Display", new Vector2f(2,2), font) {
            
            @Override
            public void onUpdate(float tpf){}
            
            @Override
            public void onEffectStop(){
            }
            
            @Override
            public void onEffectStart(){
            }
            
        };
        
        Effect showEffect = new Effect(EffectType.SlideIn, EffectEvent.Show, 2.2f);
        Effect hideEffect = new Effect(EffectType.SlideOut, EffectEvent.Hide, 2.2f);        
       
        showEffect.setEffectDirection(EffectDirection.Left);
        hideEffect.setEffectDirection(EffectDirection.Right);
        
        scoreDisplay.addEffect(showEffect);
        scoreDisplay.addEffect(hideEffect);        
        
        scoreDisplay.setDimensions(screen.getWidth()/5, screen.getHeight()/10);
        scoreDisplay.setPosition(screen.getWidth() - scoreDisplay.getWidth() *1.5f, 0 + scoreDisplay.getHeight()/2);
        
        screen.addElement(scoreDisplay);
        
        scoreDisplay.setText("Score: " + 0);
        scoreDisplay.hide();
        
    }
    
    private void createStartButton() {
        
        BitmapFont font = app.getAssetManager().loadFont("Interface/Impact.fnt");
        
        startButton = new ButtonAdapter(screen, "Start Button", new Vector2f(2,2)) {
        
            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
        
                if (startPressed) {
                    return;
                }
                
                hideWithEffect();
                titleText.hideWithEffect();
                scoreDisplay.showWithEffect();
                startPressed   = true;
                startPressTime = System.currentTimeMillis();
        
            }
        
        };
        
        Material m        = app.getAssetManager().loadMaterial("Materials/Transparent.j3m");
        Effect showEffect = new Effect(EffectType.SlideIn, EffectEvent.Show, 2.2f);
        Effect hideEffect = new Effect(EffectType.SlideOut, EffectEvent.Hide, 2.2f);        
       
        showEffect.setEffectDirection(EffectDirection.Left);
        hideEffect.setEffectDirection(EffectDirection.Right);
        
        startButton.setFont("Interface/Impact.fnt");
        startButton.setFontSize(font.getPreferredSize());
        
        startButton.addEffect(showEffect);
        startButton.addEffect(hideEffect); 
        
        startButton.setDimensions(screen.getWidth()/5f, screen.getHeight()/10);
        startButton.setPosition(screen.getWidth()/2 - startButton.getWidth()/2, screen.getHeight()/2 -  startButton.getHeight()/2);
        
        screen.addElement(startButton);
        startButton.setMaterial(m);
        startButton.setText("Start");
        
    }
    
    private void updateScoreDisplay() {
        scoreDisplay.setText("Score: " + player.getScore());
    }
    
    public void showMenu() {
        scoreDisplay.hideWithEffect();
        titleText.showWithEffect();
        startButton.showWithEffect();
    }
    
    public void update(float tpf) {
    
        if (!player.isDead()) {
            updateScoreDisplay();
        }
        
        if (startPressed)
            
            if(System.currentTimeMillis()/100 - startPressTime/100 > 22) {
                startPressed = false;
                player.setIsDead(false);
                startButton.setText("Restart");
                titleText.setText("Game Over");
            }
        
    }
    
}
