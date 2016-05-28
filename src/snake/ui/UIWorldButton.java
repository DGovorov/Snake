package snake.ui;

import snake.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

/**
 * Created by Dim on 27.05.2016.
 */
public class UIWorldButton extends UIObject {

    private File worldFile;
    private String worldName;
    private ClickListener clicker;
    private Map<Boolean, BufferedImage> images;
    private static int bWidth = 200;
    private static int bHeight = 40;
    private Font font;

    public UIWorldButton(int x, int y, File worldFile, ClickListener clicker) {
        super(x, y, bWidth, bHeight);
        this.worldFile = worldFile;
        this.clicker = clicker;
        images = Assets.worldButton;
        font = new Font("Serif", Font.PLAIN, 20);

        worldName = worldFile.toString();
        worldName = worldName.replace("res\\worlds\\", "");
        worldName = worldName.replace("res/worlds/", "");
        worldName = worldName.replace(".txt", "");

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if (hovering){
            g.drawImage(images.get(true), x, y, null);
        } else {
            g.drawImage(images.get(false), x, y, null);
        }

        g.setColor(Color.black);
        g.setFont(font);

        FontMetrics metrics = g.getFontMetrics(font);
        int textX = x + bWidth/2 - (metrics.stringWidth(worldName)) / 2;
        int textY = y + bHeight/2 - ((metrics.getHeight()) / 2) + metrics.getAscent();

        g.drawString(worldName, textX, textY);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
