package snake.tiles.woolTiles;

import snake.gfx.Assets;
import snake.tiles.Tile;

/**
 * Created by Dim on 26.05.2016.
 */
public class WoolOrangeTile extends Tile {

    public WoolOrangeTile() {
        super(Assets.woolOrange, 25);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
