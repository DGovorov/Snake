package snake.gfx;

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

    public static BufferedImage tilePalette;

    public static void init() {
        cropMainSheet();

        cropButtons();

        cropAppleRotation();

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/tilePalette.png"));
        tilePalette = sheet.crop(0, 0, 250, 100);

        menuBackground = ImageLoader.loadImage("/textures/menuBackground.png");
    }

    private static void cropAppleRotation() {
        int width = 61;
        int height = 87;

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/appleRotation.png"));
        rotatingApple = new ArrayList<>();
        for (int i = 0; i < 33; i++) {
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
    }

    private static void cropMainSheet() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        dirt = sheet.crop(0, 0, TILE_WIDTH, TILE_HEIGHT);
        grass = sheet.crop(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT);
        stone = sheet.crop(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT);
        sand = sheet.crop(TILE_WIDTH * 3, 0, TILE_WIDTH, TILE_HEIGHT);
        water = sheet.crop(TILE_WIDTH * 4, 0, TILE_WIDTH, TILE_HEIGHT);
        mossStone = sheet.crop(TILE_WIDTH * 5, 0, TILE_WIDTH, TILE_HEIGHT);

        apple = sheet.crop(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        snakeBall = sheet.crop(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
    }

}
