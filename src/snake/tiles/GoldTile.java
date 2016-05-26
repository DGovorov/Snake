package snake.tiles;

import snake.gfx.Assets;

/**
 * Created by Dim on 25.05.2016.
 */
public class GoldTile extends Tile {

    public GoldTile() {
        super(Assets.gold, 10);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
