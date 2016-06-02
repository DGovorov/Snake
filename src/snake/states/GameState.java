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
    private String currentWorld;
    private WelcomeMessage welcomeMessage;
    private boolean endless;
    private boolean over;

    public GameState(Handler handler) {
        super(handler);
        currentWorld = "world1";
        init(handler);
        uiManager = new UIManager(handler);

        //TODO: rework this terrible welcome message prototype, and in another constructor
        welcomeMessage = new WelcomeMessage(currentWorld);
        uiManager.add(welcomeMessage);

    }

    public GameState(Handler handler, String worldName) {
        //TODO: fix this messy constructor, calling init() method two times; (first call in this() method;)
        this(handler);
        currentWorld = worldName;
        init(handler);
        if (world != null) {
            handler.setWorld(world);
        }

        uiManager.remove(welcomeMessage);
        welcomeMessage = new WelcomeMessage(currentWorld);
        uiManager.add(welcomeMessage);
    }

    public GameState(Handler handler, String worldName, boolean isEndless) {
        this(handler, worldName);
        endless = isEndless;
    }

    private void init(Handler handler) {
        String worldPath = getWorldPath(currentWorld);
        world = new World(handler, worldPath);
        handler.setWorld(world);

        snake = new Snake(handler, handler.getxSpawn(), handler.getySpawn(), 3);
        handler.setSnake(snake);

        apple = new Apple(handler);
        handler.setApple(apple);

        apple.respawn();
    }

    private String getWorldPath(String currentWorld) {
        return "res/worlds/" + currentWorld + ".txt";
    }

    @Override
    public void createUIManager() {

        //TODO: !!! rework game_end logic. current if() statement is used just to avoid loading non existing file
        if (snake.isVictorious() && !currentWorld.equals("world9")) {
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
        uiManager.add(new PopupMessage("Dead. Score: " + snake.getSize(), Color.RED));
    }

    private void levelCompleteUI() {
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        uiManager.add(new UIImageButton(234, 180, 171, 57, Assets.gameButtonNext, new ClickListener() {
            @Override
            public void onClick() {
                int number = Integer.valueOf(currentWorld.substring(5, 6));
                number++;
                currentWorld = "world" + number;
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
            if (!welcomeMessage.isGoing()) {
                uiManager.remove(welcomeMessage);
            }
        }

        if (!endless && !over) {
            over = levelCompleteCheck();
        }

    }

    private boolean levelCompleteCheck() {
        if (snake.getSize() >= 20) {
            snake.setDead(true);
            snake.setVictorious(true);
            createUIManager();
            return true;
        }
        return false;
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
    private class WelcomeMessage extends UIObject {

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
        public void onClick() {
        }
    }
}
