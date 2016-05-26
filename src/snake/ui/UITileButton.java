package snake.ui;

import snake.tiles.Tile;

import java.awt.*;

/**
 * Created by Dim on 25.05.2016.
 */
public class UITileButton extends UIObject {

    private Tile tile;
    private ClickListener clicker;
    private static final int BUTTON_WIDTH = 20;
    private static final int BUTTON_HEIGHT = 20;

    public UITileButton(int x, int y, Tile tile, ClickListener clicker) {
        super(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        this.tile = tile;
        this.clicker = clicker;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        tile.render(g, x, y);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
