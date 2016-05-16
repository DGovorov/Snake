package snake.tiles;

import snake.gfx.Assets;

/**
 * Created by Dim on 14.05.2016.
 */
public class StoneTile extends Tile {

    public StoneTile() {
        super(Assets.stone, 2);
    }

    /*public StoneTile(BufferedImage texture, int id) {
        super(Assets.stone, id);
    }*/

    @Override
    public boolean isSolid() {
        return true;
    }

}
