package snake;

import snake.entities.Apple;
import snake.entities.Snake;
import snake.input.KeyManager;
import snake.input.MouseManager;
import snake.tiles.Tile;
import snake.worlds.World;

/**
 * Created by Dim on 14.05.2016.
 */
public class Handler {

    private Game game;
    private World world;
    private Snake snake;
    private int xSpawn;
    private int ySpawn;
    private Apple apple;

    public Handler(Game game) {
        this.game = game;
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public Tile getTile(int x, int y) {
        return world.getTile(x, y);
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    public Apple getApple() {
        return apple;
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Snake getSnake() {
        return snake;
    }

    public World getWorld() {
        return world;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getxSpawn() {
        return xSpawn;
    }

    public void setxSpawn(int xSpawn) {
        this.xSpawn = xSpawn;
    }

    public int getySpawn() {
        return ySpawn;
    }

    public void setySpawn(int ySpawn) {
        this.ySpawn = ySpawn;
    }
}
