package snake.tiles;

import snake.gfx.Assets;

/**
 * Created by Dim on 25.05.2016.
 */
public class SnowTile extends Tile {

    public SnowTile() {
        super(Assets.snow, 8);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
