package bondarmih.edu.persistence.serializator.binarySerializator;

import bondarmih.edu.catalog.Catalog;
import bondarmih.edu.persistence.dataobjects.CatalogDataObject;
import bondarmih.edu.persistence.serializator.CatalogSerializer;
import bondarmih.edu.util.BinaryCatalogReaderWriter;

import java.io.Serializable;

/**
 * Created by bondarm on 11.06.16.
 */
public class CatalogBinarySerializer implements CatalogSerializer, Serializable {
    public static String filename = "catalogBin.catalog";

    @Override
    public void serializeCatalog(Catalog serializedItem) {
        CatalogDataObject catalogDO = CatalogDataCollector.toCatalogDO(serializedItem);
        BinaryCatalogReaderWriter.writeToFile(catalogDO, filename);
    }

    @Override
    public Catalog deserializeCatalog() {
        CatalogDataObject catalogDO = BinaryCatalogReaderWriter.readFromFile(filename);
        return CatalogDataCollector.toCatalog(catalogDO);
    }
}
