package snake.entities;

import snake.Handler;

import java.awt.*;

/**
 * Created by Dim on 05.05.2016.
 */
public abstract class Entity {

    protected Handler handler;
    protected int xCoor;
    protected int yCoor;

    public Entity(Handler handler, int xCoor, int yCoor) {
        this.handler = handler;
        this.xCoor = xCoor;
        this.yCoor = yCoor;
    }

    //public abstract void tick();

    public abstract void render(Graphics g);

    public Handler getGame() {
        return handler;
    }

    public void setGame(Handler handler) {
        this.handler = handler;
    }

    public int getxCoor() {
        return xCoor;
    }

    public void setxCoor(int xCoor) {
        this.xCoor = xCoor;
    }

    public int getyCoor() {
        return yCoor;
    }

    public void setyCoor(int yCoor) {
        this.yCoor = yCoor;
    }

}
