package snake.tiles.woolTiles;

import snake.gfx.Assets;
import snake.tiles.Tile;

/**
 * Created by Dim on 26.05.2016.
 */
public class WoolWhiteTile extends Tile {

    public WoolWhiteTile() {
        super(Assets.woolWhite, 27);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
