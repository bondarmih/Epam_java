package bondarmih.edu.serializator;

import bondarmih.edu.catalog.Catalog;
import bondarmih.edu.util.TextFileReaderWriter;

import java.io.*;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by bondarm on 06.06.16.
 */
public class CatalogSerializatorImpl implements CatalogSerializator {
    public void serializeCatalog(Catalog catalog) {
        TextFileReaderWriter.writeToFile(catalog.toStringList(), fileName);
    }

    public Catalog deserializeCatalog() {
        List<String> catalogStringList = TextFileReaderWriter.readFromFile(fileName);
        Catalog result = new CatalogItemParser().parseCatalog(catalogStringList);
        if (result != null) {
            return result;
        } else {
            throw new IllegalStateException("Catalog is not serialized");
        }
    }
}
