package snake.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Created by Dim on 15.05.2016.
 */
public class UIImageButton extends UIObject{

    private Map<Boolean, BufferedImage> images;
    private ClickListener clicker;

    public UIImageButton(int x, int y, int width, int height, Map<Boolean, BufferedImage> images, ClickListener clicker) {
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;
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
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }

}
