package snake.tiles.woolTiles;

import snake.gfx.Assets;
import snake.tiles.Tile;

/**
 * Created by Dim on 26.05.2016.
 */
public class WoolDarkGreenTile  extends Tile {

    public WoolDarkGreenTile() {
        super(Assets.woolDarkGreen, 16);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
