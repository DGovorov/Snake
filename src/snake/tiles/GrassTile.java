package snake.tiles;

import snake.gfx.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by Dim on 14.05.2016.
 */
public class GrassTile extends Tile {

    public GrassTile() {
        super(Assets.grass, 1);
    }

    /*public GrassTile(BufferedImage texture, int id) {
        super(Assets.grass, id);
    }*/

    @Override
    public boolean isSolid() {
        return false;
    }
}
