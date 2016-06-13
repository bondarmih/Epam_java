package bondarmih.edu.util;

import bondarmih.edu.persistence.dataobjects.CatalogDataObject;

import java.io.*;

/**
 * Created by bondarm on 12.06.16.
 */
public class BinaryCatalogReaderWriter {
    public static void writeToFile(CatalogDataObject catalog, String fileName) throws IllegalStateException {

        try {
            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new BufferedOutputStream(
                                    new FileOutputStream(fileName)));
            out.writeObject(catalog);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can not write to file " + fileName + ".");
        }
    }

    public static CatalogDataObject readFromFile(String filename) throws IllegalStateException {
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(filename)));
            CatalogDataObject catalogDO = (CatalogDataObject)in.readObject();
            in.close();
            return catalogDO;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can not read from file " + filename +".");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException("File" + filename + " not found.");
        }
    }

}
