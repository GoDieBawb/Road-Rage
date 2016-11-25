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
    private TextElement       titleText, arcadeHighScoreText, classicHighScoreText;
    private ButtonAdapter     startButton, arcadeButton, classicButton, highScoreButton, backButton;
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
        createClassicButton();
        createArcadeButton();
        createHighScoreButton();
        createArcadeHighScoreText();
        createClassicHighScoreText();
        createBackButton();
        startPressTime = System.currentTimeMillis();
        
    }
    
    private void createScreen() {
        
        screen = new Screen(app, "tonegod/gui/style/atlasdef/style_map.gui.xml");
        screen.setUseTextureAtlas(true,"tonegod/gui/style/atlasdef/atlas.png");
        //screen.setUseMultiTouch(true);
        app.getInputManager().setSimulateMouse(true);
        app.getGuiNode().addControl(screen);
        
    }    
    
    public Screen getScreen() {
        return screen;
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
        titleText.setText("Stuntman"); 
        titleText.setDimensions(screen.getWidth()/2, screen.getWidth()/4);
        titleText.setPosition(screen.getWidth()/2f - titleText.getWidth()/5, screen.getHeight() / 2f + titleText.getHeight()/2);
        
    }
    
    private void createArcadeHighScoreText() {
        
        BitmapFont font = app.getAssetManager().loadFont("Interface/Impact.fnt");
        
        arcadeHighScoreText = new TextElement(screen, "Arcade HighScore Text", new Vector2f(2,2), font) {
            
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
        
        arcadeHighScoreText.setFontSize(font.getPreferredSize());
        //arcadeHighScoreText.setFontColor(ColorRGBA.Red);
        arcadeHighScoreText.addEffect(hideEffect);
        arcadeHighScoreText.addEffect(showEffect);
        arcadeHighScoreText.setTextAlign(BitmapFont.Align.Center);
        screen.addElement(arcadeHighScoreText);
        arcadeHighScoreText.setText("Arcade HighScore: " + player.getScoreManager().getArcadeHighScore());
        arcadeHighScoreText.setDimensions(screen.getWidth()/2, screen.getWidth()/4);
        arcadeHighScoreText.setPosition(screen.getWidth()/2f - arcadeHighScoreText.getWidth()/5, screen.getHeight() / 2f);
        arcadeHighScoreText.hide();
        
    }    
    
    private void createClassicHighScoreText() {
        
        BitmapFont font = app.getAssetManager().loadFont("Interface/Impact.fnt");
        
        classicHighScoreText = new TextElement(screen, "Classic HighScore Text", new Vector2f(2,2), font) {
            
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
        
        classicHighScoreText.setFontSize(font.getPreferredSize());
        //classicHighScoreText.setFontColor(ColorRGBA.Red);
        classicHighScoreText.addEffect(hideEffect);
        classicHighScoreText.addEffect(showEffect);
        classicHighScoreText.setTextAlign(BitmapFont.Align.Center);
        screen.addElement(classicHighScoreText);
        classicHighScoreText.setText("Classic HighScore: " + player.getScoreManager().getClassicHighScore());
        classicHighScoreText.setDimensions(screen.getWidth()/2, screen.getWidth()/4);
        classicHighScoreText.setPosition(screen.getWidth()/2f - classicHighScoreText.getWidth()/5, screen.getHeight() / 2f - classicHighScoreText.getHeight()/2);
        classicHighScoreText.hide();
        
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
        
                if (arcadeButton.getIsVisible()) {
                    return;
                } 
                
                hideWithEffect();
                backButton.showWithEffect();
                highScoreButton.hideWithEffect();
                arcadeButton.showWithEffect();
                classicButton.showWithEffect();
        
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
        
        startButton.setDimensions(screen.getWidth()/3f, screen.getHeight()/10);
        startButton.setPosition(screen.getWidth()/2 - startButton.getWidth()/2, screen.getHeight()/2 -  startButton.getHeight()/2);
        
        screen.addElement(startButton);
        startButton.setMaterial(m);
        startButton.setText("Start");
        
    }
    
    private void createHighScoreButton() {
    
        BitmapFont font = app.getAssetManager().loadFont("Interface/Impact.fnt");
        
        highScoreButton = new ButtonAdapter(screen, "HighScore Button", new Vector2f(2,2)) {
        
            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
        
                if (arcadeButton.getIsVisible()) {
                    return;
                } 
                
                hideWithEffect();
                backButton.showWithEffect();
                arcadeHighScoreText.showWithEffect();
                classicHighScoreText.showWithEffect();
                startButton.hideWithEffect();
        
            }
        
        };
        
        Material m        = app.getAssetManager().loadMaterial("Materials/Transparent.j3m");
        Effect showEffect = new Effect(EffectType.SlideIn, EffectEvent.Show, 2.2f);
        Effect hideEffect = new Effect(EffectType.SlideOut, EffectEvent.Hide, 2.2f);        
       
        showEffect.setEffectDirection(EffectDirection.Left);
        hideEffect.setEffectDirection(EffectDirection.Right);
        
        highScoreButton.setFont("Interface/Impact.fnt");
        highScoreButton.setFontSize(font.getPreferredSize());
        
        highScoreButton.addEffect(showEffect);
        highScoreButton.addEffect(hideEffect); 
        
        highScoreButton.setDimensions(screen.getWidth()/2.5f, screen.getHeight()/10);
        highScoreButton.setPosition(screen.getWidth()/2 - highScoreButton.getWidth()/2, screen.getHeight()/2 + highScoreButton.getHeight());
        
        screen.addElement(highScoreButton);
        highScoreButton.setMaterial(m);
        highScoreButton.setText("HighScores");
    
    }
    
    private void createBackButton() {
    
        BitmapFont font = app.getAssetManager().loadFont("Interface/Impact.fnt");
        
        backButton = new ButtonAdapter(screen, "Back Button", new Vector2f(2,2)) {
        
            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
                
                hideWithEffect();
                arcadeHighScoreText.hideWithEffect();
                classicHighScoreText.hideWithEffect();
                highScoreButton.showWithEffect();
                startButton.showWithEffect();
                arcadeButton.hideWithEffect();
                classicButton.hideWithEffect();
        
            }
        
        };
        
        Material m        = app.getAssetManager().loadMaterial("Materials/Transparent.j3m");
        Effect showEffect = new Effect(EffectType.SlideIn, EffectEvent.Show, 2.2f);
        Effect hideEffect = new Effect(EffectType.SlideOut, EffectEvent.Hide, 2.2f);        
       
        showEffect.setEffectDirection(EffectDirection.Left);
        hideEffect.setEffectDirection(EffectDirection.Right);
        
        backButton.setFont("Interface/Impact.fnt");
        backButton.setFontSize(font.getPreferredSize());
        
        backButton.addEffect(showEffect);
        backButton.addEffect(hideEffect); 
        
        backButton.setDimensions(screen.getWidth()/2.5f, screen.getHeight()/10);
        backButton.setPosition(0 - backButton.getWidth()/5, 0);
        
        screen.addElement(backButton);
        backButton.setMaterial(m);
        backButton.setText("Back");
        backButton.hide();
    
    }    
    
    private void createArcadeButton() {
        
        BitmapFont font = app.getAssetManager().loadFont("Interface/Impact.fnt");
        
        arcadeButton = new ButtonAdapter(screen, "Arcade Button", new Vector2f(2,2)) {
        
            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
        
                if (startPressed) {
                    return;
                }
                
                backButton.hideWithEffect();
                player.setTopDown(true);
                hideWithEffect();
                classicButton.hideWithEffect();
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
        
        arcadeButton.setFont("Interface/Impact.fnt");
        arcadeButton.setFontSize(font.getPreferredSize());
        
        arcadeButton.addEffect(showEffect);
        arcadeButton.addEffect(hideEffect); 
        
        arcadeButton.setDimensions(screen.getWidth()/3f, screen.getHeight()/10);
        arcadeButton.setPosition(screen.getWidth()/2 - arcadeButton.getWidth()/2, screen.getHeight()/2 -  arcadeButton.getHeight()/2);
        
        screen.addElement(arcadeButton);
        arcadeButton.setMaterial(m);
        arcadeButton.setText("Arcade");
        arcadeButton.hide();
        
    }    
    
    private void createClassicButton() {
        
        BitmapFont font = app.getAssetManager().loadFont("Interface/Impact.fnt");
        
        classicButton   = new ButtonAdapter(screen, "Classic Button", new Vector2f(2,2)) {
        
            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
        
                if (startPressed) {
                    return;
                }
                
                player.setTopDown(false);
                hideWithEffect();
                backButton.hideWithEffect();
                arcadeButton.hideWithEffect();
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
        
        classicButton.setFont("Interface/Impact.fnt");
        classicButton.setFontSize(font.getPreferredSize());
        
        classicButton.addEffect(showEffect);
        classicButton.addEffect(hideEffect); 
        
        classicButton.setDimensions(screen.getWidth()/3f, screen.getHeight()/10);
        classicButton.setPosition(screen.getWidth()/2 - classicButton.getWidth()/2, screen.getHeight()/2 + classicButton.getHeight());
        
        screen.addElement(classicButton);
        classicButton.setMaterial(m);
        classicButton.setText("Classic");
        classicButton.hide();
        
    }        
    
    private void updateScoreDisplay() {
        
        scoreDisplay.setText("Score: " + player.getScore());
        
        if (player.getTopDown()) {
        
            if(player.getScore() > player.getScoreManager().getArcadeHighScore()) {
        
                scoreDisplay.setFontColor(ColorRGBA.Yellow);
        
            }
        
            else {
        
                scoreDisplay.setFontColor(ColorRGBA.White);
            
            }
        
        }
        
        else {
        
            if(player.getScore() > player.getScoreManager().getClassicHighScore()) {
        
                scoreDisplay.setFontColor(ColorRGBA.Yellow);
        
            }
        
            else {
        
                scoreDisplay.setFontColor(ColorRGBA.White);
            
            }
        
        }        
        
    }
    
    public void showMenu() {
        scoreDisplay.hideWithEffect();
        titleText.showWithEffect();
        startButton.showWithEffect();
        highScoreButton.showWithEffect();
    }
    
    public void update(float tpf) {
    
        if (!player.isDead()) {
            updateScoreDisplay();
        }
        
        if (startPressed)
            
            if(System.currentTimeMillis()/100 - startPressTime/100 > 22) {
                startPressed = false;
                player.setIsDead(false);
            }
        
    }
    
}
