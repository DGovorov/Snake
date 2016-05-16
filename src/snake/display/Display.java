package snake.display;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Dim on 05.05.2016.
 */
public class Display {

    private JFrame frame;
    private Canvas canvas;

    private int width;
    private int height;

    public Display(String title, int width, int height) {
        this.width = width;
        this.height = height;

        createDisplay(title);
    }

    private void createDisplay(String title) {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }

}
