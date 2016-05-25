package snake.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Dim on 14.05.2016.
 */
public abstract class Tile {

    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile();
    public static Tile dirtTile = new DirtTile();
    public static Tile stoneTile = new StoneTile();
    public static Tile sandTile = new SandTile();
    public static Tile waterTile = new WaterTile();
    public static Tile mossStoneTile = new MossStoneTile();
    public static Tile brickTile = new BrickTile();

    public static final int TILE_WIDTH = 20;
    public static final int TILE_HEIGHT = 20;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public abstract boolean isSolid();

    public int getId() {
        return id;
    }

}
