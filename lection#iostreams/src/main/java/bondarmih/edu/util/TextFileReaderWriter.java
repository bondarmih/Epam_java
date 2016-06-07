package bondarmih.edu.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bondarm on 25.05.16.
 */
public class TextFileReaderWriter {
    private static String filePath = "./";

    public static List<String> readFromFile (String fileName) {
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

    public static void writeToFile (List<String> dataStringList, String fileName) {
        try {
            BufferedWriter textFileWriter = new BufferedWriter(new FileWriter(new File(filePath+fileName)));
            for (String currentLine : dataStringList) {
                textFileWriter.write(currentLine);
                textFileWriter.newLine();
            }
            textFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
