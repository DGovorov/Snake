package snake.tiles.woolTiles;

import snake.gfx.Assets;
import snake.tiles.Tile;

/**
 * Created by Dim on 26.05.2016.
 */
public class WoolGreenTile  extends Tile {

    public WoolGreenTile() {
        super(Assets.woolGreen, 17);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
