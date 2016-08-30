package bondarmih.edu.persistence.serializer.binary;

import bondarmih.edu.catalog.Catalog;
import bondarmih.edu.persistence.dataobjects.CatalogDataObject;
import bondarmih.edu.persistence.serializer.CatalogSerializer;
import bondarmih.edu.util.BinaryCatalogReaderWriter;

/**
 * Created by bondarm on 11.06.16.
 */
public class CatalogBinarySerializer implements CatalogSerializer {
    private static final String FILENAME = "./catalogBinary.catalog";
    CatalogDataCollector processor = new CatalogDataCollector();

    @Override
    public void serializeCatalog(Catalog serializedItem) {
        CatalogDataObject catalogDO = processor.toCatalogDO(serializedItem);
        BinaryCatalogReaderWriter.writeToFile(catalogDO, FILENAME);
    }

    @Override
    public Catalog deserializeCatalog() {
        CatalogDataObject catalogDO = BinaryCatalogReaderWriter.readFromFile(FILENAME);
        return processor.toCatalog(catalogDO);
    }
}
