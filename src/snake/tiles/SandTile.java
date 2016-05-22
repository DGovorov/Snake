package snake.tiles;

import snake.gfx.Assets;

/**
 * Created by Dim on 22.05.2016.
 */
public class SandTile extends Tile {

    public SandTile() {
        super(Assets.sand, 3);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
