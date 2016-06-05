package bondarmih.edu.application;


import bondarmih.edu.catalog.Catalog;
import bondarmih.edu.util.CatalogCreator;
import bondarmih.edu.serializator.JsonCatalogSerializator;

/**
 * Created by bondarm on 05.06.16.
 */
public class Runner extends Thread {
    public void run() {
        Catalog catalog = CatalogCreator.createCatalog();
        JsonCatalogSerializator jsonCatalogSerializator = new JsonCatalogSerializator();
        jsonCatalogSerializator.serialize(catalog);
        Catalog elseCatalog = jsonCatalogSerializator.deserialize();
        elseCatalog.printCatalog();
    }
}
