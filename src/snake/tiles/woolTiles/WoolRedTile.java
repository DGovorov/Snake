package snake.tiles.woolTiles;

import snake.gfx.Assets;
import snake.tiles.Tile;

/**
 * Created by Dim on 26.05.2016.
 */
public class WoolRedTile extends Tile {

    public WoolRedTile() {
        super(Assets.woolRed, 14);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}