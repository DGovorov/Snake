package snake.tiles.woolTiles;

import snake.gfx.Assets;
import snake.tiles.Tile;

/**
 * Created by Dim on 26.05.2016.
 */
public class WoolTurquoiseTile extends Tile {

    public WoolTurquoiseTile() {
        super(Assets.woolTurquoise, 21);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
