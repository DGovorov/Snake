package snake.gfx;

import snake.tiles.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dim on 05.05.2016.
 */
public class Assets {

    private static final int TILE_WIDTH = 20;
    private static final int TILE_HEIGHT = 20;
    private static final int MENU_BUTTON_WIDTH = 171;
    private static final int MENU_BUTTON_HEIGHT = 57;

    public static BufferedImage menuBackground;
    public static List<BufferedImage> rotatingApple;

    public static BufferedImage dirt;
    public static BufferedImage grass;
    public static BufferedImage stone;
    public static BufferedImage sand;
    public static BufferedImage water;
    public static BufferedImage mossStone;
    public static BufferedImage brick;
    public static BufferedImage gravel;
    public static BufferedImage snow;
    public static BufferedImage iron;
    public static BufferedImage gold;
    public static BufferedImage diamond;

    public static BufferedImage woolBlack;
    public static BufferedImage woolDarkGrey;
    public static BufferedImage woolRed;
    public static BufferedImage woolPink;
    public static BufferedImage woolDarkGreen;
    public static BufferedImage woolGreen;
    public static BufferedImage woolBrown;
    public static BufferedImage woolYellow;
    public static BufferedImage woolBlue;
    public static BufferedImage woolTurquoise;
    public static BufferedImage woolViolet;
    public static BufferedImage woolDarkPink;
    public static BufferedImage woolDarkTurquoise;
    public static BufferedImage woolOrange;
    public static BufferedImage woolGrey;
    public static BufferedImage woolWhite;

    public static BufferedImage apple;
    public static BufferedImage snakeBall;

    public static Map<Boolean, BufferedImage> menuButtonStart;
    public static Map<Boolean, BufferedImage> menuButtonOptions;
    public static Map<Boolean, BufferedImage> menuButtonQuit;
    public static Map<Boolean, BufferedImage> menuButtonEditor;
    public static Map<Boolean, BufferedImage> gameButtonRestart;
    public static Map<Boolean, BufferedImage> gameButtonMenu;
    public static Map<Boolean, BufferedImage> gameButtonNext;
    public static Map<Boolean, BufferedImage> editorButtonSave;

    public static Map<Boolean, BufferedImage> worldButton;

    public static BufferedImage tilePalette;
    public static BufferedImage woodBackground;

    public static void init() {
        cropMainSheet();

        cropButtons();

        cropAppleRotation();

        tilePalette = ImageLoader.loadImage("/textures/tilePalette.png");
        woodBackground = ImageLoader.loadImage("/textures/woodBackground.png");
        menuBackground = ImageLoader.loadImage("/textures/menuBackground.png");
    }

