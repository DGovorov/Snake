package snake.entities;

import snake.Handler;
import snake.gfx.Assets;

import java.awt.*;

/**
 * Created by Dim on 07.05.2016.
 */
public class BodyPart extends Entity {

    public BodyPart(Handler handler, int xCoor, int yCoor) {
        super(handler, xCoor, yCoor);
    }

    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.snakeBall, xCoor, yCoor, null);
    }

}
