package bondarmih.edu.application;


import bondarmih.edu.catalog.Catalog;
import bondarmih.edu.util.CatalogCreator;
import bondarmih.edu.serializator.CatalogSerializatorImpl;
import bondarmih.edu.util.TextFileReaderWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bondarm on 05.06.16.
 */
public class Runner extends Thread {
    public void run() {
        Catalog catalog = CatalogCreator.createCatalog();
        CatalogSerializatorImpl catalogSerializator = new CatalogSerializatorImpl();
        catalogSerializator.serializeCatalog(catalog);
        Catalog elseCatalog = catalogSerializator.deserializeCatalog();
        elseCatalog.printCatalog();
    }
}
