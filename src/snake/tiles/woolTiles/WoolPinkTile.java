package snake.tiles.woolTiles;

import snake.gfx.Assets;
import snake.tiles.Tile;

/**
 * Created by Dim on 26.05.2016.
 */
public class WoolPinkTile extends Tile {

    public WoolPinkTile() {
        super(Assets.woolPink, 15);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}