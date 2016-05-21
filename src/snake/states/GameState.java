package snake.states;

import snake.Handler;
import snake.entities.Apple;
import snake.entities.Snake;
import snake.gfx.Assets;
import snake.ui.ClickListener;
import snake.ui.UIImageButton;
import snake.ui.UIManager;
import snake.worlds.World;

import java.awt.*;

/**
 * Created by Dim on 15.05.2016.
 */
public class GameState extends State {

    private World world;
    private Snake snake;
    private Apple apple;
    private UIManager uiManager;

    public GameState(Handler handler) {
        super(handler);
        init(handler);
    }

    public GameState(Handler handler, World world) {
        this(handler);
        init(handler);
        if (world != null) {
            this.world = world;
        }
    }

    private void init(Handler handler) {
        world = new World(handler, "res/worlds/world1.txt");
        snake = new Snake(handler, handler.getxSpawn(), handler.getySpawn(), 3);
        apple = new Apple(handler, 80, 80);

        handler.setWorld(world);
        handler.setSnake(snake);
        handler.setApple(apple);
        apple.respawn();
    }

    @Override
    public void createUIManager() {
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        uiManager.add(new UIImageButton(234, 180, 171, 57, Assets.gameButtonRestart, new ClickListener() {
            @Override
            public void onClick() {
                //TODO: replace this uiManager workaround. (two following lines)
                handler.getMouseManager().setUIManager(null);
                uiManager = null;
                init(handler);
                handler.getKeyManager().resetSnakeControlls();
            }
        }));
        uiManager.add(new UIImageButton(234, 180 + 57, 171, 57, Assets.gameButtonMenu, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                uiManager = null;
                init(handler);
                handler.getKeyManager().resetSnakeControlls();
                handler.setState(new MenuState(handler));
            }
        }));
    }

    //TODO: changeLevel logic
    private int count = 0;

    public void levelCompleteUI() {
        if(count != 0){
            System.out.println("here!");
        }
        count++;
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        uiManager.add(new UIImageButton(234, 180, 171, 57, Assets.menuButtonStart, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                uiManager = null;
                init(handler);
                handler.getKeyManager().resetSnakeControlls();
                handler.setState(new GameState(handler, new World(handler, "res/worlds/world2.txt")));
            }
        }));
    }

    @Override
    public void tick() {
        world.tick();
        snake.tick();
        if (uiManager != null) {
            uiManager.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        snake.render(g);
        apple.render(g);
        if (uiManager != null) {
            uiManager.render(g);
        }
    }

}
