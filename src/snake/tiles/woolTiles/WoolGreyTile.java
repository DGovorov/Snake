package snake.tiles.woolTiles;

import snake.gfx.Assets;
import snake.tiles.Tile;

/**
 * Created by Dim on 26.05.2016.
 */
public class WoolGreyTile extends Tile {

    public WoolGreyTile() {
        super(Assets.woolGrey, 26);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
