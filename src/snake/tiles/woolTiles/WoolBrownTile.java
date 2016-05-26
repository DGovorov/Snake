package snake.tiles.woolTiles;

import snake.gfx.Assets;
import snake.tiles.Tile;

/**
 * Created by Dim on 26.05.2016.
 */
public class WoolBrownTile extends Tile {

    public WoolBrownTile() {
        super(Assets.woolBrown, 18);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
