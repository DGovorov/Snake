package snake.states;

import snake.Handler;
import snake.entities.Apple;
import snake.entities.Snake;
import snake.gfx.Assets;
import snake.input.KeyManager;
import snake.ui.ClickListener;
import snake.ui.UIImageButton;
import snake.ui.UIManager;
import snake.worlds.World;

import java.awt.Graphics;


/**
 * Created by Dim on 15.05.2016.
 */
public class GameState extends State {

    private KeyManager keyManager;
    private World world;
    private Snake snake;
    private Apple apple;
    private UIManager uiManager;
    private int currentWorld;
    private int speed;
    private int tick;

    public GameState(Handler handler) {
        super(handler);
        currentWorld = 1;
        init(handler);
    }

    public GameState(Handler handler, int worldNumber) {
        //TODO: fix this messy constructor, calling init() method two times; (first call in this();)
        this(handler);
        currentWorld = worldNumber;
        init(handler);
        if (world != null) {
            handler.setWorld(world);
        }
    }

    private void init(Handler handler) {
        //keyManager = new KeyManager();
        keyManager = handler.getKeyManager();
        String worldPath = getWorldPath(currentWorld);
        world = new World(handler, worldPath);
        snake = new Snake(handler, handler.getxSpawn(), handler.getySpawn(), 3);
        apple = new Apple(handler, 80, 80);

        handler.setWorld(world);
        handler.setSnake(snake);
        handler.setApple(apple);
        apple.respawn();
        setSpeed(5);
    }

    private String getWorldPath(int currentWorld) {
        return "res/worlds/world" + currentWorld + ".txt";
    }

    public void setSpeed(int speed){
        if (speed > 0 && speed < 20) {
            this.speed = speed;
        }
    }

    @Override
    public void createUIManager() {

        //TODO: !!! REMOVE && currentWorld != 5. workaround just to avoid fail on loading not existing file.
        if (snake.isVictorious() && currentWorld != 5) {
            levelCompleteUI();
        } else {
            levelFailedUI();
        }
    }

    private void levelFailedUI() {
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

    private void levelCompleteUI() {
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        uiManager.add(new UIImageButton(234, 180, 171, 57, Assets.gameButtonNext, new ClickListener() {
            @Override
            public void onClick() {
                currentWorld++;
                handler.getMouseManager().setUIManager(null);
                uiManager = null;
                init(handler);
                handler.getKeyManager().resetSnakeControlls();
                handler.setState(new GameState(handler, currentWorld));
            }
        }));
    }

    @Override
    public void tick() {
        if (tick < speed){
            tick++;
            return;
        } else {
            tick = 0;
        }

        //handler.getKeyManager().tick();
        keyManager.tick();

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
