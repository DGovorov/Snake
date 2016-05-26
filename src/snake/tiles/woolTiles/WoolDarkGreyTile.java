package snake.tiles.woolTiles;

import snake.gfx.Assets;
import snake.tiles.Tile;

/**
 * Created by Dim on 26.05.2016.
 */
public class WoolDarkGreyTile extends Tile {

    public WoolDarkGreyTile() {
        super(Assets.woolDarkGrey, 13);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}