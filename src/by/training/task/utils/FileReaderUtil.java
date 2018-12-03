package by.training.task.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReaderUtil {

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
