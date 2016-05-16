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

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        uiManager.add(new UIImageButton(234, 180, 171, 57, Assets.menuButtonStart, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));

        uiManager.add(new UIImageButton(234, 180 + 57, 171, 57, Assets.menuButtonOptions, new ClickListener() {
            @Override
            public void onClick() {

            }
        }));

        uiManager.add(new UIImageButton(234, 180 + 57 * 2, 171, 57, Assets.menuButtonQuit, new ClickListener() {
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
        uiManager.render(g);
    }
}
