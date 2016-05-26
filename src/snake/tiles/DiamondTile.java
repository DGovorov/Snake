package snake.tiles;

import snake.gfx.Assets;

/**
 * Created by Dim on 25.05.2016.
 */
public class DiamondTile extends Tile {

    public DiamondTile() {
        super(Assets.diamond, 11);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
