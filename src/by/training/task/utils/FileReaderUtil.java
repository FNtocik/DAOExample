package by.training.task.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Util to read all information from file
 *
 * @author Anton Puhachou
 */
public class FileReaderUtil {

    /**
     * Method of obtaining all strings from specific file without tabs and spaces
     * @return information from file
     * */
    public static String readAllFromFile(String path, String fileName) {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(path + fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        data = data.replaceAll("\\s{2,}", "");
        return data;
    }
}
