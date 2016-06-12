package bondarmih.edu.util;

import bondarmih.edu.persistence.dataobjects.CatalogDataObject;

import java.io.*;

/**
 * Created by bondarm on 12.06.16.
 */
public class BinaryCatalogReaderWriter {
    public static void writeToFile(CatalogDataObject catalog, String filename) {

        try {
            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new BufferedOutputStream(
                                    new FileOutputStream(filename)));
            out.writeObject(catalog);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CatalogDataObject readFromFile(String filename) {
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(filename)));
            CatalogDataObject catalogDO = (CatalogDataObject)in.readObject();
            in.close();
            return catalogDO;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
