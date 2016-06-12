package bondarmih.edu.application;


import bondarmih.edu.catalog.Catalog;
import bondarmih.edu.persistence.serializator.CatalogSerializer;
import bondarmih.edu.persistence.serializator.binarySerializator.CatalogBinarySerializer;
import bondarmih.edu.util.CatalogCreator;
import bondarmih.edu.persistence.serializator.txtserializator.CatalogTextSerializer;

/**
 * Created by bondarm on 05.06.16.
 */
public class Runner extends Thread {
    public void run() {
        Catalog catalog = CatalogCreator.createCatalog();
        CatalogSerializer catalogSerializer = new CatalogBinarySerializer();
        catalogSerializer.serializeCatalog(catalog);
        Catalog elseCatalog = catalogSerializer.deserializeCatalog();
        CatalogSerializer elseCatalogSerializer = new CatalogTextSerializer();
        elseCatalogSerializer.serializeCatalog(elseCatalog);
        Catalog oneMoreCatalog = elseCatalogSerializer.deserializeCatalog();
    }
}
