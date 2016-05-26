package snake.tiles;

import snake.tiles.woolTiles.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Dim on 14.05.2016.
 */
public abstract class Tile {

    public static Tile[] tiles = new Tile[256];
    //TODO: rework tile initialization. Make it possible to create Tile by id.
    public static Tile grassTile = new GrassTile();
    public static Tile dirtTile = new DirtTile();
    public static Tile stoneTile = new StoneTile();
    public static Tile sandTile = new SandTile();
    public static Tile waterTile = new WaterTile();
    public static Tile mossStoneTile = new MossStoneTile();
    public static Tile brickTile = new BrickTile();
    public static Tile gravelTile = new GravelTile();
    public static Tile snowTile = new SnowTile();
    public static Tile ironTile = new IronTile();
    public static Tile goldTile = new GoldTile();
    public static Tile diamondTile = new DiamondTile();

    public static Tile woolBlackTile = new WoolBlackTile();
    public static Tile woolDarkGreyTile = new WoolDarkGreyTile();
    public static Tile woolRedTile = new WoolRedTile();
    public static Tile woolPinkTile = new WoolPinkTile();
    public static Tile woolDarkGreenTile = new WoolDarkGreenTile();
    public static Tile woolGreenTile = new WoolGreenTile();
    public static Tile woolBrownTile = new WoolBrownTile();
    public static Tile woolYellowTile = new WoolYellowTile();
    public static Tile woolBlueTile = new WoolBlueTile();
    public static Tile woolTurquoiseTile = new WoolTurquoiseTile();
    public static Tile woolVioletTile = new WoolVioletTile();
    public static Tile woolDarkPinkTile = new WoolDarkPinkTile();
    public static Tile woolDarkTurquoiseTile = new WoolDarkTurquoiseTile();
    public static Tile woolOrangeTile = new WoolOrangeTile();
    public static Tile woolGreyTile = new WoolGreyTile();
    public static Tile woolWhiteTile = new WoolWhiteTile();

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
