package snake.states;

import snake.Handler;
import snake.entities.Apple;
import snake.entities.Snake;
import snake.gfx.Assets;
import snake.ui.*;
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
    private int currentWorld;
    private WelcomeMessage welcomeMessage;

    public GameState(Handler handler) {
        super(handler);
        currentWorld = 1;
        init(handler);
        uiManager = new UIManager(handler);

        //TODO: rework this terrible welcome message prototype, and in another constructor
        welcomeMessage = new WelcomeMessage("Level " +currentWorld);
        uiManager.add(welcomeMessage);

    }

    public GameState(Handler handler, int worldNumber) {
        //TODO: fix this messy constructor, calling init() method two times; (first call in this() method;)
        this(handler);
        currentWorld = worldNumber;
        init(handler);
        if (world != null) {
            handler.setWorld(world);
        }

        uiManager.remove(welcomeMessage);
        welcomeMessage = new WelcomeMessage("Level " +currentWorld);
        uiManager.add(welcomeMessage);
    }

    private void init(Handler handler) {
        String worldPath = getWorldPath(currentWorld);
        world = new World(handler, worldPath);
        snake = new Snake(handler, handler.getxSpawn(), handler.getySpawn(), 3);
        apple = new Apple(handler, 80, 80);

        handler.setWorld(world);
        handler.setSnake(snake);
        handler.setApple(apple);
        apple.respawn();
    }

    private String getWorldPath(int currentWorld) {
        return "res/worlds/world" + currentWorld + ".txt";
    }

    @Override
    public void createUIManager() {

        //TODO: !!! REMOVE && currentWorld != 5. workaround just to avoid fail on loading not existing file.
        if (snake.isVictorious() && currentWorld != 9) {
            levelCompleteUI();
        } else {
            //TODO: Game Complete UI
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
        uiManager.add(new PopupMessage("Level Failed!", Color.RED));
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
                GameState gameState = new GameState(handler, currentWorld);
                handler.setState(gameState);
                handler.setGameState(gameState);
            }
        }));
        uiManager.add(new PopupMessage("Level Complete!", Color.GREEN));
    }

    @Override
    public void tick() {

        world.tick();
        snake.tick();
        if (uiManager != null) {
            uiManager.tick();
            if (!welcomeMessage.isGoing()){
                uiManager.remove(welcomeMessage);
            }
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

    //TODO: think! Are these even worth being inner-classes
    private class WelcomeMessage extends UIObject{

        private int lifetime;
        private String message;

        public WelcomeMessage(String message) {
            super(20, handler.getHeight() / 2, 0, 0);
            lifetime = 130;
            this.message = message;
        }

        public boolean isGoing() {
            return (lifetime > 0);
        }

        public void tick() {
            if (lifetime > 0) {
                lifetime--;
            }
        }

        public void render(Graphics g) {
            int size = Math.max(lifetime, 42);
            Font font = new Font("Serif", Font.PLAIN, size);
            g.setFont(font);
            g.setColor(Color.orange);
            g.drawString(message, x, y);
        }

        @Override
        public void onClick() {}
    }
}
