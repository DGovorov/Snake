package snake.tiles.woolTiles;

import snake.gfx.Assets;
import snake.tiles.Tile;

/**
 * Created by Dim on 26.05.2016.
 */
public class WoolDarkPinkTile extends Tile {

    public WoolDarkPinkTile() {
        super(Assets.woolDarkPink, 23);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
