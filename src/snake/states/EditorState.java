package snake.states;

import snake.Handler;
import snake.editor.WorldEditor;
import snake.gfx.Assets;
import snake.ui.ClickListener;
import snake.ui.UIImageButton;
import snake.ui.UIManager;
import snake.utils.Utils;
import java.util.List;

import java.awt.*;

/**
 * Created by Dim on 19.05.2016.
 */
public class EditorState extends State {

    private WorldEditor worldEditor;
    private UIManager uiManager;
    private String fileName;

    public EditorState(Handler handler) {
        super(handler);
        worldEditor = new WorldEditor(handler);
        createUIManager();
        fileName = "default";
    }

    @Override
    public void tick() {
        worldEditor.tick();
        if(uiManager != null){
            uiManager.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        worldEditor.render(g);
        if (uiManager != null){
            uiManager.render(g);
        }
    }

    @Override
    public void createUIManager() {
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        uiManager.add(new UIImageButton(10, 10, 171, 57, Assets.gameButtonMenu, new ClickListener() {
            @Override
            public void onClick() {
                //worldEditor.setBrush();
                List<String> world = worldEditor.worldToText();
                Utils.saveStringToFile(world, fileName);
            }
        }));
    }
}
