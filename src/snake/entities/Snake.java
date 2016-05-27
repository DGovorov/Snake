package snake.entities;

import snake.Handler;
import snake.input.Direction;
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
    private int speed = 5;
    private int tick = 0;
    private boolean dead;
    private boolean victorious;
    private List<ScoringAnimation> scorings;
    private List<Direction> directions;

    public Snake(Handler handler, int xCoor, int yCoor) {
        super(handler, xCoor, yCoor);
        snake = new ArrayList<>();
        snake.add(new BodyPart(handler, xCoor, yCoor));
        this.size = 0;
        scorings = new ArrayList<>();
        directions = new ArrayList<>(2);
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

        if (tick < speed){
            tick++;
            return;
        } else {
            tick = 0;
        }
        move();


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

        snake.add(snakeHead);
        meetAppleCheck();

        tickScoringAnimations();

        //movement logic
        if (snake.size() > size) {
            snake.remove(0);
        }

        //temporary speed change during the game.
        if (size == 8) {
            setSpeed(4);
        }
        if (size == 14) {
            setSpeed(3);
        }

    }

    private void move() {
        if(directions.isEmpty()){
            return;
        }

        Direction direction = directions.get(0);
        switch (direction) {
            case LEFT:
                xCoor -= 20;
                break;
            case RIGHT:
                xCoor += 20;
                break;
            case UP:
                yCoor -= 20;
                break;
            case DOWN:
                yCoor += 20;
                break;
            default:
                break;
        }
        directions.remove(0);
    }

    public void setSpeed(int speed){
        if (speed > 0 && speed < 20) {
            this.speed = speed;
        }
    }

    private void tickScoringAnimations() {
        Iterator<ScoringAnimation> i = scorings.iterator();
        while (i.hasNext()) {
            ScoringAnimation scoring = i.next();
            scoring.tick();
            if (!scoring.isGoing()) {
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
        Direction up = Direction.UP;
        Direction down = Direction.DOWN;
        Direction left = Direction.LEFT;
        Direction right = Direction.RIGHT;

        /*if (manager.up) {
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
        }*/

        if (manager.up && !directions.contains(up)) {
            directions.add(up);
        }
        if (manager.down && !directions.contains(down)) {
            directions.add(down);
        }
        if (manager.left && !directions.contains(left)) {
            directions.add(left);
        }
        if (manager.right && !directions.contains(right)) {
            directions.add(right);
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

        public boolean isGoing() {
            return (lifetime > 0);
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
