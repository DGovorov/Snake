package snake.states;

import snake.Handler;
import snake.gfx.Assets;
import snake.ui.ClickListener;
import snake.ui.UIImageButton;
import snake.ui.UIManager;
import snake.ui.UIWorldButton;
import snake.utils.Utils;

import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * Created by Dim on 27.05.2016.
 */
public class WorldSelectionState extends State {

    private UIManager uiManager;
    private List<File> worlds;

    public WorldSelectionState(Handler handler) {
        super(handler);
        createUIManager();
    }

    @Override
    public void tick() {
        if (uiManager != null) {
            uiManager.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.worldsBackground, 0, 0, null);

        if (uiManager != null) {
            uiManager.render(g);
        }
    }

    @Override
    public void createUIManager() {
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        worlds = Utils.loadWorlds();
        int yStep = 0;
        int xStep = 0;
        for (File world : worlds) {
            String number = world.toString().replace(".txt", "");
            number = number.replace("res\\worlds\\", "");
            number = number.replace("res/worlds/", "");
            System.out.println(number);
            final String worldName = number;
            uiManager.add(new UIWorldButton(20 + xStep, 20 + yStep, world, new ClickListener() {
                @Override
                public void onClick() {
                    handler.getMouseManager().setUIManager(null);
                    handler.setState(new GameState(handler, worldName, true));
                }
            }));
            yStep += 40;
            if (yStep > 310) {
                yStep = 0;
                xStep += 5 + 200;
            }
        }

        uiManager.add(new UIImageButton(460, 300, 171, 57, Assets.gameButtonMenu, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                uiManager = null;
                handler.setState(new MenuState(handler));
            }
        }));
    }
}
