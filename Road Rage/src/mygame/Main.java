package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        setShowSettings(false);
        setDisplayFps(false);
        setDisplayStatView(false);
        getCamera().setLocation(new Vector3f(-15, 5.5f, 0));
        getFlyByCamera().setMoveSpeed(10);
        getFlyByCamera().setEnabled(false);
        GameManager gm = new GameManager(this);
        getStateManager().attach(gm);
        cam.lookAt(gm.getPlayerManager().getPlayer().getWorldTranslation(), new Vector3f(0,1,0));
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
