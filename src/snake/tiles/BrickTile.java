package snake.tiles;

import snake.gfx.Assets;

/**
 * Created by Dim on 25.05.2016.
 */
public class BrickTile extends Tile {

    public BrickTile() {
        super(Assets.brick, 6);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
