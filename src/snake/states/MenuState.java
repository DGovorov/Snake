package snake.states;

import snake.Handler;
import snake.gfx.Assets;
import snake.ui.ClickListener;
import snake.ui.UIImageButton;
import snake.ui.UIManager;

import java.awt.*;

/**
 * Created by Dim on 15.05.2016.
 */
public class MenuState extends State {

    private UIManager uiManager;
    private int appleFrames;
    private int currentAppleFrame;

    public MenuState(Handler handler) {
        super(handler);
        appleFrames = Assets.rotatingApple.size();
        createUIManager();
    }

    @Override
    public void createUIManager() {
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);



        uiManager.add(new UIImageButton(234, 180 + 59, 171, 57, Assets.menuButtonEndless, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                //handler.setState(handler.getGameState());
                handler.setState(new WorldChoosingState(handler));
            }
        }));




        uiManager.add(new UIImageButton(234, 180, 171, 57, Assets.menuButtonStart, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                //handler.setState(handler.getGameState());
                handler.setState(new GameState(handler));
            }
        }));

        uiManager.add(new UIImageButton(234, 180 + 57 * 2, 171, 57, Assets.menuButtonEditor, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                handler.setState(new EditorState(handler));
            }
        }));

        uiManager.add(new UIImageButton(234 + 200, 180 + 57 * 2, 171, 57, Assets.menuButtonQuit, new ClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
            }
        }));

    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.menuBackground, 0, 0, null);

        drawRotatingApple(g);

        uiManager.render(g);
    }

    private void drawRotatingApple(Graphics g) {
        g.drawImage(Assets.rotatingApple.get(currentAppleFrame/2), 410, 5, null);
        currentAppleFrame++;
        if (currentAppleFrame == appleFrames * 2){
            currentAppleFrame = 0;
        }
    }
}