    private static void cropAppleRotation() {
        int width = 61;
        int height = 87;

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/appleRotation.png"));
        rotatingApple = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            int xStep = i % 10;
            int yStep = i / 10;
            rotatingApple.add(sheet.crop(width * xStep, height * yStep, width, height));
        }

    }

    private static void cropButtons() {
        SpriteSheet sheet;
        sheet = new SpriteSheet(ImageLoader.loadImage("/textures/gameButtons.png"));
        menuButtonStart = new HashMap<>();
        menuButtonStart.put(false, sheet.crop(0, 0, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        menuButtonStart.put(true, sheet.crop(0, MENU_BUTTON_HEIGHT, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        menuButtonOptions = new HashMap<>();
        menuButtonOptions.put(false, sheet.crop(0, MENU_BUTTON_HEIGHT * 2, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        menuButtonOptions.put(true, sheet.crop(0, MENU_BUTTON_HEIGHT * 3, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        menuButtonQuit = new HashMap<>();
        menuButtonQuit.put(false, sheet.crop(0, MENU_BUTTON_HEIGHT * 4, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        menuButtonQuit.put(true, sheet.crop(0, MENU_BUTTON_HEIGHT * 5, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        menuButtonEditor = new HashMap<>();
        menuButtonEditor.put(false, sheet.crop(0, MENU_BUTTON_HEIGHT * 6, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        menuButtonEditor.put(true, sheet.crop(0, MENU_BUTTON_HEIGHT * 7, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        gameButtonRestart = new HashMap<>();
        gameButtonRestart.put(false, sheet.crop(MENU_BUTTON_WIDTH, 0, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        gameButtonRestart.put(true, sheet.crop(MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        gameButtonMenu = new HashMap<>();
        gameButtonMenu.put(false, sheet.crop(MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT * 2, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        gameButtonMenu.put(true, sheet.crop(MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT * 3, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        editorButtonSave = new HashMap<>();
        editorButtonSave.put(false, sheet.crop(MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT * 4, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        editorButtonSave.put(true, sheet.crop(MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT * 5, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        gameButtonNext = new HashMap<>();
        gameButtonNext.put(false, sheet.crop(MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT * 6, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        gameButtonNext.put(true, sheet.crop(MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT * 7, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));

        sheet = new SpriteSheet(ImageLoader.loadImage("/textures/worldButton.png"));
        worldButton = new HashMap<>();
        worldButton.put(false, sheet.crop(0, 0, 214, 40));
        worldButton.put(true, sheet.crop(0, 40, 214, 40));
    }

    private static void cropMainSheet() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        //TODO: crop tiles in a loop, using collection, probably store images as collection

        dirt = sheet.crop(0, 0, TILE_WIDTH, TILE_HEIGHT);
        grass = sheet.crop(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT);
        stone = sheet.crop(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT);
        sand = sheet.crop(TILE_WIDTH * 3, 0, TILE_WIDTH, TILE_HEIGHT);
        water = sheet.crop(TILE_WIDTH * 4, 0, TILE_WIDTH, TILE_HEIGHT);
        mossStone = sheet.crop(TILE_WIDTH * 5, 0, TILE_WIDTH, TILE_HEIGHT);
        brick = sheet.crop(TILE_WIDTH * 6, 0, TILE_WIDTH, TILE_HEIGHT);
        gravel = sheet.crop(TILE_WIDTH * 7, 0, TILE_WIDTH, TILE_HEIGHT);
        snow = sheet.crop(TILE_WIDTH * 8, 0, TILE_WIDTH, TILE_HEIGHT);
        iron = sheet.crop(TILE_WIDTH * 9, 0, TILE_WIDTH, TILE_HEIGHT);
        gold = sheet.crop(TILE_WIDTH * 10, 0, TILE_WIDTH, TILE_HEIGHT);
        diamond = sheet.crop(TILE_WIDTH * 11, 0, TILE_WIDTH, TILE_HEIGHT);

        woolBlack = sheet.crop(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        woolDarkGrey = sheet.crop(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        woolRed = sheet.crop(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        woolPink = sheet.crop(TILE_WIDTH * 3, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        woolDarkGreen = sheet.crop(TILE_WIDTH * 4, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        woolGreen = sheet.crop(TILE_WIDTH * 5, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        woolBrown = sheet.crop(TILE_WIDTH * 6, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        woolYellow = sheet.crop(TILE_WIDTH * 7, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        woolBlue = sheet.crop(TILE_WIDTH * 8, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        woolTurquoise = sheet.crop(TILE_WIDTH * 9, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        woolViolet = sheet.crop(TILE_WIDTH * 10, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        woolDarkPink = sheet.crop(TILE_WIDTH * 11, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        woolDarkTurquoise = sheet.crop(TILE_WIDTH * 12, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        woolOrange = sheet.crop(TILE_WIDTH * 13, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        woolGrey = sheet.crop(TILE_WIDTH * 14, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        woolWhite = sheet.crop(TILE_WIDTH * 15, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);

        apple = sheet.crop(0, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT);
        snakeBall = sheet.crop(TILE_WIDTH, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT);
    }

}
