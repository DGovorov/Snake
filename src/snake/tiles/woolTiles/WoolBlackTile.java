package snake.tiles.woolTiles;

import snake.gfx.Assets;
import snake.tiles.Tile;

/**
 * Created by Dim on 26.05.2016.
 */
public class WoolBlackTile extends Tile {

    public WoolBlackTile() {
        super(Assets.woolBlack, 12);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
