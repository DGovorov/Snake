package snake.worlds;

import snake.Game;
import snake.Handler;
import snake.gfx.Assets;
import snake.tiles.DirtTile;
import snake.tiles.Tile;
import snake.utils.Utils;

import java.awt.*;

/**
 * Created by Dim on 14.05.2016.
 */
public class World {

    private Handler handler;
    private int width;
    private int height;
    private int xSpawn;
    private int ySpawn;
    private int[][] tiles;

    public World(Handler handler, String path) {
        this.handler = handler;
        loadWorld(path);
        handler.setxSpawn(xSpawn);
        handler.setySpawn(ySpawn);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        //TODO: limits can be used to increase rendering efficiency, if using "following camera" mode

        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                //TODO: here can be implemented "following camera" view
                getTile(x,y).render(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
            }
        }

    }

    public Tile getTile(int x, int y) {
        Tile tile = Tile.tiles[tiles[x][y]];
        if (tile == null) {
            return new DirtTile();
        }
        return tile;
    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        xSpawn = Utils.parseInt(tokens[2]);
        ySpawn = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

}