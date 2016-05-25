package snake.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Dim on 05.05.2016.
 */
public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;

    public KeyManager() {
        keys = new boolean[256];
        right = true;
    }

    public void tick() {
        //KeyEvent event = new KeyEvent();

        up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];

    }

    @Override
    public void keyPressed(KeyEvent e) {

        //TODO: nullify all the directions except just one and move and move "choosing" logic to Snake class
        int keyCode = e.getKeyCode();
        if ((keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) && !left) {
            clearUpAndDown();
            keys[keyCode] = true;
        }
        if ((keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) && !right) {
            clearUpAndDown();
            keys[keyCode] = true;
        }
        if ((keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) && !up) {
            clearLeftAndRight();
            keys[keyCode] = true;
        }
        if ((keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) && !down) {
            clearLeftAndRight();
            keys[keyCode] = true;
        }
    }

    private void clearLeftAndRight() {
        keys[KeyEvent.VK_D] = false;
        keys[KeyEvent.VK_A] = false;
        keys[KeyEvent.VK_LEFT] = false;
        keys[KeyEvent.VK_RIGHT] = false;
    }

    private void clearUpAndDown() {
        keys[KeyEvent.VK_W] = false;
        keys[KeyEvent.VK_S] = false;
        keys[KeyEvent.VK_DOWN] = false;
        keys[KeyEvent.VK_UP] = false;
    }

    public void resetSnakeControlls() {
        clearLeftAndRight();
        clearUpAndDown();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
