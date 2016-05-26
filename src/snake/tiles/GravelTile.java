package snake.tiles;

import snake.gfx.Assets;

/**
 * Created by Dim on 25.05.2016.
 */
public class GravelTile extends Tile {

    public GravelTile() {
        super(Assets.gravel, 7);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
