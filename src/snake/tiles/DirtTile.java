package snake.tiles;

import snake.gfx.Assets;

/**
 * Created by Dim on 14.05.2016.
 */
public class DirtTile extends Tile {

    public DirtTile() {
        super(Assets.dirt, 0);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
