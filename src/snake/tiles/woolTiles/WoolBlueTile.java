package snake.tiles.woolTiles;

import snake.gfx.Assets;
import snake.tiles.Tile;

/**
 * Created by Dim on 26.05.2016.
 */
public class WoolBlueTile extends Tile {

    public WoolBlueTile() {
        super(Assets.woolBlue, 20);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
