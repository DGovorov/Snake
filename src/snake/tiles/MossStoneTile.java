package snake.tiles;

import snake.gfx.Assets;

/**
 * Created by Dim on 22.05.2016.
 */
public class MossStoneTile extends Tile {

    public MossStoneTile() {
        super(Assets.mossStone, 5);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
