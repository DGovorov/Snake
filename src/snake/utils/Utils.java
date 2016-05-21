package snake.utils;

import java.io.*;
import java.util.List;

/**
 * Created by Dim on 14.05.2016.
 */
public class Utils {

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

    public static void saveStringToFile(List<String> world, String fileName){
        String path = "res/worlds/" + fileName + ".txt";
        //TODO: make fileExistsCheck()
        File file = new File(path);
        try (Writer writer = new FileWriter(file, true)){
            for (String string : world) {
                writer.write(string);
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int parseInt(String number) {
        return Integer.parseInt(number);
    }

}
