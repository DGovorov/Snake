package snake.editor;

import snake.Handler;
import snake.tiles.DirtTile;
import snake.tiles.StoneTile;
import snake.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dim on 17.05.2016.
 */
public class WorldEditor {

    private Handler handler;
    private int width;
    private int height;
    private int xSpawn;
    private int ySpawn;
    private int mouseX;
    private int mouseY;
    private int[][] tiles;

    private Tile brush;
    private String fileName;

    public WorldEditor(Handler handler) {
        this.handler = handler;
        brush = new StoneTile();

        width = 32;
        height = 18;
        tiles = new int[width][height];
        for (int[] row : tiles) {
            Arrays.fill(row, 1);
        }

        xSpawn = 6;
        ySpawn = 7;
    }

    public void setBrush() {
        brush = new DirtTile();
    }

    public void drawTile() {
        tiles[mouseX][mouseY] = brush.getId();
    }

    public Tile getTile(int x, int y) {
        Tile tile = Tile.tiles[tiles[x][y]];
        if (tile == null) {
            return new DirtTile();
        }
        return tile;
    }

    public void tick() {
        int currentX = handler.getMouseManager().getMouseX();
        if (currentX >= 0 && currentX <= width * 20) {
            mouseX = (currentX - (currentX % 20)) / 20;
        }
        int currentY = handler.getMouseManager().getMouseY();
        if (currentY >= 0 && currentY <= height * 20) {
            mouseY = (currentY - (currentY % 20)) / 20;
        }
        if (handler.getMouseManager().isLeftPressed()) {
            drawTile();
        }
    }

    public void render(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                getTile(x, y).render(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
            }
        }
    }

    public List<String> worldToText() {
        List<String> world = new ArrayList<>();
        world.add(width + " " + height);
        world.add(xSpawn + " " + ySpawn);

        for (int y = 0; y < height; y++) {
            StringBuilder builder = new StringBuilder();
            for (int x = 0; x < width; x++) {
                builder.append(tiles[x][y]).append(" ");
            }
            world.add(builder.toString());
        }

        System.out.println(world);
        return world;
    }

    //TODO: manage saving tiles to file as string

    //TODO: make brushChange UI

    //TODO: think about running WorldEditor inside Game class as State, or separate executable
}
