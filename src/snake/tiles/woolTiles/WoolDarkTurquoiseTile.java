package snake.tiles.woolTiles;

import snake.gfx.Assets;
import snake.tiles.Tile;

/**
 * Created by Dim on 26.05.2016.
 */
public class WoolDarkTurquoiseTile extends Tile {

    public WoolDarkTurquoiseTile() {
        super(Assets.woolDarkTurquoise, 24);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
