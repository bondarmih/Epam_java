package bondarmih.edu.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Stream;

/**
 * Created by bondarm on 25.05.16.
 */
public class TextFileReader {
    private static String filePath = "./src/main/resources/";

    public static ArrayList<String> readFromFile (String fileName) {
        try {
            BufferedReader textFileReader = new BufferedReader(new FileReader(filePath+fileName));
            String currentLine;
            ArrayList<String> result = new ArrayList<>();
            while ((currentLine = textFileReader.readLine()) != null) {
                result.add(currentLine);
            }
            textFileReader.close();
            return result;
        }
        catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found.");
        }
        catch (IOException e) {
            System.out.println("IO error while reading from file " + fileName);
        }
        return null;
    }
}
