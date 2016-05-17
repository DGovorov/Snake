package snake.tiles;

import snake.gfx.Assets;

/**
 * Created by Dim on 14.05.2016.
 */
public class GrassTile extends Tile {

    public GrassTile() {
        super(Assets.grass, 1);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
