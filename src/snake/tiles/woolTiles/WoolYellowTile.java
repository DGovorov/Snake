package snake.tiles.woolTiles;

import snake.gfx.Assets;
import snake.tiles.Tile;

/**
 * Created by Dim on 26.05.2016.
 */
public class WoolYellowTile  extends Tile {

    public WoolYellowTile() {
        super(Assets.woolYellow, 19);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
