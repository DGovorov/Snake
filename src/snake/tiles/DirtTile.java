package snake.tiles;

import snake.gfx.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by Dim on 14.05.2016.
 */
public class DirtTile extends Tile {

    public DirtTile() {
        super(Assets.dirt, 0);
    }

    /*public DirtTile(BufferedImage texture, int id) {
        super(Assets.dirt, id);
    }*/

    @Override
    public boolean isSolid() {
        return false;
    }
}
