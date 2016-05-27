package snake.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dim on 14.05.2016.
 */
public class Utils {

    public static void main(String[] args) {
        loadWorlds();
    }

    public static List<File> loadWorlds() {
        File folder = new File("res/worlds");
        List<File> worlds = new ArrayList<>();
        for (File file : folder.listFiles()) {
            worlds.add(file);
            System.out.println(file.toString());
        }

        return worlds;
    }

    public static String loadFileAsString(String path) {

        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public static boolean saveStringToFile(List<String> world, String fileName) {
        String path = "res/worlds/" + fileName + ".txt";
        File file = new File(path);
        if (!file.exists()) {
            try (Writer writer = new BufferedWriter(new FileWriter(file, true))) {
                for (String string : world) {
                    writer.write(string);
                    writer.write("\n");
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static int parseInt(String number) {
        return Integer.parseInt(number);
    }

}
