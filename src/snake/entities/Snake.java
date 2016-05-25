package snake.entities;

import snake.Handler;
import snake.input.KeyManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dim on 07.05.2016.
 */
public class Snake extends Entity {

    private List<BodyPart> snake;
    private int size;
    private boolean dead;
    private boolean victorious;
    private List<ScoringAnimation> scorings;

    public Snake(Handler handler, int xCoor, int yCoor) {
        super(handler, xCoor, yCoor);
        snake = new ArrayList<>();
        snake.add(new BodyPart(handler, xCoor, yCoor));
        this.size = 0;
        scorings = new ArrayList<>();
    }

    public Snake(Handler handler, int xCoor, int yCoor, int size) {
        this(handler, xCoor, yCoor);
        this.size = size;
    }

    public void tick() {

        if (dead) {
            return;
        }

        if (levelCompleteCheck()) {
            return;
        }

        getInput();
        BodyPart snakeHead = new BodyPart(handler, xCoor, yCoor);

        if (hitScreenBordersCheck()) {
            return;
        }

        if (hitSolidTileCheck()) {
            return;
        }

        if (hitSelfCheck()) {
            return;
        }

        meetAppleCheck();

        tickScoringAnimations();

        //movement logic
        snake.add(snakeHead);
        if (snake.size() > size) {
            snake.remove(0);
        }

    }

    private void tickScoringAnimations() {
        Iterator<ScoringAnimation> i = scorings.iterator();
        while (i.hasNext()) {
            ScoringAnimation scoring = i.next();
            scoring.tick();
            if (scoring.getLifetime() <= 0) {
                i.remove();
            }
        }
    }

    private boolean levelCompleteCheck() {
        if (size >= 20) {
            dead = true;
            victorious = true;
            handler.getState().createUIManager();
            return true;
        }
        return false;
    }

    private void meetAppleCheck() {
        if (handler.getApple().xCoor == xCoor && handler.getApple().yCoor == yCoor) {
            handler.getApple().respawn();
            size++;
            scorings.add(new ScoringAnimation());
        }
    }

    private boolean hitSelfCheck() {
        for (int i = 0; i < snake.size() - 3; i++) {
            if (xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()) {
                dead = true;
                handler.getState().createUIManager();
                return true;
            }
        }
        return false;
    }

    private boolean hitSolidTileCheck() {
        if (handler.getTile(xCoor / 20, yCoor / 20).isSolid()) {
            dead = true;
            handler.getState().createUIManager();
            return true;
        }
        return false;
    }

    private boolean hitScreenBordersCheck() {
        if (xCoor < 0 || yCoor < 0 || xCoor >= handler.getWidth() || yCoor >= handler.getHeight()) {
            dead = true;
            handler.getState().createUIManager();
            return true;
        }
        return false;
    }

    @Override
    public void render(Graphics g) {
        for (BodyPart snakePart : snake) {
            snakePart.render(g);
        }

        for (ScoringAnimation scoring : scorings) {
            scoring.render(g);
        }
    }

    private void getInput() {
        KeyManager manager = handler.getKeyManager();

        if (manager.up) {
            yCoor -= 20;
        }
        if (manager.down) {
            yCoor += 20;
        }
        if (manager.left) {
            xCoor -= 20;
        }
        if (manager.right) {
            xCoor += 20;
        }
    }

    public List<BodyPart> getParts() {
        return snake;
    }

    public boolean isVictorious() {
        return victorious;
    }

    private class ScoringAnimation {
        private int lifetime;
        private int animationX;
        private int animationY;
        private String score;

        public ScoringAnimation() {
            lifetime = 15;
            animationX = xCoor;
            animationY = yCoor;
            score = Integer.toString(size);

            correctCoords();
        }

        public int getLifetime() {
            return lifetime;
        }

        public void correctCoords() {
            if (animationX < 50) {
                animationX = 50;
            }
            if (animationY < 50) {
                animationY = 50;
            }
        }

        public void tick() {
            if (lifetime > 0) {
                lifetime--;
            }
            animationY--;
        }

        public void render(Graphics g) {
            g.setColor(Color.YELLOW);
            g.drawString(score, animationX, animationY);
        }
    }

}
