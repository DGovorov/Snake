package snake.states;

import snake.Handler;
import snake.entities.Apple;
import snake.entities.Snake;
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

    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        snake = new Snake(handler, handler.getxSpawn(), handler.getySpawn(), 3);
        apple = new Apple(handler, 80, 80);

        handler.setWorld(world);
        handler.setSnake(snake);
        handler.setApple(apple);
        apple.respawn();
    }

    @Override
    public void tick() {
        world.tick();
        snake.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        snake.render(g);
        apple.render(g);
    }


}
