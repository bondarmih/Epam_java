package bondarmih.edu.persistence.serializer.binary;

import bondarmih.edu.catalog.Catalog;
import bondarmih.edu.persistence.dataobjects.CatalogDataObject;
import bondarmih.edu.persistence.serializer.CatalogSerializer;
import bondarmih.edu.util.BinaryCatalogReaderWriter;

import java.io.Serializable;
import java.io.StringReader;

/**
 * Created by bondarm on 11.06.16.
 */
public class CatalogBinarySerializer implements CatalogSerializer {
    private static final String filename = "./catalogBinary.catalog";
    CatalogDataCollector processor = new CatalogDataCollector();

    @Override
    public void serializeCatalog(Catalog serializedItem) {
        CatalogDataObject catalogDO = processor.toCatalogDO(serializedItem);
        BinaryCatalogReaderWriter.writeToFile(catalogDO, filename);
    }

    @Override
    public Catalog deserializeCatalog() {
        CatalogDataObject catalogDO = BinaryCatalogReaderWriter.readFromFile(filename);
        return processor.toCatalog(catalogDO);
    }
}
