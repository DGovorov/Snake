package snake.ui;

import java.awt.*;

/**
 * Created by Dim on 27.05.2016.
 */
public class PopupMessage extends UIObject {

    private String message;
    private Color color;

    public PopupMessage(String message, Color color) {
        super(270, 150, 0, 0);
        this.message = message;
        this.color = color;
    }

    public PopupMessage(String message) {
        this(message, Color.BLACK);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        Font font = new Font("Serif", Font.PLAIN, 46);

        coordinatesCorrection(g, font);

        g.setFont(font);
        g.setColor(color);
        g.drawString(message, x, y);
    }

    /**
     * different fonts produce letters with different width/height, and this method is needed to center the text properly
     *
     * @param g
     * @param font
     */
    private void coordinatesCorrection(Graphics g, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        x = (640 - metrics.stringWidth(message)) / 2;
        y = ((300 - metrics.getHeight()) / 2) + metrics.getAscent();
    }

    @Override
    public void onClick() {

    }
}