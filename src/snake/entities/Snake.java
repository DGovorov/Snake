package snake.entities;

import snake.Handler;
import snake.input.KeyManager;
import snake.states.GameState;
import snake.states.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dim on 07.05.2016.
 */
public class Snake extends Entity {

    private List<BodyPart> snake;
    private int size;
    private boolean dead;
    private int speed = 2;
    private int tick;

    public Snake(Handler handler, int xCoor, int yCoor) {
        super(handler, xCoor, yCoor);
        snake = new ArrayList<>();
        snake.add(new BodyPart(handler, xCoor, yCoor));
        this.size = 1;
    }

    public Snake(Handler handler, int xCoor, int yCoor, int size) {
        this(handler, xCoor, yCoor);
        this.size = size;
    }

    public void tick() {
        if (tick < speed){
            tick++;
            return;
        } else {
            tick = 0;
        }

        if (dead) {
            return;
        }

        getInput();
        BodyPart snakeHead = new BodyPart(handler, xCoor, yCoor);

        //out of screen check
        if (xCoor < 0 || yCoor < 0 || xCoor >= handler.getWidth() || yCoor >= handler.getHeight()) {
            /*State.setState(new GameState(handler));
            xCoor = 20;
            yCoor = 20;*/
            dead = true;
            //TODO: make game reset possible instead of this "return;"s
            handler.getState().createUIManager();
            return;
        }

        //hit stone tile check
        if (handler.getTile(xCoor/20, yCoor/20).isSolid()) {
            /*State.setState(new GameState(handler));
            xCoor = 20;
            yCoor = 20;*/
            dead = true;
            handler.getState().createUIManager();
            return;
        }

        //hit self check
        for (int i = 0; i < snake.size() - 3; i++) {
            if (xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()){
                /*State.setState(new GameState(handler));
                xCoor = 20;
                yCoor = 20;*/
                dead = true;
                handler.getState().createUIManager();
                return;
            }
        }

        //ate apple check
        if (handler.getApple().xCoor == xCoor && handler.getApple().yCoor == yCoor){
            handler.getApple().respawn();
            size++;
        }

        //movement logic
        snake.add(snakeHead);
        if (snake.size() > size) {
            snake.remove(0);
        }

    }

    @Override
    public void render(Graphics g) {
        for (BodyPart snakePart : snake) {
            snakePart.render(g);
        }
    }

    private void getInput() {
        KeyManager manager = handler.getKeyManager();

        if (manager.up) {
            yCoor -= 20;
        }
        if (manager.down) {
            yCoor += 20;
        }
        if (manager.left) {
            xCoor -= 20;
        }
        if (manager.right) {
            xCoor += 20;
        }
    }

    public List<BodyPart> getParts() {
        return snake;
    }

}
