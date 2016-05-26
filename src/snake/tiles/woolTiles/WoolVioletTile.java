package snake.tiles.woolTiles;

import snake.gfx.Assets;
import snake.tiles.Tile;

/**
 * Created by Dim on 26.05.2016.
 */
public class WoolVioletTile extends Tile {

    public WoolVioletTile() {
        super(Assets.woolViolet, 22);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
