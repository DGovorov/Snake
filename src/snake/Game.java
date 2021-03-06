package snake;

import snake.display.Display;
import snake.gfx.Assets;
import snake.input.KeyManager;
import snake.input.MouseManager;
import snake.states.GameState;
import snake.states.MenuState;
import snake.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Dim on 05.05.2016.
 */
//TODO: make tile size a field
public class Game implements Runnable {

    private Display display;
    private int width;
    private int height;
    private String title;

    private Thread thread;
    public boolean running = false;

    private BufferStrategy bs;
    private Graphics g;

    private KeyManager keyManager;
    private MouseManager mouseManager;
    private Handler handler;

    private State currentState;
    public GameState gameState;

    private int fps = 30;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();

    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getCanvas().addKeyListener(keyManager);
        //TODO: google more about that weird listeners addition
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        handler = new Handler(this);

        gameState = new GameState(handler);
        MenuState menuState = new MenuState(handler);
        setCurrentState(menuState);


    }

    private void tick() {
        keyManager.tick();


        if (getCurrentState() != null) {
            getCurrentState().tick();
        }
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        if (getCurrentState() != null) {
            getCurrentState().render(g);
        }

        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        init();

        double timePerTick = 1_000_000_000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                delta--;
            }

            if (timer >= 1_000_000_000) {
                timer = 0;
            }
        }

        stop();

    }

    public synchronized void start() {
        if (running) {
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }

        running = false;

        render();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public Display getDisplay() {
        return display;
    }

}
