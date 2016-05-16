package snake.entities;

import snake.Handler;
import snake.gfx.Assets;

import java.awt.*;
import java.util.Random;
import java.util.List;

/**
 * Created by Dim on 06.05.2016.
 */
public class Apple extends Entity{

    //TODO add constructor without x|y paratemers
    public Apple(Handler handler, int xCoor, int yCoor) {
        super(handler, xCoor, yCoor);
    }

    public void respawn() {
        Random r = new Random();
        xCoor = r.nextInt(handler.getWidth()/20) * 20;
        yCoor = r.nextInt(handler.getHeight()/20) * 20;

        //spawned in Solid check
        if (handler.getTile(xCoor/20, yCoor/20).isSolid()){
            respawn();
        }

        //spawned in Snake check
        List<BodyPart> snakeParts = handler.getSnake().getParts();
        for (BodyPart part : snakeParts) {
            if (xCoor == part.getxCoor() && yCoor == part.getyCoor()){
                respawn();
            }
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.apple, xCoor, yCoor, null);
    }

}
