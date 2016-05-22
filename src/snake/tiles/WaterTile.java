package snake.tiles;

import snake.gfx.Assets;

/**
 * Created by Dim on 22.05.2016.
 */
public class WaterTile extends Tile {

    public WaterTile() {
        super(Assets.water, 4);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
