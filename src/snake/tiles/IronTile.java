package snake.tiles;

import snake.gfx.Assets;

/**
 * Created by Dim on 25.05.2016.
 */
public class IronTile extends Tile {

    public IronTile() {
        super(Assets.iron, 9);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
