/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.state.AppStateManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author bob
 */
public class ScoreManager {
    
    private int    arcadeHighScore, classicHighScore;
    private AppStateManager stateManager;
    
    public ScoreManager(AppStateManager stateManager) {
        this.stateManager = stateManager;
        loadInfo();
        
    }
    
    public final void loadInfo() {
  
        Yaml yaml         = new Yaml();
        LinkedHashMap map;
        String filePath;
      
        try {
      
            filePath = stateManager.getState(AndroidManager.class).filePath;
      
        }
      
        catch (Exception e) {
      
            filePath = System.getProperty("user.home")+ "/";
      
        }
      
        try {
          
            File file            = new File(filePath + "StuntmanSave" + ".yml");
            FileInputStream fi   = new FileInputStream(file);
            Object obj           = yaml.load(fi);
            map                  = (LinkedHashMap) obj;
            fi.close();
          
        }
      
        catch (Exception e) {
          
            arcadeHighScore   = 0;
            classicHighScore  = 0;
            saveInfo();
            loadInfo();
            return;
      
        }

        arcadeHighScore  = (Integer)  map.get("Arcade HighScore");
        classicHighScore = (Integer)  map.get("Classic HighScore");
      
    }
  
    public void saveInfo() {
      
      HashMap contents = new HashMap();
      contents.put("Arcade HighScore", arcadeHighScore);
      contents.put("Classic HighScore", classicHighScore);
      
      DumperOptions options = new DumperOptions();
      options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
      options.setAllowUnicode(true);
      Yaml yaml = new Yaml(options);      
      
      
      String filePath;
      
      try {
      
          filePath = stateManager.getState(AndroidManager.class).filePath;
      
      }
      
      catch (Exception e) {
      
          filePath = System.getProperty("user.home") + "/";
      
      }
      
      File file = new File(filePath + "StuntmanSave" + ".yml");
      
      try {
          
          FileWriter fw  = new FileWriter(file);
          yaml.dump(contents, fw);
          fw.close();
          
      }
      
      catch(Exception e) {
      
      }
  
    }
    
    public int getClassicHighScore() {
        return classicHighScore;
    }
    
    public int getArcadeHighScore() {
        return arcadeHighScore;
    }
    
    public void saveNewArcadeHighScore(int newHighScore) {
        
        arcadeHighScore = newHighScore;
        saveInfo();
        
    }
    
    public void saveNewClassicHighScore(int newHighScore) {
    
        classicHighScore = newHighScore;
        saveInfo();        
        
    }
    
}
