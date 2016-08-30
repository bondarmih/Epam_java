package bondarmih.edu.application;


import bondarmih.edu.catalog.Catalog;
import bondarmih.edu.persistence.serializer.CatalogSerializer;
import bondarmih.edu.persistence.serializer.binary.CatalogBinarySerializer;
import bondarmih.edu.util.CatalogCreator;
import bondarmih.edu.persistence.serializer.txt.CatalogTextSerializer;

/**
 * Created by bondarm on 05.06.16.
 */
public class Runner extends Thread {
    public void run() {
        Catalog catalog = CatalogCreator.getCatalogInstance(10,2,5);
        Catalog catalogFromTxt = processCatalog(catalog, new CatalogTextSerializer());
        Catalog catalogFromBinary = processCatalog(catalog, new CatalogBinarySerializer());
    }

    private Catalog processCatalog(Catalog catalog, CatalogSerializer catalogSerializer) {
        try {
            catalogSerializer.serializeCatalog(catalog);
            Catalog result = catalogSerializer.deserializeCatalog();
            System.out.println("Catalog processed with " + catalogSerializer.getClass());
            return result;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Error while processing catalog with " + catalogSerializer.getClass());
        }
        return null;
    }
}
