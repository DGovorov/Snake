package snake.gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;
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

    public static BufferedImage dirt;
    public static BufferedImage grass;
    public static BufferedImage stone;
    public static BufferedImage apple;
    public static BufferedImage snakeBall;

    public static Map<Boolean, BufferedImage> menuButtonStart;
    public static Map<Boolean, BufferedImage> menuButtonOptions;
    public static Map<Boolean, BufferedImage> menuButtonQuit;
    public static Map<Boolean, BufferedImage> gameButtonRestart;
    public static Map<Boolean, BufferedImage> gameButtonMenu;

    public static void init() {
        cropTiles();

        cropButtons();

        menuBackground = ImageLoader.loadImage("/textures/menuBackground.png");
    }

    private static void cropButtons() {
        SpriteSheet sheet;
        sheet = new SpriteSheet(ImageLoader.loadImage("/textures/gamebuttons.png"));
        menuButtonStart = new HashMap<>();
        menuButtonStart.put(false, sheet.crop(0, 0, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        menuButtonStart.put(true, sheet.crop(0, MENU_BUTTON_HEIGHT, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        menuButtonOptions = new HashMap<>();
        menuButtonOptions.put(false, sheet.crop(0, MENU_BUTTON_HEIGHT * 2, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        menuButtonOptions.put(true, sheet.crop(0, MENU_BUTTON_HEIGHT * 3, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        menuButtonQuit = new HashMap<>();
        menuButtonQuit.put(false, sheet.crop(0, MENU_BUTTON_HEIGHT * 4, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        menuButtonQuit.put(true, sheet.crop(0, MENU_BUTTON_HEIGHT * 5, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        gameButtonRestart = new HashMap<>();
        gameButtonRestart.put(false, sheet.crop(172, 0, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        gameButtonRestart.put(true, sheet.crop(172, MENU_BUTTON_HEIGHT, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        gameButtonMenu = new HashMap<>();
        gameButtonMenu.put(false, sheet.crop(172, MENU_BUTTON_HEIGHT * 2, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
        gameButtonMenu.put(true, sheet.crop(172, MENU_BUTTON_HEIGHT * 3, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT));
    }

    private static void cropTiles() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        dirt = sheet.crop(0, 0, TILE_WIDTH, TILE_HEIGHT);
        grass = sheet.crop(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT);
        stone = sheet.crop(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT);
        apple = sheet.crop(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        snakeBall = sheet.crop(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
    }

}
