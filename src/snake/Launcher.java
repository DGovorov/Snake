package snake;

/**
 * Created by Dim on 05.05.2016.
 */
public class Launcher {
    public static void main(String[] args) {
        String version = "0.8.9";
        new Game("Snake Game " + version, 640, 360).start();
    }
}
